package org.venda;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.venda.models.Produto;
import org.venda.repositories.ProdutoRepositories;

import java.util.List;

@Path("produtos")
public class ProdutoResource {

    ProdutoRepositories produtoRepositories;

    @GET
    public List<Produto> getProdutos(){
        return ProdutoRepositories.produtos;
    }

    @POST
    public Response createProduto(Produto produto){
        if (produto == null){
            return Response.status(400).entity("Produto não pode ser nulo").build();
        }
        ProdutoRepositories.produtos.add(produto);
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
    public Response updateProduto(@PathParam("id") int id){
        ProdutoRepositories.produtos.removeIf(produto -> produto.getId() == id);
        return Response.status(204).build();
    }
}
