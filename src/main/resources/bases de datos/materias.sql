INSERT INTO materias (nombre, codigo, activo, cuatrimestre) VALUES
('Programación Orientada a Objetos', 'POO101', true, 'Segundo'),
('Bases de Datos', 'BD202', true, 'Tercero'),
('Desarrollo Web', 'DW303', true, 'Cuarto'),
('Ingeniería de Software', 'IS404', true, 'Quinto'),
('Sistemas Operativos', 'SO505', true, 'Sexto');


INSERT INTO programa_educativo (id, nombre, activo) VALUES
(1, 'Ingeniería en Desarrollo de Software', true),
(2, 'Ingeniería Industrial', true),
(3, 'Gastronomía', true),
(4, 'Administración de Empresas', true),
(5, 'Diseño Digital y Multimedia', true);


INSERT INTO materias (id, nombre, codigo, activo, cuatrimestre, programa_id) VALUES
(1, 'Programación Orientada a Objetos', 'POO-101', true, '3', 1),
(2, 'Logística y Cadenas de Suministro', 'LCS-204', true, '4', 2),
(3, 'Cocina Internacional', 'CI-310', true, '5', 3),
(4, 'Contabilidad Administrativa', 'CA-120', true, '2', 4),
(5, 'Diseño de Interfaces Digitales', 'UX-450', true, '4', 5);


INSERT INTO programa_educativo (id, nombre, activo)
VALUES (8, 'Programa Fantasma', true);

INSERT INTO materias (id, nombre, codigo, activo, cuatrimestre, programa_id) VALUES
(6, 'Programación Orientadag a Objetos', 'POO-11', true, '3', 8);