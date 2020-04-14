// ListMap.java

class ListMap<K, V> implements Map<K, V> {
  public ListElement first;

  /**
   * Kelas ListElement yang diimplementasikan sebagai InnerClass
   * Anda diperkenankan menambahkan method apabila dirasa perlu
   */
  public class ListElement {
    private K key;
    private V value;
    private ListElement next;

    /**
     * Constructor ListElement
     * Set next = null
     */
    public ListElement(K key, V value) {
      // Your code goes here :)
      this.key = key;
      this.value = value;
      this.next = null;
    }

    /**
     * HINTED METHOD (Boleh dihilangkan jika tidak digunakan)
     * Menghitung panjang rantai ListElement yang ada (terhitung dari elemen *ini*)
     * Mungkin diperlukan untuk menghitung size()
     */

    /**
     * HINTED METHOD (Boleh dihilangkan jika tidak digunakan)
     * Mengembalikan ListElement yang terletak pada ujung rantai ListElement
     * Mungkin diperlukan ketika menambahkan elemen yang belum terdapat pada map. :)
     */
  }

  /**
   * Constructor ListMap
   * Set first = null
   */
  public ListMap() {
    // Your code goes here :)
    this.first = null;
  }

  /**
   * Menambahkan (key, value) ke dalam ListMap jika key belum terdapat pada map.
   * Melakukan overwrite jika sudah terdapat elemen dengan key yang sama.
   */
  public void add(K key, V value) {
    // Your code goes here :)
    if (this.first == null) {
      this.first = new ListElement(key, value);
    } else {
      ListElement window = first;
      while(!(window.key.equals(key)) && window.next != null) {
        window = window.next;
      }
      if (window.key.equals(key)) {
        window.value = value;
      } else {
        window.next = new ListElement(key, value);
      }
    }
  }

  /**
   * Mengembalikan value yang tersimpan untuk key tertentu pada map
   * Mengembalikan null apabila map tidak mengandung key masukan.
   */
  public V get(K key) {
    // Your code goes here :)
    ListElement window = first;
    if (window != null) {
      while (!(window.key.equals(key)) && window.next != null) {
        window = window.next;
      }
      if (window.key.equals(key)) {
        return window.value;
      } else {
        return null;
      }
    } else {
      return null;
    }
  }

  /**
   * Menghitung jumlah elemen yang ada pada map
   */
  public int size() {
    // Your code goes here :)
    int i = 0;
    ListElement currPointer = first;
    while (currPointer != null) {
      i++;
      currPointer = currPointer.next;
    }
    return i;
  }
}