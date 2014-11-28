/**
 * Created by Jung on 2014-11-11.
 */

class HashTable<K, V> {
    private Bucket<K, V>[] table;
    private int size;
    private int tableSize;
    private double LOAD_THRESHOLD = 0.7;
    private static int INITIAL_TABLE_SIZE = 5;
    public HashTable()	// 생성자
    {
        table= new Bucket[INITIAL_TABLE_SIZE];
        size=0;
        tableSize=INITIAL_TABLE_SIZE;
        for (int i = 0; i < INITIAL_TABLE_SIZE; i++)
            table[i] = new Bucket<K,V>();
    }
    public int hash(K key) {
        return key.hashCode() % tableSize;
    }
    public int probe(int tableIndex) {
        return 1; 	// 선형 조사
    }
    public V search(K key)	// 탐색
    {
        int home = hash(key);
        int tableIndex = home;
        int increment = probe(tableIndex);
        do{
            if(table[tableIndex].status == Status.USING && key.equals(table[tableIndex].key))
                return table[tableIndex].value;
            else
                tableIndex= (tableIndex+increment) %tableSize;
        } while( tableIndex != home );
        return null;
    }
    public void insert(K key, V value)	// 삽입
    {
        if((double)size/tableSize>=LOAD_THRESHOLD){
            rehash();
            System.out.println("리해쉬");
        }


        int tableIndex=hash(key);
        int increment=probe(tableIndex);

        while ( table[tableIndex].status == Status.USING ) {
            if ( table[tableIndex].key.equals(key)){
                table[tableIndex].value=value;
                return;
            }
            tableIndex=(tableIndex+increment)%tableSize;
        }
        table[tableIndex].key=key;
        table[tableIndex].value=value;
        table[tableIndex].status=Status.USING;
        size++;
    }
    public boolean remove(K key)	// 삭제
    {
        int home = hash(key);
        int tableIndex = home;
        int increment = probe(tableIndex);
        do{
            if(table[tableIndex].status == Status.USING && key.equals(table[tableIndex].key)) {
                table[tableIndex].status = Status.DELETED;
                size--;
                return true;
            }
            else
                tableIndex= (tableIndex+increment) %tableSize;
        } while( tableIndex != home );
        return false;
    }
    public void rehash() {	// 재해싱
        Bucket<K, V>[] oldTable = table;
        tableSize = 2 * oldTable.length + 1;
        table = new Bucket[tableSize];
        for (int i = 0; i < tableSize; i++)
            table[i] = new Bucket<K,V>();
        size = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i].status == Status.USING) {
                insert(oldTable[i].key, oldTable[i].value);
            }
        }
    }
    public void printHashTable() 	// 테이블 출력
    {
        for(int i=0; i<tableSize; i++){
                System.out.println(table[i].key+": "+table[i].value+" "+table[i].status);
        }
    }
}

