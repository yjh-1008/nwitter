/**
 * 3.4/console.js
 */

const string = 'abc';
const number = 1;
const boolean = true;
const obj = {
  outside: {
    inside: {
      key: 'value',
    },
  },
};
console.time('total time');
console.log('general log');
console.log(string, number, boolean);
console.error('error message from console.error');
console.table([{ name: 'zero', birth: 1994 }, { name: 'hero', birth: 1988}]);
console.dir(obj, { colors: false, depth: 2 });
console.dir(obj, { colors: true, depth: 1 });
console.time('elapsed time');
for (let i = 0; i < 100000; i++) {}
console.timeEnd('elapsed time');
function b() {
  console.trace('error trace');
}
function a() {
  b();
}
a();

console.timeEnd('total time');