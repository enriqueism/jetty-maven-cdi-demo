package rmohr.examples.cdi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class RestResource {

    @Inject
    Greeting greeting;

    @Inject
    RestSubResource subResource;

    @GET
    public Response printMessage() {
        String result = greeting.getText() + "\n I am the root resource!";
        return Response.status(200).entity(result).build();
    }

    @Path("/sub")
    public Object sub(){
        return subResource;
    }
}
