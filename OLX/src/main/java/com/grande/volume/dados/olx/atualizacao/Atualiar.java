package com.grande.volume.dados.olx.atualizacao;

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

public class Atualiar {
 
	public static void consulta(String url, String identificador,String titulo,String descricao) throws ClientProtocolException, IOException 
	{
		String USER_AGENT = "Mozilla/5.0";

		HttpClient client = HttpClientBuilder.create().build();
		String nova_url =  "http://sp.olx.com.br".concat(url.split("sp.olx.com.br")[1]);
		
		HttpGet request = new HttpGet(nova_url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
 
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		 
		String linha = "";
		
		boolean fim = false;
		int count = 0;
		
		while ((linha = rd.readLine()) != null) 
		{
			
			if(linha.contains(titulo) || linha.contains("<meta name") || linha.contains("foi vendido e o anunciante removeu o anúncio."))	
			{
				if(descricao.equals("null"))
				{
					if(linha.contains("<meta name") && linha.contains("description"))
					{
						descricao=linha.split("content")[1];
						descricao=descricao.substring(2, descricao.length()-2);
						break;
					}
							
				} 
				 
			   if(linha.contains("foi vendido e o anunciante removeu o anúncio."))
			   {
					fim = true;
				} 
				if(linha.contains(titulo))
				{
					fim = false;
				}
				if(count <= 400 && !descricao.equals("null"))
					break;
			}
			
			
			count++; 
		} 
		
		rd.close();
		update(fim, identificador, nova_url,descricao);
		
		

	}

	
	public static void executar(int inicio , int fim) throws Exception
	{
		ResultSet rt  = DBLeao.select("select p.identificador, p.link , p.titulo, p.descricao from produtos_ p where p.id >  ".concat(String.valueOf(inicio)));
		
		
		
		//ResultSet rt  = DBLeao.select(" select * from produtos_ p where p.identificador like '%-%'");
		int contador = 0 ;
	 
		
		while (rt.next())
		{
			System.out.println(rt.getString("identificador"));
		
			long tempoInicial = System.currentTimeMillis();
			
			consulta(rt.getString("link"), rt.getString("identificador"), rt.getString("titulo"), rt.getString("descricao"));
			
			contador++;
		
			System.out.println("Foram Atualizados :"+contador );
			System.out.println( new SimpleDateFormat("mm:ss.SSS").format(new Date( System.currentTimeMillis() - tempoInicial)) );
		}
		
		
		rt.close();
		  
	} 
	
	private static void update(boolean fim, String identificador,String url,String descricao ) {
		 
	 	
		Date date = new  Date(); 
		
		  
		StringBuffer d = new StringBuffer();
 
		d.append("update produtos_ set   `fim` = '");
		d.append(fim?"Sim":"Nao");
		d.append("', `descricao`= '").append(descricao);
		d.append("', `identificador`= '").append(identificador.substring(1,identificador.length()));
		d.append("',`data_hoje`='").append( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date));
		d.append("' where identificador = '").append(identificador).append("'");
		
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
