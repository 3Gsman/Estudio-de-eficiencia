import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Alg {

    //ORDENACIONES:
	
    //MERGE SORT:
	
    public static void mergeSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergeSort(numbers, low, middle);
            mergeSort(numbers, middle + 1, high);
            merge(numbers, low, middle, high);
        }
    }

    private static void merge(int[] numbers, int low, int middle, int high) {
        int l = numbers.length;
        int[] helper = new int[l];
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
    }

    //QUICK SORT:
    
    public static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int posicionPivote = pivot(numbers, low, high);
            quickSort(numbers, low, posicionPivote - 1);
            quickSort(numbers, posicionPivote + 1, high);
        }
    }

    public static int pivot(int[] numbers, int low, int high) {
        int i = low;
        int pivot = numbers[i];
        for (int j = low + 1; j <= high; j++) {
            if (numbers[j] <= pivot) {
                i++;
                if (i != j) {
                    exchange(numbers, i, j);
                }
            }
        }
        exchange(numbers, low, i);
        return i;
    }
    
    private static void exchange(int[] numbers,int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    //BUSQUEDAS:
    
    //BUSQUEDA SECUANCIAL:
    public static boolean busquedaSecuencial(int[] lista, int x ){
		int i = 0;
    	while (lista[i]!=x && i < lista.length -1){
    		i++; 
    	}
    	if (i < lista.length - 1){
    		return true;
    	}else if (x == lista[lista.length-1]){
    		return true;
    	}else{
    		return false;
    	}
    }

    //BUSQUEDA BINARIA:
    
    public static boolean busquedaBinaria(int[] lista, int inicio, int fin, int x) {
        int mitad;
        if (inicio > fin) {
            return false;
        } else {
            mitad = (inicio + fin) / 2;
            if (lista[mitad] == x) {
                return true;
            } else if (lista[mitad] > x) {
                return busquedaBinaria(lista, inicio, mitad - 1, x);
            } else {
                return busquedaBinaria(lista, mitad + 1, fin, x);
            }
        }
    }

    public static void algTests (){
    	int[] listaMerge = {5,7,8,1,4,9,6,2};
    	int[] listaQuick = {5,7,8,1,4,9,6,2};
    	int[] listaSec = {5,7,8,1,4,9,6,2};
    	int[] listaBin = {1,2,4,5,6,7,8,9};
    	
    	System.out.println("");
    	System.out.println("TESTS: ");
    	System.out.println("");
    	
    	System.out.println("MERGESORT: ");
    	mergeSort(listaMerge,0,listaMerge.length-1);
    	System.out.println("Array ordenado: ");
    	for(int i = 0; i < listaMerge.length;i++){
    		System.out.print(listaMerge[i]);
    	}
    	System.out.println("");
    	System.out.println("");
    	
    	System.out.println("QUICKSORT: ");
    	mergeSort(listaQuick,0,listaQuick.length-1);
    	System.out.println("Array ordenado: ");
    	for(int i = 0; i < listaQuick.length;i++){
    		System.out.print(listaQuick[i]);
    	}
    	System.out.println("");
    	System.out.println("");
    	
    	System.out.println("BUSQUEDA SECUENCIAL: ");
    	System.out.println("Busco el 2 (esta en la ultima posicion) " + busquedaSecuencial(listaSec,2)); 
    	System.out.println("Busco el 4 (esta en la lista) " + busquedaSecuencial(listaSec,4));
    	System.out.println("Busco el 3 (no esta en la lista) " + busquedaSecuencial(listaSec,3));
    	System.out.println("");
    	System.out.println("BUSQUEDA BINARIA: ");
    	System.out.println("Busco el 9 (esta en la ultima posicion) " + busquedaBinaria(listaBin,0,listaBin.length-1,2)); 
    	System.out.println("Busco el 4 (esta en la lista) " + busquedaBinaria(listaBin,0,listaBin.length-1,4));
    	System.out.println("Busco el 3 (no esta en la lista) " + busquedaBinaria(listaBin,0,listaBin.length-1,3));
    }
    
    public static void tiempoMerge (int t){
        int[] array;
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("MergeSortData.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String ColumnNamesList = "Intento,Tiempo,Memoria";
        builder.append(ColumnNamesList +"\n");
        
        for(int i = 0 ; i<100;i++){
            array = new int[t];
            
            for(int j = 0; j<t;j++){
            int elemento = (int)Math.round(Math.random()*10000);
            array[i]=elemento;
            }
            
            System.out.println("Array numero: " + i );
            long memL1 = Runtime.getRuntime().freeMemory();
            System.out.println("Memoria libre 1 (bytes): " + 
            	memL1);
            long start = System.nanoTime();
            mergeSort(array,0,array.length-1);
            long end = System.nanoTime();
            long resta = end-start;
            long memL2 = Runtime.getRuntime().freeMemory();
            System.out.println("Memoria libre 2 (bytes): " + 
            		memL2);
            System.out.println("Memoria utilizada " + Math.abs(memL1 -memL2));
            System.out.println("Tiempo " + resta + "\n");
            
            //builder.append(ColumnNamesList +"\n");
            builder.append(i+1 +",");
            builder.append(resta+",");
            builder.append(Math.abs(memL1 -memL2));
            builder.append('\n');
        }
        
        pw.write(builder.toString());
        pw.close();
        System.out.println("MergeSort done!");
    }
    
    public static void tiempoQuick (int t){
        int[] array;
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("QuickSortData.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String ColumnNamesList = "Intento,Tiempo(ns),Memoria(bytes)";
        builder.append(ColumnNamesList +"\n");
        
        for(int i = 0 ; i<100;i++){
            array = new int[t];
            
            long memL1 = Runtime.getRuntime().freeMemory(); // AQUI
            for(int j = 0; j<t;j++){
            int elemento = (int)Math.round(Math.random()*10000);
            array[i]=elemento;
            }
            
            System.out.println("Array numero: " + i );
            long start = System.nanoTime();
            quickSort(array,0,array.length-1);
            long end = System.nanoTime();
            long resta = end-start;
            long memL2 = Runtime.getRuntime().freeMemory(); //AQUI
            System.out.println("Memoria utilizada " + Math.abs(memL2-memL1)); //AQUI
            System.out.println("Tiempo " + resta + "\n");
            
            //builder.append(ColumnNamesList +"\n");
            builder.append(i+1 +",");
            builder.append(resta+",");
            builder.append(Math.abs(memL2-memL1));
            builder.append('\n');
        }
        
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");
    }
    
    public static void tiempoBinary(int t){
        int[] array;
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("BinarySearchData.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String ColumnNamesList = "Intento,Tiempo(ns)";
        builder.append(ColumnNamesList +"\n");
        
        for(int i = 0 ; i<100;i++){
            array = new int[t];
            
            for(int j = 0; j<t;j++){
            int elemento = (int)Math.round(Math.random()*10000);
            array[i]=elemento;
            }
            
            quickSort(array,0,array.length-1);
            
            System.out.println("Array numero: " + i );
            long start = System.nanoTime();
            busquedaBinaria(array,0,array.length-1,(int)Math.round(Math.random()*10000));
            long end = System.nanoTime();
            long resta = end-start;
            System.out.println("Tiempo " + resta + "\n");
            
            //builder.append(ColumnNamesList +"\n");
            builder.append(i+1 +",");
            builder.append(resta);
            builder.append('\n');
        }
        
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");
    }
    
    public static void tiempoSecuencial(int t){
    int[] array;
        
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("BusquedaSecuancialData.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        String ColumnNamesList = "Intento,Tiempo(ns)";
        builder.append(ColumnNamesList +"\n");
        
        for(int i = 0 ; i<100;i++){
            array = new int[t];
            
            for(int j = 0; j<t;j++){
            int elemento = (int)Math.round(Math.random()*10000);
            array[i]=elemento;
            }
            
            System.out.println("Array numero: " + i );
            
            long start = System.nanoTime();
            busquedaSecuencial(array,(int)Math.round(Math.random()*10000));
            long end = System.nanoTime();
            long resta = end-start;
            System.out.println("Tiempo " + resta + "\n");
            
            //builder.append(ColumnNamesList +"\n");
            builder.append(i+1 +",");
            builder.append(resta);
            builder.append('\n');
        }
        
        pw.write(builder.toString());
        pw.close();
        System.out.println("done!");
    }
    

    
    public static void main(String[] args) {
       
    	//algTests();
    	
    	int tamano = 13000;
    	
    	tiempoQuick(tamano);
    	System.out.println("___________________________________________________________________________");
    	tiempoMerge(tamano);
    	System.out.println("___________________________________________________________________________");
    	tiempoBinary(tamano);
    	System.out.println("___________________________________________________________________________");
    	tiempoSecuencial(tamano);


    }
}
