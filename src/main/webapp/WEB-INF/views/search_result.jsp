<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <div class="container">        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        <!-- content -->
        <div class="col-sm-10 text-left">
            <div class="panel panel-default szare-tlo">
                <div class="panel-heading">
                    <h3 class="panel-title">Spis artykułów pasujących do szukanej frazy: <span class="label label-success"><c:out value="${searchText}" /></span></h3>
                </div>
                <div class="panel-body">                        
                    <ol>
                        <c:forEach items="${searchResult}" var="art">
                            <li>
                                <a href="<c:url value="/artykul/${art.id}" />" ><c:out value="${art.name}" /></a>
                            </li>
                        </c:forEach>
                    </ol>
                </div> 
            </div>     
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>    
</body>
</html>