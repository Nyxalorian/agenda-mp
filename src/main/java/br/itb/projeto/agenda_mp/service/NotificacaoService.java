package br.itb.projeto.agenda_mp.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class NotificacaoService {

    public String enviar(String token, String titulo, String corpo) throws Exception {

        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification.builder()
                                .setTitle(titulo)
                                .setBody(corpo)
                                .build())
                .build();

        return FirebaseMessaging.getInstance().send(message);
    }
}