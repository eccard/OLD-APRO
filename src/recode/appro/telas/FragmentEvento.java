package recode.appro.telas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import recode.appro.model.Evento;

/**
 * Created by eccard on 7/25/14.
 */
public class FragmentEvento extends android.support.v4.app.Fragment {
    Evento evento;

    public FragmentEvento(Evento evento) {
        this.evento = evento;
    }

    public FragmentEvento(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getActionBar().setTitle("Evento");
        getActivity().getActionBar().setSubtitle(null);

        View view = inflater.inflate(R.layout.fragment_evento,container,false);

        TextView nome = (TextView) view.findViewById(R.id.textView_evento_nome);
        TextView datahora = (TextView) view.findViewById(R.id.textView_evento_data_hora);
        TextView descricao = (TextView) view.findViewById(R.id.textView_evento_descricao);
        TextView local = (TextView) view.findViewById(R.id.textView_evento_local);

        TextView organizadores = (TextView) view.findViewById(R.id.textView_evento_organizadores);
        TextView confirmados = (TextView) view.findViewById(R.id.textView_evento_confimados);

        nome.setText(evento.getNome());
        datahora.setText("Dia: " + evento.getData() + " às " + evento.getHora());
        descricao.setText(evento.getDescricao());
        local.setText("Local: "+evento.getLocal());
        organizadores.setText("Organizadores: " + evento.getOrganizadores());
//        organizadores.setGravity(Gravity.CENTER_HORIZONTAL);
        confirmados.setText("Confirmados: 0");

        return view;
    }
}
