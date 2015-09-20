import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MyArray {
//Attributes
    private int length;
    private int[] values;
//Methods
  //Constructors
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
  //Generic methods
    //Length getter
    public int getLength() {
        return length;
    }
    //To String method
    @Override
    public String toString() {
        return "MyArray{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
    //Swap 2 elements
    public void swap(int firstElementIndex, int secondElementIndex){
        int temp = this.values[firstElementIndex];
        this.values[firstElementIndex] = this.values[secondElementIndex];
        this.values[secondElementIndex] = temp;
    }
  //Merge sort methods
    //Merge function, both arrays MUST be sorted before run this function otherwise it wont work correctly
    private MyArray merge(MyArray array){
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
  //Heap sort methods
    //Make sub array [nodeIndex;size] heap
    private void heapify(int nodeIndex, int size) {
        nodeIndex++;
        int maxIndex = nodeIndex; //Container for index of node with maximum value
        int left = 2 * nodeIndex; //Index of left child
        int right = left + 1; //Index of right child
        // If left's child value is greater than node value then change index of node with maximum value to left node index
        if (left <= size && this.values[maxIndex-1] < this.values[left-1])
            maxIndex = left;
        //  If right's child value is greater than node value change index of node with maximum value to right's node index
        if (right <= size && this.values[maxIndex-1] < this.values[right-1])
            maxIndex = right;
        //If node value not maximum then swap and call recursively
        if (maxIndex != nodeIndex) {
            this.swap(nodeIndex-1,maxIndex-1);
            this.heapify(maxIndex-1, size);
        }
    }
    //Make whole array heap
    private void buildHeap(){
        for(int i=this.length/2+1;i>=0;i--) {
            this.heapify(i,this.length);
        }
    }
    //Sorting using heap algorithm
    public void heapSort(){
        this.buildHeap();
        int size = this.length-1;
        while(size>0){
            this.swap(0,size);
            this.heapify(0, size--);
        }
    }
  //Quick sort methods
    //Sorting using quicksort algorithm
    public void quickSort(int left,int right){
    if(left>=right)
        return;
    int m = this.partition(left,right);
    this.quickSort(left,m-1);
    this.quickSort(m+1,right);
    }
    //Divide array into 2 parts <= last element and > last element, return index of separation
    private int partition(int left, int right){
    int lessThanLastIndex=left-1;
    int greaterThanLastIndex;
    for (greaterThanLastIndex=left;greaterThanLastIndex<=right;greaterThanLastIndex++)
        if(this.values[greaterThanLastIndex]<=this.values[right]){
            this.swap(greaterThanLastIndex,lessThanLastIndex+1);
            lessThanLastIndex++;
        }
        return lessThanLastIndex;
    }
}
