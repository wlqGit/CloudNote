package com.wlq.cloudnote;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by aristark on 3/4/16.
 */
public class NoteAddActivity extends SingleFragmentActivity{
    @Override
    public Fragment creatFrament() {
        return new NoteAddFragment();
//        return new Fragment();
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,NoteAddActivity.class);
        return intent;
    }
}