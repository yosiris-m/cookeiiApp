"use strict";

const searchContainer = document.getElementById("search");

function cookSearch() {
  const search = document.createElement("form");
  search.classList.add("form-search");
  search.method = "get";
  search.innerHTML = `
    <label for="name" class="icon-search">
      <i class="fa-solid fa-magnifying-glass"></i>
    </label>
    <input
      type="text"
      id="input-search"
      class="input-search"
      placeholder="Buscar recetas por..."
    />
    <button class="icon-delete" onclick="handleReset()">
      <i class=""><i class="fa-solid fa-xmark"></i></i>
    </button>
  `;

	if(searchContainer) searchContainer.appendChild(search);
}

cookSearch();

const headCard = document.getElementById("box-card");
function listCard() {
  const list = document.createElement("div");
  list.classList.add("nav-list");
  list.innerHTML = `
  <button class="all-list"><p class="all-border">Todas</p></button>
  <button class="saved-list" >
   <span><i id="ico-heart" class="fa-solid fa-bookmark fa-sm"></i> Guardadas</span>
 </button>

  `;

  if(headCard) headCard.appendChild(list);
}

listCard();

const inputSearch = document.getElementById("input-search");
inputSearch.addEventListener("input", cookList);
const cookContainerList = document.getElementById("cook-list");


 function cookList(data) {
  cookContainerList.innerHTML = "";
  const filterData = inputSearch.value.toLowerCase();
  const saveCooks = {};
  let foundResults = false;

  function toggleLiked(itemId) {
    const likeIcon = document.getElementById(`like${itemId}`);
    saveCooks[itemId] = !saveCooks[itemId];

    if (saveCooks[itemId]) {
      likeIcon.classList.add("liked");
    } else {
      likeIcon.classList.remove("liked");
    }
  }

  data.forEach(function (item) {
      item.state == "remision" ? item.state = "Remisión": null; 
      item.state == "brote leve moderado" ? item.state = "Brote leve-moderado": null;
      item.state == "brote" ? item.state = "Brote": null;
    const dataList = item.title.toLowerCase();
    if (dataList.includes(filterData)) {
      foundResults = true;

      const liItem = document.createElement("li");
      liItem.classList.add("list-item");
      liItem.dataset.id = item.id;
      liItem.innerHTML = `
        <div class="box" > 
          <a class="link-detail " href="./detail.html?id=${item.id}">
            <div class="box-img-tile">
              <img class="img-card" id="cardImage" src="./recipe_photo/${item.photo}" alt="${item.title}">
            <h4 class="title-items"><class="small" id="cardTitle" small>${item.title}</class=></h4>
            </div>
            <p class="user-card">
              <i class="fa-solid fa-user fa-1x ico-card"></i>
              <span id="cardAuthor">${item.author}</span>
            </p>
          </a>              
          <div class="state-like">
            <span id="state">
              <i class="fa-solid fa-circle fa-xs ico-card circle"></i>
              ${item.state}
            </span>
            <div class="ico-like" id="like${item.id}">
              <i id="ico-heart" class="fa-solid fa-bookmark fa-sm"></i>
            </div>
          </div>
        </div>
        
        `;
      cookContainerList.appendChild(liItem);

      liItem.querySelector(".ico-like").addEventListener("click", function () {
        toggleLiked(item.id);
      });
    }
  });

  if (!foundResults) {
    let notResults = document.createElement("p");
    notResults.textContent = "No se encontraron resultados.";
    cookContainerList.appendChild(notResults);
  }
}
//cookList();
fetchData()
  .then(cookList)
  .catch(error => {
    console.error('Error al obtener los datos:', error);
  });



function handledReset() {
  inputSearch.innerHTML = "";
}
