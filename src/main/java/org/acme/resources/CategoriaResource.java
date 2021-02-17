package org.acme.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.domain.Categoria;
import org.acme.dto.CadastrarCategoriaDTO;
import org.acme.services.CategoriaService;

@Path("categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {
	
	@Inject CategoriaService service;

	@POST
	public Response insert(CadastrarCategoriaDTO dto) {
		String resp = service.insert(dto.nome);
		if(resp == "ok") {
			return null;
			//return Response.created("");
		} else {
			return Response.status(400).build();
		}
	}
	
	@GET
	public Response getAll() {
		return Response.ok(service.getAll()).build();
		//return null;
	}
	
	@GET
	@Path("{id}")
	public Response getById(@PathParam("id") Integer id) {
		Categoria cat = service.findById(id);
		return Response.ok(cat).build();
	}
}
