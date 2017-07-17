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

function reserve(){
	var sel_slots = $(".selected");
	


}

function clickedTimebtn(source,schedDetails){
	var parent = $("#"+source.id);
	console.log(schedDetails);
	parent.toggleClass("selected");
	
}