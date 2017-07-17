var editMode = false;

$(document).ready(function() {
    for(var i = 0; i <= 100; i++) {
        $("#pub-year-select").append("<option>" + (1917+i) + "</option>");
    }

    copyDescriptions();
    $("#edit-button").click(function() {
        if(!editMode) {
            $(".description-elems").each(function(index) {
                $(this).addClass("hidden");
            })
            $(".edit-elems").each(function(index) {
                $(this).removeClass("hidden");                
            })
            $(this).text("CANCEL");
        } else {
            $(".edit-elems").each(function(index) {
                $(this).addClass("hidden");
            })
            $(".description-elems").each(function(index) {
                $(this).removeClass("hidden");                
            })
            $(this).text("EDIT");
        }
        editMode = !editMode;

    })
})

function copyDescriptions() {
    $("#pub-title").val($("#pub-title-text").text());
    $("#pub-author").val($("#pub-author-text").text());
    $("#pub-location").val($("#pub-location-text").text());
    $("#pub-publisher").val($("#pub-publisher-text").text());
}