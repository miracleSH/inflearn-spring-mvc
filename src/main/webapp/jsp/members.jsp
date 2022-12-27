<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>id</td>
        <td>userName</td>
        <td>age</td>
    </tr>
    <%
        for (Member member : members){
            out.print("<tr>");
            out.print("<td>" + member.getId() + "</td>");
            out.print("<td>" + member.getUserName() + "</td>");
            out.print("<td>" + member.getAge() + "</td>");
            out.print("</tr>");
        }
    %>
</table>
</body>
</html>
