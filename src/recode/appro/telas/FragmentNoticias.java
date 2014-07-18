package recode.appro.telas;

import java.util.ArrayList;
import java.util.List;

import recode.appro.model.Noticia;
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

public class FragmentNoticias extends Fragment {

	List<Noticia> noticias = new ArrayList<Noticia>();
	ListView listViewNoticias;
	AdapterItemNoticias listViewAdapter;

	public FragmentNoticias() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().getActionBar().setTitle("Notícias");
		getActivity().getActionBar().setSubtitle(null);
		
		View view = inflater.inflate(R.layout.fragment_list_view_generica,
				container, false);

		listViewAdapter = new AdapterItemNoticias(noticias, getActivity()
				.getApplicationContext());
		listViewNoticias = (ListView) view.findViewById(R.id.listView_generica);
		listViewNoticias.setAdapter(listViewAdapter);

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
