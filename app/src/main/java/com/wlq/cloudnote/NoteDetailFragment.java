package com.wlq.cloudnote;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.aristark.note.R;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteDetailFragment extends Fragment {
    private static String ARG_NOTE_ID;
    private Note note;
    TextView noteDate;
    TextView noteTitle;
    TextView noteContent;


    public NoteDetailFragment() {
        // Required empty public constructor
    }

     public static Fragment newFragment(UUID uuid){
         Bundle args = new Bundle();
         args.putSerializable(ARG_NOTE_ID,uuid);
         Fragment fragment = new NoteDetailFragment();
         fragment.setArguments(args);
         return fragment;

     }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        note = NoteLab.getNoteLab(getActivity()).getNote(uuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note_detail,container,false);
        noteDate = (TextView) root.findViewById(R.id.note_date);
        noteTitle = (TextView) root.findViewById(R.id.note_title);
        noteContent = (TextView) root.findViewById(R.id.note_content);
        noteDate.setText(note.getDate().toString());
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
        return root;
    }

}
