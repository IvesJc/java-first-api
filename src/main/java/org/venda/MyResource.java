package org.venda;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.venda.models.Produto;
import jakarta.ws.rs.core.MediaType;
import org.venda.repositories.ProdutoRepositories;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    ProdutoRepositories produtoRepositories = new ProdutoRepositories();

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("produtoteste")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto getProdutoTest() {
        return new Produto(1, "Airpods Pro", 1500.00);
    }

    @POST
    @Path("produtoteste")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProdutoTeste(Produto produto) {
        if (produto == null){
            return Response.status(400).entity("Produto não pode ser nulo").build();
        }
        System.out.println(produto.toString());
        return Response.status(201).entity(produto).build();
    }

    @PUT
    @Path("produtoteste/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto updateProdutoTeste(@PathParam("id") int id, Produto produto){
        return produto;
    }

    @DELETE
    @Path("produtoteste/{id}")
    public Response deleteProdutoTeste(@PathParam("id") int id){
        return Response.status(204).build();
    }



}

