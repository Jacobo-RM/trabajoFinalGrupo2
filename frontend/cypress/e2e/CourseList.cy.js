describe('CourseList.vue', () => {
  beforeEach(() => {
    // Visitar la página de la lista de cursos antes de cada test
    cy.visit('/course-list');
  });

  it('should display the list of courses', () => {
    // Verificar que la lista de cursos se muestra correctamente
    cy.get('.cards-container .card').should('have.length.greaterThan', 0);
  });

  it('should filter courses based on search input', () => {
    // Escribir en el campo de búsqueda
    cy.get('input[name="search"]').type('Matemáticas');
    // Verificar que los cursos filtrados se muestran correctamente
    cy.get('.cards-container .card').each(($el) => {
      cy.wrap($el).contains('Matemáticas');
    });
  });



  it('should navigate to add course form', () => {
    // Hacer clic en el botón de agregar curso
    cy.get('.addBtn .aniadir').click();
    // Verificar que la URL cambió a la ruta del formulario de agregar curso
    cy.url().should('include', '/curso-form');
  });

  it('should navigate to edit course form', () => {
    // Hacer clic en el botón de editar del primer curso
    cy.get('.cards-container .card').first().find('.btn.btn-primary').click();
    // Verificar que la URL cambió a la ruta del formulario de editar curso
    cy.url().should('include', '/curso-form/');
  });

  it('should navigate to course details', () => {
    // Hacer clic en el botón de ver detalles del primer curso
    cy.get('.cards-container .card').first().find('.btn.btn-secondary').click();
    // Verificar que la URL cambió a la ruta de detalles del curso
    cy.url().should('include', '/curso/');
  });


});