package hello.servlet.web.frontController.v3.controller;

import com.sun.el.util.ReflectionUtil;
import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.MyView;
import hello.servlet.web.frontController.v2.ControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontController.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontController.v3.ControllerV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

@WebServlet(name = "FrontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3(){
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String path = req.getRequestURI();
        ControllerV3 controllerV3 = controllerMap.get(path);
        Map<String, String> paramMap = createParamMap(req);
        ModelView modelView = controllerV3.process(paramMap);
        MyView myView = viewResolver(modelView);
        myView.render(modelView.getModel(), req, resp);
    }

    private MyView viewResolver(ModelView modelView) {
        return new MyView("/WEB-INF/views/" + modelView.getViewName() + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Iterator<String> stringIterator = req.getParameterNames().asIterator();
        Map<String, String> paramMap = new HashMap<>();
        stringIterator.forEachRemaining(it-> paramMap.put(it, req.getParameter(it)));
        return paramMap;
    }
}
