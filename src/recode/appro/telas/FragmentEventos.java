package recode.appro.telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import recode.appro.model.Evento;
import recode.appro.model.Noticia;

/**
 * Created by eccard on 09/07/14.
 */
public class FragmentEventos extends Fragment {
//    ArrayList<Evento> eventos = new ArrayList<Evento>();
    List<Evento> eventos = new ArrayList<Evento>();
    ListView listViewEventos;
    AdapterItemEventos listViewAdapter;

    public FragmentEventos() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().getActionBar().setTitle("Eventos");
        getActivity().getActionBar().setSubtitle(null);

        View view = inflater.inflate(R.layout.fragment_list_view_generica,
                container, false);

        listViewAdapter = new AdapterItemEventos(eventos, getActivity()
                .getApplicationContext());
        listViewEventos = (ListView) view.findViewById(R.id.listView_generica);
        listViewEventos.setAdapter(listViewAdapter);

    return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.pesquisa, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_pesquisa:
                Intent intent = new Intent(getActivity(), ActivityPesquisa.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }
}
