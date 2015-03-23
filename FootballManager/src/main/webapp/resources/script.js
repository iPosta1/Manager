/*---------------Checking league form----------------------------*/
function validateLeagueForm() {

	var errCount = 0;
	var name = document.getElementById('leaguename').value;

	var sttime = document.getElementById('datetimepicker1').value;
	var stdate = document.getElementById('datetimepicker').value;

	/* check date */
	if (stdate == null || stdate.length < 6) {
		alert("select start date ");
		errCount++;
	}
	/* check time */
	if (sttime == null || sttime.length < 4) {
		errCount++;
	}

	/* check name length */
	if (name.length < 6) {
		document.getElementById("createleague_error").style.display = "block";
		errCount++;
	} else {
		document.getElementById("createleague_error").style.display = "none";
	}

	/* if no errors in input, check league */
	if (errCount == 0) {
		checkLeague(stdate, sttime);
	} else
		return false;
}

/* check league and submit */
function checkLeague(stdate, sttime) {
	// get user name
	var name = document.getElementById("leaguename").value;
	// ajax request, check if user exists
	$
			.ajax({
				type : 'get',
				url : 'http://localhost:8080/FootballManager/checkleague',
				dataType : "json",
				data : {
					'leaguename' : name
				},
				response : 'text',
				success : function(data) {
					if (data == 1) {
						// if exists - show error
						document.getElementById("createleague_error").style.display = "block";
					} else {
						// get form
						var form = document.getElementById("createLeagueForm");
						/* add date and time for format yyyy-MM-dd HH:mm */
						document.getElementById('datetimepicker').value = stdate
								+ " 00:00";
						document.getElementById('datetimepicker1').value = "2300-01-01 "
								+ sttime;
						form.submit(); // sumbit form
						// hide error
						document.getElementById("createleague_error").style.display = "none";

					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
					//alert("error= " + errorThrown);

				}
			});
}
/*---------------End of checking league form----------------------------*/

/*------------------ checking user -------------------------------------*/
/* checking input values */
function validateForm() {
	var username = document.getElementById("usrname").value;
	var password = document.getElementById("pssword").value;
	var repeate_password = document.getElementById("rpssword").value;
	var email = document.getElementById("email").value;

	var errCount = 0;

	if (username == null || username == "" || username.length < 4) {
		document.getElementById("username_error").style.display = "block";
		errCount = +1;
	} else {
		document.getElementById("username_error").style.display = "none";
	}

	if (password.length < 6) {
		document.getElementById("password_error").style.display = "block";
		errCount = +1;
	} else {
		document.getElementById("password_error").style.display = "none";
	}

	if (repeate_password != password) {
		document.getElementById("rpassword_error").style.display = "block";
		errCount = +1;
	} else {
		document.getElementById("rpassword_error").style.display = "none";
	}

	/*
	 * checking email/ Email must have @, dot . after @, and at least 2 sumbols
	 * after @
	 */
	var atpos = email.indexOf("@");
	var dotpos = email.lastIndexOf(".");
	if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
		document.getElementById("email_error").style.display = "block";
		errCount = +1;
	} else {
		document.getElementById("email_error").style.display = "none";
	}

	if (errCount > 0)
		return false;
	else {
		checkuser();
	}

}

