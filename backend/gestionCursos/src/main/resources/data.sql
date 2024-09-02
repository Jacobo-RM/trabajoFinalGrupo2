INSERT INTO curso (nombre, descripcion, fechaInicio, fechaFin, precio) 
VALUES 
('Curso de Programación Básica', 'Introducción a los conceptos de programación', '2024-09-15', '2024-12-15', 150.00),
('Curso de Matemáticas Avanzadas', 'Estudio avanzado de cálculo y álgebra lineal', '2024-10-01', '2025-01-30', 200.00);


INSERT INTO asignatura (nombre, descripcion, curso_id) 
VALUES 
('Fundamentos de Programación', 'Aprender los fundamentos de la programación en C', 1),
('Programación Orientada a Objetos', 'Conceptos avanzados de POO con Java', 1),
('Cálculo Diferencial', 'Estudio de las derivadas y aplicaciones', 2),
('Álgebra Lineal', 'Estudio de vectores, matrices y espacios vectoriales', 2);
