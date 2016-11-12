package Logica;
import processing.core.PApplet;

public class MainApp extends PApplet {
static Logica log;
	static public void main(String[] args) {
		  PApplet.main(MainApp.class.getName());
		}
	
	public void settings(){
		size(1200,700,P3D);
	}
	
	public void setup(){
		log= new Logica(this);
		
	}
	
	public void draw(){
		background(255);
		log.pintar();
	}
	
	
	

}
