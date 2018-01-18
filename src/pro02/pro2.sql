/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */
select country_name, b.max_avg avg_sal
from countries c,
		(select l.country_id, avg(salary) avg
         from employees e, departments d, locations l
         where e.department_id = d.department_id
         and d.location_id = l.location_id
         group by l.country_id) a, (select max(avg(salary)) max_avg
                                    from employees e, departments d, locations l
                                    where e.department_id = d.department_id
                                    and d.location_id = l.location_id
                                    group by l.country_id) b
                                     
where c.country_id = a.country_id
and a.avg = b.max_avg;
/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/
select region_name, b.max_avg avg_sal
from countries c, regions r,
		(select l.country_id, avg(salary) avg
         from employees e, departments d, locations l
         where e.department_id = d.department_id
         and d.location_id = l.location_id
         group by l.country_id) a, (select max(avg(salary)) max_avg
                                    from employees e, departments d, locations l
                                    where e.department_id = d.department_id
                                    and d.location_id = l.location_id
                                    group by l.country_id) b
                                     
where c.region_id = r.region_id
and c.country_id = a.country_id
and a.avg = b.max_avg;

/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/
select d.department_name, count(*) "직원수" 
from employees e, departments d

where e.department_id = d.department_id 

group by d.department_name

having  count(*)>=45

order by count(*) desc;
