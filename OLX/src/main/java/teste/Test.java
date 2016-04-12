package teste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class Test
{

	public static void main(String[] args) throws ClientProtocolException, IOException
	{

		String USER_AGENT = "Mozilla/5.0";
		String url = "http://sp.olx.com.br/";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";

		BufferedWriter buffWrite = new BufferedWriter(
				new FileWriter("C:\\Git_Home\\gd\\OLX\\src\\main\\java\\teste\\NewFile.html"));
		ArrayList<Produto> list = new ArrayList<Produto>();
		boolean inicio = false;
		StringBuffer sb = new StringBuffer();
		while ((line = rd.readLine()) != null)
		{

			if (line.contains("<li class=\"item\">")) inicio = true;
			if (inicio) sb.append(line.concat(" \n"));
			if (line.contains("</li>"))
			{
				inicio = false;
				list.add(new Produto(sb));
				sb.delete(0, sb.length());
			}

		}
		
		for(Produto p : list )
		{
			System.out.println(p.getDescricao());	
		}

		
		buffWrite.close();
	}

	public static void escritor(String path) throws IOException
	{
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = in.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

}
