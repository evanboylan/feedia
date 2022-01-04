package com.wcci.Feedia.repository;

import com.wcci.Feedia.model.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note, Long> {
}
