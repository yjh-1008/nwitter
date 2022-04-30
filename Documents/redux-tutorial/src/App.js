
import './App.css';
import Move from './Move.js'
import Home from './Home.js'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/move" element={<Move />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
