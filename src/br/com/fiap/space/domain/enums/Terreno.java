package br.com.fiap.space.domain.enums;

public enum Terreno {
    PLANICIE("Planicie", 1.0),
    CRATERA("Cratera", 1.5),
    ROCHOSO("Rochoso", 2.0);

    private String tipoSolo;
    private Double mutiplicadorConsumo;

    Terreno(String tipoSolo, Double mutiplicadorConsumo){
        this.tipoSolo = tipoSolo;
        this.mutiplicadorConsumo = mutiplicadorConsumo;
    }

    public String getTipoSolo() {
        return tipoSolo;
    }

    public Double getMutiplicadorConsumo() {
        return mutiplicadorConsumo;
    }
}
