"use strict";
// function handleSubmitForm(event) {
document.addEventListener("DOMContentLoaded", function(event) {
	event.preventDefault();

	// const updateId = document.getElementById("updateId");

	const urlParams = new URLSearchParams(window.location.search);

	const id = Number.parseInt(urlParams.get("id"));


	if (id) {
		fetchDetail(id)
			.then(data => {
				console.log("udtaed---->", data);
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

				console.log("ingredients update", ingredients)

				console.log("preparation", preparation)


			})
			.catch(error => {
				console.error('Error al obtener los datos actualizar:', error);
			});


	}
}
)