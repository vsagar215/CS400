// --== CS400 File Header Information ==--
// Name: Sahil Srivastava
// Email: srivastava34@wisc.edu
// Team: KF
// TA: Sid
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMapGeneric<K, V> implements MapADT<K, V> {

    private LinkedList<Node<K, V>>[] table;
    private int size;

    HashTableMapGeneric() {
        table = new LinkedList[500];

        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    HashTableMapGeneric(int capacity) {
        table = new LinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    /**
     * This method takes in a key and value that are then stored inside our array of linked lists
     * if the hashedIndex is null (or empty) then the method will add a new Node instance with the key and value
     * store inside. After, if the the load capcity is >= to 80% then we will create a newTable and store it
     * with rehashed values from our old table; then, we will set the reference of the newTable equal to that
     * of our HashTableMap's table to store the newTable values.
     * 
     * Uncomment out the commented lines to see information on added keys
     * 
     * @return true if able to add key-value pair, false if otherwise
     */
    @Override
    public boolean put(K key, V value) {
        int hashIndex = Math.abs(key.hashCode()) % table.length;
        
        if (key instanceof Integer) {
            hashIndex = (int) key;
        }

        if (table[hashIndex] == null) {
            table[hashIndex] = new LinkedList<>();
            table[hashIndex].add(new Node<K, V>(key, value));
            size += 1;
            //System.out.println("ADDED " + " Key: " + key + " Value: " + value + " HashIndex: " + Math.abs(key.hashCode()) % table.length);

            rehash();
            return true;
        } else {
            // Checks to see if the nonEmpty index has the key already stored
            for (int i = 0; i < table[hashIndex].size(); i++) {
                if (table[hashIndex].get(i).key.equals(key)) {
                    //System.out.println("ALREADYADDED " + " Key: " + key + " Value: " + value + " HashIndex: " + Math.abs(key.hashCode()) % table.length);
                    return false;
                }
            }
            // Only Executes if key not added
            table[hashIndex].add(new Node<K, V>(key, value));
            size += 1;
            //System.out.println("ADDED " + " Key: " + key + " Value: " + value + " HashIndex: " + Math.abs(key.hashCode()) % table.length);

            rehash();
            return true;
        }
    }

    /**
     * This method only alters the array if the load capacity of
     * 0.8 is reached. If so, the method will rehash the array
     * by creating a replica of it and then chanding the size of the array
     * and reinputing all the values, newly hashed, based on the
     * infomration stored in the replica.
     * 
     * Uncomment out the commented lines to see when it is rehashing
     * 
     * @return true if rehashed, false if not rehashed
     */
    public boolean rehash() {
        if ((double)size / table.length >= 0.8) {
            //System.out.println("\nRehashing, Size: " + size);
            //System.out.println("");
            LinkedList<Node<K, V>>[] tableCopy = new LinkedList[table.length];
            for (int i = 0; i < table.length; i++) {
                tableCopy[i] = table[i];
            }

            table = new LinkedList[table.length * 2];
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
            size = 0;

            for (int i = 0; i < tableCopy.length; i++) {
                if (tableCopy[i] != null) {
                    for (int j = 0; j < tableCopy[i].size(); j++) {
                        put(tableCopy[i].get(j).key, tableCopy[i].get(j).value);
                    }
                }
            }
            return true;
        }
        //System.out.println("NOTRehashing, Size: " + size);
        return false;
    }

    /**
     * This method prints out a visual representation of the hashTable
     * Uncomment out the commented lines to see the printed representation
     * 
     * @return true if the checked size of the table equals
     * the acutal size
     */
    public boolean printTable() {
        int sizeCheck = 0;
        //System.out.println("\nPRINTING");
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    if (j != 0) {
                        //System.out.print(" | ");
                    }
                    //System.out.print("Index: " + i + " Hash index: " + 
                    //Math.abs(table[i].get(j).key.hashCode()) % table.length + " Key: " + table[i].get(j).key + " Value: " + table[i].get(j).value);
                    sizeCheck += 1;
                }
                //System.out.println("");
            } else {
                //System.out.println("Index: " + i + " NULL");
            }
        }

        //System.out.println("\nSize Check: " + sizeCheck + " Size: " + size);
        return sizeCheck == size;
    }

    /**
     * This method returns the K key if it is present in the array
     * using the hashIndex
     * 
     * @throws NoSuchElementException if no corresponding key
     * @return value found in Key-Pair
     */
    @Override
    public V get(K key) throws NoSuchElementException {
        int hashIndex = Math.abs(key.hashCode()) % table.length;

        if (key instanceof Integer) {
            hashIndex = (int) key;
        }

        if (containsKey(key)) {
            for (int i = 0; i < table[hashIndex].size(); i++) {
                if (table[hashIndex].get(i).key.equals(key)) {
                    return table[hashIndex].get(i).value;
                }
            }
            throw new NoSuchElementException("No corresponding value for key");
        } else {
            throw new NoSuchElementException("No corresponding value for key");
        }
    }

    /**
     * @return size var
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This method checks to see if the K key it is passed corresponds to
     * a key in the array of linked lists
     * 
     * @return true if K key has coressponding key, false if otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int hashIndex = Math.abs(key.hashCode()) % table.length;

        if (key instanceof Integer) {
            hashIndex = (int) key;
        }

        if (table[hashIndex] != null) {
            for (int i = 0; i < table[hashIndex].size(); i++) {
                if (table[hashIndex].get(i).key.equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method checks to see if the K key is present in the hash table
     * and removes it if it is by checking if there are multiple links
     * and removing the link is so, or setting the index element to null
     * if only one link is present
     * 
     * @return removed value or null
     */
    @Override
    public V remove(K key) {
        int hashIndex = Math.abs(key.hashCode()) % table.length;

        if (key instanceof Integer) {
            hashIndex = (int) key;
        }

        if (containsKey(key)) {
            Node<K,V> removedNode = null;
            for (int i = 0; i < table[hashIndex].size(); i++) {
                if (table[hashIndex].get(i).key.equals(key)) {
                    removedNode = table[hashIndex].get(i);
                    
                    if (table[hashIndex].size() != 1) {
                        table[hashIndex].remove(i);
                        size -= 1;
                    } else {
                        table[hashIndex] = null;
                        size -= 1;
                    }
                    return removedNode.value;
                }
            }
            return null;
        }
        return null;
    }

    /**
     * This method clears the table by setting the refence to a new empty LinkedList[]
     * of the tables length
     */
    @Override
    public void clear() {
        table = new LinkedList[table.length];

        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }

        size = 0;
    }
}
