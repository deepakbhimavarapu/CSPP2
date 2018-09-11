import java.io.BufferedInputStream;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Class for set.
 * @author : 
 */
class Set {
	private int[] arr;
	private int size;
	public Set(int capacity){
		arr = new int[capacity];
		size = 0;
	}
	public Set(){
		arr = new int[10];
		size = 0;
	}

	private void resize(){
		arr = Arrays.copyOf(arr, size*2);
	}

	public void add(int item){
		if(size == arr.length) {
			resize();
		}
		if(!contains(item)){
			arr[size++] = item;
		}
	}

	public void add(int[] newarr){
		for(int i:newarr){
			add(i);
		}
	}
	public int get(int index){
		return arr[index];
	}
	public String toString(){
		if(size==0){
			return "{}";
		}
		String str = "{";
		for(int i = 0; i < size-1; i++ ){
			str+=arr[i] + ", ";
		}
		return str+ arr[size-1]+"}";
	}

	public boolean contains(int item){
		for(int i : arr){
			if(i == item){
				return true;
			}
		}
		return false;
	}
	public Set intersection(Set newset){
		Set result = new Set();
		for(int i = 0; i<size;i++){
			if(newset.contains(arr[i])){
				result.add(arr[i]);
			}
		}
		return result;
	}

	public Set retainAll(int[] newarr){
		Set result = new Set();
		result.add(newarr);
		return intersection(result);
	}
	public int[][] cartesianProduct(Set newset){
		if(size == 0 || newset.size() == 0){
			return null;
		}
		int[][] result = new int[size*newset.size()][2];
		int a = 0;
		for(int i = 0; i< size; i++){
			for(int j = 0; j < newset.size(); j++){
				int[] child = new int[2];
				child[0] = arr[i];
				child[1] = newset.get(j); 
				result[a++] = child;
			}
		}
		return result;
	}

	public int size(){
		return size;
	}
    //your code goes here...
    //Good luck :-)
}
/**
 * Solution class for code-eval.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * helper function to convert string input to int array.
     *
     * @param      s     { string input from test case file }
     *
     * @return     { int array from the given string }
     */
    public static int[] intArray(final String s) {
        String input = s;
        if (input.equals("[]")) {
            return new int[0];
        }
        if (s.contains("[")) {
            input = s.substring(1, s.length() - 1);
        }
        return Arrays.stream(input.split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();
    }
    /**
     * main function to execute test cases.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // instantiate this set
        Set s = new Set();
        // code to read the test cases input file
        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
        // check if there is one more line to process
        while (stdin.hasNext()) {
            // read the line
            String line = stdin.nextLine();
            // split the line using space
            String[] tokens = line.split(" ");
            // based on the list operation invoke the corresponding method
            switch (tokens[0]) {
                case "size":
                System.out.println(s.size());
                break;
                case "contains":
                System.out.println(s.contains(Integer.parseInt(tokens[1])));
                break;
                case "print":
                System.out.println(s);
                break;
                case "add":
                int[] intArray = intArray(tokens[1]);
                if (intArray.length == 1) {
                    s.add(intArray[0]);
                } else {
                    s.add(intArray);
                }
                break;
                case "intersection":
                s = new Set();
                Set t = new Set();
                intArray = intArray(tokens[1]);
                s.add(intArray);
                intArray = intArray(tokens[2]);
                t.add(intArray);
                System.out.println(s.intersection(t));
                break;
                case "retainAll":
                s = new Set();
                intArray = intArray(tokens[1]);
                s.add(intArray);
                intArray = intArray(tokens[2]);
                System.out.println(s.retainAll(intArray));
                break;
                case "cartesianProduct":
                s = new Set();
                t = new Set();
                intArray = intArray(tokens[1]);
                s.add(intArray);
                intArray = intArray(tokens[2]);
                t.add(intArray);
                System.out.println(Arrays.deepToString(s.cartesianProduct(t)));
                break;
                default:
                break;
            }
        }
    }
}
