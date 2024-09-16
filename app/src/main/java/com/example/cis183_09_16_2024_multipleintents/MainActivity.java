package com.example.cis183_09_16_2024_multipleintents;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_j_goToAddPet;
    ListView lv_j_listOfPets;
    Intent intent_j_addNewPet;
    //this is to show that when we load an intent it will always be a new load
    //data will not be retained between loads
    //we change this variable below to 99 but when we come back from
    //addNewPet it will be reinitialized to 50
    //You can avoid this by one of the following methods:
    //1. make it a static int
    //2. pass it to the other function and then pass it back
    //3. make a static class
    int numberTestingIfNewLoad = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.d("VALUE TESTING ON LOAD: ", numberTestingIfNewLoad + "");
        btn_j_goToAddPet = findViewById(R.id.btn_v_main_addNewPet);
        lv_j_listOfPets  = findViewById(R.id.lv_v_listOfPets);

        Intent cameFrom = getIntent();
        Bundle infoPassedToMe = cameFrom.getExtras();

        if(infoPassedToMe != null)
        {
            String name = infoPassedToMe.getString("Name");

            Log.d("INFO FROM ADD PET", name);
        }


        //give the intent the following info:
        //the class that you came from
        //the class you want to open
        intent_j_addNewPet = new Intent(MainActivity.this, AddNewPet.class);
        //setup the button listener
        addNewPetButtonListener();
    }

    private void addNewPetButtonListener()
    {
        btn_j_goToAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for testing only
                numberTestingIfNewLoad = 99;
                //for testing only
                Log.d("VALUE TESTING ON CLICK: ", numberTestingIfNewLoad + "");

                //create a bundle to pass to addnewPet
                intent_j_addNewPet.putExtra("InfoPassed", "Hello from main");
                startActivity(intent_j_addNewPet);
            }
        });
    }
}