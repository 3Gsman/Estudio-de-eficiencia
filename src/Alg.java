import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 * La clase Alg.java es aquella en la que tenemos los algoritmos de ordenacion y de busqueda
 * MergeSort, Quicksort, BinarySearch y SecuentialSearch.
 * En ella para cada metodo de ordenacion y busqueda creamos un array de 100 posiciones para el cual 
 * vamos introduciendo cantidades de elementos que varian entre 5000, 7500, 10000 y 12500.
 * Tras crear los arrays con sus elementos hacemos testeos de los algoritmos en base al tiempo que tardan
 * y en concreto para los algoritmos de ordenacion, la memoria del sistema que utilizan.
 * Acto seguido los datos son enviados a ficheros excel.csv. 
 * 
 * @author German Garcia Garcia && Pablo Ferrer Lopez.
 */
public class Alg {

    //ORDENACIONES:
	
    //MERGE SORT:
	
	/**
	 * El algoritmo MergeSort es un metodo de ordenacion recursivo por mezcla ,que a traves de la tecnica
	 * divide y venceras, divide el array en unidades lo mas pequeñas posibles (estando desordenadas)
	 * y a continuacion compara cada una y la coloca en su posicion correspondiente (ordenandolas)
	 * de forma que se vallan agrupando en unidades mas grandes y con estas vuelve a comparar los elementos
	 * entre los grupos que tenemos hasta tener nuestro array ordenado.
	 *  
	 * @param Como parametros que le pasamos tenemos: Un Array de numeros enteros, la primera posicion y
	 * 		  la ultima posicion
	 * 
	 * @param int[] numbers
	 * @param low
	 * @param high
	 */
    public static void mergeSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergeSort(numbers, low, middle);
            mergeSort(numbers, middle + 1, high);
            merge(numbers, low, middle, high);
        }
    }

    private static void merge(int[] numbers, int low, int middle, int high) {
    	long memL1 = Runtime.getRuntime().freeMemory();
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
        long memL2 = Runtime.getRuntime().freeMemory(); 
    }

    //QUICK SORT:
    
    /**
     * 
     * @param numbers
     * @param low
     * @param high
     */
    
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
    
    /**
     * El algoritmo de BusquedaSecuencial es un metodo boolean que nos permite realizar una busqueda de un 
     * elemento en un Array de elementos de forma que recorre el Array y compara el elemento en cuestion
     * y si es ese el buscado nos devolvera true y si no lo es continuara avanzando posiciones una a una 
     * hasta encontrar el elemento dado.
     * Si no lo encuentra al recorrer el Array hasta el final entonces devolvera false.
     * 
     * @param Como parametros que le pasamos tenemos: Un Array de numeros enteros y el elemento a buscar.
     * 
     * @param int[] lista
     * @param int x
     * 
     * @return Devolvera true o false dependiendo de si encuentra el elemento o no.
     */
    
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
    
    /**
     * El algoritmo de BusquedaBinaria es un metodo recursivo boolean que nos permite 
     * realizar una busqueda de un elemento en un Array a traves de la tecnica divide y
     * venceras. Consiste en comparar el valor con el elemento que se encuentra en la
     * posicion que ocupa el medio del Array y verificar si este es nuestro elemento buscado.
     * Si lo es entonces directamente devolvera true pero si no lo es entonces comprobamos si 
     * nuestro valor a buscar es mayor que el elemento que ocupa la posicion del medio y si 
     * es mayor entonces aplicamos el metodo recursivo pasandole como inicio 
     * la mitad del Array + 1 y como final simplemente el final del Array. En caso de que sea menor
     * hacemos la misma operacion pero esta vez como inicio le pasamos el inicio del Array y como 
     * final la mitad de nuestro Array - 1. 
     *   
     * @param Los parametros que le pasamos son: Un Array de numeros enteros, una posicion inicial,
     * 		  una posicion final y el valor a buscar.
     * 
     * @param int [] lista
     * @param int inicio
     * @param int fin
     * @param int x
     * @return
     */
    
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

    /**
     * Este metodo nos permite realizar una comprobacion sobre los algoritmos a utilizar de forma
     * que nos cercioremos de que funcionan correctamente para poder hacer pruebas con ellos.
     * Para ello realizamos tests con Arrays predeterminados de forma que podamos verificarlo 
     * correctamente.
     * 
     */
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
    
    /**
     * El siguiente metodo crea, a traves de bucles, 100 Arrays de numeros enteros los cuales
     * posteriormente seran reyenados con un numero T de elementos el cual le pasamos por parametro
     * al metodo.
     * A continuacion lo que nos permite es para cada uno de los Arrays calcular el tiempo de ejecución 
     * que tarda en aplicar el algoritmo de ordenacion MergeSort y la memoria utilizada por este.
     * Seguidamente se crea un archivo Excel.csv en el cual se almancenan dichos datos de forma que podamos
     * trabajar con ellos
     *  
     * @param El parametro que utilizamos es un entero que es el numero de elementos de cada Array.
     * @param int t
     */
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
         
           // System.out.println("Memoria libre 1 (bytes): " + 
            //	memL1);
            long start = System.nanoTime();
            long memL1 = Runtime.getRuntime().freeMemory();
            mergeSort(array,0,array.length-1);
            long memL2 = Runtime.getRuntime().freeMemory();
            long end = System.nanoTime();
            long resta = end-start;
         
            //System.out.println("Memoria libre 2 (bytes): " + 
            //		memL2);
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
    
    
    /**
     * El siguiente metodo crea, a traves de bucles, 100 Arrays de numeros enteros los cuales
     * posteriormente seran reyenados con un numero T de elementos el cual le pasamos por parametro
     * al metodo.
     * A continuacion lo que nos permite es para cada uno de los Arrays calcular el tiempo de ejecución 
     * que tarda en aplicar el algoritmo de ordenacion QuickSort y la memoria utilizada por este.
     * Seguidamente se crea un archivo Excel.csv en el cual se almancenan dichos datos de forma que podamos
     * trabajar con ellos
     *  
     * @param El parametro que utilizamos es un entero que es el numero de elementos de cada Array.
     * @param int t
     */
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
    
    /**
     * El siguiente metodo crea, a traves de bucles, 100 Arrays de numeros enteros los cuales
     * posteriormente seran reyenados con un numero T de elementos el cual le pasamos por parametro
     * al metodo.
     * A continuacion lo que nos permite es para cada uno de los Arrays calcular el tiempo de ejecución 
     * que tarda en aplicar el algoritmo de busqueda BinarySearch .
     * Seguidamente se crea un archivo Excel.csv en el cual se almancenan dichos datos de forma que podamos
     * trabajar con ellos
     *  
     * @param El parametro que utilizamos es un entero que es el numero de elementos de cada Array.
     * @param int t
     */
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
    
    /**
     * El siguiente metodo crea, a traves de bucles, 100 Arrays de numeros enteros los cuales
     * posteriormente seran reyenados con un numero T de elementos el cual le pasamos por parametro
     * al metodo.
     * A continuacion lo que nos permite es para cada uno de los Arrays calcular el tiempo de ejecución 
     * que tarda en aplicar el algoritmo de busqueda SecuentialSearch.
     * Seguidamente se crea un archivo Excel.csv en el cual se almancenan dichos datos de forma que podamos
     * trabajar con ellos
     *  
     * @param El parametro que utilizamos es un entero que es el numero de elementos de cada Array.
     * @param int t
     */
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
    

    /**
     * El main de esta clase lo que nos permite es, a traves de un entero tamano, imprimir por pantalla
     * los valores de tiempo y memoria que utilizan nuestros distintos metodos.
     * El entero tamano sera modificado entre valores de 5000, 7500, 10000 y 12500 para obtener distintos
     * resultados y poder hacer pruebas con ellos.
     * 
     */
    public static void main(String[] args) {
       
    	//algTests();
    	
    	int tamano = 5000;
    	
    	tiempoQuick(tamano);
    	System.out.println("___________________________________________________________________________");
    	tiempoMerge(tamano);
    	System.out.println("___________________________________________________________________________");
    	tiempoBinary(tamano);
    	System.out.println("___________________________________________________________________________");
    	tiempoSecuencial(tamano);


    }
}
