---------------------------------------------------------------------------------------
-- This file is used to init roles table for tests                                   --
-- It can be used this way "@Sql(scripts = "classpath:sqlTestFolder/insertRoles.sql" --
---------------------------------------------------------------------------------------

delete from role where id > 3;

INSERT INTO role (id,name)
SELECT * FROM (SELECT 1 AS id, 'ROLE_EMPLOYEE' AS name) AS temp
WHERE NOT EXISTS (
    SELECT id FROM role WHERE id = 1
);

INSERT INTO role (id,name)
SELECT * FROM (SELECT 2 AS id, 'ROLE_MANAGER' AS name) AS temp
WHERE NOT EXISTS (
    SELECT id FROM role WHERE id = 2
);

INSERT INTO role (id,name)
SELECT * FROM (SELECT 3 AS id, 'ROLE_ADMIN' AS name) AS temp
WHERE NOT EXISTS (
    SELECT id FROM role WHERE id = 3
);
