import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.lang.*;


public class SLP2 {
	public int minVal;
	public int [] etiquetas;
	
	public SLP2(int V) {
		this.minVal=V*V;
		this.etiquetas=new int[V];
	}
	public static int min(int a,int b) {
		if(a<b) {
			return a;
		}else {
			return b;
		}
	}
	
	
	public  int[] SLP(int V,int E,String [] aristas,int iteraciones) {
		for(int i=0;i<this.etiquetas.length;i++) {
			this.etiquetas[i] = i+1;
		}
		
		int[][] uniones = GenerarUniones(aristas);
		this.etiquetas=busquedaLocal(etiquetas,uniones,minVal,iteraciones);
		
		for(int i = 0;i<V;i++) {
			int u=(int)(Math.random()*etiquetas.length)+1;
			int v=(int)(Math.random()*etiquetas.length)+1;
			int [] permutacion;
			if(u>v) {
				permutacion = permutarEtiquetas(etiquetas, v, u);
			}else {
				permutacion = permutarEtiquetas(etiquetas, u, v);
			}
			permutacion=busquedaLocal(etiquetas,uniones,minVal,iteraciones);
			int evalPermutacion=evaluar(permutacion,uniones);
			if(evalPermutacion< this.minVal) {
				this.minVal=evalPermutacion;
				for (int x=0; x<etiquetas.length; x++) {
					this.etiquetas[x]=permutacion[x];
				}
			}
			//System.out.println(this.minVal);
			
		}
		//System.out.println(k);
		
		System.out.println("El valor mínimo aproximado es: " + this.minVal);
		System.out.println();
		for(int i = 0;i<V;i++ ) {
			System.out.println("La mejor etiqueta para " + (i+1) + " es " + this.etiquetas[i]);
		}
		
		return etiquetas;
	}
	
	public static int[][] GenerarUniones(String[] aristas) {
		
		int[][] uniones = new int[aristas.length][2];
		for(int i = 0; i<aristas.length;i++) {
			String[] temp = aristas[i].split(" ");
			uniones[i][0]= Integer.parseInt(temp[0]);
			uniones[i][1]= Integer.parseInt(temp[1]);
			//System.out.println(uniones[i][0] + " "+ uniones[i][1]);
		}
		
		return uniones;
	}
	
	public int[] busquedaLocal(int[] etiquetas, int[][] uniones, int minVal,int iteraciones) {
		int bestI=0;
		int bestJ=0;
		int minimoLocal=minVal;
		boolean mejora=false;
		Integer[] orden=new Integer[etiquetas.length];
		for (int x=0; x<etiquetas.length; x++) {
			orden[x]=etiquetas[x];
		}
		for(int k=0; k<iteraciones; k++) {
			List<Integer> listaOrden= Arrays.asList(orden);
			Collections.shuffle(listaOrden);
			listaOrden.toArray(orden);
			for (int i=0; i<etiquetas.length-1; i++) {
				int u=orden[i];
				for (int j=u+1; j<etiquetas.length; j++) {
					int temp=etiquetas[u];
					etiquetas[u]=etiquetas[j];
					etiquetas[j]=temp;
				    int suma=evaluar(etiquetas,uniones);
					if(minimoLocal>suma) {
						minimoLocal = suma;
						bestI=u;
						bestJ=j;
						mejora=true;
					}
					temp=etiquetas[u];
					etiquetas[u]=etiquetas[j];
					etiquetas[j]=temp;
				}
			}
		}
		if(mejora) {
			int temp=etiquetas[bestI];
			etiquetas[bestI]=etiquetas[bestJ];
			etiquetas[bestJ]=temp;
			minimoLocal=evaluar(etiquetas,uniones);
		}
		return etiquetas;
	}
	
	public static int evaluar(int[] etiquetas, int[][] uniones) {
		int sum=0;
		for(int k = 0; k<uniones.length;k++) {
			int a = etiquetas[uniones[k][0]-1];
			int b = etiquetas[uniones[k][1]-1];
			sum = sum + min(a,b);
		}
		return sum;
	}
	
	public static int [] permutarEtiquetas(int [] arr, int u, int v) {
		int temp;
		temp = arr[u];
		for(int i=u;i<v-1;i++) {
			arr[i] = arr[i+1];
		}
		arr[v-1] = temp;
		
		return arr;
	}
	
	
	public static void main(String[] args) {
		String path= args[0];
		int iteraciones=Integer.parseInt(args[1]);
		
		BufferedReader objReader = null; 
		try {
			String strCurrentLine;   
	        objReader = new BufferedReader(new FileReader(path));   
	        String[] valoresIniciales = objReader.readLine().split(" ");
	        int V= Integer.parseInt(valoresIniciales[1]);
	        int E = Integer.parseInt(valoresIniciales[2]);
	        String[] aristas = new String[E];
	        int i = 0;
	        while ((strCurrentLine = objReader.readLine()) != null && i<E) {
	        	aristas[i] = strCurrentLine;
	        	//System.out.println(aristas[i]);
	        	i++;
	        }  
	        
	       // System.out.println(V + " "+ E);
	        SLP2 s= new SLP2(V); 
	        long start = System.currentTimeMillis();
	       
	       s.SLP(V,E,aristas,iteraciones);
	       System.out.print("Current Time in milliseconds = ");
	       long end = System.currentTimeMillis();
			 float sec = (end-start)/1000F;
			 System.out.println(sec);
		}catch(IOException E){
			E.printStackTrace();
		}
	
		 
	   
	

		
	}
		
}
