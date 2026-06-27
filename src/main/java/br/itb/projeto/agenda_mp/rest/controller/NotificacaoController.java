package br.itb.projeto.agenda_mp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.itb.projeto.agenda_mp.service.NotificacaoService;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping("/teste")
    public String teste(@RequestBody java.util.Map<String, String> body) throws Exception {

        String token = body.get("token");

        return notificacaoService.enviar(
                token,
                "PharmaLife",
                "Teste de notificação 🚀");
    }
}