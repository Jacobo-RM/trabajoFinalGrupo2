INSERT INTO curso (id, nombre, descripcion, fecha_inicio, fecha_fin, precio) 
VALUES 
(1, 'Curso de Programación Básica', 'Introducción a los conceptos de programación', '2024-09-15', '2024-12-15', 150.00),
(2, 'Curso de Matemáticas Avanzadas', 'Estudio avanzado de cálculo y álgebra lineal', '2024-10-01', '2025-01-30', 200.00),
(3, 'Curso de Diseño Gráfico', 'Aprende los fundamentos del diseño gráfico utilizando herramientas como Photoshop e Illustrator', '2024-09-20', '2024-12-20', 180.00),
(4, 'Curso de Marketing Digital', 'Estrategias de marketing en internet y redes sociales', '2024-10-05', '2025-01-10', 220.00),
(5, 'Curso de Física Moderna', 'Introducción a la relatividad y la mecánica cuántica', '2024-11-01', '2025-03-01', 250.00),
(6, 'Curso de Escritura Creativa', 'Desarrolla tus habilidades para la escritura de ficción y no ficción', '2024-12-01', '2025-03-01', 160.00);




INSERT INTO asignatura (id, nombre, descripcion, curso_id) 
VALUES 
(1, 'Fundamentos de Programación', 'Aprender los fundamentos de la programación en C', 1),
(2, 'Programación Orientada a Objetos', 'Conceptos avanzados de POO con Java', 1),
(3, 'Cálculo Diferencial', 'Estudio de las derivadas y aplicaciones', 2),
(4, 'Álgebra Lineal', 'Estudio de vectores, matrices y espacios vectoriales', 2),
(5, 'Fundamentos del Diseño Gráfico', 'Aprende los conceptos básicos del diseño gráfico', 3),
(6, 'Herramientas de Diseño', 'Uso de herramientas digitales en el diseño gráfico', 3),
(7, 'Estrategias de SEO', 'Optimización para motores de búsqueda', 4), 
(8, 'Publicidad en Redes Sociales', 'Creación y gestión de campañas en redes sociales', 4),
(9, 'Teoría de la Relatividad', 'Estudio de la teoría de la relatividad de Einstein', 5),
(10, 'Mecánica Cuántica', 'Conceptos fundamentales de la mecánica cuántica', 5),
(11, 'Narrativa Creativa', 'Desarrollo de técnicas narrativas para la escritura creativa', 6), 
(12, 'Técnicas de Edición', 'Mejoras en la edición y corrección de textos', 6);
