package org.venda.infra;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        // ALLOW ORIGIN: permite que qualquer origem acesse a API
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        // ALLOW HEADERS: permite que qualquer origem acesse a API
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, " +
                "authorization");
        // ALLOW CREDENTIALS: permite que qualquer origem acesse a API

        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, " +
                "HEAD, PATCH");

    }
}
