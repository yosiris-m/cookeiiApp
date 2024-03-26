"use strict";

/**detail */
function detail(data) {
	const selectedItem = data
	const detailCard = document.getElementById("detail-l");
	selectedItem.state === "remision" ? selectedItem.state = "Remisión" : null;
	selectedItem.state === "brote leve moderado" ? selectedItem.state = "Brote leve-moderado" : null;
	selectedItem.state === "brote" ? selectedItem.state = "Brote" : null;
	console.log("detail", data)
	const liCard = document.createElement("section");
	liCard.classList.add("list-detail");
	liCard.dataset.id = selectedItem.id;
	liCard.innerHTML = `        
      <div class="box-head patrick-hand-regular"> 
        <img class="img-detail" src="./recipe_photo/${selectedItem.photo}"
        alt="${selectedItem.title}"/> 
        <div class="box-title">    
          <h2 class="title-detail">${selectedItem.title}</h2> 
          <div class="flex-c-center"> 
          <span class="mg-lef display-row-center">
          <i class="fa-regular fa-clock"></i>
            ${selectedItem.timePreparation}
          </span>
        </div>
          <div class="box-icons-title display-row-center">
            <div class="display-row-center">
                <i class="fa-regular fa-user mg-lef icon"></i>
                <div class="flex-c-center"> 
                  <p class="icon-text">Raciones</p>
                  <span class="mg-lef display-row-center">
                    ${selectedItem.quantity}
                    <span class="mg-lef">Personas</span>
                  </span>
                </div>
            </div>
            <div class="display-row-center mg-lef-M">
            <i class="fa-solid fa-chart-line mg-lef-M ico-font ${selectedItem.state === "Remisión" ? "icon-remision" : "icon-brote"}"></i>
                <div class="flex-c-center"> 
                  <p class="icon-text">Estado</p>
                  <span class="mg-lef">
                    ${selectedItem.state}
                  </span>
                </div>
            </div>
          </div>
        </div>
      </div> 
      <div class="box-butts">
      <button class="button-wit edit icon-size" id="edit"><i class="fa-regular fa-pen-to-square"></i></button>
      <button class="button-wit  delete icon-size" id="delete"><i class="fa-regular fa-trash-can"></i></button>
    </div>
    <article class="box-ingredient-preparation patrick-hand-regular"> 
      <div class="ingredient">
        <h3 class="mg-lef-M title-ing-prep">Ingredientes</h3>
        <ul class="list-d">
          ${selectedItem.ingredients ? selectedItem.ingredients.map((stepIng) =>
		     `<li class="list-ingredient">
			   <i class="fa fa-check icon-check "></i>
			    ${stepIng.ingredient}
			 </li>`
	      ).join("") : ''}
        </ul>
        </div> 
      <div class="box-preparation">
        <h3 class="mg-lef-M title-ing-prep">Preparación</h3>  
        <ol class="list-ol">
         ${selectedItem.preparations ? selectedItem.preparations.map((step) =>
		   `<li>
              <p>${step.preparation}</p>
            </li>`)
			.join("") : ''}
          </ol>
        </div>  
      </article>
    `;

	const editButton = liCard.querySelector(".edit");
	const deleteButton = liCard.querySelector(".delete");

	editButton.onclick = function() {
		const urlParams = new URLSearchParams(window.location.search);
		window.location.href = `./createCook.html?id=${urlParams.get("id")}`;

	};

	deleteButton.onclick = function() {
		console.log("eliminar");
	};

	detailCard.appendChild(liCard);
}
const urlParams = new URLSearchParams(window.location.search);

const id = Number.parseInt(urlParams.get("id"));

fetchDetail(id)
	.then(data => {
		detail(data); // Llamada a la función detail y le paso los datos
	})
	.catch(error => {
		console.error('Error al obtener los datos del detalle:', error);
	});



