import java.util.*;

public class HashMapCode{

    static class HashMap<K,V>{/* K,V--> these are generics (when we dont know 
        what will be the type of our Hashmap , then we use these generics)*/
        private class Node{
            K key;
            V value;

            public Node(K key, V value){
                this.key= key;
                this.value= value;
            }
        }

        private int n; //node size
        private int N; // Array size
        private LinkedList<Node> buckets[]; //N-> buckets.length

        @SuppressWarnings("unchecked") /*this is just for ignoring the warning that is given by 
        inside my hashmap constructor where i am creating the linkedlist without defining its type, 
        so it will just ignore the warnings and the error (if its occur) */
        public HashMap(){
            this.N=4;
            this.buckets= new LinkedList[4];
            for(int i=0; i<buckets.length; i++){
                this.buckets[i]= new LinkedList<>();
            }
        }
        
        private int hashFunction(K key){
            int hashC= key.hashCode();/*this will give any random value it can be negative or positive,
            we should take its absolute value and then its modulo with size coz we need index till size */
            return Math.abs(hashC) % N;
        }

        private int searchInLL(K key, int buckIdx){
            LinkedList<Node> ll= buckets[buckIdx];
            int di= 0;
            for(int i=0; i<ll.size(); i++){
                Node node= ll.get(i);
                if(node.key==key){
                    return di;
                }
                di++;
            }
            return -1;
        }

        @SuppressWarnings("unchecked")
        public void rehash(){
            LinkedList<Node> oldBucket[]= buckets;// storing old data(nodes) of buckets
            buckets= new LinkedList[N*2];// creating a new bucket of twice size
            N= N*2;
            // initializing all buckets with a empty linked list
            for(int i=0; i<buckets.length; i++){
                buckets[i]= new LinkedList<>();
            }

            // now copying the old data(nodes) into this new bucket
            for(int i=0; i<oldBucket.length; i++){
                LinkedList<Node> ll= oldBucket[i];
                for(int j=0; j<ll.size(); j++){
                    Node node= ll.remove();
                    put(node.key, node.value);
                }
            }
        }

        public void put(K key, V value){ //--> O(lambda)-> O(1)
            int buckIdx= hashFunction(key);
            int dataIdx= searchInLL(key, buckIdx);// return data index or -1 if already exixts 
            if(dataIdx!= -1){
                Node node= buckets[buckIdx].get(dataIdx);
                node.value= value;
            }else{
                buckets[buckIdx].add(new Node(key, value));
                n++;
            }

            double lambda= (double) n/N;
            if(lambda > 2.0){
                rehash();
            }  
        }


        public boolean containsKey(K key){ //-- O(1)
            int buckIdx= hashFunction(key);// first we find the bucket index for that key
            int dataIdx= searchInLL(key, buckIdx);/*  second we check the desired key in that bucket index,
            if its -1 means the key is not present return false, otherwise return true */

            if(dataIdx!= -1){
                return true;
            }else{
                return false;
            }
        }

        public V get(K key){/* O(1) ->Same logic is applied here but this time we are returning that value */
            int buckIdx= hashFunction(key);
            int dataIdx= searchInLL(key, buckIdx);
            
            if(dataIdx!= -1){
                Node node= buckets[buckIdx].get(dataIdx);
                return node.value;
            }else{
                return null;
            }
        }
        
        public V remove(K key){//  O(1)-> same first 2 steps are applied here 
            int buckIdx= hashFunction(key);
            int dataIdx= searchInLL(key, buckIdx);

            if(dataIdx!= -1){
                Node node = buckets[buckIdx].remove(dataIdx);
                n--;
                return node.value;
            }else{
                return null;
            }
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys= new ArrayList<>();

            for(int i=0; i<buckets.length; i++){
                LinkedList<Node> ll= buckets[i];
                for(Node node: ll){
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty(){
            return n==0;
        }


    }
    public static void main(String args[]){
        HashMap<String, Integer> hm= new HashMap<>();
        hm.put("india", 100);
        hm.put("US", 50);
        hm.put("china", 150);

        ArrayList<String> keys= hm.keySet();
        for(String key: keys){
            System.out.println(key);
        }

    }
}