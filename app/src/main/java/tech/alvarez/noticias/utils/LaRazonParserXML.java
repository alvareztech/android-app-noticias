package tech.alvarez.noticias.utils;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

import org.xml.sax.Attributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import tech.alvarez.noticias.models.Noticia;

/**
 * Created by Daniel Alvarez on 15/7/16.
 * Copyright © 2016 Alvarez.tech. All rights reserved.
 */
public class LaRazonParserXML {
    private URL url;
    private Noticia noticiaActual;

    public LaRazonParserXML(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Noticia> parse() {

        final ArrayList<Noticia> noticias = new ArrayList<Noticia>();

        RootElement root = new RootElement("rss");
        Element channel = root.getChild("channel");
        Element item = channel.getChild("item");

        item.setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                noticiaActual = new Noticia();
            }
        });


        item.getChild("title").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String s) {
                noticiaActual.setTitulo(s);
            }
        });

        item.getChild("description").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String s) {
                noticiaActual.setDescripcion(s);
            }
        });

        item.getChild("link").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String s) {
                noticiaActual.setUrlNoticia(s);
            }
        });

        item.getChild("enclosure").setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                String urlFoto = attributes.getValue("url");
                noticiaActual.setUrlFoto(urlFoto);
            }
        });


        item.setEndElementListener(new EndElementListener() {
            @Override
            public void end() {
                noticias.add(noticiaActual);
            }
        });

        try {
            Xml.parse(getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return noticias;
    }


    private InputStream getInputStream() {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}