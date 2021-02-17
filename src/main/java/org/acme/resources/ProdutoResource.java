package org.acme.resources;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.domain.Produto;

import io.vertx.core.json.JsonObject;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
	
    @Inject
    EntityManager entityManager;

    @GET
    public Response buscarTodosProdutos() {
    	List<Produto> list = entityManager.createNativeQuery("select * from produto", Produto.class).getResultList();
    	JsonObject jsonFilho = new JsonObject()
    	        .put("Produtos", list);
    	JsonObject jsonPai = new JsonObject().put("data", jsonFilho);
    	
        return Response.ok(jsonPai).header("authorization", "Bearer bfabnalnfçamçlfmaçnfan").build();
    }

}