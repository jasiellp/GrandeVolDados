package com.camera.swi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class TeoriaDoRAIO
{
	public static void main(String[] args)
	{
		try
		{
			BufferedImage pb = pretoEBranco(carregarImagem());
			

			ImageIO.write(pb, "PNG", new File("C:\\Intel\\mariabonita2.png"));
			int c = 0;
			
			
			 
		 	for (int y = 0; y < pb.getHeight(); y++)
			{
				for (int x = 0; x < pb.getWidth(); x++)
				{
					int cor = pb.getRGB(x, y);
					
					if (cor == PRETO )
					{ 
						/*if(pb.getRGB(x, y+1) != PRETO)
							pb.setRGB(x, y+1, 0xFF_FF_00_00);
						
						if(pb.getRGB(x, y-1) != PRETO)
							pb.setRGB(x, y-1, 0xFF_FF_00_00);
						if(pb.getRGB(x+1, y) != PRETO)
							pb.setRGB(x+1, y, 0xFF_FF_00_00);
						if(pb.getRGB(x-1, y) != PRETO)
							pb.setRGB(x-1, y, 0xFF_FF_00_00);*/
							
						
						new Circulo(pb, x, y).eh_circulo();
							
					}
				}
			} 
			
			System.out.println("sucesso");
			ImageIO.write(pb, "PNG", new File("C:\\Intel\\mariabonita.png"));
			
		}
		catch (IOException e)
		{ 
			e.printStackTrace();
		}
	}
	
	private static BufferedImage carregarImagem() throws IOException
	{
		try
		{
			return ImageIO.read(new File("C:\\Intel\\circulo.jpg"));
		}
		catch (MalformedURLException e)
		{
			throw new AssertionError(e);
		}
	}
	
	private static BufferedImage pretoEBranco(BufferedImage input)
	{
		BufferedImage bi = new BufferedImage(input.getWidth(),
		        input.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < input.getHeight(); y++)
		{
			for (int x = 0; x < input.getWidth(); x++)
			{
				int cor = input.getRGB(x, y);
				int substituta = vermelho(cor) > LIMIAR && verde(cor) > LIMIAR
				        && azul(cor) > LIMIAR ? BRANCO : PRETO;
				bi.setRGB(x, y, substituta);
			}
		}
		return bi;
	}
	
	// Usado para diferenciar o fundo das outras cores.
	private static final int	             LIMIAR	  = 232;
	
	// Cores conhecidas.
	public static final int	                 VERMELHO	= 0xFF_FF_00_00;
	public static final int	                 VERDE	  = 0xFF_00_FF_00;
	public static final int	                 AZUL	  = 0xFF_00_00_FF;
	public static final int	                 BRANCO	  = 0xFF_FF_FF_FF;
	public static final int	                 PRETO	  = 0xFF_00_00_00;
	public static final int	                 AMARELO	= 0xFF_FF_FF_00;
	public static final int	                 CIANO	  = 0xFF_00_FF_FF;
	public static final int	                 LIMA	  = 0xFF_80_FF_00;
	public static final int	                 MAGENTA	= 0xFF_FF_00_FF;
	public static final int	                 ROSA	  = 0xFF_FF_00_80;
	public static final int	                 VIOLETA	= 0xFF_C0_80_FF;
	public static final int	                 MARROM	  = 0xFF_80_40_00;
	public static final int	                 LARANJA	= 0xFF_80_40_00;
	public static final int	                 CINZA	  = 0xFF_80_80_80;
	
	// Dá nome as cores.
	public static final Map<Integer, String>	CORES	= new HashMap<>(20);
	static
	{
		CORES.put(VERMELHO, "vermelho");
		CORES.put(VERDE, "verde");
		CORES.put(AZUL, "azul");
		CORES.put(BRANCO, "branco");
		CORES.put(PRETO, "preto");
		CORES.put(AMARELO, "amarelo");
		CORES.put(CIANO, "ciano");
		CORES.put(LIMA, "verde-lima");
		CORES.put(MAGENTA, "magenta");
		CORES.put(ROSA, "rosa");
		CORES.put(VIOLETA, "violeta");
		CORES.put(MARROM, "marrom");
		CORES.put(LARANJA, "laranja");
		CORES.put(CINZA, "cinza");
	}
	
	// Isola a quantidade de vermelho existente em uma cor.
	private static int vermelho(int cor)
	{
		return (cor & 0xFF0000) >>> 16;
	}
	
	// Isola a quantidade de verde existente em uma cor.
	private static int verde(int cor)
	{
		return (cor & 0xFF00) >>> 8;
	}
	
	// Isola a quantidade de azul existente em uma cor.
	private static int azul(int cor)
	{
		return cor & 0xFF;
	}
	
}
