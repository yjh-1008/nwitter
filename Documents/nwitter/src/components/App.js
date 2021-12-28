import React, { useEffect, useState } from 'react';
import AppRouter from 'components/Router';
import {authService} from 'fbase';
function App() {
  const [init,setInit] = useState(false);
  const [isLoggedIn, setIsLoggedIn]= useState(false);
  //const auth= firebase.auth();
  useEffect(()=>{
    authService.onAuthStateChanged((user)=>{
      if(user){
        setIsLoggedIn(true);
      }else{
        setIsLoggedIn(false);
      }
      setInit(true);
    }
    );
  },[]);
  return (
    <>
      
        {init?<AppRouter isLoggedIn={isLoggedIn}/>:"initializing"}<br></br>
        <fotter>&copy; {new Date().getFullYear()} Nwitter </fotter>
    </>
  );
}

export default App;