/* check user and submit */
function checkuser() {
	// get user name
	var _username = document.getElementById("usrname").value;

	// ajax request, check if user exists
	$
			.ajax({
				type : 'get',
				url : 'http://localhost:8080/FootballManager/checkuser',
				dataType : "json",
				data : {
					'username' : _username
				},
				response : 'text',
				success : function(data) {
					if (data == 1) {
						// if exists - show error
						document.getElementById("userexists_error").style.display = "block";
					} else {
						// get form
						var form = document.getElementById("reg_form");
						form.submit(); // sumbit form
						// hide error
						document.getElementById("userexists_error").style.display = "none";

					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
				//	alert("error= " + errorThrown);

				}
			});
}
/*------------------End of checking user -------------------------------------*/

/* shows edit window */
function show(username, password, group, email) {

	document.getElementById('input_window').style.display = "block";

	document.getElementById('i_username').innerHTML = username;
	document.getElementById('i_name').value = username;
	document.getElementById('i_password').value = password;
	document.getElementById('i_group').value = group;
	document.getElementById('i_email').value = email;
}

/* closes window */
function i_close() {
	document.getElementById('input_window').style.display = "none";
}

/* deletes user */
function deleteUser() {
	document.location.href = "delete/"
			+ document.getElementById('i_name').value;
}

/* edits user */
function editUser() {
	var username = document.getElementById('i_username').innerHTML;
	var newusername = document.getElementById('i_name').value;
	var password = document.getElementById('i_password').value;
	var group = document.getElementById('i_group').value;
	var email = document.getElementById('i_email').value;

	document.location.href = "edit/" + username + "," + newusername + ","
			+ password + "," + group + "," + email;
}

/* search for user */
function searchUser() {

	var search_name = document.getElementById('search_name').value;
	document.location.href = "search/" + search_name;

}

/*---get teams of the league---*/
function getTeams(leaguename) {
	document.location.href = "leagues/" + leaguename + "/teams";
}

/*---get standings of the league---*/
function intoTheLeague(leaguename, started) {
	if (started == 'y') {
		document.location.href = "leagues/" + leaguename + "/standings";
	} else {
		document.location.href = "leagues/" + leaguename + "/teams";
	}

}

/* to create team view */
function createTeam(leaguename) {
	document.location.href = "teams/create";
}

/*---------------Checking team form----------------------------*/
function validateTeamForm(leaguename) {

	var errCount = 0;
	var teamname = document.getElementById('teamname').value;

	/* check name length */
	if (teamname.length < 6) {
		document.getElementById("createteam_error").style.display = "block";
		errCount++;
	} else {
		document.getElementById("createteam_error").style.display = "none";
	}

	/* if no errors in input, check league */
	if (errCount == 0) {
		checkTeam(teamname, leaguename);
	} else
		return false;
}
/* check league if exists */
function checkTeam(teamname, leaguename) {
	// get user name
	// ajax request, check if user exists
	$
			.ajax({
				type : 'get',
				url : 'http://localhost:8080/FootballManager/checkteam',
				dataType : "json",
				data : {
					'teamname' : teamname,
					'leaguename' : leaguename
				},
				response : 'text',
				success : function(data) {
					if (data == 1) {
						// if exists - show error
						document.getElementById("createteam_error").style.display = "block";
					} else {
						// get form
						var form = document.getElementById("createTeamForm");
						form.submit(); // sumbit form

						// hide error
						document.getElementById("createteam_error").style.display = "none";

					}
				},
				error : function(XmlHttpRequest, textStatus, errorThrown) {
				//	alert("error= " + errorThrown);

				}
			});
}
/*---------------End of checking league form----------------------------*/

function startLeague(leaguename) {
	document.location.href = "teams/start";
}

function leaveLeague(leaguename) {
	document.location.href = "teams/leave";
}

function toDraft(leaguename) {
	document.location.href = "draft";
}

function delfirst(leaguename) {

	document.location.href = leaguename + "/pop";

}

/* delete team from team's queue */
function dropTeamFromQ() {
	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/dropteam',
		dataType : "json",
		cache : false,
		data : {},
		response : 'text',
		success : function(data) {
			if (data == 1) {
				// dropPlayer(playerID, leaguename);
			} else {

			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert("error= " + errorThrown);
		}
	});
}

var selectedRow; // Selected row in player's table

/* setting players info */
function p_info(id, firstname, lastname, photo, birthdate,height,weight,
		position,ovr,speed,agility,awareness,catching,carrying,
		tackling,breakTackle,jumping,tpower,taccuracy,kpower,
		kaccuracy,strength, row) {

	document.getElementById('p_id').innerHTML = id;
	document.getElementById('p_firstname').innerHTML = firstname;
	document.getElementById('p_lastname').innerHTML = lastname;
	document.getElementById('p_photo').innerHTML = photo;
	document.getElementById('p_birthdate').innerHTML = birthdate;
	document.getElementById('p_height').innerHTML = height;
	document.getElementById('p_weight').innerHTML = weight;
	document.getElementById('p_position').innerHTML = position;
	document.getElementById('p_ovr').innerHTML = ovr;
	document.getElementById('p_speed').innerHTML = speed;
	document.getElementById('p_agility').innerHTML = agility;
	document.getElementById('p_awareness').innerHTML = awareness;
	document.getElementById('p_catching').innerHTML = catching;
	document.getElementById('p_carrying').innerHTML = carrying;
	document.getElementById('p_tackling').innerHTML = tackling;
	document.getElementById('p_breaktackle').innerHTML = breakTackle;
	document.getElementById('p_jumping').innerHTML = jumping;
	document.getElementById('p_throwpower').innerHTML = tpower;
	document.getElementById('p_throwaccuracy').innerHTML = taccuracy;
	document.getElementById('p_kickpower').innerHTML = kpower;
	document.getElementById('p_kickaccuracy').innerHTML = kaccuracy;
	document.getElementById('p_strength').innerHTML = strength;

	selectedRow = row; // get selected row
}

/* draft player and then drop player and team */
function draftPlayer(username, leaguename) {
	// get player id
	var lplayerID = document.getElementById('p_id').innerHTML;
	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/draftplayer',
		dataType : "text",
		cache : false,
		data : {
			'lplayerID' : lplayerID,
			'username' : username
		},
		response : 'text',
		success : function(data) {
			if (data != "0") {
				//getPlayers(leaguename);
				getRosterTeam(leaguename,principalName);
				/* draft picks */

			} else {

				
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert("error= " + errorThrown);
		}
	});
}

function getPlayers(leaguename) {
	document.getElementById("page_loader").style.display = "block";
	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/players',
		dataType : "json",
		contentType : "application/json",
		cache : false,
		success : function(data) {
			//alert(JSON.stringify(data));
			drawTable(data);
			document.getElementById("page_loader").style.display = "none";

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert(XmlHttpRequest.responseText);
			// alert("error= " + errorThrown);

		}
	});
}

var principalName; // principal's name


