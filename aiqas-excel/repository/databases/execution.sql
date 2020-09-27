select * from scenes_info;
select * from formula_info;
select * from template_info;
select * from template_formula;

truncate scenes_info ;
truncate formula_info ;
truncate template_info ;
truncate template_formula ;

select a.content  ,f.name,c.name  from template_info a inner join template_formula b on a.id=b.template_id
inner join formula_info f on b.formula_id =f.id
inner join scenes_info c on c.id = a.scenes_id order by a.id;