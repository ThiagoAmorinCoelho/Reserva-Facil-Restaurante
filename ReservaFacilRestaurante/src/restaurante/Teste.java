package restaurante;

import java.util.List;
import java.util.Scanner;

import restaurante.excecao.CapacidadeExcedidaException;
import restaurante.excecao.MesaIndisponivelException;
import restaurante.excecao.ReservaNaoEncontradaException;
import restaurante.modelo.Cliente;
import restaurante.modelo.Endereco;
import restaurante.modelo.Funcionario;
import restaurante.modelo.MesaExterna;
import restaurante.modelo.MesaInterna;
import restaurante.modelo.Reserva;
import restaurante.modelo.Restaurante;
import restaurante.servico.SistemaReservas;

public class Teste {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaReservas sistema;

    public static void main(String[] args) {
        carregarDadosIniciais();
        exibirMenu();
        scanner.close();
    }

    private static void carregarDadosIniciais() {
        Endereco enderecoRestaurante = new Endereco("Avenida Afonso Pena", 1234, "Centro", "Campo Grande", "79002-000");
        Restaurante restaurante = new Restaurante("ReservaFacil Restaurante", enderecoRestaurante);
        sistema = new SistemaReservas(restaurante);

        Endereco endAna = new Endereco("Rua das Flores", 120, "Centro", "Campo Grande", "79010-000");
        Endereco endBruno = new Endereco("Rua Bahia", 450, "Jardim dos Estados", "Campo Grande", "79020-000");
        Endereco endCarlos = new Endereco("Campo Grande");

        sistema.cadastrarCliente(new Cliente("Ana Lima", "123.456.789-10", "(67) 99001-1111", endAna, "ana@email.com"));
        sistema.cadastrarCliente(new Cliente("Bruno Souza", "987.654.321-00", "(67) 99002-2222", endBruno, "bruno@email.com"));
        sistema.cadastrarFuncionario(new Funcionario("Carlos Matos", "456.789.123-11", "(67) 99003-3333", endCarlos, "Gerente", "MAT-001"));

        sistema.cadastrarMesa(new MesaInterna(1, 4, "Salao Principal"));
        sistema.cadastrarMesa(new MesaInterna(2, 6, "Salao VIP"));
        sistema.cadastrarMesa(new MesaExterna(3, 4, true));
        sistema.cadastrarMesa(new MesaExterna(4, 8, false));

        try {
            sistema.realizarReserva("123.456.789-10", 1, "15/07/2026", "19:00", 3);
            sistema.realizarReserva("987.654.321-00", 3, "16/07/2026", "20:30", 4);
        } catch (MesaIndisponivelException | CapacidadeExcedidaException e) {
            System.out.println("Erro ao criar reservas iniciais: " + e.getMessage());
        }
    }

    private static void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========= RESERVAFACIL RESTAURANTE =========");
            System.out.println("1. Dados da entrega");
            System.out.println("2. Listar clientes");
            System.out.println("3. Cadastrar cliente");
            System.out.println("4. Listar funcionarios");
            System.out.println("5. Cadastrar funcionario");
            System.out.println("6. Listar mesas");
            System.out.println("7. Cadastrar mesa interna");
            System.out.println("8. Cadastrar mesa externa");
            System.out.println("9. Realizar reserva");
            System.out.println("10. Cancelar reserva");
            System.out.println("11. Listar reservas em ordem alfabetica");
            System.out.println("12. Buscar reservas por cliente");
            System.out.println("13. Verificar disponibilidade de mesa");
            System.out.println("14. Relatorio de ocupacao");
            System.out.println("15. Roteiro rapido de demonstracao");
            System.out.println("0. Sair");
            System.out.println("============================================");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    mostrarDadosEntrega();
                    break;
                case 2:
                    sistema.listarClientes();
                    break;
                case 3:
                    cadastrarCliente();
                    break;
                case 4:
                    sistema.listarFuncionarios();
                    break;
                case 5:
                    cadastrarFuncionario();
                    break;
                case 6:
                    sistema.getRestaurante().listarMesas();
                    break;
                case 7:
                    cadastrarMesaInterna();
                    break;
                case 8:
                    cadastrarMesaExterna();
                    break;
                case 9:
                    realizarReserva();
                    break;
                case 10:
                    cancelarReserva();
                    break;
                case 11:
                    sistema.getControleReservas().imprimirReservas();
                    break;
                case 12:
                    buscarReservasPorCliente();
                    break;
                case 13:
                    verificarDisponibilidade();
                    break;
                case 14:
                    sistema.gerarRelatorioOcupacao();
                    break;
                case 15:
                    roteiroRapidoDemonstracao();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastrar Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        String cpf = lerCpfValido();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Endereco endereco = lerEndereco();

