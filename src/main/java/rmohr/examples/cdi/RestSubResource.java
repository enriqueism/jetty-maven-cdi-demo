package rmohr.examples.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
public class RestSubResource {

    @Inject
    private Greeting greeting;

    @GET
    @Path("/")
    public Response get(){
        String result = greeting.getText() + "\n I am the subresource!";
        return Response.status(200).entity(result).build();
    }
}
