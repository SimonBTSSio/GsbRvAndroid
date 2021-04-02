package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VisuRvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visu_rv);
        Bundle paquet = this.getIntent().getExtras();
        String[] mois = paquet.getStringArray("leRapport");
        String titre = "Rapport du " + mois[4];
        TextView titreRapport = (TextView) findViewById(R.id.textView7);
        titreRapport.setText(titre);
        TextView rapportNum = (TextView) findViewById(R.id.tvRapportNum);
        System.out.println(mois[0] + " " + mois[1]);
        rapportNum.setText("Numero : " + mois[2]);
        TextView rapportBilan = (TextView) findViewById(R.id.tvRapportBilan);
        rapportBilan.setText("Bilan : " + mois[0]);

        TextView praticienNom = (TextView) findViewById(R.id.praticienNom);
        praticienNom.setText("Nom : " + mois[3]);
        TextView praticienPrenom = (TextView) findViewById(R.id.praticienPrenom);
        praticienPrenom.setText("Prenom : " + mois[5]);
        TextView praticienVille = (TextView) findViewById(R.id.praticienVille);
        praticienVille.setText("Ville : " + mois[6]);
        TextView praticienCP = (TextView) findViewById(R.id.praticienCP);
        praticienCP.setText("Code postal : " + mois[1]);
        ImageView imageView = (ImageView) findViewById(R.id.retourVisuRapport);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisuRvActivity.this, RechercheRvActivity.class);
                startActivity(intent);
            }
        });
    }
}