        try {
            sistema.cadastrarCliente(new Cliente(nome, cpf, telefone, endereco, email));
            System.out.println("Cliente cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void cadastrarFuncionario() {
        System.out.println("\n--- Cadastrar Funcionario ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        String cpf = lerCpfValido();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Matricula: ");
        String matricula = scanner.nextLine();
        Endereco endereco = lerEndereco();

        try {
            sistema.cadastrarFuncionario(new Funcionario(nome, cpf, telefone, endereco, cargo, matricula));
            System.out.println("Funcionario cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar funcionario: " + e.getMessage());
        }
    }

    private static void cadastrarMesaInterna() {
        System.out.println("\n--- Cadastrar Mesa Interna ---");
        System.out.print("Numero da mesa: ");
        int numero = lerInteiro();
        System.out.print("Capacidade: ");
        int capacidade = lerInteiro();
        System.out.print("Setor: ");
        String setor = scanner.nextLine();

        sistema.cadastrarMesa(new MesaInterna(numero, capacidade, setor));
        System.out.println("Mesa interna cadastrada com sucesso.");
    }

    private static void cadastrarMesaExterna() {
        System.out.println("\n--- Cadastrar Mesa Externa ---");
        System.out.print("Numero da mesa: ");
        int numero = lerInteiro();
        System.out.print("Capacidade: ");
        int capacidade = lerInteiro();
        System.out.print("Mesa coberta? (s/n): ");
        boolean coberta = scanner.nextLine().trim().equalsIgnoreCase("s");

        sistema.cadastrarMesa(new MesaExterna(numero, capacidade, coberta));
        System.out.println("Mesa externa cadastrada com sucesso.");
    }

    private static void realizarReserva() {
        System.out.println("\n--- Realizar Reserva ---");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        System.out.print("Numero da mesa: ");
        int numeroMesa = lerInteiro();
        System.out.print("Data da reserva (dd/mm/aaaa): ");
        String data = scanner.nextLine();
        System.out.print("Horario da reserva (hh:mm): ");
        String horario = scanner.nextLine();
        System.out.print("Quantidade de pessoas: ");
        int quantidade = lerInteiro();

        try {
            Reserva reserva = sistema.realizarReserva(cpf, numeroMesa, data, horario, quantidade);
            System.out.println("Reserva realizada com sucesso:");
            System.out.println(reserva);
        } catch (MesaIndisponivelException | CapacidadeExcedidaException | IllegalArgumentException e) {
            System.out.println("Erro ao realizar reserva: " + e.getMessage());
        }
    }

    private static void cancelarReserva() {
        System.out.println("\n--- Cancelar Reserva ---");
        System.out.print("Codigo da reserva: ");
        int codigo = lerInteiro();

        try {
            sistema.cancelarReserva(codigo);
            System.out.println("Reserva cancelada com sucesso.");
        } catch (ReservaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void buscarReservasPorCliente() {
        System.out.println("\n--- Buscar Reservas por Cliente ---");
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        List<Reserva> reservas = sistema.buscarReservasPorCliente(cpf);

        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para este cliente.");
            return;
        }

        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private static void verificarDisponibilidade() {
        System.out.println("\n--- Verificar Disponibilidade ---");
        System.out.print("Numero da mesa: ");
        int numero = lerInteiro();
        sistema.verificarDisponibilidadeMesa(numero);
    }

    private static void roteiroRapidoDemonstracao() {
        System.out.println("\n===== ROTEIRO RAPIDO PARA APRESENTACAO =====");
        System.out.println("1) Use a opcao 1 para mostrar nome dos alunos, projeto e link do Drive.");
        System.out.println("2) Use a opcao 11 para mostrar as reservas ja criadas.");
        System.out.println("3) Use a opcao 14 para mostrar o relatorio de ocupacao.");
        System.out.println("4) Use a opcao 3 para construir um cliente ao vivo.");
        System.out.println("5) Use a opcao 9 para construir uma reserva ao vivo.");
        System.out.println("6) Tente reservar uma mesa ocupada para demonstrar a excecao MesaIndisponivelException.");
        System.out.println("7) Tente reservar uma mesa com pessoas acima da capacidade para demonstrar CapacidadeExcedidaException.");
        System.out.println("8) Use a opcao 10 para cancelar uma reserva e liberar a mesa.");
        System.out.println("============================================\n");
    }

    private static Endereco lerEndereco() {
        System.out.println("Endereco:");
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Numero: ");
        int numero = lerInteiro();
        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();
        return new Endereco(rua, numero, bairro, cidade, cep);
    }

    private static String lerCpfValido() {
        while (true) {
            System.out.print("CPF (000.000.000-00): ");
            String cpf = scanner.nextLine();
            String numeros = cpf.replaceAll("[^0-9]", "");

            if (numeros.length() == 11 && !todosDigitosIguais(numeros)) {
                return cpf;
            }
            System.out.println("CPF invalido. Tente novamente.");
        }
    }

    private static boolean todosDigitosIguais(String numeros) {
        char primeiro = numeros.charAt(0);
        for (int i = 1; i < numeros.length(); i++) {
            if (numeros.charAt(i) != primeiro) {
                return false;
            }
        }
        return true;
    }

    private static int lerInteiro() {
        while (true) {
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.print("Valor invalido. Digite um numero inteiro: ");
            }
        }
    }
}
