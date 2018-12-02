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
		query.append("SELECT user_book.data_hora, usuario.id, usuario.nome, usuario.email, usuario.senha, usuario.data_nascimento, ");
		query.append("livro.id as 'id_livro', livro.nome as 'livro', livro.autor, ");
		query.append("livro.tipo  as 'genêro', livro.preco as 'preço', livro.editora, livro.edicao ");
		query.append("FROM usuario ");
		query.append("LEFT JOIN usuario_livros user_book on user_book.usuario_email like usuario.email ");
		query.append("INNER JOIN livro on livro.id = user_book.livro_id;");
		
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
