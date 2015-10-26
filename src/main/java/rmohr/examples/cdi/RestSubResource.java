package rmohr.examples.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestScoped
@Api
public class RestSubResource {

    @Inject
    private Greeting greeting;

    @GET
    @Path("/")
    @ApiOperation(value = "lala")
    @Produces("text/plain")
    public String get(){
        String result = greeting.getText() + "\n I am the subresource!";
        return result;
    }
}
