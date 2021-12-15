package com.kostQ.exceptions;

public class RumahKostException extends Exception {
    public RumahKostException() {
        super("Nama rumah kost, jalan, jenis kost, atau kota tidak boleh kosong");
    }
}
