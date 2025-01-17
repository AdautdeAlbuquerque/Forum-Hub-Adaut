package com.adautlima.forum_hub.controller;

import com.adautlima.forum_hub.Repositorys.TopicoRepository;
import com.adautlima.forum_hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

        @Autowired
        private TopicoRepository topicoRepository;

        @PostMapping
        @Transactional
        public ResponseEntity<String> cadastrarTopicos(@RequestBody @Valid DadosCadastroTopico dados) {
                var titulo = dados.tituloDoComentario();
                var mensagem = dados.mensagem();

                Optional<Topico> verificaTitulo = topicoRepository.findByTituloAndMensagem(titulo, mensagem);

                if (verificaTitulo.isPresent()) {
                        return ResponseEntity.badRequest().body("Já existe um tópico igual cadastrado!");
                }

                var topico = new Topico(titulo, mensagem, dados.autor(), dados.curso());
                topicoRepository.save(topico);
                return ResponseEntity.ok("Cadastrado com sucesso!");
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> buscarTopicoPorId(@PathVariable Long id) {
                return verificarId(id);
        }

        @GetMapping
        public ResponseEntity<Page<Map<String, Object>>> listarTopicos(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
                var dadosTopicos = topicoRepository.findByEstadoTopicoTrue(paginacao)
                        .map(topico -> {
                                Map<String, Object> dados = new HashMap<>();
                                dados.put("id", topico.getId());
                                dados.put("titulo", topico.getTitulo());
                                dados.put("mensagem", topico.getMensagem());
                                dados.put("data", topico.getDataCriacao());
                                dados.put("estadoTopico", topico.isEstadoTopico() ? "Ativo" : "Inativo");
                                dados.put("autor", topico.getAutor());
                                dados.put("curso", topico.getCurso());
                                return dados;
                        });

                return ResponseEntity.ok(dadosTopicos);
        }

        @GetMapping("/recentes")
        public ResponseEntity<List<Map<String, Object>>> listarTop10TopicosRecentes() {
                var topicosRecentes = topicoRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataCriacao")))
                        .getContent();

                var dadosTopicos = topicosRecentes.stream()
                        .map(topico -> {
                                Map<String, Object> dados = new HashMap<>();
                                dados.put("id", topico.getId());
                                dados.put("titulo", topico.getTitulo());
                                dados.put("mensagem", topico.getMensagem());
                                dados.put("data", topico.getDataCriacao());
                                dados.put("estadoTopico", topico.isEstadoTopico() ? "Ativo" : "Inativo");
                                dados.put("autor", topico.getAutor());
                                dados.put("curso", topico.getCurso());
                                return dados;
                        })
                        .toList();

                return ResponseEntity.ok(dadosTopicos);
        }

        @GetMapping("/buscar")
        public ResponseEntity<List<Map<String, Serializable>>> buscarTopicosPorCursoEAno(@RequestParam String curso, @RequestParam String ano) {
                var topicosFiltrados = topicoRepository.findAll()
                        .stream()
                        .filter(topico -> topico.getCurso().toLowerCase().contains(curso.toLowerCase()) && topico.getDataCriacao().startsWith(ano))
                        .map(topico -> Map.<String, Serializable>of(
                                "id", topico.getId(),
                                "titulo", topico.getTitulo(),
                                "mensagem", topico.getMensagem(),
                                "autor", topico.getAutor(),
                                "curso", topico.getCurso(),
                                "estadoTopico", topico.isEstadoTopico() ? "Ativo" : "Inativo",
                                "data", topico.getDataCriacao()
                        ))
                        .toList();

                return ResponseEntity.ok(topicosFiltrados);
        }

        @PutMapping("/{id}")
        @Transactional
        public ResponseEntity<?> atualizarTopico(@RequestBody @Valid DadosAtualizacaoTopico dados, @PathVariable Long id) {
                var resposta = verificarId(id);
                if (resposta.getStatusCode().isError()) {
                        return resposta;
                }

                var topico = topicoRepository.getReferenceById(id);
                topico.atualizarInformacao(dados);
                return ResponseEntity.ok(new DadosAtualizacaoTopico(topico));
        }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<String> excluirTopico(@PathVariable Long id) {
                var resposta = verificarId(id);
                if (resposta.getStatusCode().isError()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O ID informado não existe.");
                }

                topicoRepository.deleteById(id);
                return ResponseEntity.ok("Registro excluído com sucesso.");
        }

        private ResponseEntity<?> verificarId(Long id) {
                var topico = topicoRepository.findById(id);
                if (topico.isPresent()) {
                        return ResponseEntity.ok(topico.get());
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tópico não encontrado.");
                }
        }
}
