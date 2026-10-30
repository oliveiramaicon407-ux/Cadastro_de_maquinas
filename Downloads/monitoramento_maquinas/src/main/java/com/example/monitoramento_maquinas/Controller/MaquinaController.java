package com.example.monitoramento_maquinas.Controller;


import com.example.monitoramento_maquinas.model.Maquina;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MaquinaController {

    // Lista em memória para simular o banco de dados por enquanto
    private List<Maquina> listaMaquinas = new ArrayList<>();

    @GetMapping
    public String home() {
        return "API de Monitoramento Industrial - CloudTech Solutions em Execução!";
    }

    // RF05 — Verificar status da aplicação
    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> verificarStatus() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("status", "online");
        resposta.put("mensagem", "Aplicação de monitoramento em execução");
        return ResponseEntity.ok(resposta);
    }

    // RF01 — Cadastrar máquina
    @PostMapping("/maquinas/cadastro")
    public ResponseEntity<Maquina> cadastrarMaquina(@RequestBody Maquina maquina) {
        listaMaquinas.add(maquina);
        return ResponseEntity.ok(maquina);
    }

    // RF03 — Consultar máquinas cadastradas
    @GetMapping("/maquinas")
    public ResponseEntity<List<Maquina>> listarMaquinas() {
        return ResponseEntity.ok(listaMaquinas);
    }

    // Versão da aplicação
    @GetMapping("/versao")
    public String versao() {
        return "Versão da aplicação: 1.0.0";
    }
}