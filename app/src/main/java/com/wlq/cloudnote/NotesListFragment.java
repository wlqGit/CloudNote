package com.wlq.cloudnote;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aristark.note.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesListFragment extends Fragment {
    private RecyclerView noteRecycler;
    private NoteAdapter noteAdapter;



    public NotesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_notes_list, container, false);
        View root = inflater.inflate(R.layout.fragment_notes_list,container,false);

        noteRecycler = (RecyclerView) root.findViewById(R.id.note_recycler_view);
        noteRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        NoteLab noteLab = NoteLab.getNoteLab(getActivity());
//        ArrayList<Note> notes = noteLab.getNotes();
//        noteAdapter = new NoteAdapter(notes);
//        noteRecycler.setAdapter(noteAdapter);
        updateView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private class NoteHolder extends RecyclerView.ViewHolder{
        private Note note;
        private TextView noteTitle;
        private TextView noteContent;
        private TextView noteDate;

        public NoteHolder(View root) {
            super(root);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = NotePagerActivity.newIntent(getActivity(),note.getUuid());
                    startActivity(i);

                }
            });
            noteTitle = (TextView) root.findViewById(R.id.list_item_note_title);
            noteContent = (TextView) root.findViewById(R.id.list_item_note_content);
            noteDate = (TextView) root.findViewById(R.id.list_item_note_date);
        }

        public void bindView(Note n){
            this.note = n;
            noteTitle.setText(note.getTitle());
            noteContent.setText(note.getContent());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(note.getDate());
            int year = calendar.get(1);
            int day = calendar.get(5);
            int month = calendar.get(2)+1;
            String date = year+"年"+month+"月"+day+"日";
            noteDate.setText(date);
        }

    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{
        private List<Note> notes;

        public NoteAdapter(List<Note> notes){
            this.notes = notes;
        }

        public void setNotes(List<Note> notes) {
            this.notes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note,parent,false);
            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = notes.get(position);
            holder.bindView(note);
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }

    public void updateView(){
        NoteLab noteLab = NoteLab.getNoteLab(getActivity());
        ArrayList<Note> notes = noteLab.getNotes();
        if (noteAdapter == null){
            noteAdapter = new NoteAdapter(notes);
            noteRecycler.setAdapter(noteAdapter);
            return;
        }

        noteAdapter.setNotes(notes);
        noteRecycler.setAdapter(noteAdapter);

    }

}
