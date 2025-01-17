package com.adautlima.forum_hub.Repositorys;

import com.adautlima.forum_hub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findByEstadoTopicoTrue(Pageable paginacao);
}
