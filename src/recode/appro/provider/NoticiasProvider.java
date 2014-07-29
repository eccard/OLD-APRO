package recode.appro.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by eccard on 7/28/14.
 */
public class NoticiasProvider extends ContentProvider {
    private static final String TAG = "NoticiasContentProvider";
    // fields for the database
    private static final String TAG_SUCCESSO = "sucesso";
    private static final String TAG_NOTICIAS = "noticias";
    private static final String TAG_CODIGO = "codigo";
    private static final String TAG_ASSUNTO = "assunto";
    private static final String TAG_DESCRICAO = "descricao";
    private static final String TAG_DATA = "data";
    private static final String TAG_HORA = "hora";
    private static final String TAG_CURSORELACIONADO = "cursoRelacionado";

    static final UriMatcher sUriMatcher;

    private static final int NOTICIAS = 1;

    private static final int NOTICIAS_ID = 2;

    // projection map for a query
    private static HashMap<String, String> notesProjectionMap;

    static final String AUTHORITY = "recode.appro.provider.NoticiasProvider";
    static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/noticia");


    static final String DATABASE_NAME = "PUROapp_Updated-1.3";
    static final String NOTES_TABLE_NAME = "noticias";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE = "CREATE TABLE noticias (_id INTEGER," +
            " assunto VARCHAR(41), dataExpedida VARCHAR(11),  " +
            "horaExpedida VARCHAR(11),cursoRelacionado INT, " +
            "descricao VARCHAR(300), visualizar INTEGER  NOT NULL  DEFAULT (0)))";


    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/noticia";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE_NAME);
            onCreate(db);
        }
    }
    private DatabaseHelper dbHelper;

    @Override
    public int delete(Uri uri, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case NOTICIAS:
                break;
            case NOTICIAS_ID:
                where = where + "_id = " + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        int count = db.delete(NOTES_TABLE_NAME, where, whereArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case NOTICIAS:
                return Noticia.Noticias.CONTENT_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues initialValues) {
        if (sUriMatcher.match(uri) != NOTICIAS) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }

        ContentValues values;
        if (initialValues != null) {
            values = new ContentValues(initialValues);
        } else {
            values = new ContentValues();
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insert(NOTES_TABLE_NAME, Noticia.Noticias.DESCRICAO, values);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(Noticia.Noticias.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }

        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(NOTES_TABLE_NAME);
        qb.setProjectionMap(notesProjectionMap);

        switch (sUriMatcher.match(uri)) {
            case NOTICIAS:
                break;
            case NOTICIAS_ID:
                selection = selection + "_id = " + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count;
        switch (sUriMatcher.match(uri)) {
            case NOTICIAS:
                count = db.update(NOTES_TABLE_NAME, values, where, whereArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, NOTES_TABLE_NAME, NOTICIAS);
        sUriMatcher.addURI(AUTHORITY, NOTES_TABLE_NAME + "/#", NOTICIAS_ID);

        notesProjectionMap = new HashMap<String, String>();
        notesProjectionMap.put(Noticia.Noticias.NOTE_ID, Noticia.Noticias.NOTE_ID);

        notesProjectionMap.put(Noticia.Noticias.ASSUNTO, Noticia.Noticias.ASSUNTO);
        notesProjectionMap.put(Noticia.Noticias.DESCRICAO, Noticia.Noticias.DESCRICAO);
        notesProjectionMap.put(Noticia.Noticias.DATA, Noticia.Noticias.DATA);
        notesProjectionMap.put(Noticia.Noticias.HORA, Noticia.Noticias.HORA);
        notesProjectionMap.put(Noticia.Noticias.CURSORELACIONADO, Noticia.Noticias.CURSORELACIONADO);
    }
}