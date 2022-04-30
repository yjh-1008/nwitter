
async function asyncFunc(value){
    if (value > 10) {
        return `Success: value is ${value}`;
    }
    else {
        throw new Error(`${value} not enough`);
    }               
}


async function b(){
    try{
        const q=await asyncFunc(40);
        console.log(q);
    }
    catch(e){
        console.error(e);
    }
}

b();