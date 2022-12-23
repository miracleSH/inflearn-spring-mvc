package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. 파라미터 전송 기능
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        System.out.println("전체 파라미터 조회");
        Enumeration<String> parameterNames = req.getParameterNames();
        parameterNames.asIterator().forEachRemaining(it-> System.out.println(it + "= " + req.getParameter(it)));
        System.out.println("전체 파라미터 조회 -end");
        System.out.println();

        System.out.println("단일 파라미터 조화");
        System.out.println(req.getParameter("userName"));
        System.out.println(req.getParameter("age"));

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] userNames = req.getParameterValues("userName");
        for (String userName : userNames) {
            System.out.println("userName: " + userName);
        }
        resp.getWriter().write("Ok");
    }
}
