package com.corpay.user;

import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.HashMap;
import java.util.Map;

import com.corpay.security.annotation.Secured;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Secured
public class ProfileController {
    
    @Context
    private ContainerRequestContext requestContext;
    
    @GET
    public Response getProfile(@Context SecurityContext securityContext) {
        String emailId = securityContext.getUserPrincipal().getName();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully retrieved resources");
        response.put("authenticatedUser", emailId);
        response.put("resources", new String[]{"Resource 1", "Resource 2", "Resource 3"});
        
        return Response.ok(response).build();
    }
}
