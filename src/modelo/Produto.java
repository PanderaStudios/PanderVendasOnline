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
    private String codProd;
    private String nome;
    private String qtdProd;
    private String valorProd;

    
    
    public Produto(String cpf, String nome, String qtdProd, String valorProd) {
        this.codProd = cpf;
        this.nome = nome;
        this.qtdProd = qtdProd;
        this.valorProd = valorProd;
    }

    public Produto() {
    }

    
    /**
     * @return the codProd
     */
    public String getCodProd() {
        return codProd;
    }

    /**
     * @param cpf the codProd to set
     */
    public void setCodProd(String cpf) {
        this.codProd = cpf;
    }

    /**
     * @return the nome
     */
    public String getNomeProd() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNomeProd(String nome) {
        this.nome = nome;
    }

    /**
     * @return the qtdProd
     */
    public String getQtdProd() {
        return qtdProd;
    }

    /**
     * @param qtdProd the qtdProd to set
     */
    public void setQtdProd(String qtdProd) {
        this.qtdProd = qtdProd;
    }

    /**
     * @return the valorProd
     */
    public String getValorProd() {
        return valorProd;
    }

    /**
     * @param valorProd the valorProd to set
     */
    public void setValorProd(String valorProd) {
        this.valorProd = valorProd;
    }

}
