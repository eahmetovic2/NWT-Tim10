import React, { Component } from "react";
import "./dodajMaterijal.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Badge from 'react-bootstrap/Badge';
import Figure from 'react-bootstrap/Figure';
import Button from 'react-bootstrap/Button';


class uploadMaterijal extends Component {

	/*handleClick(e) {
		const { username, password } = this.state;
		console.log(username);
		console.log(password);
		axios
			.post("/uploadFile", {
				username,
				password
			})
			.then(response => {
				if (response.status === 200) {
					localStorage.setItem(
						"token",
						response.headers.authorization
					);
					this.setState({ redirect: true });
				} else {
					alert("error: Bad credentials!");
				}
			})
			.catch(error => alert("error: Server error!"));
	}*/


	componentDidMount() {
		/*const { auth } = this.props;
		axios
			.get("/nwtUpload/ucenici", {
				headers: {
					Authorization: "Bearer " + auth
				}
			})
			.then(response => this.setState({ ucenici: response.data }))
			.catch(err => console.log(err));*/
	}

	render() {
		return (
			<div className="upload">
			<Button variant="primary">Upload</Button>
			<h2>    Uploadani materijal:  </h2>
			<Figure>
				<Figure.Image
					width={171}
					height={180}
					alt="171x180"
					//src="holder.js/171x180"
				/>
				<Figure.Caption>
					Uploadani materijal
				</Figure.Caption>
			</Figure>
			<h2>    Progres:   </h2>
			<ProgressBar animated now={100} />
			<h2>    Poruka:  </h2>
			<p>Upload u toku</p>
			</div>
		);
	}
}

export default uploadMaterijal;
