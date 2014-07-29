package recode.appro.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by eccard on 7/28/14.
 */
public class Noticia {
    public Noticia() {
    }

    public static final class Noticias implements BaseColumns {
        private Noticias() {
        }

        public static final Uri CONTENT_URI = Uri.parse("content://"
                + NoticiasProvider.AUTHORITY + "/notes");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.jwei512.notes";

        public static final String NOTE_ID = "_id";

        public static final String ASSUNTO = "assunto";
        public static final String DESCRICAO = "descricao";
        public static final String DATA = "data";
        public static final String HORA = "hora";
        public static final String CURSORELACIONADO = "cursoRelacionado";

    }


}
