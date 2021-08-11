const player={
    name:"yjh",
    age:24,
    pr :function(name,age){
        console.log(name,age);
    }
};
function plus(user){
    console.log(user.age);
    return user.age;
}
let a=plus(player);
console.log(a);
player.pr(player.name,player.age);