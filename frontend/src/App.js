import React, { Component } from 'react';
//import logo from './logo.svg';
//import './App.css';
import axios from 'axios';
import NavBar from './components/navbar/NavBar';
import '../node_modules/react-bootstrap-table/dist/react-bootstrap-table-all.min.css';


class App extends Component {
  render(){
    return (
      <div>
          <NavBar/>
          <div className="App">
            <div className="container">{this.props.children}</div>
          </div>
      </div>
    );
  }
}
export default App;
