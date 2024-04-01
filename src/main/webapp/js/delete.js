
function onDeleteButtonClick(id) {
    fetch(`/finalyProject/SVDelete?id=${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('La solicitud no fue exitosa');
        }
        return response.json(); // Convertir la respuesta a JSON
    })
    .catch(error => {
        console.error('No se pudo eliminar la receta:', error);
    });
}
