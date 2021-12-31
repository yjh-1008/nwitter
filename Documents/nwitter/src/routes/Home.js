import React, { useEffect } from 'react';
import { useState } from 'react/cjs/react.development';
import { dbService, strageService } from "fbase";
import { firebaesInstance } from 'fbase';
import {v4 as uuidv4} from "uuid";
import Nweet from 'components/Nweet';
import NweetFactory from 'components/NweetFactory';
const Home=({userObj})=>{
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
    
    return(
        <div className="container">
            <NweetFactory userObj={userObj}/>
            <div style={{ marginTop: 30 }}>
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