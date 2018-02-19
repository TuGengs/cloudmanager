create database test;

use test;

drop table if exists user;
create table user(
	id int unsigned primary key auto_increment,
	tel char(11) unique,
	hashed_password char(32) unique,
	name char(5),
	class varchar(255),
	sysnum varchar(255),
    is_teacher tinyint,	/*是不是老师*/
	building varchar(255),
    room varchar(255),
	gender tinyint unsigned,
	school_id int unsigned,
	academy varchar(255),
	icon_id int unsigned,
	photo_id int unsigned
);

drop table if exists school;
create table school (
	id int unsigned primary key auto_increment,
    name varchar(255),
    longitude decimal(9, 6),
    latitude decimal(9, 6),
    crawled tinyint unsigned default 0
);

drop table if exists sign_in_check;
create table sign_in_check (
	id int unsigned auto_increment primary key,
    check_id int unsigned,
    name varchar(255) not null,
    creator_id int unsigned not null,
    create_time datetime not null default current_timestamp,
    start_time date,
    is_allowed tinyint default 0,
    cycle int default -1,
    score_absence json,
    score_late json,
    score_leave json,
    checker_type tinyint,
    remark varchar(255)
);

drop table if exists single_sign_in_check;
create table single_sign_in_check (
	id int unsigned auto_increment primary key,
    sign_in_check_id int unsigned not null,
    create_time datetime default current_timestamp,
    remark varchar(255)
);

drop table if exists detailed_sign_in_check;
create table detailed_sign_in_check (
	id int unsigned auto_increment primary key,
    single_sign_in_check_id int unsigned not null,
    user_id int unsigned not null,
    is_here tinyint unsigned not null default 0,
    signed_times int unsigned default 0
);

drop table if exists grade_check;
create table grade_check (
	id int unsigned auto_increment primary key,
    name varchar(255) not null,
    check_id int unsigned not null,
    creator_id int unsigned not null,
    create_time datetime not null default current_timestamp,
    cycle int default -1,
    start_time date,
    is_allowed tinyint default 0,
    checker_type tinyint,
    remark varchar(255)
);

drop table if exists single_grade_check;
create table single_grade_check (
	id int unsigned auto_increment primary key,
	grade_check_id int unsigned not null,
    create_time datetime not null default current_timestamp,
    remark varchar(255)
);

drop table if exists detailed_grade_check;
create table detailed_grade_check (
	id int unsigned auto_increment primary key,
    single_grade_check_id int unsigned not null,
    user_id int unsigned not null,
    grading_items json,
    manager_id int unsigned not null,
    remark varchar(255)
);

drop table if exists grading_item;
create table grading_item (
	id int unsigned auto_increment primary key,
	grade_check_id int unsigned not null,
    name varchar(255) not null,
    score float4 not null,
    remark varchar(255)
);

delimiter //
drop procedure if exists proc_get_single_check//
create procedure proc_get_single_check(
    in in_check_id int,
	in in_cycle_time int,
    in in_table varchar(255)
)
begin

	declare check_cycle int;
    declare check_start_time datetime;
    declare result_start_time datetime;
    declare result_end_time datetime;
    
    set @get_cycle = concat("select cycle from ", in_table, "_check where id = ", in_check_id, " into @check_cycle");
    set @get_start_time = concat("select start_time from ", in_table, "_check where id = ", in_check_id, " into @check_start_time");
    
    prepare stmt1 from @get_cycle;
    execute stmt1;
    deallocate prepare stmt1;
    
    prepare stmt2 from @get_start_time;
    execute stmt2;
    deallocate prepare stmt2;
    
    set check_cycle = @check_cycle;
    set check_start_time = @check_start_time;
    
    if check_cycle > 0 then
        
        set result_start_time = date_add(check_start_time, interval check_cycle * (in_cycle_time - 1) day);
        
        if result_start_time > now() then
			select null;
        else
			set result_end_time = date_add(check_start_time, interval check_cycle * in_cycle_time - 1 day);
        
			set @single_ordinary = concat("select * from single_", in_table, "_check where create_time between '", result_start_time, "' and '", result_end_time, "'");
            prepare stmt3 from @single_ordinary;
			execute stmt3;
			deallocate prepare stmt3;
		end if;
        
    elseif cycle = 0 then
    
		set result_start_time = date_add(date_add(check_start_time, interval - day(check_start_time) + 1 day), interval in_cycle_time - 1 month);
		
        if result_start_time > now() then
			select null;
		else
			set result_end_time = last_day(result_start_time);
            
			set @single_month = concat("select * from single_", in_table, "_check where create_time between '", result_start_time, "' and '", result_end_time, "'");
			prepare stmt4 from @single_month;
			execute stmt4;
			deallocate prepare stmt4;
        end if;
        
	else
    
		select null;
        
    end if;
    
