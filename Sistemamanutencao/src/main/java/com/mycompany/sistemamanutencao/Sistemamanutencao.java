package com.mycompany.sistemamanutencao;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistemamanutencao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> sistemaMensagens = new ArrayList<>();
        int opcao = 0;

        do {
            System.out.println("\n--- Menu do Sistema ---");
            System.out.println("1. Adicionar Mensagem");
            System.out.println("2. Visualizar Mensagens");
            System.out.println("3. Remover Mensagem");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opcao: ");

            // Leitura segura: evita InputMismatchException se digitar letras ou Enter
            String entrada = scanner.nextLine().trim();
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite apenas o numero da opcao.");
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1:
                    adicionarMensagem(scanner, sistemaMensagens);
                    break;
                case 2:
                    visualizarMensagens(sistemaMensagens);
                    break;
                case 3:
                    removerMensagem(scanner, sistemaMensagens);
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        } while (opcao != 4);

        scanner.close();
    }

    // Chamado #01 - Correcao: nao permite mensagem vazia ou so com espacos
    private static void adicionarMensagem(Scanner scanner, ArrayList<String> mensagens) {
        System.out.print("Digite a mensagem: ");
        String msg = scanner.nextLine();

        if (msg.trim().isEmpty()) {
            System.out.println("ALERTA: a mensagem nao pode ser vazia ou conter apenas espacos!");
            return;
        }

        mensagens.add(msg.trim());
        System.out.println("Mensagem adicionada! Total de mensagens: " + mensagens.size());
    }

    private static void visualizarMensagens(ArrayList<String> mensagens) {
        System.out.println("\nMensagens no sistema:");
        if (mensagens.isEmpty()) {
            System.out.println("Nenhuma mensagem registrada.");
        } else {
            listar(mensagens);
        }
    }

    // Chamado #02 - Melhoria: remover mensagem com validacao de indice
    private static void removerMensagem(Scanner scanner, ArrayList<String> mensagens) {
        if (mensagens.isEmpty()) {
            System.out.println("Nenhuma mensagem registrada para remover.");
            return;
        }

        System.out.println("\nMensagens no sistema:");
        listar(mensagens);

        System.out.print("Digite o numero da mensagem a remover (0 para cancelar): ");
        String entrada = scanner.nextLine().trim();

        int numero;
        try {
            numero = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida! Digite apenas um numero.");
            return;
        }

        if (numero == 0) {
            System.out.println("Remocao cancelada.");
            return;
        }

        // Validacao do indice (evita IndexOutOfBoundsException)
        if (numero < 1 || numero > mensagens.size()) {
            System.out.println("Numero invalido! Informe um valor entre 1 e " + mensagens.size() + ".");
            return;
        }

        String removida = mensagens.remove(numero - 1);
        System.out.println("Mensagem removida: \"" + removida + "\"");
        System.out.println("Total de mensagens: " + mensagens.size());
    }

    private static void listar(ArrayList<String> mensagens) {
        for (int i = 0; i < mensagens.size(); i++) {
            System.out.println((i + 1) + ". " + mensagens.get(i));
        }
    }
}
