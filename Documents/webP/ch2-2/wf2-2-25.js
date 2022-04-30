/**
 * wf2-2-25.js
 */

const promise1 = Promise.resolve('Success1');
const promise2 = Promise.resolve('Success2');
(async () => {
  for await (promise of [promise1, promise2]) {
    console.log(promise);
  }
})();
