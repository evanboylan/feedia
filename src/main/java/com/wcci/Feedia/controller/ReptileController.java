package com.wcci.Feedia.controller;

import com.wcci.Feedia.model.Note;
import com.wcci.Feedia.model.Reptile;
import com.wcci.Feedia.model.Schedule;
import com.wcci.Feedia.model.TempHumidity;
import com.wcci.Feedia.model.GoogleCalendar;
import com.wcci.Feedia.repository.*;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RestController
@RequestMapping("/reptiles")
public class ReptileController {

    private ReptileRepository reptileRepo;
    private NeedRepository needRepo;
    private NoteRepository noteRepo;
    private ScheduleRepository scheduleRepo;
    private GoogleCalendarRepo googleCalendarRepo;

    public ReptileController(ReptileRepository reptileRepo, NeedRepository needRepo, NoteRepository noteRepo, ScheduleRepository scheduleRepo, GoogleCalendarRepo googleCalendarRepo) {
        this.reptileRepo = reptileRepo;
        this.needRepo = needRepo;
        this.noteRepo = noteRepo;
        this.scheduleRepo = scheduleRepo;
        this.googleCalendarRepo = googleCalendarRepo;
    }

    @GetMapping("/")
    public Iterable<Reptile> retrieveAllReptiles(){
        return reptileRepo.findAll();
    }

    @GetMapping("/{id}")
    public Reptile retrieveSingleReptile(@PathVariable Long id) {
        return reptileRepo.findById(id).get();
    }

    @PostMapping("/")
    public Reptile addReptile(@RequestBody Reptile reptile) throws GeneralSecurityException, IOException {
        reptileRepo.save(reptile);
        reptile.myCalendar.setReptile(reptile);
        reptile.createCalendar();
        googleCalendarRepo.save(reptile.getMyCalendar());
        return reptile;
    }

    @PutMapping("/")
    public Iterable<Reptile> editReptile(@RequestBody Reptile reptileToEdit) {
        if (reptileToEdit.getId() != null) {
            reptileRepo.save(reptileToEdit);
        }
        return reptileRepo.findAll();
    }

    @PatchMapping("/{id}/update")
    public Reptile updateReptileData(@PathVariable Long id, @RequestBody TempHumidity newData){
        Reptile reptileToUpdate = reptileRepo.findById(id).get();
        reptileToUpdate.setHumidity(newData.getHumidity());
        reptileToUpdate.setTemp(newData.getTemp());
        reptileRepo.save(reptileToUpdate);
        return reptileRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public Iterable<Reptile> deleteReptile(@PathVariable Long id){
        reptileRepo.deleteById(id);
        return reptileRepo.findAll();
    }

//    @PostMapping("/{id}/addNote")
//    public Reptile addNote(@PathVariable Long id, @RequestBody Note noteToAdd) {
//        Reptile reptileToEdit = reptileRepo.findById(id).get();
//        noteToAdd.setReptile(reptileToEdit);
//        noteRepo.save(noteToAdd);
//        return reptileRepo.findById(id).get();
//    }
}
