package com.processo.leao.verde.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
	public static ResultSet select(Object query) throws Exception
	{
		  Connection conn;
		  Statement stmt = null;
		  ResultSet rs = null;
		  
			try
			{
				conn = Conexao.abrir();
				   
				 stmt = conn.createStatement();
				
				 rs = stmt.executeQuery(query.toString());
				 
				//  stmt.close(); 
				
			}
			catch (SQLException  e1)
			{
					System.out.println(e1.getSQLState());
				
			}
			return rs;
			 
	}
}
