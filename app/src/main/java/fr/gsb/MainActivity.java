package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import fr.gsb.rv.technique.Session;

public class MainActivity extends AppCompatActivity {
    private EditText etMatricule;
    private EditText etMdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.i("creation", "Creation de l'activite principale.");
        etMatricule = (EditText) findViewById(R.id.matricule);
        etMdp = (EditText) findViewById(R.id.mdp);


    }
    public void seConnecter(View v){
        /*if(etMatricule.getText().toString() == "a131" && etMdp.getText().toString() == "azerty"){
            Log.i("seConnecter", "Connexion ok (" + etMatricule.getText() + " " + etMdp.getText());
        }else{
            Log.i("seConnecter", "Connexion Nok");
            Toast.makeText(getApplicationContext(),"Echec à la connexion. Recommencez...", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(getApplicationContext(),"Echec à la connexion. Recommencez...",Toast.LENGTH_SHORT);
            toast.setMargin(0,0);
            toast.show();
        }*/
        if(Session.getSession() != null){
            Session.fermer();
            //Log.i("Fermeture" , "Session fermer");
        }
        String matHttp = null;
        String mdpHttp = null;
        try {
            matHttp = URLEncoder.encode(String.valueOf(etMatricule.getText()), "UTF-8");
            mdpHttp = URLEncoder.encode(String.valueOf(etMdp.getText()), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        String url = String.format("http://192.168.121.141:5000/visiteurs/%s/%s", matHttp,mdpHttp);
        Response.Listener<String> ecouteurReponse = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i("reponse" , response);
                if(response != null){
                    String data = response.substring(1, response.length()-1);
                    String tblData[] = data.split(", ");
                    String finalData[] = new String[3];
                    for (int i = 0; i < tblData.length; i++) {
                        String tbl[] = tblData[i].split(": ");
                        //Log.i("test",tbl[1].substring(1, tbl[1].length()-1));
                        finalData[i] = tbl[1].substring(1, tbl[1].length()-1);
                    }
                    Session.ouvrir(new Visiteur(finalData[0],etMdp.getText().toString(),finalData[2],finalData[1]));
                    Intent intentionEnvoyer = new Intent(MainActivity.this, MenuRvActivity.class);
                    startActivity(intentionEnvoyer);

                    Log.i("test", Session.getSession().toString());
                }

            }
        };
        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("erreur connexion", "Erreur HTTP : " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Echec à la connexion. Recommencez...", Toast.LENGTH_SHORT).show();
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




    }
    public void annuler(View v){
        //Log.i("annuler", "Initialisation des champs");
        etMatricule.setText("");
        etMdp.setText("");
    }
}