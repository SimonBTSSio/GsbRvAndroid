package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import fr.gsb.rv.entites.Visiteur;
import fr.gsb.rv.modeles.ModeleGsb;
import fr.gsb.rv.technique.Session;

public class ListeRvActivity extends AppCompatActivity {
    List<String[]> rapports = new ArrayList<>();
    List<String> listRap = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_rv);
        Bundle paquet = this.getIntent().getExtras();
        String mois = paquet.getString("mois");
        String ans = paquet.getString("ans");
        int moisEntier = paquet.getInt("moisEntier") + 1;
        TextView text = (TextView) findViewById(R.id.textView6);
        text.setText("Liste des rapports pour " + mois + " " + ans);
        //String[] produits = {"Burger","Baggel","hot-dog","pizza"};
        ImageView imageView = (ImageView) findViewById(R.id.retourListeRapport);
        ListView lv = (ListView) findViewById(R.id.liste);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle paquet = new Bundle();
                paquet.putStringArray("leRapport", rapports.get(position));
                System.out.println(rapports.get(position)[2]);
                Intent intent = new Intent(ListeRvActivity.this, VisuRvActivity.class);
                intent.putExtras(paquet);
                startActivity(intent);
            }
        });
        //ArrayAdapter<String> adapterMois = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,produits);
        //lv.setAdapter(adapterMois);
        try {
            ans = URLEncoder.encode(ans, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format("http://192.168.121.141:5000/rapports/%s/%s/%s", Session.getSession().getVisiteur().getMatricule(),moisEntier,ans);
        Response.Listener<String> ecouteurReponse = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response != null){

                    try {

                        JSONArray tblJson = new JSONArray(response);
                        for(int i = 0; i < tblJson.length(); i++){
                            JSONObject unObject = tblJson.getJSONObject(i);
                            System.out.println(unObject.get("rap_num").toString());
                            String[] unRapport = new String[]{unObject.get("rap_bilan").toString(),
                                    unObject.get("pra_cp").toString(),
                                    unObject.get("rap_num").toString(),
                                    unObject.get("pra_nom").toString(),
                                    unObject.get("rap_date_visite").toString(),
                                    unObject.get("pra_prenom").toString(),
                                    unObject.get("pra_ville").toString()};
                            rapports.add(unRapport);
                            listRap.add("Rapport " + unObject.get("rap_num").toString());
                        }
                        ListView lv = (ListView) findViewById(R.id.liste);
                        ArrayAdapter<String> adapterMois = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,listRap);
                        lv.setAdapter(adapterMois);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println(response);
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