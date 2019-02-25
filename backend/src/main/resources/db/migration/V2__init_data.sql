INSERT INTO department(created, updated, name) VALUES (NOW(), NOW(), 'JAVA');
INSERT INTO department(created, updated, name) VALUES (NOW(), NOW(), 'DB');
INSERT INTO department(created, updated, name) VALUES (NOW(), NOW(), 'JS');

INSERT INTO employee(created, updated, first_name, last_name, salary, department_id, active) VALUES (NOW(), NOW(), 'James', 'Gosling', 1000000, 1, b'1');
INSERT INTO employee(created, updated, first_name, last_name, salary, department_id, active) VALUES (NOW(), NOW(), 'Bruce', 'Eckel', 500000, 1, b'1');
INSERT INTO employee(created, updated, first_name, last_name, salary, department_id, active) VALUES (NOW(), NOW(), 'Joshua', 'Bloch', 700000, 1, b'1');