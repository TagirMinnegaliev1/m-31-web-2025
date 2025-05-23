package ru.nfbgu.service;

public interface PasswordHashService {
    String getHash(String pass);
    boolean checkPass(String hash, String pass);
}
