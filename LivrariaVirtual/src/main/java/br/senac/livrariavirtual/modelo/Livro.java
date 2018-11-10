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
		String query = String.format("INSERT INTO Livro (nome, editora, edicao, preco, genero, autor) "
				+ "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')" ,nome ,editora, edicao, preco, genero, autor);
		System.out.println(query);
		statement.executeUpdate(query);
	}

	@Override
	public void Alterar() throws SQLException {
		// TODO Auto-generated method stub
		iniciarConexao();
		String query = String.format("update usuario set nome = '%s', editora = '%s', "
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
		String query = String.format("SELECT * FROM livro WHERE titulo like '%%s%' AND isExcluido < 1;", nome);
		resultSet = statement.executeQuery(query);
		List<DbContext> livros = new ArrayList<DbContext>();
		while (resultSet.next())
		{
			id = resultSet.getInt(id);
			nome = resultSet.getString("nome");
			editora = resultSet.getString("editora");
			edicao = resultSet.getInt("edicao");
			preco = resultSet.getDouble("isADM");
			genero = resultSet.getString("tipo");
			autor = resultSet.getString("autor");
			livros.add(this);
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
