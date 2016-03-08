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
                    <h3 class="panel-title">Spis Tagów</h3> 
                </div>
                <div class="panel-body">
                    <c:forEach items="${tags}" var="tag">
                        <a href="#" class="btn btn-info tag" type="button" id="<c:out value="${tag}" />">
                            <i class="fa fa-tag"></i> <c:out value="${tag}" /> <span class="badge">${tag.number}</span><!-- -->
                        </a>
                    </c:forEach>
                </div> 
            </div>     
        </div>
        <div class="col-sm-10 text-left hidden" id="hidden">
            <div class="panel panel-default szare-tlo ">
                <div class="panel-heading">
                    <h3 class="panel-title">Spis Artykułów, które zostały oznaczone tagiem: <span id="tag" class="label label-primary"></span></h3> 
                </div>
                <div class="panel-body" id="result">
                                       
                </div>                
            </div>            
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div> 
    <script src="<c:url value='/js/my-scripts.tags.js'/>" ></script>
</body>
</html>