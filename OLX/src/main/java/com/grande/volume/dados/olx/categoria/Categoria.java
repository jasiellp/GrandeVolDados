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
	
	public static void CarregaCategoria() throws Exception
	{
		categorias_= carregaCategoria();
	}
	
	protected static ArrayList<Categoria_> carregaCategoria() throws Exception
	{
		ArrayList<Categoria_> categorias_ = new ArrayList<Categoria_>();
		 
		ResultSet rs = DBLeao.select("select C.ID,C.NOME from categoria c");
		
		while (rs.next())
		{
			categorias_.add(new Categoria_(rs.getInt("id"), rs.getString("nome")));
		}
		
		rs.close();
		
		return categorias_;
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
