jetty-maven-cdi demo
====================

This small demo describes how to configure a project to get the following
features:

 * Dependency injection in jetty through weld
 * Automatic resteasy resource scanning and dependency injection through weld
 * JSR-303 validation in resteasy resources
 * Swagger jax-rs scanning
 * Swagger serving the rest documentation on an endpoint
 * JAX-RS subresource handling with resteasy and swagger

To run the application simply type

```
$ mvn jetty:run
```

When the application is started, you can look up the swagger documentation on
<http://localhost:8080/api-docs/swagger.json> or
<http://localhost:8080/api-docs/swagger.yaml>. The most difficult part here is
to convince swagger to scan your resteasy resources. The documentation is not
that good on that part. You can find the scanner which is also the servlet
which provides the documentation
[here](../master/src/main/java/rmohr/examples/cdi/RestScanner.java).


Visit <http://localhost:8080/api> multiple times to see the Greeter service in
action, which is injected in the root resource.

Visit <http://localhost:8080/api/sub> to see the injected Greeter service
invoked from the subresource.

Run

```
$ curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST \
  -i -d '{"name":"test", "description":"testsettsddsfs", "tags":["a", "b"]}' \
  http://localhost:8080/api/sub/dto
```

to post a valid dto and run 

```
$ curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST \
  -i -d '{"name":"test", "description":"", "tags":["a", "b"]}' \
  http://localhost:8080/api/sub/dto
```

to post an invalid dto, to see the JSR-303 validation in action.
