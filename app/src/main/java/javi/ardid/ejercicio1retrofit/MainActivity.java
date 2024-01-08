package javi.ardid.ejercicio1retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import javi.ardid.ejercicio1retrofit.adapters.UserAdapter;
import javi.ardid.ejercicio1retrofit.conexiones.ApiConexiones;
import javi.ardid.ejercicio1retrofit.conexiones.RetrofitObject;
import javi.ardid.ejercicio1retrofit.databinding.ActivityMainBinding;
import javi.ardid.ejercicio1retrofit.modelos.DataItem;
import javi.ardid.ejercicio1retrofit.modelos.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    UserAdapter adapter;
    private RecyclerView.LayoutManager lm;
    private List<DataItem> listaUsuarios;
    private Retrofit retrofit;
    private ApiConexiones api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaUsuarios = new ArrayList<>();
        adapter = new UserAdapter(listaUsuarios, R.layout.user_view_holder, this);
        lm = new LinearLayoutManager(this);

        binding.contenedorMain.setAdapter(adapter);
        binding.contenedorMain.setLayoutManager(lm);

        retrofit = RetrofitObject.getConexion();
        api = retrofit.create(ApiConexiones.class);

        cargarUsuarios("1");

    }

    private void cargarUsuarios(String s) {
        Call<Respuesta> dogetUsers = api.getUsers(Integer.parseInt(s));
        dogetUsers.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                if(response.code() == HttpsURLConnection.HTTP_OK
                && response.body() != null){
                    listaUsuarios.addAll(response.body().getData());
                    adapter.notifyItemRangeInserted(0, listaUsuarios.size());
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}