import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import './index.css';
import * as serviceWorker from './serviceWorker';
import routes from "./routes";

ReactDOM.render(<BrowserRouter >{routes}</BrowserRouter >, document.getElementById("root"));
serviceWorker.unregister();

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA

