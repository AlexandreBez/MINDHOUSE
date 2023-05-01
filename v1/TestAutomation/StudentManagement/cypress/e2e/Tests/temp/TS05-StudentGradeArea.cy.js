describe("Student grade", () => {

    beforeEach(() => {
      cy.visit("http://localhost:4200/#/Students");
    });
  
    it("Search student grade(positive)", () => {
  
      cy.get('#manageBtn2').click();
  
      cy.get('.nav > :nth-child(3) > .nav-link').click();
  
      cy.url().should('be.equal', 'http://localhost:4200/#/StudentManagement/StudentGrades');
  
      cy.get('.form-control').type("Java");
  
      cy.get('.input-group > .btn').click();
  
      cy.get('app-student-grades > .container').should('contain', 'Java Web Developer');
      
    })

    it("Search student grade(negative)", () => {
  
      cy.get('#manageBtn2').click();
  
      cy.get('.nav > :nth-child(3) > .nav-link').click();
  
      cy.url().should('be.equal', 'http://localhost:4200/#/StudentManagement/StudentGrades');
  
      cy.get('.form-control').type("test");
  
      cy.get('.input-group > .btn').click();
  
      cy.get('h1').should('have.text', 'No records found...');
      
    })
  
    it("Get student grade information", () => {
  
      cy.get('#manageBtn2').click();
  
      cy.get('.nav > :nth-child(3) > .nav-link').click();
  
      cy.url().should('be.equal', 'http://localhost:4200/#/StudentManagement/StudentGrades');
  
      cy.get('.form-control').type("Java");
  
      cy.get('.input-group > .btn').click();
  
      cy.get('app-student-grades > .container').should('contain', 'Java Web Developer');
      
      cy.get('.mb-3 > .btn-dark').click();
  
      cy.get('.list-group > :nth-child(1)').should('contain', 'Teacher: Andre De Souza');
      cy.get('.list-group > :nth-child(2)').should('contain', 'First note: 8');
      cy.get('.list-group > :nth-child(3)').should('contain', 'Second note: 7.5');
      cy.get('.list-group > :nth-child(4)').should('contain', 'Third Note: 3.5');
      cy.get('.list-group > :nth-child(5)').should('contain', 'Additional note: 1');
      cy.get('.list-group > :nth-child(6)').should('contain', 'Final Note: 7.33');
      cy.get('.list-group > :nth-child(7)').should('contain', 'Status: Passed');
  
      cy.task(
        "queryDb",
        "SELECT o.course_name AS COURSE,t.teacher_name AS TEACHER,e.first_note AS FIRST,"
        + "e.second_note AS SECOND,e.third_note AS THIRD,e.additional_note AS ADDITIONAL,"
        + "e.final_note AS FINAL,e.status AS STATUS,c.classroom_start_date AS START,c.classroom_end_date AS END\r\n"
              + "FROM Grade e\r\n"
              + "INNER JOIN Classroom c ON e.fk_classroom = c.classroom_id\r\n"
              + "INNER JOIN Teacher t ON c.fk_teacher = t.teacher_id\r\n"
              + "INNER JOIN Course o ON c.fk_course = o.course_id\r\n"
              + "WHERE o.course_name = 'Java Web Developer' AND e.fk_student = '2'"
      ).then((result) => {
        expect(result[0].COURSE).to.eq("Java Web Developer");
        expect(result[0].TEACHER).to.eq("Andre De Souza");
        expect(result[0].FIRST).to.eq(8.00);
        expect(result[0].SECOND).to.eq(7.50);
        expect(result[0].THIRD).to.eq(3.50);
        expect(result[0].ADDITIONAL).to.eq(1.00);
        expect(result[0].FINAL).to.eq(7.33);
        expect(result[0].STATUS).to.eq("Passed");
        expect(result[0].START).to.eq("2023-03-10");
        expect(result[0].END).to.eq("2023-03-28");
      });
  
    })
  
    it("Check grade and email informations", () => {
  
      cy.get('#manageBtn2').click();
  
      cy.get('.nav > :nth-child(3) > .nav-link').click();
  
      cy.url().should('be.equal', 'http://localhost:4200/#/StudentManagement/StudentGrades');
  
      cy.get(':nth-child(3) > :nth-child(1) > .btn-dark').click();
  
      cy.wait(2000);
      cy.get('#modal0 > .card > .list-group > :nth-child(8) > .btn').click();
  
      cy.get('.alert').should('contain', 'Email sent with success...')
    
      cy.task("mailosaur", {
        emailAddress: "rp7o1mgh@mailosaur.net",
        subject: "Grade information: Java Web Developer - DON'T REPLY",
      }).then((email) => {
        expect(email.subject).to.equal(
          "Grade information: Java Web Developer - DON'T REPLY"
        );
      })
  
    })
  
  });