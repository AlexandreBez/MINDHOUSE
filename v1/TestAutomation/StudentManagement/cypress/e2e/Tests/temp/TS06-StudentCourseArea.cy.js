describe("Management Student Area(Student Courses)", () => {

    beforeEach(() => {
      cy.visit("http://localhost:4200/#/Students");
    });
  
    it("Select and return to student area", () => {

      cy.get("#manageBtn2").click();
  
      cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");
  
      cy.get("h3").should("have.text", "Student Management: Maria Souza de Lima");
  
      cy.get(".me-1 > .text-warning").click();
  
      cy.url().should("be.equal", "http://localhost:4200/#/Students");

    });
  
    it("Check if courses exist", () => {

      cy.get("#manageBtn2").click();
  
      cy.url().should("be.equal", "http://localhost:4200/#/StudentManagement");
  
      cy.get(".nav > :nth-child(2) > .nav-link").click();
  
      cy.url().should(
        "be.equal",
        "http://localhost:4200/#/StudentManagement/StudentCourse"
      );
  
      cy.get("app-student-course > .container")
        .should("contain", "Angular")
        .and("contain", "Java Web Developer");
  
      cy.task(
        "queryDb",
        "SELECT c.COURSE_NAME AS COURSENAME FROM COURSE c INNER JOIN Student_Classroom_Course e ON c.COURSE_ID = e.fk_course WHERE e.fk_student = 2 ORDER BY c.COURSE_NAME;"
      ).then((result) => {
        expect(result[0].COURSENAME).to.eq("Angular");
        expect(result[1].COURSENAME).to.eq("Java Web Developer");
      });

    });
  
    it("Search Student Course by name(positive)", () => {

      cy.get("#manageBtn2").click();
  
      cy.get(".nav > :nth-child(2) > .nav-link").click();
  
      cy.get(".form-control").type("Ang");
  
      cy.get(".input-group > .btn").click();
  
      cy.get("app-student-course > .container").should("contain", "Angular");

    });
  
    it("Search Student Course by name(negative)", () => {

      cy.get("#manageBtn2").click();
  
      cy.get(".nav > :nth-child(2) > .nav-link").click();
  
      cy.get(".form-control").type("Test");
  
      cy.get(".input-group > .btn").click();
  
      cy.get(".d-flex > h1").should("contain", "No records found...");

    });
  
    it("Validate course can't be cancel", () => {

      cy.get("#manageBtn2").click();
  
      cy.get(".nav > :nth-child(2) > .nav-link").click();
  
      cy.get(":nth-child(2) > :nth-child(7) > .btn").should("be.disabled");

    });
  
    it("Validate course info and email with info", () => {

      cy.get("#manageBtn2").click();
  
      cy.get(".nav > :nth-child(2) > .nav-link").click();
  
      cy.get("#modal_info").should("not.be.visible");
  
      cy.get(":nth-child(1) > :nth-child(1) > .btn").click();
  
      cy.get("#modal_info").should("be.visible");
  
      cy.get("#exampleModalLabe2").should("have.text", "Teacher: Andre De Souza");
      cy.get(".list-group > :nth-child(1)").should("contain", "Course: Angular");
      cy.get(".list-group > :nth-child(2)").should(
        "contain",
        "Field: Web Developer"
      );
      cy.get(".list-group > :nth-child(3)").should("contain", "Price: $200.00");
      cy.get(".list-group > :nth-child(4)").should("contain", "Modality: Online");
      cy.get(".list-group > :nth-child(5)").should(
        "contain",
        "Period: Afternoon"
      );
      cy.get(".list-group > :nth-child(6)").should(
        "contain",
        "Start time: 03:00 PM"
      );
      cy.get(".list-group > :nth-child(7)").should(
        "contain",
        "End Time: 06:00 PM"
      );
      cy.get(".list-group > :nth-child(8)").should(
        "contain",
        "Start Date: 04/01/2023"
      );
      cy.get(".list-group > :nth-child(9)").should(
        "contain",
        "End Date: 04/25/2023"
      );
  
      cy.get(
        "#modal_info > .modal-dialog > .modal-content > .modal-footer > .btn"
      ).click();
  
      cy.wait(20000);
  
      cy.task("mailosaur", {
        emailAddress: "rp7o1mgh@mailosaur.net",
        subject: "Your course Info - DON'T REPLY",
      }).then((email) => {
        expect(email.subject).to.equal("Your course Info - DON'T REPLY");
      });
    });
  
    it("Cancel course", () => {
      cy.get("#manageBtn3").click();
  
      cy.get(".nav > :nth-child(2) > .nav-link").click();
  
      cy.get(":nth-child(1) > :nth-child(7) > .btn").click();
  
      cy.get("#exampleModal").should("be.visible");
  
      cy.get("#exampleModalLabel").should("contain", "Delete Course: Angular");
  
      cy.get("#exampleModal > .modal-dialog > .modal-content > .modal-body")
        .should("contain", "You really want to delete the course register?")
        .and("contain", "Action can't be undone after student confirmation");
  
      cy.wait(2000);
      cy.get(".modal-footer > .btn-danger").click();
  
      cy.get("#successMessage").should('contain', 'Course canceled with success...');
  
      cy.get('app-student-course > .container').should('not.contain', 'Angular');
  
      cy.task(
        "queryDb",
        "SELECT student_classroom_course_id,fk_student,fk_classroom,fk_course FROM student_classroom_course WHERE student_classroom_course_id = 13;"
      ).then((result) => {
        expect(result[0].COURSENAME).to.eq("");
      });
  
      cy.wait(20000);
  
      cy.task("mailosaur", {
        emailAddress: "rp7o1mgh@mailosaur.net",
        subject: "Course Angular canceled with success - DON'T REPLY",
      }).then((email) => {
        expect(email.subject).to.equal(
          "Course Angular canceled with success - DON'T REPLY"
        );
      });
    });
  
  });
  