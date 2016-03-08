<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <div class="container">        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        <!-- content -->
        <div class="col-sm-10 text-left">
            <div class="jumbotron">
                <h1>Hello, world!</h1>
                  <p>Strona ta jest leksykalnym zapisem postępów w nauce technologi internetowych.</p>
            </div>
            <div class="panel panel-default">
            <div class="panel-heading">Ostatnio dodane artykuły</div>
                <div class="list-group">
                    <c:forEach items="${lastArticle}" var="article" >
                        <a href="<c:url value="/artykul/${article.id}" />" class="list-group-item list-group-item-default" ><c:out value="${article.name}" /></a>                        
                    </c:forEach>
                </div>            
        </div>
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>    
</body>
</html>