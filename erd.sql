SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS Contribution_details;
DROP TABLE IF EXISTS Favorite;
DROP TABLE IF EXISTS Prize;
DROP TABLE IF EXISTS Report;
DROP TABLE IF EXISTS Tag;
DROP TABLE IF EXISTS Contribution;
DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS Title;
DROP TABLE IF EXISTS Competition;
DROP TABLE IF EXISTS Point_buy;
DROP TABLE IF EXISTS Possession;
DROP TABLE IF EXISTS Member;


/* Create Tables */

CREATE TABLE Admin
(
	admin_id varchar(20) NOT NULL,
	name varchar(50),
	login_timestamp timestamp NOT NULL,
	password char(32) NOT NULL,
	-- 削除はしない、
	-- フラグを立てたらログインできないようにする
	-- 
	delete_flag boolean COMMENT '削除はしない、
フラグを立てたらログインできないようにする
',
	PRIMARY KEY (admin_id),
	UNIQUE (admin_id)
);
CREATE TABLE Competition
(
	competition_id int NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL,
	-- 競技で必要なポイントを掲示する
	-- キャンペーン等で変動する場合があるため
	use_point int NOT NULL COMMENT '競技で必要なポイントを掲示する
キャンペーン等で変動する場合があるため
',
	explanation text NOT NULL,
	PRIMARY KEY (competition_id)
);
CREATE TABLE Contribution
(
	contribution_id int NOT NULL AUTO_INCREMENT,
	member_no int NOT NULL,
	contribution_timestamp timestamp NOT NULL,
	score double,
	-- 実際に使用したポイントの情報
	-- 
	point int NOT NULL COMMENT '実際に使用したポイントの情報
',
	-- ０だったら管理者が見てない
	-- １だったら管理者が見てる
	-- ２だったら通報してる
	exhibition_status int(1) COMMENT '０だったら管理者が見てない
１だったら管理者が見てる
２だったら通報してる
',
	status_update_admin_id varchar(20) NOT NULL,
	PRIMARY KEY (contribution_id)
);
CREATE TABLE Contribution_details
(
	contribution_id int NOT NULL,
	title_id int NOT NULL,
	img_pass varchar(255) NOT NULL,
	img_title varchar(32) NOT NULL,
	count int DEFAULT 0 NOT NULL,
	PRIMARY KEY (contribution_id, title_id)
);
CREATE TABLE Favorite
(
	contribution_id int NOT NULL,
	member_no int NOT NULL,
	PRIMARY KEY (contribution_id, member_no),
	UNIQUE (contribution_id, member_no)
);
CREATE TABLE Member
(
	member_no int NOT NULL AUTO_INCREMENT,
	-- ログインする時に使う任意のID
	id varchar(20) NOT NULL COMMENT 'ログインする時に使う任意のID
',
	name varchar(20) NOT NULL,
	password varchar(256) NOT NULL,
	mail_adress varchar(255) NOT NULL,
	birthday date NOT NULL,
	-- Uomen
	-- Men
	-- でUかMで判断する
	-- 
	sex char(1) NOT NULL COMMENT 'Uomen
Men
でUかMで判断する
',
	-- 削除はしない、
	-- フラグを立てたらログインできないようにする
	-- 
	delete_flag boolean COMMENT '削除はしない、
フラグを立てたらログインできないようにする
',
	PRIMARY KEY (member_no),
	UNIQUE (id)
);
CREATE TABLE Message
(
	message_id int NOT NULL AUTO_INCREMENT,
	member_no int NOT NULL,
	-- お祝いか警告か
	title varchar(255) COMMENT 'お祝いか警告か
',
	contents text NOT NULL,
	send_timestamp timestamp NOT NULL,
	admin_id varchar(20),
	PRIMARY KEY (message_id)
);

CREATE TABLE Point_buy
(
	point_buy_id int NOT NULL AUTO_INCREMENT,
	member_no int NOT NULL,
	buy_point int NOT NULL,
	buy_timestamp timestamp NOT NULL,
	-- 電子マネー番号
	card_no char(32) COMMENT 
	'
電子マネー番号
',
	PRIMARY KEY (point_buy_id)
);

CREATE TABLE Possession
(
	member_no int NOT NULL,
	-- 残高みたいなもの
	-- 
	point int NOT NULL COMMENT '残高みたいなもの
',
	PRIMARY KEY (member_no)
);
CREATE TABLE Prize
(
	contribution_id int NOT NULL,
	name varchar(100),
	point int,
	timestamp timestamp NOT NULL,
	PRIMARY KEY (contribution_id)
);
CREATE TABLE Report
(
	report_no int NOT NULL AUTO_INCREMENT,
	contribution_id int,
	member_no int NOT NULL,
	report_timestamp timestamp,
	comment text,
	-- 管理者がどう対応したのかのステータス
	contents text COMMENT '管理者がどう対応したのかのステータス
',
	PRIMARY KEY (report_no)
);

CREATE TABLE Tag
(
	tag_no int NOT NULL AUTO_INCREMENT,
	contribution_id int NOT NULL,
	name varchar(50) NOT NULL,
	PRIMARY KEY (tag_no, contribution_id)
);

CREATE TABLE Title
(
	title_id int NOT NULL AUTO_INCREMENT,
	competition_id int NOT NULL,
	name varchar(255),
	PRIMARY KEY (title_id)
);
/* Create Foreign Keys */

ALTER TABLE Contribution
	ADD FOREIGN KEY (status_update_admin_id)
	REFERENCES Admin (admin_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Message
	ADD FOREIGN KEY (admin_id)
	REFERENCES Admin (admin_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Title
	ADD FOREIGN KEY (competition_id)
	REFERENCES Competition (competition_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Contribution_details
	ADD FOREIGN KEY (contribution_id)
	REFERENCES Contribution (contribution_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Favorite
	ADD FOREIGN KEY (contribution_id)
	REFERENCES Contribution (contribution_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Prize
	ADD FOREIGN KEY (contribution_id)
	REFERENCES Contribution (contribution_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Report
	ADD FOREIGN KEY (contribution_id)
	REFERENCES Contribution (contribution_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Tag
	ADD FOREIGN KEY (contribution_id)
	REFERENCES Contribution (contribution_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Contribution
	ADD FOREIGN KEY (member_no)
	REFERENCES Member (member_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Favorite
	ADD FOREIGN KEY (member_no)
	REFERENCES Member (member_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Message
	ADD FOREIGN KEY (member_no)
	REFERENCES Member (member_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Point_buy
	ADD FOREIGN KEY (member_no)
	REFERENCES Member (member_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Possession
	ADD FOREIGN KEY (member_no)
	REFERENCES Member (member_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Report
	ADD FOREIGN KEY (member_no)
	REFERENCES Member (member_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;
ALTER TABLE Contribution_details
	ADD FOREIGN KEY (title_id)
	REFERENCES Title (title_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;