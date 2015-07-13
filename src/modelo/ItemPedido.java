/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author pande_000
 */
public class ItemPedido implements Serializable {

    private static int item = 0;
    private String codItem;
    private String codPed;
    private String codProd;
    private String nomeProd;
    private String qtdComprada;
    private String valorCompra;

    public ItemPedido(String codPed, String codProd, String nomeProd, String qtdComprada, String valorCompra) {
        this.codPed = codPed;
        this.codProd = codProd;
        this.nomeProd = nomeProd;
        this.qtdComprada = qtdComprada;
        this.valorCompra = valorCompra;
        item ++;
        codItem = "" + item; 
    }

    public String getCodPed() {
        return codPed;
    }

    public void setCodPed(String codPed) {
        this.codPed = codPed;
    }

    public String getCodProd() {
        return codProd;
    }

    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }
    
    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String codProd) {
        this.nomeProd = codProd;
    }

    public String getQtdComprada() {
        return qtdComprada;
    }

    public void setQtdComprada(String qtdComprada) {
        this.qtdComprada = qtdComprada;
    }

    public String getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(String valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

}
