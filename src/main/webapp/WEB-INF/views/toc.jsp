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
                    <h3 class="panel-title">Spis Treści</h3>
                </div>
                <div class="panel-body">                        
                    <ol>
                        <c:forEach items="${toc}" var="toc">
                            <c:if test="${toc.descr != 'Niewidoczne'}" >
                            <li>
                                <c:out value="${toc.descr}" />
                            </li>
                            <ul>
                                <c:forEach items="${toc.list}" var="art" >
                                    <li>                                        
                                        <security:authorize access="hasRole('admin')">
                                            <a href="<c:url value="/artykul/edycja/${art.id}" />" class="btn btn-warning btn-xs"><i class="fa fa-pencil-square-o"></i> Edycja </a>
                                            <c:if test="${art.visible}" >
                                                <a href="<c:url value="/artykul/ukryj/${art.id}" />" class="btn btn-default btn-xs"><i class="fa fa-check-square-o"></i></a>
                                            </c:if>
                                            <c:if test="${!art.visible}" >
                                                <a href="<c:url value="/artykul/pokaz/${art.id}" />" class="btn btn-default btn-xs"><i class="fa fa-square-o"></i></a>
                                            </c:if>
                                        </security:authorize>
                                        <a href="<c:url value="/artykul/${art.id}" />" ><c:out value="${art.name}" /></a>
                                    </li>
                                </c:forEach>
                            </ul>
                            </c:if>
                        </c:forEach>
                        <security:authorize access="hasRole('admin')">
                            <li>
                                <c:out value="${hiden.descr}" />
                            </li>
                            <ul>
                                <c:forEach items="${hiden.list}" var="art" >
                                    <li>                                                
                                        <security:authorize access="hasRole('admin')">
                                            <a href="<c:url value="/artykul/edycja/${article.id}" />" class="btn btn-warning btn-xs"><i class="fa fa-pencil-square-o"></i> Edycja </a>
                                            <c:if test="${article.visible}" >
                                                <a href="<c:url value="/artykul/ukryj/${article.id}" />" class="btn btn-default btn-xs"><i class="fa fa-check-square-o"></i></a>
                                            </c:if>
                                            <c:if test="${!article.visible}" >
                                                <a href="<c:url value="/artykul/pokaz/${article.id}" />" class="btn btn-default btn-xs"><i class="fa fa-square-o"></i></a>
                                            </c:if>
                                        </security:authorize>
                                        <a href="<c:url value="/artykul/${art.id}" />" ><c:out value="${art.name}" /></a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </security:authorize>
                    </ol>
                </div> 
            </div>     
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>    
</body>
</html>