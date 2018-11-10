package br.senac.livrariavirtual.servicos;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.senac.livrariavirtual.modelo.Usuario;

@Path("/Usuario")
public class UsuarioServico {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getCliente(@QueryParam("email")String email, @QueryParam("senha")String senha)
	{
		try
		{
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			
			usuario = usuario.Selecionar();
			
			return usuario;
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return null;
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean postCliente(Usuario usuario)
	{
		try
		{ 
			usuario.Inserir();	
			return true;
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			return false;
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return false;
		}	
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean putCliente(Usuario usuario)
	{
		try 
		{
			usuario.Alterar();
			
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	@PUT
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteCliente(Usuario usuario)
	{
		try 
		{
			usuario.Deletar();
			return true;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
}
