describe('CourseForm.vue', () => {
    beforeEach(() => {
      // Interceptar las solicitudes de API
      cy.intercept('POST', '/api/cursos/agregarCurso', {
        statusCode: 200,
        body: { message: 'Curso agregado exitosamente' }
      }).as('addCurso');
  
      cy.intercept('PUT', '/api/cursos/actualizarCurso/*', {
        statusCode: 200,
        body: { message: 'Curso actualizado exitosamente' }
      }).as('updateCurso');
  
      cy.intercept('GET', '/api/cursos/*', {
        statusCode: 200,
        body: {
          id: 1,
          nombre: 'Curso de Prueba',
          descripcion: 'Descripción de prueba',
          fecha_inicio: '2023-01-01',
          fecha_fin: '2023-12-31',
          precio: 100,
          anio: 2023
        }
      }).as('fetchCurso');
    });
  
    it('should create a new course', () => {
      // Visitar la página de creación de curso
      cy.visit('/curso-form');
  
      // Llenar el formulario con datos de prueba
      cy.get('#nombre').type('Nuevo Curso');
      cy.get('#descripcion').type('Descripción del nuevo curso');
      cy.get('#fechaInicio').type('2023-01-01');
      cy.get('#fechaFin').type('2023-12-31');
      cy.get('#precio').type('200');
      cy.get('#anio').type('2023');
  
      // Enviar el formulario
      cy.get('button[type="submit"]').click();
  
      // Verificar que la solicitud POST fue enviada y la respuesta es correcta
      cy.wait('@addCurso').its('response.statusCode').should('eq', 200);
      cy.get('@addCurso').its('response.body.message').should('eq', 'Curso agregado exitosamente');
  
      // Verificar que la URL cambió a la lista de cursos
      cy.url().should('include', '/course-list');
    });
  
    it('should edit an existing course', () => {
      // Visitar la página de edición de curso con un ID específico
      cy.visit('/curso-form/1');
  
      // Esperar a que se complete la solicitud GET y verificar los datos cargados
      cy.wait('@fetchCurso').its('response.statusCode').should('eq', 200);
      cy.get('#nombre').should('have.value', 'Curso de Prueba');
      cy.get('#descripcion').should('have.value', 'Descripción de prueba');
      cy.get('#fechaInicio').should('have.value', '2023-01-01');
      cy.get('#fechaFin').should('have.value', '2023-12-31');
      cy.get('#precio').should('have.value', '100');
      cy.get('#anio').should('have.value', '2023');
  
      // Modificar algunos campos del formulario
      cy.get('#nombre').clear().type('Curso Actualizado');
      cy.get('#precio').clear().type('150');
  
      // Enviar el formulario
      cy.get('button[type="submit"]').click();
  
      // Verificar que la solicitud PUT fue enviada y la respuesta es correcta
      cy.wait('@updateCurso').its('response.statusCode').should('eq', 200);
      cy.get('@updateCurso').its('response.body.message').should('eq', 'Curso actualizado exitosamente');
  
      // Verificar que la URL cambió a la lista de cursos
      cy.url().should('include', '/course-list');
    });
  
    it('should navigate back to course list', () => {
      // Visitar la página de creación de curso
      cy.visit('/curso-form');
  
      // Hacer clic en el botón de "Volver"
      cy.get('button[type="button"]').click();
  
      // Verificar que la URL cambió a la lista de cursos
      cy.url().should('include', '/course-list');
    });
  });