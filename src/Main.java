public class Main {
    public static void main(String[] args){
        int[] sourceArray = {2,3,4,1,8,5};
        MyArray array1 = new MyArray(sourceArray);
        MyArray array2 = new MyArray(array1);
        MyArray array3 = new MyArray(array1);
        array1.mergeSort();
        array2.heapSort();
        array3.quickSort(0, array3.getLength() - 1);
        System.out.println(array1);
        System.out.println(array2);
        System.out.println(array3);
    }
}
