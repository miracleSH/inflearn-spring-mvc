package hello.servlet.web.frontController.v4;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v4.controller.ControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontController.v4.controller.MemberSaveControllerV4;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerV4(){
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String path = req.getRequestURI();
        ControllerV4 controllerV4 = controllerMap.get(path);
        if(controllerV4 == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = getParamMap(req);
        Map<String, Object> model = new HashMap<>();
        String viewName = controllerV4.process(paramMap, model);
        MyView myView = viewResolver(viewName);
        myView.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        ModelView modelView = new ModelView(viewName);
        return new MyView("/WEB-INF/views/" + modelView.getViewName() + ".jsp");
    }


    private Map<String, String> getParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        Iterator<String> stringIterator = req.getParameterNames().asIterator();
        stringIterator.forEachRemaining(name-> paramMap.put(name, req.getParameter(name)));
        return paramMap;
    }
}
