package com.adautlima.forum_hub.domain.topico;

public record DadosListagemTopico(Long id,
                                  String autor,
                                  String curso,
                                  String data,
                                  boolean estadoTopico,
                                  String mensagem,
                                  String titulo) {


public DadosListagemTopico(Topico topico){
    this(topico.getId(), topico.getAutor(), topico.getCurso(),
            topico.getDataCriacao(), topico.isEstadoTopico(),topico.getMensagem(),topico.getTitulo());
}

}
