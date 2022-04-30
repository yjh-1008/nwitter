import React from 'react';
import {useSelector, useDispatch} from 'react-redux'
import { Link } from 'react-router-dom';
const Home=()=>{
    const dispatch= useDispatch()
    const {value}= useSelector((state)=>state.value)
    const {count} = useSelector((state)=>state.count)
  
    const addValue=()=>{
      dispatch({type:'increment'})
    }
    const subValue=()=>{
      dispatch({type:'decrement'})
    }
    const resetValue=()=>{
      dispatch({type:'reset'})
    }
    const pushButton=()=>{
      dispatch({type:'push'})
    }
    return (
      <div className="App">
        <div>value: {value}</div>
        <button onClick={addValue}> + </button>
        <button onClick={subValue}> - </button>
        <button onClick={resetValue}> reset </button>
        <div>count: {count}</div>
        <Link 
        to="/move"
        state={{
          value:value
        }}
         >das</Link>
        <button onClick={pushButton}> click </button>
      </div>
    );
}

export default Home;