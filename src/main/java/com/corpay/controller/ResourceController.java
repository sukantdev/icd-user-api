package com.corpay.controller;

import java.util.List;

import com.corpay.dto.Menu;
import com.corpay.service.ResourceService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("AUTHENTICATED")
public class ResourceController {

    @Inject
    private ResourceService resourceService;

    @GET
    public Response getResources(@Context SecurityContext securityContext) {
        String emailId = securityContext.getUserPrincipal().getName();

        List<Menu> resourceSet = resourceService.findUserResources(emailId);
        return Response.ok(resourceSet).build();
    }
}
    