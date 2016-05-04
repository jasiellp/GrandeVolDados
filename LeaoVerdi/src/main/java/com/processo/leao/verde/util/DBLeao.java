package com.processo.leao.verde.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBLeao
{ 
	public static void Insert(Object query) throws Exception
	{
		  Connection conn;
		  
		  PreparedStatement stmt;
		  
			try
			{
				conn = Conexao.abrir();
				   
				stmt = conn.prepareStatement(query.toString());
				
				stmt.execute();
				
				stmt.close(); 
				
			}
			catch (SQLException  e1)
			{
					System.out.println(e1.getSQLState());
				
			}
			 
	}
}
