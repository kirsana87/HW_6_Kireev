package ru.geekbrains.mynotes.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.mynotes.R;
import ru.geekbrains.mynotes.model.entities.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    private final TextView detailTextView = itemView.findViewById(R.id.detail_text_view);
    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);

    private final OnItemClickListener clickListener;

    private NoteEntity note;

    public NoteViewHolder(@NonNull ViewGroup parent, @NonNull OnItemClickListener clickListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_view_holder, parent, false));
        this.clickListener = clickListener;
//        itemView.setOnClickListener(view -> clickListener.onItemClick(note));
    }

    public void bind(NoteEntity note) {
        this.note = note;
        titleTextView.setText(note.getTitle());
        detailTextView.setText(note.getDetail());
        dateTextView.setText(note.getCreationDate());
        itemView.setOnClickListener(view -> {
            clickListener.onItemClick(note, this.getLayoutPosition());
        });
        itemView.setOnLongClickListener(view -> {
            clickListener.onItemLongClick(note, itemView, this.getLayoutPosition());
            return true;
        });
    }
}
