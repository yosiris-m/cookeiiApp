"use strict";
/*
const data = [
  {
    id: 1,
    title: "Ensalada tropical con mandarina y granada",
    quantity: "4",
    timePreparation: "2 horas",
    state: "remision",
    like: "liked",
    author: "John Smith",
    image:
      "https://1.bp.blogspot.com/-FZXLk2jGajE/VsCU8vVL9pI/AAAAAAAAWfU/yVHGFkDosQg/s1600/blogs%2Bcomida%2Bsana%2B%25283%2529.jpg",
    ingredient: [
      " 1 banana madura o 2 pequeñas",
      " 3 huevos",
      " 100 ml Leche",
      " 3 huevos",
      " 3 huevos",
    ],
    preparation: [
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear)Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
      "https://1.bp.blogspot.com/-FZXLk2jGajE/VsCU8vVL9pI/AAAAAAAAWfU/yVHGFkDosQg/s1600/blogs%2Bcomida%2Bsana%2B%25283%2529.jpg",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },

      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },

      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
    ],
  },
  {
    id: 2,
    title: "Espirales al tomate",
    quantity: "6",
    timePreparation: "2:20 horas",
    state: "brote leve moderado",
    like: "liked",
    author: "Alvar carrasco",
    image:
      "https://www.dosfarma.com/blog/wp-content/uploads/2020/07/24_08_-5-platos-para-comer-ligero-sano-y-fresquito.jpg",
    ingredient: [
      " 1 taza de harina leudante o harina de repostería",
      " 3 Unidad huevos",
      " 1 cda levadura",
      " 3 unidad huevos",
      " 3 3 cucharadas Cacao amargo",
    ],
    preparation: [
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
          "https://www.lacocinasana.com/static/7b3046853e27aba28891d12702dea4a5/5e630/pasta_sin_gluten_sana_b67511ab23.jpg",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },

    ],
  },
  {
    id: 3,
    title: "Pollo al horno con queso",
    quantity: "2",
    timePreparation: "45 minutos",
    state: "remision",
    like: "liked",
    author: "Luna carrasco",
    image:
      "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    ingredient: [
      " 1 taza de harina leudante o harina de repostería",
      " 3 Unidad huevos",
      " 1 cda levadura",
      " 3 unidad huevos",
      " 3 unidad huevos",
    ],
    preparation: [
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
          "https://laverdadnoticias.com/__export/1626547403735/sites/laverdad/img/2021/07/17/receta_comida_saludable.jpg_478486366.jpg",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },

      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
    ],
  },
  {
    id: 4,
    title: "Pollo con verduras",
    quantity: "8",
    timePreparation: "2 horas",
    state: "remision",
    like: "liked",
    author: "Alvar Antonio silva",
    image:
      "https://www.cardamomo.news/__export/1642903893262/sites/debate/img/2022/01/22/comida_saludable.png_976912859.png",
    ingredient: [
      " 1 taza de harina leudante o harina de repostería",
      " 3 Unidad huevos",
      " 1 cda levadura",
      " 3 unidad huevos",
      " 3 unidad huevos",
    ],
    preparation: [
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },

      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
          "https://www.cardamomo.news/__export/1642903893262/sites/debate/img/2022/01/22/comida_saludable.png_976912859.png",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
    ],
  },
  {
    id: 5,
    title: "Pechuga a la plancha ",
    quantity: "2",
    timePreparation: "2 horas",
    state: "brote",
    like: "liked",
    author: "Alvar carrasco",
    image:
      "https://laverdadnoticias.com/__export/1626547403735/sites/laverdad/img/2021/07/17/receta_comida_saludable.jpg_478486366.jpg",
    ingredient: [
      " 1 taza de harina leudante o harina de repostería",
      " 3 Unidad huevos",
      " 1 cda levadura",
      " 3 unidad huevos",
      " 3 unidad huevos",
    ],
    preparation: [
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
          "https://www.lacocinasana.com/static/7b3046853e27aba28891d12702dea4a5/5e630/pasta_sin_gluten_sana_b67511ab23.jpg",
      },

      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image: "",
      },
    ],
  },
  {
    id: 6,
    title: "Pasta al tomate ",
    quantity: "6",
    timePreparation: "2 horas",
    state: "brote",
    like: "Notliked",
    author: "Carlo luna",
    image:
      "https://www.lacocinasana.com/static/7b3046853e27aba28891d12702dea4a5/5e630/pasta_sin_gluten_sana_b67511ab23.jpg",
    ingredient: [
      " 1 taza de harina leudante o harina de repostería",
      " 3 Unidad huevos",
      " 1 cda levadura",
      " 3 unidad huevos",
      " 3 unidad huevos",
    ],
    preparation: [
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
      "https://1.bp.blogspot.com/-FZXLk2jGajE/VsCU8vVL9pI/AAAAAAAAWfU/yVHGFkDosQg/s1600/blogs%2Bcomida%2Bsana%2B%25283%2529.jpg",
      },
      {
        descripton:
          "Hacer la receta de panqueques es súper fácil. Primero, reúne todos los ingredientes. Si no tienes harina leudante o preparada, que es harina de trigo que ya incluye levadura (también se llama harina de repostería), puedes usar harina de trigo normal y añadir una cucharadita de levadura química en polvo (polvo de hornear).",
        image:
      "https://1.bp.blogspot.com/-FZXLk2jGajE/VsCU8vVL9pI/AAAAAAAAWfU/yVHGFkDosQg/s1600/blogs%2Bcomida%2Bsana%2B%25283%2529.jpg",
      },
    ],
  },
];

*/


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

  searchContainer.appendChild(search);
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

  headCard.appendChild(list);
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
