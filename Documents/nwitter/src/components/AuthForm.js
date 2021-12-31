import React,{useState} from 'react';
import { authService } from 'fbase';
const inputStyles={};
const AuthFrom=()=>{
    const [email, setEmail]= useState('');
    const [password, setPassword]= useState ('');
    const [error,setError]=useState("");
    const [newAccount, setNewAccount]= useState(true);
    const onChange=(event)=>{
        const {target:{name,value}}=event;
        if(name==="email"){
            setEmail(value);
        }else{
            setPassword(value);
        }
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
    const toggleAccount=()=>{
        setNewAccount((prev)=>!prev);
    }   
    return(
        <>
        <form onSubmit={onSubmit} className="container">
                <input
                name="email"
                type="text"
                placeholder="Email"
                required value={email}
                onChange={onChange}
                className="authInput"
                />
                <input
                name="password"
                type="password"
                placeholder='password'
                required value={password}
                onChange={onChange}
                className="authInput"
                />
                <input 
                type="submit"
                className="authInput authSubmit"
                value={newAccount ? "Create Account":"Log in"}/>
               {error && <span className="authError">{error}</span>}<br></br>
            </form>
            <span onClick={toggleAccount} className="authSwitch">
            {newAccount ? "Sign In" : "Create Account"}
          </span>
          </>
    );
};

export default AuthFrom;