"use strict";

function fetchDetail(id) {
  return fetch(`/finalyProject/SvCookDetail?id=${id}`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("No se pudo obtener la respuesta de la API");
      }
      return response.json();
    })
    .then((data) => {
      return data;
    })
    .catch((error) => {
      console.error('Error al obtener el detalle los datos:', error);
    });
}