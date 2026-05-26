package br.com.fiap.space.util;

import br.com.fiap.space.application.singleton.CentroDeComando;

public class SondaUtils {

    public static String gerarIdSonda() {
    
        String prefixo = "SND-00";
        int totalSondas = CentroDeComando.getInstancia().listarSondas().size();
        int id = totalSondas + 1;

        if (id > 9) {
            prefixo = "SND-0";
        }

        if (id > 99) {
            prefixo = "SND-";
        }
    
        return prefixo + String.valueOf(id);
    }
}