function getRosterTeam(leaguename,user) {
	document.getElementById("roster_loading").style.visibility = "visible";
	document.getElementById("roster_loading").style.opacity = "1"; 
	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/teamroster',
		dataType : "json",
		contentType : "application/json",
		cache : false,
		data : {
			'username' : user
		},
		success : function(data) {
			drawRoster(data);
				document.getElementById("roster_loading").style.visibility = "hidden";   
				document.getElementById("roster_loading").style.opacity = "0"; 
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

		}
	});
}

/* getting team's queue */
function getQueue(leaguename) {

	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/queue',
		dataType : "json",
		contentType : 'application/json',
		cache : false,
		success : function(data) {
			drawTableQueue(data,leaguename);
			// alert(data);
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

		

		}
	});
}

/* get picks */
function getPicks(leaguename) {

	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/picks',
		dataType : "json",
		contentType : 'application/json',
		cache : false,
		success : function(data) {
			drawTablePicks(data);
			// alert(data);
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

			// alert("standings error= " + XmlHttpRequest.responseText);

		}
	});
}

/* get last pick */
function getLastPick(leaguename) {

	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/lastpick',
		dataType : "json",
		contentType : 'application/json',
		cache : false,
		success : function(data) {
			var row = $("<tr/>");
			$("#draftPicks tbody").prepend(row);
			row.prepend($("<td><span id='pick_number'> Round " + data.draftqueueID.round + " pick "
					+ data.draftqueueID.pick + ":</span><span id='pick_info'>player "
					+data.lpplayer.pplayer.firstname[0]+". "+data.lpplayer.pplayer.lastname+" was drafted by "
					+data.draftqueueID.team.name+"</span></td>"));
			
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

			// alert("standings error= " + XmlHttpRequest.responseText);

		}
	});
}

/* get age from birthdate */
function getAge(date) {
	return ((new Date().getTime() - new Date(date)) / (24 * 3600 * 365.25 * 1000)) | 0;

}
/*----------------Draw table for players---------------------------*/
function drawTable(data) {

	$("#draft_p_table tbody tr").remove();
	$("#draft_p_table_QB tbody tr").remove();
	$("#draft_p_table_RB tbody tr").remove();
	$("#draft_p_table_WR tbody tr").remove();
	$("#draft_p_table_DE tbody tr").remove();
	$("#draft_p_table_CB tbody tr").remove();
	$("#draft_p_table_DT tbody tr").remove();
	$("#draft_p_table_OT tbody tr").remove();
	$("#draft_p_table_TE tbody tr").remove();
	$("#draft_p_table_P tbody tr").remove();
	$("#draft_p_table_K tbody tr").remove();
	$("#draft_p_table_C tbody tr").remove();
	$("#draft_p_table_OG tbody tr").remove();
	$("#draft_p_table_FB tbody tr").remove();
	$("#draft_p_table_FS tbody tr").remove();
	$("#draft_p_table_SS tbody tr").remove();
	$("#draft_p_table_MLB tbody tr").remove();
	$("#draft_p_table_OLB tbody tr").remove();
	for (var i=0 in data) {
		drawRow(data[i], i);
	}
	 $("#draft_p_table").tablesorter();
	 $("#draft_p_table_QB").tablesorter();
	 $("#draft_p_table_RB").tablesorter();
	 $("#draft_p_table_WR").tablesorter();
	 $("#draft_p_table_DE").tablesorter();
	 $("#draft_p_table_CB").tablesorter();
	 $("#draft_p_table_DT").tablesorter();
	 $("#draft_p_table_OT").tablesorter();
	 $("#draft_p_table_TE").tablesorter();
	 $("#draft_p_table_P").tablesorter();
	 $("#draft_p_table_K").tablesorter();
	 $("#draft_p_table_C").tablesorter();
	 $("#draft_p_table_OG").tablesorter();
	 $("#draft_p_table_FB").tablesorter();
	 $("#draft_p_table_FS").tablesorter();
	 $("#draft_p_table_SS").tablesorter();
	 $("#draft_p_table_MLB").tablesorter();
	 $("#draft_p_table_OLB").tablesorter();
}

