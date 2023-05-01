cy.task("mailosaur", {
  emailAddress: "EMAIL",
  subject: "EMAIL SUBJECT",
}).then((email) => {
  expect(email.subject).to.equal(
    "EMAIL SUBJECT"
  );
  expect(email.html).to.contain("HTML BODY");
})