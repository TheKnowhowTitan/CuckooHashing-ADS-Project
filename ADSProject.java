/*
A memory of size 20 with three blocks is available for storage. [20x3]. Assume
suitable data set of size 150 and demonstrate the implementation of Cuckoo Hashing,
with different possibilities. [Each element of dataset is an object, which will be stored
in one blocked]. Comment on size of memory required for implementation.
*/


package ads.project;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author RAGHAV
 */

class cuckooHashing{
    /*
        We have a Cuckoo Hashing Function that has to be implemented on a table
        of size 20 with three blocks available for storage. Let us assume that
        we have two such tables. Thus, we declare the following constants.
    */
    static final int TABLES = 2;
    static final int SIZE = 20;
    static final int CHAIN = 3;
    
    /*
        The next step is to declare the data structures required.
        These are the the hash table and an array to point to the block which we
        will be referencing.
        Further, to make the work easier using functions, we add another dimension
        to both our table and pointer.
    */
    
    int [][]pointer = new int[TABLES][SIZE];
    int [][][]table = new int[TABLES][SIZE][CHAIN];
    
    //Cycle is a queue that will help to detect cycle which doing insertions.
    Queue<Integer> cycle = new LinkedList<>();
    
    //The constructor is used to initialise data structures with suitable values
    cuckooHashing(){
        for(int i=0;i<TABLES;i++)
            for(int j=0;j<SIZE;j++)
                pointer[i][j] = 0;
        
        for(int i=0;i<TABLES;i++)
            for(int j=0;j<SIZE;j++)
                for(int k=0;k<CHAIN;k++)
                    table[i][j][k] = -1;
    }
    
    //This is our hash function
    private int hashFunc(int key, int id){
        if(id == 0)
            return (key%17)+(key%4); //For the first table, the function is h1(x) = x%17 + x%4
        return (key%19)+(key%2); //For the second table, it is h2(x) = x%19 + x%2
    }
    
    //This recursive function holds the main logic of our program.
    void place(int key, int id){
        //If placing a certain element forms a cycle, we don't insert it.
        if(!cycle.isEmpty() && cycle.peek() == key){
            int cEle = cycle.peek();
            System.out.println("Cycle");
            while(!cycle.isEmpty()){
                System.out.print(cycle.peek()+" ");
                cycle.remove();
            }
            System.out.println("\nCannot insert element "+cEle);
            return;
        }
        //Next, we calculate the position where the element needs to be inserted
        int pos = hashFunc(key, id);
        if(table[id][pos][pointer[id][pos]] == -1){
            while(!cycle.isEmpty())
                cycle.remove();
            table[id][pos][pointer[id][pos]] = key; //if the block is empty, insert
            pointer[id][pos] = (pointer[id][pos] + 1)%3; //Increment the position to the next block at the same position
            return;
        }
        //The block is not empty
        cycle.add(key);
        int temp = table[id][pos][pointer[id][pos]];
        table[id][pos][pointer[id][pos]] = key;
        key = temp;
        pointer[id][pos] = (pointer[id][pos] + 1)%3;//We try for the next block
        place(key, (id+1)%2); //Recursively call the function by switching between the tables
    }
    
    //Function to see if the required key is present in the table or not
    int search(int key){
        int pos = hashFunc(key, 0);
        for(int i=0;i<CHAIN;i++){
            if(table[0][pos][pointer[0][i]] == key){
                System.out.println("Table 1");
                return i;
            }
        }
        pos = hashFunc(key,1);
        for(int i=0;i<CHAIN;i++){
            if(table[1][pos][pointer[1][i]] == key){
                System.out.println("Table 2");
                return i;
            }
        }
        return -1;
    }
    
    //A function to print the tables
    void print(){
        for(int i = 0; i<TABLES;i++){
            System.out.println("Table "+(i+1));
            for(int j = 0;j<SIZE;j++){
                for(int k=0;k<CHAIN;k++)
                    System.out.print(table[i][j][k]+" ");
                System.out.println("");
            }
            System.out.println("");
        }
    }
    
}

public class ADSProject {
    
    public static void main(String[] args) {
        cuckooHashing ch = new cuckooHashing();
        //Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            //int x = r.nextInt(1000);
            int x = sc.nextInt();
            ch.place(Math.abs(x), 0);
            arr[i] = Math.abs(x);
        }
        System.out.println("");
        ch.print();
    }
  
}
