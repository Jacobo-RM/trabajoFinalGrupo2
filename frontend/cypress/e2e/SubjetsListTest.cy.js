// cypress/e2e/asignaturas.spec.js
describe('Asignaturas', () => {
    beforeEach(() => {
      cy.visit('/asignaturas');
    });
  
    it('Debería mostrar el botón de añadir asignatura', () => {
      cy.get('button.aniadir').should('exist');
    });
  
    it('Debería mostrar las asignaturas si hay datos disponibles', () => {
      cy.get('.card').should('exist');
    });
  
    it('Debería mostrar detalles cuando se haga clic en "Ver detalles"', () => {
      cy.get('.card').first().find('button.btn-secondary').click();
      cy.get('.modal-content').should('be.visible');
    });
  
    
  
    it('Debería navegar a la página de crear asignatura cuando se haga clic en el botón "Crear nueva asignatura"', () => {
      cy.get('button.aniadir').click();
      cy.url().should('include', '/asignatura-form');
    });
  
  });
  