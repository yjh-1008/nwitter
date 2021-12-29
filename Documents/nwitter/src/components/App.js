import React, { useEffect, useState } from 'react';
import AppRouter from 'components/Router';
import {authService} from 'fbase';
function App() {
  const [init,setInit] = useState(false);
  const [isLoggedIn, setIsLoggedIn]= useState(false);
  const [userObj, setUserObj]= useState(null);
  //const auth= firebase.auth();
  useEffect(()=>{
    authService.onAuthStateChanged((user)=>{
      if(user){
        setUserObj(user);
      }else{
        setIsLoggedIn(false);
      }
      setInit(true);
    }
    );
  },[]);
  return (
    <>
      
        {init?<AppRouter isLoggedIn={Boolean(userObj)} userObj={userObj}/>:"initializing"}<br></br>
        <div>&copy; {new Date().getFullYear()} Nwitter </div>
    </>
  );
}

export default App;
