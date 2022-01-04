package com.wcci.Feedia.controller;

import com.wcci.Feedia.model.Schedule;
import com.wcci.Feedia.repository.NeedRepository;
import com.wcci.Feedia.repository.NoteRepository;
import com.wcci.Feedia.repository.ReptileRepository;
import com.wcci.Feedia.repository.ScheduleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private ReptileRepository reptileRepo;
    private NeedRepository needRepo;
    private NoteRepository noteRepo;
    private ScheduleRepository scheduleRepo;

    public ScheduleController(ReptileRepository reptileRepo, NeedRepository needRepo, NoteRepository noteRepo, ScheduleRepository scheduleRepo) {
        this.reptileRepo = reptileRepo;
        this.needRepo = needRepo;
        this.noteRepo = noteRepo;
        this.scheduleRepo = scheduleRepo;
    }

    @GetMapping("/")
    public Iterable<Schedule> retrieveAllSchedules() {
        return scheduleRepo.findAll();
    }

    @PostMapping("/")
    public Iterable<Schedule> addSchedule(@RequestBody Schedule schedule) {
        scheduleRepo.save(schedule);
        return scheduleRepo.findAll();
    }

    @PutMapping("/")
    public Iterable<Schedule> editSchedule(@RequestBody Schedule scheduleToEdit) {
        if (scheduleToEdit.getId() != null) {
            scheduleRepo.save(scheduleToEdit);
        }
        return scheduleRepo.findAll();
    }
}
