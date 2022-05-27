package ru.geekbrains.mynotes;

import android.app.Application;

import ru.geekbrains.mynotes.implementation.NotesRepositoryImplementation;
import ru.geekbrains.mynotes.model.entities.NoteEntity;
import ru.geekbrains.mynotes.model.repos.NotesRepository;

public class App extends Application {

    private final NotesRepository notesRepository = new NotesRepositoryImplementation();

    @Override
    public void onCreate() {
        super.onCreate();
        fillRepositoryByTestValues();
    }

    public NotesRepository getNotesRepository() {
        return notesRepository;
    }

    private void fillRepositoryByTestValues() {
        notesRepository.createNote(new NoteEntity("Заметка 1", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 2", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 3", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 4", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 5", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 6", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 7", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 8", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 9", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 10", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 11", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 12", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 13", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 14", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 15", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 16", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 17", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 18", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 19", "какой-то длинный текст олдоавдрлоадордапр"));
        notesRepository.createNote(new NoteEntity("Заметка 20", "какой-то длинный текст олдоавдрлоадордапр"));
    }
}
