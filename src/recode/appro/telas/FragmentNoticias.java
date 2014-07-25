package recode.appro.telas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import recode.appro.conexao.JSONParser;
import recode.appro.controlador.ControladorEvento;
import recode.appro.controlador.ControladorNoticia;
import recode.appro.model.Evento;
import recode.appro.model.Noticia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentNoticias extends Fragment {

//	List<Noticia> noticias = new ArrayList<Noticia>();
	ListView listViewNoticias;
	AdapterItemNoticias listViewAdapter;

    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> eventosList;
    private static String url_all_noticias = "http://10.0.0.104/aproWS/noticias/listarultimasnoticias.php";

    // JSON Node names
    private static final String TAG_SUCCESSO = "sucesso";
    private static final String TAG_NOTICIAS = "noticias";
    private static final String TAG_CODIGO = "codigo";
    private static final String TAG_ASSUNTO = "assunto";
    private static final String TAG_DESCRICAO = "descricao";
    private static final String TAG_DATA = "data";
    private static final String TAG_HORA = "hora";
    private static final String TAG_CURSORELACIONADO = "cursoRelacionado";

    // products JSONArray
    JSONArray jnoticias = null;
    // eventos novos vindo do servidos
    ArrayList<Noticia> listaNovasNoticias;

	public FragmentNoticias() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		getActivity().getActionBar().setTitle("Notícias");
		getActivity().getActionBar().setSubtitle(null);
		
		View view = inflater.inflate(R.layout.fragment_list_view_generica,
				container, false);

        // Hashmap for ListView
//        noticiasList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllProducts().execute();


        listViewAdapter = new AdapterItemNoticias(getActivity()
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

    /**
     * Background Async Task to Load all product by making HTTP Request
     * */
    class LoadAllProducts extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Loading noticias. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        /**
         * getting All noticias from url
         * */
        protected String doInBackground(String... args) {


            ControladorNoticia controladorNoticia = new ControladorNoticia(getActivity().getApplicationContext());
            int codigoultimoNoticia = controladorNoticia.getCodigoUltimaNoticia();
            Log.i("pegar o ultimo evento",String.valueOf(codigoultimoNoticia));
            controladorNoticia = null;

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_noticias + "?codigo=" + String.valueOf(codigoultimoNoticia), "GET",params);

            // Check your log cat for JSON reponse
            Log.d("All Products: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESSO);

                if (success == 1) {
                    // products found
                    // Getting Array of Products
//                    Log.i("json de eventos array",json.toString());
                    jnoticias = json.getJSONArray(TAG_NOTICIAS);
                    listaNovasNoticias = new ArrayList<Noticia>();
                    // looping through All Products
                    for (int i = 0; i < jnoticias.length(); i++) {
                        JSONObject c = jnoticias.getJSONObject(i);

                        // Storing each json item in variable
                        int codigo = c.getInt(TAG_CODIGO);
                        String assunto = c.getString(TAG_ASSUNTO);
                        String descricao = c.getString(TAG_DESCRICAO);
                        String data = c.getString(TAG_DATA);
                        String hora = c.getString(TAG_HORA);
                        int curoRelacionado = c.getInt(TAG_CURSORELACIONADO);

                        listaNovasNoticias.add(new Noticia(codigo,assunto,data,hora,curoRelacionado,descricao));

                        // creating new HashMap
//                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
//                        map.put(TAG_CODIGO, codigo);
//                        map.put(TAG_NOME, nome);

                        // adding HashList to ArrayList
//                        eventosList.add(map);

//                        Log.i("passou aki",eventosList.get(i).toString());
                    }
                } else {
                    Log.i("passou aki zeroo ","teste");

                    /*
                    // no products found
                    // Launch Add New product Activity
                    Intent i = new Intent(getApplicationContext(),
                            NewProductActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                */
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            if(listaNovasNoticias!=null) {
                listViewAdapter.concatenarArrayDeNoticias(listaNovasNoticias);
            }
            listViewNoticias.setAdapter(listViewAdapter);
            //for(int i=0;i<eventosList.size();i++){
            //    Log.i("todos",eventosList.get(i).toString());
            //}

//            Log.i("array",eventosList.get(1).toString());
             /*
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * /
                    ListAdapter adapter = new SimpleAdapter(
                            AllProductsActivity.this, productsList,
                            R.layout.list_item, new String[] { TAG_PID,
                            TAG_NAME},
                            new int[] { R.id.pid, R.id.name });
                    // updating listview
                    setListAdapter(adapter);
                }
            });
             */


        }

    }


}
