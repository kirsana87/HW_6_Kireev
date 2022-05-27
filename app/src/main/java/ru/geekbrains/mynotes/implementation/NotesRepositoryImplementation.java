package ru.geekbrains.mynotes.implementation;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.geekbrains.mynotes.model.entities.NoteEntity;
import ru.geekbrains.mynotes.model.repos.NotesRepository;

public class NotesRepositoryImplementation implements NotesRepository {
    private final ArrayList<NoteEntity> cache = new ArrayList<>();

    @Override
    public List<NoteEntity> getNotes() {
        return new ArrayList<>(cache);
    }

    @Nullable
    @Override
    public String createNote(NoteEntity note) {
        String newId = UUID.randomUUID().toString();
        note.setUid(newId);
        cache.add(note);
        return newId;
    }

    @Override
    public boolean deleteNote(String uid) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getUid().equals(uid)) {
                cache.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateNote(String uid, NoteEntity note) {
        deleteNote(uid);
        note.setUid(uid);
        cache.add(note);
        return true;
    }
}
