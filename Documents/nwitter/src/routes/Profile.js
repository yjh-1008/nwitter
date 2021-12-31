import { authService, dbService } from 'fbase';
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

const Profile=({refreshUser,userObj})=>{
    const history=useHistory();
    const onLogOutClick=()=>{
        authService.signOut();
        history.push("/");
        refreshUser();
    }
    const [newDisplayName, setNewDisplayName]=useState(userObj.displayName);
    const getMyNweets=async ()=>{
        const nweets = await dbService
        .collection("nweets")
        .where("creatorId","==",userObj.uid)
        .orderBy("createdAt"
        ).get();
        console.log(nweets.docs.map((doc)=>doc.data()));
    };

    const onSubmit=async(event)=>{
        event.preventDefault();
        if(userObj.displayName !== newDisplayName){
            console.log("changed");
            await userObj.updateProfile({
                displayName: newDisplayName,
            });
            refreshUser();
        }
    };
    const onChange = (event) => {
        const {
          target: { value },
        } = event;
        setNewDisplayName(value);
      };
    return(
        <div className="container">
            <form onSubmit={onSubmit} className="profileForm">
            <input
            onChange={onChange}
            type="text"
            autoFocus
            placeholder="Display Name"
            value={newDisplayName}
            />

            <input
            type="submit"
            value="Update Profile"
            className="formBtn"
            style={{
                marginTop: 10,
            }}
            />
        </form>
        <span className="formBtn cancelBtn logOut" onClick={onLogOutClick}>
            Log Out
        </span>
       </div>
    );
};

export default Profile;