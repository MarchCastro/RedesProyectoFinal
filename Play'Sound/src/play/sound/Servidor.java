
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
            
            ArrayList <canciones> lista= new ArrayList();
            
            canciones loco=new canciones("Loco", "kinky","04:45", 1);
            canciones  Loco=new canciones("Loco", "Siddhartha", "03:31",2);
            canciones  Naufrago=new canciones("Naufrago", "Siddhartha", "04:11",3);
            canciones  Bailar=new canciones("Bailar", "Deorro", "02:18",4);
            canciones  Turn=new canciones("Turn back", "Deorro", "03:49",5);
            canciones  Have=new canciones("Have you ever seen the rain", "Creedence", "02:40",6);
            canciones  How=new canciones("How deep is your love", "Bee Gees", "04:02",7);
            canciones  Stayin=new canciones("Stayin Alive", "Bee Gees", "04:02",8);
            canciones  Piano=new canciones("Piano Man", "Billy Joel", "05:36",9);
            canciones  Pierdeme=new canciones("Pierdeme el respeto", "Playa Limbo", "03:38",10);
            canciones  Thunder=new canciones("Thunder", "Imagine Dragons", "03:07",11);
            canciones  Sweet=new canciones("Sweet child o mine", "Guns N' Roses", "05:54",12);
            
            
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

