// Map.java

interface Map<K,V> {
  /**
   * Menambahkan (key, value) ke dalam map
   * Melakukan overwrite jika sudah terdapat elemen dengan key yang sama.
   */
  public void add(K key, V value);

  /**
   * Mengembalikan value yang tersimpan untuk key tertentu pada map
   * Mengembalikan null apabila map tidak mengandung key masukan.
   * Hanya menyimpan elemen yang sebelumnya tidak pernah disimpan.
   */

  public V get(K key);

  /**
   * Menghitung jumlah elemen yang ada pada map
   */
  public int size();
}
