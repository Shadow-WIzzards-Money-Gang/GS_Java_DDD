package br.com.fiap.space.util;

import java.util.concurrent.atomic.AtomicInteger;

public class SondaUtils {

    private static final AtomicInteger contador = new AtomicInteger(0);

    public static String gerarIdSonda() {
        int id = contador.incrementAndGet();

        if (id <= 9) {
            return "SND-00" + id;
        } else if (id <= 99) {
            return "SND-0" + id;
        } else {
            return "SND-" + id;
        }
    }
}
