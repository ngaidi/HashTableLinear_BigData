
/**
 * @author Nessrine GAIDI
 *
 */
public class HashLinearProbing {

	private int capacity; // size of Linear Probing table
	private String[] arrayKey; // Keys
	private int[] arrayValue; // Values
	
	public HashLinearProbing(int capacity) {
		this.capacity = capacity;
		arrayKey = new String[capacity];
		arrayValue = new int[capacity];
		for(int i=0;i<capacity;i++){
			arrayKey[i]=null;
		}
	}
	
    //Hash function for keys
	public int Hash(String key){
		return key.hashCode() % capacity;
	}
	
	//Insert the key-value in the table 
	public void Put(String key, int value){
		if(key != null){
			int hash = Hash(key);
			while(arrayKey[hash] !=null && arrayKey[hash] != key){
				hash = (hash + 1) % capacity;
			}
			arrayKey[hash]=key;
			arrayValue[hash]=value;
		}	
	}
	
	//Return the associated value to the key specified
	public int Get(String key){
		int hash = Hash(key);
		while(arrayKey[hash] !=null && arrayKey[hash] != key){
			hash = (hash + 1) % capacity;
		}
		return arrayValue[hash];
		
	}
	
	//Remove the specified key and it's associated value from the table 
	public void Remove(String key){
		int i = Hash(key);
		while(!key.equals(arrayKey[i])){
			i = (i + 1) % capacity;
		}
		
		//delete key and associated value
		arrayKey[i] = null;
		arrayValue[i] = 0;
		
		// rehash all keys
		i = (i + 1) % capacity;
		while(arrayKey[i] != null){
			String keyToRehash = arrayKey[i];
			int valueToRehash = arrayValue[i];
			arrayKey[i] = null;
			arrayValue[i] = 0;
			Put(keyToRehash, valueToRehash);
			i = (i + 1) % capacity;
		}
		
		
	}
	
	public static void main(String[] args) {
		int size = 11;
		HashLinearProbing HLP = new HashLinearProbing(size);
		String[] table = {"A","B","C","D","E","F","G","H","I","J","K"};
		for(int i=0;i<size;i++){
			HLP.Put(table[i],i);
		}
		//Printing the keys table
		System.out.print("The keys table:");
		for(int i=0;i<size;i++){
			System.out.print(HLP.Get(table[i]) + "|"); 
		}
		// Removing "I"
	     System.out.println();
	     System.out.print("The keys table after removing I :");
		 HLP.Remove("I");
		for(int i=0;i<size;i++){
			 System.out.print(HLP.Get(table[i]) + "|"); 
		}
			
	}

}
