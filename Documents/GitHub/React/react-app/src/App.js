import './App.css';
import React, {Component} from 'react';

class Subject extends Component {
  render(){
  return (
    <header>
      <h1>{this.props.title}</h1>
      {this.props.sub}
      </header>
    );
  }
}


class App extends Component {
  render(){
  return (
    <div className="App">
      <Subject title="WEB" sub="world wid web"></Subject>
      <Subject title="React" sub="For UI"></Subject>
      <Href title="HTML" desc="HTML is Hyper..."></Href>
      <Content></Content>
    </div>
    
    );
  }
}
class Content extends Component{
  render(){
    return(
      <article>
    <h2>{this.props.HTML}</h2>
    {this.props.desc}
    </article>
    );
  }
}
class Href extends Component{
  render(){
    return(
      <ul>
        <li><a href="1.html">HTML</a></li>
        <li><a href="2.html">CSS</a></li>
        <li><a href="3.html">JAVAScript</a></li>

    </ul>
    );
  }
}

export default App;
