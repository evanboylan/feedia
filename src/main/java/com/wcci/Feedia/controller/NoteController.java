package com.wcci.Feedia.controller;

import com.wcci.Feedia.model.Note;
import com.wcci.Feedia.model.Reptile;
import com.wcci.Feedia.repository.NeedRepository;
import com.wcci.Feedia.repository.NoteRepository;
import com.wcci.Feedia.repository.ReptileRepository;
import com.wcci.Feedia.repository.ScheduleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private ReptileRepository reptileRepo;
    private NeedRepository needRepo;
    private NoteRepository noteRepo;
    private ScheduleRepository scheduleRepo;

    public NoteController(ReptileRepository reptileRepo, NeedRepository needRepo, NoteRepository noteRepo, ScheduleRepository scheduleRepo) {
        this.reptileRepo = reptileRepo;
        this.needRepo = needRepo;
        this.noteRepo = noteRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @GetMapping("/")
    public Iterable<Note> retrieveAllNotes() {
        return noteRepo.findAll();
    }

    @PostMapping("/{id}")
    public Reptile addNote(@PathVariable Long id, @RequestBody Note note) {
        Reptile returnReptile = reptileRepo.findById(id).get();
        note.setReptile(returnReptile);
        noteRepo.save(note);
//        reptileRepo.save(returnReptile);
        return returnReptile;
    }

    @PutMapping("/")
    public Iterable<Note> editNote(@RequestBody Note noteToEdit) {
        if (noteToEdit.getId() != null) {
            noteRepo.save(noteToEdit);
        }
        return noteRepo.findAll();
    }

    @DeleteMapping("/{id}")
    public Reptile deleteNote(@PathVariable Long id){
        Note noteToDelete = noteRepo.findById(id).get();
        Reptile returnReptile = noteToDelete.getReptile();
        noteRepo.deleteById(id);
        return returnReptile;
    }
}
