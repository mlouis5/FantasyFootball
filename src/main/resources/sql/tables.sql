

create table if not exists fantasy.owner (
	owner_id varchar(128) primary key,
	owner_name varchar(128) not null,
	is_commish boolean default false,
	has_paid boolean default false
) without oids;
alter table if exists fantasy.owner owner to postgres;

create table if not exists fantasy.team (
	team_id serial primary key,
	team_name varchar(128) not null,
	team_owner varchar(128) not null unique references fantasy.owner(owner_id),
	is_complete boolean default false
) without oids;
alter table if exists fantasy.team owner to postgres;

create table if not exists fantasy.roster (
	roster_id serial primary key,
	roster_position varchar(7) not null,
	p_name varchar(128) not null,
	p_pro_team varchar(3) default "AGT",
	team_id integer not null references fantasy.team(team_id),
	p_number integer default null
) without oids;
alter table if exists fantasy.roster owner to postgres;

create table if not exists fantasy.league (
	league_id serial primary key,
	league_name varchar(128) not null,
	league_creator varchar(128) not null references fantasy.owner(owner_id)	
) without oids;
alter table if exists fantasy.league owner to postgres;

create table if not exists fantasy.preference (
	preference_id serial primary key,
	draft_starttime timestamp without time zone not null,
	draft_type varchar(7) check(draft_type = "SNAKE" or draft_type = "AUCTION"),
	time_per_pick integer not null check(time_per_pick >= 30 and time_per_pick <= 120),
	league_fee integer not null,
	league_id integer not null unique references fantasy.league(league_id),
	max_owner integer not null check(max_owner >= 8 and max_owner <= 14),
	max_roster_size integer not null,
	max_qb integer default -1,
	max_rb  integer default -1,
	max_wr integer default -1,
	max_te integer default -1,
	max_k integer default -1,
	max_defense integer default -1,
	starting_qb integer default 1,
	starting_rb integer default 1,
	starting_wr integer default 1,
	starting_te integer default 1,
	starting_k integer default 1,
	starting_defense integer default 1,
	starting_rb_wr integer default 0,
	starting_wr_te integer default 0,
) without oids;
alter table if exists fantasy.preference owner to postgres;

create or replace function preference_validation() returns trigger AS $preference_validation$
	begin
		--check that all starting values add up to max_roster_size
		if NEW.starting_qb + NEW.starting_rb + NEW.starting_wr 
			+ NEW.starting_te + NEW.starting_k + NEW.starting_defense 
			+ NEW.starting_rb_wr + NEW.starting_wr_te = NEW.max_roster_size
		then
			return NEW;
		else
			raise exception 'starting positions must add up to max_roster_size';
	end;
$preference_validation$ language plpgsql;

create trigger preference_validation before insert or update on fantasy.preference for each row execute procedure preference_validation();
		
drop table if exists fantasy.player;
CREATE TABLE fantasy.player (
	player_id VARCHAR(20) NOT NULL, 
	active VARCHAR(1), 
	college VARCHAR(45), 
	display_name VARCHAR(144) NOT NULL, 
	dob VARCHAR(10), 
	fname VARCHAR(77) NOT NULL, 
	height VARCHAR(5), 
	jersey VARCHAR(3), 
	lname VARCHAR(77) NOT NULL, 
	position VARCHAR(3) NOT NULL, 
	retrieval_year INTEGER NOT NULL, 
	team VARCHAR(3) NOT NULL, 
	weight VARCHAR(3), 
	PRIMARY KEY (player_id)
);
alter table if exists fantasy.player owner to postgres;

create table if not exists fantasy.qb_stats (
	
) without oids;
alter table if exists fantasy.qb_stats owner to postgres;

create table if not exists fantasy.team () without oids;
alter table if exists fantasy.owner owner to postgres;

create table if not exists fantasy.team () without oids;
alter table if exists fantasy.owner owner to postgres;

create table if not exists fantasy.team () without oids;
alter table if exists fantasy.owner owner to postgres;

create table if not exists fantasy.team () without oids;
alter table if exists fantasy.owner owner to postgres;

create table if not exists fantasy.team () without oids;
alter table if exists fantasy.owner owner to postgres;

