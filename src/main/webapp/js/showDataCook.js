"use strict";

function showUpdateImg(imgPreview, imageUrl) {
  imgPreview.innerHTML = "";

  if (imageUrl) {
    const img = document.createElement("img");
    img.src = imageUrl;
    img.classList.add("img-pr");

    imgPreview.appendChild(img);

    const info = document.createElement("small");
    info.classList.add("info");
   // info.innerHTML = imageUrl || "No hay imagen selecionada";
    imgPreview.appendChild(info);
  }
}


const ingredBox = document.getElementById("element-Ing");

function showIngredientInput(ingredients) {
    ingredBox.innerHTML = "";
    const ingredientList = ingredients ? ingredients.split(",") : [];

    ingredientList.forEach((ingredient) => {
        const inputIngredient = document.createElement("section");
        inputIngredient.classList.add("input-ad");
        inputIngredient.innerHTML = `
    <i class="fa-solid fa-square squa-c"></i>
    <label for="ingredient" id="label" class="input-ing-prep "></label>
    <input type="text" class="input-focus input-focu1 input-width " name="ingredient" value="${ingredient}" placeholder="Ej: 500ml agua" required>
    <button type="button" class="buttons buttons-delet" name="delete"><i class="fa-regular fa-trash-can"></i></button>

    `;

        createElementIngred.appendChild(inputIngredient);
    });

}


const preparationContainer = document.getElementById("element-prep")

 let count = 0;
function addPreparationStep(preparations) {
    preparationContainer.innerHTML = "";
    const preparationArray = JSON.parse(preparations);

    preparationArray.forEach((item) => {
        const inputPreparation = document.createElement("section");
        inputPreparation.classList.add("input-prep");
        

        inputPreparation.innerHTML = `
            <div class="box-prep-inp">
                <i class="fa-solid fa-square squa-c"></i> 
                <label for="ingredient" class="input-ing-prep"></label>
                <textarea rows="1" maxLength="700" dir="auto" class="textarea input-focus input-focu" id="textarea" name="ingredient" placeholder="Ej: Cortamos las patatas y las ponemos a cocinar" required>${item.descripton}</textarea>
                <button type="delete" id="btnpreraration" class="buttons buttons-delet" name="delete-preparation"><i class="fa-regular fa-trash-can"></i></button>
            </div>
            <div class="img-preparation">
                <input type="file" class="input-focu" id="preparation${count}" name="ingre-file" value="" name="preparation" accept="image/*" style="display: none">
                <div class="file-ingred" id="preparationBox${count}">
                <div id="imgIngredientPreview${count}" class="img-text">
                ${item.img ? `<img class="img-prepa" src="${item.img}" alt="Foto del plato preparación" />`: ''}
                </div>
                <button class="buton-img-file" type="button">
                <img class="img-prepa" src="./images/camara.png" alt="Foto de la preparación" />  
                </button>
                </div>
            </div>
        `;

        createElementPrep.appendChild(inputPreparation);
        
        
        
    });
}


