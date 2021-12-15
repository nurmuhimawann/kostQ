package com.kostQ.exceptions;

public class KamarKostException extends Exception {
    public KamarKostException() {
        super("Nama kost tidak boleh kosong");
    }
}
