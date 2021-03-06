import React from "react";
import { Route, Redirect, Switch } from "react-router-dom";
import loadable from "@loadable/component";

import App from "../App";
import withAuth from "../helpers/withAuth";

const Login = loadable(() => import("../components/login/Login"));
const Dashboard = loadable(() => import("../components/dashboard/Dashboard"));
const dodajMaterijal = loadable(() => import("../components/dodajMaterijal/dodajMaterijal"));
const ListaUcenikaPredmeta = loadable(() => import("../components/listaUcenikaPredmeta/ListaUcenikaPredmeta"));
const UcenikPredmeta = loadable(() => import("../components/ucenikPredmeta/UcenikPredmeta"));
const predmetUcenika = loadable(() => import("../components/predmetUcenika/predmetUcenika"));
const materijaliUcenika = loadable(() => import("../components/materijaliUcenika/materijaliUcenika"));
const zadaceUcenika = loadable(() => import("../components/zadaceUcenika/zadaceUcenika"));
const UploadZadacu = loadable(() => import("../components/uploadZadacu/uploadZadacu"));
const UploadZadacuUcenik = loadable(() => import("../components/uploadZadacuUcenik/uploadZadacuUcenik"));
const DodajPredmet = loadable(() => import("../components/dodajPredmet/dodajPredmet"));


export default (
	<App>
		<Switch>
			<Route path="/" exact component={withAuth(Dashboard)} />
			<Route path="/login" component={Login} />
			<Route path="/ucenici/predmet/:predmetID" component={withAuth(ListaUcenikaPredmeta)} />
			<Route path="/zadaca/dodaj/:predmetId" component={withAuth(UploadZadacu)} />
			<Route path="/predmet/dodaj/:nastavnikId" component={withAuth(DodajPredmet)} />
			<Route path="/predmet/:predmetId/ucenik/:ucenikId" component={withAuth(UcenikPredmeta)} />
			<Route path="/ucenik/:ucenikId/predmet/:predmetId" component={withAuth(predmetUcenika)} />
			<Route path="/materijaliPregled/:predmetId" component={withAuth(predmetUcenika)} />
			
			<Route path="/zadaceUcenika/:ucenikId/predmet/:predmetId" component={withAuth(zadaceUcenika)} />
			<Route path="/zadaca/dodaj/ucenik/:ucenikId/predmet/:predmetId" component={withAuth(UploadZadacuUcenik)} />
			
			<Route path="/dodajMaterijal" component={withAuth(dodajMaterijal)} />
		</Switch>
	</App>
);