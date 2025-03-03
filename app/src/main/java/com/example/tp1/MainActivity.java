package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNomPrenom, editTextEmail, editTextPhone, editTextAdresse;
    private Spinner spinnerVille;
    private Button buttonEnvoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser les composants de l'interface
        editTextNomPrenom = findViewById(R.id.editTextNomPrenom);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextAdresse = findViewById(R.id.editTextAdresse);
        spinnerVille = findViewById(R.id.spinnerVille);
        buttonEnvoyer = findViewById(R.id.buttonEnvoyer);

        // Configurer le spinner avec une liste de villes
        String[] villes = {"Agadir", "Marrakech", "Casablanca", "Rabat", "Tanger", "Fès"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, villes);
        spinnerVille.setAdapter(adapter);

        // Configurer le bouton Envoyer
        buttonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les données saisies
                String nomPrenom = editTextNomPrenom.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String adresse = editTextAdresse.getText().toString();
                String ville = spinnerVille.getSelectedItem().toString();

                // Créer un intent pour passer à la seconde activité
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // Ajouter les données à l'intent
                intent.putExtra("NOM_PRENOM", nomPrenom);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PHONE", phone);
                intent.putExtra("ADRESSE", adresse);
                intent.putExtra("VILLE", ville);

                // Démarrer la seconde activité
                startActivity(intent);
            }
        });
    }
}