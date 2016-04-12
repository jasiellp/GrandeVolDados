package com.processo.leao.verde.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 

public class ClienteDAO {
	
	
	public static void main(String[] args) throws Exception
	{
		Cliente c = new Cliente();
		c.setNomeCliente("Jasiel");
		new ClienteDAO().buscar(c);
	}

    public List<Cliente> buscar(Cliente c) throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM configure_frame ");
        sql.append("WHERE nome LIKE ? ");
        sql.append("ORDER BY nome ");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        comando.setString(1, "%" + c.getNomeCliente()+ "%");

        /* Executa a SQL e captura o resultado da consulta */
        ResultSet resultado = comando.executeQuery();

        /* Cria uma lista para armazenar o resultado da consulta */
        List<Cliente> lista = new ArrayList<Cliente>();

        /* Percorre o resultado armazenando os valores em uma lista */
        while (resultado.next()) 
        { 
         System.out.println(resultado.getString("categoria"));
        }

        /* Fecha a conexão */
        resultado.close();
        comando.close();
        conn.close();

        /* Retorna a lista contendo o resultado da consulta */
        return lista;
    }

    }