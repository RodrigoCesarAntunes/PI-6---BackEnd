package br.senac.exemplojersey.servicos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Cliente")
public class ServicoCliente {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCliente(String Usuario, String Senha)
	{
		
		return "";
	}
}
