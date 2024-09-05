// cypress/integration/course_list_spec.js
describe('Course List Page', () => {
    beforeEach(() => {
      // Visita la página de lista de cursos antes de cada prueba
      cy.visit('/course-list'); // Cambia la URL a la de tu aplicación
    });
  
    it('should display the add course button', () => {
      // Verifica que el botón de agregar curso está presente
      cy.contains('button', 'Agregar Curso').should('be.visible');
    });
  
    it('should display the courses table', () => {
      // Verifica que la tabla de cursos está presente
      cy.get('table').should('be.visible');
      cy.get('thead').should('contain', 'Nombre')
                     .and('contain', 'Descripción')
                     .and('contain', 'Fecha Inicio')
                     .and('contain', 'Fecha Fin')
                     .and('contain', 'Precio')
                     .and('contain', 'Año')
                     .and('contain', 'Acciones');
    });
  
    it('should open the edit course form when edit button is clicked', () => {
      // Simula el clic en el botón de editar y verifica la navegación
      cy.get('tbody tr').first().find('button').contains('Editar').click();
      cy.url().should('include', '/curso-form/');
    });
  
    it('should open the course details when details button is clicked', () => {
      // Simula el clic en el botón de ver detalles y verifica la navegación
      cy.get('tbody tr').first().find('button').contains('Ver Detalles').click();
      cy.url().should('include', '/curso/');
    });
  
    it('should open the delete modal when delete button is clicked', () => {
      // Simula el clic en el botón de eliminar y verifica que el modal se abre
      cy.get('tbody tr').first().find('button').contains('Eliminar').click();
      cy.get('.modal-overlay').should('be.visible');
    });
  
    it('should delete a course when confirm delete button is clicked', () => {
      // Simula el clic en el botón de eliminar y confirma la eliminación
      cy.get('tbody tr').first().find('button').contains('Eliminar').click();
      cy.get('.modal-overlay').should('be.visible');
      cy.get('.modal button').contains('Sí, eliminar').click();
      cy.get('.modal-overlay').should('not.exist');
      cy.get('tbody tr').should('have.length.lessThan', 1); // Ajusta según la cantidad de cursos iniciales
    });
  });