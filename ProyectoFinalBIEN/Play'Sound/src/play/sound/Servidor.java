
package play.sound;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.net.*;
import java.io.*;


public class Servidor {
    
    public static void main(String[] args) {
    try{
            int pto = 9000;
            ServerSocket s= new ServerSocket(pto);
            s.setReuseAddress(true);
            System.out.println("Iniciando Servicio.....");
            
            ArrayList <Cancion> lista= new ArrayList();
            //new Cancion(id, titulo, artista, album, imagen portada, año, genero, duración, archivo mp3);
            
            
            Cancion d1 = new Cancion(1, "Loco", "Kinky", "Nada vale más que tú", "src/Portadas/locoK.jpg", "2017", "Alternativo/independiente", "04:45", "src/Musica/Loco-kinky.mp3");
            Cancion d2 = new Cancion(2, "Naufrago", "Siddhartha", "Naufrago", "src/Portadas/naufrago.jpg", "2011", "Rock en español", "03:31", "src/Musica/Naufrago- Siddhartha.mp3");
            Cancion d3 = new Cancion(3, "Bailar", "Deorro", "Berlin Tag & Nacht: Party & Feiern 2017", "src/Portadas/bailar.jpg", "2017", "Dance", "02:18", "src/Musica/Bailar- Deorro.mp3");
            Cancion d4 = new Cancion(4, "Turn back time", "Deorro", "Good evening", "src/Portadas/tbt.jpg", "2017", "Dance", "03:49", "src/Musica/Turn Back Time-Deorro.mp3");
            Cancion d5 = new Cancion(5, "Have you ever seen the rain", "Creedence", "Pendulum", "src/Portadas/hyestr.jpg", "1970", "Rock", "02:40", "src/Musica/Have you ever seen the rain-Creedence.mp3");
            Cancion d6 = new Cancion(6, "How deep is your love", "Bee Gees", "Fiebre de sábado por la noche", "src/Portadas/hdiyl.jpg", "1977", "Pop", "04:02", "src/Musica/How deep is your love-Bee Gees.mp3");
            Cancion d7 = new Cancion(7, "Stayin Alive", "Bee Gees", "Fiebre de sábado por la noche", "src/Portadas/sa.jpg", "1977", "Pop", "04:02", "src/Musica/Stayin Alive-Bee Gees .mp3");
            Cancion d8 = new Cancion(8, "Piano Man", "Billy Joel", "Piano Man", "src/Portadas/piano.jpg", "1973", "Pop/cantautor", "05:36", "src/Musica/Piano man-Billy Joel .mp3");
            Cancion d9 = new Cancion(9, "Pierdeme el respeto", "Playa Limbo", "De días y noches", "src/Portadas/per.jpg", "2015", "Pop", "03:38", "src/Musica/Pierdeme el respeto-Playa Limbo -.mp3");
            Cancion d10 = new Cancion(10, "Thunder", "Imagine Dragons", "Evolve", "src/Portadas/thunder.jpg", "2017", "Rock pop", "03:38", "src/Musica/Thunder- Imagine Dragons.mp3");
            Cancion d11 = new Cancion(11, "Sweet child o mine", "Guns N' Roses", "Appetite for Destruction", "src/Portadas/scom.jpg", "1987", "Hard rock", "05:54", "src/Musica/Sweet child o mine-Guns N' Roses.mp3");
            Cancion d12 = new Cancion(12, "Loco", "Siddhartha", "El vuelo del pez", "src/Portadas/locoS.jpg", "2014", "Rock en español", "03:31", "src/Musica/Loco-Siddhartha.mp3");
            
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
            lista.add(d12);
            
            
            
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde: " + cl.getInetAddress().getHostAddress() + "  :  " + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                PrintWriter numero = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                int opcion = dis.readInt();
                System.out.println("OPCION: " + opcion);
                if(opcion == 0){
                    String res = dis.readUTF();
                    System.out.println("Recibí... "+res);
                    ArrayList <String> canciones_enviar= new ArrayList();
                    if(res.length() > 0){
                        String[] cancion = res.toLowerCase().split(" ");     
                        //System.out.println(" "+cancion);
                        for (int i = 0; i < cancion.length; i++) {
                            //System.out.print("Cancion enviada "+cancion[i]);
                            for(int j = 0; j < lista.size(); j++){
                                String cancion_titulo = lista.get(j).getTitulo().toLowerCase();
                                if(cancion_titulo.contains(cancion[i])){
                                    System.out.println("\n Envio: "+ lista.get(j).getId() + " " +lista.get(j).getTitulo());
                                        canciones_enviar.add(lista.get(j).getId() + "--" +lista.get(j).getTitulo());
                                }
                            }
                        }
                    }else{
                        for(int j = 0; j < lista.size(); j++){
                            System.out.println("\n Envio: "+ lista.get(j).getId() + " " +lista.get(j).getTitulo());
                            canciones_enviar.add(lista.get(j).getId() + "--" +lista.get(j).getTitulo());  
                        }
                    }

                    dos.writeInt(canciones_enviar.size());
                    dos.flush();

                    for(int i = 0; i < canciones_enviar.size(); i++) {
                        System.out.println("\n LISTA "+ canciones_enviar.get(i));
                        dos.writeUTF(canciones_enviar.get(i));
                        dos.flush();
                    }
                }else{
                    int res = dis.readInt();
                    System.out.println("Me piden: "+res);

                    for(int i = 0; i < lista.size(); i++){
                        if(res == lista.get(i).getId()){
                            ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                            oos.writeObject(lista.get(i));
                            System.out.println("\n Envio: "+lista.get(i).getId());
                            oos.flush();
                        }
                    }
                }
                
                //Numero 
                //Envia objeto con productos
                /* */
                
            }
            
        }catch(Exception e){
        }
    }
}

