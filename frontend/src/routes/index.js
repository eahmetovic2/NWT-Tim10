import React from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import loadable from "@loadable/component";

import App from "../App";
import withAuth from "../helpers/withAuth";

const Login = loadable(() => import("../components/login/Login"));
const Dashboard = loadable(() => import("../components/dashboard/Dashboard"));

export default (
	<App>
		<Switch>
			<Route path="/" exact component={withAuth(Dashboard)} />
			<Route path="/login" component={Login} />
		</Switch>
	</App>
);
