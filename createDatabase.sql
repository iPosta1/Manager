-- create database script
 
drop database if exists footballmanager;
 
-- create database  FootballManager
create database footballmanager; 
 
use footballmanager;                  
create table users (
userID int not null primary key AUTO_INCREMENT,
username varchar(20) not null unique,
password varchar (20) not null,
email varchar(30) not null unique,
user_group varchar (30) not null
);
 
 
create table players (
playerID int not null primary key AUTO_INCREMENT,
firstname varchar(30) not null,
lastname varchar(30) not null,
birthdate date not null,
height real not null,
weight real not null,
photo varchar (100),
default_position varchar(10) not null,
ovr int,
speed int ,
agility int,
awareness int,
catching int,
carrying int,
tackling int,
break_tackle int,
jumpimg int,
throw_power int,
throw_accuracy int,
kicking_power int,
kicking_accuracy int,
strength int,
stamina int
);
 
create table leagues (
leagueID int not null primary key AUTO_INCREMENT,
league_name varchar(30) not null unique,
primetime time not null,
maxplayers int not null,
start_date date not null,
is_started varchar (1)
);
 
create table teams (
teamID int not null primary key AUTO_INCREMENT,
leagueID int not null,
userID int not null,
name varchar(30) not null,
logo mediumblob,
constraint fk_leagueID foreign key (leagueID) references leagues(leagueID) on delete cascade,
constraint fk_userID foreign key (userID) references users(userID),
UNIQUE KEY (userID,leagueID),
UNIQUE KEY (name,leagueID)
);
 
 create table lplayers (
lplayerID int not null primary key AUTO_INCREMENT,
stamina int,
leagueID int not null,
teamID int null,
playerID int not null,
constraint fk_leagueID3 foreign key (leagueID) references leagues(leagueID) on delete cascade,
constraint fk_teamID3 foreign key (teamID) references teams(teamID) on delete cascade,
constraint fk_playerID3 foreign key (playerID) references players(playerID) on delete cascade,
UNIQUE KEY (playerID,leagueID)
);
create table roster (
teamID int not null,
lplayerID int not null,
position varchar(10) ,
position_number int ,
constraint fk_teamID foreign key (teamID) references teams(teamID) on delete cascade,
constraint fk_lplayerID foreign key (lplayerID) references lplayers(lplayerID) on delete cascade,
constraint pk primary key (teamID,lplayerID),
UNIQUE KEY (lplayerID,teamID)
);
 
     
 
 
create table weeks (
weekID int not null primary key AUTO_INCREMENT,
leagueID int not null,
week_name varchar(20) not null,
game_date DATETIME not null,
constraint fkleagueID foreign key (leagueID) references leagues(leagueID) on delete cascade
);
     
create table games (
gameID int not null primary key AUTO_INCREMENT,
team1ID int not null,
team2ID int not null,
weekID int not null,
team1_points int,
team2_points int,
attack_team int CHECK (attack_team = team1ID OR arrack_team = team2ID),
down int,
yards_left int,
remaining_time int default 3600, 
game_time time not null,
field_position int,
constraint fk_weekID foreign key (weekID) references weeks(weekID) on delete cascade,
constraint fk_team1ID foreign key (team1ID) references teams(teamID) on delete cascade,
constraint fk_team2ID foreign key (team2ID) references teams(teamID) on delete cascade,
unique(team1ID,weekID)
);  

create table events (
event_id int not null primary key AUTO_INCREMENT,
event varchar(100),
game_id not null,
constraint fk_gameIDe foreign key (game_id) references games(game_id) on delete cascade
);
 
create table standings (
team_number int,
draft_number int,
leagueID int not null,
teamID int not null,
wins int,
loses int,
ties int,
constraint fk_sleagueID foreign key (leagueID) references leagues(leagueID) on delete cascade,
constraint fk_steamID foreign key (teamID) references teams(teamID) on delete cascade
);
 
 
         
 create table draftqueue(
teamID int not null,
round int not null,
pick int not null,
is_online int,
constraint fk_teamIDd foreign key (teamID) references standings(teamID) on delete cascade
);

  create table footballmanager.draftpicks(
teamID int not null,
round int not null,
pick int not null,
lplayerID int,
constraint fk_teamIDp foreign key (teamID) references standings(teamID) on delete cascade,
constraint fk_lplayerIDf foreign key (lplayerID) references lplayers(lplayerID) on delete cascade
);

 create table formations(
formation_id int primary key AUTO_INCREMENT,
form_type varchar(1) not null,
form_name varchar(20) not null,
form_img varchar(20)
);

