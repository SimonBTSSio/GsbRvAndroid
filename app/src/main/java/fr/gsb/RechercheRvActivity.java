package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

public class RechercheRvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_rv);
        ImageView imageView = (ImageView) findViewById(R.id.myImageView);
        Spinner spinnerMois = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> mois = new ArrayList<String>();
        String[] moisTbl = new String[]{"Janvier","Février","Mars","Avril","Mais","Juin","Juillet","Aout","Septembre","Octobre","Nomvembre","Décembre"};
        for(String unMois : moisTbl){
            mois.add(unMois);
        }
        ArrayAdapter<String> adapterMois = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,mois);
        spinnerMois.setAdapter(adapterMois);
        Spinner spinnerAns = (Spinner) findViewById(R.id.spinner2);
        Date d=new Date();
        int year=d.getYear();
        int currentYear=year+1900;
        ArrayList<Integer> annees = new ArrayList<Integer>();
        for(int i = 0; i < 6; i++){
            annees.add(currentYear - i);
        }
        ArrayAdapter<Integer> adapterAns = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_spinner_item,annees);
        spinnerAns.setAdapter(adapterAns);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheRvActivity.this, MenuRvActivity.class);
                startActivity(intent);
            }
        });
    }
    public void listeRapports(View v){
        Spinner spinnerMois = (Spinner) findViewById(R.id.spinner);
        String donneeSpinnerMois = spinnerMois.getSelectedItem().toString();
        Spinner spinnerAns = (Spinner) findViewById(R.id.spinner2);
        String donneeSpinnerAns = spinnerAns.getSelectedItem().toString();

        Bundle paquet = new Bundle();
        paquet.putString("mois", donneeSpinnerMois);
        paquet.putString("ans", donneeSpinnerAns);
        Intent intent = new Intent(RechercheRvActivity.this, ListeRvActivity.class);
        intent.putExtras(paquet);
        startActivity(intent);
    }
}