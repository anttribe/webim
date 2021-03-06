<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en_US">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="app.appname" /> - <spring:message code="app.user.action.signup" /></title>
        <link rel="stylesheet" type="text/css" href="static/assets/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/base.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/common.css" />
        <link rel="stylesheet" type="text/css" href="static/static/css/signin.css" />
    </head>
    <body>
        <div class="container">
            <div class="row">
            <div class="auth-form col-md-5 well center-block signup-form">
                <div class="auth-form-body">
                    <c:if test="${not empty errorNo}">
                        <div class="alert alert-danger tc"><spring:message code="app.errorNo.${errorNo}" text="" /></div>
                    </c:if>
                    <form id="signup-form" action='<c:url value="/doSignup" />' method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
                                <input id="email" name="email" class="form-control" placeholder="<spring:message code="app.user.email" />" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                                <input id="username" name="username" class="form-control" placeholder="<spring:message code="app.user.username" />" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input type="password" id="password" name="password" class="form-control" placeholder="<spring:message code="app.user.password" />" />
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="<spring:message code="app.user.confirmPassword" />" />
                            </div>
                        </div>
                        <div class="alert alert-warning tc"><spring:message code="app.user.prompt.signup" /></div>
                        <div class="center-block">
                            <button type="submit" class="btn btn-success btn-signup"><spring:message code="app.user.action.signup" /></button>
                        </div>
                        <div class="pt10"><a href="<c:url value="/signin" />"><spring:message code="app.user.action.signin.link" /></a></div>
                    </form>
                </div>
            </div>
            </div>
        </div>
        <script type="text/javascript" src="static/assets/jquery/jquery-1.11.1.js"></script>
        <script type="text/javascript" src="static/assets/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="static/assets/jquery-validation/jquery.validate.min.js"></script>
        <script type="text/javascript">
            $('#signup-form').validate({
            	//focusCleanup: true,
            	errorElement: 'span',
            	wrapper: 'div',
            	errorPlacement: function(error, element){
            		error.addClass('tooltip-error left').css({'left': element.position().left + element.outerWidth()}).appendTo(element.parent());
            		// 解决错误提示信息不随窗口的变化而变化的问题
            		$(window).bind({
            			resize: function(){
            				error.css({'left': element.position().left + element.outerWidth()});
            			}
            		});
            	},
            	rules: {
            		email: {
            			required:true,
                        email:true,
                        remote: {
                        	type: 'POST',
                        	url: 'signup/emailUnique',
                        	data: {
                        		email: function(){return $('#email').val();}
                        	}
                        }
            		},
            		username: {
            			required:true,
            			remote: {
                        	type: 'POST',
                        	url: 'signup/usernameUnique',
                        	data: {
                        		username: function(){ return $('#username').val(); }
                        	}
                        }
            		},
            		password: {
            			required:true
            		},
            		confirmPassword: {
            			required:true,
            			equalTo: '#password'
            		}
            	},
            	messages: {
            		email: {
            			required: '<spring:message code="app.common.error.required" />',
                        email: '<spring:message code="app.errorNo.010008" />',
                        remote: '<spring:message code="app.errorNo.010005" />'
            		},
            		username: {
            			required: '<spring:message code="app.common.error.required" />',
            			remote: '<spring:message code="app.errorNo.010006" />'
            		},
            		password: {
            			required: '<spring:message code="app.common.error.required" />'
            		},
            		confirmPassword: {
            			required: '<spring:message code="app.common.error.required" />',
            			equalTo: '<spring:message code="app.errorNo.010007" />'
            		}
            	}
            });
        </script>
    </body>
</html>