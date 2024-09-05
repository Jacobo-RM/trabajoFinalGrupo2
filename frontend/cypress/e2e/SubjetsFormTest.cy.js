describe('SubjetsForm.vue', () => {
    beforeEach(() => {
      // Visitar el formulario de asignaturas antes de cada prueba
      cy.visit('/asignatura-form');
    });
  
    it('should display the form correctly', () => {
      // Verificar que los elementos del formulario están presentes
      cy.get('#nombre').should('exist');
      cy.get('#descripcion').should('exist');
      cy.get('#curso').should('exist');
      cy.get('#creditos').should('exist');
      cy.get('#num_horas').should('exist');
      cy.get('#tipo').should('exist');
      cy.get('button[type="submit"]').should('exist');
      cy.get('button[type="button"]').should('exist');
    });
  
    it('should create a new asignatura', () => {
      // Interceptar la solicitud POST para crear una nueva asignatura
      cy.intercept('POST', '/api/cursos/agregarAsignatura', {
        statusCode: 200,
        body: { message: 'Asignatura creada con éxito' }
      }).as('createAsignatura');
  
      // Llenar el formulario con datos de prueba
      cy.get('#nombre').type('Matemáticas');
      cy.get('#descripcion').type('Descripción de Matemáticas');
      
      // Esperar a que el select esté habilitado
      cy.get('#curso').should('not.be.disabled').then(() => {
        cy.get('#curso').select('1'); // Asegúrate de que el valor '1' sea válido en tu caso
      });
  
      cy.get('#creditos').type('5');
      cy.get('#num_horas').type('30');
      cy.get('#tipo').select('OBLIGATORIA');
      cy.get('button[type="submit"]').click();
  
      // Esperar a que se complete la solicitud y verificar la respuesta
      cy.wait('@createAsignatura').its('response.statusCode').should('eq', 200);
      cy.url().should('not.include', '/asignatura-form'); // Verificar redirección o navegación correcta
    });
  
    it('should edit an existing asignatura', () => {
        // Navegar a la ruta de edición con un ID específico y cursoEditable=true
        cy.visit('/asignatura-form/1?cursoEditable=true'); // Asegúrate de que el ID '1' sea válido y exista
      
        // Interceptar la solicitud PUT para actualizar una asignatura existente
        cy.intercept('PUT', '/api/cursos/actualizarAsignatura/1', {
          statusCode: 200,
          body: { message: 'Asignatura actualizada con éxito' }
        }).as('updateAsignatura');
      
        // Llenar el formulario con datos de prueba para edición
        cy.get('#nombre').clear().type('Matemáticas Avanzadas');
        cy.get('#descripcion').clear().type('Descripción actualizada de Matemáticas');
        
        // Esperar a que el select esté habilitado
        cy.get('#curso').should('not.be.disabled').then(() => {
          cy.get('#curso').select('2'); // Cambia el valor según sea necesario
        });
      
        cy.get('#creditos').clear().type('6');
        cy.get('#num_horas').clear().type('35');
        cy.get('#tipo').select('OPCIONAL');
        cy.get('button[type="submit"]').click();
      
        // Esperar a que se complete la solicitud y verificar la respuesta
        cy.wait('@updateAsignatura').its('response.statusCode').should('eq', 200);
        cy.url().should('not.include', '/asignatura-form'); // Verificar redirección o navegación correcta
      });
  
    it('should handle the back button correctly', () => {
      // Hacer clic en el botón de volver y verificar la navegación
      cy.get('button[type="button"]').click();
      cy.url().should('not.include', '/asignatura-form'); // Verificar redirección o navegación correcta
    });
  });
  