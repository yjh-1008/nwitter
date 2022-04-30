import React from 'react';
import {useSelector, useDispatch} from 'react-redux'
import { useLocation } from 'react-router-dom';
const Move=()=>{
    const location =useLocation();
    
    console.log(location.state)
    return(
        <div>
            {location.state.value}
        </div>
    )
}

export default Move;