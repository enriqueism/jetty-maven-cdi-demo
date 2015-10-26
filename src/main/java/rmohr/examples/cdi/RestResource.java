package rmohr.examples.cdi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("/")
@Api(value = "/", description = "Testdescription")
public class RestResource {

    @Inject
    Greeting greeting;

    @Inject
    RestSubResource subResource;

    @GET
    @ApiOperation(value = "description X", notes = "whatever")
    public Response printMessage() {
        String result = greeting.getText() + "\n I am the root resource!";
        return Response.status(200).entity(result).build();
    }

    @Path("/sub")
    public RestSubResource sub(){
        return subResource;
    }
}
