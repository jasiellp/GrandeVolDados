package com.processo.leao.verde.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBLeao
{ 
	private static Statement stmt_ = null;
	private static Connection conn;
	private static PreparedStatement stmt;
	  
	public static void Insert(Object query) throws Exception
	{ 
			try
			{
				
				if(conn == null || conn.isClosed())
				{ 
					conn = Conexao.abrir();
				}
				
				stmt = conn.prepareStatement(query.toString());
				stmt.execute(); 
				
			}
			catch (SQLException  e1)
			{
					System.out.println(e1.getSQLState());
				
			}
			 
	}
	
	
	
	public static void sair() throws SQLException 
	{
	
		if(conn!=null)
			conn.close();
		
		if(stmt!=null)
			stmt.close();
		
		if(stmt_!=null)
			stmt_.close();
	}
	
	public static ResultSet select(Object query) throws Exception
	{ 
			try
			{ 
				if(conn==null  || conn.isClosed())
				{ 
					conn = Conexao.abrir();	
				}
				
			}
			catch (SQLException  e1)
			{
					System.out.println(e1.getSQLState());
				
			}
			
			return conn.createStatement().executeQuery(query.toString());
	}
}
