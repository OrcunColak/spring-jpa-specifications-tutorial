insert into company (id,name) values (1,'company-1');
insert into project (id,name) values (10,'project-1');
insert into company_project_mapping (company_id,project_id) values (1,10);

-- Add company with no project
insert into company (id,name) values (2,'company-2');