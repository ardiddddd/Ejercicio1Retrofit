package javi.ardid.diaz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javi.ardid.diaz.R;
import javi.ardid.diaz.modelos.ToDo;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.ToDoVH>{

    private List<ToDo> objects;
    private int resource;
    private Context context;

    public todoAdapter(List<ToDo> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }



    @NonNull
    @Override
    public ToDoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(context).inflate(resource,null);
        return new ToDoVH(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoVH holder, int position) {
        ToDo toDo = objects.get(position);
        holder.lbNombre.setText(ToDo.getNombreCorredor());
        holder.lbTiempo.setText(ToDo.getTiempoCorredor().toString());
        holder.lbDistancia.setText(ToDo.getDistanciaRecorrida().toString());
        if (toDo.isCompletado())

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ToDoVH extends RecyclerView.ViewHolder{
        TextView lbNombre, lbTiempo, lbDistancia;
        ImageButton btnCompletado;

        public ToDoVH(@NonNull View itemView) {
            super(itemView);
           lbNombre = itemView.findViewById(R.id.lbNombre);
           lbTiempo = itemView.findViewById(R.id.lbTiempo);
           lbDistancia = itemView.findViewById(R.id.lbDistancia);
           btnCompletado = itemView.findViewById(R.id.btnCompletado);
        }




    }


}
