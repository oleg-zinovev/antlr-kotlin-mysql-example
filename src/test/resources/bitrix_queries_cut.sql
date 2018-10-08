
#begin
-- select_column tests
select * from `select`;
select *, `select`.*, `select`.* from `select`;
select *, 'abc' from `select`;
select *, 1, N'string' 'string2' from `select`;
#end

#begin
-- UNION tests
select 1 union select 2 limit 0,5;
select * from (select 1 union select 2 union select 0) as t order by 1 limit 0,10;
select col1 from t1 union select * from (select 1 as col2) as newt;
select col1 from t1 union (select * from (select 1 as col2) as newt);
select 1 as c1 union (((select 2)));
#end
#begin
-- -- -- subquery in UNION
select 1 union select * from (select 2 union select 3) as table1;
select 1 union (select * from (select 2 union select 3) as table1);
#end
#begin
-- subquery FROM
select * from (((((((select col1 from t1) as ttt))))));
select ship_power.gun_power, ship_info.*
FROM
	(
		select s.name as ship_name, sum(g.power) as gun_power, max(callibr) as max_callibr
		from
			ships s inner join ships_guns sg on s.id = sg.ship_id inner join guns g on g.id = sg.guns_id
		group by s.name
	) ship_power
	inner join
	(
		select s.name as ship_name, sc.class_name, sc.tonange, sc.max_length, sc.start_build, sc.max_guns_size
		from
			ships s inner join ship_class sc on s.class_id = sc.id
	) ship_info using (ship_name)
order by ship_power.ship_name;
#end
#begin
-- JOIN
-- -- -- join condition
select * from t1 inner join (t1 as tt1, t2 as tt2) on t1.col1 = tt1.col1;
select * from  (t1 as tt1, t2 as tt2) inner join t1 on t1.col1 = tt1.col1;
select * from  t1 as tt1, t2 as tt2 inner join t1 on true;
#end
#begin
-- where_condition test
select col1 from t1 inner join t2 on (t1.col1 = t2.col2);
select table_name from information_schema.TABLES where table_schema = DATABASE();
#end
#begin
-- identifiers tests
select 1 as 123e;
#end
#begin
-- not latin1 literals
select CONVERT( LEFT( CONVERT( 'Привет' USING binary ), 100 ) USING utf8 ) AS x_0;
select CONVERT( LEFT( CONVERT( 'Привет' USING binary ), 6 ) USING utf8 ) AS x_0;
select  t.*, tt.* FROM wptests_terms AS t  INNER JOIN wptests_term_taxonomy AS tt ON t.term_id = tt.term_id WHERE tt.taxonomy IN ('category') AND t.name IN ('远征手记') ORDER BY t.name ASC;
#end
