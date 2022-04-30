
/*function to(){
    return new Promise((resolve, reject),()=>{
        setTimeout(()=>{
            console.log("1초 경과")
            resolve();
        },1000)
    })
}

function to(){
    return new Promise((resolve, reject),()=>{
        setInterval(()=>{
            console.log("1초 경과")
            resolve();
        },1000)
    })
}
async function as(){
    try{
        await to();
        await wait();
    }catch(err){
        console.log(err);
    }
}*/

const wait = ()=>{
    return new Promise((resolve,reject)=>{
        setTimeout(()=>{
            console.log('1초 경과')
        },1000);
        resolve();
    })
}


const interval = ()=>{
    console.log('come here')
    return new Promise((resolve,reject)=>{
        setInterval(()=>{
            console.log('1초 ')
        },1000);
    })
}

wait()
    .then((msg)=>{
    return interval();
}).then(()=>{console.log('성공')})