package Logica;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import programaciondmi.per.modelo.Instrumento;
import programaciondmi.per.modelo.NotaMusical;

public class Logica implements Observer, Runnable{
PApplet app;

ArrayList<NotaMusical> notasEntrantes;
ArrayList<NotaMusical> percucion;
Circulo percucionClase;
ComunicacionTCP tcp;
Thread hilito;
Thread hiloLogica;
	public Logica(PApplet app) {
		notasEntrantes= new ArrayList<NotaMusical>();
		percucion= new ArrayList<NotaMusical>();
	tcp= new ComunicacionTCP();
	hilito= new Thread(tcp.hilo());
	hilito.start();
	tcp.addObserver(this);
		this.app=app;
	//	Comunicacion.getInstance();
	//	Comunicacion.getInstance().addObserver(this);
	//	new Thread(Comunicacion.getInstance()).start();;
		
		percucionClase= new Circulo(this);
		hiloLogica=new Thread(this);
		hiloLogica.start();
	}
	
	public void pintar(){
		percucionClase.pintar();
		percucionClase.sonarNota();
	}
	
	private void controlarTempos(){
		
	}
	
private void comprobarTipoDeInstrumento(){
		
		for (int i = 0; i < notasEntrantes.size(); i++) {
			NotaMusical notita = notasEntrantes.get(i);
			 Instrumento ins=    notita.getInstrumento();
		      if(ins.getTipo()==Instrumento.TIPO_PERCUSION){
		    	  percucion.add(notita);
		    	  notasEntrantes.remove(notita);
		    	  System.out.println();
		    	  System.out.println("Se agrego a la cola de percusion");
		    	  System.out.println("Cantidad de elementos en cola de percusion:"+percucion.size());
		      }
			
		}
	}



	public void update(Observable arg0, Object arg1) {
		
		if(arg1 instanceof NotaMusical){
			notasEntrantes.add((NotaMusical) arg1);
			comprobarTipoDeInstrumento();
			
		}
		
		System.out.println();
		System.out.println("llego objeto update:"+ arg1);
		
	}

	public void run() {
		try{
			while(true){
				controlarTempos();
			//	comprobarTipoDeInstrumento();
				Thread.sleep(50);
			}
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

}
