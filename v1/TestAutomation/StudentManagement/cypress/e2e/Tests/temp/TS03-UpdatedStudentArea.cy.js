describe("Update student area", () => {
  beforeEach(() => {
    cy.visit("http://localhost:4200/#/Students");
  });

  it("Config test", () => {
    cy.task(
      "queryDb",
      "INSERT INTO STUDENT (STUDENT_NAME,STUDENT_DOCUMENT,STUDENT_AGE,STUDENT_BIRTHDATE,STUDENT_COUNTRY,STUDENT_STATE,STUDENT_CITY,STUDENT_ADRESS,STUDENT_ZIPCODE,STUDENT_EMAIL,STUDENT_PHONE,STUDENT_PHONE_2,STUDENT_CONTRACT_TERMS,STUDENT_CREATION_DATE) "+
      "VALUES ('Andre de Souza',"+ 
      "'037.902.350-30',"+ 
      "'25',"+ 
      "'1999-05-05',"+  
      "'Brasil',"+ 
      "'SC',"+  
      "'São Lourenço d''Oeste',"+  
      "'Rua Flores da cunha, 123',"+  
      "'88106282',"+ 
      "'rp7o1mgh@mailosaur.net',"+  
      "'48 988228825',"+ 
      "null,"+ 
      "true,"+ 
      "'2023-03-25');"
    );
  });

  it("Data pre-loaded after click 'update student'", () => {

    cy.get(":nth-child(5) > #updateBtn0").click();

    cy.url().should("be.equal", "http://localhost:4200/#/UpdateStudent");

    cy.get("#name").should("have.value", "Andre de Souza");
    cy.get("#document").should("have.value", "037.902.350-30");
    cy.get("#age").should("have.value", "25");
    cy.get("#birthdate").should("have.value", "1999-05-05");
    cy.get("#country").should("have.value", "Brasil");
    cy.get("#state").should("have.value", "SC");
    cy.get("#city").should("have.value", "São Lourenço d'Oeste");
    cy.get("#adress").should("have.value", "Rua Flores da cunha, 123");
    cy.get("#zipcode").should("have.value", "88106282");
    cy.get("#email").should("have.value", "rp7o1mgh@mailosaur.net");
    cy.get("#phone").should("have.value", "48 988228825");
    cy.get("#phone_2").should("have.value", "");
    cy.get("#registrationDate").should("have.value", "2023-03-25");

    cy.get(".btn-outline-danger").click();

    cy.url().should("be.equal", "http://localhost:4200/#/Students");
  });

  it("Disable button when obligatory fields are empty", () => {

    cy.get(":nth-child(5) > #updateBtn0").click();

    cy.url().should("be.equal", "http://localhost:4200/#/UpdateStudent");

    cy.get("#name").clear();

    cy.get(".help-block").should("have.text", "Please enter with valid data");

    cy.get("#email").clear();

    cy.get("#email").type("test");

    cy.get(":nth-child(3) > :nth-child(1) > .alert > .help-block").should(
      "have.text",
      "Please enter with valid email"
    );

    cy.get(".btn-outline-primary").should("be.disabled");
  });

  it("Don't allow to updated when email is already in use", () => {
    
    cy.get(":nth-child(5) > #updateBtn0").click();

    cy.url().should("be.equal", "http://localhost:4200/#/UpdateStudent");

    cy.get("#email").clear();

    cy.get("#email").type("lucasbez14@outlook.com");

    cy.get("#email").should("have.value", "lucasbez14@outlook.com");

    cy.wait(2000);
    cy.get(".btn-outline-primary").click();

    cy.get(".alert").should(
      "contain",
      "Opss...Something is not working but we will fix soon. Please try again later"
    );

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
      expect(result[0].STUDENT_PHONE_2).to.eq(null);
      expect(result[0].STUDENT_CONTRACT_TERMS).to.eq(1);
      expect(result[0].STUDENT_CREATION_DATE).to.eq("2023-03-25");
    });
  });

  it("Updated student", () => {
    cy.get(":nth-child(5) > #updateBtn0").click();

    cy.url().should("be.equal", "http://localhost:4200/#/UpdateStudent");

    cy.get("#name").should("have.value", "Andre de Souza");
    cy.get("#document").should("have.value", "037.902.350-30");
    cy.get("#age").should("have.value", "25");
    cy.get("#birthdate").should("have.value", "1999-05-05");
    cy.get("#country").should("have.value", "Brasil");
    cy.get("#state").should("have.value", "SC");
    cy.get("#city").should("have.value", "São Lourenço d'Oeste");
    cy.get("#adress").should("have.value", "Rua Flores da cunha, 123");
    cy.get("#zipcode").should("have.value", "88106282");
    cy.get("#email").should("have.value", "rp7o1mgh@mailosaur.net");
    cy.get("#phone").should("have.value", "48 988228825");
    cy.get("#phone_2").should("have.value", "");
    cy.get("#registrationDate").should("have.value", "2023-03-25");

    cy.get("#phone_2").type("48 55664488");

    cy.get("#phone_2").should("have.value", "48 55664488");

    cy.wait(2000);

    cy.get(".btn-outline-primary").click();

    cy.get(".alert").should("contain", "Student updated with success...");

    cy.url().should("be.equal", "http://localhost:4200/#/Students");

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
      expect(result[0].STUDENT_PHONE_2).to.eq("48 55664488");
      expect(result[0].STUDENT_CONTRACT_TERMS).to.eq(1);
      expect(result[0].STUDENT_CREATION_DATE).to.eq("2023-03-25");
    });

    cy.get(':nth-child(1) > th > a').click();

    cy.get('.list-group > :nth-child(11)').should('contain', 'Phone 2: 48 55664488');

  });

});
