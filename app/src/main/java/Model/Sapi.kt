package Model

class Sapi(nama: String, Jenis: String, Usia: String) : Hewan(nama, Jenis, Usia) {
    override fun speak(): String {
        return "mooo moooo mooooo"
    }

    override fun eat(rumput: Rumput): String {
        return "Rumput"
    }
}