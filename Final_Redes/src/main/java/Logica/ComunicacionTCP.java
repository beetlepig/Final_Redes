package Logica;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

import programaciondmi.per.modelo.Instrumento;


public class ComunicacionTCP extends Observable {
	private Socket s;
	private boolean conectado;

	public ComunicacionTCP() {
		try {
			s = new Socket(InetAddress.getByName("localhost"), 5000);
			conectado = true;
			// enviar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public Runnable hilo () {
		Runnable r= new Runnable() {
			
			public void run() {
				while (conectado) {
					// recibir();
				Object notificar=recibirObjeto();
				if(notificar!= null){
					setChanged();
					notifyObservers(notificar);
					clearChanged();
				}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
	
		return r;
	}

	private Object recibirObjeto() {
		ObjectInputStream entrada = null;
		Object mensaje=null;
		try {
			entrada = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
			mensaje = entrada.readObject();
			System.out.println("Se recibio: " + mensaje);

			if (mensaje instanceof Instrumento) {
				Instrumento i = (Instrumento) mensaje;

				// Crear notas musicales y enviarlas
				
/*
				try {
					NotaMusical n = new NotaMusical(i, NotaMusical.DO, NotaMusical.BLANCA);
					enviarObjeto(n);
				} catch (MalaNotaMusicalException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			
			}
			
		} catch (IOException e) {
			try {
				if (entrada != null) {
					entrada.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensaje;
	}

	public void enviarObjeto(Object o) {
		ObjectOutputStream salida = null;

		try {
			salida = new ObjectOutputStream(s.getOutputStream());
			salida.writeObject(o);
			System.out.println("Se envió el mensaje: " + o);
		} catch (IOException e) {
			try {
				if (salida != null) {
					salida.close();
				}
				s.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			s = null;
			conectado = false;
			e.printStackTrace();
		}
	}
}
