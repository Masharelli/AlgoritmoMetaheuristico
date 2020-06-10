import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class GeneradorGrafos {
	
	public void grafoLineal(int tamaño) {
		
		try {
            PrintWriter writer = new PrintWriter("lineal" + tamaño, "UTF-8");
            
    		 writer.println(tamaño + " "+tamaño +" " +(tamaño-1));
    		
    		for(int i = 0; i<tamaño-1; i++) {
    			writer.println(i +" "+ (i+1));
    		}
    		 writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void grafoCircular(int tamaño) {
		
		try {
            PrintWriter writer = new PrintWriter("circular" + tamaño, "UTF-8");
            
    		 writer.println(tamaño + " "+tamaño +" " + tamaño);
    		
    		for(int i = 0; i<tamaño-1; i++) {
    			writer.println(i +" "+ (i+1));
    		}
    		 writer.println(tamaño + " " + 0);
    		 writer.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void grafoCaterpillar(int tamaño) {
		
		try {
            PrintWriter writer = new PrintWriter("caterpillar" + tamaño, "UTF-8");
            
    		 writer.println(tamaño + " "+tamaño +" " + (tamaño-1));
    		
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
	
	public void grafoRandom(int tamaño) {
	
		try {
            
    		LinkedList<String> uniones = new LinkedList<String>();
    		int aristas = 0;
    		 for(int i=1;i<tamaño+1;i++) {
    			 for(int j=1+i;j<tamaño+1;j++) {
    				 int union = (int)(Math.random()*tamaño)+1;
    				 System.out.println(union);
    				 if(union<tamaño/4) {
    					 aristas++;
    					 uniones.add(i + " " + j);
    				 }
    			 }
    		 }
    		 PrintWriter writer = new PrintWriter("random" + tamaño, "UTF-8");
    		 writer.println(tamaño + " "+tamaño +" "+ aristas);
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
