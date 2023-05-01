const mysql = require("mysql");
const dotenv = require("dotenv");
const MailosaurClient = require('mailosaur');

dotenv.config({ path: "ThirdPartInfos.env" });

const mailosaurTask = async (options) => {
  const client = new MailosaurClient(process.env.MAILOSAUR_API_KEY);

  console.log('MAILOSAUR_API_KEY:', process.env.MAILOSAUR_API_KEY);
  console.log('MAILOSAUR_SERVER_ID:', process.env.MAILOSAUR_SERVER_ID);

  const { emailAddress, subject } = options;

  const messages = await client.messages.search(
    process.env.MAILOSAUR_SERVER_ID,
    {
      sentTo: emailAddress,
      subject: subject,
    }
  );

  if (messages.items.length === 0) {
    throw new Error(`No email found for "${subject}" sent to "${emailAddress}"`);
  }

  const email = await client.messages.getById(messages.items[0].id);

  return {
    subject: email.subject,
    text: email.text.body,
    html: email.html.body,
  };
};

function queryTestDb(query, config) {
  // creates a new mysql connection using credentials from process.env
  const connection = mysql.createConnection({
    host: process.env.DB_HOST,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
  });

  // start connection to db
  connection.connect();
  // exec query + disconnect to db as a Promise
  return new Promise((resolve, reject) => {
    connection.query(query, (error, results) => {
      if (error) reject(error);
      else {
        connection.end();
        return resolve(results);
      }
    });
  });
}

module.exports = {
  projectId: "8b8ysw",
  e2e: {
    setupNodeEvents(on) {
      on("task", {
        queryDb(query) {
          return queryTestDb(query, process.env);
        },
        mailosaur: mailosaurTask,
      });
    },
    excludeSpecPattern: [
      "cypress/e2e/Library"
    ]
  },  
  component: {
    devServer: {
      framework: "angular",
      bundler: "webpack",
    },
    specPattern: "**/*.cy.ts",
  },
  defaultCommandTimeout: 5000,
  video: true,
  viewportHeight: 800,
  viewportWidth: 1200,
};
