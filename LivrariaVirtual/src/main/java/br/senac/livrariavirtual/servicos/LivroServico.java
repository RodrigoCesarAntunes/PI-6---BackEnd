package br.senac.livrariavirtual.servicos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import Utils.Constantes;
import br.senac.livrariavirtual.modelo.Livro;
import br.senac.livrariavirtual.modelo.UsuarioLivros;

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
		Livro livro = new Livro();
		livro.SelecionarUltimo();
		
	    String UPLOAD_PATH = Constantes.Livros_Caminho;
	    try
	    {
	        int read = 0;
	        byte[] bytes = new byte[1024];
	        //fileMetaData.getFileName()
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + livro.getNome() + livro.getId() + ".pdf"));
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
	
	@GET
	@Path("/DownloadLivro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLivro(@QueryParam("id")int id, @QueryParam("titulo")String titulo)
	{
		
		try
		{
			Livro livro = new Livro();
			livro.setId(id);
			livro.setNome(titulo);
			//livro.SelecionarUltimo();
			
			UsuarioLivros usuarioLivros = new UsuarioLivros();  
			usuarioLivros.Selecionar();	
			
			StreamingOutput fileStream =  new StreamingOutput()
	        {
	            @Override
	            public void write(java.io.OutputStream output) throws IOException, WebApplicationException
	            {
	                try
	                {
	                	String caminho = Constantes.Livros_Caminho + livro.getNome() + livro.getId() + ".pdf";
	                	System.out.println(caminho);
	                    java.nio.file.Path path = Paths.get(caminho);
	                    byte[] data = Files.readAllBytes(path);
	                    output.write(data);
	                    output.flush();
	                }
	                catch (Exception e)
	                {
	                    throw new WebApplicationException("File Not Found !!");
	                }
	            }
	        };
	        
	        return Response
	                .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
	                .header("content-disposition", "attachment; filename = Livro.pdf")
	                .build();
			
			
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
	
	
}
