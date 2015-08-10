<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en_US">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="app.appname" /> - <spring:message code="app.user.title.signin" /></title>
        <link rel="stylesheet" type="text/css" href="static/assets/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/base.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/common.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/signin.css" />
    </head>
    <body>
        <div class="container">
            <div class="row">
            <div class="auth-form col-md-4 well center-block">
                <div class="auth-form-body">
                    <c:if test="${not empty errorNo}">
                        <div class="alert alert-danger tc"><spring:message code="app.errorNo.${errorNo}" text="" /></div>
                    </c:if>
                    <form action='<c:url value="/doSignin" />' method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                                <input id="userAccount" name="userAccount" class="form-control" placeholder="<spring:message code="app.user.auth.useraccount" />" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="app.user.auth.password" />" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="checkbox" style="display: inline-block; margin: 0;">
                                <label><input type="checkbox" name="autologin" /><spring:message code="app.user.auth.autologin" /></label>
                            </div>
                            <div class="fr"><a href="<c:url value="/forgotpassword" />"><spring:message code="app.user.auth.forgotpassword" /></a></div>
                        </div>
                        <div class="center-block">
                            <button type="submit" class="btn btn-primary btn-signin"><spring:message code="app.user.action.signin" /></button>
                        </div>
                        <div class="pt10"><a href="<c:url value="/signup" />"><spring:message code="app.user.action.signup.link" /></a></div>
                    </form>
                </div>
            </div>
            </div>
        </div>
    </body>
    <script type="text/javascript" src="static/assets/jquery/jquery-1.11.1.js"></script>
    <script type="text/javascript" src="static/assets/jquery.form.js"></script>
    <script type="text/javascript" src="static/assets/bootstrap/js/bootstrap.min.js"></script>
</html>