/**
 * 3.4/this_testcode1.js
 */

firstname = 'k';
const obj = {
    firstname : 's',
    getFirstname : function(){
        console.log(this);  // this: obj
        return this.firstname;
    },
    getFirstnameArr : () => {
        console.log(this);  // this: {}
        return this.firstname;
    },
    getFirstnameES6(){
        console.log(this);  // this: obj
        return this.firstname;
    },
};

console.log(obj.firstname);
console.log(obj.getFirstname());
console.log(obj.getFirstnameArr());
console.log(obj.getFirstnameES6());
