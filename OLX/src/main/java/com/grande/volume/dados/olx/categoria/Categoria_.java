package com.grande.volume.dados.olx.categoria;
public class Categoria_
	{
		
		
		public int getId() {
			return id;
		}


		public String getNome() {
			return nome;
		}


		private int id;
		private String nome;
		
		
		public Categoria_(int id, String nome)
		{
			this.id=id;
			this.nome=nome;
		}
	}