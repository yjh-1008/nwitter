import { dbService, strageService } from 'fbase';
import React, { useEffect } from 'react';
import { useState } from 'react/cjs/react.development';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faPencilAlt } from "@fortawesome/free-solid-svg-icons";
const Nweet=({nweetObj, isOwner})=>{
    const [editing, setEditing] =useState(false);
    const [newNweet, setNewNweet]= useState(nweetObj.text);
    const onDeleteClick=async()=>{
        const ok= window.confirm("Are you sure you want to delete this nweet?");
        if(ok){
            await dbService.doc(`nweets/${nweetObj.id}`).delete();
            await strageService.refFromURL(nweetObj.attachmentUrl).delete(); 
        }
    };
    const onSubmit=async(event)=>{
        event.preventDefault();
        await dbService.doc(`nweets/${nweetObj.id}`).update({
            text:newNweet,
        });
        setEditing(false);
    };
    
    const onChange=(event)=>{
        event.preventDefault();
        setNewNweet(event.target.value);
    };
    const toggleEditing=()=>setEditing((prev)=>!prev);
    return(
        <div>
        <div className="nweet">
        {
            editing?
            <>
            <form onSubmit={onSubmit} className="container nweetEdit"><input
            type="text"
            placeholder='Eidt your nweet'
            autoFocus
            className="formInput"
            value={newNweet}
            required
            onChange={onChange}/>
            <input type="submit" value="Update Nweet" className="formBtn"   /> 
            </form>
            <span onClick={toggleEditing} className="formBtn cancelBtn">
            Cancel
          </span>
            </>
            :
            <>
            <h4>{nweetObj.text}</h4>
            {nweetObj.attachmentUrl && <img src={nweetObj.attachmentUrl}/>}
            {isOwner &&
            (
                <div className="nweet__actions">
                    <span onClick={onDeleteClick}>
                        <FontAwesomeIcon icon={faTrash} />
                    </span>
                    <span onClick={toggleEditing}>
                        <FontAwesomeIcon icon={faPencilAlt} />
                    </span>
                </div>
            )}
            </>
        }
        </div>
        </div>
    );
};

export default Nweet;