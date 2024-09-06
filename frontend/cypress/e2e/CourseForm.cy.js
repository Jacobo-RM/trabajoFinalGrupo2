describe('CourseForm.vue', () => {
    beforeEach(() => {
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
  
    it('Debería crear un curso', () => {
      cy.visit('/curso-form');
  
      cy.get('#nombre').type('Nuevo Curso');
      cy.get('#descripcion').type('Descripción del nuevo curso');
      cy.get('#fechaInicio').type('2023-01-01');
      cy.get('#fechaFin').type('2023-12-31');
      cy.get('#precio').type('200');
      cy.get('#anio').type('2023');
  
      cy.get('button[type="submit"]').click();
  
      cy.wait('@addCurso').its('response.statusCode').should('eq', 200);
      cy.get('@addCurso').its('response.body.message').should('eq', 'Curso agregado exitosamente');
  
      cy.url().should('include', '/course-list');
    });
  
    it('Visitar página de edición con id', () => {
      cy.visit('/curso-form/1');
  
      cy.wait('@fetchCurso').its('response.statusCode').should('eq', 200);
      cy.get('#nombre').should('have.value', 'Curso de Prueba');
      cy.get('#descripcion').should('have.value', 'Descripción de prueba');
      cy.get('#fechaInicio').should('have.value', '2023-01-01');
      cy.get('#fechaFin').should('have.value', '2023-12-31');
      cy.get('#precio').should('have.value', '100');
      cy.get('#anio').should('have.value', '2023');
  
      cy.get('#nombre').clear().type('Curso Actualizado');
      cy.get('#precio').clear().type('150');
  
      cy.get('button[type="submit"]').click();
  
      cy.wait('@updateCurso').its('response.statusCode').should('eq', 200);
      cy.get('@updateCurso').its('response.body.message').should('eq', 'Curso actualizado exitosamente');
  
      cy.url().should('include', '/course-list');
    });
  
    it('Visitar la página de creación de curso', () => {
      
      cy.visit('/curso-form');
  
      cy.get('button[type="button"]').click();
  
      cy.url().should('include', '/course-list');
    });
  });