
package play.sound;

import java.io.Serializable;

public class canciones  implements Serializable 
{
    String Nombre;
    String Autor;
    String Duracion;
    int ID;
    
    public canciones(canciones c){
        this.Nombre=c.Nombre;
        this.Autor=c.Autor;
        this.Duracion=c.Duracion;
        this.ID=c.ID;
    }
    
    public canciones(String n, String a, String d, int id){
        this.Nombre=n;
        this.Autor=a;
        this.Duracion=d;
        this.ID=id;   
    }
    
    public String getNombre(){
        return this.Nombre;
    }
    
    public String getAutor(){
        return this.Autor;
    }
    
    public String getDuracion(){
        return this.Duracion;
    }
    public void setID(int identificador){
        ID = identificador;
    }
    
}
