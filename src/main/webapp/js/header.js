"use strict";

let currentPage = window.location.pathname;
const headerContainer = document.getElementById("header");

function userList(loggedUser) {
	let homeLink = "";
	const header = document.createElement("header");
	header.innerHTML = "";
	header.classList.add("container-header");

	let loginResult = `
   <a class="login-user" href="./login.html"> 
      <i class="fa-solid fa-circle-user fa-lg"></i>
      Iniciar sesión
   </a>`;
	if (loggedUser !== null) {
		loginResult = `
    <div class="link-header">
        <a class="link" href="./createCook.html">
           <i class="fa-solid fa-plus plus-ico"></i>
           <span>Crear</span>
        </a>           
       <div class="logged-user">
		     <i class="fa-solid fa-circle-user user"></i>
           <div class="user-log">
             <span>${loggedUser.userName}</span>
             <div>
               <a href="./SvLogout" alt="Cerrar sesión" title="Cerrar sesión"><i class="fa-solid fa-right-from-bracket fa-lg"></i></a>
               <a href="./updateUser.html" alt="Cambiar contraseña" title="Cambiar contraseña"><i class="fa-solid fa-key fa-lg"></i></a>
             </div>
           </div>  
       </div>  
    </div>`;
	}
	if (currentPage !== "/finalyProject/index.html") {
		homeLink = `
        <a class="link-home" href="./index.html">
            <img class="logo" src="./images/logo.png" alt="logo" /> 
        </a>`;
	} else {
		homeLink = `
        <img class="logo" src="./images/logo.png" alt="logo" /> 
      `;
	}

	header.innerHTML = `
       ${homeLink}  
       ${loginResult}
     `;
	headerContainer.appendChild(header);
}

fetchUsers()
	.then(userList)
	.catch(error => {
		console.error("Error al obtener los datos:", error);
	});

