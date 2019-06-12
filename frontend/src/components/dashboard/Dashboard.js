import React, { Component } from "react";
import "./Dashboard.css";
import PocetnaNastavnik from '../../components/pocetnaNastavnik/pocetnaNastavnik';
import PocetnaUcenik from '../../components/pocetnaUcenik/pocetnaUcenik';
import PocetnaAdmin from '../../components/pocetnaAdmin/pocetnaAdmin';
import axios from "axios";
import jwt from 'jsonwebtoken'
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';

class Dashboard extends Component {
	constructor(props) {
		super(props)
		this.state ={
			rola: null,
			id: null,
			ucenici: []
		};
		this.Pocetna = this.Pocetna.bind(this);
	}
	componentDidMount() {
		//console.log("DASHBOARD:", this.props);
		const { auth, username, role, natavnikId, ucenikId } = this.props;
		
		var token = auth;
		token = token.replace('Bearer ','');
		var decoded = jwt.decode(token);
		this.setState({ rola: decoded.authorities[0] });	
		this.state.rola = decoded.authorities[0];
		if(this.state && this.state.rola && this.state.rola.substring(0, 6) != "ROLE_a") {	
			this.setState({ id: this.state.rola.substring(6) });
			this.state.id = this.state.rola.substring(6);
		}
		this.setState({ rola: this.state.rola.substring(0, 6) });
		this.state.rola = this.state.rola.substring(0, 6);
		//console.log(this.state);

		axios
			.get("/nwtUpload/ucenici", {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => this.setState({ ucenici: response.data }))
			.catch(err => console.log(err));
	}
	Pocetna(a) {
		console.log(a);
		const uloga = a.state.rola;
		if (uloga == "ROLE_u") {
		  return <PocetnaUcenik items={this.props} id={this.state.id}/>;
		}
		else if (uloga == "ROLE_n") {
			return <PocetnaNastavnik items={this.props} id={this.state.id}/>;
		}
		else if (uloga == "ROLE_a") {
			return <PocetnaAdmin items={this.props} id={this.state.id}/>;
		}
		return null;
	  }
	render() {
		return (
			<div className="container">
				<this.Pocetna state={this.state} />
				{/* <PocetnaNastavnik props={this.props}/>
				<div className="razmak"></div>
				<h2>Učenici</h2>
				<PocetnaUcenik props={this.props}/> */}
			</div>
		);
	}
}

export default Dashboard;
