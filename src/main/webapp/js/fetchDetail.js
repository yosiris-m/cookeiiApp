"use strict";

function fetchDetail() {
  return fetch(`/finalyProject/SvCookDetail?id=${id}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("No se pudo obtener la respuesta de la API");
      }
      return response.json();
    })
    .then((data) => {
      console.log("fetchData---->", data);
      return data;
    })
    .catch((error) => {
      console.error('Error al obtener el detalle los datos:', error);
    });
}