package com.wcci.Feedia.controller;

import com.wcci.Feedia.model.Need;
import com.wcci.Feedia.model.Reptile;
import com.wcci.Feedia.repository.NeedRepository;
import com.wcci.Feedia.repository.NoteRepository;
import com.wcci.Feedia.repository.ReptileRepository;
import com.wcci.Feedia.repository.ScheduleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/needs")
public class NeedController {

    private ReptileRepository reptileRepo;
    private NeedRepository needRepo;
    private NoteRepository noteRepo;
    private ScheduleRepository scheduleRepo;

    public NeedController(ReptileRepository reptileRepo, NeedRepository needRepo, NoteRepository noteRepo, ScheduleRepository scheduleRepo) {
        this.reptileRepo = reptileRepo;
        this.needRepo = needRepo;
        this.noteRepo = noteRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @GetMapping("/")
    public Iterable<Need> retrieveAllNeeds() {
        return needRepo.findAll();
    }

    @PostMapping("/")
    public Iterable<Need> addNeed(@RequestBody Need need, @RequestParam Long id) {
        Reptile reptileToModify = reptileRepo.findById(id).get();
        need.setReptile(reptileToModify);
        needRepo.save(need);
        return needRepo.findAll();
    }

    @PutMapping("/")
    public Iterable<Need> editNeed (@RequestBody Need needToEdit) {
        if (needToEdit.getId() != null) {
            needRepo.save(needToEdit);
        }
        return needRepo.findAll();
    }
}
