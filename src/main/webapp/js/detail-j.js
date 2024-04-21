"use strict";

/**detail */



function detail(detailCook) {
	console.log("detailCook", detailCook)

	const detailCard = document.getElementById("detail-l");
	detailCook.state === "remision" ? detailCook.state = "Remisión" : null;
	detailCook.state === "brote leve moderado" ? detailCook.state = "Brote leve-moderado" : null;
	detailCook.state === "brote leve moderado" ? detailCook.state = "Brote moderado" : null;
	detailCook.state === "brote" ? detailCook.state = "Brote" : null;

	const liCard = document.createElement("section");
	liCard.classList.add("list-detail");
	liCard.dataset.id = detailCook.id;
	liCard.innerHTML = `        
      <div class="box-head patrick-hand-regular"> 
        <img class="img-detail" src="./recipe_photo/${detailCook.photo}"
        alt="${detailCook.title}"/> 
        <div class="box-title">    
          <h2 class="title-detail">${detailCook.title}</h2> 
          <div class="flex-c-center"> 
          <span class="mg-lef display-row-center">
          <i class="fa-regular fa-clock"></i>
            ${detailCook.timePreparation}
          </span>
        </div>
          <div class="box-icons-title display-row-center">
            <div class="display-row-center">
                <i class="fa-regular fa-user mg-lef icon"></i>
                <div class="flex-c-center"> 
                  <p class="icon-text">Raciones</p>
                  <span class="mg-lef display-row-center">
                    ${detailCook.quantity}
                    <span class="mg-lef">Personas</span>
                  </span>
                </div>
            </div>
            <div class="display-row-center mg-lef-M">
            <i class="fa-solid fa-chart-line mg-lef-M ico-font ${detailCook.state === "Remisión" ? "icon-remision" : "icon-brote"}"></i>
                <div class="flex-c-center"> 
                  <p class="icon-text">Estado</p>
                  <span class="mg-lef">
                    ${detailCook.state}
                  </span>
                </div>
            </div>
          </div>
        </div>
      </div> 
      ${detailCook.isOwner ?
			`<div class="box-butts">
			<button class="button-wit edit icon-size" id="edit">
			<i class="fa-regular fa-pen-to-square"></i></button>
			<button class="button-wit  delete icon-size" id="delete"
			 onclick="onDeleteButtonClick(${detailCook.id})">
			<i class="fa-regular fa-trash-can"></i></button>
		</div>` : ''}
    <article class="box-ingredient-preparation patrick-hand-regular"> 
      <div class="ingredient">
        <h3 class="mg-lef-M title-ing-prep">Ingredientes</h3>
        <ul class="list-d">
          ${detailCook.ingredients ? detailCook.ingredients.map((stepIng) =>
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
         ${detailCook.preparations ? detailCook.preparations.map((step) =>
				`<li>
              <p>${step.preparation}</p>
            </li>`)
			.join("") : ''}
          </ol>
        </div>  
      </article>
    `;

	const editButton = liCard.querySelector(".edit");

	detailCook.isOwner ?
		editButton.onclick = function() {
			loggued(detailCook.isOwner)
			const urlParams = new URLSearchParams(window.location.search);
			window.location.href = `./updateCook.html?id=${urlParams.get("id")}`;

		} : null

	detailCard.appendChild(liCard);
}
const urlParams = new URLSearchParams(window.location.search);

const id = Number.parseInt(urlParams.get("id"));


fetchDetail(id)
	.then(data => {
		console.log("Datos de usuario obtenidos llamada:", data);
		detail(data)
	})
	.catch(error => {
		console.error('Error al obtener los datos del detalle:', error);
	});

function onDeleteButtonClick(id) {
	// Llama la función delete y le paso el id a eliminar
	fetchDelete(id);
}
