import { render } from '@testing-library/react';
import React,{useState,useEffect} from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, NavLink } from 'react-router-dom';
import { Route, Switch,Link,useParams } from 'react-router-dom';
import { HashRouter } from 'react-router-dom/cjs/react-router-dom.min';
import './App.css';

class ClassComp extends React.Component{
    state={
        number:this.props.initNumber,
        date:(new Date()).toString()
    };
    componentWillMount(){
        console.log('component will mount');
    }
    render(){
            return(
                <div className="container">
                        <h2>class style component</h2>
                        <p>Number is {this.state.number}</p>
                        <p>{this.state.date}</p>
                        <input type="button" value="random" onClick={
                            function(){
                                this.setState({number:Math.random()})
                            }.bind(this)
                        }/>
                        <input type="button" value="date" onClick={
                            function(){
                                this.setState({date:(new Date()).toString()})
                            }.bind(this)
                        }
                        />
                        
                </div>
            );
        
    };
}

const FuncComp = (props)=>{
    const [number, setNumber] =useState(props.initNumber);
    const [date,setDate] = useState(new Date().toString());
    const dateChange=()=>{
        setDate(date=>new Date().toString());
    }
    useEffect(function(){
        document.title=date;
    })
    return(
        <div className="container">
                <h2>function style component</h2>
                <p>Number is : {number}</p>
                <p>Date : {date}</p>
                <input type="button" value="random" onClick={()=>{
                    setNumber(number=>Math.random());
                }}/>
                <input type="button" value="date" onClick={dateChange}/>
        </div>
    );
}

const App=()=> {
    return (
      <div className="container">
        hello world
        <FuncComp initNumber={2}/>
        <ClassComp initNumber={2}/>
      </div>
    );
  }

  export default App;