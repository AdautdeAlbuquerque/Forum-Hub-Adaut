package com.adautlima.forum_hub.domain.topico;

public record DadosAtualizacaoTopico(
                                     String titulo,
                                     String mensagem,
                                     String autor,
                                     String curso) {
    public DadosAtualizacaoTopico(Topico topico){
        this( topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}
