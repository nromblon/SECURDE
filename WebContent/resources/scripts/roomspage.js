var roomName = "Meeting Room";
var rowNum = 5;
var numInterval = 26;


function init(){
	populateAvailable();

}

function addRow(parent,roomnum){
	
	var roomid = "rm"+roomnum;
	
	var line = "<tr> <td>"+roomName +" "+ roomnum + "</td>";
	
	var h,suffix,info;
	for(var i=0;i<numInterval-1;i++){
		h = (i/2)+7;
		suffix = h < 12? 'AM' : 'PM';
		h = ((h + 11) % 12 + 1);
		info = "Meeting Room "+roomnum+" | "+h+":00-"+h+":29 "+suffix;
		line+="<td> <div id ='"+roomid+"-"+i+ "' class='time-btn' title='Reserve "+info+"' onclick='clickedTimebtn(this,\""+info+"\")'>&nbsp;</div>"; //first interval (XX:00-XX:29)
		i++;
		info = "Meeting Room "+roomnum+" | "+h+":30-"+h+":59 "+suffix;
		line+="<div id ='"+roomid+"-"+i+ "' class='time-btn' title='Reserve "+info+"' onclick='clickedTimebtn(this,\""+info+"\")'>&nbsp;</div></td>"; //2nd interval (XX:30-XX:59)
	}
	line+="</tr>";
	parent.append(line);
}

function populateAvailable(){
	var tableBody = $(".table-body");
	for(var i=1;i<=rowNum;i++){
		addRow(tableBody,i);
	}
}

function updateSlots(){
	var date = $("#calendar").val();
	var timebtnList = $(".time-btn");
	
	//first, reset button tags
	var current;
	for(var i=0;i<timebtnList.length;i++){
		current = timebtnList[i];
		$(current).removeClass("selected");
		$(current).removeClass("unavailable");
	}
	
	$.get("rooms/update",{
		date: date
	}, function(data,status){
		var jsonArray = JSON.parse(data);
		console.log(jsonArray);
	
		for(var i=0;i<jsonArray.length;i++){
			var rmid = jsonArray[i]["rmid"];
			var slotid = jsonArray[i]["slotid"];
			var id = "#rm"+rmid+"-"+slotid;
			$(id).addClass("unavailable");
		}
	});
	
	
	
}

function delReservation(){
	console.log("del");
	var sel_slots = $(".selected");
	
	var tokens, rmid, slotid;
	
	var date = $("#calendar").val();
	if(date==""){
		alert("Select a date!");
		return;
	}
	var current;
	var flag = true;
	for(var i=0; i<sel_slots.length;i++){;
		current = sel_slots[i];
		
		tokens = $(current).attr("id").split("-");
		rmid = parseInt(tokens[0].substring(2,3));
		slotid = parseInt(tokens[1]);
		
		$.post("rooms/update",{
		rmid: rmid,
		slotid: slotid,
		date: date
			
		}, function(data,status){
			if(data=="error")
				flag = false;
			else
				updateSlots();
		});
		
		
	}
	if(!flag)
		alert("Cancellation unsuccessful!");
}


function reserve(){
	var sel_slots = $(".selected");

	var tokens, rmid, slotid;
	
	var date = $("#calendar").val();
	
	if(date==""){
		alert("Select a date!");
		return;
	}
	
	var current;
	var flag = true;
	for(var i=0; i<sel_slots.length;i++){;
		current = sel_slots[i];
		
		tokens = $(current).attr("id").split("-");
		rmid = parseInt(tokens[0].substring(2,3));
		slotid = parseInt(tokens[1]);
		
		$.post("rooms",{
		rmid: rmid,
		slotid: slotid,
		date: date
			
		}, function(data,status){
			if(data=="fail")
				flag = false;
			else
				updateSlots();
		});
		
		
	}
	if(!flag)
		alert("Reservation unsuccessful!");

}

