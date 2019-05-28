import React, { Component } from 'react';
//import logo from './logo.svg';
//import './App.css';
import axios from 'axios';
import NavBar from './components/navbar/NavBar';
import '../node_modules/react-bootstrap-table/dist/react-bootstrap-table-all.min.css';


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
          <div className="App">
            <div className="Container">{this.props.children}</div>
          </div>
      </div>
    );
  }
}
export default App;
