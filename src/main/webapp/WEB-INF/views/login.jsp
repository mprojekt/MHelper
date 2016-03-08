<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <div class="container">        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        <!-- content -->
        <div class="col-sm-10">
            <div class="col-md-offset-3 col-lg-6">
                <c:if test="${loginError eq 'true'}" >
                    <div class="alert alert-danger" role="alert">Nieprawidłowy login lub hasło!</div>
                </c:if>
                <div class="panel panel-info ">
                    <div class="panel-heading blekitny-gradient">
                        <h3 class="panel-title">Logowanie do serwisu</h3>
                    </div>
                    <div class="panel-body text-left bg-info">                        
                        <c:url var="postLoginUrl" value="/j_spring_security_check" />
                        <form action="${postLoginUrl}" method="POST" class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">Login</label>
                                <div class="col-sm-10">
                                    <input name="j_username" type="text" class="form-control" id="inputEmail3" placeholder="Twój login">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">Hasło</label>
                                <div class="col-sm-10">
                                    <input name="j_password" type="password" class="form-control" id="inputPassword3" placeholder="Twoje hasło">
                                </div>
                            </div>                      
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-3">
                                    <button title="submit" class="btn btn-success">Zaloguj się</button>
                                </div>
                                <div class="col-sm-7">
                                    <a href="<c:url value="/rejestracja"/>" class="btn btn-info">Zarejestruj się</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>