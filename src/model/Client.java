/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.Jframe;

/**
 *
 * @author Alex Arias
 */
public class Client implements Runnable {

    private int port = 0;
    private String hostname = "";
    private Socket thesocket = null;
    private PrintWriter out;
    private BufferedReader in;
    private Thread theThread;
    private String MessageSend;
    private Jframe Jframe;
    private LinkedList<Fish> listaLocal = new LinkedList<>();

    public Client(String hostname, String port, Jframe J) {
        this.hostname = hostname;
        this.port = Integer.parseInt(port);
        this.MessageSend = "";
        this.Jframe = J;

    }

    public boolean Open() {
        boolean result = false;
        try {
            this.thesocket = new Socket(hostname, this.port);
            out = new PrintWriter(thesocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(thesocket.getInputStream()));
            this.theThread = new Thread(this);
            this.theThread.start();
            result = true;

        } catch (Exception e) {
            System.out.println("no pudo iniciar");
        }
        return result;
    }

    public void close() throws IOException {
        out.close();
        in.close();
        thesocket.close();
    }

    public void SendClick(int x, int y) {
        this.out.println("<Click>" + x + "&" + y);
    }

    public void RegisterClient(String data) {
        this.out.println("<Register>" + data);
    }

    @Override
    public void run() {
        while (true) {
            String readline = "";
            try {
                readline = this.in.readLine();
                if (!readline.isEmpty()) {
                    System.out.println("Esta es la linea actual->" + readline);
                    if (readline.contains("Add Fish")) {
//                        this.Jframe.getDraw().getListFish().clear();
                        readline = readline.substring(10);
                        String data[] = readline.split("&");
                        //No se si el x y y si los retorna el servidor en este orden
                        int typefish = Integer.parseInt(data[0]);
                        int x = Integer.parseInt(data[1]);
                        int y = Integer.parseInt(data[2]);
                        int dir = Integer.parseInt(data[3]);
//                        System.out.println("esto es x: "+x);

                        //El alto y el ancho no se si se maneja de esta manera o si se debe detectar de la imagen que este seleecionada
                        Fish tmp = new Fish(x, y, 100, 100, "../img/pez" + typefish + "di.png", dir);

                        //Estos condicinales no entiendo como funcionan
                        if (tmp.getDirec() == 1) {
                            if (tmp.getX() + 100 <= 1300) {
                                this.Jframe.getDraw().getListFish().add(tmp);
                                this.Jframe.getDraw().repaint();
                                System.out.println((tmp.getX() + 100));
                            }
                        } else if (tmp.getDirec() != 1) {
                            if (tmp.getX() > -130) {
                                this.Jframe.getDraw().getListFish().add(tmp);
                                this.Jframe.getDraw().repaint();
                                System.out.println((tmp.getX() + 30));
                            }
                        } else {
                            this.Jframe.getDraw().getListFish().clear();
                            this.Jframe.getDraw().revalidate();
                            this.Jframe.getDraw().removeAll();
                            System.out.println("aca entro suerte");
                            break;
                        }
                    }
                    if (readline.contains("RemoveAllFishes")) {
//                        System.out.println("borrar ahora");
//                        System.out.println("Asi llego la lista-> " + this.Jframe.getDraw().getListFish().size());

                        this.Jframe.getDraw().getListFish().clear();
//                        System.out.println("Asi quedo la lista-> " + this.Jframe.getDraw().getListFish().size());
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the thesocket
     */
    public Socket getThesocket() {
        return thesocket;
    }

    /**
     * @param thesocket the thesocket to set
     */
    public void setThesocket(Socket thesocket) {
        this.thesocket = thesocket;
    }

    /**
     * @return the out
     */
    public PrintWriter getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public void setOut(PrintWriter out) {
        this.out = out;
    }

    /**
     * @return the in
     */
    public BufferedReader getIn() {
        return in;
    }

    /**
     * @param in the in to set
     */
    public void setIn(BufferedReader in) {
        this.in = in;
    }

    /**
     * @return the MessageSend
     */
    public String getMessageSend() {
        return MessageSend;
    }

    /**
     * @param MessageSend the MessageSend to set
     */
    public void setMessageSend(String MessageSend) {
        this.MessageSend = MessageSend;
    }
}
