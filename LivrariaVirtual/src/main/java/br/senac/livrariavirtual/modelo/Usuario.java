package br.senac.livrariavirtual.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Usuario extends DbContext {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String numeroDocumento;
	private String dataNascimento;
	private Boolean isADM;
	
	private Connection conexao;
	
	public Usuario() throws SQLException
	{

	}
	
	private void iniciarConexao()throws SQLException
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
	
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String documento) {
		this.numeroDocumento = documento;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// A��es
	
	@Override
	public void Inserir() throws SQLException {
		iniciarConexao();
		String query = String.format("INSERT INTO usuario (nome, email, senha, documento, data_nascimento, isADM) "
				+ "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",nome, email, senha, numeroDocumento, dataNascimento, isADM);
		System.out.println(query);
		statement.executeUpdate(query);
	}
	@Override
	public void Alterar() throws SQLException{
		iniciarConexao();
		String query = String.format("update usuario set nome = '%s', email = '%s', "
				+ "senha = '%s', documento = '%s', data_nascimento = '%s', isAdm = '%s' "
				+ "where id = '%s';", nome, email, senha, numeroDocumento, dataNascimento, isADM, id);
		
		System.out.println(query);
		statement.executeUpdate(query);
	}

	@Override
	public List<DbContext> SelecionarTudo() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Usuario Selecionar() throws SQLException{
		iniciarConexao();
		String query = String.format("SELECT * FROM usuario WHERE email = '%s' AND isExcluido < 1;", email);
		resultSet = statement.executeQuery(query);
		String _senha = "";
		while (resultSet.next())
		{
			id = resultSet.getInt("id");
			_senha = resultSet.getString("senha");
			nome = resultSet.getString("nome");
			numeroDocumento = resultSet.getString("documento");
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
		iniciarConexao();
		String query = String.format("update usuario set isExcluido = %d where email = '%s'; ", 1, email);
		System.out.println(query);
		statement.executeUpdate(query);
	}



}
