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
        setUserObj({
          displayName:user.displayName,
          uid:user.uid,
          updateProfile:(args)=>user.updateProfile(args),
        });
      }else{
        setIsLoggedIn(false);
      }
      setInit(true);
    }
    );
  },[]);
  const refreshUser=()=>{
    const user= authService.currentUser;
    setUserObj({
      displayName:user.displayName,
          uid:user.uid,
          updateProfile:(args)=>user.updateProfile(args),
    });
  };
  return (
    <>
      
        {init?<AppRouter
        refreshUser={refreshUser}
         isLoggedIn={Boolean(userObj)}
          userObj={userObj}/>:"initializing"}<br></br>
        
    </>
  );
}

export default App;
