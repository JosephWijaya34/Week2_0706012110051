package Model

class Kambing(nama: String, Jenis: String, Usia: String) : Hewan(nama, Jenis, Usia) {
    override fun speak(): String {
        return "mbek mbekk mbekk"
    }

    override fun eat(rumput: Rumput): String {
        return "Rumput"
    }
}