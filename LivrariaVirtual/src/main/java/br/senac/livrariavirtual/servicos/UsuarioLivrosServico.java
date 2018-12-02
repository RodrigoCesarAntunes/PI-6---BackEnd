package br.senac.livrariavirtual.servicos;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.senac.livrariavirtual.modelo.UsuarioLivros;;

@Path("/Compra")
public class UsuarioLivrosServico {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean postLivro(UsuarioLivros usuarioLivros)
	{
		try
		{
			usuarioLivros.Inserir();	
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
	
}
