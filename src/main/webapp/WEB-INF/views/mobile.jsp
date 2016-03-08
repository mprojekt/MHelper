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
                    <h3 class="panel-title">Wersja na smartfony</h3>
                </div>
                <div class="panel-body"> 
                    ${actual.content}
                </div> 
            </div>     
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>    
</body>
</html>