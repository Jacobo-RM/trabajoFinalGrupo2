INSERT INTO curso (nombre, descripcion, fecha_inicio, fecha_fin, precio) 
VALUES 
('Curso de Programación Básica', 'Introducción a los conceptos de programación', '2024-09-15', '2024-12-15', 150.00),
('Curso de Matemáticas Avanzadas', 'Estudio avanzado de cálculo y álgebra lineal', '2024-10-01', '2025-01-30', 200.00),
('Curso de Diseño Gráfico', 'Aprende los fundamentos del diseño gráfico utilizando herramientas como Photoshop e Illustrator', '2024-09-20', '2024-12-20', 180.00),
('Curso de Marketing Digital', 'Estrategias de marketing en internet y redes sociales', '2024-10-05', '2025-01-10', 220.00),
('Curso de Física Moderna', 'Introducción a la relatividad y la mecánica cuántica', '2024-11-01', '2025-03-01', 250.00),
('Curso de Escritura Creativa', 'Desarrolla tus habilidades para la escritura de ficción y no ficción', '2024-12-01', '2025-03-01', 160.00);




INSERT INTO asignatura (nombre, descripcion, curso_id) 
VALUES 
('Fundamentos de Programación', 'Aprender los fundamentos de la programación en C', 1),
('Programación Orientada a Objetos', 'Conceptos avanzados de POO con Java', 1),
('Cálculo Diferencial', 'Estudio de las derivadas y aplicaciones', 2),
('Álgebra Lineal', 'Estudio de vectores, matrices y espacios vectoriales', 2),
('Fundamentos del Diseño Gráfico', 'Aprende los conceptos básicos del diseño gráfico', 3),
('Herramientas de Diseño', 'Uso de herramientas digitales en el diseño gráfico', 3),
('Estrategias de SEO', 'Optimización para motores de búsqueda', 4), 
('Publicidad en Redes Sociales', 'Creación y gestión de campañas en redes sociales', 4),
('Teoría de la Relatividad', 'Estudio de la teoría de la relatividad de Einstein', 5),
('Mecánica Cuántica', 'Conceptos fundamentales de la mecánica cuántica', 5),
('Narrativa Creativa', 'Desarrollo de técnicas narrativas para la escritura creativa', 6), 
('Técnicas de Edición', 'Mejoras en la edición y corrección de textos', 6);
