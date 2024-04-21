
function fetchDelete(id) {
	fetch(`/finalyProject/SVDelete?id=${id}`, {
		method: 'DELETE'
	})
		.then(response => {	
				window.location.href = './'	
		})
		.catch(error => {
			console.error('No se pudo eliminar la receta:', error);
		});
}
