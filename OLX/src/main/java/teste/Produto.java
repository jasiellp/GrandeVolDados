package teste;

import java.util.Date;

public class Produto
{

	
	public double getValor()
	{
		return valor;
	}



	public Date getData_anuncio()
	{
		return data_anuncio;
	}



	public String getLink()
	{
		return link;
	}



	public String getTipo_produto()
	{
		return tipo_produto;
	}



	public String getTitulo()
	{
		return titulo;
	}



	public String getDescricao()
	{
		return descricao;
	}



	public String getFoto()
	{
		return foto;
	}



	public String getContato()
	{
		return contato;
	}


	private double 	valor;
	private Date 	data_anuncio;
	private String	link;
	private String  tipo_produto;
	private String  titulo;
	private String  descricao;
	private String  foto;
	private String  contato;
	
	
	
	public Produto(Object obj)
	{
		this.descricao=obj.toString();
		
	}
	
}
