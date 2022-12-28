package hello.servlet.web.frontController.v5.adapter;

import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v4.controller.ControllerV4;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV4;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewPath = controller.process(paramMap, model);
        ModelView modelView = new ModelView(viewPath);
        modelView.setModel(model);
        return modelView;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Iterator<String> stringIterator = req.getParameterNames().asIterator();
        Map<String, String> paramMap = new HashMap<>();
        stringIterator.forEachRemaining(it-> paramMap.put(it, req.getParameter(it)));
        return paramMap;
    }
}
