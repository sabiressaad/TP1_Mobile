package com.example.tp1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewRecapitulatif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialiser le TextView
        textViewRecapitulatif = findViewById(R.id.textViewRecapitulatif);

        // Récupérer les données de l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nomPrenom = extras.getString("NOM_PRENOM");
            String email = extras.getString("EMAIL");
            String phone = extras.getString("PHONE");
            String adresse = extras.getString("ADRESSE");
            String ville = extras.getString("VILLE");

            // Construire le texte récapitulatif
            StringBuilder sb = new StringBuilder();
            sb.append("Nom : ").append(nomPrenom).append("\n");
            sb.append("Email : ").append(email).append("\n");
            sb.append("Phone : ").append(phone).append("\n");
            sb.append("Adresse : ").append(adresse).append("\n");
            sb.append("Ville : ").append(ville);

            // Afficher le texte récapitulatif
            textViewRecapitulatif.setText(sb.toString());
        }
    }
}