package Logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import ddf.minim.Minim;
import processing.core.PApplet;
import programaciondmi.per.modelo.Instrumento;
import programaciondmi.per.modelo.NotaMusical;

public class Logica implements Observer, Runnable{
PApplet app;

LinkedList<NotaMusical> notasEntrantes;
LinkedList<NotaMusical> percucion;
LinkedList<NotaMusical> urbano;
LinkedList<NotaMusical> electronico;


Instrumento instrumentoAsignado;

NotaMusical tempPercusion;
NotaMusical tempUrbano;
NotaMusical tempElectronico;

Circulo percucionClase;
Electronico electronicoClase;
Urbano urbanoClse;

ComunicacionTCP tcp;
Thread hilito;
Thread hiloLogica;
	public Logica(PApplet app) {
		notasEntrantes= new LinkedList<NotaMusical>();
		percucion= new LinkedList<NotaMusical>();
		urbano= new LinkedList<NotaMusical>();
		electronico=new LinkedList<NotaMusical>();
		
	tcp= new ComunicacionTCP();
	hilito= new Thread(tcp.hilo());
	hilito.start();
	tcp.addObserver(this);
		this.app=app;
	//	Comunicacion.getInstance();
	//	Comunicacion.getInstance().addObserver(this);
	//	new Thread(Comunicacion.getInstance()).start();;
		
		percucionClase= new Circulo(this);
		urbanoClse= new Urbano(this);
		electronicoClase= new Electronico(this);
		
		hiloLogica=new Thread(this);
		hiloLogica.start();
	}
	
	public void pintar(){
		percucionClase.pintar();
		urbanoClse.pintar();
		electronicoClase.pintar();

	}
	
