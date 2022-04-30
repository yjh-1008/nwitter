/**
 * testcode2.js 
 * This code is to substitute wf2-2-20.js and wf2-2-22.js
 * 
 * how to run:
 * $ node testcode1 __argv__
 * __argv__ : 0 (or ommitted) - func() and start0() for promise
 * __argv__ : 1 (or others)   - asyncFunc() and start1() for async/await 
 */

// case 0: for promise - start0()
function func(value){
    return new Promise((resolve, reject) => {
        if (value > 10) {
            resolve(`Success: value is ${value}`);
        }
        else {
            reject (new Error(`${value} not enough`));
        }               
    });
}

function start0(){
    setTimeout(() => {
        const promise1 = func(30);
        promise1
        .then(msg => console.log(msg))
        .catch(error => console.error(error))
    
        const promise2 = func(5);
        promise2
        .then(msg => console.log(msg))
        .catch(error => console.error(error))
    }, 1000);
}



// case 1: for async/await - start1()
function delay(ms){
    return new Promise((resolve, reject) => {
        setTimeout(() => resolve(), ms);
    });
}

async function asyncFunc(value){
    if (value > 10) {
        return `Success: value is ${value}`;
    }
    else {
        throw new Error(`${value} not enough`);
    }               
}

async function start1(){
    await delay(1000);
    try {
        const promise1 = await asyncFunc(30);
        console.log(promise1);
    }
    catch (e){
        console.error(e);
    }

    try {
        const promise2 = await asyncFunc(5);;
        console.log(promise2);
    }
    catch (e){
        console.error(e);
    }
}

// user input case: 0, 1
let input = process.argv[2];
if (input == 0 || input == undefined){
    start0();
}
else {
    start1();
}