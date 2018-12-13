package br.senac.livrariavirtual.servicos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import Utils.Constantes;
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
			usuarioLivros.setDataHora(DataAtual.Obter(true));
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
	public Response getRelatorio(@QueryParam("mesInicio")int mesInicio, 
			@QueryParam("mesFim")int mesFim, @QueryParam("tipo")String tipo)
	{
		
		String dataInicio = "2018-" + mesInicio + "-01";
		String dataFim = "2018-" + mesFim + "-28";
		
		try
		{
			UsuarioLivros usuarioLivros = new UsuarioLivros();
			switch(tipo)
			{
				case "livros":
					usuarioLivros.SelecionarLivros(dataInicio, dataFim);
					break;
				case "usuarios":
					usuarioLivros.SelecionarUsuarios(dataInicio, dataFim);
					break;
				default:
					usuarioLivros.SelecionarLivros(dataInicio, dataFim);
					break;
			}
				
			
			StreamingOutput fileStream =  new StreamingOutput()
	        {
	            @Override
	            public void write(java.io.OutputStream output) throws IOException, WebApplicationException
	            {
	                try
	                {
	                    java.nio.file.Path path = Paths.get(Constantes.Relatorio_Caminho);
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
	                .header("content-disposition", "attachment; filename = Relatório_" + tipo + "_" + DataAtual.Obter(false)+ ".csv")
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
