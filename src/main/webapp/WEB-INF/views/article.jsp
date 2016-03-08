<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/head.jspf" %>

<body>
    <div class="container">        
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
        
        <!-- content -->
        <div class="col-sm-10 text-left">
            <div class="panel panel-default szare-tlo">
                <ul class="list-group">
                    <li class="list-group-item szare-tlo">
                        <h6 class="silver">
                            <div class="col-sm-3">
                                <c:out value="${article.category}" />
                            </div>
                            <div class="col-sm-3 col-sm-offset-6 text-right">
                                <c:out value="${article.author}"/>, 
                                [ <c:out value="${article.createDate}"/> ]
                            </div>
                        </h6>
                        <h2>
                            <security:authorize access="hasRole('admin')">
                                <a href="<c:url value="/artykul/edycja/${article.id}" />" class="btn btn-warning btn-xs"><i class="fa fa-pencil-square-o"></i> Edycja </a>
                                <c:if test="${article.visible}" >
                                    <a href="<c:url value="/artykul/ukryj/${article.id}" />" class="btn btn-default btn-xs"><i class="fa fa-check-square-o"></i></a>
                                </c:if>
                                <c:if test="${!article.visible}" >
                                    <a href="<c:url value="/artykul/pokaz/${article.id}" />" class="btn btn-default btn-xs"><i class="fa fa-square-o"></i></a>
                                </c:if>    
                            </security:authorize>
                            <c:out value="${article.title}"/>
                        </h2>                
                    </li>
                    <li class="list-group-item szare-tlo">
                        ${article.content}
                        <hr>
                        <div class="clearfix">
                            <nav>
                                <ul class="pager">
                                    <div class="col-sm-4">
                                        <li class="previous <c:if test="${prev.name eq 'brak'}" >disabled</c:if>">
                                            <a href="<c:out value="${prev.id}"/>"><i class="fa fa-arrow-left"></i> <c:out value="${prev.name}"/></a>
                                        </li>
                                    </div>                        
                                    <div class="col-sm-4">
                                        <c:if test="${article.source != null}">    
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    Kod źródłowy do pobrania: <br />
                                                    <a class="btn btn-default btn-block" href="<c:url value="${article.source}" />" role="button"><i class="fa fa-download"></i> Pobierz</a>
                                                </div>
                                            </div> 
                                        </c:if>
                                    </div>         
                                    <div class="col-sm-4">
                                        <li class="next <c:if test="${next.name eq 'brak'}" >disabled</c:if>">
                                            <a href="<c:out value="${next.id}"/>"><c:out value="${next.name}"/> <i class="fa fa-arrow-right"></i></a>
                                        </li>
                                    </div>
                                </ul>
                            </nav>
                        </div>                            
                    </li>
                    <li class="list-group-item szare-tlo">
                        <c:forEach items="${article.tags}" var="tag" >
                            <a class="btn btn-default btn-xs text-muted" href="<c:url value="/spis_tagow" />" role="button"><i class="fa fa-tag"></i> <c:out value="${tag}" /></a>  
                        </c:forEach>
                    </li>
                    <li class="list-group-item szare-tlo">
                        <h3>SKOMENTUJ</h3>
                        <c:url var="addComment" value="/artykul/komentarz/dodaj" ></c:url>        
                        <form:form action="${addComment}" modelAttribute="commentForm" method="POST">                            
                            <form:errors path="*" >
                                <!-- Błąd -->
                                <div class="panel panel-danger">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Błąd danych!</h3>
                                    </div>
                                    <div class="panel-body text-left bg-danger">
                                        <form:errors path="name" element="p" cssClass="text-danger"/>
                                        <form:errors path="mail" element="p" cssClass="text-danger"/>
                                        <form:errors path="text" element="p" cssClass="text-danger"/>
                                        <form:errors path="notify" element="p" cssClass="text-danger"/>                                    
                                    </div>
                                </div>
                            </form:errors>
                            <div class="panel-body">
                                <div class="clearfix">
                                    <div class="col-sm-3">
                                        <div class="input-group  input-group-sm <form:errors path="name" >has-error</form:errors>">
                                            <span class="input-group-addon" id="basic-addon1">Imię </span>
                                            <form:input path="name" type="text" class="form-control" placeholder="wpisz (wymagane)" />
                                        </div>
                                    </div>
                                    <div class="col-sm-7">
                                        <div class="input-group input-group-sm <form:errors path="mail" >has-error</form:errors><form:errors path="notify" >has-error</form:errors>">
                                            <span class="input-group-addon" id="basic-addon1">e-mail </span>
                                            <form:input path="mail" type="text" class="form-control" placeholder="wpisz" />
                                            <span class="input-group-addon">
                                                <form:checkbox path="notify" /> powiadom o odpowiedziach
                                            </span>
                                        </div>
                                    </div>
                                    <div class="col-sm-2">
                                        <div class="input-group input-group-sm">
                                            <button type="submit" class="btn btn-success btn-block"><i class="fa fa-share-square"></i> Opublikuj</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="top-margin-5">
                                    <form:textarea path="text" id="editor" rows="8" ></form:textarea> 
                                    <form:hidden path="articleId"/>
                                </div>
                            </div> 
                        </form:form>
                    </li>
                    <li class="list-group-item szare-tlo">
                        <h3>KOMENTARZE</h3>
                        <ul class="media-list">                            
                            <c:forEach items="${article.comments}" var="comment" varStatus="i">
                                <li class="media" id="${comment.id}">
                                    <hr>
                                    <%--<div class="col-sm-1">
                                        <div class="btn-group-vertical btn-block" role="group" >
                                            <div class="btn-group btn-group-xs" role="group">
                                                <button type="button" class="btn btn-success tooltip-plus" id="${comment.id}"><i class="fa fa-plus-square"></i></button>
                                            </div>
                                            <div class="btn-group btn-group-xs" role="group">
                                                <div class="btn btn-default tooltip-info" id="${comment.id}"><c:out value="${comment.commentLevel}"/></div>
                                            </div>                                            
                                            <div class="btn-group btn-group-xs" role="group">
                                                <button type="button" class="btn btn-danger tooltip-minus" id="${comment.id}"><i class="fa fa-minus-square"></i></button>
                                            </div>                                            
                                        </div>
                                    </div>--%>
                                    <div class="media-body">
                                        <div class="media-heading silver">                                        
                                            <div class="col-sm-4">
                                                <u>#${i.count} <i class="fa fa-user"></i> <c:out value="${comment.name}" /></u>
                                            </div>
                                            <div class="col-sm-3">
                                                <button type="button" class="btn btn-default btn-xs showCommentForm" id="${comment.id}" data-article="${article.id}" data-comment="${comment.id}">komentuj</button>
                                            </div>                                            
                                            <div class="col-sm-1">
                                                <security:authorize access="hasRole('admin')">
                                                    <button type="button" class="btn btn-danger btn-xs delete" id="${comment.id}"><i class="fa fa-times"></i></button>
                                                </security:authorize>
                                            </div>                                            
                                            <div class="col-sm-4 text-right">                                            
                                                <h6 class="silver">
                                                    [ <c:out value="${comment.createDate}"/> ]
                                                </h6>
                                            </div>                                        
                                        </div>
                                        ${comment.text}     
                                    </div>
                                </li>                                
                                <c:forEach items="${comment.comment}" var="c" varStatus="i">
                                    <li class="media" id="${c.id}">
                                        <hr>
                                        <div class="col-sm-1"></div>
                                        <%--<div class="col-sm-1">
                                            <div class="btn-group-vertical btn-block" role="group" >
                                                <div class="btn-group btn-group-xs" role="group">
                                                    <button type="button" class="btn btn-success tooltip-plus" id="${c.id}"><i class="fa fa-plus-square"></i></button>
                                                </div>
                                                <div class="btn-group btn-group-xs" role="group">
                                                    <div class="btn btn-default tooltip-info" id="${c.id}"><c:out value="${c.commentLevel}"/></div>
                                                </div>                                                
                                                <div class="btn-group btn-group-xs" role="group">
                                                    <button type="button" class="btn btn-danger tooltip-minus" id="${c.id}"><i class="fa fa-minus-square"></i></button>
                                                </div>
                                            </div>
                                        </div>--%>
                                        <div class="media-body">
                                            <div class="media-heading silver">                                        
                                                <div class="col-sm-4">
                                                    <u>#${i.count} <i class="fa fa-user"></i> <c:out value="${c.name}" /></u>
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-default btn-xs showCommentForm" id="${c.id}" data-article="${article.id}" data-comment="${comment.id}" >komentuj</button>
                                                </div>                                                
                                                <div class="col-sm-1">
                                                    <security:authorize access="hasRole('admin')">
                                                        <button type="button" class="btn btn-danger btn-xs delete" id="${c.id}"><i class="fa fa-times"></i></button>
                                                    </security:authorize>
                                                </div>                                                
                                                <div class="col-sm-4 text-right">                                            
                                                    <h6 class="silver">
                                                        [ <c:out value="${c.createDate}"/> ]
                                                    </h6>
                                                </div>                                        
                                            </div>
                                            ${c.text}     
                                        </div>
                                    </li>
                                </c:forEach>                                
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
            </div>     
        </div>
        
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </div>
    <script src="<c:url value='/js/my-scripts.comment.js'/>" ></script>
</body>
</html>