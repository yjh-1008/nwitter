import React, { useEffect } from 'react';
import { useState } from 'react/cjs/react.development';
import { dbService } from "fbase";
import { firebaesInstance } from 'fbase';
import Nweet from 'components/Nweet';
const Home=({userObj})=>{
    const [nweet,setNweet]= useState('');
    const [nweets, setNweets]= useState([]);
 
    useEffect(()=>{
        dbService.collection("nweets").onSnapshot(snapshot=>{
            const nweetsArray=snapshot.docs.map(doc=>({
                id:doc.id,
                ...doc.data()
            }));
            setNweets(nweetsArray);
        });
        
    },[]);
    
    const onSubmit=async(event)=>{
        event.preventDefault();
        await dbService.collection("nweets").add({
            text:nweet,
            createdAt:Date.now(),
            creatorId: userObj.uid,
        });
        setNweet("");
    };


    const onChange=(event)=>{
        const {target:{value}}=event;
        console.log(value);
        setNweet(value);
    };
    return(
        <div>
            <form onSubmit={onSubmit}>
                <input
                onChange={onChange}
                type="text"
                value={nweet}
                placeholder="What's on your mind?"
                maxLength={120}/>

                <input
                type="submit"
                value="Nweet"/>

            </form>
            <div>
                {nweets.map(nweet=>
                    <Nweet key={nweet.id}
                    nweetObj={nweet}
                    isOwner={nweet.creatorId===userObj.uid}/>
                    )}
            </div>
        </div>
    );
};

export default Home;