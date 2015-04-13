/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;

/**
 *
 * @author Marcos
 */
public class Produto implements Serializable {
    private String cpf;
    private String nome;
    private String quantidade;
    private String valor;

    
    
    public Produto(String cpf, String nome, String quantidade, String valor) {
        this.cpf = cpf;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Produto() {
    }

    
    /**
     * @return the cpf
     */
    public String getCod() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the quantidade
     */
    public String getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

}
