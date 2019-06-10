import React, { Component } from "react";
import "./UploadZadacu.css";
import axios from "axios";
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Badge from 'react-bootstrap/Badge';
import Figure from 'react-bootstrap/Figure';
import Button from 'react-bootstrap/Button';


class UploadZadacu extends Component {
	constructor(props) {
		super(props);
		  this.state = {
			selectedFile: null
		  }
	   
	  }
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
		console.log("UPLOAD",this.props.match.params.predmetId);
		this.setState({predmetId: this.props.match.params.predmetId}); 
	}
	onChangeHandler = event => {

		console.log(event.target.files[0])
		this.setState({
			selectedFile: event.target.files[0],
			loaded: 0,
		  })
	}
	onClickHandler = () => {

		var data2 = {
			status: 'open'
	   	}
		var headers = {
            'Content-Type': 'application/json',
            'Authorization': "Bearer " + this.props.auth 
        }
        axios.post("/nwtUpload/zadaca", data2, {headers: headers})
			// Kreiranje zadace
            .then((response) => {
				console.log(response.data.id);
				axios
				// Dodavanje predmeta zadaci
				.get("/nwtUpload/dodjeliZadacuPredmetu/" + response.data.id + "/" + this.state.predmetId, {
					headers: {
						Authorization: "Bearer " + this.props.auth 
					}
				})
				.then(response => {
					//OVO JE UPLOAD ZADACE
					
					console.log(response.data);

					const data = new FormData()
					data.append('file', this.state.selectedFile)
					data.append('zadacaId', response.data.id)

					var headers = {
						'Content-Type': 'application/json',
						'Authorization': "Bearer " + this.props.auth 
					}

					axios.post("/nwtUpload/uploadFile", data, {headers: headers})
					.then(res => { 
						console.log(res)
					})
				})
				.catch(err => console.log(err));
            })
            .catch((error) => {
                console.log(error);
            })
	}

	render() {
		return (
			<div className="container">
				<div className="row">
					<div className="col-12">
						<div className="card border">
							<div className="card-header">Dodavanje zadaće</div>
							<div className="card-body text-left">
								<div className="upload">
									<input type="file" name="file" onChange={this.onChangeHandler}/>
									
									<button type="button" class="btn btn-success btn-block" onClick={this.onClickHandler}>Upload</button> 
									
									
									{/* <Button variant="primary">Upload</Button>
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
									<p>Upload u toku</p> */}


								</div>
							</div>
						</div>
					</div>
				</div>
      		</div>
			
		);
	}
}

export default UploadZadacu;
