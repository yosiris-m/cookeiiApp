"use strict";

/*document.addEventListener("DOMContentLoaded", function() {
	const createUserForm = document.getElementById("createUserForm");
	createUserForm.addEventListener("submit", function(event) {
		event.preventDefault();
		sendDataUser();
	});
});*/

function sendDataUser() {
	const userName = document.getElementById("userName").value;
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;

	const userData = {
		userName: userName,
		email: email,
		password: password
	};

	console.log("Datos a enviar:", userData);

	fetch("/finalyProject/SvUser", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(userData)
	})
		.then(response => {
			window.location.href = './'	
		})
		.catch(error => {
			console.error("Error al enviar los datos", error);
		});
}