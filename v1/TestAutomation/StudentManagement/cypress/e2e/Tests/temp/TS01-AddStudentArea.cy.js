describe("Add Student area", () => {
  
  beforeEach(() => {
    cy.visit("http://localhost:4200/#/Students");
  });

  it("Don't allow to save whith empty fields", () => {
    cy.get(":nth-child(1) > .dropdown-toggle").click();

    cy.get(
      ":nth-child(1) > .dropdown-menu > :nth-child(3) > .nav-link"
    ).click();

    cy.url().should("be.equal", "http://localhost:4200/#/AddStudent");

    cy.get("#name").type("test");

    cy.get("#name").clear();

    cy.get(".col-4 > .alert > .help-block").should(
      "have.text",
      "Please enter with valid data"
    );

    cy.get("#email").type("test");

    cy.get(":nth-child(3) > :nth-child(1) > .alert > .help-block").should(
      "have.text",
      "Please enter with valid email"
    );

    cy.get("#save").should("be.disabled");

  });

  it("Don't allow to save with email already in use", () => {
    cy.get(":nth-child(1) > .dropdown-toggle").click();

    cy.get(
      ":nth-child(1) > .dropdown-menu > :nth-child(3) > .nav-link"
    ).click();

    cy.url().should("be.equal", "http://localhost:4200/#/AddStudent");

    cy.get("#name").type("Jose da silva");
    cy.get("#document").type("78921364512");
    cy.get("#age").type(30);
    cy.get("#birthdate").type("1980-05-25");
    cy.get("#country").type("Brasil");
    cy.get("#state").type("RS");
    cy.get("#city").type("Francisco do sul");
    cy.get("#adress").type("Rua flores da cunha, 456");
    cy.get("#zipcode").type("85978456");
    cy.get("#email").type("lucasbez14@outlook.com");
    cy.get("#phone").type("4988552233");
    cy.get("#phone_2").type("4977664455");
    cy.get("#registrationDate").type("2023-03-27");
    cy.get("#contract_term").check();

    cy.get("#save").should("not.be.disabled");

    cy.wait(2000);
    cy.get("#save").click();

    cy.get(".alert").should("contain", "Opss...Something is not working but we will fix soon. Please try again later");

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
        "FROM STUDENT WHERE STUDENT_DOCUMENT = '78921364512'"
    ).then((result) => {
      expect(result.length).to.eq(0);
    });

  });

  it("Save new student", () => {
    cy.get(":nth-child(1) > .dropdown-toggle").click();

    cy.get(
      ":nth-child(1) > .dropdown-menu > :nth-child(3) > .nav-link"
    ).click();

    cy.url().should("be.equal", "http://localhost:4200/#/AddStudent");

    cy.get("#name").type("Jose da silva");
    cy.get("#document").type("78921364512");
    cy.get("#age").type(30);
    cy.get("#birthdate").type("1980-05-25");
    cy.get("#country").type("Brasil");
    cy.get("#state").type("RS");
    cy.get("#city").type("Francisco do sul");
    cy.get("#adress").type("Rua flores da cunha, 456");
    cy.get("#zipcode").type("85978456");
    cy.get("#email").type("rp7o1mgh@mailosaur.net");
    cy.get("#phone").type("4988552233");
    cy.get("#phone_2").type("4977664455");
    cy.get("#registrationDate").type("2023-03-27");
    cy.get("#contract_term").check();

    cy.get("#save").should("not.be.disabled");

    cy.wait(2000);
    cy.get("#save").click();

    cy.get(".alert").should("contain", "Student created with success...");

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
        "FROM STUDENT WHERE STUDENT_DOCUMENT = '78921364512'"
    ).then((result) => {
      expect(result[0].STUDENT_NAME).to.eq("Jose da silva");
      expect(result[0].STUDENT_DOCUMENT).to.eq("78921364512");
      expect(result[0].STUDENT_AGE).to.eq("30");
      expect(result[0].STUDENT_BIRTHDATE).to.eq("1980-05-25");
      expect(result[0].STUDENT_COUNTRY).to.eq("Brasil");
      expect(result[0].STUDENT_STATE).to.eq("RS");
      expect(result[0].STUDENT_CITY).to.eq("Francisco do sul");
      expect(result[0].STUDENT_ADRESS).to.eq("Rua flores da cunha, 456");
      expect(result[0].STUDENT_ZIPCODE).to.eq("85978456");
      expect(result[0].STUDENT_EMAIL).to.eq("rp7o1mgh@mailosaur.net");
      expect(result[0].STUDENT_PHONE).to.eq("4988552233");
      expect(result[0].STUDENT_PHONE_2).to.eq("4977664455");
      expect(result[0].STUDENT_CONTRACT_TERMS).to.eq(1);
      expect(result[0].STUDENT_CREATION_DATE).to.eq("2023-03-27");
    });

    cy.wait(10000);

    cy.task("mailosaur", {
      emailAddress: "rp7o1mgh@mailosaur.net",
      subject: "Welcome Jose da silva - DON'T REPLY",
    }).then((email) => {
      expect(email.subject).to.equal("Welcome Jose da silva - DON'T REPLY");
    });

    cy.url().should("be.equal", "http://localhost:4200/#/Students");
  });

  it("Saved student is visible", () => {
    cy.get("#student_information").should("not.be.visible");

    cy.get(":nth-child(3) > th > a").click();

    cy.get("#student_information").should("be.visible");

    cy.get(
      "#student_information > .modal-dialog > .modal-content > .modal-header > #exampleModalLabel"
    ).should("have.text", "Jose da silva");
    cy.get(".list-group > :nth-child(1)").should(
      "have.text",
      "Document: 78921364512"
    );
    cy.get(".list-group > :nth-child(2)").should("have.text", "Age: 30");
    cy.get(".list-group > :nth-child(3)").should(
      "have.text",
      "Birthdate: 05/25/1980"
    );
    cy.get(".list-group > :nth-child(4)").should(
      "have.text",
      "Country: Brasil"
    );
    cy.get(".list-group > :nth-child(5)").should("have.text", "State: RS");
    cy.get(".list-group > :nth-child(6)").should(
      "have.text",
      "City: Francisco do sul"
    );
    cy.get(".list-group > :nth-child(7)").should(
      "have.text",
      "Adress: Rua flores da cunha, 456"
    );
    cy.get(".list-group > :nth-child(8)").should(
      "have.text",
      "Zipcode: 85978456"
    );
    cy.get(".list-group > :nth-child(9)").should(
      "have.text",
      "Email: rp7o1mgh@mailosaur.net"
    );
    cy.get(".list-group > :nth-child(10)").should(
      "have.text",
      "Phone: 4988552233"
    );
    cy.get(".list-group > :nth-child(11)").should(
      "have.text",
      "Phone 2: 4977664455"
    );
    cy.get(".list-group > :nth-child(12)").should(
      "have.text",
      "Registration Date: 03/27/2023"
    );
  });

});
