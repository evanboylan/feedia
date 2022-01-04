import {
    clearChildren
} from "./app.js"

import{
    displayPetsView
} from "./pets.js"

function displayPetView(mainEl, reptile, reptiles) {

    const singlePetDisplayAnchorEl = document.createElement("div");
    singlePetDisplayAnchorEl.classList.add("anchor");

    const singlePetDisplayContainerEl = document.createElement("div");
    singlePetDisplayContainerEl.classList.add("container");
    singlePetDisplayContainerEl.classList.add("single_pet_view_container");

    const singlePetInfoSectionEl = document.createElement("section");
    singlePetInfoSectionEl.classList.add("first_section")
   
    const singlePetDisplayDivEl = document.createElement("div");
    singlePetDisplayDivEl.classList.add("single_pet");

    const singlePetPetInfoDivEl = document.createElement("div");
    singlePetPetInfoDivEl.classList.add("pet_info");

    const singlePetPortraitDivEl = document.createElement("div");
    singlePetPortraitDivEl.classList.add("pet_portrait");

    const singlePetPortraitEl = document.createElement("img");
    singlePetPortraitEl.src = reptile.image;
    singlePetPortraitEl.alt = "pet portrait";
    singlePetPortraitEl.classList.add("pet_portrait");

    const petDataDivEl = document.createElement("div");
    petDataDivEl.classList.add("pet_data_div");

    const singlePetDemographicsDivEl = document.createElement("div");
    singlePetDemographicsDivEl.classList.add("pet_demographics");

    const tempHumidityDivEl = document.createElement("div");
    tempHumidityDivEl.classList.add("temp_humidity_div")

    petDataDivEl.appendChild(singlePetDemographicsDivEl);
    petDataDivEl.appendChild(tempHumidityDivEl);

    const tempDisplayEl = document.createElement("p");
    tempDisplayEl.classList.add("temp_data_display");
    tempDisplayEl.innerText = 'Temperature: ' + reptile.temp + '°C';

    const humidityDisplayEl = document.createElement("p");
    humidityDisplayEl.classList.add("humidity_data_display");
    humidityDisplayEl.innerText = 'Humidity: ' + reptile.humidity + '%';

    const singlePetNameEl = document.createElement("p");
    singlePetNameEl.innerText = 'Name: ' + reptile.name;

    const singlePetAgeEl = document.createElement("p");
    singlePetAgeEl.innerText = 'Age: ' + reptile.age;

    const singlePetSexEl = document.createElement("p");
    singlePetSexEl.innerText = 'Sex: ' + reptile.sex;

    singlePetDisplayAnchorEl.appendChild(singlePetDisplayContainerEl);
    singlePetDisplayContainerEl.appendChild(singlePetInfoSectionEl);
    singlePetInfoSectionEl.appendChild(singlePetDisplayDivEl);
    singlePetDisplayDivEl.appendChild(singlePetPetInfoDivEl);
    singlePetPetInfoDivEl.appendChild(singlePetPortraitDivEl);
    singlePetPetInfoDivEl.appendChild(petDataDivEl);
    singlePetPortraitDivEl.appendChild(singlePetPortraitEl);
    tempHumidityDivEl.appendChild(tempDisplayEl);
    tempHumidityDivEl.appendChild(humidityDisplayEl);
    singlePetDemographicsDivEl.appendChild(singlePetNameEl);
    singlePetDemographicsDivEl.appendChild(singlePetAgeEl);
    singlePetDemographicsDivEl.appendChild(singlePetSexEl);

    const singlePetTopTableSectionEl = document.createElement("section");
    singlePetTopTableSectionEl.classList.add("second_section");

    const singlePetTablesTopEl = document.createElement("div");
    singlePetTablesTopEl.classList.add("tables_top");

    const singlePetCareTableEl = document.createElement("div");
    singlePetCareTableEl.classList.add("pet_care_table");

    const singlePetTableLinksEl = document.createElement("div");
    singlePetTableLinksEl.classList.add("table_links");

    const singlePetTableTitleEl = document.createElement("h4");
    singlePetTableTitleEl.innerText = "Schedule";

    const singlePetTableItemsDivEl = document.createElement("div");
    singlePetTableItemsDivEl.classList.add("table_items");

    const petAgendaCalendarDivEl = document.createElement("div");
    petAgendaCalendarDivEl.classList.add('pet_calendar_div');
    petAgendaCalendarDivEl.innerHTML = `<iframe src="https://calendar.google.com/calendar/embed?src=${reptile.myCalendar.googleCalendarId}&mode=AGENDA&showPrint=0&showTabs=0&bgcolor=%231a8d26" style="border:solid 1px #777" width="400" height="300" frameborder="0" scrolling="no"></iframe>`

    const singlePetCareCalendarsTableEl = document.createElement("div");
    singlePetCareCalendarsTableEl.classList.add("pet_care_table");

    const singlePetCalendarsTableLinksEl = document.createElement("div");
    singlePetCalendarsTableLinksEl.classList.add("table_links");

    const singlePetTableCalendarsTitleEl = document.createElement("h4");
    singlePetTableCalendarsTitleEl.innerText = "Calendar";

    const singlePetTableCalendarsDivEl = document.createElement("div");
    singlePetTableCalendarsDivEl.classList.add("table_items");

    const petMonthCalendarDivEl = document.createElement("div");
    petMonthCalendarDivEl.classList.add('pet_calendar_div');
    petMonthCalendarDivEl.innerHTML = `<iframe src="https://calendar.google.com/calendar/embed?src=${reptile.myCalendar.googleCalendarId}&showPrint=0&showTabs=0&bgcolor=%231a8d26" style="border:solid 1px #777" width="400" height="300" frameborder="0" scrolling="no"></iframe>`

    singlePetTopTableSectionEl.appendChild(singlePetTablesTopEl);
    singlePetTablesTopEl.appendChild(singlePetCareTableEl);
    singlePetTablesTopEl.appendChild(singlePetCareCalendarsTableEl);
    singlePetCareTableEl.appendChild(singlePetTableLinksEl);
    singlePetCareTableEl.appendChild(singlePetTableItemsDivEl);
    singlePetTableLinksEl.appendChild(singlePetTableTitleEl);
    singlePetTableItemsDivEl.appendChild(petAgendaCalendarDivEl);


    const singlePetTablesSectionEl = document.createElement("section");
    singlePetTablesSectionEl.classList.add("third_section");

    const singlePetCareNotesTableEl = document.createElement("div");
    singlePetCareNotesTableEl.classList.add("pet_care_table");

    const singlePetNotesTableLinksEl = document.createElement("div");
    singlePetNotesTableLinksEl.classList.add("table_links");
    
    const singlePetAddNoteDivEl = document.createElement("div");
    singlePetAddNoteDivEl.classList.add("add_note_section");

    const singlePetTableNotesTitleEl = document.createElement("h4");
    singlePetTableNotesTitleEl.innerText = "Notes";

    const notesDisplayDivEl = document.createElement("div");
    notesDisplayDivEl.classList.add("notes_display_div");

    reptile.notes.forEach(note => {
        const noteDisplayBoxEl = document.createElement("div");
        noteDisplayBoxEl.classList.add("note_display_box");

        const noteTitleEl = document.createElement("p");
        noteTitleEl.innerText = note.name;

        const noteTextEl = document.createElement("p");
        noteTextEl.innerText = note.description;

        const trashButtonIconEl = document.createElement("i");
        trashButtonIconEl.classList.add("far");
        trashButtonIconEl.classList.add("fa-trash-alt");
        trashButtonIconEl.classList.add("clickable_el");
        trashButtonIconEl.addEventListener("click", () => {
            fetch(`/notes/${note.id}`, {
                method: 'DELETE'
            })
            .then(res => res.json())
            .then(reptile => {
                clearChildren(mainEl);
                displayPetView(mainEl, reptile);
            })
            .catch(err => console.error(err));
        })

        noteDisplayBoxEl.appendChild(noteTitleEl);
        noteDisplayBoxEl.appendChild(noteTextEl);
        noteDisplayBoxEl.appendChild(trashButtonIconEl);

        notesDisplayDivEl.appendChild(noteDisplayBoxEl);
    })

    const addNoteNameEl = document.createElement("input");
    addNoteNameEl.type = "text";
    addNoteNameEl.placeholder = "Note Title: ";

    const addNoteTextAreaEl = document.createElement("textarea");
    addNoteTextAreaEl.rows = "4";
    addNoteTextAreaEl.cols = "40";
    addNoteTextAreaEl.placeholder = "Enter Your Note Here";

    const singlePetAddNotesEl = document.createElement("button");
    singlePetAddNotesEl.innerText = "Add Note";
    singlePetAddNotesEl.classList.add("clickable_el");
    // singlePetAddNotesEl.href = "#";
    singlePetAddNotesEl.addEventListener("click", () => {
        const newNoteJson = {
            "name": addNoteNameEl.value,
            "description": addNoteTextAreaEl.value,
            "isPinned": "True"
        }
        fetch(`/notes/${reptile.id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newNoteJson)
        })
        .then(res => res.json())
        .then(reptile => {
            clearChildren(mainEl);
            displayPetView(mainEl, reptile);
        })
        .catch(err => {console.error(err)})
    })

    const notesTitleAndNotesDiv = document.createElement("div");
    notesTitleAndNotesDiv.classList.add("notes_display_section");
    notesTitleAndNotesDiv.appendChild(singlePetTableNotesTitleEl);
    notesTitleAndNotesDiv.appendChild(notesDisplayDivEl);


    singlePetAddNoteDivEl.appendChild(addNoteNameEl);
    singlePetAddNoteDivEl.appendChild(addNoteTextAreaEl);
    singlePetAddNoteDivEl.appendChild(singlePetAddNotesEl);
       
    singlePetTableCalendarsDivEl.appendChild(petMonthCalendarDivEl);
    
    // const singlePetTableCalendarsListEl = document.createElement("ul");

    // const singlePetTableCalendars1El = document.createElement("li");
    // singlePetTableCalendars1El.innerText = "Placeholder";

    // const singlePetTableCalendars2El = document.createElement("li");
    // singlePetTableCalendars2El.innerText = "Placeholder";

    // const singlePetTableCalendars3El = document.createElement("li");
    // singlePetTableCalendars3El.innerText = "Placeholder";

    const petArchiveTextEl = document.createElement("button");
    petArchiveTextEl.innerText = "Archive Pet";
    petArchiveTextEl.classList.add("archive_pet_button")
    petArchiveTextEl.classList.add("clickable_el");
    petArchiveTextEl.addEventListener("click", () => {
        fetch(`/reptiles/${reptile.id}`, {
            method: 'DELETE'
        })
        .then(res => res.json())
        .then(reptiles => {
            clearChildren(mainEl);
            displayPetsView(mainEl, reptiles);
        })
        .catch(err => console.error(err));
    });

    singlePetTablesSectionEl.appendChild(singlePetCareNotesTableEl);
    singlePetCareNotesTableEl.appendChild(singlePetNotesTableLinksEl);
    singlePetNotesTableLinksEl.appendChild(notesTitleAndNotesDiv);
    singlePetNotesTableLinksEl.appendChild(singlePetAddNoteDivEl);

    // singlePetTablesSectionEl.appendChild(singlePetCareCalendarsTableEl);
    singlePetCareCalendarsTableEl.appendChild(singlePetCalendarsTableLinksEl);    
    singlePetCareCalendarsTableEl.appendChild(singlePetTableCalendarsDivEl);
    singlePetCalendarsTableLinksEl.appendChild(singlePetTableCalendarsTitleEl);
    // singlePetCalendarsTableLinksEl.appendChild(singlePetAddCalendarsEl);
    // singlePetTableCalendarsDivEl.appendChild(singlePetTableCalendarsListEl);
    // singlePetTableCalendarsListEl.appendChild(singlePetTableCalendars1El);
    // singlePetTableCalendarsListEl.appendChild(singlePetTableCalendars2El);
    // singlePetTableCalendarsListEl.appendChild(singlePetTableCalendars3El);

    singlePetDisplayContainerEl.appendChild(singlePetTopTableSectionEl);
    singlePetDisplayContainerEl.appendChild(singlePetTablesSectionEl);
    singlePetDisplayContainerEl.appendChild(petArchiveTextEl);

    mainEl.appendChild(singlePetDisplayAnchorEl);
    
}

export {
    displayPetView
};