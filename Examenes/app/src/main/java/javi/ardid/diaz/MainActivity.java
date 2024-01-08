package javi.ardid.diaz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import javi.ardid.diaz.databinding.ActivityMainBinding;
import javi.ardid.diaz.modelos.ToDo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private ArrayList<ToDo> toDoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        toDoLista = new ArrayList<>();

        crearTarea();



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void crearTarea() {
        for (int i = 0; i < 1000000; i++) {
            toDoLista.add(new ToDo("Nombre Corredor"+i,+i,+i));
        }
    }

}