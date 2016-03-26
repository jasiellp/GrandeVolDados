package com.camera.swi;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Circulo
{
	protected BufferedImage	  imagem;
	protected int	          altura	 = 0;
	protected int	          largura	 = 0;
	protected int	          x_	     = 0;
	protected final int	      PRETO	     = 0xFF_00_00_00;
	protected boolean	      eh_circulo	= false;
	protected ArrayList<Reta>	array_;
	
	public Circulo(BufferedImage imagem, int altura, int largura)
	{
		this.altura = altura;
		this.imagem = imagem;
		this.largura = largura;
		this.x_ = this.x____();
		
	}
	
	public boolean eh_circulo() throws IOException
	{
		array_ = this.geraIndice(x_);
		
		int cores = 0;
		
		if (imagem.getRGB(largura + x_, altura) != PRETO)
		{
			imagem.setRGB(largura + x_, altura, 0xFF_FF_00_00);
		}
		if (imagem.getRGB(largura - x_, altura) != PRETO)
		{
			imagem.setRGB(largura - x_, altura, 0xFF_FF_00_00);
			
		}
		if (imagem.getRGB(largura, altura + x_) != PRETO)
		{
			imagem.setRGB(largura, altura + x_, 0xFF_FF_00_00);
			
		}
		if (imagem.getRGB(largura, altura - x_) != PRETO)
		{
			System.out.println("1");
			
			imagem.setRGB(largura, altura - x_, 0xFF_FF_00_00);
			
		}
		
		return cores > 1;
	}
	
	private ArrayList<Reta> geraIndice(int raio)
	{
		ArrayList<Reta> array_ = new ArrayList<Reta>();
		
		int largura1 = raio;
		int largura2 = raio;
		
		for (int i = altura; i <= raio * 2; i++)
		{
			array_.add(new Reta(i, largura1, largura2));
			
			if (i >= raio) largura1++;
			else largura1--;
			
			if (i >= raio) largura2--;
			else largura2++;
			
		}
		
		return array_;
	}
	
	private int x____()
	{
		int altura_ = this.altura;
		
		while (this.imagem.getRGB(this.largura, altura_) == this.PRETO)
			altura_++;
		
		altura_ += -this.altura;
		
		return altura_;// (int) ((int) altura_ + (altura_ * 0.8));
	}
	
	public int getAltura()
	{
		return this.altura;
	}
	
	public void setAltura(int altura)
	{
		this.altura = altura;
	}
	
	public int getLargura()
	{
		return largura;
	}
	
	public void setLargura(int largura)
	{
		this.largura = largura;
	}
	
	public class Reta
	{
		
		private int	largura1	= 0;
		private int	largura2	= 0;
		private int	altura		= 0;
		
		public Reta(int altura, int largura1, int largura2)
		{
			this.altura = altura;
			this.largura1 = largura1;
			this.largura2 = largura2;
		}
		
		public int getLargura2()
		{
			return largura2;
		}
		
		public void setLargura2(int largura)
		{
			this.largura2 = largura;
		}
		
		public int getLargura1()
		{
			return this.largura1;
		}
		
		public void setLargura1(int largura)
		{
			this.largura1 = largura;
		}
		
		public int getAltura()
		{
			return this.altura;
		}
		
		public void setAltura(int altura)
		{
			this.altura = altura;
		}
		
	}
	
}
