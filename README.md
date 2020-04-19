# PvZ-with-GUI

compile : javac Main.java Gameplay.java Zombie.java Shot.java Peashooter.java PeaShot.java Sun.java Sound.java IndexInject.java

run : java Main


Saat program berhasil dijalankan, maka kita akan dialihkan ke Main Menu
* Tekan 'Click To Start' untuk memulai game

Program akan masuk ke inti permainan
* Mengambil Sunflower Point -> Klik Sun yang muncul sebelum ia menghilang
* Menanam Plant -> Klik kartu Plant -> Klik bidang tempat tanaman ingin ditanam
* Setiap menanam Plant, perhatikan Sunflower Point yang dimiliki dan ketersediaan petak

Program akan memasuki tahap Game Over
* Program akan masuk tahap ini jika zombie terdeteksi pada sisi paling kiri lapangan
* Akan ditampilkan jumlah zombie yang berhasil dikalahkan
* Restart program untuk memainkan program dari awal
