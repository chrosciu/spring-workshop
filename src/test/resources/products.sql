INSERT INTO product(id, name, description, price, type)
VALUES (1,
        'Spring masterclass',
        'Praktyczny kurs Spring framework',
        100,
        'VIDEO');

INSERT INTO product(id, name, description, price, type)
VALUES (2,
        'Spring guide',
        'Praktyczne ćwieczenia do samodzielnego wykonania',
        200,
        'BOOK');

INSERT INTO product(id, name, description, price, type)
VALUES (3,
        'Spring masterclass',
        'Praktyczny kurs Spring framework',
        300,
        'VIDEO');

INSERT INTO product(id, name, description, price, type)
VALUES (4,
        'Spring guide',
        'Praktyczne ćwieczenia do samodzielnego wykonania',
        400,
        'BOOK');

INSERT INTO product(id, name, description, price, type)
VALUES (5,
        'Spring do poduszki',
        'Szybki usypiacz',
        500,
        'AUDIO');

INSERT INTO product(id, name, description, price, type)
VALUES (6,
        'Spring guide',
        'Praktyczne ćwieczenia do samodzielnego wykonania',
        600,
        'AUDIO');

INSERT INTO product(id, name, description, price, type)
VALUES (7,
        'Spring masterclass',
        'Praktyczny kurs Spring framework',
        700,
        'VIDEO');

INSERT INTO product(id, name, description, price, type)
VALUES (8,
        'Spring do poduszki',
        'Szybki usypiacz',
        800,
        'BOOK');

INSERT INTO product(id, name, description, price, type)
VALUES (9,
        'Spring masterclass',
        'Praktyczny kurs Spring framework',
        900,
        'AUDIO');

INSERT INTO product(id, name, description, price, type)
VALUES (10,
        'Spring guide',
        'Praktyczne ćwiczenia do samodzielnego wykonania',
        1000,
        'AUDIO');

SELECT setval('product_seq', 11, FALSE);