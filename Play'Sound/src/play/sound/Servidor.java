
package play.sound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Servidor {
    public static void main(String[] args) {
    try{
            int pto = 2000;
            ServerSocket s= new ServerSocket(pto);
            System.out.println("Iniciando Servicio.....");
            
            ArrayList <Cancion> lista= new ArrayList();
            //new Cancion(id, titulo, artista, album, imagen portada, año, genero, duración, archivo mp3);
            
            /*
            Cancion loco=new Cancion(1, "Loco", "kinky","04:45", 1);
            Cancion  Loco=new Cancion("Loco", "Siddhartha", "03:31",2);
            Cancion  Naufrago=new Cancion("Naufrago", "Siddhartha", "04:11",3);
            Cancion  Bailar=new Cancion("Bailar", "Deorro", "02:18",4);
            Cancion  Turn=new Cancion("Turn back", "Deorro", "03:49",5);
            Cancion  Have=new Cancion("Have you ever seen the rain", "Creedence", "02:40",6);
            Cancion  How=new Cancion("How deep is your love", "Bee Gees", "04:02",7);
            Cancion  Stayin=new Cancion("Stayin Alive", "Bee Gees", "04:02",8);
            Cancion  Piano=new Cancion("Piano Man", "Billy Joel", "05:36",9);
            Cancion  Pierdeme=new Cancion("Pierdeme el respeto", "Playa Limbo", "03:38",10);
            Cancion  Thunder=new Cancion("Thunder", "Imagine Dragons", "03:07",11);
            Cancion  Sweet=new Cancion("Sweet child o mine", "Guns N' Roses", "05:54",12);
            */
            
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde: " + cl.getInetAddress().getHostAddress() + "  :  " + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                
                
                String res=dis.readUTF();
                System.out.println(" "+res);
                String[] cancion=res.split(" ");
                System.out.println(" "+cancion);
                for (int i = 0; i < cancion.length; i++) {
                    System.out.println(cancion[i]);
                }
                
            }
            
        }catch(Exception e){
        }
    }
}

