package recode.appro.controlador;

import android.content.Context;
import android.database.SQLException;

import java.io.IOException;

import recode.appro.persistencia.DataBaseHelper;

/**
 * Created by eccard on 7/29/14.
 */
public class ControladorUsuario {
    private Context context;
    private DataBaseHelper dataBaseHelper;

    public ControladorUsuario(Context context) {
        this.context = context;
        this.dataBaseHelper = new DataBaseHelper(context);
    }

    public void abrirBanco() {

        try {

            dataBaseHelper.CriarDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            dataBaseHelper.abrirDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }

    }

    public int verificarSeExisteUsuario(){
        abrirBanco();
        return dataBaseHelper.verificarSeExisteUsuario();
    }
    public void criarUsuario(String nick,int matricula){
        abrirBanco();
        dataBaseHelper.criarUsuario(nick, matricula);
    }
}
