/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package play.sound;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

public class Inicio extends javax.swing.JFrame {
    public int num;  
    public String path = "/home/marce/Desktop/cancion/";
    public String archivo;
    public String Portada;
    public Player player;
    public FileInputStream FIS;
    public BufferedInputStream BIS;
    public Boolean flag;
    public Inicio() {
        String[] lista = {
            "Loco",
            "Loco",
            "Naufrago",
            "Bailar",
            "Turn back",
            "Have you ever seen the rain?",
            "How deep is your love?",
            "Staying alive",
            "Piano man",
            "Piérdeme el respeto",
            "Thunder",
            "Sweet Child O' Mine"
        };
        
        initComponents();
        
        jPanel2.setVisible(false);
        
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = lista;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        
        MouseListener mouseListener;
        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                   int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        System.out.println("Clic en el índice: " + index);
                        jPanel2.setVisible(true);
                        jList1.setSelectedIndex(index);
                        despliegaInfo2(index);
                    }
                   
                }
            }
        };
        jList1.addMouseListener(mouseListener);
        ImageIcon imagen2=      new ImageIcon("src/Imagenes/icono.png");
        ImageIcon btnPlay =     new ImageIcon("src/Imagenes/reproducir.png");
        ImageIcon btnDescarga = new ImageIcon("src/Imagenes/descarga.png");
        Icon icono2=    new ImageIcon(imagen2.getImage().getScaledInstance(icono.getWidth(),icono.getHeight(),Image.SCALE_DEFAULT));
        Icon iPlay=     new ImageIcon(btnPlay.getImage().getScaledInstance(jLabel18.getWidth(),jLabel18.getHeight(),Image.SCALE_DEFAULT));
        Icon iDescarga= new ImageIcon(btnDescarga.getImage().getScaledInstance(jLabel17.getWidth(),jLabel17.getHeight(),Image.SCALE_DEFAULT));
        icono.setIcon(icono2);
        jLabel18.setIcon(iPlay);
        jLabel17.setIcon(iDescarga);
        this.repaint();
        //jList1.setSelectedIndex(5);
        /*ImageIcon portada =     new ImageIcon("src/Portadas/hyestr.jpg");
        ImageIcon btnPlay =     new ImageIcon("src/Imagenes/reproducir.png");
        ImageIcon btnDescarga = new ImageIcon("src/Imagenes/descarga.png");
        ImageIcon imagen2=      new ImageIcon("src/Imagenes/icono.png");
        Icon icono2=    new ImageIcon(imagen2.getImage().getScaledInstance(icono.getWidth(),icono.getHeight(),Image.SCALE_DEFAULT));
        Icon iPortada=  new ImageIcon(portada.getImage().getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(),Image.SCALE_DEFAULT));
        Icon iPlay=     new ImageIcon(btnPlay.getImage().getScaledInstance(jLabel18.getWidth(),jLabel18.getHeight(),Image.SCALE_DEFAULT));
        Icon iDescarga= new ImageIcon(btnDescarga.getImage().getScaledInstance(jLabel17.getWidth(),jLabel17.getHeight(),Image.SCALE_DEFAULT));
        icono.setIcon(icono2);
        jLabel4.setIcon(iPortada);
        jLabel18.setIcon(iPlay);
        jLabel17.setIcon(iDescarga);
        this.repaint();*/
    }
    public void despliegaInfo2(int n){
        try{
            String host="127.0.0.1";
            int pto=7000;
            Socket cl= new Socket(host,pto);
            DataOutputStream dos= new DataOutputStream(cl.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(cl.getInputStream());
            
            dos.writeInt(n);
            dos.flush();
            Thread.sleep(50);
            
            Cancion c = (Cancion)ois.readObject();
            
            ImageIcon portada =     new ImageIcon(c.getPortada());            
            Icon iPortada=  new ImageIcon(portada.getImage().getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(),Image.SCALE_DEFAULT));            
            jLabel4.setIcon(iPortada);
            
            jLabel11.setText(c.getTitulo());//titulo
            jLabel12.setText(c.getArtista());
            jLabel13.setText(c.getAlbum());
            jLabel14.setText(c.getAnio());
            jLabel15.setText(c.getGenero());
            jLabel16.setText(c.getDuracion());
            archivo = c.getArchivo();
            flag = false;
            this.repaint();
            dos.close();
            ois.close();
        }catch(Exception e){
           e.printStackTrace();
        }
    }
    public void despliegaInfo(int n){
        System.out.println("entra al metodo con " + n);
        jLabel11.setText("Agregamos un titulo de verdad");
        ImageIcon portada =     new ImageIcon("src/Portadas/hyestr.jpg");
        ImageIcon btnPlay =     new ImageIcon("src/Imagenes/reproducir.png");
        ImageIcon btnDescarga = new ImageIcon("src/Imagenes/descarga.png");
        ImageIcon imagen2=      new ImageIcon("src/Imagenes/icono.png");
        Icon icono2=    new ImageIcon(imagen2.getImage().getScaledInstance(icono.getWidth(),icono.getHeight(),Image.SCALE_DEFAULT));
        Icon iPortada=  new ImageIcon(portada.getImage().getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(),Image.SCALE_DEFAULT));
        Icon iPlay=     new ImageIcon(btnPlay.getImage().getScaledInstance(jLabel18.getWidth(),jLabel18.getHeight(),Image.SCALE_DEFAULT));
        Icon iDescarga= new ImageIcon(btnDescarga.getImage().getScaledInstance(jLabel17.getWidth(),jLabel17.getHeight(),Image.SCALE_DEFAULT));
        icono.setIcon(icono2);
        jLabel4.setIcon(iPortada);
        jLabel18.setIcon(iPlay);
        jLabel17.setIcon(iDescarga);
        this.repaint();
    }
    public void Stop(){
        if(player != null){
            player.close();
        }
    }
    public void Play(){
        try{
            FIS = new FileInputStream(archivo);
            BIS = new BufferedInputStream(FIS);
            player = new Player(BIS);
        }catch(Exception e){
        }
        new Thread(){
            @Override
            public void run(){
                try{
                    player.play();
                }catch(Exception e){}
            }
        }.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Cancion = new javax.swing.JTextField();
        Buscar = new javax.swing.JButton();
        icono = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Freestyle Script", 0, 50)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(249, 15, 30));
        jLabel1.setText("Play");

        jLabel2.setFont(new java.awt.Font("Harlow Solid Italic", 0, 50)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("'Sound");

        Cancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancionActionPerformed(evt);
            }
        });

        Buscar.setBackground(new java.awt.Color(246, 0, 0));
        Buscar.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        Buscar.setForeground(new java.awt.Color(255, 255, 255));
        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono.png"))); // NOI18N
        icono.setText("jLabel3");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jList1.setBackground(new java.awt.Color(0, 0, 0));
        jList1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.setMaximumSize(new java.awt.Dimension(61, 374));
        jList1.setMinimumSize(new java.awt.Dimension(61, 374));
        jScrollPane1.setViewportView(jList1);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("X");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Título:");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Artista:");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Álbum:");

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Año:");

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Género:");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Duración:");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Have you ever seen the rain?");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Creedence Clearwater Revival");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Pendulum");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("1970");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Rock");

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("02:40 mins.");

        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(12, 12, 12))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(28, 28, 28)
                            .addComponent(jLabel11))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel15))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel13)
                                .addComponent(jLabel12)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel16)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(244, 244, 244))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancion, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(icono, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Buscar))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        try{

            String host="127.0.0.1";
            int pto=9000;
            Socket cl= new Socket(host,pto);
            DataOutputStream dos= new DataOutputStream(cl.getOutputStream());
            DataInputStream dis= new DataInputStream(cl.getInputStream());

            String can= Cancion.getText();
            System.out.println(" "+can);
            dos.writeUTF(can);

            dos.flush();
            Thread.sleep(1);
            
            
            //NO SIRVE ESTO, SOLO ES PRUEBA...
            //dos.writeUTF("3");
            //dos.flush();
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }//GEN-LAST:event_BuscarActionPerformed

    private void CancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancionActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        jPanel2.setVisible(false);
        jList1.clearSelection();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        //Al presionar el label de descarga descargaremos la cancion seleccionada
        try{         
            File f = new File(archivo);
            String nombre = archivo.split("/")[2];
            System.out.println("archivo: " + nombre);
            FileOutputStream fos = new FileOutputStream(path + nombre);
            BufferedOutputStream bos = new BufferedOutputStream(fos);//archivo externo
            
            FileInputStream fis2 = new FileInputStream(archivo);
            BufferedInputStream bis2 = new BufferedInputStream(fis2);//archivo interno
            
            int n=0, enviados=0, tam = (int)f.length();
            byte [] buf = new byte[1500];
            System.out.println("tamano = " + tam);
            while(enviados < tam){
                n = bis2.read(buf);
                
                bos.write(buf, 0, n);
                bos.flush();
                enviados = enviados + n;
                System.out.print(enviados + " de " + tam + "\r");
            }
            System.out.println("");
            System.out.println("File downloaded!");
            fos.close();
            bos.close();
            fis2.close();
            bis2.close();
        }catch(Exception e){
        }
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        // Al presionar el label reproduciremos la cancion, si se presiona de nuevo se detendra
        if(!flag){
            Play();
            flag = true;
        }
        else{
            Stop();
            flag = false;
        }
        
    }//GEN-LAST:event_jLabel18MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JTextField Cancion;
    private javax.swing.JLabel icono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
