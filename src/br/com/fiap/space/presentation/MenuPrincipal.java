package br.com.fiap.space.presentation;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.space.application.service.MissaoService;
import br.com.fiap.space.domain.entity.Sonda;
import br.com.fiap.space.domain.entity.SondaExploradora;
import br.com.fiap.space.domain.entity.SondaMineradora;
import br.com.fiap.space.domain.enums.Terreno;
import br.com.fiap.space.domain.enums.TipoSonda;
import br.com.fiap.space.domain.exceptions.BaseException;
import br.com.fiap.space.util.Cor;

public class MenuPrincipal {

    private Scanner scanner;
    private MissaoService missaoService;

    public MenuPrincipal() {
        this.scanner = new Scanner(System.in);
        this.missaoService = new MissaoService();
    }

    public void iniciar() {
        int opcao = -1;

        System.out.println(Cor.CYAN.getCodigo());
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   SURFACE AUTONOMOUS FLEET SYSTEM    ║");
        System.out.println("║         Centro de Comando            ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print(Cor.RESET.getCodigo());

        while (opcao != 0) {
            exibirMenu();
            opcao = lerInteiro(">> Opção: ");

            switch (opcao) {
                case 1 -> lancarSonda();
                case 2 -> listarSondas();
                case 3 -> executarRotina();
                case 4 -> recarregarSonda();
                case 0 -> System.out.println(Cor.AMARELO.getCodigo() + "\nEncerrando sistema. Até logo, Comandante." + Cor.RESET.getCodigo());
                default -> System.out.println(Cor.VERMELHO.getCodigo() + "Opção inválida. Tente novamente." + Cor.RESET.getCodigo());
            }
        }

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println(Cor.AZUL.getCodigo());
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║         MENU             ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║  1. Lançar nova sonda    ║");
        System.out.println("║  2. Listar sondas        ║");
        System.out.println("║  3. Executar rotina      ║");
        System.out.println("║  4. Recarregar sonda     ║");
        System.out.println("║  0. Sair                 ║");
        System.out.println("╚══════════════════════════╝");
        System.out.print(Cor.RESET.getCodigo());
    }

    private void lancarSonda() {
        System.out.println(Cor.CYAN.getCodigo() + "\n--- LANÇAMENTO DE SONDA ---" + Cor.RESET.getCodigo());

        System.out.println("Tipo de sonda:");
        System.out.println("  1. Exploradora");
        System.out.println("  2. Mineradora");
        int tipo = lerInteiro(">> Tipo: ");

        if (tipo != 1 && tipo != 2) {
            System.out.println(Cor.VERMELHO.getCodigo() + "Tipo inválido." + Cor.RESET.getCodigo());
            return;
        }

        TipoSonda tipoSonda = tipo == 1 ? TipoSonda.EXPLORADORA : TipoSonda.MINERADORA;

        int x = lerInteiro("Posição inicial X: ");
        int y = lerInteiro("Posição inicial Y: ");
        double bateriaAtual = lerDouble("Nível de bateria atual: ");
        double bateriaMax = lerDouble("Capacidade máxima de bateria: ");

        Double alcanceSensor = null;
        Double volumeMaximo = null;

        if (tipoSonda == TipoSonda.EXPLORADORA) {
            alcanceSensor = lerDouble("Alcance do sensor (km): ");
        } else {
            volumeMaximo = lerDouble("Volume máximo da carga (m³): ");
        }

        try {
            missaoService.criarSonda(tipoSonda, x, y, bateriaAtual, bateriaMax, alcanceSensor, volumeMaximo);
            System.out.println(Cor.VERDE.getCodigo() + "✔ Sonda lançada com sucesso!" + Cor.RESET.getCodigo());
        } catch (BaseException e) {
            System.out.println(Cor.VERMELHO.getCodigo() + "⚠ ALERTA: " + e.getMessage() + Cor.RESET.getCodigo());
        }
    }

    private void listarSondas() {
        System.out.println(Cor.CYAN.getCodigo() + "\n--- SONDAS ATIVAS ---" + Cor.RESET.getCodigo());

        List<Sonda> sondas = missaoService.listarSondas();

        if (sondas.isEmpty()) {
            System.out.println("Nenhuma sonda registrada no sistema.");
            return;
        }

        for (Sonda sonda : sondas) {
            System.out.println("\n  ID     : " + sonda.getIdSonda());
            System.out.println("  Tipo   : " + sonda.getTipoSonda());
            System.out.printf("  Posição: X=%d, Y=%d%n",
                    sonda.getPosicaoAtual().getEixoX(),
                    sonda.getPosicaoAtual().getEixoY());
            System.out.printf("  Bateria: %.1f / %.1f%n",
                    sonda.getBateria().getCapacidadeAtual(),
                    sonda.getBateria().getCapacidadeMaxima());

            if (sonda instanceof SondaMineradora mineradora) {
                System.out.printf("  Carga  : %.1f / %.1f m³%n",
                        mineradora.getCarga().getVolumeOcupado(),
                        mineradora.getCarga().getVolumeMaximo());
                if (!mineradora.getCarga().getRecursos().isEmpty()) {
                    System.out.println("  Recursos coletados: " + mineradora.getCarga().getRecursos());
                }
            }

            if (sonda instanceof SondaExploradora exploradora) {
                System.out.printf("  Alcance do sensor: %.1f km%n", exploradora.getAlcanceSensor());
            }

            System.out.println("  ----------------------------");
        }
    }

    private void executarRotina() {
        System.out.println(Cor.CYAN.getCodigo() + "\n--- ROTINA AUTÔNOMA ---" + Cor.RESET.getCodigo());

        List<Sonda> sondas = missaoService.listarSondas();
        if (sondas.isEmpty()) {
            System.out.println("Nenhuma sonda disponível.");
            return;
        }

        System.out.println("Sondas disponíveis:");
        for (Sonda s : sondas) {
            System.out.printf("  %s (%s) | Bateria: %.1f%n",
                    s.getIdSonda(), s.getTipoSonda(), s.getBateria().getCapacidadeAtual());
        }

        String id = lerTexto("ID da sonda: ");
        int x = lerInteiro("Coordenada destino X: ");
        int y = lerInteiro("Coordenada destino Y: ");

        System.out.println("Tipo de terreno:");
        System.out.println("  1. Planície");
        System.out.println("  2. Cratera  (atenção: inacessível para sondas com rodas)");
        System.out.println("  3. Rochoso");
        int terrenoOpcao = lerInteiro(">> Terreno: ");

        Terreno terreno = switch (terrenoOpcao) {
            case 2 -> Terreno.CRATERA;
            case 3 -> Terreno.ROCHOSO;
            default -> Terreno.PLANICIE;
        };

        try {
            System.out.println(Cor.AMARELO.getCodigo() + "\nIniciando rotina autônoma..." + Cor.RESET.getCodigo());
            missaoService.executarRotinaAutonoma(id, x, y, terreno);
            System.out.println(Cor.VERDE.getCodigo() + "✔ Rotina concluída! Relatório enviado ao Centro de Comando." + Cor.RESET.getCodigo());
        } catch (BaseException e) {
            System.out.println(Cor.VERMELHO.getCodigo() + "⚠ ALERTA: " + e.getMessage() + Cor.RESET.getCodigo());
        }
    }

    private void recarregarSonda() {
        System.out.println(Cor.CYAN.getCodigo() + "\n--- RECARGA NA BASE ---" + Cor.RESET.getCodigo());

        List<Sonda> sondas = missaoService.listarSondas();
        if (sondas.isEmpty()) {
            System.out.println("Nenhuma sonda disponível.");
            return;
        }

        System.out.println("Sondas disponíveis:");
        for (Sonda s : sondas) {
            System.out.printf("  %s (%s) | Bateria: %.1f / %.1f%n",
                    s.getIdSonda(), s.getTipoSonda(),
                    s.getBateria().getCapacidadeAtual(),
                    s.getBateria().getCapacidadeMaxima());
        }

        String id = lerTexto("ID da sonda para recarregar: ");

        try {
            missaoService.conectarBase(id);
            System.out.println(Cor.VERDE.getCodigo() + "✔ Sonda recarregada com sucesso!" + Cor.RESET.getCodigo());
        } catch (BaseException e) {
            System.out.println(Cor.VERMELHO.getCodigo() + "⚠ ALERTA: " + e.getMessage() + Cor.RESET.getCodigo());
        }
    }

    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println(Cor.VERMELHO.getCodigo() + "Valor inválido. Digite um número inteiro." + Cor.RESET.getCodigo());
            scanner.next();
            System.out.print(mensagem);
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.println(Cor.VERMELHO.getCodigo() + "Valor inválido. Digite um número." + Cor.RESET.getCodigo());
            scanner.next();
            System.out.print(mensagem);
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }
}
