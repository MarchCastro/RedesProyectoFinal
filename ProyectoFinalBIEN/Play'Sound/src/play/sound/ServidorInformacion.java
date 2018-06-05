package play.sound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class ServidorInformacion {
    public static void main(String [] args){
        try{
            int pto = 7000;
            ServerSocket s= new ServerSocket(pto);
            s.setReuseAddress(true);
            System.out.println("Iniciando Servicio.....");
            ArrayList <Cancion> lista= new ArrayList();
            Cancion d0 = new Cancion(0, "Loco", "Kinky", "Album",          "src/Portadas/locoK.jpg", "año", "genero", "04:45", "src/Musica/Loco-kinky.mp3");
            Cancion d1 = new Cancion(1, "Loco", "Siddhartha", "Album",     "src/Portadas/locoS.jpg", "año", "genero", "03:31", "src/Musica/Loco-Siddhartha.mp3");
            Cancion d2 = new Cancion(2, "Naufrago", "Siddhartha", "Album", "src/Portadas/naufrago.jpg", "año", "genero", "03:31", "src/Musica/Naufrago- Siddhartha.mp3");
            Cancion d3 = new Cancion(3, "Bailar", "Deorro", "Album",       "src/Portadas/bailar.jpg", "año", "genero", "02:18", "src/Musica/Bailar- Deorro.mp3");
            Cancion d4 = new Cancion(4, "Turn back", "Deorro", "Album",    "src/Portadas/tbt.jpg", "año", "genero", "03:49", "src/Musica/Turn Back Time-Deorro.mp3");
            Cancion d5 = new Cancion(5, "Have you ever seen the rain", "Creedence", "Album", "src/Portadas/hyestr.jpg", "año", "genero", "02:40", "src/Musica/Have you ever seen the rain-Creedence.mp3");
            Cancion d6 = new Cancion(6, "How deep is your love", "Bee Gees", "Album",        "src/Portadas/hdiyl.jpg", "año", "genero", "04:02", "src/Musica/How deep is your love-Bee Gees.mp3");
            Cancion d7 = new Cancion(7, "Stayin Alive", "Bee Gees", "Album", "src/Portadas/sa.jpg", "año", "genero", "04:02", "src/Musica/Stayin Alive-Bee Gees .mp3");
            Cancion d8 = new Cancion(8, "Piano Man", "Billy Joel", "Album",  "src/Portadas/piano.jpg", "año", "genero", "05:36", "src/Musica/Piano man-Billy Joel .mp3");
            Cancion d9 = new Cancion(9, "Pierdeme el respeto", "Playa Limbo", "Album",    "src/Portadas/per.jpg", "año", "genero", "03:38", "src/Musica/Pierdeme el respeto-Playa Limbo -.mp3");
            Cancion d10 = new Cancion(10, "Thunder", "Imagine Dragons", "Album",          "src/Portadas/thunder.jpg", "año", "genero", "03:38", "src/Musica/Thunder- Imagine Dragons.mp3");
            Cancion d11 = new Cancion(11, "Sweet child o mine", "Guns N' Roses", "Album", "src/Portadas/scom.jpg", "año", "genero", "05:54", "src/Musica/Sweet child o mine-Guns N' Roses.mp3");
            
            lista.add(d0);
            lista.add(d1);
            lista.add(d2);
            lista.add(d3);
            lista.add(d4);
            lista.add(d5);
            lista.add(d6);
            lista.add(d7);
            lista.add(d8);
            lista.add(d9);
            lista.add(d10);
            lista.add(d11);
            
            for(;;){
                Socket cl = s.accept();
                DataInputStream dis = new DataInputStream(cl.getInputStream());//recibimos indice de la cancion
                ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());//enviamos la cancion correspondiente
                
                int index = dis.readInt();
                System.out.println("Recibimos: " + index);
                oos.writeObject(lista.get(index));
                oos.flush();
                Thread.sleep(50);
                dis.close();
                oos.close();
            }
            
        }catch(Exception e){
        }
    }
}
