SET @id = 0;
create table t_workstream_type as 
select @id:= @id +1 as id, type_activite, code_activite FROM (select distinct 'g2r' as type_activite, code_activite from t_workstream_g2r) AS T;

