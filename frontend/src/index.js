import React from "react";
import ReactDOM from "react-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./index.css";
import { BrowserRouter as Router } from "react-router-dom";
import * as serviceWorker from "./serviceWorker";
import routes from "./routes";

ReactDOM.render(<Router>{routes}</Router>, document.getElementById("root"));
serviceWorker.unregister();
