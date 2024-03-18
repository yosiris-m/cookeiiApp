"use strict";
// function handleSubmitForm(event) {
document.addEventListener("DOMContentLoaded", function (event) {  
  event.preventDefault();
  
  //const updateId = document.getElementById("updateId");
    
  const urlParams = new URLSearchParams(window.location.search);
  
  // urlParams.forEach((value, key) => {
  //   console.log("URL Params:",key, value);
  // });


  const title = urlParams.get("title");
  const quantity = urlParams.get("quantity");
  const timePreparation = urlParams.get("timePreparation");
  const author = urlParams.get("author");
  const state = urlParams.get("state")
  const ingredients = urlParams.get("ingredients")
  const preparation = urlParams.get("preparations")
  

  document.getElementById("title").value = title;
  document.getElementById("quantity").value = quantity;
  document.getElementById("timePreparation").value = timePreparation;
  document.getElementById("author").value = author;
  const imgPreview = document.getElementById("imgPreview")
  
  
    const imgUrl = urlParams.get("file");
    showUpdateImg(imgPreview, imgUrl)  

  const radioInput = document.querySelector(`input[value="${state}"]`);
  radioInput ? radioInput.checked = true : radioInput.checked = false;

  showIngredientInput(ingredients)

  addPreparationStep(preparation);
  

  
}
)