package br.com.model;

import br.com.services.SegurancaUtils;

/**
 *
 * @author Marina
 */
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senhaHash; // Alterada para armazenar a senha criptografada
    private String tipoUsuario;
    private int tentativasLoginIncorretas; // Novo atributo para armazenar o número de tentativas de login incorretas
    private boolean contaSuspensa; // Novo atributo para indicar se a conta está suspensa

    public boolean verificarSenha(String senha) {
        return SegurancaUtils.verificarSenha(senha, senhaHash);
    }

    // Métodos getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Correção: utilizar senhaHash em vez de senha
    public String getSenhaHash() {
        return senhaHash;
    }

    // Correção: utilizar senhaHash em vez de senha
    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTentativasLoginIncorretas() {
        return tentativasLoginIncorretas;
    }

    public void setTentativasLoginIncorretas(int tentativasLoginIncorretas) {
        this.tentativasLoginIncorretas = tentativasLoginIncorretas;
    }

    public boolean isContaSuspensa() {
        return contaSuspensa;
    }

    public void setContaSuspensa(boolean contaSuspensa) {
        this.contaSuspensa = contaSuspensa;
    }
}
