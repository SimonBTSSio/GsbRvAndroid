package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.modeles.ModeleGsb;
import fr.gsb.rv.technique.Session;

public class ListeRvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_rv);
        Bundle paquet = this.getIntent().getExtras();
        String mois = paquet.getString("mois");
        String ans = paquet.getString("ans");
        TextView text = (TextView) findViewById(R.id.textView6);
        text.setText("Liste des rapports pour " + mois + " " + ans);
        String[] produits = {"Burger","Baggel","hot-dog","pizza"};
        ImageView imageView = (ImageView) findViewById(R.id.retourListeRapport);
        ListView lv = (ListView) findViewById(R.id.liste);
        ArrayAdapter<String> adapterMois = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,produits);
        lv.setAdapter(adapterMois);
        String[] moisTbl = new String[]{"Janvier","Février","Mars","Avril","Mais","Juin","Juillet","Aout","Septembre","Octobre","Nomvembre","Décembre"};
        int leMoisI = 0;
        String leMois = "";
        for(int i = 0; i < moisTbl.length; i++){
            System.out.println(moisTbl[i] + " == " + mois);
            if(moisTbl[i] == mois){
                System.out.println("je suis al");
                leMoisI = i + 1;
                leMois = "" + leMoisI;

            }
        }
        try {
            ans = URLEncoder.encode(ans, "UTF-8");
            leMois = URLEncoder.encode(leMois, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format("http://192.168.121.141:5000/rapports/%s/%s/%s", Session.getSession().getVisiteur().getMatricule(),leMois,ans);
        Response.Listener<String> ecouteurReponse = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){
                }

            }
        };
        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        };
        StringRequest requete = new StringRequest(
                Request.Method.GET ,
                url,
                ecouteurReponse,
                ecouteurErreur
        );
        RequestQueue fileRequetes = Volley.newRequestQueue(this);
        fileRequetes.add(requete);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeRvActivity.this, RechercheRvActivity.class);
                startActivity(intent);
            }
        });

    };
}