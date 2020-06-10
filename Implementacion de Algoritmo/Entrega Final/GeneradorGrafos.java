import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class GeneradorGrafos {
	
	public void grafoLineal(int tama�o) {
		
		try {
            PrintWriter writer = new PrintWriter("lineal" + tama�o, "UTF-8");
            
    		 writer.println(tama�o + " "+tama�o +" " +(tama�o-1));
    		
    		for(int i = 0; i<tama�o-1; i++) {
    			writer.println(i +" "+ (i+1));
    		}
    		 writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void grafoCircular(int tama�o) {
		
		try {
            PrintWriter writer = new PrintWriter("circular" + tama�o, "UTF-8");
            
    		 writer.println(tama�o + " "+tama�o +" " + tama�o);
    		
    		for(int i = 0; i<tama�o-1; i++) {
    			writer.println(i +" "+ (i+1));
    		}
    		 writer.println(tama�o + " " + 0);
    		 writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void grafoCaterpillar(int tama�o) {
		
		try {
            PrintWriter writer = new PrintWriter("caterpillar" + tama�o, "UTF-8");
            
    		 writer.println(tama�o + " "+tama�o +" " + (tama�o-1));
    		
    		int i=0;
    		int h = 1;
    		int g = 2;
    		while(i<500) {
    			writer.println(i+" "+ h);
    			writer.println(i+" "+ g);
    			writer.println(i+" "+ (i+=3));
    			h+=3;
    			g+=3;
    			
    		}
    		 writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void grafoRandom(int tama�o) {
	
		try {
            
    		LinkedList<String> uniones = new LinkedList<String>();
    		int aristas = 0;
    		 for(int i=1;i<tama�o+1;i++) {
    			 for(int j=1+i;j<tama�o+1;j++) {
    				 int union = (int)(Math.random()*tama�o)+1;
    				 System.out.println(union);
    				 if(union<tama�o/4) {
    					 aristas++;
    					 uniones.add(i + " " + j);
    				 }
    			 }
    		 }
    		 PrintWriter writer = new PrintWriter("random" + tama�o, "UTF-8");
    		 writer.println(tama�o + " "+tama�o +" "+ aristas);
    		 while(!uniones.isEmpty()) {
    			 writer.println(uniones.pollFirst());
    		 }
    		 
    		 writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	public static void main(String[] args) {
		GeneradorGrafos g = new GeneradorGrafos();
		
		g.grafoRandom(104);
	}

}
