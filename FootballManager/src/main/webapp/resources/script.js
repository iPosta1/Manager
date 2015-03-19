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
function p_info(id, firstname, lastname, photo, birthdate, position, row) {

	document.getElementById('p_id').innerHTML = id;
	document.getElementById('p_firstname').innerHTML = firstname;
	document.getElementById('p_lastname').innerHTML = lastname;
	document.getElementById('p_photo').innerHTML = photo;
	document.getElementById('p_birthdate').innerHTML = birthdate;
	document.getElementById('p_position').innerHTML = position;

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
			

		},
		error : function(XmlHttpRequest, textStatus, errorThrown) {
			// alert(XmlHttpRequest.responseText);
			// alert("error= " + errorThrown);

		}
	});
}

var principalName; // principal's name


function getRosterTeam(leaguename,user) {
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
			row.prepend($("<td> Round " + data.draftqueueID.round + " pick "
					+ data.draftqueueID.pick + ": " + data.draftqueueID.team.name
					+ " picks " + data.lpplayer.pplayer.lastname + "</td>"));
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
}

function drawRow(rowData, i) {
	
	
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

	$("#draft_p_table tbody").append(row);
	row.append($("<td width='15%'>" + rowData.pplayer.firstname +" "+ rowData.pplayer.lastname + "</td>"));
	row.append($("<td width='5%'>"
			+ getAge(new Date(rowData.pplayer.birthdate)) + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.height + "</td>"));
	row.append($("<td width='5%'>" + rowData.pplayer.weight + "</td>"));
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
	
		if (i < 20) drawRowQueue(data[i], i,leaguename);
		
	}
}

function drawRowQueue(rowData, num,league) {
	//console.log("<tr onclick='getRosterTeam("+league+","+rowData.draftqueueID.team.uuser.username+")' />");

	var row = $("<tr/>").click(function() {
		document.getElementById('rosterName').innerHTML = "<div id='npblock'><img id='drafticon' src='data:image/png;base64,"+rowData.draftqueueID.team.teamlogo+" '/><div id='nexttdata'>" + rowData.draftqueueID.team.name+ "</div>";
		
		
		getRosterTeam(league,rowData.draftqueueID.team.uuser.username);
		});

  
	$("#draftTeams").prepend(row);
	//row.click(getRosterTeam(league,rowData.draftqueueID.team.uuser.username));
	
	
	var image;
	image = "<img id='drafticon' src='data:image/png;base64,"+rowData.draftqueueID.team.teamlogo+" '/>";
	
	 
	if (num == 0) {
		if (principalName == rowData.draftqueueID.team.uuser.username) {		
			row.prepend($("<td><div id='onclock'>Round " + rowData.draftqueueID.round
					+" pick "+rowData.draftqueueID.pick
					+ "<div id='drafttpanel_your'>"+image+"<div id='drafttdata_your'>" + rowData.draftqueueID.team.name
					+ "</div></div><div id='drtime'></div></div></td>"));
		} else {
			if (rowData.isOnline ==1) {
				row.prepend($("<td><div id='onclock'>Round " + rowData.draftqueueID.round
						+" pick "+rowData.draftqueueID.pick
						+ "<div id='drafttpanel_online'>"+image+"<div id='drafttdata_online'>" + rowData.draftqueueID.team.name
						+ "</div></div><div id='drtime'></div></div></td>"));
			}else {
				row.prepend($("<td><div id='onclock'>Round " + rowData.draftqueueID.round
						+" pick "+rowData.draftqueueID.pick
						+ "<div id='drafttpanel_online'>"+image+"<div id='drafttdata'>" + rowData.draftqueueID.team.name
						+ "</div></div><div id='drtime'></div></div></td>"));
			}
		}
	} else {

		if (principalName == rowData.draftqueueID.team.uuser.username) {
			row.prepend($("<td><div id='drpick'>Round " + rowData.draftqueueID.round
					+" pick "+rowData.draftqueueID.pick
					+ "</div><div id='drafttpanel_your'>"+image+"<div id='drafttdata_your'>" + rowData.draftqueueID.team.name
					+ "</div></div></td>"));
		} else {
				if (rowData.isOnline ==1) {
					row.prepend($("<td><div id='drpick'>Round " + rowData.draftqueueID.round
							+" pick "+rowData.draftqueueID.pick
							+ "</div><div id='drafttpanel_online'>"+image+"<div id='drafttdata_online'>" + rowData.draftqueueID.team.name
							+ "</div></div></td>"));
				} else {
	            
					row.prepend($("<td><div id='drpick'>Round " + rowData.draftqueueID.round
					+" pick "+rowData.draftqueueID.pick
					+ "</div><div id='drafttpanel'>"+image+"<div id='drafttdata'>" + rowData.draftqueueID.team.name
					+ "</div></div></td>"));
					
					
				}
		}
	}
	

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

	for (var i = 0; i < data.length; i++) {
		drawRosterRows(data[i], i + 1);
	}
}

function drawRosterRows(rowData, num) {

	var row = $("<tr id ='rosterPlayers'/>");

	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "QB") {
		$("#draftRosterQB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "WR") {
		$("#draftRosterWR").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "RB") {
		$("#draftRosterRB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "TE") {
		$("#draftRosterTE").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "FB") {
		$("#draftRosterFB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "C") {
		$("#draftRosterC").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "OT") {
		$("#draftRosterOT").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "OG") {
		$("#draftRosterOG").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}

	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "MLB") {
		$("#draftRosterMLB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "OLB") {
		$("#draftRosterOLB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "CB") {
		$("#draftRosterCB").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "FS") {
		$("#draftRosterFS").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "SS") {
		$("#draftRosterSS").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "DE") {
		$("#draftRosterDE").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "DT") {
		$("#draftRosterDT").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}

	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "K") {
		$("#draftRosterK").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
	}
	if (rowData.rosterID.lplayer.pplayer.defaultPosition == "P") {
		$("#draftRosterP").append(row);
		row.append($("<td>" + rowData.rosterID.lplayer.pplayer.lastname
				+ "</td>"));
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
	row.prepend($("<td> Round " + rowData.draftqueueID.round + " pick "
			+ rowData.draftqueueID.pick + ": " + rowData.draftqueueID.team.name
			+ " picks " + rowData.lpplayer.pplayer.lastname + "</td>"));
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
				document.getElementById('drtime').innerHTML = "Draft starting in "+sec +" seconds";
			} else if (data<10) {
				document.getElementById('drtime').innerHTML = "0:0"+data;
			} else {
				document.getElementById('drtime').innerHTML = "0:"+data;
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





