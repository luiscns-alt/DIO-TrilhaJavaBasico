package org.example;

import java.util.Scanner;
import java.util.function.Predicate;

public class ContaBanco {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String agencyNumber = promptForValidInput(scanner, "Por favor, digite o número da Agência (5 dígitos/ Formato: 000-0): ",
                    input -> input.matches("\\d{3}-\\d"), "Formato de agência inválido. Use o formato '067-8'.");

            int accountNumber = Integer.parseInt(promptForValidInput(scanner, "Por favor, digite o número da Conta (4 dígitos): ",
                    input -> input.matches("\\d{4}"), "O número deve ter 4 dígitos."));

            String customerName = promptForValidInput(scanner, "Por favor, digite o nome do Cliente (até 30 caracteres): ",
                    input -> !input.isEmpty() && input.length() <= 30, "O texto não pode ser vazio e deve ter no máximo 30 caracteres.");

            double balance = Double.parseDouble(promptForValidInput(scanner, "Por favor, digite o saldo da Conta: ",
                    input -> input.matches("[0-9]+(\\.[0-9]{1,2})?"), "Entrada inválida. Digite um número decimal positivo."));

            System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %04d e seu saldo R$ %.2f já está disponível para saque.%n",
                    customerName, agencyNumber, accountNumber, balance);
        }
    }

    private static String promptForValidInput(Scanner scanner, String promptMessage, Predicate<String> validation, String errorMessage) {
        String input;
        do {
            System.out.print(promptMessage);
            input = scanner.nextLine();
            if (validation.test(input)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        } while (true);
        return input;
    }
}
