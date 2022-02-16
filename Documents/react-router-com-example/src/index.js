import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, NavLink } from 'react-router-dom';
import { Route, Switch,Link,useParams } from 'react-router-dom';
import { HashRouter } from 'react-router-dom/cjs/react-router-dom.min';
import './index.css';
import App from './App.js';



ReactDOM.render(
  <HashRouter>
    <App />
    </HashRouter>,
  document.getElementById('root')
);
