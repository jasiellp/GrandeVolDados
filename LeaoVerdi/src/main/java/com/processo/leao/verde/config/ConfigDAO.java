package com.processo.leao.verde.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.processo.leao.verde.util.Conexao;
import com.processo.leao.verde.util.LVLog;

public class ConfigDAO
{
 
	public Object[][] getConfig() throws Exception
	{
		Connection conn = null;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		StringBuilder sql;
		
		
		 sql = new StringBuilder();
		 sql.append("SELECT ").append(" \n ");
		 sql.append("	cf.id_config,   ").append(" \n ");
		 sql.append("	cf.nome,   		").append(" \n ");
		 sql.append("	cf.categoria,   ").append(" \n ");
		 sql.append("	cf.url,   		").append(" \n ");
		 sql.append("	cf.status   	").append(" \n ");
		 
		 sql.append("FROM configure_frame cf ");
	     
		 sql.append("ORDER BY id_config ");
	
		 try
		{
			  conn = Conexao.abrir();
			  comando = conn.prepareStatement(sql.toString());
			  resultado = comando.executeQuery();
		}
		catch (Exception e)
		{ 
			LVLog.logErro(e);
		}
		 
		 resultado.getFetchSize();
		 
		 Object[][] valor = new Object[1+resultado.getFetchSize()][5];
		 int indice = 0;
		 
	     while (resultado.next()) 
	     { 
	    	 
	    	 valor[indice][0]=String.valueOf(resultado.getInt("id_config"));
	    	 valor[indice][1]=String.valueOf(resultado.getString("nome"));
	    	 valor[indice][2]=String.valueOf(resultado.getString("categoria"));
	    	 valor[indice][3]=String.valueOf(resultado.getString("url"));
	    	 valor[indice][4]=Boolean.parseBoolean(resultado.getInt("status")==1?"TRUE":"FALSE"); 
	    
	    	 indice++; 
	     
	     
	     }

	        /* Fecha a conexão */
	        resultado.close();
	        comando.close();
	        conn.close();

		 
		 return valor;

	}
	
}
