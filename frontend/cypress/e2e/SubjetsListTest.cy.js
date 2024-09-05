// cypress/e2e/asignaturas.spec.js
describe('Asignaturas', () => {
    beforeEach(() => {
      // Visita la página inicial de tu aplicación Vue
      cy.visit('/asignaturas');
    });
  
    it('Debería mostrar el botón de añadir asignatura', () => {
      cy.get('button.aniadir').should('exist');
    });
  
    it('Debería mostrar las asignaturas si hay datos disponibles', () => {
      // Asegúrate de que hay al menos un elemento con la clase "card" en la página
      cy.get('.card').should('exist');
    });
  
    it('Debería mostrar detalles cuando se haga clic en "Ver detalles"', () => {
      // Asegúrate de que la primera asignatura tenga el botón "Ver detalles"
      cy.get('.card').first().find('button.btn-secondary').click();
      // Verifica que se muestre el modal con los detalles
      cy.get('.modal-content').should('be.visible');
    });
  
    it('Debería abrir el modal de confirmación al intentar eliminar una asignatura', () => {
      // Haz clic en el primer botón de eliminar
      cy.get('.card').first().find('button.btndelete').click();
      // Verifica que el modal de confirmación esté visible
      cy.get('.modal').should('be.visible');
    });
  
    it('Debería navegar a la página de crear asignatura cuando se haga clic en el botón "Crear nueva asignatura"', () => {
      cy.get('button.aniadir').click();
      // Verifica que la URL haya cambiado a la ruta de creación de asignaturas
      cy.url().should('include', '/asignatura-form');
    });
  
    // Puedes añadir más tests según sea necesario
  });
  