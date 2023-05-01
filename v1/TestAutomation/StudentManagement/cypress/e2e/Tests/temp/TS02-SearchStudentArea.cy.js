describe("Search student area", () => {
  
  beforeEach(() => {
    cy.visit("http://localhost:4200/#/Students");
  });

  it("Config test", () => {

    cy.task(
      "queryDb",
      "DELETE FROM STUDENT WHERE STUDENT_DOCUMENT = '78921364512'"
    );

    cy.task(
      "queryDb",
      "UPDATE STUDENT SET STUDENT_EMAIL = 'rp7o1mgh@mailosaur.net' WHERE STUDENT_DOCUMENT = '037.902.350-30'"
    );

    cy.task(
      "queryDb",
      "SELECT STUDENT_EMAIL FROM STUDENT WHERE STUDENT_DOCUMENT = '037.902.350-30'"
    ).then((result) => {
      expect(result[0].STUDENT_EMAIL).to.eq('rp7o1mgh@mailosaur.net');
    });

  });

  it("Search student by name(positve)", () => {
    cy.get(".input-group > .btn-dark").click();

    cy.get("#SearchByName").click();

    cy.get(".form-control").type("Maria Souza");

    cy.get(".btn-outline-success").click();

    cy.get(".align-items-center").should("contain", "Maria Souza de Lima");
  });

  it("Search student by name(negative)", () => {
    cy.get(".input-group > .btn-dark").click();

    cy.get("#SearchByName").click();

    cy.get(".form-control").type("Test");

    cy.wait(3000);
    cy.get(".btn-outline-success").click();

    cy.get("#errorMSG").should("contain", "Student not found.... ");

    cy.get(".d-flex > h1").should("have.text", "No records found...");

    cy.get(".align-items-center").should("not.contain", "Test");
  });

  it("Search student by document(positive)", () => {
    cy.get(".input-group > .btn-dark").click();

    cy.get("#SearchByDocument").click();

    cy.get(".form-control").type("037.902.350-70");

    cy.get(".btn-outline-success");

    cy.get(".align-items-center")
      .should("contain", "Hernesto da silva")
      .and("contain", "037.902.350-70");
  });

  it("Search student by document(negative)", () => {
    cy.get(".input-group > .btn-dark").click();

    cy.get("#SearchByDocument").click();

    cy.get(".form-control").type("987.456.777-00");

    cy.wait(3000);
    cy.get(".btn-outline-success").click();

    cy.get("#errorMSG").should("contain", "Student not found.... ");

    cy.get(".d-flex > h1").should("have.text", "No records found...");

    cy.get(".align-items-center").should(
      "not.contain",
      "LUCAS ALEXANDRE BEZ PIANCOSKI"
    );
  });

  it("Show Student info", () => {
    cy.get("#student_information").should("not.be.visible");

    cy.get(":nth-child(1) > th > a").click();

    cy.get("#student_information").should("be.visible");

    cy.get(
      "#student_information > .modal-dialog > .modal-content > .modal-header > #exampleModalLabel"
    ).should("have.text", "Andre de Souza");

    cy.get(".list-group > :nth-child(1)").should(
      "have.text",
      "Document: 037.902.350-30"
    );
    cy.get(".list-group > :nth-child(2)").should("have.text", "Age: 25");
    cy.get(".list-group > :nth-child(3)").should(
      "have.text",
      "Birthdate: 05/05/1999"
    );
    cy.get(".list-group > :nth-child(4)").should(
      "have.text",
      "Country: Brasil"
    );
    cy.get(".list-group > :nth-child(5)").should("have.text", "State: SC");
    cy.get(".list-group > :nth-child(6)").should(
      "have.text",
      "City: São Lourenço d'Oeste"
    );
    cy.get(".list-group > :nth-child(7)").should(
      "have.text",
      "Adress: Rua Flores da cunha, 123"
    );
    cy.get(".list-group > :nth-child(8)").should(
      "have.text",
      "Zipcode: 88106282"
    );
    cy.get(".list-group > :nth-child(9)").should(
      "have.text",
      "Email: rp7o1mgh@mailosaur.net"
    );
    cy.get(".list-group > :nth-child(10)").should(
      "have.text",
      "Phone: 48 988228825"
    );
    cy.get(".list-group > :nth-child(11)").should("have.text", "Phone 2: ");
    cy.get(".list-group > :nth-child(12)").should(
      "have.text",
      "Registration Date: 03/25/2023"
    );

    cy.get(
      "#student_information > .modal-dialog > .modal-content > .modal-header > .btn-close"
    ).click();

    cy.get("#student_information").should("not.be.visible");

    cy.task(
      "queryDb",
      "SELECT " +
        "STUDENT_NAME," +
        "STUDENT_DOCUMENT," +
        "STUDENT_AGE," +
        "STUDENT_BIRTHDATE," +
        "STUDENT_COUNTRY," +
        "STUDENT_STATE," +
        "STUDENT_CITY," +
        "STUDENT_ADRESS," +
        "STUDENT_ZIPCODE," +
        "STUDENT_EMAIL," +
        "STUDENT_PHONE," +
        "STUDENT_PHONE_2," +
        "STUDENT_CONTRACT_TERMS," +
        "STUDENT_CREATION_DATE " +
        "FROM STUDENT WHERE STUDENT_DOCUMENT = '037.902.350-30'"
    ).then((result) => {
      expect(result[0].STUDENT_NAME).to.eq("Andre de Souza");
      expect(result[0].STUDENT_DOCUMENT).to.eq("037.902.350-30");
      expect(result[0].STUDENT_AGE).to.eq("25");
      expect(result[0].STUDENT_BIRTHDATE).to.eq("1999-05-05");
      expect(result[0].STUDENT_COUNTRY).to.eq("Brasil");
      expect(result[0].STUDENT_STATE).to.eq("SC");
      expect(result[0].STUDENT_CITY).to.eq("São Lourenço d'Oeste");
      expect(result[0].STUDENT_ADRESS).to.eq("Rua Flores da cunha, 123");
      expect(result[0].STUDENT_ZIPCODE).to.eq("88106282");
      expect(result[0].STUDENT_EMAIL).to.eq("rp7o1mgh@mailosaur.net");
      expect(result[0].STUDENT_PHONE).to.eq("48 988228825");
      expect(result[0].STUDENT_PHONE_2).to.eq("");
      expect(result[0].STUDENT_CONTRACT_TERMS).to.eq(1);
      expect(result[0].STUDENT_CREATION_DATE).to.eq("2023-03-25");
    });
  });

  it("Cancel delete(Button 'X')", () => {
    cy.get("#exampleModal").should("not.be.visible");

    cy.get("#deleteBtn1").click();

    cy.get("#exampleModal").should("be.visible");

    cy.get(
      "#exampleModal > .modal-dialog > .modal-content > .modal-header > #exampleModalLabel"
    ).should("have.text", "Delete student");

    cy.get("#exampleModal > .modal-dialog > .modal-content > .modal-body")
      .should("contain", "You really want to delete the student register?")
      .and("contain", "Action can't be undone after confirmation");

    cy.wait(2000);
    cy.get(
      "#exampleModal > .modal-dialog > .modal-content > .modal-header > .btn-close"
    ).click();

    cy.get("#exampleModal").should("not.be.visible");
  });

  it("Cancel delete(Button 'Cancel')", () => {
    cy.get("#exampleModal").should("not.be.visible");

    cy.get("#deleteBtn1").click();

    cy.get("#exampleModal").should("be.visible");

    cy.wait(2000);
    cy.get("#cancelDelete").click();

    cy.get("#exampleModal").should("not.be.visible");
  });

  it("Delete Student and check email", () => {
    cy.get("#exampleModal").should("not.be.visible");

    cy.get("#deleteBtn0").click();

    cy.get("#exampleModal").should("be.visible");

    cy.wait(2000);
    cy.get("#confirmDelete").click();

    cy.get("#successMSG").should("contain", "Student deleted with success...");

    cy.wait(2000);
    cy.get("#successMSG").should("not.exist");

    cy.get(".align-items-center")
      .should("not.contain", "Andre de Souza")
      .and("not.contain", "037.902.350-30");

    cy.task(
      "queryDb",
      "SELECT * FROM STUDENT WHERE STUDENT_DOCUMENT = '037.902.350-30'"
    ).then((result) => {
      expect(result.length).to.eq(0);
    });

    cy.wait(10000);

    cy.task("mailosaur", {
      emailAddress: "rp7o1mgh@mailosaur.net",
      subject: "We will miss you Andre de Souza - DON'T REPLY",
    }).then((email) => {
      expect(email.subject).to.equal(
        "We will miss you Andre de Souza - DON'T REPLY"
      );
    });
  });
});
