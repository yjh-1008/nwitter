/**
 * testcode1.js 
 * This code is to substitute wf2-2-19.js and wf2-2-20.js
 * 
 * how to run:
 * $ node testcode1 __argv__
 * __argv__ : 0 - func0 for asynchronized codes
 * __argv__ : 1 - func1 for callback hell
 * __argv__ others - for promise
 */

let x = 0;

// case 0: func0 - asynchonized codes
const func0 = () => {
  console.log(`start with x=${x}`);
  setTimeout(() => {
    x = x + 10;
    console.log(`1st timeout, x=${x}`);
  }, 1000);
  setTimeout(() => {
    x = x + 100;
    console.log(`2nd timeout, x=${x}`);
  }, 1000);
  setTimeout(() => {
    x = x + 1000;
    console.log(`3rd timeout, x=${x}`);
  }, 1000);
  setTimeout(() => {
    x = x + 10000;
    console.log(`end with x=${x}`);
  }, 1000);  
};

// case 1: func1 - callback hell
const func1 = () => {
  console.log(`start with x=${x}`);
  setTimeout( () => {
    x = x + 10;
    console.log(`1st callback, x=${x}`);
    setTimeout( () => {
      x = x + 100;
      console.log(`2nd callback, x=${x}`);
      setTimeout( () => {
        x = x + 1000;
        console.log(`3rd callback, x=${x}`);
        setTimeout( () => {
          x = x + 10000;
          console.log(`end with x=${x}`);
        }, 1000);
      }, 1000);
    }, 1000);
  }, 1000);
};

// case 2: func2 - promise code
const func2 = () => {
  const promise = new Promise((resolve, reject) => {
    console.log(`start with x=${x}`);
    setTimeout(() => resolve(10), 1000);
  });

  promise
    .then((value) => {
      x = x + value;
      console.log(`1st consumer, x=${x}`);
      return new Promise((resolve, reject) => {
        setTimeout(() => resolve(100), 1000);
      });
    })
    .then((value) => {
      x = x + value;
      console.log(`2nd consumer, x=${x}`);
      return new Promise((resolve, reject) => {
        setTimeout(() => resolve(1000), 1000);
      });
    })
    .then((value) => {
      x = x + value;
      console.log(`3rd consumer, x=${x}`);
      return new Promise((resolve, reject) => {
        setTimeout(() => resolve(10000), 1000);
      });
    })
    .then((value) => {
      x = x + value;
    })
    .finally(() => {
      console.log(`end with x=${x}`);
    });
};

// user input case: 0, 1, 2
let input = process.argv[2];

if (input == 0 || input == undefined){  // async
  func0();
}
else if (input == 1){ // callback hell
  func1();
}
else {  // promise
  func2();
}