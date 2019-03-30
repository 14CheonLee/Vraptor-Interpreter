package com.interpreter.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class IndexResource extends ResourceObject {

    @GET
    public String getIndex() {
        return "Vraptor Interpreter";
    }
}
