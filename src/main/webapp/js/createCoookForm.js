"use strict";

const createIngredient = document.getElementById("element-Ing");
function addNewIngredient() {
	const ingredient = document.createElement("section");
	ingredient.classList.add("input-ad");
	ingredient.innerHTML = `
    <i class="fa-solid fa-square squa-c"></i>
    <label for="ingredient" id="label" class="input-ing-prep "></label>
    <input type="text" class="input-focus input-focu1 input-width " name="ingredient" value="" placeholder="Ej: 500ml agua" required>
    <button type="button" class="buttons buttons-delet" name="delete"><i class="fa-regular fa-trash-can"></i></button>

    `;

	createIngredient.appendChild(ingredient);
}
addNewIngredient();
addNewIngredient();

function handleDeleteClick(event) {
	const clickedElement = event.target;
	const button = clickedElement.closest("button[name='delete']");
	if (button) {
		const inputClass = button.closest(".input-ad");

		if (inputClass) {
			inputClass.remove();
		}
	}
}

document.addEventListener("DOMContentLoaded", function() {
	createIngredient.addEventListener("click", handleDeleteClick);
});



const createPreparation = document.getElementById("element-prep");
let preparationCount = 0;
function addNewPreparation() {
    const preparation = document.createElement("section");
    preparation.classList.add("input-prep");
    preparationCount++;

    preparation.innerHTML = `
        <div class="box-prep-inp">
            <i class="fa-solid fa-square squa-c"></i> 
            <label for="preparation" class="input-ing-prep"></label>
            <input type="text" class="input-width input-focus input-focu" id="preparation" name="preparation" value="" placeholder="Ej: Cortamos las patatas y las ponemos a cocinar" required>
            <button type="delete" id="b" class="buttons buttons-delet" name="delete-preparation"><i class="fa-regular fa-trash-can"></i></button>
        </div>
        </div>
    `;

    createPreparation.appendChild(preparation);

}

addNewPreparation();

function handleDeletePrep(event) {
	const clickedElement = event.target;
	const button = clickedElement.closest("button[name='delete-preparation']");
	if (button) {
		const inputClass = button.closest(".input-prep");

		if (inputClass) {
			inputClass.remove();
		}
	}
}

document.addEventListener("DOMContentLoaded", function() {
	createPreparation.addEventListener("click", handleDeletePrep);
});