function drawRow(rowData, i) {
	
	
	var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
			+ JSON.stringify(rowData.pplayer.firstname) + ","
			+ JSON.stringify(rowData.pplayer.lastname) + ","
			+ JSON.stringify(rowData.pplayer.photo) + ","
			+ getAge(new Date(rowData.pplayer.birthdate)) + ","
			+ JSON.stringify(rowData.pplayer.height) + ","
			+ JSON.stringify(rowData.pplayer.weight) + ","
			+ JSON.stringify(rowData.pplayer.defaultPosition) + ","
			+ JSON.stringify(rowData.pplayer.ovr) + "," 
			+ JSON.stringify(rowData.pplayer.speed) + ","
			+ JSON.stringify(rowData.pplayer.agility) + ","
			+ JSON.stringify(rowData.pplayer.awareness) + ","
			+ JSON.stringify(rowData.pplayer.catching) + ","
			+ JSON.stringify(rowData.pplayer.carrying) + ","
			+ JSON.stringify(rowData.pplayer.tackling) + ","
			+ JSON.stringify(rowData.pplayer.breakTackle) + ","
			+ JSON.stringify(rowData.pplayer.jumping) + ","
			+ JSON.stringify(rowData.pplayer.throwPower) + ","
			+ JSON.stringify(rowData.pplayer.throwAccuracy) + ","
			+ JSON.stringify(rowData.pplayer.kickPower) + ","
			+ JSON.stringify(rowData.pplayer.kickAccuracy) + ","
			+ JSON.stringify(rowData.pplayer.strength) + ")'/>");

	$(row).click(function() {
		var selected = $(this).hasClass("highlight");
		$("#draft_p_table tbody tr").removeClass("highlight");
		$("#draft_p_table_QB tbody tr").removeClass("highlight");
		$("#draft_p_table_RB tbody tr").removeClass("highlight");
		$("#draft_p_table_WR tbody tr").removeClass("highlight");
		$("#draft_p_table_DE tbody tr").removeClass("highlight");
		$("#draft_p_table_CB tbody tr").removeClass("highlight");
		$("#draft_p_table_DT tbody tr").removeClass("highlight");
		$("#draft_p_table_OT tbody tr").removeClass("highlight");
		$("#draft_p_table_TE tbody tr").removeClass("highlight");
		$("#draft_p_table_P tbody tr").removeClass("highlight");
		$("#draft_p_table_K tbody tr").removeClass("highlight");	
		$("#draft_p_table_C tbody tr").removeClass("highlight");
		$("#draft_p_table_OG tbody tr").removeClass("highlight");
		$("#draft_p_table_FB tbody tr").removeClass("highlight");
		$("#draft_p_table_FS tbody tr").removeClass("highlight");
		$("#draft_p_table_SS tbody tr").removeClass("highlight");
		$("#draft_p_table_MLB tbody tr").removeClass("highlight");
		$("#draft_p_table_OLB tbody tr").removeClass("highlight");
		if (!selected)
			$(this).addClass("highlight");
	});

	$("#draft_p_table tbody").append(row);
	row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
	row
			.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
					+ "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.throwPower + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.throwAccuracy + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.kickPower + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.kickAccuracy + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>"));
	

	
	if (rowData.pplayer.defaultPosition == "QB"){

		var rowQB = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(rowQB).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_QB tbody").append(rowQB);
		rowQB.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		rowQB.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.throwPower + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.throwAccuracy + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		rowQB.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "RB"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_RB tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "DE"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_DE tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "WR"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_WR tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "CB"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_CB tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "OT"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_OT tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "MLB"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_MLB tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "OLB"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_OLB tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "SS"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_SS tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "FS"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_FS tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "OG"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_OG tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "DT"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_DT tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "TE"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_TE tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "C"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_C tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "FB"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_FB tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.jumping + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "K"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_K tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.kickPower + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.kickAccuracy + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.throwPower + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.throwAccuracy + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}
	
	if (rowData.pplayer.defaultPosition == "P"){

		var row = $("<tr id='"+rowData.lplayerID+"' onclick='p_info(" + rowData.lplayerID + ","
				+ JSON.stringify(rowData.pplayer.firstname) + ","
				+ JSON.stringify(rowData.pplayer.lastname) + ","
				+ JSON.stringify(rowData.pplayer.photo) + ","
				+ getAge(new Date(rowData.pplayer.birthdate)) + ","
				+ JSON.stringify(rowData.pplayer.defaultPosition) + ")'/>");

		$(row).click(function() {
			var selected = $(this).hasClass("highlight");
			$("#draft_p_table tbody tr").removeClass("highlight");
			$("#draft_p_table_QB tbody tr").removeClass("highlight");
			$("#draft_p_table_RB tbody tr").removeClass("highlight");
			$("#draft_p_table_WR tbody tr").removeClass("highlight");
			$("#draft_p_table_DE tbody tr").removeClass("highlight");
			$("#draft_p_table_CB tbody tr").removeClass("highlight");
			$("#draft_p_table_DT tbody tr").removeClass("highlight");
			$("#draft_p_table_OT tbody tr").removeClass("highlight");
			$("#draft_p_table_TE tbody tr").removeClass("highlight");
			$("#draft_p_table_P tbody tr").removeClass("highlight");
			$("#draft_p_table_K tbody tr").removeClass("highlight");	
			$("#draft_p_table_C tbody tr").removeClass("highlight");
			$("#draft_p_table_OG tbody tr").removeClass("highlight");
			$("#draft_p_table_FB tbody tr").removeClass("highlight");
			$("#draft_p_table_FS tbody tr").removeClass("highlight");
			$("#draft_p_table_SS tbody tr").removeClass("highlight");
			$("#draft_p_table_MLB tbody tr").removeClass("highlight");
			$("#draft_p_table_OLB tbody tr").removeClass("highlight");
			if (!selected)
				$(this).addClass("highlight");
		});
		$("#draft_p_table_P tbody").append(row);
		row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
		row.append($("<td width='5%'>"
				+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.defaultPosition
						+ "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.ovr + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.kickPower + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.kickAccuracy + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.throwPower + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.throwAccuracy + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.speed + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.agility + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.awareness + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.catching + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.carrying + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.tackling + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.breakTackle + "</td>"));
		row.append($("<td width='5%'>" + rowData.pplayer.strength + "</td>")); 
	}

}
/*----------------Draw table for players end---------------------------*/

/*----------------Draw table for teams queue---------------------------*/
function drawTableQueue(data,leaguename) {
	
	$("#draftTeams tr").remove();
	

	for (var i = data.length-1; i > -1 ; i--) {

		
		if (data[i].draftqueueID.team.uuser.username == principalName) {
			
				var image;
				image = "<img id='drafticon' src='data:image/png;base64,"+data[i].draftqueueID.team.teamlogo+" '/>";
				
				$("#yourNext tr").remove();
				var username = data[i].draftqueueID.team.uuser.username;
				var row1 = $("<tr><td><div id='yourNextTitle'>Your Team</div><div id='npblock'>"+image+"<div id='nexttdata'>" + data[i].draftqueueID.team.name
						+ "</div></div></td></tr>").click(function() {
							document.getElementById('rosterName').innerHTML = document.getElementById('npblock').innerHTML;
							getRosterTeam(leaguename,username);});;
				$("#yourNext").append(row1);
				
				var row2 = $("<tr><td><div id='yourNextTitle'>Next pick: Round "+data[i].draftqueueID.round+" Pick "+data[i].draftqueueID.pick+"</div><td></tr>");
				$("#yourNext").append(row2);
			

		}
	
		if (i < 25) drawRowQueue(data[i], i,leaguename);
		
	}
}
var round=0;
function drawRowQueue(rowData, num,league) {
	//console.log("<tr onclick='getRosterTeam("+league+","+rowData.draftqueueID.team.uuser.username+")' />");

	var row = $("<tr/>").click(function() {
		document.getElementById('rosterName').innerHTML = "<div id='npblock'><img id='drafticon' src='data:image/png;base64,"+rowData.draftqueueID.team.teamlogo+" '/><div id='nexttdata'>" + rowData.draftqueueID.team.name+ "</div>";
		
		
		getRosterTeam(league,rowData.draftqueueID.team.uuser.username);
		});

  
	
	//row.click(getRosterTeam(league,rowData.draftqueueID.team.uuser.username));
	
	
	var image;
	image = "<img id='drafticon' src='data:image/png;base64,"+rowData.draftqueueID.team.teamlogo+" '/>";
	
	 
	if (num == 0) {
		$("#draftTeams").prepend(row);
		if (principalName == rowData.draftqueueID.team.uuser.username) {		
			
			row.prepend($("<td><div id='onclock'><div id='drinfo'></div><div id='onclock_round'>Round " + rowData.draftqueueID.round
					+" pick "+rowData.draftqueueID.pick
					+ "</div><div id='drafttpanel_online'>"+image+"<div id='drafttdata_your'>" + rowData.draftqueueID.team.name
					+ "</div></div><div id='drtime'></div></div></td>"));
		} else {
			if (rowData.isOnline ==1) {
				
				row.prepend($("<td><div id='onclock'><div id='drinfo'></div><div id='onclock_round'>Round " + rowData.draftqueueID.round
						+" pick "+rowData.draftqueueID.pick
						+ "</div><div id='drafttpanel_online'>"+image+"<div id='drafttdata_online'>" + rowData.draftqueueID.team.name
						+ "</div></div><div id='drtime'></div></div></td>"));
				
			}else {
				row.prepend($("<td><div id='onclock'><div id='drinfo'></div><div id='onclock_round'>Round " + rowData.draftqueueID.round
						+" pick "+rowData.draftqueueID.pick
						+ "</div><div id='drafttpanel'>"+image+"<div id='drafttdata'>" + rowData.draftqueueID.team.name
						+ "</div></div><div id='drtime'></div></div></td>"));
			}
		}
	} else {
		
		
		if (round != rowData.draftqueueID.round){
			var newrow = $("<tr/>");
		$("#draftTeams").prepend(newrow);
		newrow.prepend($("<td><div id='round_header'>Round "+(rowData.draftqueueID.round+1)+"</div></td>"));
		}
		
		$("#draftTeams").prepend(row);
		
		if (principalName == rowData.draftqueueID.team.uuser.username) {
			
			row.prepend($("<td><div id='drafttpanel_online'><div id='teamround'>Round " + rowData.draftqueueID.round
					+" pick "+rowData.draftqueueID.pick
					+ "</div>"+image+"<div id='drafttdata_your'>" + rowData.draftqueueID.team.name
					+ "</div></div></td>"));
		} else {
				if (rowData.isOnline ==1) {
					
					row.prepend($("<td><div id='drafttpanel_online'><div id='teamround'>Round " + rowData.draftqueueID.round
							+" pick "+rowData.draftqueueID.pick
							+ "</div>"+image+"<div id='drafttdata_online'>" + rowData.draftqueueID.team.name
							+ "</div></div></td>"));
				} else {
	            
					row.prepend($("<td><div id='drafttpanel'><div id='teamround'>Round " + rowData.draftqueueID.round
					+" pick "+rowData.draftqueueID.pick
					+ "</div>"+image+"<div id='drafttdata'>" + rowData.draftqueueID.team.name
					+ "</div></div></td>"));
					
					
				}
		}
	}
	round = rowData.draftqueueID.round;

}
				/* var img = $("<img />");
		            img.attr('width', '200px');
		            img.attr('height', '150px');
		            img.attr("src", rowData.draftqueueID.team.enclogo).appendTo("#draftTeams");*/
				

/*----------------Draw table for players end---------------------------*/

/*----------------Draw roster for user---------------------------*/
function drawRoster(data) {
	//document.getElementById('rosterName').innerHTML = data[0].draftqueueID.team.name + "<img id='drafticon' src='data:image/png;base64,"+data[0].draftqueueID.team.teamlogo+" '/>";
	
	$("#draftRosterQB tbody tr").remove();
	$("#draftRosterWR tbody tr").remove();
	$("#draftRosterRB tbody tr").remove();
	$("#draftRosterTE tbody tr").remove();
	$("#draftRosterFB tbody tr").remove();
	$("#draftRosterC tbody tr").remove();
	$("#draftRosterOG tbody tr").remove();
	$("#draftRosterOT tbody tr").remove();
	$("#draftRosterDE tbody tr").remove();
	$("#draftRosterDT tbody tr").remove();
	$("#draftRosterMLB tbody tr").remove();
	$("#draftRosterOLB tbody tr").remove();
	$("#draftRosterCB tbody tr").remove();
	$("#draftRosterFS tbody tr").remove();
	$("#draftRosterSS tbody tr").remove();
	$("#draftRosterK tbody tr").remove();
	$("#draftRosterP tbody tr").remove();
	
	$("#draftRosterQB thead tr td").html("QB (0/2)");
	$("#draftRosterWR thead tr td").html("WR (0/4)");
	$("#draftRosterRB thead tr td").html("RB (0/2)");
	$("#draftRosterTE thead tr td").html("TE (0/2)");
	$("#draftRosterFB thead tr td").html("FB (0/1)");
	$("#draftRosterC thead tr td").html("C (0/2)");
	$("#draftRosterOG thead tr td").html("OG (0/4)");
	$("#draftRosterOT thead tr td").html("OT (0/4)");
	$("#draftRosterDE thead tr td").html("DE (0/4)");
	$("#draftRosterDT thead tr td").html("DT (0/4)");
	$("#draftRosterMLB thead tr td").html("MLB (0/2)");
	$("#draftRosterOLB thead tr td").html("OLB (0/4)");
	$("#draftRosterCB thead tr td").html("CB (0/4)");
	$("#draftRosterFS thead tr td").html("FS (0/2)");
	$("#draftRosterSS thead tr td").html("SS (0/2)");
	$("#draftRosterK thead tr td").html("K (0/1)");
	$("#draftRosterP thead tr td").html("P (0/1)");
	
	$("#draftRosterQB thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterWR thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterRB thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterTE thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterFB thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterC thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterOG thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterOT thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterDE thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterDT thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterMLB thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterOLB thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterCB thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterFS thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterSS thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterK thead tr").attr("id","rosterNonePlayers");
	$("#draftRosterP thead tr").attr("id","rosterNonePlayers");

	for (var i = 0; i < data.length; i++) {
		drawRosterRows(data[i], i + 1);
	}
}

function drawRosterRows(rowData, num) {

	var row = $("<tr id ='rosterPlayers'/>");

	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "QB") {
		$("#draftRosterQB").append(row);
		row.append($("<td>"+ rowData.rosterID.lplayer.pplayer.firstname[0]+ ". " + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterQB thead tr").remove();
		var rowlength = $('#draftRosterQB tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> QB ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> QB ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td > QB ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterQB thead").append(header);
		
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "WR") {
		$("#draftRosterWR tbody").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterWR thead tr").remove();
		var rowlength = $('#draftRosterWR tbody tr').length;
		var header;
		if (rowlength > 3 && rowlength <6) {
			header = $("<tr id='rosterDonePlayers'><td> WR ("+rowlength+"/4)</tr></td>");
		} else if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> WR ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> WR ("+rowlength+"/4)</tr></td>");
		}
		$("#draftRosterWR thead").append(header);
		
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "RB") {
		$("#draftRosterRB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterRB thead tr").remove();
		var rowlength = $('#draftRosterRB tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> RB ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> RB ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> RB ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterRB thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "TE") {
		$("#draftRosterTE").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterTE thead tr").remove();
		var rowlength = $('#draftRosterTE tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> TE ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> TE ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> TE ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterTE thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "FB") {
		$("#draftRosterFB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterFB thead tr").remove();
		var rowlength = $('#draftRosterFB tbody tr').length;
		var header;
		if (rowlength == 2) {
			header = $("<tr id='rosterFullPlayers'><td> FB ("+rowlength+"/1)</tr></td>");
		} else if (rowlength == 1) {
			header = $("<tr id='rosterDonePlayers'><td> FB ("+rowlength+"/1)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> FB ("+rowlength+"/1)</tr></td>");
		} 
		$("#draftRosterFB thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "C") {
		$("#draftRosterC").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterC thead tr").remove();
		var rowlength = $('#draftRosterC tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> C ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> C ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> C ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterC thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "OT") {
	$("#draftRosterOT").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterOT thead tr").remove();
		var rowlength = $('#draftRosterOT tbody tr').length;
		var header;
		if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> OT ("+rowlength+"/4)</tr></td>");
		} else if (rowlength > 3 && rowlength < 6) {
			header = $("<tr id='rosterDonePlayers'><td> OT ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> OT ("+rowlength+"/4)</tr></td>");
		}
		$("#draftRosterOT thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "OG") {
		$("#draftRosterOG").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterOG thead tr").remove();
		var rowlength = $('#draftRosterOG tbody tr').length;
		var header;
		if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> OG ("+rowlength+"/4)</tr></td>");
		} else if (rowlength > 3 && rowlength < 6) {
			header = $("<tr id='rosterDonePlayers'><td> OG ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> OG ("+rowlength+"/4)</tr></td>");
		}
		$("#draftRosterOG thead").append(header);
	}

	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "MLB") {
		$("#draftRosterMLB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterMLB thead tr").remove();
		var rowlength = $('#draftRosterMLB tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> MLB ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> MLB ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> MLB ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterMLB thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "OLB") {
		$("#draftRosterOLB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterOLB thead tr").remove();
		var rowlength = $('#draftRosterOLB tbody tr').length;
		var header;
		if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> OLB ("+rowlength+"/4)</tr></td>");
		} else if (rowlength > 3 && rowlength < 6) {
			header = $("<tr id='rosterDonePlayers'><td> OLB ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> OLB ("+rowlength+"/4)</tr></td>");
		}
		$("#draftRosterOLB thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "CB") {
		$("#draftRosterCB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterCB thead tr").remove();
		var rowlength = $('#draftRosterCB tbody tr').length;
		var header;
		if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> CB ("+rowlength+"/4)</tr></td>");
		} else if (rowlength > 3 && rowlength < 6) {
			header = $("<tr id='rosterDonePlayers'><td> CB ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> CB ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterCB thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "FS") {
		$("#draftRosterFS").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterFS thead tr").remove();
		var rowlength = $('#draftRosterFS tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> FS ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> FS ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> FS ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterFS thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "SS") {
		
		$("#draftRosterSS").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterSS thead tr").remove();
		var rowlength = $('#draftRosterSS tbody tr').length;
		var header;
		if (rowlength == 3) {
			header = $("<tr id='rosterFullPlayers'><td> SS ("+rowlength+"/2)</tr></td>");
		} else if (rowlength == 2) {
			header = $("<tr id='rosterDonePlayers'><td> SS ("+rowlength+"/2)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> SS ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterSS thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "DE") {
		$("#draftRosterDE").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterDE thead tr").remove();
		var rowlength = $('#draftRosterDE tbody tr').length;
		var header;
		if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> DE ("+rowlength+"/4)</tr></td>");
		} else if (rowlength > 3 && rowlength < 6) {
			header = $("<tr id='rosterDonePlayers'><td> DE ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> DE ("+rowlength+"/4)</tr></td>");
		}
		$("#draftRosterDE thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "DT") {
		$("#draftRosterDT").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterDT thead tr").remove();
		var rowlength = $('#draftRosterDT tbody tr').length;
		var header;
		if (rowlength == 6) {
			header = $("<tr id='rosterFullPlayers'><td> DT ("+rowlength+"/4)</tr></td>");
		} else if (rowlength > 3 && rowlength < 6) {
			header = $("<tr id='rosterDonePlayers'><td> DT ("+rowlength+"/4)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> DT ("+rowlength+"/4)</tr></td>");
		}
		$("#draftRosterDT thead").append(header);
	}

	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "K") {
		$("#draftRosterK").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterK thead tr").remove();
		var rowlength = $('#draftRosterK tbody tr').length;
		var header;
		if (rowlength == 2) {
			header = $("<tr id='rosterFullPlayers'><td> K ("+rowlength+"/1)</tr></td>");
		} else if (rowlength == 1) {
			header = $("<tr id='rosterDonePlayers'><td> K ("+rowlength+"/1)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> K ("+rowlength+"/1)</tr></td>");
		}
		$("#draftRosterK thead").append(header);
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "P") {
		$("#draftRosterP").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
		
		$("#draftRosterP thead tr").remove();
		var rowlength = $('#draftRosterP tbody tr').length;
		var header;
		if (rowlength == 2) {
			header = $("<tr id='rosterFullPlayers'><td> P ("+rowlength+"/1)</tr></td>");
		} else if (rowlength == 1) {
			header = $("<tr id='rosterDonePlayers'><td> P ("+rowlength+"/1)</tr></td>");
		} else {
			header = $("<tr id='rosterNonePlayers'><td> P ("+rowlength+"/2)</tr></td>");
		}
		$("#draftRosterP thead").append(header);
	}

}
/*----------------Draw roster for user end---------------------------*/

/*----------------Draw picks---------------------------*/
function drawTablePicks(data) {
	$("#draftPicks tbody tr").remove();
	for (var i = 0; i < data.length; i++) {
		drawRowPicks(data[i], i + 1);
	}
}
function drawRowPicks(rowData, num) {
	var row = $("<tr/>");
	$("#draftPicks tbody").prepend(row);

	row.prepend($("<td><span id='pick_number'> Round " + rowData.draftqueueID.round + " pick "
			+ rowData.draftqueueID.pick + ":</span><span id='pick_info'>player "
			+rowData.lpplayer.pplayer.firstname[0]+". "+rowData.lpplayer.pplayer.lastname+" was drafted by "
			+rowData.draftqueueID.team.name+"</span></td>"));
}
/*----------------Draw picks end---------------------------*/

function dropPlayer(playerID, leaguename) {

	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/dropplayer',
		dataType : "json",
		data : {
			'playerID' : playerID
		},
		response : 'text',
		success : function(data) {
			if (data == 1) {
				getPlayers(leaguename);
			} else {
				
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert("error= " + errorThrown);

		}
	});
}

function dropTeam(leaguename) {

	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/dropteam',
		response : 'text',
		success : function(data) {
			if (data == 1) {
				getQueue(leaguename);
			} else {
				// alert("player already drafted");
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert("error= " + errorThrown);

		}
	});
}

function checkUser(leaguename, username) {
	$.ajax({
		type : 'get',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/onlinecheck',
		dataType : "text",
		data : {
			'username' : username
		},
		contentType : "application/json",
		cache : false,
		success : function(data) {

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert(XmlHttpRequest.responseText);
			// alert("error= " + errorThrown);

		}
	});
}

/* set online */
function enterDraft(leaguename) {

	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/enter',
		cache : false,
		success : function(data) {

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

			// alert("=error= " + XmlHttpRequest.responseText);

		}
	});
}
/* set offline */
function leaveDraft(leaguename) {

	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/leave',
		cache : false,
		success : function(data) {

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

			// alert("=error= " + XmlHttpRequest.responseText);

		}
	});
}

/* getting principal's name and then load all tables */
function loadTables(leaguename) {
	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/username',
		cache : false,
		response : 'text',
		success : function(data) {
			principalName = data;

			enterDraft(leaguename);
			getPlayers(leaguename);
			getQueue(leaguename);
			getPicks(leaguename);
			getRosterTeam(leaguename,principalName);
			

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			return "";
		}
	});
}

