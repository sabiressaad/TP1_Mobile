package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

        // Animer l'entrée des éléments du formulaire
        animateFormElements();

        // Configurer le bouton Envoyer
        buttonEnvoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vérifier si tous les champs sont remplis
                if (validateForm()) {
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

                    // Démarrer la seconde activité avec une animation
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                } else {
                    // Afficher un message d'erreur
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    // Animer le bouton pour indiquer l'erreur
                    Animation shake = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
                    buttonEnvoyer.startAnimation(shake);
                }
            }
        });
    }

    // Vérifier si tous les champs requis sont remplis
    private boolean validateForm() {
        boolean valid = true;

        if (editTextNomPrenom.getText().toString().trim().isEmpty()) {
            editTextNomPrenom.setError("Ce champ est requis");
            valid = false;
        }

        if (editTextEmail.getText().toString().trim().isEmpty()) {
            editTextEmail.setError("Ce champ est requis");
            valid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches()) {
            editTextEmail.setError("Email invalide");
            valid = false;
        }

        if (editTextPhone.getText().toString().trim().isEmpty()) {
            editTextPhone.setError("Ce champ est requis");
            valid = false;
        }

        if (editTextAdresse.getText().toString().trim().isEmpty()) {
            editTextAdresse.setError("Ce champ est requis");
            valid = false;
        }

        return valid;
    }

    // Animer les éléments du formulaire un par un
    private void animateFormElements() {
        // Charger les animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn.setDuration(400);

        // Appliquer les animations avec un délai pour chaque élément
        int delay = 150; // Délai en millisecondes entre chaque élément

        editTextNomPrenom.setVisibility(View.INVISIBLE);
        editTextEmail.setVisibility(View.INVISIBLE);
        editTextPhone.setVisibility(View.INVISIBLE);
        editTextAdresse.setVisibility(View.INVISIBLE);
        spinnerVille.setVisibility(View.INVISIBLE);
        buttonEnvoyer.setVisibility(View.INVISIBLE);

        editTextNomPrenom.postDelayed(new Runnable() {
            @Override
            public void run() {
                editTextNomPrenom.setVisibility(View.VISIBLE);
                editTextNomPrenom.startAnimation(fadeIn);
            }
        }, delay);

        editTextEmail.postDelayed(new Runnable() {
            @Override
            public void run() {
                editTextEmail.setVisibility(View.VISIBLE);
                editTextEmail.startAnimation(fadeIn);
            }
        }, delay * 2);

        editTextPhone.postDelayed(new Runnable() {
            @Override
            public void run() {
                editTextPhone.setVisibility(View.VISIBLE);
                editTextPhone.startAnimation(fadeIn);
            }
        }, delay * 3);

        editTextAdresse.postDelayed(new Runnable() {
            @Override
            public void run() {
                editTextAdresse.setVisibility(View.VISIBLE);
                editTextAdresse.startAnimation(fadeIn);
            }
        }, delay * 4);

        spinnerVille.postDelayed(new Runnable() {
            @Override
            public void run() {
                spinnerVille.setVisibility(View.VISIBLE);
                spinnerVille.startAnimation(fadeIn);
            }
        }, delay * 5);

        buttonEnvoyer.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonEnvoyer.setVisibility(View.VISIBLE);
                buttonEnvoyer.startAnimation(fadeIn);
            }
        }, delay * 6);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}