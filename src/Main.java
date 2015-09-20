public class Main {
    public static void main(String[] args){
        int[] sourceArray = {2,3,4,1,8,5};
        MyArray array1 = new MyArray(sourceArray);
        array1.mergeSort();
        System.out.println(array1);
    }
}
