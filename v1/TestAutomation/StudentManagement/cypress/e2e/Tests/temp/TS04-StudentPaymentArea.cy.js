describe("Management Student Area(Payment Courses)", () => {
  beforeEach(() => {
    cy.visit("http://localhost:4200/#/Students");
  });

  it("Config test", () => {
    cy.task(
      "queryDb",
      "DELETE FROM STUDENT WHERE STUDENT_DOCUMENT = '037.902.350-30'"
    );

    cy.task(
      "queryDb",
      "UPDATE STUDENT SET STUDENT_EMAIL = 'rp7o1mgh@mailosaur.net' WHERE STUDENT_DOCUMENT = '025.485.123-48'"
    );
  });

  it("Check student payments", () => {
    cy.get("#manageBtn2").click();

    cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");

    cy.get("h3").should("have.text", "Student Management: Maria Souza de Lima");

    cy.get(":nth-child(4) > .nav-link").click();

    cy.get("app-student-payments > .container-fluid")
      .should("contain", "Angular")
      .and("contain", "Java Web Developer");
  });

  it("Check search input need to be fullfill", () => {
    cy.get("#manageBtn2").click();

    cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");

    cy.get("h3").should("have.text", "Student Management: Maria Souza de Lima");

    cy.get(":nth-child(4) > .nav-link").click();

    cy.get(".input-group > :nth-child(2)").type("2023-04-01");

    cy.get(".btn-outline-success").click();

    cy.get(".help-block").should(
      "have.text",
      "Please enter with data in both fields"
    );
  });

  it("Search payments(Positive)", () => {
    cy.get("#manageBtn2").click();

    cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");

    cy.get("h3").should("have.text", "Student Management: Maria Souza de Lima");

    cy.get(":nth-child(4) > .nav-link").click();

    cy.get(".input-group > :nth-child(2)").type("2023-03-06");

    cy.get(".input-group > :nth-child(4)").type("2023-03-29");

    cy.get(".btn-outline-success").click();

    cy.get("app-student-payments > .container-fluid").should(
      "contain",
      "Angular"
    );
  });

  it("Search payments(Negative)", () => {
    cy.get("#manageBtn2").click();

    cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");

    cy.get("h3").should("have.text", "Student Management: Maria Souza de Lima");

    cy.get(":nth-child(4) > .nav-link").click();

    cy.get(".input-group > :nth-child(2)").type("2024-03-06");

    cy.get(".input-group > :nth-child(4)").type("2024-03-29");

    cy.get(".btn-outline-success").click();

    cy.get("h1").should("contain", "No records found...");
  });

  it("Send and check bill receipt", () => {
    cy.get("#manageBtn2").click();

    cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");

    cy.get("h3").should("have.text", "Student Management: Maria Souza de Lima");

    cy.get(":nth-child(4) > .nav-link").click();

    cy.get(':nth-child(2) > :nth-child(9) > .btn').click();

    cy.get('.alert').should('contain', 'Email sent with success');

    cy.wait(10000);

    cy.task("mailosaur", {
      emailAddress: "rp7o1mgh@mailosaur.net",
      subject: "Course Receipt - DON'T REPLY",
    }).then((email) => {
      expect(email.subject).to.equal(
        "Course Receipt - DON'T REPLY"
      );
    })
    
  });

});
