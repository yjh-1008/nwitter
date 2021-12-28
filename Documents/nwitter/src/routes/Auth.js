import { authService } from 'fbase';
import React,{useState} from 'react';
import { firebaesInstance } from 'fbase';
const Auth=()=>{
    const [email, setEmail]= useState('');
    const [password, setPassword]= useState ('');
    const [newAccount, setNewAccount]= useState(true);
    const [error,setError]=useState("");
    const onChange=(event)=>{
        const {target:{name,value}}=event;
        if(name==="email"){
            setEmail(value);
        }else{
            setPassword(value);
        }
    }

    const toggleAccount=()=>{
        setNewAccount((prev)=>!prev);
    }
    const onSocialClick=async(event)=>{
        event.preventDefault();
        const {target:{name}}=event;
        let provider;
        if(name==="google"){
            provider=new firebaesInstance.auth.GoogleAuthProvider();
        }else if(name==="github"){
            provider = new firebaesInstance.auth.GithubAuthProvider();
        }
        const data=await authService.signInWithPopup(provider);
        console.log(data);
    }
    const onSubmit=async(event)=>{
        event.preventDefault();
        try{
            if(newAccount){
                //create account
                const data=await authService.createUserWithEmailAndPassword(
                    email,
                    password
                    );
                console.log(data);
            }else{
                //log in
                const data=
                    await authService.signInWithEmailAndPassword(
                        email,
                        password
                    );
                console.log(data);
            }
        }catch(error){
            setError(error);
        }
    }
    return(
        <div>
            <form onSubmit={onSubmit}>
                <input
                name="email"
                type="text"
                placeholder="Email"
                required value={email}
                onChange={onChange}/>
                <input
                name="password"
                type="password"
                placeholder='password'
                required value={password}
                onChange={onChange}/>
                <input 
                type="submit"
                value={newAccount ? "Create Account":"Log in"}/>
                {error}<br></br>
            </form>
            <span onClick={toggleAccount}>
                    {newAccount?"Sign In":"Create Account"}
            </span><br/>
            <button name="google" onClick={onSocialClick}>Continue with Google</button>
            <button name="github" onClick={onSocialClick }>Continue with Github</button>
        </div>
    );
};

export default Auth;