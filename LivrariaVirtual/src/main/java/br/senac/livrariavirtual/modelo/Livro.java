package br.senac.livrariavirtual.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Livro extends DbContext {
	
	private double preco;
	private String nome;
	private String	editora;
	private int edicao;
	private String genero;
	private String autor;
	private int id;
	
private Connection conexao;
	
	public Livro() throws SQLException
	{
	}
	private void iniciarConexao()throws SQLException
	{
		conexao = getConexaoMySQL();
		statement = conexao.createStatement();
	}
	
	public double getPreço() {
		return preco;
	}
	public void setPreço(double preco) {
		this.preco = preco;
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
		iniciarConexao();
		String query = String.format("INSERT INTO livro (nome, editora, edicao, preco, tipo, autor) "
				+ "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')" ,nome ,editora, edicao, preco, genero, autor);
		System.out.println(query);
		statement.executeUpdate(query);
	}

	@Override
	public void Alterar() throws SQLException {
		// TODO Auto-generated method stub
		iniciarConexao();
		String query = String.format("update livro set nome = '%s', editora = '%s', "
				+ "edicao = '%s', preco = '%s', genero = '%s', autor = '%s' "
				+ "where id = '%s';", nome, editora, edicao, preco, genero, autor, id);
		
		System.out.println(query);
		statement.executeUpdate(query);
	}

	@Override
	public Livro Selecionar() throws SQLException {
		return null;
	}

	@Override
	public List<DbContext> SelecionarTudo() throws SQLException {
		iniciarConexao();
		String query = String.format("SELECT * FROM livro WHERE nome like '%%%s%%' AND isExcluido < 1;", nome);
		System.out.print(query);
		resultSet = statement.executeQuery(query);
		List<DbContext> livros = new ArrayList<DbContext>();
		while (resultSet.next())
		{
			Livro book = new Livro();
			
			book.setId(resultSet.getInt("id")); 
			book.setNome(resultSet.getString("nome"));
			book.setEditora(resultSet.getString("editora")); 
			book.setEdicao(resultSet.getInt("edicao"));
			book.setPreço(resultSet.getDouble("preco"));
			book.setGenero(resultSet.getString("tipo")); 
			book.setAutor(resultSet.getString("autor"));
			livros.add(book);
		}
		
		if (livros.size() > 0)
		{
			return livros;
		}
		
		return null;
	}

	@Override
	public void Deletar() throws SQLException {
		// TODO Auto-generated method stub
				iniciarConexao();
				String query = String.format("update livro set isExcluido = %d, where id = '%d', ", 1, id);
				System.out.println(query);
				statement.executeUpdate(query);
	}
}
