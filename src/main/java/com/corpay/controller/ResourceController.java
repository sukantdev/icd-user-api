package com.corpay.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.corpay.dao.dbo.LinkResource;
import com.corpay.dto.Menu;
import com.corpay.repository.ResourceRepository;
import com.corpay.service.ResourceService;

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

        Set<Menu> resourceSet = resourceService.findUserResources(emailId);
        return Response.ok(resourceSet).build();
    }
}
    