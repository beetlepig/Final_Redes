package Logica;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;
import processing.core.PVector;

public class Electronico implements Runnable{
	AudioPlayer song;
	short centecimas=0;
	BeatDetect beat;
	Logica mundo;
	Minim minim;
	PVector posicion;
	PVector velocidad;
	PVector aceleracion;
	boolean disponibleParaSalto=true;;
	//tempo provicional
	short segundosTempo=10;
	ClassLoader classLoader;
	boolean puedeTocar=false;
	boolean puedereiniciar=true;
	public Electronico(Logica mundo) {
		this.mundo=mundo;
		minim= new Minim(this.mundo.app);
		
		classLoader = getClass().getClassLoader();
		
		song = minim.loadFile(classLoader.getResource("sounds/Electronico/electricoSOL.mp3").getPath(), 2048);
		

		
		posicion= new PVector(300,670);
		velocidad= new PVector(0,0);
		aceleracion= new PVector(0,0);
		
		
		beat= new BeatDetect();
		new Thread(this).start();
		
	}
	
	public void cargarSonido(String filename){
		song = minim.loadFile(classLoader.getResource("sounds/Electronico/" + filename).getPath(), 2048);
	}
	
	public boolean sonarNota(){
//		System.out.println(centecimas);
		//	System.out.println(song.isPlaying());
			if(!song.isPlaying() && puedeTocar){
				if(puedereiniciar){
				
				song.play();
				
				puedereiniciar=false;
				}
				
			}
			
			
			
			
			if(centecimas==segundosTempo){
				song.rewind();
				puedeTocar=false;
				song.pause(); 
			
			
				
				centecimas=0;
				
			
				return true;
			}
			return false;
		}
	
	
	
	public void iniciarPlay(){
		song.play();
	}
	

	
	private void animacionSalto(){
		new Thread(new Runnable() {
			boolean termino=false;
			boolean llego=false;
			float magTempo=0;
			float magTempoFix=0;
			
			@Override
			public void run() {
				try{
					disponibleParaSalto=false;
				magTempo=	PApplet.map(segundosTempo, 1, 24, 2.3F, 0.1F);
				magTempoFix= PApplet.map(magTempo, 2.5F, 0.1F, 0.4F, 0.007F);
				while(!termino){
					
					
					if(!llego){
					   aceleracion.set(0,-10);
					   aceleracion.setMag(magTempo+magTempoFix);
					   velocidad.add(aceleracion);
					   posicion.add(velocidad);
					  
					   if(posicion.y<=600){
						   llego=true;
					   }
					
					}else{
						aceleracion.set(0,10);
						aceleracion.setMag(magTempo-magTempoFix);
						velocidad.add(aceleracion);
						posicion.add(velocidad);
						if(posicion.y>=670){
							aceleracion.set(0,0);
							velocidad.set(0,0);
							termino=true;
							disponibleParaSalto=true;
							
						}
					}
					
					
					Thread.sleep(17);
				}
				
				}catch (InterruptedException e) {
					// TODO: handle exception
				}
				
			}
		}).start();
		
	}
	
	public void pintar(){
		beat.detect(song.mix);
		
		  mundo.app.fill(60, 255, 100);
		  if ( beat.isOnset() ){
			  if(disponibleParaSalto){
			 animacionSalto();
			  }
		  }
		  mundo.app.ellipse(posicion.x, posicion.y, 20, 20);
		  
	}

	public void run() {
		try{
			while(true){
				if(puedeTocar){
				centecimas++;
				}
				
				Thread.sleep(100);
			}
			
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	


}
