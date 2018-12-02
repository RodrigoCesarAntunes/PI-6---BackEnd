package br.senac.livrariavirtual.servicos;

import java.sql.SQLException;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Utils.DataAtual;
import br.senac.livrariavirtual.modelo.UsuarioLivros;;

@Path("/Compra")
public class UsuarioLivrosServico {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean postCompra(UsuarioLivros usuarioLivros)
	{
		try
		{
			usuarioLivros.setDataHora(DataAtual.Obter());
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getRelatorio()
	{
		try
		{
			UsuarioLivros usuarioLivros = new UsuarioLivros();  
			usuarioLivros.Selecionar();	
			return "Arquivo gerado";
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
			return "ex.getMessage()";
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}	
	}
	
}
