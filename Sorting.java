import java.util.*;
//all basic sorting algorithems are coded here 
public class Sorting{

    //insertion sort;
    public static void insertionSort(int arr[]){
        for(int i=0; i<arr.length-1; i++){
            for(int j=i+1; j>0; j--){
                if(arr[j]<arr[j-1]){
                    int temp= arr[j];
                    arr[j]= arr[j-1];
                    arr[j-1]= temp;
                }else{
                    break;
                }
            }
        }
    }
    //selection sort
    public static void selectionSort(int arr[]){
        for(int i=0; i<arr.length-1; i++){
            int min= i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[min]>arr[j]){
                    min= j;
                }
            }
            int temp= arr[min];
            arr[min]= arr[i];
            arr[i]= temp;
        }
    }

    //bubble sort
    public static void bubbleSort(int arr[]){
        boolean swap=false;
        for(int i=0; i<arr.length; i++){
            for(int j=1; j<arr.length-i; j++){
                if(arr[j]<arr[j-1]){
                    int temp= arr[j-1];
                    arr[j-1]= arr[j];
                    arr[j]= temp;
                    swap= true;
                }
            }
            if(swap==false){
                break;
            }
        }
    }
    //count sort 
    public static void countSort(int arr[]){
        int largest= arr[0];
        for(int i=0; i<arr.length; i++){
            largest= Math.max(arr[i], largest);
        }
        int count[]= new int [largest+1];
        for(int i=0; i<arr.length; i++){
            count[arr[i]]++;
        }
        int j=0;
        for(int i=0; i<count.length; i++){
            while(count[i]>0){
                arr[j]= i;
                j++;
                count[i]--;
            }
        }
    }
    public static void main(String[] args){
        int arr[]= {5, 3, 1, 4, 2};
        countSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}