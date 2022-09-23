package Model

import android.os.Parcel

open class Ayam(nama:String,Jenis:String,Usia:String) : Hewan(nama,Jenis,Usia) {
    override fun speak():String {
        return "pok pok pok"
    }

    override fun eat(biji: Biji): String {
        return "Biji"
    }
}