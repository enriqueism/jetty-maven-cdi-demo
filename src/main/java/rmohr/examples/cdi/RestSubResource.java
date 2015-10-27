package rmohr.examples.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestScoped
@Api
public class RestSubResource {

    @Inject
    private Greeting greeting;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("/")
    @ApiOperation(value = "get from subresource")
    @Produces("text/plain")
    public String get(){
        String result = greeting.getText() + "\n I am the subresource!";
        return result;
    }


    @POST
    @Path("/validate")
    @ApiOperation(value = "post something in subresource")
    public Response post(@NotEmpty String test){
        return Response.status(201).build();
    }

    @POST
    @Path("/dto")
    @ApiOperation(value = "post dao in subresource")
    @Consumes("application/json")
    public Response post(@NotNull @Valid MyDto myDto){
        return Response.status(201).build();
    }

    @GET
    @Path("/context")
    @ApiOperation(value = "check if context annotations are working")
    public String context(){
        return uriInfo.getPath();
    }

}
