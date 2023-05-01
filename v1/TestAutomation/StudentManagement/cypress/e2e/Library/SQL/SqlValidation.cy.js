cy.task(
  "queryDb",
  "QUERY"
).then((result) => {
  expect(result[0].RESULT).to.eq("Value");
});