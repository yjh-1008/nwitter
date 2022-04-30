/* 3.4/exit.js */

let i = 1;
// setInterval(() => {
//   if (i === 5) {
//     console.log('terminate!');
//     process.exit();
//   }
//   console.log(i);
//   i += 1;
// }, 10000);


const func = () => {
  if (i === 5) {
    console.log('terminate!');
    process.exit();
  }
  console.log(i);
  i += 1;
}

setInterval(func, 3000);
