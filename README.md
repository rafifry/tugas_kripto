# tugas_kripto

Pada implementasi DES ini ditulis menggunakan bahasa java, yang terdiri dari file MainDes dan class DesEncrypter
Pada DesEncrypter sendiri terdapat beberapa method untuk melakukan enkripsi
-Pertama tama dilakukan deklarasi key dan plaintext berupa string-Selanjutnya dilakukan deklarasi matriks matriks yang diperlukan dalam algoritma DES seperti matriks Initialization, Permutation, expand, dan Sbox.
-Plaintext di konversi menjadi biner dengan method teksToBiner.
-Method makeBlocks membagi plaintext menjadi blok blok yang berukuran 64bit. Apabila plaintext tidak mencapai 64bit maka akan ditambah integer 11111111 untuk melengkapi blok menjadi 64bit.
-Method permutation akan mengenakan plaintext dengan matriks permutasi nya.
-Method keyToBiner akan mengkonversi key yang dimasukan kedalam bentuk biner.
-getLeftPlainBiner dan getRighPlainBiner akan membagi plaintext menjadi 32bit sub-blok kiri dan 32bit sub-blok kanan, dimana bagian kiri akan di XOR kan dengan hasil permutation function (setelah sbox) sementara bagian kanan di expand dan juga menjadi bagian kiri di iterasi selanjutnya
-getLeftKeyBiner dan getRightKeyBiner akan membagi key yang telah di kenai PC1 menjadi 28bit sub-blok kiri dan 28bit sub-blok kanan.
-leftShift akan melakukan left shift pada masing masing sub-blok key yang telah terbuat.
-method merge menggabungkan kembali sub-blok key menjadi 1 blok untuk dikenai matriks PC2.
-method xor akan melakukan proses xor antara array temp dan temp2.
-pada method sBox, Key 48bit akan dibagi menjadi 8x6bit blok dengan menggunakan makeBlock. Dengan menggunakan substring dan parseInt untuk mendapat kan index pada matrix, tiap 6bit sub-blok tersebut dikenai dengan tabel sBox nya masing masing sehingga menghasilkan 8x4bit string
-terakhir adalah method encrypt, dimana semua proses enkripsi algoritma DES dijalankan. Pertama tama dibuat beberapa variable array untuk menampung data selama proses enkripsi. Selanjutnya tiap 64bit plaintext di kenai method method yang telah dibuat sebelumnya dengan menggunakan perulangan for sesuai jumlah block plaintext. Setelah proses selesai array array yg dibuat akan diclear dan method akan mengembalikan nilai cipher.


Kelompok Ryan dkk.
Kontribusi: Membantu membuat beberapa method yaitu getLeft, getRight, merge, teksToBiner, serta ikut saat presentasi di kelas.
