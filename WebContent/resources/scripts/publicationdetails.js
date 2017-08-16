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
    		type: $('#pub-type-select').find(":selected").val(),
    		title: $("#pub-title").val(),
    		author: $("#pub-author").val(),
    		location: $("#pub-location").val(),
    		publisher: $("#pub-publisher").val(),
    		year: $("#pub-year-select").val(),
    		tags: getCheckedTags()
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
    
    $("#reserve-button").click(function() {
    	var r = confirm("Are you sure you want to reserve this publication?");
        if (r == true) {
            $.post("../reserve", {
            	id: id
            }).done(function() {
            	location.reload();
            })
        } 
    })
    
    $("#override-reserve").click(function() {
    	var r = confirm("Are you sure you want to override this reservation?");
        if (r == true) {
            $.post("../override", {
            	id: id
            }).done(function() {
            	location.reload();
            })
        } 
    })
    
})

function getCheckedTags(){
	var tags = $("input[name='tags']:checked");
	var tagIds = [];
	
	for(var i = 0; i < tags.length; i++) {
		tagIds.push(tags[i].value);
	}
	
	return tagIds;
}

function copyDescriptions() {
    $("#pub-title").val($("#pub-title-text").text());
    $("#pub-author").val($("#pub-author-text").text());
    $("#pub-location").val($("#pub-location-text").text());
    $("#pub-publisher").val($("#pub-publisher-text").text());
}