"use strict";

/**detail */

function detail(detailCook) {

	const detailCard = document.getElementById("detail-l");
	detailCook.state === "remision" ? detailCook.state = "Remisión" : null;
	detailCook.state === "brote leve moderado" ? detailCook.state = "Brote leve-moderado" : null;
	detailCook.state === "brote moderado" ? detailCook.state = "Brote moderado" : null;
	detailCook.state === "brote" ? detailCook.state = "Brote" : null;

	const liCard = document.createElement("section");
	liCard.classList.add("list-detail");
	liCard.dataset.id = detailCook.id;
	liCard.innerHTML = `        
      <div class="box-head patrick-hand-regular"> 
        <img class="img-detail" src="./recipe_photo/${detailCook.photo}"
        alt="${detailCook.title}"/> 
        <div class="box-title">    
          <h2 class="title-d">${detailCook.title}</h2> 
          <div class="author-time"> 
	          <span class="mg-lef padding-t">
	            <i class="fa-regular fa-user mg-lef icon-h"></i>
	            ${detailCook.author}
	          </span>   
	          <span class="mg-lef padding-t">
	              <i class="fa-regular fa-clock icon-h spacing-left1"></i>
	              <span class="mg-lef"> ${detailCook.timePreparation}</span>
	          </span>  
        </div>
          <div class="box-icons-title display-row-center">
            <div class="display-row-center">
                <i class="fa-solid fa-utensils icon"></i>
                <div class="flex-c-center"> 
                  <p class="icon-text">Raciones</p>
                  <span class="mg-lef display-row-center">
                    ${detailCook.quantity}
                    <span class="mg-lef">Personas</span>
                  </span>
                </div>
            </div>
            <div class="display-row-center mg-lef-M">
            <i class="fa-solid fa-chart-line ico-font spacing-right1 ${detailCook.state === "Remisión" ? "icon-remision" : "icon-brote"}"></i>
                <div class="flex-c-center"> 
                  <p class="icon-text mg-lef">Estado</p>
                  <span>
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
				  <i class="fa-regular fa-pen-to-square"></i>
				</button>
				<button class="button-wit icon-size" onclick="toggleInfoModal()">
				   <i class="fa-regular fa-trash-can  icon-dele"></i> 
				</button>
		   </div>
		   <div class="modal-Info" id="showModalInfo" hidden>
		      <div class="container-modal">
		        <button type="close" class="close-info" id="closedModalInfo" onclick="toggleClosefoModal()">
		          <i class="fa-solid fa-xmark"></i>
		         </button> 
				<p>¡Seguro que quieres eliminar la receta!</p>
				<button type="delete" class="deleteCook">Eliminar</button>
			   </div>	
		  </div>
		
		` : ''}
    <article class="box-ingredient-preparation patrick-hand-regular"> 
      <div class="ingredient">
        <p class="title-ing-prep">Ingredientes</p>
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
        <p class="title-ing-prep">Preparación</p>  
        <ol class="list-ol">
         ${detailCook.preparations ? detailCook.preparations.map((step) =>
				`<li class="list-p">
                    <p>${step.preparation}</p>
                </li>`)
			.join("") : ''}
          </ol>
       </div>  
     </article>
    `;
	const deleteCook = liCard.querySelector(".deleteCook");
	const editButton = liCard.querySelector(".edit");


	detailCook.isOwner ?
		editButton.onclick = function() {
			const urlParams = new URLSearchParams(window.location.search);
			window.location.href = `./updateCook.html?id=${urlParams.get("id")}`;
		} : null

	detailCook.isOwner ?
		deleteCook.onclick = function() {
			onDeleteButtonClick(detailCook.id);
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

// Función para botón mostrar modal
function toggleInfoModal() {
	const showModalInfo = document.getElementById("showModalInfo");
	if (showModalInfo) {
		showModalInfo.style.display = 'block';
	}
}

// Función para botón ocultar modal
function toggleClosefoModal() {
	const showModalInfo = document.getElementById("showModalInfo");
	if (showModalInfo) {
		showModalInfo.style.display = 'none';
	}
}


function onDeleteButtonClick(id) {
	fetchDelete(id)
}