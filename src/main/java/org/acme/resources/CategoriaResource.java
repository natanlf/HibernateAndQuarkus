package org.acme.resources;

import java.util.List;

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
import org.acme.dto.Links;
import org.acme.dto.Meta;
import org.acme.dto.Pagination;
import org.acme.dto.ResponseCategoriaList;
import org.acme.dto.ResponseCategoriaListData;
import org.acme.exception.MyApplicationException;
import org.acme.services.CategoriaService;

@Path("categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {
	
	@Inject CategoriaService service;

	@POST
	public Response insert(CadastrarCategoriaDTO dto) {
		Categoria cat = service.insert(dto.nome);
		return Response.ok(cat).build();
	}
	
	@GET
	public ResponseCategoriaList getAll() throws MyApplicationException {
		List<Categoria> categorias;
		try {
			categorias = service.getAll();
			ResponseCategoriaListData data = ResponseCategoriaListData.builder().categorias(categorias).build();
			return ResponseCategoriaList.builder().data(data).meta(new Meta()).links(new Links()).build();
		} catch (Exception e) {
			throw new MyApplicationException(e.getMessage());
		}
		
	}
	
	@GET
	@Path("{id}")
	public Response getById(@PathParam("id") Integer id) {
		Categoria cat = service.findById(id);
		return Response.ok(cat).build();
	}
}
