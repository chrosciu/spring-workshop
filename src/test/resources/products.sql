INSERT INTO product(id, name, description, price, type)
VALUES (nextval('product_seq'),
        'Spring masterclass',
        'Praktyczny kurs Spring framework',
        1500,
        'VIDEO');

INSERT INTO product(id, name, description, price, type)
VALUES (nextval('product_seq'),
        'Spring guide',
        'Praktyczne ćwiczenia do samodzielnego wykonania',
        200,
        'BOOK');