	private void controlarTempos(){
	
		if(!percucion.isEmpty()){
		if(!percucionClase.song.isPlaying() && (tempPercusion!=percucion.get(0) || tempPercusion==null)){
			
				NotaMusical not = percucion.get(0);
				tempPercusion= not;
				if(not.getNota()== NotaMusical.DO){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionDO.wav").getPath(), 2048);
				} else if(not.getNota()== NotaMusical.RE){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionRE.mp3").getPath(), 2048);
				} else if(not.getNota()== NotaMusical.MI){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionMI.mp3").getPath(), 2048);
				} else if(not.getNota()== NotaMusical.FA){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionFA.mp3").getPath(), 2048);
				} else if(not.getNota()== NotaMusical.SOL){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionSOL.mp3").getPath(), 2048);
				} else if(not.getNota()== NotaMusical.LA){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionLA.mp3").getPath(), 2048);
				} else if(not.getNota()== NotaMusical.SI){
					percucionClase.song= percucionClase.minim.loadFile(percucionClase.classLoader.getResource("sounds/Percusion/percusionSI.mp3").getPath(), 2048);
				}
				if(not.getDuracion()==NotaMusical.NEGRA){
					percucionClase.segundosTempo=6;
				}else if(not.getDuracion()==NotaMusical.BLANCA){
					percucionClase.segundosTempo=12;
				}else if(not.getDuracion()==NotaMusical.REDONDA){
					percucionClase.segundosTempo=24;
				}else if(not.getDuracion()==NotaMusical.CORCHEA){
					percucionClase.segundosTempo=4;
				}else if(not.getDuracion()== NotaMusical.SEMICORCHEA){
					percucionClase.segundosTempo=3;
				}else if(not.getDuracion()== NotaMusical.FUSA){
					percucionClase.segundosTempo=2;
				}else if(not.getDuracion()== NotaMusical.SEMIFUSA){
					percucionClase.segundosTempo=1;
				}
			
			percucionClase.puedeTocar=true;
			percucionClase.puedereiniciar=true;
		}
		}
		
		
		if(!urbano.isEmpty()){
			if(!urbanoClse.song.isPlaying() && (tempUrbano!=urbano.get(0) || tempUrbano==null)){
				
				NotaMusical not = urbano.get(0);
				tempPercusion= not;
				if(not.getNota()== NotaMusical.DO){
					urbanoClse.cargarSonido("urbanoDO.mp3");
				} else if(not.getNota()== NotaMusical.RE){
					urbanoClse.cargarSonido("urbanoRE.mp3");
				} else if(not.getNota()== NotaMusical.MI){
					urbanoClse.cargarSonido("urbanoMI.mp3");
				} else if(not.getNota()== NotaMusical.FA){
					urbanoClse.cargarSonido("urbanoFA.mp3");
				} else if(not.getNota()== NotaMusical.SOL){
					urbanoClse.cargarSonido("urbanoSOL.mp3");
				} else if(not.getNota()== NotaMusical.LA){
					urbanoClse.cargarSonido("urbanoLA.mp3");
				} else if(not.getNota()== NotaMusical.SI){
					urbanoClse.cargarSonido("urbanoSI.wav");
				}
				
				if(not.getDuracion()==NotaMusical.NEGRA){
					urbanoClse.segundosTempo=6;
				}else if(not.getDuracion()==NotaMusical.BLANCA){
					urbanoClse.segundosTempo=12;
				}else if(not.getDuracion()==NotaMusical.REDONDA){
					urbanoClse.segundosTempo=24;
				}else if(not.getDuracion()==NotaMusical.CORCHEA){
					urbanoClse.segundosTempo=4;
				}else if(not.getDuracion()== NotaMusical.SEMICORCHEA){
					urbanoClse.segundosTempo=3;
				}else if(not.getDuracion()== NotaMusical.FUSA){
					urbanoClse.segundosTempo=2;
				}else if(not.getDuracion()== NotaMusical.SEMIFUSA){
					urbanoClse.segundosTempo=1;
				}
			
				urbanoClse.puedeTocar=true;
				urbanoClse.puedereiniciar=true;
		}
		}
		
		
		
		if(!electronico.isEmpty() && (tempElectronico!=electronico.get(0) || tempElectronico==null)){
			if(!electronicoClase.song.isPlaying()){
				
				NotaMusical not = electronico.get(0);
				tempElectronico= not;
				if(not.getNota()== NotaMusical.DO){
					electronicoClase.cargarSonido("electricoDO.wav");
				} else if(not.getNota()== NotaMusical.RE){
					electronicoClase.cargarSonido("electricoRE.mp3");
				} else if(not.getNota()== NotaMusical.MI){
					electronicoClase.cargarSonido("electricoMI.mp3");
				} else if(not.getNota()== NotaMusical.FA){
					electronicoClase.cargarSonido("electricoFA.mp3");
				} else if(not.getNota()== NotaMusical.SOL){
					electronicoClase.cargarSonido("electricoSOL.mp3");
				} else if(not.getNota()== NotaMusical.LA){
					electronicoClase.cargarSonido("electricoLA.mp3");
				} else if(not.getNota()== NotaMusical.SI){
					electronicoClase.cargarSonido("electricoSI.wav");
				}
				
				if(not.getDuracion()==NotaMusical.NEGRA){
					electronicoClase.segundosTempo=6;
				}else if(not.getDuracion()==NotaMusical.BLANCA){
					electronicoClase.segundosTempo=12;
				}else if(not.getDuracion()==NotaMusical.REDONDA){
					electronicoClase.segundosTempo=24;
				}else if(not.getDuracion()==NotaMusical.CORCHEA){
					electronicoClase.segundosTempo=4;
				}else if(not.getDuracion()== NotaMusical.SEMICORCHEA){
					electronicoClase.segundosTempo=3;
				}else if(not.getDuracion()== NotaMusical.FUSA){
					electronicoClase.segundosTempo=2;
				}else if(not.getDuracion()== NotaMusical.SEMIFUSA){
					electronicoClase.segundosTempo=1;
				}
			
				electronicoClase.puedeTocar=true;
				electronicoClase.puedereiniciar=true;
		}
		}
		
	//	System.out.println("elementos en cola:"+ percucion.size() );
	

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
		    	  
		      }else if(ins.getTipo()==Instrumento.TIPO_URBANO){
		    	  urbano.add(notita);
		    	  notasEntrantes.remove(notita);
		    	  System.out.println();
		    	  System.out.println("Se agrego a la cola de Urbano");
		    	  System.out.println("Cantidad de elementos en cola de Urbano:"+urbano.size());
		      }else if(ins.getTipo()== Instrumento.TIPO_ELECTRONICO){
		    	  electronico.add(notita);
		    	  notasEntrantes.remove(notita);
		    	  System.out.println();
		    	  System.out.println("Se agrego a la cola de Electronico");
		    	  System.out.println("Cantidad de elementos en cola de Electronico:"+electronico.size());
		      }
			
		}
	}



	public void update(Observable arg0, Object arg1) {
		System.out.println();
		System.out.println("llego objeto update:"+ arg1);
		if(arg1 instanceof NotaMusical){
			notasEntrantes.add((NotaMusical) arg1);
			comprobarTipoDeInstrumento();
			
		}else if(arg1 instanceof Instrumento){
		 instrumentoAsignado= (Instrumento) arg1;
		}
		
	
		
	}

	public void run() {
		try{
			while(true){
				controlarTempos();
				if(percucionClase.sonarNota()){
					if(!percucion.isEmpty()){
					percucion.remove(percucion.get(0));
					}
				}
				
				if(urbanoClse.sonarNota()){
					if(!urbano.isEmpty()){
					urbano.remove(urbano.get(0));
					}
				}
				
				if(electronicoClase.sonarNota()){
					if(!electronico.isEmpty()){
					electronico.remove(electronico.get(0));
					}
				}
			//	comprobarTipoDeInstrumento();
				Thread.sleep(17);
			}
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

}
