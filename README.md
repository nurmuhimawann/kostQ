# Final Project Pemrograman Berorientasi Obyek (Kelas B)
# kostQ App
Repository final project mata kuliah PBO dimana ini merupakan pengembangan
sistem informasi dan manajemen laporan keuangan untuk usaha kost-kostan.
Dalam project ini, kami menerapkan konsep atau paradigma OOP yakni MVC. 
Dimana MVC merupakan sebuah metode dalam pengembangan sebuah aplikasi dengan memisahkan
data (Model) dari tampilannya (Views) dan bagaimana cara progam itu memprosesnya (Controller).
Project ini dikembangkan dengan menggunakan bahasa Java dan PostgreeSQL.

### Contributor:
1. Nur Muhammad Himawan (202410101070)
2. Varrel Dwitantio Purwadiansyah (202410101081)
3. Sella Eka Safitri (202410101095)

## Langkah-langkah:
Aplikasi kostQ berbasis console.

1. Buat database PostgreeSQL14/KostQ dan import file `Kost.in.sql` lalu run (F5) terlebih dahulu agar database
   atau tabel bisa terbentuk
2. Buka kode progam java aplikasi kostQ
3. Atur dbUrl, dbUser, dan dbPassword database di file `src/com/kostQ/models/BaseModel.java` sesuaikan dengan settingan
   yang ada pada PostgreeSQL masing-masing
4. Jalankan file class `Main.java`

Note. file `Kost.in.sql` merupakan file query DDL jadi harus dijalankan terlebih dahulu.
