import React, { Component } from 'react';
import {Link} from 'react-router-dom';

class NavBar extends Component{
  logout(e) {
    console.log("LOGOUT")
    localStorage.clear();
    window.location.href = '/';
  }
	render() {
    return (
      <nav className="navbar navbar-dark bg-primary fixed-top">
        <Link className="navbar-brand" to="/">
          Online dnevnik
        </Link>
        <button className="btn btn-primary" onClick={e => this.logout(e)}>
          Log out
        </button>
      </nav>
    );
  }
}

export default NavBar;