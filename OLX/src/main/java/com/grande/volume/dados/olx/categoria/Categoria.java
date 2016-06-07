package com.grande.volume.dados.olx.categoria;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.processo.leao.verde.util.DBLeao;


/**
 * 
 * @author CPQi
 *
 *<br><br><br>@Tabela
 *
 *<br><br>
 *create table categoria <br>
(<br>
			id int primary key,<br>
			nome varchar(100)<br>
<br>
)<br><br>
 */


public class Categoria 
{	
	protected static ArrayList<Categoria_> categorias_;
	protected static ArrayList<String> link_categorias;
	
	public static void CarregaCategoria() throws Exception
	{
		categorias_= carregaCategoria();
	}
	
	protected static ArrayList<Categoria_> carregaCategoria() throws Exception
	{
		ArrayList<Categoria_> categorias_ = new ArrayList<Categoria_>();
		link_categorias = new ArrayList<String>();
		
		ResultSet rs = DBLeao.select("select c.id,c.nome,c.link from categoria c where c.ligado = 1");
		
		while (rs.next())
		{
			categorias_.add(new Categoria_(rs.getInt("id"), rs.getString("nome")));
			link_categorias.add(rs.getString("link"));
		}
		
		rs.close();
		
		return categorias_;
	}
	
	public static ArrayList<String> getLink_categorias() {
		return link_categorias;
	}

	public static int getCategoria(String SCategoria) throws Exception
	{
		int id=-1;
		
		for(Categoria_ c : categorias_)
		{
			if(c.getNome().equalsIgnoreCase(SCategoria))
			{
				id=c.getId();
				 
				break;
			}
		}
		
		return id==-1?insertCategoria(SCategoria) : id;
	}
	
	protected static int insertCategoria(String nome) throws Exception
	{
		String query = "INSERT INTO  `categoria` ( `id`,`nome`) VALUES (".concat(String.valueOf(categorias_.size()+1)).concat(",'".concat(nome).concat("');"));
		
		DBLeao.Insert(query);
		
		categorias_.add(new Categoria_(categorias_.size()+1, nome));
		
		return categorias_.size();
	}

}
