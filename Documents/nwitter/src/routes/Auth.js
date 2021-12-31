import { authService } from 'fbase';
import React,{useState} from 'react';
import { firebaesInstance } from 'fbase';
import AuthFrom from 'components/AuthForm';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faTwitter,
  faGoogle,
  faGithub,
} from "@fortawesome/free-brands-svg-icons";
const Auth=()=>{
    
    const [newAccount, setNewAccount]= useState(true);
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
    
    return(
        <div className="authContainer">
            <FontAwesomeIcon
            icon={faTwitter}
            color={"#04AAFF"}
            size="3x"
            style={{ marginBottom: 30 }}
            />
            <AuthFrom/>
            <span onClick={toggleAccount}>
                    {newAccount?"Sign In":"Create Account"}
            </span><br/>
            <div className="authBtns">
                <button onClick={onSocialClick} name="google" className="authBtn">
                Continue with Google <FontAwesomeIcon icon={faGoogle} />
                </button>
                <button onClick={onSocialClick} name="github" className="authBtn">
                Continue with Github <FontAwesomeIcon icon={faGithub} />
                </button>
            </div>
        </div>
    );
};

export default Auth;