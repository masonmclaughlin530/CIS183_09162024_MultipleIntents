package com.example.cis183_09_16_2024_multipleintents;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddNewPetType extends AppCompatActivity {

    EditText et_j_typeName;
    Button btn_j_addType;
    Button btn_j_backButton;
    TextView tv_j_errorMsg;
    Intent intent_j_addPet;
    boolean newPetType = true;
    String petType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_pet_type);

        et_j_typeName = findViewById(R.id.et_v_addType_typeName);
        btn_j_addType = findViewById(R.id.btn_v_addType_addType);
        btn_j_backButton = findViewById(R.id.btn_v_newType_backButton);
        tv_j_errorMsg = findViewById(R.id.tv_v_addType_error);

        intent_j_addPet = new Intent(AddNewPetType.this, AddNewPet.class);


        tv_j_errorMsg.setVisibility(View.INVISIBLE);





        returnButtonEventListener();
        petTypeButtonClickEvent();
        addPetTypeEventListener();





    }

    public void addPetTypeEventListener()
    {
        et_j_typeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                petType = et_j_typeName.getText().toString();
                petType = changeCase(petType);
                newPetType = validPetType(petType);

                if (newPetType)
                {
                    tv_j_errorMsg.setVisibility(View.INVISIBLE);
                    btn_j_addType.setEnabled(true);

                }
                else
                {
                    tv_j_errorMsg.setText("Error: This Pet Type Already Exists");
                    tv_j_errorMsg.setTextColor(Color.parseColor("#FF0000"));
                    tv_j_errorMsg.setVisibility(View.VISIBLE);
                    btn_j_addType.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void returnButtonEventListener()
    {
        btn_j_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent_j_addPet);
            }
        });
    }

    private void addNewPetToList()
    {
        if(!et_j_typeName.getText().toString().isEmpty())
        {
            tv_j_errorMsg.setVisibility(View.INVISIBLE);
            Pet.PetType.addPetType(petType);

            et_j_typeName.setText("");

            Log.d("Pet Types", "addNewPetToList:" + Pet.PetType.getAllPetTypes());

            startActivity(intent_j_addPet);
        }
        else
        {
            tv_j_errorMsg.setText("Error: Please fill out a pet type");
            tv_j_errorMsg.setTextColor(Color.parseColor("#FF0000"));
            tv_j_errorMsg.setVisibility(View.VISIBLE);
        }
    }

    private void petTypeButtonClickEvent()
    {
        btn_j_addType.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addNewPetToList();

            }
        });
    }

    private boolean validPetType(String u)
    {
        for(int i = 0; i < Pet.PetType.getAllPetTypes().size();i++)
        {
            if(u.equals(Pet.PetType.getPetAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    private String changeCase(String p)
    {
        char[] chars = p.toCharArray();
        for(int i = 0; i < p.length();i++)
        {
            if(i == 0)
            {
                chars[i] = Character.toUpperCase(chars[i]);
            }
            else
            {
                chars[i] = Character.toLowerCase(chars[i]);
            }
        }
        return new String(chars);
    }
}