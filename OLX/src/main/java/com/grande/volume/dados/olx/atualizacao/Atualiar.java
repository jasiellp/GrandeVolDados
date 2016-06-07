package com.grande.volume.dados.olx.atualizacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.grande.volume.dados.olx.util.HtmlConexao;
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

public class Atualiar 
{

	
	private final static int NUM_ATUALIZACAO = 1000;
	
	public static Atualizacao consulta(String url, String identificador,String titulo,String descricao) throws ClientProtocolException, IOException, CloneNotSupportedException, URISyntaxException 
	{ 
		
		long tempoInicialHtml = System.currentTimeMillis();
		
		
		System.out.println("tempoHtml "+new SimpleDateFormat("mm:ss.SSS").format(new Date( System.currentTimeMillis() - tempoInicialHtml)) );
		
		String linha = "";
		
		boolean fim = true;
		int count = 0;
		
		
		long tempoInicialArq = System.currentTimeMillis();
		
		if(descricao.equals("null"))
		{
			
			BufferedReader rd = HtmlConexao.conectar(url);
			HtmlConexao.sair();
			while ((linha = rd.readLine()) != null) 
			{    
				if(linha.contains(titulo))
				{ 
						fim=false;
						break;
				} 
				
				if(descricao.equals("null"))
				{ 
						if(linha.contains("<meta name") && linha.contains("description"))
						{ 
							descricao=linha.split("content")[1].substring(2, descricao.length()-2);
							break;
						}	 
				 
					if(!descricao.equals("null"))
						break;
				}
				 count++;
				 
				 if(count>200) break;
				 
			}
			rd.close();
		}
		else
		{
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) new URL(url).openConnection();
			conn.connect();
			conn.getContent();
			
			fim=(!conn.getURL().toString().contains(identificador));
			
		} 
		 
		System.out.println("tempo Arquivo "+new SimpleDateFormat("mm:ss.SSS").format(new Date( System.currentTimeMillis() - tempoInicialArq)) );
	
		return new Atualizacao(fim, identificador,  url, descricao);
		 
	}
	
	public static int id_nimimo() throws Exception
	{
		ResultSet rt_a  = DBLeao.select(" select min(p.id) as minimo from produtos_ p where p.atualizando = 0 and p.fim = 'Nao'");
		
		int minimo = 0;

		while (rt_a.next())
		{
			minimo =   rt_a.getInt("minimo"); 
		}
		
		rt_a.close();
		
		return minimo;
	}
	
	
	public static void main(String[] args) throws Exception 
	{  
		executar(id_nimimo());
	}

	
	public static void executar(int minimo) throws Exception
	{  
		
		long tempo_total = System.currentTimeMillis() ;
		ResultSet rt = DBLeao.select(
				"select p_.identificador, "
				+ " p_.link, "
				+ " p_.titulo, "
				+ " p_.descricao "
				+ " "
				+ " from produtos_ p_ where p_.fim = 'Nao' and p_.atualizando = 0 and p_.id <= "+(minimo+NUM_ATUALIZACAO));
				 
		
		DBLeao.Insert("update produtos_ set atualizando = 1 where fim = 'Nao' and atualizando = 0 and  id <= "+(minimo+NUM_ATUALIZACAO));
		 
		ArrayList<Atualizacao> atualizacao = new ArrayList<Atualiar.Atualizacao>();
		  
		int contador = 0 , sim = 0, nao = 0 ;
	 
		
		while (rt.next())
		{
			System.out.println(rt.getString("identificador"));
		
			long tempoInicial = System.currentTimeMillis();
		
			try 
			{
				contador++;
				 
				atualizacao.add(consulta(rt.getString("link"), rt.getString("identificador"), rt.getString("titulo"), rt.getString("descricao")));
				
			} 
			catch (Exception e) 
			{
			} 
		
			System.out.println("Foram Atualizados :".concat(String.valueOf(contador)));
			
			System.out.println(new SimpleDateFormat("mm:ss.SSS").format(new Date(System.currentTimeMillis() - tempoInicial)));
		
		} 
		
		rt.close();
		
		System.out.println("Updated ");
		
		
		StringBuffer sb = new StringBuffer();
		
		for(Atualizacao atu : atualizacao)
		{  
			if(atu.isFim())
			{
				sim++;
				  sb.append("update produtos_ set   `fim` = '");
				  sb.append(atu.isFim()?"Sim":"Nao");
				  sb.append("', `descricao`= '").append(atu.getDescricao()); 
				  sb.append("',`data_hoje`='").append( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new  Date()));
				  sb.append("' where identificador = '").append(atu.getIdentificador()).append("';");
				  
				  DBLeao.Insert(sb);
				  
				  sb.delete(0, sb.length());
			}
			else
			{
				nao++;
			}
				 
		} 
		
			System.out.println("-------------------------------------------------------");
			System.out.println("Foi Realizado Varredura em : ".concat(String.valueOf(sim+nao)).concat(" Anuncios."));
			System.out.println("-------------------------------------------------------");
			System.out.println("\n  Total de : ".concat(String.valueOf(sim)).concat(" Anuncios Finalizados."));
			System.out.println("\n \n Total de : ".concat(String.valueOf(nao)).concat(" Anuncios Ativos."));
			System.out.println("-------------------------------------------------------");
			System.out.println(" \n \n \n");
			System.out.println("Tempo Total para a Execucao: "+new SimpleDateFormat("mm:ss.SSS").format(new Date( System.currentTimeMillis() - tempo_total)) );
		DBLeao.sair();
		  
	} 
	
	private static void updated(Atualizacao atu)
	{
		
		long tempoInicial = System.currentTimeMillis();
		
		update(atu.isFim(), atu.getIdentificador(), atu.getUrl(), atu.getDescricao());	
		
		System.out.println("Updated realiazado em: "+ new SimpleDateFormat("mm:ss.SSS").format(new Date( System.currentTimeMillis() - tempoInicial)) );
	}
	
	private static void update(boolean fim, String identificador,String url,String descricao ) {
		  
		  System.out.println("Fim anuncio:"+(fim?"Sim":"Nao"));
		 
		  StringBuffer d = new StringBuffer();
 
		  String[] m = url.split("-");
		  int t = m.length;
		  
		  
		  d.append("update produtos_ set   `fim` = '");
		  d.append(fim?"Sim":"Nao");
		  d.append("', `descricao`= '").append(descricao);
		  d.append("', `identificador`= '").append(m[t-1]);
		  d.append("',`data_hoje`='").append( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new  Date()));
		  d.append("' where identificador = '").append(identificador).append("'");
		
		try 
		{
			DBLeao.Insert(d);
			
			d.delete(0, d.length());
			
			System.out.println("Sucesso");
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	 
	public static class Atualizacao
	{
		
		
		protected boolean fim;
		protected String identificador;
		protected String url;
		protected String descricao;
		
		public boolean isFim() 
		{
			return fim;
		} 

		public String getIdentificador() 
		{
			return identificador;
		}
 
		public String getUrl() 
		{
			return url;
		}
 
		public String getDescricao() 
		{
			return descricao;
		}
		
		
		public Atualizacao(boolean fim, String identificador,String url,String descricao ) 
		{
			this.descricao=descricao;
			this.fim=fim;
			this.identificador=identificador;
			this.url=url;
		
		}
	}

}
