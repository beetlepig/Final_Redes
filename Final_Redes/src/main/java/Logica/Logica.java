package Logica;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;

public class Logica implements Observer{
PApplet app;


	public Logica(PApplet app) {
		this.app=app;
		Comunicacion.getInstance();
		Comunicacion.getInstance().addObserver(this);
	}
	
	public void pintar(){
		
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
