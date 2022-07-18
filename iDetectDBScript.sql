create database idetect;
USE idetect;
CREATE table website(URL varchar(1000),websiteID int(7) primary key auto_increment,active varchar(10),isPhished varchar(20),website_type varchar(15),original_website varchar(1000),list_name varchar(50),time_date_foreign int(10),type varchar(15),is_hacked varchar(20) default 'Unknown',title_hash_value int(100),detection_technique varchar(250) , autorun varchar(10));
create table history(URL varchar(1000),active varchar(10),isPhished varchar(20),websiteID_foreign int(7),historyID int(7) primary key auto_increment,time_date_foreign int(10));
create table time_date(time_dateID int (10) primary key auto_increment ,minute int(2),hour int(2) , day int(2),month int(2),year int (4),purpose varchar(15));
create table phishtankdb(xml_download_time_hour int (4),xml_download_time_day int (4),xml_download_time_month int(4) ,xml_download_time_year int (6));
create table customizeenumeration(firstReplace varchar(3),secondReplace varchar(3));
create table hacked_keywords (keyword varchar(30));
create table hacker_nicknames(nickname varchar(70));
create table iframe (websiteID int(20),iframe longtext );
create table javascript (websiteID int (20),script longtext);
create table meta (websiteID int (20),meta longtext);
 







