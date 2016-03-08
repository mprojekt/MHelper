<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <div class="container">        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        
        <!-- content -->
        <c:url var="addAction" value="/artykul/dodaj" ></c:url>        
        <form:form action="${addAction}" modelAttribute="articleForm" method="POST" cssClass="form-horizontal" role="form">
            <div class="col-sm-10 text-left">
                <form:errors path="*">
                    <div class="alert alert-danger" role="alert">
                        <form:errors path="title" element="p" cssClass="text-danger"/>
                        <form:errors path="category" element="p" cssClass="text-danger"/>
                        <form:errors path="content" element="p" cssClass="text-danger"/>                            
                        <form:errors path="tags" element="p" cssClass="text-danger"/> 
                        <form:errors path="source" element="p" cssClass="text-danger"/> 
                    </div>
                </form:errors>
                <form:hidden path="id" />
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Nowy artykuł</h3>
                    </div>
                    <ul class="list-group">
                        <li class="list-group-item szare-tlo">
                            <div class="form-group <form:errors path="title" >has-error</form:errors>">
                                <label for="titleField" class="col-sm-1 control-label">Tytuł: </label>
                                <div class="col-sm-11">
                                    <form:input path="title" id="title" type="text" class="form-control" placeholder="Tutaj wpisz tytuł artykułu (wymagany)" aria-describedby="titleField" />
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item szare-tlo">
                            <div class="form-group">
                                <label for="categoryField" class="col-sm-1 control-label">Kategoria: </label>
                                <div class="col-sm-11">
                                    <div class="input-group <form:errors path="category" >has-error</form:errors>">
                                        <div class="input-group-btn">
                                            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Wybierz <span class="caret"></span></button>
                                            <ul class="dropdown-menu" role="menu">
                                                <c:forEach items="${categoryList}" var="category" > 
                                                    <li><a href="#" class="category" >${category}</a></li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                        <form:input path="category" id="category" type="text" class="form-control" placeholder="lub wpisz nazwę kategorii, do której należy dodawnay artykuł (wymagane)" aria-describedby="categoryField" />
                                    </div>                                   
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item szare-tlo">
                            <div class="panel-body <form:errors path="content" >has-error</form:errors>">
                                <form:textarea path="content" id="editor" rows="40" ></form:textarea>
                            </div>
                        </li>
                        <li class="list-group-item szare-tlo">
                            <div class="form-group <form:errors path="tags" >has-error</form:errors>">
                                <label for="tagsField" class="col-sm-1 control-label">Tagi: </label>
                                    <div class="col-sm-11">
                                        <form:input path="tags" id="tags" type="text" class="form-control" placeholder="Tutaj wpisz tagi artykułu" aria-describedby="tagsField" />
                                    </div>                        
                            </div>                            
                        </li>
                        <li class="list-group-item szare-tlo">
                            <div class="form-group <form:errors path="source" >has-error</form:errors>">
                                <label for="fileField" class="col-sm-1 control-label">Załącznik: </label>
                                    <div class="col-sm-11">
                                        <form:input path="source" id="file" type="text" class="form-control" placeholder="Tutaj wstaw link do kodu źródłowego (nie wymagane)" aria-describedby="tagsField" />
                                    </div>                        
                            </div>                            
                        </li>
                    </ul>
                    <div class="panel-footer">
                        <div class="btn-group btn-group-justified" role="group" >
                            <div class="btn-group" role="group">
                                <button type="submit" class="btn btn-default"><i class="fa fa-floppy-o"></i> Zapisz</button>
                            </div>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#wyczysc"><i class="fa fa-file-o"></i> Wyczyść</button>
                            </div>
                        </div>
                    </div>
                </div>      
            </div>
        </form:form>
        
        <!-- potwierdzenie usuniecia -->
        <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" id="wyczysc" aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-body">
                        <p class="text-danger">Wykonanie tego polecenia spowoduje nieodwracalne wyczyszczenie wszystkich wprowadzonych danych!</p>
                    </div>                    
                    <div class="modal-footer">
                        <button type="button" class="btn btn-success" data-dismiss="modal">Zrezygnuj</button>
                        <a href="<c:url value="/artykul/nowy" />" type="button" class="btn btn-danger">Wykonaj</a>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
    <script src="<c:url value='/js/my-scripts.article.js'/>" ></script>
</body>
</html>