import React, { Component } from "react";
import "./pocetnaNastavnik.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Redirect } from "react-router-dom";
import { Modal } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';

class PocetnaNastavnik extends Component {
	constuctor() {
		this.routeChange = this.routeChange.bind(this);
	  }
	state = {
		predmeti: [],
		redirect: false,
		predmet: {},
		id: null,
		tipDodavanja: "",
		show: false
	};
	componentDidMount() {
		this.state.id = this.props.id;
		this.setState({id: this.props.id});
		//console.log("PocetnaNastavnik:", this.props);
		
		const { auth } = this.props.items;
		axios
			.get("/nwtOcjena/predmeti/nastavnik/" + this.state.id, {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => this.setState({ predmeti: response.data }))
			.catch(err => console.log(err));

	};
	routeChange = (row) => {
		let path = "/ucenici/predmet/" + row.id;
		this.state.predmet = row;
		this.state.redirect = true;
		this.props = this.props.items;
		this.props.history.push(path);
		console.log(this.props)
		
	  };
	options = {
		onRowClick: 		
			this.routeChange
		
	}
	handleClose() {
		this.setState({ show: false });
	}
	
	handleShow(tipDodavanja) {
		this.setState({ tipDodavanja, show: true });
	}
	dodajPredmet(e) {
		this.setState({redirect: true});
	}
	render() {		
		const { redirect, id, tipDodavanja } = this.state;
		if (redirect) {
			return <Redirect to={"/predmet/dodaj/"+id} />;
		}
		return (
			<div>
				
				<br></br>
				<h2>Predmeti
					<button className="btn btn-primary right" onClick={e => this.dodajPredmet(e)}>
						Dodaj
					</button>
				</h2>
				
				<BootstrapTable data={ this.state.predmeti } options={ this.options }>
					<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
					<TableHeaderColumn dataField='naziv'>Naziv</TableHeaderColumn>
				</BootstrapTable>
			</div>
		);
	}
}

export default PocetnaNastavnik;
