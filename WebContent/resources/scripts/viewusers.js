$(document).ready(function() {
	$(".user-row").on("click", ".lock-button", function() {
		console.log($(this))
		var changed = false;
		if($(this).data("is-locked") == 1){
			$(this).data("is-locked", 0);
			$(this).removeClass("button-primary");
			$(this).text("Lock");
			changed = true;
		} else {
			$(this).data("is-locked", 1);
			$(this).addClass("button-primary");
			$(this).text("Unlock");
			changed = true;
		}
		
		if(changed) {
			$.post("../userlock", {
	        	userId: $(this).data("userid"),
	        	isLocked: $(this).data("is-locked")
	        }).done(function() {
	
	        })
		}
	});
})