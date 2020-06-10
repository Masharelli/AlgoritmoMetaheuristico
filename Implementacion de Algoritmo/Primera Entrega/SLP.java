import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SLP {

	int min(int a,int b) {
		if(a<b) {
			return a;
		}else {
			return b;
		}
	}
	
	
	int[] SLP(int V,int E,String [] aristas) {
		int minVal = V*V;
		
		int[] etiquetas = new int[V];
		for(int i=0;i<etiquetas.length;i++) {
			etiquetas[i] = i+1;
		}
		
		int[][] uniones = GenerarUniones(aristas);
		int [] mejoresEtiquetas = new int[V];
		for(int i = 0;i<V;i++) {
			
			int sum = 0;
			
			for(int j = 0; j<uniones.length;j++) {
				int a = etiquetas[uniones[j][0]-1];
				int b = etiquetas[uniones[j][1]-1];
				sum = sum + min(a,b);
			}
			if(minVal>sum) {
				minVal = sum;
				for(int x =0;x<etiquetas.length;x++) {
					mejoresEtiquetas[x]=etiquetas[x];
				}
				
			}
			
			etiquetas = permutarEtiquetas(etiquetas);
			
		}
		//System.out.println(k);
		
		System.out.println("El valor mínimo aproximado es: " + minVal);
		System.out.println();
		for(int i = 0;i<V;i++ ) {
			System.out.println("La mejor etiqueta para " + (i+1) + " es " + mejoresEtiquetas[i]);
		}
		
		return mejoresEtiquetas;
	}
	
	int[][] GenerarUniones(String[] aristas) {
		
		int[][] uniones = new int[aristas.length][2];
		for(int i = 0; i<aristas.length;i++) {
			String[] temp = aristas[i].split(" ");
			uniones[i][0]= Integer.parseInt(temp[0]);
			uniones[i][1]= Integer.parseInt(temp[1]);
			//System.out.println(uniones[i][0] + " "+ uniones[i][1]);
		}
		
		return uniones;
	}
	
	
	
	
	int [] permutarEtiquetas(int [] arr) {
		int temp;
		temp = arr[0];
		for(int i=0;i<arr.length-1;i++) {
			arr[i] = arr[i+1];
		}
		arr[arr.length-1] = temp;
		
		return arr;
	}
	
	
	public static void main(String[] args) {
		SLP s = new SLP();
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
	        
	       s.SLP(V,E,aristas);
		}catch(IOException E){
			E.printStackTrace();
		}
		
		
	}
}
