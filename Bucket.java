/**
 * Created by Jung on 2014-11-11.
 */
enum Status {EMPTY, USING, DELETED}
class Bucket<K, V> {
    public K key;
    public V value;
    public Status status;
    public Bucket(K key, V value) {
        this.key = key;
        this.value = value;
        status = Status.USING;
    }
    public Bucket() {
        status = Status.EMPTY;
    }
}

