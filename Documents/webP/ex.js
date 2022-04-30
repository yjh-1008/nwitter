const fs=require('fs');
async function b(){
    fs.open('',(err)=>{
        console.log(err)
    });
}


async function a(){
    await b();
}
a();