package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private int capacity;
	private static final double loadFactor = 0.75;
	private Entry<K,V> [] table;
	private int size;
	
	public SimpleHashMap () {
		capacity = 16;
		table = (Entry<K,V>[]) new Entry[capacity];
		
	}
	
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K,V>[]) new Entry[capacity];
	}

	
	@Override
	public V get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	@Override
	public V put(K arg0, V arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	} 
	private int index (K key) {
		return 0;
	}
	
	private Entry<K,V> find (int index, K key){
		return null;
	}
	
	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.length; i++) {
			sb.append(i + "\t");
			Entry<K, V> temp = table[i];
			if (temp != null) {
				while (temp != null) {
					sb.append(temp.toString() + ", ");
					temp = temp.next;
				}
			} else {
				sb.append("empty");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private static class Entry<K, V> implements Map.Entry<K,V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		public Entry (K key, V value) {
			this.key = key;
			this.value = value;
		}
		public Entry<K,V> setNext(Entry<K,V> next){
			return this.next = next;
		}

		@Override
		public K getKey() {
			return key;	
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			return this.value = value;
		}
		
		public String toString () {
			return getKey() + "=" + getValue();
		}

	}

}
