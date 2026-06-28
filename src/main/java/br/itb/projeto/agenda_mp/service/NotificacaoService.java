package br.itb.projeto.agenda_mp.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificacaoService {

    public String enviar(String token, String titulo, String corpo) {

        try {

            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(
                            Notification.builder()
                                    .setTitle(titulo)
                                    .setBody(corpo)
                                    .build())
                    .setToken(token)
                    .build();

            String resposta = FirebaseMessaging.getInstance().send(message);

            System.out.println("=================================");
            System.out.println("Mensagem enviada com sucesso!");
            System.out.println("Resposta Firebase: " + resposta);
            System.out.println("=================================");

            return resposta;

        } catch (Exception e) {

            System.out.println("=================================");
            System.out.println("ERRO AO ENVIAR NOTIFICAÇÃO");
            e.printStackTrace();
            System.out.println("=================================");

            return null;
        }
    }
}