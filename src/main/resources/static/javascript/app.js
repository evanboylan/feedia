import {displayPetView} from "./pet.js";
import {displayPetsView} from "./pets.js";
import {displayAddAPetView} from "./addAPet.js";

const containerEl = document.querySelector(".container");

buildHeader();
buildMainElement();
buildFooter();

function clearChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.lastChild);
    }
};

function buildHeader() {
    const sectionEl = document.createElement("section");
    const divEl = document.createElement("div");
    divEl.classList.add("site_links");

    const headerEl = document.createElement("header");
    headerEl.id = "header";

    const headerLogoEl = document.createElement("img");
    headerLogoEl.src= "images/feedia_logo.png";
    headerLogoEl.alt = "site logo";
    headerLogoEl.classList.add("logo");
    headerLogoEl.classList.add("clickable_el");
    headerLogoEl.addEventListener("click", () => {
        window.location = "./index.html";
    }) 

    const addPetButtonDivEl = document.createElement("div");
    addPetButtonDivEl.classList.add("site_links");

    const addPetPTagEl = document.createElement("p");
    addPetPTagEl.addEventListener("click", () => {
        const mainEl = document.querySelector(".main-content");
        clearChildren(mainEl);
        displayAddAPetView(mainEl);
    })

     const addPetITagEl = document.createElement("i");
    addPetITagEl.classList.add("fa-egg");
    addPetITagEl.classList.add("fas");

    const addPetATagEl = document.createElement("a");
    addPetATagEl.href = "#";
    addPetATagEl.innerText = "Add Pet";

    addPetButtonDivEl.appendChild(addPetPTagEl);
    addPetPTagEl.appendChild(addPetATagEl);
    addPetATagEl.appendChild(addPetITagEl);
    
    sectionEl.appendChild(headerEl);
    headerEl.appendChild(headerLogoEl);
    headerEl.appendChild(addPetButtonDivEl);
    containerEl.appendChild(headerEl);
}

function buildMainElement(){
    const mainEl = document.createElement("main");
    mainEl.classList.add("main-content");
    containerEl.appendChild(mainEl);
    fetch("/reptiles/")
    .then(res => res.json())
    .then(reptiles => {
        displayPetsView(mainEl, reptiles);
    })
}

function buildFooter() {
    const footerEl = document.createElement("footer");
    const footerDivEl = document.createElement("div");
    footerDivEl.classList.add("footer_text");
    const footerTextEl = document.createElement("p");
    footerTextEl.innerText = "We Can Code IT 2021"; // removed &copy;
    footerDivEl.appendChild(footerTextEl);
    footerEl.appendChild(footerDivEl);
    containerEl.appendChild(footerEl);
}

export {
    clearChildren
};