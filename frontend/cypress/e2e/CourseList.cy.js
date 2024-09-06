describe('CourseList.vue', () => {
  beforeEach(() => {
    cy.visit('/course-list');
  });

  it('Verificar que la lista de cursos se muestra correctamente', () => {
    cy.get('.cards-container .card').should('have.length.greaterThan', 0);
  });

  it('Debería funcionar el compo de búsqueda y filtros', () => {
    cy.get('input[name="search"]').type('Matemáticas');
    cy.get('.cards-container .card').each(($el) => {
      cy.wrap($el).contains('Matemáticas');
    });
  });



  it('Navegar a course form', () => {
    cy.get('.addBtn .aniadir').click();
    cy.url().should('include', '/curso-form');
  });

  it('Navegar a edit course form', () => {
    cy.get('.cards-container .card').first().find('.btn.btn-primary').click();
    cy.url().should('include', '/curso-form/');
  });

  it('Navegar a course details', () => {
    cy.get('.cards-container .card').first().find('.btn.btn-secondary').click();
    cy.url().should('include', '/curso/');
  });


});