package javi.ardid.ejercicio1retrofit.conexiones;

import javi.ardid.ejercicio1retrofit.modelos.Respuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiConexiones {
    @GET("/api/users?")
    Call<Respuesta> getUsers(@Query("page") int pagina);
}
