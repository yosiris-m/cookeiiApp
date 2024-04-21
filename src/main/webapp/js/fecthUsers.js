 "use strict";
/**
 * 
 */
function fetchUsers() {
  return fetch("/finalyProject/SvUser")
    .then((response) => {
      if (!response.ok) {
        throw new Error("No se pudo obtener la respuesta de la API");
      }
      return response.json();
    })
    .then((data) => {
		console.log(data)
      return data;
    })
    .catch((error) => {
      console.error("Error al obtener los datos:", error);
    });
}