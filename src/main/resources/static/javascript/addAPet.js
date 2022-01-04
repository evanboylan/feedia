import {
    displayPetView
} from "./pet.js"

import {
    clearChildren
} from "./app.js"

function displayAddAPetView(containerEl){

    const petFormTopEl = document.createElement("div");
    petFormTopEl.classList.add("Add_Pet_Form_Top");

    const addPetFormTitleEl = document.createElement("a");
    addPetFormTitleEl.innerText = "Add Pet";

    // const importPetButtonDivEl = document.createElement("div");
    // importPetButtonDivEl.classList.add("Import");

    // const importPetButtonTextEl = document.createElement("p");
    // importPetButtonTextEl.innerText = "Import Pet:";

    // const importPetButtonEl = document.createElement("input");
    // importPetButtonEl.type = "text";
    // importPetButtonEl.name = "import_pet";

    // const hrEl = document.createElement("hr");

    petFormTopEl.appendChild(addPetFormTitleEl);
    // petFormTopEl.appendChild(importPetButtonDivEl);
    // petFormTopEl.appendChild(hrEl);
    // importPetButtonDivEl.appendChild(importPetButtonTextEl);
    // importPetButtonDivEl.appendChild(importPetButtonEl);

    const basicInputSectionEl = document.createElement("div");
    basicInputSectionEl.classList.add("Basic_Input");

    const basicInputNameTextEl = document.createElement("p");
    basicInputNameTextEl.innerText = "Name: ";

    const basicInputNameButtonEl = document.createElement("input");
    basicInputNameButtonEl.type = "text";
    basicInputNameButtonEl.name = "name";

    const basicInputAgeTextEl = document.createElement("p");
    basicInputAgeTextEl.innerText = "Age: ";

    const basicInputAgeButtonEl = document.createElement("input");
    basicInputAgeButtonEl.type = "number";
    basicInputAgeButtonEl.name = "age";

    const basicInputSexTextEl = document.createElement("p");
    basicInputSexTextEl.innerText = "Sex: ";

    const basicInputSexButtonEl = document.createElement("input");
    basicInputSexButtonEl.type = "text";
    basicInputSexButtonEl.name = "sex";

    basicInputSectionEl.appendChild(basicInputNameTextEl);
    basicInputSectionEl.appendChild(basicInputNameButtonEl);
    basicInputSectionEl.appendChild(basicInputAgeTextEl);
    basicInputSectionEl.appendChild(basicInputAgeButtonEl);
    basicInputSectionEl.appendChild(basicInputSexTextEl);
    basicInputSectionEl.appendChild(basicInputSexButtonEl);

    const inputContinuedSectionEl = document.createElement("div");
    inputContinuedSectionEl.classList.add("Input_Continued");

    const petImageTextEl = document.createElement("p");
    petImageTextEl.innerText = "Pet Image: ";

    const petImageButtonEl = document.createElement("input");
    petImageButtonEl.type = "text";
    petImageButtonEl.name = "pet_image";

    const speciesTextEl = document.createElement("p");
    speciesTextEl.innerText = "Species: ";

    const speciesButtonEl = document.createElement("input");
    speciesButtonEl.type = "text";
    speciesButtonEl.name = "species";

    inputContinuedSectionEl.appendChild(petImageTextEl);
    inputContinuedSectionEl.appendChild(petImageButtonEl);
    inputContinuedSectionEl.appendChild(speciesTextEl);
    inputContinuedSectionEl.appendChild(speciesButtonEl);

    const descriptionSectionEl = document.createElement("div");
    descriptionSectionEl.classList.add("Description");

    const descriptionTextEl = document.createElement("p");
    descriptionTextEl.innerText = "Description: ";

    const descriptionTextAreaEl = document.createElement("textarea");
    descriptionTextAreaEl.name = "Description:";
    descriptionTextAreaEl.rows = "5";
    descriptionTextAreaEl.cols = "40";

    const addAPetButton = document.createElement("button");
    addAPetButton.innerText = "Add Pet";
    addAPetButton.type = "add_pet";

    descriptionSectionEl.appendChild(descriptionTextEl);
    descriptionSectionEl.appendChild(descriptionTextAreaEl);
    descriptionSectionEl.appendChild(addAPetButton);

    addAPetButton.addEventListener("click", () => {
        const newReptileJson = {
            "name": basicInputNameButtonEl.value,
            "species": speciesButtonEl.value,
            "age": basicInputAgeButtonEl.value,
            "sex": basicInputSexButtonEl.value,
            "image": petImageButtonEl.value,
            "description": descriptionTextAreaEl.value,
            "needs": [],
            "schedules": [],
            "notes": []
        }
        fetch(`/reptiles/`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newReptileJson)
        })
        .then(res => res.json())
        .then(reptile => {
            clearChildren(containerEl);
            displayPetView(containerEl, reptile);
        })
        .catch(err => {console.error(err)})
    })

    containerEl.appendChild(petFormTopEl);
    containerEl.appendChild(basicInputSectionEl);
    containerEl.appendChild(inputContinuedSectionEl);
    containerEl.appendChild(descriptionSectionEl);
}

export{
    displayAddAPetView
};