end//
delimiter ;

DROP TABLE IF EXISTS `list_to_check`;
CREATE TABLE `list_to_check` (
  `check_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `auto_id` int(11) DEFAULT NULL,
  `check_type` int(11) DEFAULT NULL
);

drop table if exists icon;
create table icon (
	id int unsigned auto_increment primary key,
    url varchar(255) not null,
    user_id int unsigned not null
);

/** 下面的存储过程以及tmp_classes表全是注册用的，不要删掉 **/

DELIMITER $$

CREATE FUNCTION `func_get_split_string`(
f_string varchar(1000),f_delimiter varchar(5),f_order int) RETURNS varchar(255) CHARSET utf8
BEGIN

      -- Get the separated number of given string.
      declare result varchar(255) default '';
      set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));
      return result;
END$$

DELIMITER ;

drop function if exists func_get_split_string_total;
DELIMITER $$

    CREATE FUNCTION `func_get_split_string_total`(
    f_string varchar(1000),f_delimiter varchar(5)
    ) RETURNS int(11)
    BEGIN

      -- Get the total number of given string.
      return 1+(length(f_string) - length(replace(f_string,f_delimiter,'')));
    END$$

DELIMITER ;

-- drop procedure if exists proc_get_splited_result;
-- DELIMITER $$

--     CREATE PROCEDURE `proc_get_splited_result`(
--      IN f_string varchar(1000),IN f_delimiter varchar(5)
--     )
--     BEGIN
--       -- Get the separated string.
--       declare cnt int default 0;
--       declare i int default 0;
--       set cnt = func_get_split_string_total(f_string,f_delimiter);
--       drop table if exists tmp_classes;
--       create temporary table tmp_classes (class varchar(255) not null);
--       while i < cnt
--       do
--         set i = i + 1;
--         insert into tmp_classes(class) values (func_get_split_string(f_string,f_delimiter,i));
--       end while;
      
--       select * from tmp_classes;

--     END$$

-- DELIMITER ;

drop table if exists tmp_classes;
create table tmp_classes (
	class varchar(255),
    check_id int,
    auto_id int
);

DELIMITER $$

drop procedure if exists proc_add_lecture$$
CREATE PROCEDURE `proc_add_lecture` (
	IN in_sysnum varchar(255)
	)
BEGIN
	
    declare uid int;
    declare check_id int;
	declare check_name varchar(255);
	declare classes varchar(255);
	declare cnt int default 0;
	declare i int default 0;	
	declare cur_end int default 0;
    declare cur cursor for select merge_kcmc, merge_skdx from t_mergeschedule where teacher_id = cast(in_sysnum as signed integer);
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET cur_end=1;
	
	select id from user where sysnum = in_sysnum into uid;
    
	open cur;
      
	repeat
    
		fetch cur into check_name, classes;
        if cur_end <> 1 then
			insert into sign_in_check(name, sub_name, creator_id) values (check_name, classes, uid);
			set check_id = last_insert_id();
            update sign_in_check set check_id = check_id where id = check_id;
			/** 获取每一个班级 **/
			set cnt = func_get_split_string_total(classes, ',');
			set i = 0;
			while i < cnt
			do
				set i = i + 1;
				insert into tmp_classes(class, check_id, auto_id) values (func_get_split_string(classes, ',' , i), check_id, check_id);
			end while;
			
			insert into list_to_check (check_id, user_id, auto_id, check_type) select * from (select tmp_classes.check_id, user.id, tmp_classes.auto_id, 1 from user, tmp_classes where user.class in (select class from tmp_classes)) as abc;
		
			truncate tmp_classes;
            
		end if;
        
    until cur_end end repeat;
    
    close cur;
        
END$$