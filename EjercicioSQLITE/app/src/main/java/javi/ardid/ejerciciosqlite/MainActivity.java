package javi.ardid.ejerciciosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

import javi.ardid.ejerciciosqlite.Modelos.Alumno;
import javi.ardid.ejerciciosqlite.configuraciones.Configuracion;
import javi.ardid.ejerciciosqlite.databinding.ActivityMainBinding;
import javi.ardid.ejerciciosqlite.helpers.AlumnosHelper;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Alumno> listaAlumnos;
    private AlumnosHelper helper;
    private Dao<Alumno, Integer> daoAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        listaAlumnos = new ArrayList<>();
        helper = new AlumnosHelper(this, Configuracion.BD_NAME, null, Configuracion.BD_VERSION);

        if(helper != null){

            try {
                daoAlumno = helper.getDaoAlumnos();
                listaAlumnos.addAll(daoAlumno.queryForAll());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        actualizarNota();


        binding.btnInsertarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarAlumno();
            }
        });

        binding.btnConsultarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultarAlumno();
            }
        });

        binding.btnModificarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.btnEliminarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void actualizarNota() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    float nota1 = Float.parseFloat(binding.txtNota1Main.getText().toString());
                    float nota2 = Float.parseFloat(binding.txtNota2Main.getText().toString());
                    float nota3 = Float.parseFloat(binding.txtNota3Main.getText().toString());
                    float notaFinal = (nota1 + nota2 + nota3) / 3;

                    binding.lbNotaFinalMain.setText("Nota Final: " + Configuracion.NF.format(notaFinal));
                }catch (Exception e){

                }
            }
        };
        binding.txtNota1Main.addTextChangedListener(textWatcher);
        binding.txtNota2Main.addTextChangedListener(textWatcher);
        binding.txtNota3Main.addTextChangedListener(textWatcher);
    }

    private void insertarAlumno(){
        if(binding.txtNombreMain.getText().toString().isEmpty() ||
                binding.txtApellidosMain.getText().toString().isEmpty() ||
                binding.txtNota1Main.getText().toString().isEmpty() ||
                binding.txtNota2Main.getText().toString().isEmpty() ||
                binding.txtNota3Main.getText().toString().isEmpty()){
            Toast.makeText(this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
        }else{
            Alumno a = new Alumno();
        a.setNombre(binding.txtNombreMain.getText().toString());
        a.setApellidos(binding.txtApellidosMain.getText().toString());
        a.setNota1(Float.parseFloat(binding.txtNota1Main.getText().toString()));
        a.setNota2(Float.parseFloat(binding.txtNota2Main.getText().toString()));
        a.setNota3(Float.parseFloat(binding.txtNota3Main.getText().toString()));

            try {
                daoAlumno.create(a);
                a.setId(daoAlumno.extractId(a));
                listaAlumnos.add(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            limpiar();
        }
    }

    private void limpiar() {
        binding.txtNombreMain.setText("");
        binding.txtApellidosMain.setText("");
        binding.txtNota1Main.setText("");
        binding.txtNota2Main.setText("");
        binding.txtNota3Main.setText("");
        binding.lbNotaFinalMain.setText("Nota Final: ");
    }


}