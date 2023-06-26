package br.ucs.ffmilani.GestaoUni.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class ValidadorEmailService {
    public boolean emailEhValido(String email) {
        if (email == null)
            return false;

        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
