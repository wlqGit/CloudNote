package com.aristark.note;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteAddFragment extends Fragment {
    private EditText titleEditText;
    private EditText contentEditText;
    private Note note;

    public NoteAddFragment() {
        // Required empty public constructor
    }

    public static Fragment fragmentInstance(){
        return new NotesListFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        note = new Note();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note_add,container,false);

        titleEditText = (EditText) root.findViewById(R.id.note_add_title);
        titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contentEditText = (EditText) root.findViewById(R.id.note_add_content);
        contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                note.setContent(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        return root;

    }

    @Override
    public void onPause() {
        super.onPause();
        NoteLab noteLab = NoteLab.getNoteLab(getActivity());
        noteLab.addNote(note);
    }
}
