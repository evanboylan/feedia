package com.wcci.Feedia;

import com.wcci.Feedia.model.Need;
import com.wcci.Feedia.model.Note;
import com.wcci.Feedia.model.Reptile;
import com.wcci.Feedia.model.Schedule;
import com.wcci.Feedia.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Populator implements CommandLineRunner {

    private ReptileRepository reptileRepo;
    private NeedRepository needRepo;
    private NoteRepository noteRepo;
    private ScheduleRepository scheduleRepo;
    private GoogleCalendarRepo googleCalendarRepo;

    public Populator(ReptileRepository reptileRepo, NeedRepository needRepo, NoteRepository noteRepo, ScheduleRepository scheduleRepo, GoogleCalendarRepo googleCalendarRepo) {
        this.reptileRepo = reptileRepo;
        this.needRepo = needRepo;
        this.noteRepo = noteRepo;
        this.scheduleRepo = scheduleRepo;
        this.googleCalendarRepo = googleCalendarRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        Reptile reptile1 = new Reptile("Kaiju", "Gecko", 4, "Male","../images/kaiju.png", "Well-behaved, likes to eat.", 0f, 0f);
        reptileRepo.save(reptile1);
        reptile1.createCalendar();
        reptile1.myCalendar.createEvent("Feed Kaiju", "Guest Room", "Five crickets, kale, and tomatoes.",
                "2021-12-08T12:30:00.000-05:00", "2021-12-08T12:45:00.000-05:00", "WEEKLY", "50");
        reptile1.myCalendar.setReptile(reptile1);
        googleCalendarRepo.save(reptile1.getMyCalendar());


        Reptile reptile2 = new Reptile("Cayde", "Leopard Gecko", 6, "Male","../images/cayde.png", "Loves to perch, eat anything that moves.", 0f, 0f);
        reptileRepo.save(reptile2);
        reptile2.createCalendar();
        reptile2.myCalendar.setReptile(reptile2);
        googleCalendarRepo.save(reptile2.getMyCalendar());

        Reptile reptile3 = new Reptile("Ginger", "Leopard Gecko", 3, "Female","../images/ginger.jpg", "Has a big appetite, likes vegetables.", 0f, 0f);
        reptileRepo.save(reptile3);
        reptile3.createCalendar();
        reptile3.myCalendar.setReptile(reptile3);
        googleCalendarRepo.save(reptile3.getMyCalendar());

        Reptile reptile4 = new Reptile("Cosmo", "Chameleon", 4, "Male","../images/cosmo.jpg", "Likes to relax, lounge anywhere available.", 0f, 0f);
        reptileRepo.save(reptile4);
        reptile4.createCalendar();
        reptile4.myCalendar.setReptile(reptile4);
        googleCalendarRepo.save(reptile4.getMyCalendar()); //test

        Need need1 = new Need("Medicine Twice Daily", reptile1);
        needRepo.save(need1);

        Note note1 = new Note("Observation:", "Seems to prefer mealworms to silkworms.", true, reptile1);
        noteRepo.save(note1);
//        note1.setReptile(reptile1);
//        reptileRepo.save(reptile1);

        Note note2 = new Note("Observation:", "They seemed to love the waxworm but should cut those back to once a week.", true, reptile2);
        noteRepo.save(note2);
//        note2.setReptile(reptile2);
//        reptileRepo.save(reptile2);

        Note note3 = new Note("Observation:", "Noticed they really seemed active today.", true, reptile3);
        noteRepo.save(note3);
//        note3.setReptile(reptile3);
//        reptileRepo.save(reptile3);

        Note note4 = new Note("Observation:", "Noticed they eat orange foods like squash before green.", true, reptile4);
        noteRepo.save(note4);
//        note4.setReptile(reptile4);
//        reptileRepo.save(reptile4);

        Schedule schedule1 = new Schedule("Kaiju's Schedule", "Schedule for Feeding", 12, reptile1);
        scheduleRepo.save(schedule1);

        
    }
}
