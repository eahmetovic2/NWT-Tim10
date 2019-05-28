import React, {Component} from 'react';
import {withRouter} from 'react-router-dom';
import axios from 'axios';

class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      disabled: false,
      username: '',
      password: '',
      token: ''
    };
  }

  updateUsername(value) {
    this.setState({
        username: value,
    });
  }

  updatePassword(value) {
    this.setState({
        password: value,
    });
  }

  async submit() {
    this.setState({
      disabled: true,
    });

    await axios.post('/auth', {
        "username": this.state.username,
        "password": this.state.password
    })
      .then(response => {
        this.setState({token: response.headers.authorization})
        return response.headers.authorization;
      })
      .catch(err => console.log(err));

    this.props.history.push('/');
  }

  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-12">
            <div className="card border-primary">
              <div className="card-header">Login</div>
              <div className="card-body text-left">
                <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Username:</label>
                  <input
                    disabled={this.state.disabled}
                    type="text"
                    onBlur={(e) => {this.updateUsername(e.target.value)}}
                    className="form-control"
                    placeholder="Username"
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Password:</label>
                  <input
                    disabled={this.state.disabled}
                    type="text"
                    onBlur={(e) => {this.updatePassword(e.target.value)}}
                    className="form-control"
                    placeholder="Password"
                  />
                </div>
                <button
                  disabled={this.state.disabled}
                  className="btn btn-primary"
                  onClick={() => {this.submit()}}>
                  Submit
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

export default withRouter(Login);