import React, { Component } from 'react';
//import logo from './logo.svg';
//import './App.css';
import axios from 'axios';
import NavBar from './NavBar/NavBar';
import Login from './Login/Login';

class App extends Component {
  state = {
    "token": ""
  };
  componentDidMount() {
    axios.post('/auth',{
      "username": "Ehvan",
      "password": "test"
    })
      .then(response => {
        this.setState({token: response.headers.authorization})
        return response.headers.authorization;
      })
      .then(() => axios.get('/nwtUpload/ucenici',{
        headers: {
          Authorization: 'Bearer ' + this.state.token 
        }
       }))
       .then(response => console.log(response))
      .catch(err => console.log(err));
  }
  render(){
    return (
      <div>
          <NavBar/>
          <Login/>
          {this.state.token}
      </div>
    );
  }
 
}

export default App;
