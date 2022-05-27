package ru.geekbrains.mynotes.ui;

import android.view.View;

import ru.geekbrains.mynotes.model.entities.NoteEntity;

public interface OnItemClickListener {

    void onItemClick(NoteEntity item, int position);
    void onItemLongClick(NoteEntity item, View itemView, int position);
}
