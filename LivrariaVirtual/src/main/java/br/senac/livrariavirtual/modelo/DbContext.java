package br.senac.livrariavirtual.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;


public abstract class DbContext {
	
	public static String status = "N�o conectou...";
	protected Statement statement;
	protected ResultSet resultSet;
	
	public DbContext() {
		
	}

	public static java.sql.Connection getConexaoMySQL() {
	 
	        Connection connection = null;          
	        //atributo do tipo Connection
	 
	        try {
	 
	        	// Carregando o JDBC Driver padr�o
	        	String driverName = "com.mysql.cj.jdbc.Driver";                        
	        
	        	Class.forName(driverName);
	        	//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	 
	            String dbURL = "jdbc:mysql://localhost:3306/livraria_virtual?useTimezone=true&serverTimezone=UTC";
	            
	            Properties properties = new Properties();
	            properties.put("user", "root");
	            properties.put("password", "VetDB-435");
	            
	            connection = DriverManager.getConnection(dbURL, properties);
	            
	            //Testa sua conex�o//  
	 
	            if (connection != null) {
	                status = ("STATUS--->Conectado com sucesso!");
	            } else {
	 
	                status = ("STATUS--->N�o foi possivel realizar conex�o");
	 
	            }
	 
	            return connection;
	 
	        }
	        catch (ClassNotFoundException e) {  //Driver n�o encontrado
	       	 
	            System.out.println("O driver expecificado nao foi encontrado.");
	 
	            return null;
	 
	        }
	        
	        catch (SQLException e) {
	 
	//N�o conseguindo se conectar ao banco
	        	System.out.println("Nao foi possivel conectar ao Banco de Dados.");
	        	e.printStackTrace();
	            return null;
	 
	        }
	 
	  
	 
	    }

	// M�todo que retorna o status da sua conex�o//

	public static String statusConection() {

		return status;

	}

	// M�todo que fecha sua conex�o//

	public static boolean FecharConexao() {

		try {

			DbContext.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	// M�todo que reinicia sua conex�o//

	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return DbContext.getConexaoMySQL();

	}
	
	
	public abstract void Inserir() throws SQLException;
	
	public abstract void Alterar() throws SQLException;
	
	public abstract DbContext Selecionar() throws SQLException;
	
	public abstract void Deletar() throws SQLException;
}
