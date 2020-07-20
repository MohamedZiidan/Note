package com.example.android.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteActivity extends AppCompatActivity {

    EditText editText;
    int noteId;
    SharedPreferences sharedPreferences;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        editText = findViewById(R.id.noteEditeText);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);

        if (noteId != -1) {

            editText.setText(MainActivity.notes.get(noteId));

        } else {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    MainActivity.notes.set(noteId, s.toString());
                    MainActivity.arrayAdapter.notifyDataSetChanged();
                }

                sharedPreferences = getSharedPreferences("com.example.android.note", MODE_PRIVATE);
                HashSet set = new HashSet(MainActivity.notes);
                sharedPreferences.edit().putStringSet("keeper", set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}