package Logica;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {
	private static Comunicacion referencia;
	private Socket socket;
	private String ip = "172.30.177.93";
	private short puerto = 5000;
	private boolean corriendo;
	private boolean conectando;
	
    private Comunicacion() {
        socket = null;
        corriendo = true;
    }
    
    public static Comunicacion getInstance() {
        if(referencia == null){
            referencia =  new Comunicacion();
            Thread t =  new Thread(referencia);
            t.start();
        }
        return referencia;
    }
    
    private boolean conectar() {
        try {
            InetAddress dirServidor = InetAddress.getByName(ip);
            socket = new Socket(dirServidor, puerto);
            System.out.println("[ CONECTADO CON: " + socket.toString() + " ]");
            setChanged();
            notifyObservers("conectado");
            clearChanged();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("[ SERVIDOR DESCONOCIDO ]");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ ERROR AL ESTABLECER LA CONEXIÓN ]");
            
            return false;
        }
        return true;
    }
    
    private Object recibir() throws IOException, ClassNotFoundException{
        System.out.println("entroRecibir");
        ObjectInputStream dis = new ObjectInputStream(socket.getInputStream());
        Object in = dis.readObject();
        return in;
    }

	public void run() {
		 
	        while (corriendo) {
	            try {
	                if (conectando) {
	                	
	                    conectando = !conectar();
	                    
	                } else {
	                    if (socket != null) {
	                        recibir();
	                        //notificar
	                    }
	                }
	                Thread.sleep(33);
	            } catch (SocketTimeoutException e) {
	                //Log.d(TAG, "[ SE PERDIÓ LA CONEXIÓN CON EL SERVIDOR ]");
	                //corriendo = false;
	                 e.printStackTrace();
	            } catch (IOException e) {
	                System.out.println("[ SE PERDIÓ LA CONEXIÓN CON EL SERVIDOR ]");
	                e.printStackTrace();
	                //notifyObservers("no_conectado");
	                //clearChanged();
	                //corriendo = false;
	                
	            } catch (InterruptedException e) {
	                 e.printStackTrace();
	               //setChanged();
	                System.out.println("[ INTERRUPCIÓN ]");
	            } catch (ClassNotFoundException e){
	                e.printStackTrace();
	            }
	        }

	        try {
	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	            System.out.println("[ ERROR CERRANDO EL SOCKET ]");
	        } finally {
	            socket = null;
	        }
		
	}

}
