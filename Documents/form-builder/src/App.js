
import './App.css';
import React,{useRef} from 'react';
import CanvasContainer from './CanvasContainer.tsx';
function App() {
  const canvas = useRef(null); 
  return (
    <div className="App">
     <CanvasContainer/>
    </div>
  );
}

export default App;
