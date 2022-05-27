package ru.geekbrains.mynotes.ui.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.mynotes.App;
import ru.geekbrains.mynotes.R;
import ru.geekbrains.mynotes.implementation.NotesDiffCallback;
import ru.geekbrains.mynotes.model.entities.NoteEntity;
import ru.geekbrains.mynotes.ui.NotesAdapter;
import ru.geekbrains.mynotes.ui.OnItemClickListener;

public class NotesListFragment extends Fragment {
    private App app;
    private final NotesAdapter adapter = new NotesAdapter();

    @SuppressWarnings("FieldCanBeLocal")
    private RecyclerView recyclerView;
    private Controller controller;

    interface Controller {
        void openNoteEditScreen(NoteEntity item);
    }

    public static NotesListFragment newInstance(NoteEntity item) {
        NotesListFragment notesListFragment = new NotesListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(NoteEditFragment.NOTE_ARGS_KEY, item);
        notesListFragment.setArguments(bundle);
        return notesListFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NotesListFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        app = (App) requireActivity().getApplication();

        initRecyclerView(view);
        getArgs();
    }

    @Override
    public void onDestroy() {
        controller = null;
        super.onDestroy();
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setData(app.getNotesRepository().getNotes());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(NoteEntity item, int position) {
                controller.openNoteEditScreen(item);
            }

            @Override
            public void onItemLongClick(NoteEntity item, View view, int position) {
                showNotePopupMenu(item, view, position);
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void showNotePopupMenu(NoteEntity item, View view, int position) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view, Gravity.END);
        popupMenu.inflate(R.menu.note_item_popup_menu);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.edit_popup_menu_item:
                    controller.openNoteEditScreen(item);
                    break;
                case R.id.delete_popup_menu_item:
                    deleteItem(item, position);
                    break;
            }
            return true;
        });
        popupMenu.show();
    }

    private void deleteItem(NoteEntity item, int position) {
        if (app.getNotesRepository().deleteNote(item.getUid())) {
            Toast.makeText(requireActivity(),
                    getString(R.string.successfully_deleted) + item.getTitle(),
                    Toast.LENGTH_SHORT).show();
//            adapter.notifyItemRemoved(position);
            checkDiffRepo();
        } else {
            Toast.makeText(requireActivity(),
                    R.string.fail_to_delete,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getArgs() {
        Bundle data = getArguments();
        if (data != null) {
            NoteEntity note = data.getParcelable(NoteEditFragment.NOTE_ARGS_KEY);
            if (note != null) {
                if (note.getUid() == null) {
                    app.getNotesRepository().createNote(note);
                    checkDiffRepo();
                } else {
                    app.getNotesRepository().updateNote(note.getUid(), note);
                    checkDiffRepo();
                }
            }
        }
    }

    public void checkDiffRepo(){
        NotesDiffCallback notesDiffCallback = new NotesDiffCallback(adapter.getData(), app.getNotesRepository().getNotes());
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(notesDiffCallback, true);
        result.dispatchUpdatesTo(adapter);
    }
}
