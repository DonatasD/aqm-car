<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log In</title>
</head>
<body>
<div class="container">
    <form method="POST" action="${contextPath}/login">
        <h2>Log in</h2>
        <div>
            <input name="username" type="text" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" placeholder="Password"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">Log In</button>
            <span>${err}</span>
            <h4>
                <a href="${contextPath}/register">Create an account</a>
            </h4>
        </div>
    </form>
</div>
</body>
</html>