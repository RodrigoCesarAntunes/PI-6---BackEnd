package br.senac.livrariavirtual.modelo;

import java.sql.SQLException;
import java.util.List;
import Utils.TratarCSV;

public class UsuarioLivros extends DbContext {

	private int id;
	private int livroID;
	private String usuarioEmail;
	private String dataHora;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLivroID() {
		return livroID;
	}

	public void setLivroID(int livroID) {
		this.livroID = livroID;
	}

	public String getUsuarioEmail() {
		return usuarioEmail;
	}

	public void setUsuarioEmail(String _usuarioEmail) {
		usuarioEmail = _usuarioEmail;
	}
	
	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}
	
	
	@Override
	public void Inserir() throws SQLException {
		iniciarConexao();
		String query = String.format("INSERT INTO usuario_livros (livro_id, usuario_email, data_hora) "
				+ "VALUES (%s, '%s', '%s')" ,livroID, usuarioEmail, dataHora);
		System.out.println(query);
		statement.executeUpdate(query);
		
	}

	@Override
	public void Alterar() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DbContext Selecionar() throws SQLException {
		iniciarConexao();
		StringBuilder query = new StringBuilder();
		query.append("SELECT l.nome AS 'Livro', l.autor AS 'Autor', l.tipo AS 'Genêro', COUNT(ul.livro_id) AS 'Downloads' ");
		query.append("FROM usuario_livros ul ");
		query.append("INNER JOIN livro l ON ul.livro_id = l.id ");
		query.append(String.format("WHERE ul.data_hora BETWEEN '%s' AND '%s' ", "2018-12-01", "2018-12-04"));
		query.append("GROUP BY livro_id ");
		query.append("ORDER BY COUNT(ul.livro_id) DESC;");
		
		TratarCSV csv = new TratarCSV();
		csv.GerarCSV(statement.executeQuery(query.toString()));
		
		return null;
	}
	
	public DbContext SelecionarLivros(String dataInicio, String dataFim) throws SQLException {
		iniciarConexao();
		StringBuilder query = new StringBuilder();
		query.append("SELECT l.nome AS 'Livro', l.autor AS 'Autor', l.tipo AS 'Genêro', COUNT(ul.livro_id) AS 'Downloads' ");
		query.append("FROM usuario_livros ul ");
		query.append("INNER JOIN livro l ON ul.livro_id = l.id ");
		query.append(String.format("WHERE ul.data_hora BETWEEN '%s' AND '%s' ", dataInicio, dataFim));
		query.append("GROUP BY livro_id ");
		query.append("ORDER BY COUNT(ul.livro_id) DESC;");
		
		TratarCSV csv = new TratarCSV();
		csv.GerarCSV(statement.executeQuery(query.toString()));
		
		return null;
	}
	
	public DbContext SelecionarUsuarios(String dataInicio, String dataFim) throws SQLException {
		iniciarConexao();
		StringBuilder query = new StringBuilder();
		query.append("SELECT u.nome, u.email, u.documento ,count(u.id) AS 'Downloads' ");
		query.append("FROM usuario_livros ul ");
		query.append("INNER JOIN usuario u ON ul.usuario_email like u.email ");
		query.append(String.format("WHERE ul.data_hora BETWEEN '%s' AND '%s' ", dataInicio, dataFim));
		query.append("group by u.nome, u.email, u.documento ");
		query.append("order by count(u.id) desc;");
		
		TratarCSV csv = new TratarCSV();
		csv.GerarCSV(statement.executeQuery(query.toString()));
		
		return null;
	}

	@Override
	public List<DbContext> SelecionarTudo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Deletar() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
