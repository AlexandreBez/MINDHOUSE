const { defineConfig } = require("cypress");
const dotenvPlugin = require('cypress-dotenv');

//npx cypress run --record --key 79ca5d63-d0b9-4580-a26d-d52458dfa1bc

module.exports = defineConfig({
  projectId: 'y54kej',
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
