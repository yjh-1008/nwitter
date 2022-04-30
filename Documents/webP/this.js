var name="ju"
var obj1={
    name:"afsdfd",
    a: function(){
        console.log(this)
        return this;    
    },
    b:function(){
        const t=()=>{
            console.log(this);
            return this;
        }
        return t();
    },
    c:()=>{
        return this;
    },
    obj2:{
        
        name:"fadsasdfsd",
        d:()=>{ //this {}
            console.log(this);
            const h=()=>{ //this {}
                console.log(this);
            }
            return h();
        },
        a:function(){ //this obj2
            console.log(this); 
            function b(){//this : global
                return this;
            }
            return b();

        },
        b:function(){//this :obj2
            console.log(this);
            const t=()=>{
                console.log(this);//this : obj2
                return this.name;
            }
            return t();
        },
        c:()=>{// this : {}
            console.log(this);
            function d(){ //this : global
                console.log(this)
                return this;
            }
            return d();
        }

    }

}
const a=()=>{
    console.log(this);
    function t(){
        console.log(this);
    }
    t();
}


console.log(obj1.b());
//일반 함수 나를 호출한 것
//arrow에서는 상위 스코프
//console.log(obj1.obj2.b())
//console.log(obj1.a())// 콘솔에서 입한 obj1에서 a를 호출하였기 때문에 this는 obj
//console.log(obj1.obj2.a())// 콘솔에서 입한 obj1에서 a를 호출하였기 때문에 this는 obj
//객체 안에서의 console.log는 obj2가 호출한 것이기 때문에 obj2를 반환하지만 obj1.ob
//arrow 함수는 누가 자신의 상위 스코프를 호출
//obj1.obj2.b()의 arrow function은 obj2가 상위 스코프이기 때문에 obj2를 this로 갖는다.
//arrow function은 기본적으로 전역객체를 가리키고 function안에 있으면 function이 가리키는 객체를 갖는다.
//console.log(obj1.obj2.c())