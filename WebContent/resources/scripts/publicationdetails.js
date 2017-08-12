var editMode = false;

$(document).ready(function() {
	if(privilege == "2" || privilege == "3") {
		$("#edit-button").show();
		$("#delete-button").show();
	}
	else {
		$("#edit-button").hide();
		$("#delete-button").hide();
	}
	
	if(privilege == "1")
		$(".review-section").show();
	else
		$(".review-section").hide();
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
    
    $("#submitEdit").click(function() {
    	$.post("../editbook", {
    		id: id,
    		title: $("#pub-title").val(),
    		author: $("#pub-author").val(),
    		location: $("#pub-location").val(),
    		publisher: $("#pub-publisher").val(),
    		year: $("#pub-year-select").val()
    	}).done(function() {
        	location.reload()
        })
    });
    
    $("#delete-button").click(function() {
    	var r = confirm("Are you sure you want to delete this publication?");
        if (r == true) {
            $.post("../deletebook", {
            	id: id
            }).done(function() {
            	window.location.href = "../search"
            })
        } 
    })
})

function copyDescriptions() {
    $("#pub-title").val($("#pub-title-text").text());
    $("#pub-author").val($("#pub-author-text").text());
    $("#pub-location").val($("#pub-location-text").text());
    $("#pub-publisher").val($("#pub-publisher-text").text());
}