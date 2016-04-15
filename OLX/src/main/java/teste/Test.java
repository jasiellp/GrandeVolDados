package teste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.processo.leao.verde.util.DBLeao;

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

		String linha = "";

		BufferedWriter buffWrite = new BufferedWriter( new FileWriter("C:\\Git_Home\\gd\\OLX\\src\\main\\java\\teste\\NewFile.html"));
		ArrayList<Produto> list = new ArrayList<Produto>();
		ArrayList<String> buf = new ArrayList<String>();
		
		
		while ((linha = rd.readLine()) != null)
		{
			buf.add(linha.concat(" \n")); 
		}
		buffWrite.close();
		String link,valor,titulo;
		
		
		for (int i = 0; i < buf.size(); i++)
		{	
			if(buf.get(i).contains("<li class=\"item\">") &&  buf.get(i+1).contains("OLXad-list-link") )	
			{
				Produto p = new Produto();
				 
				valor  = buf.get(i+1).substring(162, buf.get(i+1).length());
				link   = valor.split("title")[0];
			//	link   = link.substring(0, link.length()-2);
				titulo = valor.split("title")[1];
				 
				
				
				p.setLink(link.substring(0, link.length()));
				p.setTitulo(titulo.substring(2, titulo.length()-4));
				
				list.add(p);
			}
			
		}
		
		buf.clear();
		 
		for(Produto p : list )
		{
			System.out.println(p.getLink());	
			
			System.out.println(p.getTitulo());
			
			//insert(p);
		}

		list.clear();
		
	
	} 
	
	
	private static void insert(Produto p)
	{
		StringBuffer d = new StringBuffer();
		 
		
		d.append(" INSERT INTO ").append(" \n"); 
		d.append(" `produto`  ").append(" \n");
		d.append("  (`id_produto`, `data_anuncio`, `link`, `tipo_produto`, `titulo`, `descricao`, `foto`, `contato`) ").append(" \n"); 
		d.append("  VALUES  ").append(" \n");
		d.append("  (0,  ").append(" \n");
		d.append("  '2016-04-14 22:02:32', ").append(" \n"); 
		d.append("  '").append(p.getLink()).append("',  ").append(" \n");
		d.append("  '2',  ").append(" \n");
		d.append("  '").append(p.getTitulo()).append("',  ").append(" \n");
		d.append("  '22',  ").append(" \n");
		d.append("  '22',  ").append(" \n");
		d.append("  '22'); ").append(" \n");

		try
		{
			DBLeao.Insert(d);
			System.out.println("Sucesso");
		}
		catch (Exception e)
		{
				System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}

}
