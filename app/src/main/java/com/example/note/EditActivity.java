package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText editTitle,editDescription;
    Button btnCancel,btnSave;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final Intent intent=getIntent();
        editTitle=findViewById(R.id.edit_edit_title);
        editDescription=findViewById(R.id.edit_edit_description);
        btnCancel=findViewById(R.id.btnCancel);
        btnSave=findViewById(R.id.btnSave);

        linearLayout=findViewById(R.id.btn_holder);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note=new Note(editTitle.getText().toString(),editDescription.getText().toString());
                note.setId(intent.getIntExtra("id",1));
                if(new NoteHandler(EditActivity.this).update(note)){
                        Toast.makeText(EditActivity.this, "Note updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EditActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
                }

                onBackPressed();
            }
        });

        editDescription.setText(intent.getStringExtra("description"));
        editTitle.setText(intent.getStringExtra("title"));
    }
    @Override
    public void onBackPressed() {
        btnSave.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(linearLayout);
        super.onBackPressed();
    }
}