package recode.appro.telas;

import java.util.List;

import recode.appro.controlador.ControladorNoticia;
import recode.appro.model.Noticia;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterItemNoticias extends BaseAdapter {

	Context context;
	List<Noticia> noticias;
	ControladorNoticia controladorNoticia;
	
	public AdapterItemNoticias(List<Noticia> noticias, Context context) {
		
		this.context = context;
		this.controladorNoticia = new ControladorNoticia(context);
		this.noticias = controladorNoticia.getNoticias();
	}
	
	@Override
	public int getCount() {
		return noticias.size();
	}

	@Override
	public Object getItem(int position) {
		
		return noticias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.adapter_item_noticias, null);
		
		// Altera a cor de fundo para cada item par da lista de notícias  
		if(position%2 == 0){
			view.setBackgroundResource(R.color.White);
		}
		
		TextView assunto = (TextView) view.findViewById(R.id.textview_noticia);
		TextView data = (TextView) view.findViewById(R.id.textview_noticia_data);
		TextView hora = (TextView) view.findViewById(R.id.textView_noticia_hora);
		
	    assunto.setText(noticias.get(position).getAssunto());
		data.setText(noticias.get(position).getDataexpedida());
		hora.setText(noticias.get(position).getHoraexpedida());

		return view;
	}

}
