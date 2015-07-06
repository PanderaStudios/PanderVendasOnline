package modelo;

import java.io.Serializable;

public class Pedido implements Serializable {

    private String codPed;
    private String codCli;
    private String nomeCli;
    private String totalPed;
    
    public Pedido(String codPed, String codCli, String nomeCli, String totalPed) {
//        super(codPed, codProd, 0, 0);
        this.codPed = codPed;
        this.codCli = codCli;
        this.nomeCli = nomeCli;
        this.totalPed = totalPed;

    }

    public String getCodPed() {
        return codPed;
    }

    public void setCodPed(String codPed) {
        this.codPed = codPed;
    }

    public String getCodCli() {
        return codCli;
    }

    public void setCodCli(String codCli) {
        this.codCli = codCli;
    }

    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }

    public String getTotalPed() {
        return totalPed;
    }

    public void setTotalPed(String totalPed) {
        this.totalPed = totalPed;
    }

}
