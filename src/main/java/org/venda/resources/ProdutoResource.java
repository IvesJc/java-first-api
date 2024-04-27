package org.venda.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.venda.models.Produto;
import org.venda.repositories.ProdutoRepositories;

import java.util.List;



@Path("produtos")
public class ProdutoResource {

    ProdutoRepositories pr = new ProdutoRepositories();

    @GET
    public Response getProdutos(@QueryParam("order") String order,
                                @QueryParam("dir") String dir,
                                @QueryParam("limit") int limit,
                                @QueryParam("page") int page){
        return Response.ok(pr.getProdutos(order, dir, limit, page)).build();
    }

    @GET
    @Path("{id}")
    public Response getProdutoById(@PathParam("id") int id){
        Produto produto = pr.getProdutoById(id);
        if (produto == null){
            return Response.status(404).entity("Produto não encontrado").build();
        }
        return Response.status(200).entity(produto).build();
    }

    @POST
    public Response createProduto(Produto produto){
        if (produto == null){
            return Response.status(400).entity("Produto não pode ser nulo").build();
        }
        pr.createProduto(produto);
        return Response.status(201).entity(produto).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProduto(@PathParam("id") int id, Produto produto){
        ProdutoRepositories.produtos.stream().filter(p -> p.getId() == id).forEach(p -> {
            p.setNome(p.getNome());
            p.setPreco(p.getPreco());
        });
        return Response.status(204).entity(produto).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteProduto(@PathParam("id") int id){
//        ProdutoRepositories.produtos.removeIf(produto -> produto.getId() == id);
        pr.deleteProduto(id);
        return Response.status(204).build();
    }
}
