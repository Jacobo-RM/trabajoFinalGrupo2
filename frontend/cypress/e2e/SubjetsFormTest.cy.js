describe('SubjetsForm.vue', () => {
    beforeEach(() => {
      cy.visit('/asignatura-form');
    });
  
    it('Verificar que los elementos del formulario están presentes', () => {
    
      cy.get('#nombre').should('exist');
      cy.get('#descripcion').should('exist');
      cy.get('#curso').should('exist');
      cy.get('#creditos').should('exist');
      cy.get('#num_horas').should('exist');
      cy.get('#tipo').should('exist');
      cy.get('button[type="submit"]').should('exist');
      cy.get('button[type="button"]').should('exist');
    });
  
 
  
    it('Hacer clic en el botón de volver y verificar la navegación', () => {
      cy.get('button[type="button"]').click();
      cy.url().should('not.include', '/asignatura-form'); 
    });
  });
  