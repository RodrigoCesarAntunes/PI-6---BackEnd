package br.senac.livrariavirtual.servicos;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.senac.livrariavirtual.modelo.Livro;
import br.senac.livrariavirtual.modelo.Usuario;

@Path("/Livro")
public class LivroServico {
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livro> getLivro(@QueryParam("nome")String nome)
	{
		try
		{
			Livro livro = new Livro();
			livro.setNome(nome);
			
			return ((List<Livro>) (List<?>) livro.SelecionarTudo());
			
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
	
	@GET
	@Path("/id")
	@Produces(MediaType.APPLICATION_JSON)
	public Livro getLivro(@QueryParam("id")int id)
	{
		
		try
		{
			Livro livro = new Livro();
			livro.setId(id);
			return livro.Selecionar();
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
	public Boolean postLivro(Livro livro)
	{
		try
		{
			livro.Inserir();	
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
	public Boolean putLivro(Livro livro)
	{
		try
		{
			livro.Alterar();
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
	@Path("/deletar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean deletarLivro(Livro livro)
	{
		try
		{
			livro.Deletar();
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