create table form_positions(
form_positions_id int primary key AUTO_INCREMENT,
formation_id int not null,
pos_name varchar(5),
number_cell int not null,
constraint fk_form_id foreign key (formation_id) references formations(formation_id) on delete cascade
);

 create table combinations(
comb_id int primary key AUTO_INCREMENT,
formation_id int not null,
comb_name varchar(20) not null,
comb_img varchar(20),
constraint fk_formation_id0 foreign key (formation_id) references formations(formation_id) on delete cascade
);

create table routes(
route_id int primary key AUTO_INCREMENT,
comb_id int not null,
form_positions_id int not null,
route_type varchar(10) not null,
min_yards int,
route_complexity int,
route_img varchar(50),
constraint fk_comb_id foreign key (comb_id) references combinations(comb_id) on delete cascade,
constraint fk_form_positions_id foreign key (form_positions_id) references form_positions(form_positions_id) on delete cascade
);

create table route_cells(
cell_id int primary key AUTO_INCREMENT,
route_id int not null,
cell int not null,
constraint route_id foreign key (route_id) references routes(route_id) on delete cascade
);

create table players_position(
players_position_id int primary key AUTO_INCREMENT,
form_positions_id int not null,
lplayerID int,
constraint form_positions_id2 foreign key (form_positions_id) references form_positions(form_positions_id) on delete cascade,
constraint lplayerID11 foreign key (lplayerID) references lplayers(lplayerID) on delete cascade,
unique(form_positions_id,lplayerID)
);
 
  insert into users (userID,username,password,email,user_group) values (1,'primetimerivalry1','27092709','nkvtbsk1@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (2,'primetimerivalry2','27092709','nkvtbsk2@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (3,'primetimerivalry3','27092709','nkvtbsk3@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (4,'primetimerivalry4','27092709','nkvtbsk4@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (5,'primetimerivalry5','27092709','nkvtbsk5@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (6,'primetimerivalry6','27092709','nkvtbsk6@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (7,'primetimerivalry7','27092709','nkvtbsk7@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (8,'primetimerivalry8','27092709','nkvtbsk8@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (9,'primetimerivalry9','27092709','nkvtbsk9@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (10,'primetimerivalry10','27092709','nkvtbsk10@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (11,'primetimerivalry11','27092709','nkvtbsk11@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (12,'primetimerivalry12','27092709','nkvtbsk12@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (13,'primetimerivalry13','27092709','nkvtbsk13@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (14,'primetimerivalry14','27092709','nkvtbsk14@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (15,'primetimerivalry15','27092709','nkvtbsk15@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (16,'primetimerivalry16','27092709','nkvtbsk16@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (17,'primetimerivalry17','27092709','nkvtbsk17@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (18,'primetimerivalry18','27092709','nkvtbsk18@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (19,'primetimerivalry19','27092709','nkvtbsk19@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (20,'primetimerivalry20','27092709','nkvtbsk20@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (21,'primetimerivalry21','27092709','nkvtbsk21@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (22,'primetimerivalry22','27092709','nkvtbsk22@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (23,'primetimerivalry23','27092709','nkvtbsk23@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (24,'primetimerivalry24','27092709','nkvtbsk24@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (25,'primetimerivalry25','27092709','nkvtbsk25@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (26,'primetimerivalry26','27092709','nkvtbsk26@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (27,'primetimerivalry27','27092709','nkvtbsk27@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (28,'primetimerivalry28','27092709','nkvtbsk28@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (29,'primetimerivalry29','27092709','nkvtbsk29@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (30,'primetimerivalry30','27092709','nkvtbsk30@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (31,'primetimerivalry31','27092709','nkvtbsk31@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (32,'primetimerivalry32','27092709','nkvtbsk32@gmail.com','bot');
  insert into users (userID,username,password,email,user_group) values (33,'postal','1','nkvtbsk@gmail.com','ROLE_USER');
  insert into users (userID,username,password,email,user_group) values (34,'vasya','1','nkvtwerewrbsk@gmail.com','ROLE_USER');
  insert into users (userID,username,password,email,user_group) values (35,'petya','1','nkwerbsk@gmail.com','ROLE_USER');
  insert into users (userID,username,password,email,user_group) values (36,'valera','1','werwefk@gmail.com','ROLE_USER');
 
 
 
commit;
