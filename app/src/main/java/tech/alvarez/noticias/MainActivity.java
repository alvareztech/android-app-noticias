package tech.alvarez.noticias;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import tech.alvarez.noticias.adapters.NoticiasAdapter;
import tech.alvarez.noticias.models.Noticia;
import tech.alvarez.noticias.utils.LaRazonParserXML;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoticiasAdapter noticiasAdapter;

    private ArrayList<Noticia> noticias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noticiasAdapter = new NoticiasAdapter(this);
        recyclerView.setAdapter(noticiasAdapter);

        ObtenerLaRazonXML tarea = new ObtenerLaRazonXML();
        tarea.execute("http://www.la-razon.com/rss/nacional/");
    }

    public class ObtenerLaRazonXML extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

            String url = strings[0];

            LaRazonParserXML parserXML = new LaRazonParserXML(url);
            noticias = parserXML.parse();


            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            for (int i = 0; i < noticias.size(); i++) {
                Noticia n = noticias.get(i);

                noticiasAdapter.adicionar(n);


                Log.i("MIAPP", n.getTitulo());
                Log.i("MIAPP", n.getDescripcion());
                Log.i("MIAPP", n.getUrlFoto());
                Log.i("MIAPP", n.getUrlNoticia());


            }
        }
    }


}