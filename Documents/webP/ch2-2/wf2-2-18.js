/**
 * wf2-2-18.js
 */

const condition = true; // true면 resolve, false면 reject
const promise = new Promise((resolve, reject) => {
  if (condition) {
    resolve('Success');
  } else {
    reject('Fail');
  }
});

// 다른 코드가 들어갈 수 있음

promise
  .then((message) => {
    console.log(message + " at message1");
    return new Promise((resolve, reject) => {
      resolve(message);
    });
  })
  .then((message2) => {
    console.log(message2 + " at message2");
    return new Promise((resolve, reject) => {
      resolve(message2);
    });
  })
  .then((message3) => {
    console.log(message3 + " at message3");
  })
  .catch((error) => {
    console.error(error);
  });
