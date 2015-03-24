-- create database script
 
drop database if exists FootballManager;
 
-- create database  FootballManager
create database FootballManager; 
 
use FootballManager;                  
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
strength int
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
constraint fk_teamID3 foreign key (teamID) references teams(teamID),
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
remaining_time int default 3600, 
game_time time not null,
field_position int,
constraint fk_weekID foreign key (weekID) references weeks(weekID) on delete cascade,
constraint fk_team1ID foreign key (team1ID) references teams(teamID) on delete cascade,
constraint fk_team2ID foreign key (team2ID) references teams(teamID) on delete cascade,
unique(team1ID,weekID)
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
 




  DELIMITER $$
CREATE TRIGGER create_games before INSERT ON footballmanager.games
    FOR EACH ROW 
begin
DECLARE cnt INT;
 
    IF (NEW.remaining_time = 0) THEN
        SET NEW.remaining_time = 3600;
  END IF;
    SELECT count(*) into cnt from games where (games.team1ID = new.team1ID and games.weekID = new.weekID) or (games.team2ID = new.team1ID and games.weekID = new.weekID) or (games.team1ID = new.team2ID and games.weekID = new.weekID) ;
       if (cnt) > 0 then
  SIGNAL sqlstate '45001' set message_text = "game exists";
  END IF;
end$$
 

 
 
commit;