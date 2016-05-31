package com.piloto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.processo.leao.verde.util.DBLeao;

/***
 * 
 * @author CPQi
 *
 *
 *
 *         create table produtos ( id_produto int, identificador varchar(190),
 *         valor numeric, data_anuncio datetime, categoria varchar(1290), link
 *         varchar(1290), tipo_produto varchar(290), titulo varchar(1290),
 *         imagem varchar(1290), descricao varchar(1290), contato varchar(90),
 *         estado varchar(90), cidade varchar(90), ddd varchar(10)
 * 
 *         )
 */

public class conSu {
 
	public static void consulta(String url, String identificador,String titulo) throws ClientProtocolException, IOException 
	{
		String USER_AGENT = "Mozilla/5.0";

		HttpClient client = HttpClientBuilder.create().build();
		String nova_url =  "http://sp.olx.com.br".concat(url.split("sp.olx.com.br")[1]);
		
		HttpGet request = new HttpGet(nova_url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 
		String linha = "";
		
		boolean fim = false;
		
		while ((linha = rd.readLine()) != null) 
		{ 
			if(linha.contains("foi vendido e o anunciante removeu o anúncio."))
			{
				fim = true;
				break;
			} 
			if(linha.contains(titulo))
			{
				fim = false;
				break;
			}
		} 
		
		
		update(fim, identificador, nova_url);
		
		rd.close();

	}

	
	public static void executar() throws Exception
	{

		

		 
		ResultSet rt  = DBLeao.select("select * from produtos where  fim = 'Nao'");
		
		while (rt.next())
		{
			System.out.println(rt.getString("identificador"));
			consulta(rt.getString("link"),rt.getString("identificador"),rt.getString("titulo"));
		}
		
		
		rt.close();

	}
	
	public static void main(String[] args) throws Exception 
	{
 
		ResultSet rt  = DBLeao.select("select * from produtos where  fim = 'Nao'");
		
		while (rt.next())
		{
			System.out.println(rt.getString("identificador"));
			consulta(rt.getString("link"),rt.getString("identificador"),rt.getString("titulo"));
		}
		
		
		rt.close();
		 
	}

	private static void update(boolean fim, String identificador,String url ) {
		 
	 	
		Date date = new  Date(); 
		
		  
		StringBuffer d = new StringBuffer();
 
		d.append("update produtos set `link` = '"+url+"', `fim` = '");
		d.append(fim?"Sim":"Nao");
		d.append("',`data_hoje`='").append( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
		d.append("' where identificador = '").append(identificador).append("'");
		
		try {
			DBLeao.Insert(d);
			System.out.println("Sucesso");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
