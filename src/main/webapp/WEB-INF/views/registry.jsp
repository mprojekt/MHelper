<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <div class="container">        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        <!-- content -->
        <div class="col-sm-10 text-left">
            
            <c:url var="addAAction" value="/uzytkownik/dodaj" ></c:url>        
            <form:form action="${addAAction}" modelAttribute="registryForm" method="POST" cssClass="form-horizontal" role="form"> 

                <!-- Formularz rejestracji -->
                <div class="col-md-offset-3 col-lg-6">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">Rejestracja</h3>
                        </div>
                        <div class="panel-body text-left bg-info">                                                       
                            <div class="form-group <form:errors path="name" >has-error</form:errors>">
                                <label for="inputRName" class="col-sm-2 control-label">Imię</label>
                                <div class="col-sm-10">
                                    <form:input path="name" type="text" class="form-control" id="inputRName" placeholder="Twoje imię (nie jest wymagane)" />
                                </div>
                            </div>
                            <div class="form-group <form:errors path="mail" >has-error</form:errors>">
                                <label for="inputRMail" class="col-sm-2 control-label">e-mail</label>
                                <div class="col-sm-10">
                                    <form:input path="mail" type="email" cssClass="form-control" id="inputRMail" placeholder="Twój adres e-mail (wymagany)" />
                                </div>
                            </div>
                            <div class="form-group <form:errors path="login" >has-error</form:errors>">
                                <label for="inputRUsername" class="col-sm-2 control-label">Login</label>
                                <div class="col-sm-10">
                                    <form:input path="login" type="text" cssClass="form-control" id="inputRUsername" placeholder="Twoja nazwa użytkownika (wymagana)" />
                                </div>
                            </div>                            
                            <div class="form-group <form:errors path="password" >has-error</form:errors>">
                                <label for="inputRPassword" class="col-sm-2 control-label">Hasło</label>
                                <div class="col-sm-10">
                                    <form:password path="password" cssClass="form-control" id="inputRPassword" placeholder="Twoje hasło (wymagane)" />
                                </div>
                            </div>
                            <div class="form-group <form:errors path="passwordConfirm" >has-error</form:errors>">
                                <label for="inputRPasswordC" class="col-sm-2 control-label">Powtórz</label>
                                <div class="col-sm-10">
                                    <form:password path="passwordConfirm" cssClass="form-control" id="inputRPasswordC" placeholder="Twoje hasło ponownie (wymagane)" />
                                </div>
                            </div>                                                        
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-4">
                                    <button type="submit" class="btn btn-success">Zarejestruj się</button>
                                </div>
                                <div class="col-sm-6">
                                    <a href="<c:url value="/index"/>" class="btn btn-danger">Zrezygnuj</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>               

                <form:errors path="*" >
                    <!-- Błąd -->
                    <div class="col-lg-3">
                        <div class="panel panel-danger">
                            <div class="panel-heading">
                                <h3 class="panel-title">Błąd danych!</h3>
                            </div>
                            <div class="panel-body text-left bg-danger">
                                <form:errors path="name" element="p" cssClass="text-danger"/>
                                <form:errors path="login" element="p" cssClass="text-danger"/>
                                <form:errors path="mail" element="p" cssClass="text-danger"/>
                                <form:errors path="password" element="p" cssClass="text-danger"/>
                                <form:errors path="passwordConfirm" element="p" cssClass="text-danger"/>                                    
                            </div>
                        </div>
                    </div>
                </form:errors>
            </form:form>
            
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
</body>
</html>