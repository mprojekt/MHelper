$(function() {
    $("#editor").sceditor({
        plugins: "xhtml",
        style: "../resources/css/jquery.sceditor.default.min.css",
        locale: "pl",
        toolbarExclude: "emoticon",
        emoticonsEnabled: false,
        width: "97,5%",
        resizeWidth: false
    });
    
    $('#tags').selectize({
        plugins: ['remove_button'],
        delimiter: ',',
        persist: false,
        create: function(input) {
            return {
                value: input,
                text: input
            };
        }
    });
    
    $('a.category').click(function() {
        var value = $(this).text();
        $('#category').val(value);
    });
});


