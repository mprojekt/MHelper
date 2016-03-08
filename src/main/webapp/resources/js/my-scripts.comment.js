$(function() {
    
    $("#editor").sceditor({
        plugins: "xhtml",
        style: "../resources/css/jquery.sceditor.default.min.css",
        locale: "pl",
        toolbar: "bold,italic,underline|link,code,quote",
        emoticonsEnabled: false,
        width: "97,5%",
        resizeWidth: false
    });
    
    $('.tooltip-plus').tooltip({
        placement: "right",
        title: "dobry komentarz"
    });
    
    $('.tooltip-info').tooltip({
        placement: "right",
        title: "poziom komentarza"
    });
    
    $('.tooltip-minus').tooltip({
        placement: "right",
        title: "słaby komentarz"
    });
    
    $('button.delete').click(function(){
        var idComment = $(this).attr( 'id' );
        $.ajax({
            type: "POST",
            url: "/MHelper/rest/comment/delete",
            data: "idComment=" + idComment,
            dataType: "text",
            success: function(){
                var param = '[id="' + idComment + '"]';                
                $('li.media').filter(param).remove();
                alert("Komentarz został usunięty!");
            }
        });
    });
    
    $('button.showCommentForm').click(function(){
        var idComment = $(this).attr( 'id' );
        var idArticle = $(this).attr( 'data-article');
        var idC = $(this).attr( 'data-comment');
        var param = '[id="' + idComment + '"]'; 
        var commentForm =   '<li class="media" id="commentFormView">'+      
                                '<form action="/MHelper/artykul/komentarz/komentarz/dodaj" method="POST">'+
                                    '<div class="clearfix">'+
                                        '<div class="col-sm-3">'+
                                            '<div class="input-group  input-group-sm">'+
                                                '<span class="input-group-addon" id="basic-addon1">Imię </span>'+
                                                '<input name="name" type="text" class="form-control" placeholder="wpisz (wymagane)" required>'+
                                            '</div>'+
                                        '</div>'+
                                        '<div class="col-sm-2 col-sm-offset-7">'+
                                            '<div class="input-group input-group-sm">'+
                                                '<button type="submit" class="btn btn-success btn-block"><i class="fa fa-share-square"></i> Opublikuj</button>'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="top-margin-5">'+
                                        '<textarea name="text" id="editor2" rows="8" class="comment" required></textarea>'+
                                        '<input name="idArticle" type="hidden" value="' + idArticle + '"  >'+
                                        '<input name="idComment" type="hidden" value="' + idC + '"  >'+
                                    '</div>'+
                                '</form>'+    
                            '</li>';
        $('li#commentFormView').remove();
        $('li.media').filter(param).append(commentForm);
         
        
        $("#editor2").sceditor({
            plugins: "xhtml",
            style: "../resources/css/jquery.sceditor.default.min.css",
            locale: "pl",
            toolbar: "bold,italic,underline|link,code,quote",
            emoticonsEnabled: false,
            width: "97,5%",
            resizeWidth: false
        });
    });
    
    $('button.tooltip-plus').click(function(){
        var idComment = $(this).attr( 'id' );
        $.ajax({
            type: "POST",
            url: "/MHelper/rest/comment/plus",
            data: "idComment=" + idComment,
            dataType: "text",
            success: function(data){
                var param = '[id="' + idComment + '"]';                
                $('.tooltip-info').filter(param).html(data);
                $('button.tooltip-plus').filter(param).addClass('disabled');
                $('button.tooltip-minus').filter(param).addClass('disabled');
            }
        });
    });
    
    $('button.tooltip-minus').click(function(){
        var idComment = $(this).attr( 'id' );
        $.ajax({
            type: "POST",
            url: "/MHelper/rest/comment/minus",
            data: "idComment=" + idComment,
            dataType: "text",
            success: function(data){
                var param = '[id="' + idComment + '"]';
                $('.tooltip-info').filter(param).html(data);
                $('button.tooltip-minus').filter(param).addClass('disabled');
                $('button.tooltip-plus').filter(param).addClass('disabled');
            }
        });
    });
    
});