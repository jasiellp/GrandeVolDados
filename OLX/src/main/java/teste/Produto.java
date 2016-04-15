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
	public void setValor(double valor)
	{
		this.valor = valor;
	}



	public void setData_anuncio(Date data_anuncio)
	{
		this.data_anuncio = data_anuncio;
	}



	public void setLink(String link)
	{
		this.link = link;
	}



	public void setTipo_produto(String tipo_produto)
	{
		this.tipo_produto = tipo_produto;
	}



	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}



	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}



	public void setFoto(String foto)
	{
		this.foto = foto;
	}



	public void setContato(String contato)
	{
		this.contato = contato;
	}


	private Date 	data_anuncio;
	private String	link;
	private String  tipo_produto;
	private String  titulo;
	private String  descricao;
	private String  foto;
	private String  contato;
	
	
	
	public Produto()
	{
		
	}
	
}
