import React from 'react';
import {Link} from "react-router-dom";
const Navigation=({userObj})=>{
    return(
        <>
            <ul>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="/profile">{userObj.displayName}의 prifile </Link>
                </li>   
            </ul>
        </>
    );
};

export default Navigation;