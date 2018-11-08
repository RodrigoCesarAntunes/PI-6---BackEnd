package br.senac.livrariavirtual.modelo;

import java.sql.Connection;
import java.sql.SQLException;

public class Livro extends DbContext {
	
	private int preco;
	private String nome;
	private String	editora;
	private int edicao;
	private String genero;
	private String autor;
	private int id;
	
private Connection conexao;
	
	public Livro() throws SQLException
	{
		conexao = getConexaoMySQL();
		statement = conexao.createStatement();
	}
	
	
	public int getPreço() {
		return preço;
	}
	public void setPreço(int preço) {
		this.preço = preço;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getEdicao() {
		return edicao;
	}
	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public void Inserir() throws SQLException {
		String query = String.format("INSERT INTO Livro (id, nome, editora, edicao, preco, genero, autor) "
				+ "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",id ,nome ,editora, edicao, preco, genero, autor);
		System.out.println(query);
		statement.executeUpdate(query);
	}


	@Override
	public void Alterar() throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public DbContext Selecionar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void Deletar() throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
