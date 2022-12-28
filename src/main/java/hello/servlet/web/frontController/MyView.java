package hello.servlet.web.frontController;

import java.io.IOException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyView {

    private String viewPath;


    public MyView(String viewPath){
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);
    }


    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        addModelRequestAttribute(model, req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }

    private void addModelRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach(req::setAttribute);
    }
}
