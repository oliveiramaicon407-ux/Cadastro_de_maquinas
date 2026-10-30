package com.example.monitoramento_maquinas.Controller;

import com.example.monitoramento_maquinas.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class LivroController {

    private List<Livro> listaLivros = new ArrayList<>();
    private Long contadorId = 1L;

    // a) Página inicial
    @GetMapping
    public Map<String, String> paginaInicial() {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Bem-vindo ao sistema de gerenciamento da Biblioteca CloudTech Solutions");
        return resposta;
    }

    // b) Consultar livros disponíveis
    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(listaLivros);
    }

    // c) Cadastrar novo livro
    @PostMapping("/livros/cadastrar")
    public ResponseEntity<Livro> cadastrarLivro(@RequestBody Livro livro) {
        livro.setId(contadorId++);
        listaLivros.add(livro);
        return ResponseEntity.ok(livro);
    }

    // d) Alterar informações de um livro existente
    @PutMapping("/livros/alterar/{id}")
    public ResponseEntity<Object> alterarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        Optional<Livro> livroOpt = listaLivros.stream().filter(l -> l.getId().equals(id)).findFirst();
        
        if (livroOpt.isPresent()) {
            Livro livro = livroOpt.get();
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setAutor(livroAtualizado.getAutor());
            livro.setEditora(livroAtualizado.getEditora());
            livro.setAno(livroAtualizado.getAno());
            livro.setCategoria(livroAtualizado.getCategoria());
            livro.setStatus(livroAtualizado.getStatus());
            return ResponseEntity.ok(livro);
        }
        
        return ResponseEntity.status(404).body("Livro não encontrado com o ID: " + id);
    }

    // e) Consultar livro por ID
    @GetMapping("/livros/{id}")
    public ResponseEntity<Object> consultarPorId(@PathVariable Long id) {
        Optional<Livro> livro = listaLivros.stream().filter(l -> l.getId().equals(id)).findFirst();
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        }
        return ResponseEntity.status(404).body("Livro não encontrado");
    }

    // f) Documentação dos endpoints
    @GetMapping("/documentacao")
    public ResponseEntity<Map<String, Object>> documentacao() {
        Map<String, Object> doc = new HashMap<>();
        List<String> endpoints = Arrays.asList(
            "/",
            "/livros",
            "/livros/cadastrar",
            "/livros/alterar/{id}",
            "/livros/{id}",
            "/documentacao"
        );
        doc.put("endpoints", endpoints);
        return ResponseEntity.ok(doc);
    }
}