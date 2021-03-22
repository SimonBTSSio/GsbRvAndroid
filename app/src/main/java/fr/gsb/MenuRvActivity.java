package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.gsb.rv.technique.Session;

public class MenuRvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rv);
        String nomPrenom = Session.getSession().getVisiteur().getPrenom() + " " + Session.getSession().getVisiteur().getNom();
        TextView nomPrenomUti = (TextView)findViewById(R.id.textView3);
        nomPrenomUti.setText(nomPrenom);

    }
    public void consulterRapport(View v){
        Intent intentionEnvoyer = new Intent(MenuRvActivity.this, RechercheRvActivity.class);
        startActivity(intentionEnvoyer);
    }
    public void deconnexion(View v){
        Session.fermer();
        Intent intentionEnvoyer = new Intent(MenuRvActivity.this, MainActivity.class);
        startActivity(intentionEnvoyer);

    }
}