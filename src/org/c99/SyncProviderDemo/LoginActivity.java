/*******************************************************************************
 * Copyright 2010 Sam Steele 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.c99.SyncProviderDemo;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

import recode.appro.controlador.ControladorUsuario;
import recode.appro.telas.R;

public class LoginActivity extends AccountAuthenticatorActivity {
	EditText mNick;
	Button mLoginButton;
    RadioGroup radioGroupTipoUsuario;
    RadioGroup radioGroupCurso;
    Spinner periodo;
    ArrayList<String> s;

//    final NumberPicker np;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mNick = (EditText) findViewById(R.id.nick);
        radioGroupTipoUsuario = (RadioGroup) findViewById(R.id.radiongroup_tipo_usuario);
        radioGroupCurso = (RadioGroup) findViewById(R.id.radiobutton_loggin_curso);
        periodo = (Spinner) findViewById(R.id.spinner);
        s=carregaNumeros();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);
        periodo.setAdapter(adapter);

		mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				String nick = mNick.getText().toString().trim().toLowerCase();

				if (nick.length() > 0) {
					LoginTask t = new LoginTask(LoginActivity.this);
					t.execute(nick);

                    int selectdTipoUsuario = radioGroupTipoUsuario.getCheckedRadioButtonId();
                    int selectdCursoUsuario = radioGroupCurso.getCheckedRadioButtonId();
                    RadioButton b = (RadioButton) findViewById(selectdTipoUsuario);
                    RadioButton b2 = (RadioButton) findViewById(selectdCursoUsuario);

                    b.getText();
                    b2.getText();

                    ControladorUsuario controladorUsuario = new ControladorUsuario(getApplicationContext());
                    controladorUsuario.criarUsuario2(nick, 0);
                }
			}

		});
	}

    public ArrayList<String> carregaNumeros (){
        ArrayList<String> aux = new ArrayList<String>();
        aux.add("1");
        aux.add("2");
        aux.add("3");
        aux.add("4");
        aux.add("5");
        aux.add("6");
        aux.add("7");
        aux.add("8");
        aux.add("9");
        aux.add("10");
        aux.add("11");
        aux.add("12");
        aux.add("13");
        aux.add("14");
        aux.add("15");
        return aux;
    }

	private class LoginTask extends AsyncTask<String, Void, Boolean> {
		Context mContext;
		ProgressDialog mDialog;

		LoginTask(Context c) {
			mContext = c;
			mLoginButton.setEnabled(false);

			mDialog = ProgressDialog.show(c, "", getString(R.string.authenticating), true, false);
			mDialog.setCancelable(true);
		}

		@Override
		public Boolean doInBackground(String... params) {
			String nick = params[0];
//			String matricula = params[1];

			// Do something internetty
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Bundle result = null;
			Account account = new Account(nick, mContext.getString(R.string.ACCOUNT_TYPE));
			AccountManager am = AccountManager.get(mContext);
			if (am.addAccountExplicitly(account, null, null)) {
				result = new Bundle();
				result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
				result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
                ContentResolver.setIsSyncable(account,"evetos",1);
				setAccountAuthenticatorResult(result);
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void onPostExecute(Boolean result) {
			mLoginButton.setEnabled(true);
			mDialog.dismiss();

			if (result)
				finish();
		}
	}
}
