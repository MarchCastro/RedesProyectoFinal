
package play.sound;

import java.io.Serializable;

public class Cancion  implements Serializable 
{
    private int id;
    private String titulo;
    private String artista;
    private String album;
    private String portada;
    private String anio;
    private String genero;
    private String duracion;
    private String archivo;
    
    
    public Cancion(int id, String titulo, String artista, String album, String portada, String anio, String genero, String duracion, String archivo){
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.portada = portada;
        this.anio = anio;
        this.genero = genero;
        this.duracion = duracion;
        this.archivo = archivo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
    
    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
}