function startDraft(leaguename) {
	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/startdraft',
		cache : false,
		success : function(data) {
          console.log(data);
		}
	});
}

function openDraft(leaguename) {

	var params = [ 'height=' + screen.height, 'width=' + screen.width,
			'fullscreen=yes', // only works in IE, but here for completeness
			'Toolbar=0', 'location=no', 'Directories=0', 'Status=0',
			'Menubar=0' ].join(',');
	// and any other options from

	var popup = window.open('http://localhost:8080/FootballManager/draft/'
			+ leaguename, 'popup_window', params);
	popup.moveTo(0, 0);

}

/* -------------Cheking if tables was updated by other players------------- */
var size;

function checkUpdateState(leaguename) {

	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/checkupdate',
		dataType : "text",
		cache : false,
		success : function(data) {
			if (size == null){
				size = data;
			}
			if (data != size) {
			
				getQueue(leaguename);
				removePlayer(leaguename);
				getLastPick(leaguename);
				size = data;
			}
		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

			// alert("=error= " + XmlHttpRequest.responseText);

		}
	});
}


function updateTimer(leaguename) {

	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/timer',
		dataType : "text",
		cache : false,
		success : function(data) {
			if (data == -1) {
				document.getElementById('drtime').innerHTML = "autopick";
			} else if (data>99) {
				var sec = data-100;
				
				var hours = parseInt( sec / 3600 ) % 24;
				var minutes = parseInt( sec / 60 ) % 60;
				var seconds = sec % 60;
				
				if (hours < 10) hours = "0"+hours;
				if (minutes < 10) minutes = "0"+minutes;
				if (seconds < 10) seconds = "0"+seconds;
				
				document.getElementById('drtime').innerHTML = ""+hours +":"+minutes+":"+seconds+"";
				document.getElementById('drinfo').innerHTML = "Draft starting in";
			} else {
				
				var hours = parseInt( data / 3600 ) % 24;
				var minutes = parseInt( data / 60 ) % 60;
				var seconds = data % 60;
				
				if (hours < 10) hours = "0"+hours;
				if (minutes < 10) minutes = "0"+minutes;
				if (seconds < 10) seconds = "0"+seconds;
				document.getElementById('drinfo').innerHTML = "On the clock";
				document.getElementById('drtime').innerHTML = ""+hours +":"+minutes+":"+seconds+"";
			}
		}
	});
}

function removePlayer(leaguename){
	$.ajax({
		type : 'post',
		url : 'http://localhost:8080/FootballManager/draft/' + leaguename
				+ '/lastplayer',
		dataType : "json",
		cache : false,
		success : function(data) {

				deletePlayer(data);

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {

			// alert("=error= " + XmlHttpRequest.responseText);

		}
	});
}
function deletePlayer(id){
$("tr#"+id+"").remove();
}



