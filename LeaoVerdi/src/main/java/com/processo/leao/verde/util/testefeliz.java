package com.processo.leao.verde.util;

import com.processo.leao.verde.util.DBLeao;

public class testefeliz
{
	public static void main(String[] args)
	{
		String nome = "alam";
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(" INSERT INTO ").append(" \n"); 
		sb.append(" `configure_frame`  ").append(" \n");
		sb.append(" (`id_config`, `nome`, `categoria`, `url`, `status`) ").append(" \n"); 
		sb.append(" VALUES (0, '").append(nome).append("', 'Lindo', 'http:\\\\orkut.com', 1); ").append(" \n");

		System.out.println("Query :".concat(sb.toString()));

		try
		{
			DBLeao.Insert(sb);
		}
		catch (Exception e)
		{
				System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
