import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	
	
	public  int[] SLP(int V,int E,String [] aristas) {
		for(int i=0;i<this.etiquetas.length;i++) {
			this.etiquetas[i] = i+1;
		}
		
		int[][] uniones = GenerarUniones(aristas);
		this.etiquetas=busquedaLocal(etiquetas,uniones,minVal);
		
		for(int i = 0;i<V;i++) {
			int [] permutacion = permutarEtiquetas(etiquetas);
			permutacion=busquedaLocal(etiquetas,uniones,minVal);
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
	
	public int[] busquedaLocal(int[] etiquetas, int[][] uniones, int minVal) {
		int bestI=0;
		int bestJ=0;
		int minimoLocal=minVal;
		Integer[] orden=new Integer[etiquetas.length];
		for (int x=0; x<etiquetas.length; x++) {
			orden[x]=etiquetas[x];
		}
		for(int k=0; k<150; k++) {
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
					}
					temp=etiquetas[u];
					etiquetas[u]=etiquetas[j];
					etiquetas[j]=temp;
				}
			}
		}
		int temp=etiquetas[bestI];
		etiquetas[bestI]=etiquetas[bestJ];
		etiquetas[bestJ]=temp;
		minimoLocal=evaluar(etiquetas,uniones);
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
	
	public static int [] permutarEtiquetas(int [] arr) {
		int temp;
		temp = arr[0];
		for(int i=0;i<arr.length-1;i++) {
			arr[i] = arr[i+1];
		}
		arr[arr.length-1] = temp;
		
		return arr;
	}
	
	
	public static void main(String[] args) {
		String path= args[0];
		
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
	       s.SLP(V,E,aristas);
		}catch(IOException E){
			E.printStackTrace();
		}
		
		
	}
}
