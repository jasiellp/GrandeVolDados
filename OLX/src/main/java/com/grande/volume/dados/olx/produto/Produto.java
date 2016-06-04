package com.grande.volume.dados.olx.produto;



/***
 * 
 */
public class Produto
{

	
	private double 	valor;
	private String 	data_anuncio;
	private String	link;
	private String  tipo_produto;
	private String  titulo;
	private String  imagem; 
	private String  descricao;
	private String  foto;
	private String  contato;
	private String  identificador;
	private String  estado;
	private String  cidade;
	private String  ddd;
	private int  categoria;
	
	public double getValor()
	{
		return valor;
	}



	public String getData_anuncio()
	{
		return data_anuncio;
	}



	public String getLink()
	{ 
		return "http://sp.olx.com.br".concat(link.replaceAll("\"", "").split("sp.olx.com.br")[1]);
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



	public void setValor(double valor)
	{
		this.valor = valor;
	}



	public void setData_anuncio(String data_anuncio)
	{
		this.data_anuncio = data_anuncio;
	}



	public void setLink(String link)
	{
		this.link = link.trim();
		this.identificador=link.substring((this.link.length()-9),this.link.length());	
	}

	public void setEstado(String estado){
		this.estado=estado;
	}

	public String getIdentificador()
	{
		return identificador.replaceAll("\"", "");
	}



	public void setIdentificador(String identificador)
	{
		this.identificador = identificador.contains("-")?identificador.replaceAll("-", ""):identificador;
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


	
	
	public String getImagem()
	{
		return imagem;
	}



	public void setImagem(String imagem)
	{
		this.imagem = imagem;
	}


	
	
	public int getCategoria()
	{
		return categoria;
	}



	public void setCategoria(int categoria)
	{
		this.categoria = categoria;
	}



	public String getDdd()
	{
		return ddd;
	}



	public void setDdd(String ddd)
	{
		this.ddd = ddd;
	}



	public String getCidade()
	{
		return cidade;
	}



	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}



	public String getEstado()
	{
		return estado;
	}



	public Produto()
	{
		
	}
	
}
