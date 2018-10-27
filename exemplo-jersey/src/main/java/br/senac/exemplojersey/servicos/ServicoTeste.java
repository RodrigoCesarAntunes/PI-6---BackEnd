package br.senac.exemplojersey.servicos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/teste")
public class ServicoTeste {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String dizerOlaMundo() {
		return "Olá mundo! Seu serviço funciona corretamente!";
	}

}
