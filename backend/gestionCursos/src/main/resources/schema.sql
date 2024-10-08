DROP TABLE IF EXISTS asignatura;
DROP TABLE IF EXISTS curso;

CREATE TABLE curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha_inicio VARCHAR(20) NOT NULL,
    fecha_fin VARCHAR(20) NOT NULL,
    precio DOUBLE NOT NULL,
    anio INT NOT NULL
);

CREATE TABLE asignatura (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    creditos INT NOT NULL,
    num_horas INT NOT NULL,
    tipo ENUM('OBLIGATORIA', 'OPCIONAL') NOT NULL,
    curso_id INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);