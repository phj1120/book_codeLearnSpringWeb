<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>ex00</title>
</head>
<body>
    <%-- <h4>  name : ${peopleDTO} </h4> --%>
    <h4>  name : ${peopleDTO.getName()} </h4>
    <h4>  age  : ${peopleDTO.getAge()} </h4>
    <h4> birth : ${peopleDTO.getBirth()} </h4>
</body>
</html>
