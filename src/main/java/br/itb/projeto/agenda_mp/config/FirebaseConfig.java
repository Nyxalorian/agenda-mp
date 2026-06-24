package br.itb.projeto.agenda_mp.config;

import java.io.InputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {

        System.out.println("========== FIREBASE CONFIG CARREGADO ==========");

        try {

            InputStream serviceAccount =
                    getClass()
                    .getClassLoader()
                    .getResourceAsStream("firebase/pharmalife-firebase.json");

            System.out.println("Arquivo encontrado? " + (serviceAccount != null));

            if (serviceAccount == null) {
                throw new RuntimeException("Arquivo do Firebase não encontrado!");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("Firebase Admin inicializado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}