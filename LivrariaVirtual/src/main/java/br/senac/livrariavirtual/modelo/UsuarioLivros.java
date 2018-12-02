package br.senac.livrariavirtual.modelo;

import java.sql.SQLException;
import java.util.List;

public class UsuarioLivros extends DbContext {

	private int id;
	private int livroID;
	private String usuarioEmail;
	
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
	
	
	@Override
	public void Inserir() throws SQLException {
		iniciarConexao();
		String query = String.format("INSERT INTO usuario_livros (livro_id, usuario_email) "
				+ "VALUES (%s, '%s')" ,livroID, usuarioEmail);
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
	public List<DbContext> SelecionarTudo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Deletar() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
