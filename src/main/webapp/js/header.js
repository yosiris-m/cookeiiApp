"use strict"

class Header extends HTMLElement {
  constructor() {
    super();
  }

  connectedCallback() {
    this.innerHTML = `
      <header id="header" class="container-header">
        <img class="logo" src="./images/logo.png" alt="logo" /> 
        <aside class="box-nav">
          <div class="botons"> 
            <span id="open" class="open"><i class="fa-solid fa-bars"></i></span>
            <span id="close" class="close"><i class="fa-solid fa-x"></i></span>
          </div>
          <nav class="options-menu">
            <a class="link" href="./login.html">Iniciar sesión</a>
            <a class="link" href="./createAcount.html">Crear cuenta</a>
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

customElements.define("common-header", Header);
