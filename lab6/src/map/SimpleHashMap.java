package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private int capacity;
	private static final double loadFactor = 0.75;
	private Entry<K, V>[] table;
	private int size;

	public SimpleHashMap() {
		capacity = 16;
		table = (Entry<K, V>[]) new Entry[capacity];

	}

	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
	}

	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		Entry<K, V> temp = find(index(key), key);
		if (temp != null) {
			return temp.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		Entry<K, V> temp = find(index, arg0);
		if (temp != null) {
			return temp.setValue(arg1);
		} else {
			if (table[index] == null) {
				table[index] = new Entry<K, V>(arg0, arg1);
				size++;
			} else {
				temp = table[index];
				table[index] = new Entry<K, V>(arg0, arg1);
				table[index].next = temp;
				size++;
			}
		}
		if ((size / capacity) > loadFactor) {
			rehash();
		}
		return null;
	}

	private void rehash() {
		int increase = capacity * 2;
		Entry<K, V>[] temp = new Entry[increase];
		Entry<K, V>[] old = table;
		this.table = temp;
		this.size = 0;
		this.capacity = increase;
		for (int i = 0; i < old.length; i++) {
			Entry<K, V> e = old[i];
			if (e != null) {
				put(e.getKey(), e.getValue());
				while (e.next != null) {
					put(e.next.getKey(), e.next.getValue());
					e = e.next;
				}

			}
		}

	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		if (!isEmpty()) {
			Entry<K, V> temp = find(index(key), key);
			Entry<K, V> atIndex = table[index(key)];
			if (temp != null) {
				V old = temp.getValue();
				if (atIndex.getKey().equals(temp.getKey())) {
					if (temp.next == null) {
						table[index(key)] = null;
					} else {
						table[index(key)] = temp.next;
					}
					size--;
					return old;
				}
				while (atIndex.next != null) {
					if (atIndex.next.getKey().equals(temp.getKey())) {
						atIndex.next = temp.next;
						size--;
						return old;
					} else if (atIndex.next.next == null) {
						atIndex.next = null;
						size--;
						return old;
					} else {
						atIndex = atIndex.next;
					}
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key) {
		if (table[index] != null) {
			Entry<K, V> temp = table[index];
			while (temp != null) {
				if (temp.getKey().equals(key)) {
					return temp;
				}
				temp = temp.next;
			}
		}
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

	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
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
			V old = this.value;
			this.value = value;
			return old;
		}

		public String toString() {
			return getKey() + "=" + getValue();
		}
		
	

	}

	public static void main(String[] args) {
		SimpleHashMap<String, Integer> smh = new SimpleHashMap<String, Integer>();
		smh.put("abc", 10);
		smh.put("abc", 20);
		smh.put("abc", 30);
		smh.put("abc", 40);
		smh.put("2", 10);
		smh.put("2", 20);
		System.out.print(smh.show() + "\n" + smh.size());
	}

}
