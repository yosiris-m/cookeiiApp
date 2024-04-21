"use strict"

const headerContainer = document.getElementById("header");
function userList(loggedUser) {
	console.log("loggedUser", loggedUser)
	headerContainer.innerHTML = "";

	let loginResult = '<a class="link" href="./login.html">Iniciar sesión</a>';
	if (loggedUser !== null) {
		loginResult = `<div>
		 <a class="link" href="./createCook.html">Crear receta</a>
         <i class="fa-solid fa-circle-user"></i>    
          <p>
         	<span>${loggedUser.userName}(${loggedUser.email})</span/>
             <a class="link" href="./SvLogout">Cerrar sesión</a>
          </p>
        </div>`
	}

	const header = document.createElement("header");

	header.classList.add("container-header");
	header.innerHTML = `
	    <img class="logo" src="./images/logo.png" alt="logo" /> 
	   
         
        ${loginResult}
         `;
	headerContainer.appendChild(header);
}
//containerHeader();
//userData();
fetchUsers()
	.then(userList)
	.catch(error => {
		console.error('Error al obtener los datos:', error);
	});


/*class Header extends HTMLElement {
  constructor() {
	super();
  }

  connectedCallback() {
	this.innerHTML = `
	  <header id="header" class="container-header">
		<img class="logo" src="./images/logo.png" alt="logo" /> 
		 <a class="link" href="./createAcount.html">Crear cuenta</a>
		<div>
		  <p>
		  <i class="fa-solid fa-circle-user"></i>
			<span>usuario</span/>
		  </p>
		  </div>
		<aside class="box-nav">
		  <div class="botons"> 
			<span id="open" class="open"><i class="fa-solid fa-bars"></i></span>
			<span id="close" class="close"><i class="fa-solid fa-x"></i></span>
		  </div>
		  <nav class="options-menu">
			<a class="link" href="./login.html">Iniciar sesión</a>
		   
			<a class="link" href="./createCook.html">Crear receta</a>
		  </nav>
		</aside>  
	  </header>
	`;

	this.addEventsToHeader();
  }
  
  

  addEventsToHeader() {
	const open = this.querySelector(".open");
	const close = this.querySelector(".close");
	const options = this.querySelector(".options-menu");

	open.addEventListener("click", openMenu);
	close.addEventListener("click", closeMenu);

	function openMenu() {
	  open.style.display = "none";
	  close.style.display = "block";
	  options.style.visibility = "visible";
	}

	function closeMenu() {
	  open.style.display = "block";
	  close.style.display = "none";
	  options.style.visibility = "hidden";
	}
  }
}

customElements.define("common-header", Header);*/
