import React from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import loadable from "@loadable/component";

import App from "../App";
import withAuth from "../helpers/withAuth";

const Login = loadable(() => import("../components/login/Login"));
const Dashboard = loadable(() => import("../components/dashboard/Dashboard"));
const dodajMaterijal = loadable(() => import("../components/dodajMaterijal/dodajMaterijal"));
const ListaUcenikaPredmeta = loadable(() => import("../components/listaUcenikaPredmeta/ListaUcenikaPredmeta"));

export default (
	<App>
		<Switch>
			<Route path="/" exact component={withAuth(Dashboard)} />
			<Route path="/login" component={Login} />
			<Route path="/ucenici/predmet/:predmetID" component={withAuth(ListaUcenikaPredmeta)} />
		
			<Route path="/dodajMaterijal" component={withAuth(dodajMaterijal)} />
		</Switch>
	</App>
);
