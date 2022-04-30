/**
 * 3.4/globalB.js
 */

const A = require('./globalA');

global.message = 'hello';
console.log(A());
