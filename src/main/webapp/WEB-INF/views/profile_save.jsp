<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.sample.webapp.dto.OPResult" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" href="css/spop.min.css" />
    <style>
        #all {
            margin:0px auto;
            width:500px; /* 必须制定宽度 */
            height:200px;
            background-color:powderblue;
            text-align: center;
        }
        #id-form-register {
            padding: 55px;
        }
    </style>
</head>
<body>
<h1 align="center">Register Page</h1>
<div id="all">
    <%
        OPResult result = (OPResult)request.getAttribute("result");
    %>
    <p style="color: red"><%=result.getExtraInfo().getStateInfo()%></p>
    <form action="register.do" method="post" id="id-form-register">
        用户名：<input type="text" name="username"><br>
        密码: <input type="text" name="password"><br>
        昵称: <input type="text" name="nickname"><br>
        <input type="submit" value="注册">
    </form>
</div>
</body>
</html>