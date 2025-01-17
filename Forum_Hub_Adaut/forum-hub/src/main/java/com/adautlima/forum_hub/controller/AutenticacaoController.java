package com.adautlima.forum_hub.controller;

import com.adautlima.forum_hub.domain.usuario.DadosAutenticacao;
import com.adautlima.forum_hub.domain.usuario.Usuario;
import com.adautlima.forum_hub.infra.security.DadosTokenJwt;
import com.adautlima.forum_hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJwt> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()));
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));
    }
}
