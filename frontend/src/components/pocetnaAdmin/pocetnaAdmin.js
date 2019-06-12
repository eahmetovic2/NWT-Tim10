import React, { Component } from "react";
import "./pocetnaAdmin.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import { Modal } from 'react-bootstrap';
import { Redirect } from "react-router-dom";
import Button from 'react-bootstrap/Button';
import Dropdown from 'react-bootstrap/Dropdown';

class PocetnaAdmin extends Component {
	// constuctor() {
	// 	this.routeChange = this.routeChange.bind(this);
	// 	this.handleShow = this.handleShow.bind(this);
	//   }
	state = {
		predmeti: [],
		ucenici: [],
		nastavnici: [],
		redirect: false,
		predmet: {},
		id: null,
		show: false,
		tipDodavanja: "",
		ime: "",
		prezime: "",
		username: "",
		password: "",
		headers: ""

	};
	componentDidMount() {
		const  {auth} =  this.props.items;
		var headers = {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + auth
		}
		Promise.all(
			[
				axios.get("/nwtOcjena/nastavnik/sve", {
					headers: {
						Authorization: "Bearer " + auth
					}
				}),
				axios.get("/nwtOcjena/ucenik/sve", {
					headers: {
						Authorization: "Bearer " + auth
					}
				}),
				axios.get("/nwtOcjena/predmeti", {
					headers: {
						Authorization: "Bearer " + auth
					}
				})
			]
		)
		.then(res => {
			let [nastavnikResponse, ucenikResponse, predmetResponse] = res;
			this.setState({
				nastavnici: nastavnikResponse.data,
				predmeti: predmetResponse.data,
				ucenici: ucenikResponse.data,
				headers
			}, () => console.log(this.state))
		})
		.catch(err => console.log(err))

	}
	// routeChange = (row) => {
	// 	let path = "/ucenici/predmet/" + row.id;
	// 	this.state.predmet = row;
	// 	this.state.redirect = true;
	// 	this.props = this.props.items;
	// 	this.props.history.push(path);
	// 	console.log(this.props)
		
	//   };
	// options = {
	// 	onRowClick: 		
	// 		this.routeChange
		
	// }


	handleInput(e) {
		this.setState({
			[e.target.name]: e.target.value
		});
	}

	dodajUcenika(){
		const { ime, prezime, username, password, headers } = this.state;
		axios.post("/nwtOcjena/ucenik/create", 
				{ ime, prezime}, 
				{headers}
		).then(res => {
			alert("Uspješno dodan učenik.");
			/* let id = res.data.id;
			axios.post("/auth/create", 
				{ username, password, role: `u${id}`, ucenikId: "", nastavnikId: ""}, 
				{headers}
			)
			.then(res => alert("Jupii!"))
			.catch(err => console.log(err)) */
		})
		.catch(error => console.log(error));
	}
	


	dodajNastavnika(){
		const { ime, prezime, username, password, headers } = this.state;
		axios.post("/nwtOcjena/nastavnik/create", 
				{ ime, prezime }, 
				{headers}
		).then(res => {
			
			alert("Uspješno dodan nastavnik.");
			/* let id = res.data.id;
			axios.post("/auth/create", 
				{ username, password, role: `n${id}`, ucenikId: "", nastavnikId: ""}, 
				{headers}
			)
			.then(res => alert("Jupii!"))
			.catch(err => console.log(err)) */
		})
		.catch(error => console.log(error));
	}

	handleClose() {
		this.setState({ show: false });
	}
	
	handleShow(tipDodavanja) {
		this.setState({ tipDodavanja, show: true });
	}

	render() {
		const {tipDodavanja} = this.state;
		return (
			<div>
					<Modal show={this.state.show} onHide={() => this.handleClose()}>
					<Modal.Header closeButton>
						<Modal.Title>Dodaj {tipDodavanja}a</Modal.Title>
					</Modal.Header>
					<Modal.Body>
								<div className="form-group">
									<label htmlFor="exampleInputEmail1">Ime:</label>
									<input
										type="text"
										name="ime"
										onChange={e => this.handleInput(e)}
										className="form-control"
										placeholder="Ime"
									/>
								</div>
								<div className="form-group">
									<label htmlFor="exampleInputEmail1">Prezime:</label>
									<input
										type="text"
										name="prezime"
										onChange={e => this.handleInput(e)}
										className="form-control"
										placeholder="Prezime"
									/>
								</div>
								<div className="form-group">
									<label htmlFor="exampleInputEmail1">Username:</label>
									<input
										type="text"
										name="username"
										onChange={e => this.handleInput(e)}
										className="form-control"
										placeholder="Username"
									/>
								</div>
								<div className="form-group">
									<label htmlFor="exampleInputEmail1">Password:</label>
									<input
										type="password"
										name="password"
										onChange={e => this.handleInput(e)}
										className="form-control"
										placeholder="Password"
									/>
								</div>

					</Modal.Body>
					<Modal.Footer>
						<Button variant="secondary" onClick={() => this.handleClose()}>
						Zatvori
						</Button>
						<Button variant="primary" onClick={() => tipDodavanja == "nastavnik" ? 
									this.dodajNastavnika() : this.dodajUcenika()}>
						Spasi {tipDodavanja}
						</Button>
					</Modal.Footer>
					</Modal>
					<h2>ADMIN</h2>
					<br/>
					<h3>Predmeti</h3>
					
					<BootstrapTable data={ this.state.predmeti } options={ this.options }>
						<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
						<TableHeaderColumn dataField='naziv'>Naziv</TableHeaderColumn>
					</BootstrapTable>

					<br/>
					<h3>Ucenici
						<button className="btn btn-primary right" onClick={e => this.handleShow("ucenik")}>
							Dodaj
						</button>
					</h3>
				
					<BootstrapTable data={ this.state.ucenici } options={ this.options }>
						<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
						<TableHeaderColumn dataField='ime'>Ime</TableHeaderColumn>
						<TableHeaderColumn dataField='prezime'>Prezime</TableHeaderColumn>
					</BootstrapTable>
					<br/>
					<h3>Nastavnici
						<button className="btn btn-primary right" onClick={e => this.handleShow("nastavnik")}>
							Dodaj
						</button>
					</h3>
				
					<BootstrapTable data={ this.state.nastavnici } options={ this.options }>
						<TableHeaderColumn dataField='id' isKey>ID</TableHeaderColumn>
						<TableHeaderColumn dataField='ime'>Naziv</TableHeaderColumn>
						<TableHeaderColumn dataField='prezime'>Naziv</TableHeaderColumn>
					</BootstrapTable>
			
			 </div>
			
		);
	}
}

export default PocetnaAdmin;

			