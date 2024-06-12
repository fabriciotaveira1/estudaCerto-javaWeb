/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.services;

/**
 *
 * @author victo
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SegurancaUtils {

    // Método para criptografar a senha
    public static String criptografarSenha(String senha) {
        try {
            // Criar uma instância do MessageDigest com o algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Calcular o hash da senha
            byte[] hash = digest.digest(senha.getBytes());
            // Converter o hash para uma representação hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            // Tratar exceção, se o algoritmo não estiver disponível
            ex.printStackTrace();
            return null;
        }
    }

    // Método para verificar a senha
    public static boolean verificarSenha(String senha, String senhaCriptografada) {
        // Criptografar a senha fornecida e comparar com a senha criptografada armazenada
        return senhaCriptografada.equals(criptografarSenha(senha));
    }
}
