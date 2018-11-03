package br.senac.livrariavirtual.servicos;


import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

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
	public ResponseBuilder postCliente(Usuario usuario)
	{
		
		try
		{
			Usuario user = new Usuario();
			user = usuario; 
			user.Inserir();	
			return Response.ok(user);
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			return Response.status(201).entity("Erro de SQL");
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return Response.status(201).entity("Erro desconhecido");
		}
		
	}
	
}
