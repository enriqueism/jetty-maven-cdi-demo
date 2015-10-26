package rmohr.examples.cdi;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Swagger;
import io.swagger.util.Json;
import io.swagger.util.Yaml;

@WebServlet(name = "SwaggerJaxrsConfig", loadOnStartup = 1)
public class RestScanner extends HttpServlet {
        @Override
        public void init(ServletConfig config) throws ServletException {
            super.init(config);

            BeanConfig beanConfig = new BeanConfig();
            beanConfig.setVersion("1.0.3");
            beanConfig.setBasePath("/api");
            beanConfig.setSchemes(new String[] { "http" });
            beanConfig.setResourcePackage("rmohr.examples.cdi");
            beanConfig.setHost("localhost:8080");
            beanConfig.setScan(true);
            beanConfig.setPrettyPrint("true");
            Reader reader = new Reader(beanConfig.configure(new Swagger()));
            config.getServletContext().setAttribute("swagger", reader.read(beanConfig.classes()));
        }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Swagger swagger = (Swagger) getServletContext().getAttribute("swagger");
        if (swagger == null) {
            response.setStatus(404);
            return;
        }
        final String pathInfo = request.getPathInfo();
        if ("/swagger.json".equals(pathInfo)) {
            response.getWriter().println(Json.mapper().writeValueAsString(swagger));
        } else if ("/swagger.yaml".equals(pathInfo)) {
            response.getWriter().println(Yaml.mapper().writeValueAsString(swagger));
        } else {
            response.setStatus(404);
        }
    }
    }
