package com.aristark.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.UUID;

public class NotePagerActivity extends AppCompatActivity {
    private static final String EXTRA_NOTE_ID = "com.aristark.note.note.id";
    private ViewPager noteViewPager;
    private ArrayList<Note> notes;

    public static Intent newIntent(Context context,UUID uuid){
        Intent i = new Intent(context,NotePagerActivity.class);
        i.putExtra(EXTRA_NOTE_ID,uuid);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pager);

        noteViewPager = (ViewPager) findViewById(R.id.note_view_pager);
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        notes = NoteLab.getNoteLab(this).getNotes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        noteViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Note note = notes.get(position);
                return NoteDetailFragment.newFragment(note.getUuid());
            }

            @Override
            public int getCount() {
                return notes.size();
            }
        });

        for (int i=0;i<notes.size();i++){
            if (notes.get(i).getUuid().equals(uuid)){
                noteViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
