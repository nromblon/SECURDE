$(document).ready(function() {
    for(var i = 0; i <= 100; i++) {
        $("#pub-year-select").append("<option>" + (1917+i) + "</option>");
    }
})