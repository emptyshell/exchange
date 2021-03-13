INSERT INTO currency
    (full_name, name)
VALUES ('Leu Moldovenesc', 'LEI');

INSERT INTO branch
    (br_address, br_city, br_country, br_zip, br_local_currency)
VALUES ('virtual', 'virtual', 'virtual', 'virtual', (select cur_id from currency where currency.name = 'LEI'));

INSERT INTO employee
(br_id, emp_first_name, emp_last_name, emp_birthday, emp_access_level, emp_login, emp_password)
VALUES ((select br_id from branch where branch.br_address = 'virtual'), 'administrator', 'administrator', now(),
        'ADMIN', 'admin', '$2a$10$/W2wIwVGrC8aKEBSYjqX7ONPwWrh0nljw2QfZxpTyO7ah1Nkx/tKG');

INSERT INTO account
    (cur_id, br_id, ac_amount, "timestamp")
VALUES ((select cur_id from currency where currency.name = 'LEI'),
        (select br_id from branch where branch.br_address = 'virtual'), 0, now());
