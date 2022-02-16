import React,{useState, useEffect,useRef} from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';

const useTitle=(initialTitle)=>{
  const [title, setTitle]=useState(initialTitle);
  const updateTitle=()=>{
    const htmlTitle = document.querySelector("title");
    htmlTitle.innerText= title;
  }
  useEffect(updateTitle, [title]);
  return setTitle;
}

const App=()=>{
  const titleUpdater=useTitle("loading");
  setTimeout(()=>titleUpdater("home"),5000);
  const potato= useRef();
  return(
    <div>
      <div>h1</div>
      <input ref={potato} placeholder="hi"/>
    </div>
  );
}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
