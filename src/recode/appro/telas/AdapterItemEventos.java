package recode.appro.telas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import recode.appro.controlador.ControladorEvento;
import recode.appro.controlador.ControladorNoticia;
import recode.appro.model.Evento;

/**
 * Created by eccard on 09/07/14.
 */
public class AdapterItemEventos extends BaseAdapter {
    Context context;
//    ArrayList<Evento> eventos;
    List<Evento> eventos;
    ControladorEvento controladorEvento;

    public AdapterItemEventos(List<Evento> eventos,Context context) {
        this.context = context;
        this.controladorEvento= new ControladorEvento(context);
        this.eventos = controladorEvento.getEventos();

    }

    @Override
    public int getCount() { return   eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.adapter_item_eventos, null);

        // Altera a cor de fundo para cada item par da lista de notícias
        if(position%2 == 0){
            view.setBackgroundResource(R.color.White);
        }
        TextView nome = (TextView) view.findViewById(R.id.textview_evento);
        TextView local = (TextView) view.findViewById(R.id.textView_evento_local);
        TextView data = (TextView) view.findViewById(R.id.textview_evento_data);
        TextView hora = (TextView) view.findViewById(R.id.textView_evento_hora);

        nome.setText(eventos.get(position).getNome());
        local.setText(eventos.get(position).getLocal());
        data.setText(eventos.get(position).getData());
        hora.setText(eventos.get(position).getHora());

        return view;
    }
}
