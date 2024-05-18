package org.exemple;

import org.example.ContaTerminal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaTerminalTest {

    @Mock
    private Scanner scanner;

    @Test
    public void testPromptForValidInput_validAgency() {
        when(scanner.nextLine()).thenReturn("067-8");
        String result = ContaTerminal.promptForValidInput(scanner, "Agência:", input -> input.matches("\\d{3}-\\d"), "Erro");
        assertEquals("067-8", result);
    }

    @Test
    public void testPromptForValidInput_invalidAgencyFormat() {
        when(scanner.nextLine()).thenReturn("12345", "067-8");
        String result = ContaTerminal.promptForValidInput(scanner, "Agência:", input -> input.matches("\\d{3}-\\d"), "Erro");
        assertEquals("067-8", result);
    }

    @Test
    public void testPromptForValidInput_validAccount() {
        when(scanner.nextLine()).thenReturn("1234");
        String result = ContaTerminal.promptForValidInput(scanner, "Conta:", input -> input.matches("\\d{4}"), "Erro");
        assertEquals("1234", result);
    }

    @Test
    public void testPromptForValidInput_invalidAccountLength() {
        when(scanner.nextLine()).thenReturn("123", "1234");
        String result = ContaTerminal.promptForValidInput(scanner, "Conta:", input -> input.matches("\\d{4}"), "Erro");
        assertEquals("1234", result);
    }

    @Test
    public void testPromptForValidInput_validName() {
        when(scanner.nextLine()).thenReturn("João da Silva");
        String result = ContaTerminal.promptForValidInput(scanner, "Nome:", input -> !input.isEmpty() && input.length() <= 30, "Erro");
        assertEquals("João da Silva", result);
    }

    @Test
    public void testPromptForValidInput_invalidNameEmpty() {
        when(scanner.nextLine()).thenReturn("", "João da Silva");
        String result = ContaTerminal.promptForValidInput(scanner, "Nome:", input -> !input.isEmpty() && input.length() <= 30, "Erro");
        assertEquals("João da Silva", result);
    }

    @Test
    public void testPromptForValidInput_invalidNameLength() {
        when(scanner.nextLine()).thenReturn("Nome muito longo que ultrapassa trinta caracteres", "João da Silva");
        String result = ContaTerminal.promptForValidInput(scanner, "Nome:", input -> !input.isEmpty() && input.length() <= 30, "Erro");
        assertEquals("João da Silva", result);
    }

    @Test
    public void testPromptForValidInput_validBalance() {
        when(scanner.nextLine()).thenReturn("1500.50");
        String result = ContaTerminal.promptForValidInput(scanner, "Saldo:", input -> input.matches("[0-9]+(\\.[0-9]{1,2})?"), "Erro");
        assertEquals("1500.50", result);
    }

    @Test
    public void testPromptForValidInput_invalidBalanceFormat() {
        when(scanner.nextLine()).thenReturn("abc", "1500.50");
        String result = ContaTerminal.promptForValidInput(scanner, "Saldo:", input -> input.matches("[0-9]+(\\.[0-9]{1,2})?"), "Erro");
        assertEquals("1500.50", result);
    }
}
