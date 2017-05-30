package tech.alvarez.noticias.models;

/**
 * Created by Daniel Alvarez on 15/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class Noticia {

    private String titulo;
    private String descripcion;
    private String urlFoto;
    private String urlNoticia;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlNoticia() {
        return urlNoticia;
    }

    public void setUrlNoticia(String urlNoticia) {
        this.urlNoticia = urlNoticia;
    }
}