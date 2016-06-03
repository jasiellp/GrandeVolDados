package teste;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 *create table produtos
(
		id_produto 			int,
		identificador 		varchar(190) NOT NULL PRIMARY KEY,
		valor 				numeric,
		data_anuncio 		datetime,
		categoria 			varchar(1290),
		link 					varchar(1290),
		tipo_produto 		varchar(290),
		titulo 				varchar(1290),
		imagem 				varchar(1290),
		descricao 			varchar(1290),
		contato 				varchar(90),
		estado 				varchar(90),
		cidade 				varchar(90),
		ddd 					varchar(10),
		data_hoje 			datetime,
		fim 					varchar(10),
		valor_mercado     numeric
)

 
 */

public class Captacao
{

	@SuppressWarnings("deprecation")
	public static void consulta(String url) throws ClientProtocolException, IOException{
		String USER_AGENT = "Mozilla/5.0";
		

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String linha = "";

		//BufferedWriter buffWrite = new BufferedWriter( new FileWriter(testefeliz.class.getResource("").getFile().toString().concat("NewFile.html")));
		ArrayList<Produto> list = new ArrayList<Produto>();
		ArrayList<String> buf = new ArrayList<String>();
		
		
		while ((linha = rd.readLine()) != null)
		{
			buf.add(linha.concat(" \n")); 
		//	buffWrite.append(linha.concat(" \n"));
		}
		
		//buffWrite.close();
		
		String link 		= null,
			titulo 			= null, 
			data 			= null,
			hora 			= null, 
			valor_anuncio 	= "0",
			imagem 			= null,
			categoria 		= null;
		
		
		for (int i = 0; i < buf.size(); i++)
		{	
			if(buf.get(i).contains("<li class=\"item\">") &&  buf.get(i+1).contains("OLXad-list-link") )	
			{ 
				 
				ArrayList<String> sb_temp = new ArrayList<String>();
				boolean ent = true;
				valor_anuncio 	= "0";
				
					int indice      = 0,
						i_imagem    = 0,
						i_titulo    = 0,
						i_estado    = 0,
						i_cidade    = 0,
						i_ddd 		= 0,
						i_categoria = 0,
						i_preco     = 0,
						i_data 		= 0,
						i_hora 		= 0;
				
				String validator="",data_anuncio =
				null ;
				
				while(ent)
				{
					 					
						validator=buf.get(i+indice);
						
						
						if(!validator.trim().equals(""))
						{
							if(buf.get(i+indice).contains("</li>"))
							{
								ent=false;
							}
							
							sb_temp.add(validator);
							
							if(validator.contains("OLXad-list-image-box"))
								i_imagem=sb_temp.size();
							
							if(validator.contains("OLXad-list-title mb5px"))
								i_titulo=sb_temp.size();
							
							if(validator.contains("text detail-region"))
							{
								i_estado=sb_temp.size();
								i_cidade=sb_temp.size()+1;
								i_ddd=sb_temp.size()+3;
							}
							
							if(validator.contains("text detail-category"))
								i_categoria=sb_temp.size();
							
							if(validator.contains("OLXad-list-price"))
								i_preco=sb_temp.size();
							
							if(validator.contains("col-4"))
							{
								i_data=sb_temp.size();
								i_hora=sb_temp.size()+1;
							}
						}
						 
					
					indice++;
				}
				
				
				Produto p = new Produto();
				 
				String link_temp = sb_temp.get(1).trim().split("title")[0];
				link   = sb_temp.get(1).trim().substring(157,link_temp.length()-2);
				
				if(i_titulo>0)
				titulo = sb_temp.get(i_titulo).trim();
			
				if(i_imagem>0)
				{
					imagem = sb_temp.get(i_imagem).trim().contains("sem foto")?"Sem Foto":sb_temp.get(i_imagem).trim().split("alt")[0].substring(24,sb_temp.get(i_imagem).trim().split("alt")[0].length()-2);
					 
					
				}
				if(i_preco>0)
				valor_anuncio = sb_temp.get(i_preco-1).trim().substring(32,  sb_temp.get(i_preco-1).trim().length()).replaceAll("</p>", "");
				
				if(i_data>0)
				{
					data =  sb_temp.get(i_data).trim().substring(22, sb_temp.get(i_data).trim().length()).replaceAll("</p>", "");
					hora = 	sb_temp.get(i_hora).trim().substring(22, sb_temp.get(i_hora).trim().length()).replaceAll("</p>", "");

					Date date = new  Date();
					SimpleDateFormat formato_data = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					
					
					 
					date.setHours(Integer.parseInt(hora.substring(0, 2)));
					date.setMinutes(Integer.parseInt(hora.substring(3, 5)));
					
					 
					
					if(data.equalsIgnoreCase("HOJE"))
					{
						data_anuncio =formato_data.format(date);
					}
					  data_anuncio = data.equalsIgnoreCase("HOJE")?formato_data.format(date):formato_data.format(date);
					
				 
				}
				
				if(data.equals("Hoje"))
						System.out.println(data);



				if(i_categoria>0)
				{
					categoria = sb_temp.get(i_categoria).trim();	
				}
				
				
				 
				
				
				p.setData_anuncio(data_anuncio);
				 
				 
				p.setValor(Double.parseDouble(valor_anuncio.contains(".")?valor_anuncio.replace(".", ""):valor_anuncio));
				
				p.setCategoria(categoria);
				
				if(i_ddd>0)
				p.setDdd(sb_temp.get(i_ddd).trim().replaceAll("DDD", ""));
				
				if(i_cidade>0)
				p.setCidade(sb_temp.get(i_cidade).trim().replaceAll(",", ""));
				
				if(i_estado>0)
				p.setEstado(sb_temp.get(i_estado).trim().replaceAll(",", ""));
			
				p.setLink(link);
				p.setTitulo(titulo);
				p.setImagem(imagem.replaceAll("src=", ""));
				list.add(p);
				sb_temp.clear();
			}
			
		}
		
		buf.clear();
		 
		for(Produto p : list )
		{
			System.out.println(p.getLink());	
			
			System.out.println(p.getTitulo());
			
			insert(p);
		}

		list.clear();
		
	}
	
	
	public static void executar(int inicio,int fim) throws ClientProtocolException, IOException
	{
		for(int i = inicio; i <= fim; i++)
		{
			consulta("http://sp.olx.com.br/?o=".concat(String.valueOf(i)));
		} 
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException
	{		
		for(int i =2; i <= 800; i++)
		{
			consulta("http://sp.olx.com.br/?o=".concat(String.valueOf(i)));
		} 
	} 
	
	
	private static void insert(Produto p)
	{
		StringBuffer d = new StringBuffer();
		 
		
		
		d.append("  INSERT INTO `produtos` ").append(" \n "); 
		d.append(" (`id_produto`, `identificador`, `valor`, `data_anuncio`, `categoria`, `link`, ").append(" \n "); 
		d.append(" `tipo_produto`, `titulo`, `imagem`, `descricao`, `contato`, `estado`,  ").append(" \n ");
		d.append("  `cidade`, `ddd`,`fim`)  ").append(" \n ");
		d.append(" 	VALUES ((select nextval('MovieSeq') ),  ").append(" \n ");
		d.append(" '").append(p.getIdentificador()).append("',   ").append(" \n ");
		d.append("  ").append(p.getValor()).append(",  ").append(" \n "); 
		d.append("  '").append(p.getData_anuncio()).append("',   ").append(" \n ");
		d.append("  '").append(p.getCategoria()).append("',   ").append(" \n ");
		d.append("  '").append(p.getLink()).append("',   ").append(" \n ");
		d.append("  'xyz',  ").append(" \n ");
		d.append("  '").append(p.getTitulo()).append("',   ").append(" \n ");
		d.append("  '").append(p.getImagem()).append("',  ").append(" \n ");
		d.append("  '").append(p.getDescricao()).append("',   ").append(" \n ");
		d.append("  'zzz',   ").append(" \n ");
		d.append("  '").append(p.getEstado()).append("',   ").append(" \n ");
		d.append("  '").append(p.getCidade()).append("',   ").append(" \n ");
		d.append("  '").append(p.getDdd()).append("','Nao');  ").append(" \n ");

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
