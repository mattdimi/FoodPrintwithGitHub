package com.example.foodprint;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Courses extends MotherActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        Toast.makeText(Courses.this, "courses", Toast.LENGTH_LONG).show();

    }

    public void AjouterProduit(View view) {


    }
}
