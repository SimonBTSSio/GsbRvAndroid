package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("creation", "Creation de l'activite principale.");
    }
    public void seConnecter(View v){
        EditText matricule = (EditText) findViewById(R.id.matricule);
        EditText mdp = (EditText) findViewById(R.id.mdp);


        String chaine = "Connexion ok (" + matricule.getText() + mdp.getText() + ". Ou Connexion Nok";
        Log.i("seConnecter", chaine);

    }
    public void annuler(View v){
        Log.i("annuler", "Initialisation des champs");
    }
}