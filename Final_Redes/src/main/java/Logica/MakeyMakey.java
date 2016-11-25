package Logica;



import java.awt.RenderingHints.Key;

import processing.core.PApplet;
import programaciondmi.per.modelo.NotaMusical;
import programaciondmi.per.modelo.exceptions.MalaNotaMusicalException;

public class MakeyMakey {
	PApplet mundo;
	Logica comInstancia;
	int segundos;
	boolean presionado;
	boolean presionadoRE;
	boolean presionadoDO;
	boolean presionadoMI;
	boolean presionadoFA;
	float diferencia=-100;
	float sumar=0.5F;
	short tempo;
	

	public MakeyMakey(PApplet mundo, Logica comLog) {
	this.mundo=mundo;	
	this.comInstancia=comLog;
	loop().start();
	contador().start();
	
	
	}
	
	
	
	public void pintar(){
		//aqui el color del cuadrado
		this.mundo.stroke(100,200,100);
		this.mundo.strokeWeight(3);
		this.mundo.fill(0,0);
		
		mundo.rect(mundo.width/2, mundo.height/2-300, 70, 70);
		
		this.mundo.strokeWeight(10);
		//aqui el color de la linea
		this.mundo.stroke(0,255,0);
		mundo.line(mundo.width/2-100, mundo.height/2-230, mundo.width/2+diferencia, mundo.height/2-230);
		this.mundo.stroke(0,30);
		mundo.line(mundo.width/2-100, mundo.height/2-230, mundo.width/2+100, mundo.height/2-230);
		mundo.textSize(25);
		mundo.fill(0);
		if(presionadoDO){
			mundo.text("DO", 400, 100);
		}else if(presionadoRE){
			mundo.text("RE", 400, 100);
		}else if(presionadoMI){
			mundo.text("MI", 400, 100);
		}else if(presionadoFA){
			mundo.text("FA", 400, 100);
		}
		
		switch (tempo) {
		case 1:
			mundo.text("FUSA", mundo.width/2, mundo.height/2-280);
			
			break;

		case 2:
			mundo.text("CORCHEA", mundo.width/2, mundo.height/2-280);
			
			break;
			
		case 3:
			mundo.text("NEGRA", mundo.width/2, mundo.height/2-280);
			break;
			
			
		case 4:
			mundo.text("BLANCA", mundo.width/2, mundo.height/2-280);
			
			break;
			
		case 5:
			mundo.text("REDONDA", mundo.width/2, mundo.height/2-280);
			
			break;
		}
		
		this.mundo.noStroke();
	}
	
	
	private Thread contador(){
		Thread t= new Thread(new Runnable() {
			short centecimas;
		

			@Override
			public void run() {
				try{
					while(true){
						if(presionado){
						if(centecimas==10){	
						segundos++;
						centecimas=0;
						}
						centecimas++;
						}
						
						
						Thread.sleep(100);
					}
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		return t;
		
	}
	
	private void detectarTempo() throws MalaNotaMusicalException{
		switch (tempo) {
		case 1:
			
			if(presionadoDO==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.DO, NotaMusical.FUSA);
				comInstancia.tcp.enviarObjeto(nota);
			} else if(presionadoRE==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.RE, NotaMusical.FUSA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoMI==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.MI, NotaMusical.FUSA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoFA==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.FA, NotaMusical.FUSA);
				comInstancia.tcp.enviarObjeto(nota);
			}
			tempo=0;
			break;

		case 2:
			
			if(presionadoDO==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.DO, NotaMusical.CORCHEA);
				comInstancia.tcp.enviarObjeto(nota);
			} else if(presionadoRE==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.RE, NotaMusical.CORCHEA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoMI==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.MI, NotaMusical.CORCHEA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoFA==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.FA, NotaMusical.CORCHEA);
				comInstancia.tcp.enviarObjeto(nota);
			}
			tempo=0;
			
			break;
			
		case 3:
			
			if(presionadoDO==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.DO, NotaMusical.NEGRA);
				comInstancia.tcp.enviarObjeto(nota);
			} else if(presionadoRE==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.RE, NotaMusical.NEGRA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoMI==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.MI, NotaMusical.NEGRA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoFA==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.FA, NotaMusical.NEGRA);
				comInstancia.tcp.enviarObjeto(nota);
			}
			tempo=0;
			
			
			break;
			
		case 4:
			
			if(presionadoDO==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.DO, NotaMusical.BLANCA);
				comInstancia.tcp.enviarObjeto(nota);
			} else if(presionadoRE==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.RE, NotaMusical.BLANCA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoMI==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.MI, NotaMusical.BLANCA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoFA==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.FA, NotaMusical.BLANCA);
				comInstancia.tcp.enviarObjeto(nota);
			}
			tempo=0;
			
			
			break;
			
		case 5:
			
			if(presionadoDO==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.DO, NotaMusical.REDONDA);
				comInstancia.tcp.enviarObjeto(nota);
			} else if(presionadoRE==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.RE, NotaMusical.REDONDA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoMI==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.MI, NotaMusical.REDONDA);
				comInstancia.tcp.enviarObjeto(nota);
			}else if(presionadoFA==true){
				NotaMusical nota= new NotaMusical(comInstancia.instrumentoAsignado, NotaMusical.FA, NotaMusical.REDONDA);
				comInstancia.tcp.enviarObjeto(nota);
			}
			tempo=0;
			
			
			break;
		}
	}
	
	private void detectarKey(){
		if(mundo.keyPressed){
		
				if(mundo.keyCode== mundo.RIGHT ||mundo.keyCode== mundo.LEFT || mundo.keyCode== mundo.UP || mundo.keyCode== mundo.DOWN){
					presionado=true;
					if(mundo.keyCode== mundo.RIGHT){
						presionadoRE=true;
			

					}
					if(mundo.keyCode== mundo.UP){
						presionadoDO=true;

					}
					if(mundo.keyCode== mundo.DOWN){
						presionadoMI=true;

					}
					if(mundo.keyCode== mundo.LEFT){
						presionadoFA=true;

					}
				}
			
			//System.out.println("entro");
		}else{
			try {
				detectarTempo();
			} catch (MalaNotaMusicalException e) {
				
				e.printStackTrace();
			}
			presionado=false;
			presionadoDO=false;
			presionadoRE=false;
			presionadoMI=false;
			presionadoFA=false;
			
			segundos=0;
			
		}
	}
	
	private Thread loop(){
		Thread t= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					while(true){
						detectarKey();
						
						if(presionado && tempo<=5){
							if(diferencia<100){
						diferencia+=4F;
							}
						}
						if(segundos==0 && !presionado){
							diferencia=-100;
						}
						
						if(segundos==1){
							segundos=0;
							tempo++;
							System.out.println(tempo);
							diferencia=-100;
						}
						if(tempo>5){
							tempo=0;
						}
						Thread.sleep(17);
					}
					
					
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		return t;
	}
	
	
	
	

}
