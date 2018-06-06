
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
            
            
            Cancion d1 = new Cancion(1, "Loco", "Kinky", "Album", "URL", "año", "genero", "04:45", "archivo");
            Cancion d2 = new Cancion(2, "Naufrago", "Siddhartha", "Album", "URL", "año", "genero", "03:31", "archivo");
            Cancion d3 = new Cancion(3, "Bailar", "Deorro", "Album", "URL", "año", "genero", "02:18", "archivo");
            Cancion d4 = new Cancion(4, "Turn back", "Deorro", "Album", "URL", "año", "genero", "03:49", "archivo");
            Cancion d5 = new Cancion(5, "Have you ever seen the rain", "Creedence", "Album", "URL", "año", "genero", "02:40", "archivo");
            Cancion d6 = new Cancion(6, "How deep is your love", "Bee Gees", "Album", "URL", "año", "genero", "04:02", "archivo");
            Cancion d7 = new Cancion(7, "Stayin Alive", "Bee Gees", "Album", "URL", "año", "genero", "04:02", "archivo");
            Cancion d8 = new Cancion(8, "Piano Man", "Billy Joel", "Album", "URL", "año", "genero", "05:36", "archivo");
            Cancion d9 = new Cancion(9, "Pierdeme el respeto", "Playa Limbo", "Album", "URL", "año", "genero", "03:38", "archivo");
            Cancion d11 = new Cancion(11, "Thunder", "Imagine Dragons", "Album", "URL", "año", "genero", "03:38", "archivo");
            Cancion d12 = new Cancion(12, "Pierdeme el respeto", "Playa Limbo", "Album", "URL", "año", "genero", "03:07", "archivo");
            Cancion d13 = new Cancion(13, "Sweet child o mine", "Guns N' Roses", "Album", "URL", "año", "genero", "05:54", "archivo");
            Cancion d14 = new Cancion(14, "Loco", "Siddhartha", "Album", "URL", "año", "genero", "03:31", "archivo");
            
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
            lista.add(d13);
            lista.add(d14);
            
            
            
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectado desde: " + cl.getInetAddress().getHostAddress() + "  :  " + cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                PrintWriter numero = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));

                String res = dis.readUTF();
                System.out.println("Recibí... "+res);
                String[] cancion = res.toLowerCase().split(" ");
                ArrayList <String> canciones_enviar= new ArrayList();   
                //System.out.println(" "+cancion);
                for (int i = 0; i < cancion.length; i++) {
                    //System.out.print("Cancion enviada "+cancion[i]);
                    for(int j = 0; j < lista.size(); j++){
                        String[] cancion_titulo = lista.get(j).getTitulo().toLowerCase().split(" ");
                        for(int k = 0; k < cancion_titulo.length; k++){
                            //System.out.print("Cancion titulo "+cancion_titulo[k] +"\n");
                            if(cancion[i].compareTo(cancion_titulo[k]) == 0){
                                System.out.println("\n Envio: "+ lista.get(j).getId() + " " +lista.get(j).getTitulo());
                                canciones_enviar.add(lista.get(j).getId() + " " +lista.get(j).getTitulo());
                                //System.out.println("Si esta en: " + lista.get(j).getTitulo());
                            }
                        }   
                    }
                }
                

                dos.writeUTF(Integer.toString(canciones_enviar.size()));
                dos.flush();
                
                for(int i = 0; i < canciones_enviar.size(); i++) {
                    System.out.println("\n LISTA "+ canciones_enviar.get(i));
                    dos.writeUTF(canciones_enviar.get(i));
                    dos.flush();
                }
                
                res = dis.readUTF();
                System.out.println("Me piden: "+res);
                
                for(int i = 0; i < lista.size(); i++){
                    if(Integer.parseInt(res) == lista.get(i).getId()){
                        ObjectOutputStream oos = new ObjectOutputStream(cl.getOutputStream());
                        oos.writeObject(lista.get(i));
                        System.out.println("\n Envio: "+lista.get(i).getId());
                        oos.flush();
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

