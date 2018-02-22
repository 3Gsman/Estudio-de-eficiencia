

public class Alg {

    //ORDENACIONES:
	
    //MERGE SORT:
	
    public static void mergesort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(numbers, low, middle);
            mergesort(numbers, middle + 1, high);
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
                  //  ejemplo.exchange(numbers, i, j);
                }
            }
        }
     //  ejemplo.exchange(numbers, low, i);
        return i;
    }

    //BUSQUEDAS:
    
    //BUSQUEDA SECUANCIAL:
    
    public static boolean busquedaSecuencial(int[] lista, int x ){
		int i = 0;
    	while (lista[i]!=x){
    		i++;
    	if (lista[i]==x){
    		return true;
    	}else{
    		return false;
    	}
    	
    	}
		return false;
    	
    	
    }
    
    /*
	 * --NSET CODE HERE--
     */
    //BUSQUEDA BINARIA:
    
    public boolean busquedaBinaria(int[] lista, int inicio, int fin, int x) {
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

    public static void main(String[] args) {
       
    	System.out.println("papa");
    	
    	int[] lista = {5,7,3,1,4,9,2};
    	
    	
    	System.out.println(busquedaSecuencial(lista,8));
    	
    	

    }

}
