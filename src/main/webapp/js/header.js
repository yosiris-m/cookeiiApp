"use strict"

let currentPage = window.location.pathname;
const headerContainer = document.getElementById("header");

function userList(loggedUser) {
	let homeLink = '';
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
           <div class="logued-user">
            <i class="fa-solid fa-circle-user user"></i>
              <div class="user-log">
                <span>${loggedUser.userName}</span>
                <a class="" href="./SvLogout">Cerrar sesión</a>
               </div>  
            </div>  
        </div>`;
	}

	if (currentPage !== "/finalyProject/index.html") {
		homeLink =
			`<a class="link-home" href="./index.html">
            <i class="fa-solid fa-caret-left fa-lg"></i>
        </a>`;
	}

	header.innerHTML = `
        <img class="logo" src="./images/logo.png" alt="logo" /> 
         ${homeLink}  
        ${loginResult}
       
    `;
	headerContainer.appendChild(header);
}

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
