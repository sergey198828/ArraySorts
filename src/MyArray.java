import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MyArray {
//Attributes
    private int length;
    private int[] values;
//Methods
    //Constructor using standard array initialization
    public MyArray(int[] values) {
        this.length = 0;
        for(int i:values){
            this.length++;
        }
        this.values = new int[this.length];
        this.values = values;
    }
    //Constructor using MyArray initialization
    public MyArray(MyArray array) {
        this.length = array.length;
        this.values = array.values;
    }
    //Length getter
    public int getLength() {
        return length;
    }
    //Merge function, both arrays MUST be sorted before run this function otherwise it wont work correctly
    public MyArray merge(MyArray array){
        int resultArrayLength = this.length+array.length; //identify future result array lenght
        int[] resultArray = new int[resultArrayLength]; //Allocate place for future result array
        int i=0; //First array counter
        int j=0; //Second array counter
        int k=0; //Result counter
        //Merging
        while(i<this.length && j<array.length)
        try {
            {
                if (this.values[i] <= array.values[j]) {
                    resultArray[k] = this.values[i];
                    i++;
                } else {
                    resultArray[k] = array.values[j];
                    j++;
                }
                k++;
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.err.println("Argument arrays must be sorted");
        }
        //Write tail
        while(i<this.length){
            resultArray[k] = this.values[i];
            i++;
            k++;
        }
        while(j<array.length){
            resultArray[k] = array.values[j];
            j++;
            k++;
        }
        //Return result
        MyArray result = new MyArray(resultArray);
        return result;
    }
    //Sorting array using merge recursive algorithm
    public void mergeSort(){
        Queue<MyArray> q = new LinkedList<MyArray>(); //Queue
        //Add all array items to queue as individual arrays
        for(int i=0; i<this.length;i++){
            int[] a = new int[1];
            a[0]=this.values[i];
            MyArray queueMember = new MyArray(a);
            q.offer(queueMember);
        }
        //Merging by 2
        while(q.size()>1){
            q.offer(q.poll().merge(q.poll()));
        }
        //Updating current object
        this.values=q.poll().values;
    }

    @Override
    public String toString() {
        return "MyArray{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
