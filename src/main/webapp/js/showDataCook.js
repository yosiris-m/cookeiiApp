"use strict";
function showUpdateImg(imgPreview, imageUrl) {
	imgPreview.innerHTML = "";
	const img = document.createElement("img");
	img.src = `./recipe_photo/${imageUrl}`;
	img.classList.add("img-pr");

	imgPreview.appendChild(img);

	const info = document.createElement("small");
	info.classList.add("info");
	info.innerHTML = imageUrl || "No hay imagen selecionada";
	imgPreview.appendChild(info);

}


const createElementIngred = document.getElementById("element-Ing");

function showIngredientInput(ingredients) {
	createElementIngred.innerHTML = "";
	const ingredientList = ingredients ? ingredients : [];
	ingredientList.forEach((item) => {
		const inputIngredient = document.createElement("article");
		inputIngredient.classList.add("input-ad");
		inputIngredient.innerHTML = `
            <i class="fa-solid fa-square squa-c"></i>
            <label for="ingredient" id="label" class="input-ing-prep "></label>
            <input type="text" class="input-focus input-focu1 input-width " name="ingredient" value="${item.ingredient}" placeholder="Ej: 500ml agua" required>
            <button type="button" class="buttons buttons-delet" name="delete"><i class="fa-regular fa-trash-can"></i></button>
        `;

		createElementIngred.appendChild(inputIngredient);
	});
}

const preparationContainer = document.getElementById("element-prep");
const createElementPrep = preparationContainer; // le reasigno a una variable
let count = 0;


function addPreparationStep(preparations) {
	preparationContainer.innerHTML = "";
	const preparationArray = preparations ? preparations : [];
	preparationArray.forEach((item) => {
		const inputPreparation = document.createElement("section");
		inputPreparation.classList.add("input-prep");
		inputPreparation.innerHTML = `
            <div class="box-prep-inp margin">
                <i class="fa-solid fa-square squa-c"></i> 
                <label for="preparation" class="input-ing-prep"></label>
                <textarea rows="1" maxLength="700" dir="auto" class="textarea input-focus" id="textarea" name="preparation" placeholder="Ej: Cortamos las patatas y las ponemos a cocinar" required>${item.preparation}</textarea>
                <button type="delete" id="btnpreraration" class="buttons buttons-delet" name="delete-preparation"><i class="fa-regular fa-trash-can"></i></button>
            </div>
        `;
         createElementPrep.appendChild(inputPreparation);
		count++;
	
	});
}