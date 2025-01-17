package com.adautlima.forum_hub.domain.topico;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="titulo")
    private String titulo;
    private String mensagem;
    private String dataCriacao;
    private boolean estadoTopico;
    private String autor;
    private String curso;

    public Topico(){}

    public Topico(String tituloDoComentario, String mensagem, String autor, String curso) {

        this.titulo=tituloDoComentario;
        this.mensagem=mensagem;
        this.dataCriacao= LocalDate.now().toString();
        this.estadoTopico=true;
        this.autor=autor;
        this.curso=curso;

    }

    public Topico(Topico topico) {
        this(topico.titulo, topico.mensagem,topico.autor, topico.curso);
    }

    @Override
    public String toString() {
        return String.format(
                "Tópico:\n" +
                        " - Título do Comentário: %s\n" +
                        " - Mensagem: %s\n" +
                        " - Data de Criação: %s\n" +
                        " - Estado do Tópico: %s\n" +
                        " - Autor: %s\n" +
                        " - Curso: %s",
                id,
                titulo,
                mensagem,
                dataCriacao,
                estadoTopico ? "Ativo" : "Inativo",
                autor,
                curso
        );
    }


    public void atualizarInformacao(DadosAtualizacaoTopico dados) {

        if(dados.curso()!=null){
            this.curso=dados.curso();
        }

        if(dados.autor()!=null){
            this.autor=dados.autor();
        }

        if(dados.titulo()!=null){
            this.titulo=dados.titulo();
        }

        if(dados.mensagem()!=null){
            this.mensagem=dados.mensagem();
        }

    }


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public boolean isEstadoTopico() {
        return estadoTopico;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
