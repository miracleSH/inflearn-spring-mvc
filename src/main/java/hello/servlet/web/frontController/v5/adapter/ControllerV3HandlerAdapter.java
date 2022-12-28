package hello.servlet.web.frontController.v5.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.web.frontController.ModelView;
import hello.servlet.web.frontController.v3.ControllerV3;
import hello.servlet.web.frontController.v5.MyHandlerAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean support(Object handler) {
        return handler instanceof ControllerV3;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Iterator<String> stringIterator = req.getParameterNames().asIterator();
        Map<String, String> paramMap = new HashMap<>();
        stringIterator.forEachRemaining(it-> paramMap.put(it, req.getParameter(it)));
        return paramMap;
    }
}
