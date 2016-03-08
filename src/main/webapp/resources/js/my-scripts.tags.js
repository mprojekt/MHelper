$(function() {
    
    $('a.tag').click(function() {        
        var tagName = $(this).attr( 'id' );
        var templatePre = "<li><a href='/MHelper/artykul/";
        var templetePost = "</a></li>\n";
        var insert = "";
                
        $.ajax({
            type: "GET",
            url: "/MHelper/rest/tag",
            data: "tag=" + tagName,
            dataType: "json",
            beforeSend: function () {
                $('#tag').html("<i class='fa fa-tag'></i> " + tagName);
                $('#result').children().remove();
                $('#result').append("<p class='text-center'><i class='fa fa-spinner fa-3x fa-spin'></i></p>");
            },
            success: function(result) {
                for(i = 0; i < result.length; i++){
                    insert += templatePre + result[i].id + "'>" + result[i].name + templetePost;
                }
                $('#result').children().remove();
                $('#result').append("<ul>" + insert + "</ul>");
            }
        });
            
        $('#hidden').removeClass('hidden');
    });
});



