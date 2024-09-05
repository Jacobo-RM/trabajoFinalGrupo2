package com.gestionCursos.gestionCursos;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.gestionCursos.gestionCursos.Controller.CursoController;
import com.gestionCursos.gestionCursos.Models.Curso;
import com.gestionCursos.gestionCursos.Service.CursoService;

public class CursoControllerUnitTest {

    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCursos() {
        Curso curso1 = new Curso();
        curso1.setId(1);
        curso1.setNombre("Curso 1");
        curso1.setDescripcion("Descripcion Curso 1");
        curso1.setFecha_inicio("2024-09-01");
        curso1.setFecha_fin("2024-09-01");
        curso1.setPrecio(100.0);
        curso1.setAnio(2024);
    
        when(cursoService.getCursos()).thenReturn(Arrays.asList(curso1));
        
        List<Curso> cursos = cursoController.getCursos();
    
        assertNotNull(cursos);
        assertEquals(1, cursos.size());
        assertEquals("Curso 1", cursos.get(0).getNombre());
        assertEquals("Descripcion Curso 1", cursos.get(0).getDescripcion());
        assertEquals("2024-09-01", cursos.get(0).getFecha_inicio());
        assertEquals("2024-09-01", cursos.get(0).getFecha_fin());
        assertEquals(100.0, cursos.get(0).getPrecio());
        assertEquals(2024, cursos.get(0).getAnio());
    
        verify(cursoService).getCursos();
    }

}