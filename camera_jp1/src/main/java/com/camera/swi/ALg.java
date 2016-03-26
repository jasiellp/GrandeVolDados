package com.camera.swi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ALg {

	// Usado para diferenciar o fundo das outras cores.
	private static final int LIMIAR = 232;

	// Cores conhecidas.
	public static final int VERMELHO = 0xFF_FF_00_00;
	public static final int VERDE = 0xFF_00_FF_00;
	public static final int AZUL = 0xFF_00_00_FF;
	public static final int BRANCO = 0xFF_FF_FF_FF;
	public static final int PRETO = 0xFF_00_00_00;
	public static final int AMARELO = 0xFF_FF_FF_00;
	public static final int CIANO = 0xFF_00_FF_FF;
	public static final int LIMA = 0xFF_80_FF_00;
	public static final int MAGENTA = 0xFF_FF_00_FF;
	public static final int ROSA = 0xFF_FF_00_80;
	public static final int VIOLETA = 0xFF_C0_80_FF;
	public static final int MARROM = 0xFF_80_40_00;
	public static final int LARANJA = 0xFF_80_40_00;
	public static final int CINZA = 0xFF_80_80_80;

	// D� nome as cores.
	public static final Map<Integer, String> CORES = new HashMap<>(20);
	static {
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
	private static int vermelho(int cor) {
		return (cor & 0xFF0000) >>> 16;
	}

	// Isola a quantidade de verde existente em uma cor.
	private static int verde(int cor) {
		return (cor & 0xFF00) >>> 8;
	}

	// Isola a quantidade de azul existente em uma cor.
	private static int azul(int cor) {
		return cor & 0xFF;
	}

	private static BufferedImage carregarImagem() throws IOException {
		try {
			return ImageIO.read(new File("C:\\Intel\\circulo.jpg"));
		} catch (MalformedURLException e) {
			throw new AssertionError(e);
		}
	}

	private static BufferedImage pretoEBranco(BufferedImage input) {
		BufferedImage bi = new BufferedImage(input.getWidth(),
				input.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int y = 0; y < input.getHeight(); y++) {
			for (int x = 0; x < input.getWidth(); x++) {
				int cor = input.getRGB(x, y);
				// int substituta = vermelho(cor) > LIMIAR && verde(cor) >
				// LIMIAR && azul(cor) > LIMIAR ? BRANCO : PRETO;
				int substituta = vermelho(cor) > LIMIAR && verde(cor) > LIMIAR
						&& azul(cor) > LIMIAR ? BRANCO : PRETO;
				bi.setRGB(x, y, substituta);
			}
		}
		return bi;
	}
 

	public static int x(BufferedImage img, int largura, int altura) {

		int raio = 0;
		int abaixo = altura;
		int acima = altura;
		int ao_lado = largura;
		int outro_lado = largura;

		while (img.getRGB(largura, abaixo) == PRETO)
			abaixo++;

		abaixo += -altura;

		raio = abaixo;

		if (raio > altura || raio > largura) {
			return -1;
		} else {
			acima = altura - raio + 3;
			ao_lado = largura;
			outro_lado = largura;
			int cont = 0;
			while (img.getRGB(largura, acima) == PRETO) {
				if (cont == 2)
					break;
				else {
					acima--;
					cont++;
				}
			}

			acima = altura - acima;

			if (((Math.abs(abaixo - acima)) < 2)) {
				ao_lado = ao_lado + abaixo - 3;

				while (img.getRGB(ao_lado, altura) == PRETO)
					ao_lado++;

				ao_lado += -largura;

				if (ao_lado > largura) {
					return -1;
				} else {
					while (img.getRGB(outro_lado, altura) == PRETO)
						outro_lado--;

					outro_lado = largura - outro_lado;

					if ((Math.abs(ao_lado - outro_lado) < 2))
						return outro_lado;
					else
						return -1;
				}

			} else
				return -1;
		}

	}

	public ArrayList<reta_inicial> getReta(BufferedImage imagem, int altura,
			int largura) {

		ArrayList<reta_inicial> reta = new ArrayList<reta_inicial>();

		int largura_ = largura;
		int altura_ = altura;

		while (imagem.getRGB(largura_, altura_) == PRETO) {
			if (ehUmaReta(imagem, altura_, largura_)) {
				reta.add(new reta_inicial(altura_, largura_));
				altura_++;
			} else
				break;

		}

		return reta;
	}

	private boolean ehUmaReta(BufferedImage imagem, int altura, int largura) {

		return largura >= 4 ? imagem.getRGB(largura, altura) == PRETO
				&& (imagem.getRGB(largura - 1, altura) == BRANCO
						|| imagem.getRGB(largura - 2, altura) == BRANCO
						|| imagem.getRGB(largura - 3, altura) == BRANCO || imagem
						.getRGB(largura - 4, altura) == BRANCO)
				: true;
	}

	public class reta_inicial {

		public int getVertical() {
			return vertical;
		}

		public void setVertical(int vertical) {
			this.vertical = vertical;
		}

		public int getHorizontal() {
			return horizontal;
		}

		public void setHorizontal(int horizontal) {
			this.horizontal = horizontal;
		}

		/**
		 * 
		 * 
		 * 
		 * 
		 * */

		private int vertical = 0;
		private int horizontal = 0;

		public reta_inicial() {

		}

		public reta_inicial(int a, int l) {
			this.setHorizontal(l);
			this.setVertical(a);
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {

		BufferedImage pb  = pretoEBranco(carregarImagem());
		long tempoInicial = System.currentTimeMillis();
		int width  = pb.getWidth();
		int height = pb.getHeight();
 
 
	 	for (int x = 0; x < width; x++) 
	 	{
			for (int y = 0; y < height; y++) 
			{
				
				if(!exist(x,y))
				{
					int cor = pb.getRGB(x, y);

					if (cor == PRETO) 
					{
						if ((pb.getRGB(x + 1, y) == PRETO)
								&& (pb.getRGB(x -1, y) == PRETO)
								&& (pb.getRGB(x, y + 1) == PRETO)
								&& (pb.getRGB(x, y - 1) == PRETO)) {

							 
							vertice(pb, x, y);

							 
						}
					}	
				} 
			}
		}
   
		ImageIO.write(pb, "png", new File("C:\\Intel\\teste.png"));

		long tempoFinal = System.currentTimeMillis() - tempoInicial;

		System.out.printf("%.3f ms%n", tempoFinal / 1000d);

		System.out.println("Fim");
	}

	public static void vertice(BufferedImage imagem, int x, int y) {

		int direita, esquerda, acima = y, abaixo = y;
		int raio_vertical = 0;
		int raio_horizontal = 0;
 
		
		while (imagem.getRGB(x, abaixo) == PRETO)
			abaixo++;

		abaixo += -1; 

		while (imagem.getRGB(x, acima) == PRETO)
			acima--;

		acima += +1; 

		raio_vertical = Math.abs(abaixo - acima) / 2; 
		
		direita = x;

		while (imagem.getRGB(direita, y) == PRETO)
			direita++;

		direita += -1; 

		esquerda = x;

		while (imagem.getRGB(esquerda, y) == PRETO)
			esquerda--;

		esquerda += +1;
 
		raio_horizontal = Math.abs(direita - esquerda) / 2; 

		if (Math.abs(raio_horizontal - raio_vertical) > 2) 
		{ 
			vertice(imagem, (int) direita - raio_horizontal, (int) acima+ raio_vertical);
		}
		else 
		{

			imagem.setRGB((int) direita - raio_horizontal, (int) acima + raio_vertical, ROSA);
			
			imagem.setRGB((int) direita - raio_horizontal, ((int) acima + (raio_vertical*2))+1, AZUL);
			
			imagem.setRGB((int) direita - raio_horizontal, ((int) acima  )-1, AZUL);

			
			imagem.setRGB(((int) direita - (raio_horizontal*2))-2, (int) acima + raio_vertical, AMARELO);
			
	 		imagem.setRGB(((int) direita +1 ), (int) acima + raio_vertical, AMARELO);
			
			x_ = x+raio_horizontal;
			y_ = y+raio_vertical;
			
			circulos.add(new Circulo(x_, y_, raio_horizontal*2));
			 
		}

	}
	
	
	
	public static boolean exist(int x, int y)
	{
		int cont = 0;
		
		for(Circulo c : circulos)
		{
			if(
					(c.getX()-c.getRaio() <= x) && (c.getX() >= x)  || 
					(c.getY()-c.getRaio() >= y) && (c.getY() <= y)) 
			{
				cont++;
				break;
			}
		}
		
		return cont>0;
	}

	public static int x_ = 0;
	public static int y_ = 0;
	public static ArrayList<Circulo> circulos = new ArrayList<Circulo>();
	
	public static class Circulo
	{

		private int x = 0;
		private int y = 0;
		private int raio = 0;

		public Circulo(int x, int y, int raio) 
		{
			this.raio = raio;
			this.x 	  = x;
			this.y 	  = y;
		}


		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getRaio() {
			return raio;
		}


	}
}