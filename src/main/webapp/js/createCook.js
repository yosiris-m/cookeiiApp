"use strict";
// function handleSetFormTitle(action) {
//     const titleElement = document.getElementById('title-createC');
//     if (action === 'create') {
//       titleElement.textContent = 'Crear nueva Receta';
//     } else if (action === 'update') {
//       titleElement.textContent = 'Actualizar Receta';
//     }
//   }


function handleSubmitForm(event) {
  event.preventDefault();
  let servletUrl = "/finalyProject/SvCooks"; 

  const title = document.getElementById("title").name
  const quantity = document.getElementById("quantity").value;
  const timePreparation = document.getElementById("timePreparation").value;
  const author = document.getElementById("author").value;
 // const image = document.getElementById("fileInput").value;
  const state = document.querySelector('input[name="state"]:checked').id;
  const ingredientElements = document.querySelectorAll(
    "#element-Ing .input-focu1"
  );
  const preparationList = document.querySelectorAll(
    "#element-prep .input-prep"
  );


  let ingredient = Array.from(ingredientElements).map((input) => input.value);

  const preparations = Array.from(preparationList).map((prep) => {
    const text = prep.querySelector(".input-focu").value;
    const fileInput = prep.querySelector("input[type=file]");
    const file = fileInput.files[0];

    return {
      text: text,
      fileName: file ? file.name : null,
      fileSize: file ? file.size : null,
      fileType: file ? file.type : null,
    };
  });
  
  const data = {
    title: title,
    quantity: quantity,
    timePreparation: timePreparation,
    author: author,
    image: image,
    state: state,
    ingredient: ingredient,
    preparations: preparations 
  }

fetch(servletUrl, {
  method: 'POST',
  credentials: 'include',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(data)
})
.then(response => response.json())
.then(data =>  {
  console.log("La respuesta", data);
})

.catch(error => {
  console.error("Error al enviar los datos", error);
});

  console.log(
    "create cook-->",
    "title->",
    title,
    "comensales ->",
    quantity,
    "tiempo Preparation:",
    timePreparation,
    "autor;",
    author,
   // "imagen principal:",
   // image,
    "stado:",
    state,
    "ingrediente:",
    ingredient,
    "preparation",
    preparations
  );

}