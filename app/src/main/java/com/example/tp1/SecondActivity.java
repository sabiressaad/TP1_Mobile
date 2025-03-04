package com.example.tp1;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewRecapitulatif;
    private LinearLayout cardContainer;
    private Button buttonRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialiser les composants
        textViewRecapitulatif = findViewById(R.id.textViewRecapitulatif);
        cardContainer = findViewById(R.id.cardContainer);
        buttonRetour = findViewById(R.id.buttonRetour);

        // Récupérer les données de l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nomPrenom = extras.getString("NOM_PRENOM");
            String email = extras.getString("EMAIL");
            String phone = extras.getString("PHONE");
            String adresse = extras.getString("ADRESSE");
            String ville = extras.getString("VILLE");

            // Construire le texte récapitulatif avec mise en forme
            StringBuilder sb = new StringBuilder();
            sb.append("👤 Nom : ").append(nomPrenom).append("\n\n");
            sb.append("📧 Email : ").append(email).append("\n\n");
            sb.append("📱 Téléphone : ").append(phone).append("\n\n");
            sb.append("🏠 Adresse : ").append(adresse).append("\n\n");
            sb.append("🏙️ Ville : ").append(ville);

            // Afficher le texte récapitulatif
            textViewRecapitulatif.setText(sb.toString());
        }

        // Animer l'apparition de la carte
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.card_animation);
        cardContainer.startAnimation(fadeIn);

        // Configurer le bouton retour
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}