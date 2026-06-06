package br.com.fiap.space;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.space.application.service.MissaoService;
import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.enums.Terreno;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.BaseException;
import br.com.fiap.space.util.Cor;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final MissaoService missaoService = new MissaoService();

    public static void main(String[] args) {
        boolean executando = true;

        System.out.println(colorir(Cor.CYAN, "=== Centro de Comando de Sondas ==="));

        while (executando) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    criarSonda();
                    break;
                case 2:
                    listarSondas();
                    break;
                case 3:
                    executarRotinaAutonoma();
                    break;
                case 4:
                    conectarBase();
                    break;
                case 0:
                    executando = false;
                    System.out.println(colorir(Cor.CYAN, "Encerrando..."));
                    break;
                default:
                    System.out.println(colorir(Cor.AMARELO, "Opcao invalida. Tente novamente."));
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1 - Criar sonda");
        System.out.println("2 - Listar sondas");
        System.out.println("3 - Executar rotina autonoma");
        System.out.println("4 - Conectar sonda a base (recarregar)");
        System.out.println("0 - Sair");
    }

    private static void criarSonda() {
        TipoSonda tipoSonda = lerTipoSonda();

        // Cada coordenada e validada no momento em que e digitada.
        Integer eixoX = lerInteiroComMinimo("Coordenada X (>= 0): ", 0);
        Integer eixoY = lerInteiroComMinimo("Coordenada Y (>= 0): ", 0);

        Double capacidadeMaxima = lerDoubleComMinimo("Capacidade maxima da bateria (>= 0): ", 0.0);
        Double nivelBateria = lerDoubleNoIntervalo(
            "Nivel atual da bateria (0 a " + capacidadeMaxima + "): ", 0.0, capacidadeMaxima);

        Double alcanceSensor = null;
        Double volumeMaximo = null;

        if (tipoSonda == TipoSonda.EXPLORADORA) {
            alcanceSensor = lerDoubleComMinimo("Alcance do sensor (>= 0): ", 0.0);
        } else {
            volumeMaximo = lerDoubleComMinimo("Volume maximo de carga (>= 0): ", 0.0);
        }

        try {
            missaoService.criarSonda(tipoSonda, eixoX, eixoY, nivelBateria,
                capacidadeMaxima, alcanceSensor, volumeMaximo);
            System.out.println(colorir(Cor.VERDE, "Sonda criada com sucesso!"));
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarSondas() {
        List<Sonda> sondas = missaoService.listarSondas();

        if (sondas.isEmpty()) {
            System.out.println(colorir(Cor.AMARELO, "Nenhuma sonda cadastrada."));
            return;
        }

        System.out.println("\n--- Sondas cadastradas ---");
        for (Sonda sonda : sondas) {
            System.out.printf("%s | %s | posicao (%d, %d) | bateria %.1f/%.1f%n",
                sonda.getIdSonda(),
                sonda.getTipoSonda().getNome(),
                sonda.getPosicaoAtual().getEixoX(),
                sonda.getPosicaoAtual().getEixoY(),
                sonda.getBateria().getCapacidadeAtual(),
                sonda.getBateria().getCapacidadeMaxima());
        }
    }

    private static void executarRotinaAutonoma() {
        if (missaoService.listarSondas().isEmpty()) {
            System.out.println(colorir(Cor.AMARELO, "Nenhuma sonda cadastrada."));
            return;
        }

        String idSonda = lerTexto("ID da sonda: ");
        Integer eixoX = lerInteiroComMinimo("Coordenada X de destino (>= 0): ", 0);
        Integer eixoY = lerInteiroComMinimo("Coordenada Y de destino (>= 0): ", 0);
        Terreno terreno = lerTerreno();

        try {
            missaoService.executarRotinaAutonoma(idSonda, eixoX, eixoY, terreno);
            System.out.println(colorir(Cor.VERDE, "Rotina executada com sucesso!"));
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void conectarBase() {
        if (missaoService.listarSondas().isEmpty()) {
            System.out.println(colorir(Cor.AMARELO, "Nenhuma sonda cadastrada."));
            return;
        }

        String idSonda = lerTexto("ID da sonda: ");

        try {
            missaoService.conectarBase(idSonda);
            System.out.println(colorir(Cor.VERDE, "Sonda recarregada com sucesso!"));
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------- Leitura de input com validacao imediata ----------

    private static TipoSonda lerTipoSonda() {
        while (true) {
            System.out.println("Tipo de sonda:");
            TipoSonda[] tipos = TipoSonda.values();
            for (int i = 0; i < tipos.length; i++) {
                System.out.println("  " + (i + 1) + " - " + tipos[i].getNome());
            }
            int escolha = lerInteiro("Escolha: ");
            if (escolha >= 1 && escolha <= tipos.length) {
                return tipos[escolha - 1];
            }
            System.out.println(colorir(Cor.AMARELO, "Tipo invalido. Tente novamente."));
        }
    }

    private static Terreno lerTerreno() {
        while (true) {
            System.out.println("Terreno:");
            Terreno[] terrenos = Terreno.values();
            for (int i = 0; i < terrenos.length; i++) {
                System.out.println("  " + (i + 1) + " - " + terrenos[i].getTipoSolo());
            }
            int escolha = lerInteiro("Escolha: ");
            if (escolha >= 1 && escolha <= terrenos.length) {
                return terrenos[escolha - 1];
            }
            System.out.println(colorir(Cor.AMARELO, "Terreno invalido. Tente novamente."));
        }
    }

    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println(colorir(Cor.AMARELO, "Valor nao pode ser vazio."));
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println(colorir(Cor.AMARELO, "Digite um numero inteiro valido."));
            }
        }
    }

    private static int lerInteiroComMinimo(String mensagem, int minimo) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor >= minimo) {
                return valor;
            }
            System.out.println(colorir(Cor.AMARELO, "O valor deve ser maior ou igual a " + minimo + "."));
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println(colorir(Cor.AMARELO, "Digite um numero valido."));
            }
        }
    }

    private static double lerDoubleComMinimo(String mensagem, double minimo) {
        while (true) {
            double valor = lerDouble(mensagem);
            if (valor >= minimo) {
                return valor;
            }
            System.out.println(colorir(Cor.AMARELO, "O valor deve ser maior ou igual a " + minimo + "."));
        }
    }

    private static double lerDoubleNoIntervalo(String mensagem, double minimo, double maximo) {
        while (true) {
            double valor = lerDouble(mensagem);
            if (valor >= minimo && valor <= maximo) {
                return valor;
            }
            System.out.println(colorir(Cor.AMARELO,
                "O valor deve estar entre " + minimo + " e " + maximo + "."));
        }
    }

    private static String colorir(Cor cor, String texto) {
        return cor.getCodigo() + texto + Cor.RESET.getCodigo();
    }
}
