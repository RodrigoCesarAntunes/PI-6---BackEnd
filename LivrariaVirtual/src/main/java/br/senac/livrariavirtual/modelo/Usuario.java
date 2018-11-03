package br.senac.livrariavirtual.modelo;

import java.sql.Connection;
import java.sql.SQLException;

public class Usuario extends DbContext {
	
	private String nome;
	private String email;
	private String senha;
	private String documento;
	private String dataNascimento;
	private Boolean isADM;
	
	private Connection conexao;
	
	public Usuario() throws SQLException
	{
		conexao = getConexaoMySQL();
		statement = conexao.createStatement();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Boolean getIsADM() {
		return isADM;
	}
	public void setIsADM(Boolean isADM) {
		this.isADM = isADM;
	}
	
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	// Ações
	
	@Override
	public void Inserir() throws SQLException {
		String query = String.format("INSERT INTO usuario (nome, email, senha, documento, data_nascimento, isADM) "
				+ "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",nome, email, senha, documento, dataNascimento, isADM);
		System.out.println(query);
		statement.executeUpdate(query);
	}
	@Override
	public void Alterar() throws SQLException{
		// TODO Auto-generated method stub
		
	}
	@Override
	public Usuario Selecionar() throws SQLException{
		String query = String.format("SELECT * FROM usuario WHERE email = '%s'", email);
		resultSet = statement.executeQuery(query);
		String _senha = "";
		while (resultSet.next())
		{
			_senha = resultSet.getString("senha");
			nome = resultSet.getString("nome");
			documento = resultSet.getString("documento");
			dataNascimento = resultSet.getString("data_nascimento");
			isADM = resultSet.getBoolean("isADM");
		}
		
		if (_senha.equals(senha))
		{
			return this;
		}
		
		return null;
	}
	@Override
	public void Deletar() throws SQLException{
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
