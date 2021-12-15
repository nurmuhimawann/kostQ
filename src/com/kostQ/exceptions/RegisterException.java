package com.kostQ.exceptions;

public class RegisterException extends Exception {
    public RegisterException() {
        super("Nik, username, password, nama, alamat, nomer hp, jenis kelamin atau kota tidak boleh kosong");
    }
}
