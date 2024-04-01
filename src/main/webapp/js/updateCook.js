"use strict";
document.addEventListener("DOMContentLoaded", function(event) {
	event.preventDefault();
	loadData(); // Llamada a la función para cargar los datos al cargar la página
});

function loadData() {

	// const updateId = document.getElementById("updateId");

	const urlParams = new URLSearchParams(window.location.search);

	const id = Number.parseInt(urlParams.get("id"));
	alert("update")

	if (id) {
		fetchDetail(id)
			.then(data => {
				//console.log("udtaed---->", data);
				const title = data.title;
				console.log("title udata---->", title);
				const quantity = data.quantity;
				const timePreparation = data.timePreparation;
				const author = data.author;
				const state = data.state;
				const ingredients = data.ingredients;
				//addPreparationStep(data.preparations)
				const preparation = data.preparations;
				//const imgPreview = data.preparations.img

				const id = data.id;
				document.getElementById("id").value = id;
				console.log(id)

				document.getElementById("title").value = title;
				document.getElementById("quantity").value = quantity;
				document.getElementById("timePreparation").value = timePreparation;
				document.getElementById("author").value = author;
				const imgprevie = document.getElementById("imgPreview");
				const imgUrl = data.photo;
				imgprevie.value = imgUrl;
				showUpdateImg(imgprevie, imgUrl)

				const radioInput = document.querySelector(`input[value="${state}"]`);
				radioInput ? radioInput.checked = true : radioInput.checked = false;

				showIngredientInput(data.ingredients);
				addPreparationStep(data.preparations);

				//console.log("ingredients update", ingredients)

				//console.log("preparation", preparation)

				console.log("udtaed---->", data);


			})
			.catch(error => {
				console.error('Error al obtener los datos actualizar:', error);
			});


	}
}


