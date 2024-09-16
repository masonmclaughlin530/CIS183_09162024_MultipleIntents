package com.example.cis183_09_16_2024_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNewPet extends AppCompatActivity {
    Button btn_j_addNew_addPet;
    Intent intent_j_MainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_pet);

        btn_j_addNew_addPet = findViewById(R.id.btn_v_newPet_addPet);

        intent_j_MainActivity = new Intent(AddNewPet.this, MainActivity.class);
        //code to get info passed from mainactivity.java
        //get the intent that called me
        Intent cameFrom = getIntent();
        //get the bundle that was passed to me from "cameFrom" intent
        Bundle infoPassedToMe = cameFrom.getExtras();
        //Get the info in the bundle called "InfoPassed" -> set in MainActivity.java
        String data = infoPassedToMe.getString("InfoPassed");

        Log.d("INFO PASSED FROM MAIN: ", data);

        addPetButtonListener();

    }


    private void addPetButtonListener()
    {
        btn_j_addNew_addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent_j_MainActivity.putExtra("Name", "Zackary Moore");
                startActivity(intent_j_MainActivity);
            }
        });
    }
}