package br.senac.livrariavirtual.servicos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import br.senac.livrariavirtual.modelo.Livro;

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
	
	@POST
	@Path("/UploadLivro")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
	{
		//home/aluno/Dev/upload/arquivos/
		//System.out.print(System.getProperty("user.dir"));
	    String UPLOAD_PATH = "/home/aluno/Área de Trabalho/Dev/Repositorio/PI-6---BackEnd/LivrariaVirtual/arquivos/";
	    try
	    {
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
	        while ((read = fileInputStream.read(bytes)) != -1)
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	    } catch (IOException e)
	    {
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	    return Response.ok("PDF adicionado com sucesso !!").build();
	}
	
	
}
