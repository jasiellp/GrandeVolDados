package com.camera.swi;

import java.util.ArrayList;

public class horario
{
	public static void main(String[] args)
	{
		
		int x = 3;
		
		
		
		ArrayList<reta> array_ = new ArrayList<reta>(); 
				
				
		String[][] a1 = new String[][]
		{
		        new String[]
		        { "0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8" },
		        new String[]
		        { "1.0", "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "1.7", "1.8" },
		        new String[]
		        { "2.0", "2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "2.8" },
		        new String[]
		        { "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8" },
		        new String[]
		        { "4.0", "4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8" },
		        new String[]
		        { "5.0", "5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8" },
		        new String[]
		        { "6.0", "6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "6.7", "6.8" },
		        new String[]
		        { "7.0", "7.1", "7.2", "7.3", "7.4", "7.5", "7.6", "7.7", "7.8" },
		        new String[]
		        { "8.0", "8.1", "8.2", "8.3", "8.4", "8.5", "8.6", "8.7", "8.8" } };
		
		
		int vl= x;
		
		for (int i = 0; i <= x*2; i++)
		{  
			array_.add(new reta(i, vl));
			
			if(i>=x)
				vl++;
			else
				vl--;
			 
		} 
		  
		vl= x;
		  
		for (int i = 0; i <= x*2; i++)
		{  
			array_.add(new reta(i, vl));
			
			if(i>=x)
				vl--;
			else
				vl++;
			 
		}  
		
		
		for(reta r : array_)
		{
			System.out.println(a1[r.altura][r.largura]);
		}
	}
	 
	public static class reta
	{
		
		int	largura	= 0;
		int	altura	= 0;
		
		public reta(int altura, int largura)
		{
			this.altura = altura;
			this.largura= largura;
		}
		
		public int getLargura()
		{
			return largura;
		}
		
		public void setLargura(int largura)
		{
			this.largura = largura;
		}
		
		public int getAltura()
		{
			return altura;
		}
		
		public void setAltura(int altura)
		{
			this.altura = altura;
		}
		
	}
	
}
