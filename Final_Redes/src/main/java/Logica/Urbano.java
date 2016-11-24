package Logica;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import processing.core.PApplet;

public class Urbano implements Runnable{
	AudioPlayer song;
	short centecimas=0;
	BeatDetect beat;
	Logica mundo;
	Minim minim;
	float eRadius;
	//tempo provicional
	short segundosTempo=10;
	ClassLoader classLoader;
	boolean puedeTocar=false;
	boolean puedereiniciar=true;
	public Urbano(Logica mundo) {
		
		this.mundo=mundo;
		minim= new Minim(this.mundo.app);
		
		classLoader = getClass().getClassLoader();
		
		song = minim.loadFile(classLoader.getResource("sounds/Urbano/urbanoSOL.mp3").getPath(), 2048);
		

		
		beat= new BeatDetect();
		new Thread(this).start();
		
	}
	
	public void cargarSonido(String filename){
		song = minim.loadFile(classLoader.getResource("sounds/Urbano/" + filename).getPath(), 2048);
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
	
	public void pintar(){
		beat.detect(song.mix);
		
		  float a = PApplet.map(eRadius, 20, 80, 60, 255);
		  mundo.app.fill(60, 255, 0, a);
		  if ( beat.isOnset() ) eRadius = 80;
		  mundo.app.ellipse(1100, 600, eRadius, eRadius);
		  eRadius *= 0.95;
		  if ( eRadius < 20 ) eRadius = 20;
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

