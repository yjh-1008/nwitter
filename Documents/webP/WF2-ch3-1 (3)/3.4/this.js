/**
 * 3.4/this.js
 */

console.log('@global');
console.log('this:', this); // {}
console.log(`this === module.exports: ${this === module.exports}`);
console.log(`this === exports: ${this === exports}`);
console.log(`this === global: ${this === global}`);
console.log(`module.exports === exports: ${module.exports === exports}\n`);

function func1(){
  console.log('@func1');
  console.log('this:', this); // global
  console.log(`this === module.exports: ${this === module.exports}`);
  console.log(`this === exports: ${this === exports}`);
  console.log(`this === global: ${this === global}`);
  console.log(`module.exports === exports: ${module.exports === exports}\n`);
}
func1();

const func2 = () => {
  console.log('@func2, arrow function');
  console.log('this:', this); // {}
  console.log(`this === module.exports: ${this === module.exports}`);
  console.log(`this === exports: ${this === exports}`);
  console.log(`this === global: ${this === global}`);
  console.log(`module.exports === exports: ${module.exports === exports}`);
}
func2();
