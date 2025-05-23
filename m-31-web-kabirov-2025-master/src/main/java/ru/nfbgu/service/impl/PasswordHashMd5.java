package ru.nfbgu.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.nfbgu.service.PasswordHashService;

@Service
public class PasswordHashMd5 implements PasswordHashService {
    @Override
    public String getHash(String pass) {
        return DigestUtils.md5Hex(pass);
    }

    @Override
    public boolean checkPass(String hash, String pass) {
        return hash.equals(DigestUtils.md5Hex(pass));
    }
}
