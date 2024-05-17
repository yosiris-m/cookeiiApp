"use strict";

function handleSubmitForm(event) {
  event.preventDefault();

  const title = document.getElementById("title").name
  const quantity = document.getElementById("quantity").value;
  const timePreparation = document.getElementById("timePreparation").value;
  const author = document.getElementById("author").value;
  const photo = document.getElementById("fileInput").value;
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
    };
  });
  
  const data = {
    title: title,
    quantity: quantity,
    timePreparation: timePreparation,
    author: author,
    photo: photo,
    state: state,
    ingredient: ingredient,
    preparations: preparations 
  }

fetch("/finalyProject/SvCooks", {
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

}