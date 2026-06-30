package restaurante.servico;

import java.util.ArrayList;
import java.util.List;

import restaurante.controle.ControleReserva;
import restaurante.excecao.CapacidadeExcedidaException;
import restaurante.excecao.MesaIndisponivelException;
import restaurante.excecao.ReservaNaoEncontradaException;
import restaurante.modelo.Cliente;
import restaurante.modelo.Funcionario;
import restaurante.modelo.Mesa;
import restaurante.modelo.Reserva;
import restaurante.modelo.Restaurante;

public class SistemaReservas {
    private Restaurante restaurante;
    private ArrayList<Cliente> clientes;
    private ArrayList<Funcionario> funcionarios;
    private ControleReserva<Integer> controleReservas;
    private int proximoCodigoReserva;

    public SistemaReservas(Restaurante restaurante) {
        this.restaurante = restaurante;
        this.clientes = new ArrayList<Cliente>();
        this.funcionarios = new ArrayList<Funcionario>();
        this.controleReservas = new ControleReserva<Integer>(1001);
        this.proximoCodigoReserva = 1;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public ControleReserva<Integer> getControleReservas() {
        return controleReservas;
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void cadastrarMesa(Mesa mesa) {
        restaurante.adicionarMesa(mesa);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public Reserva realizarReserva(String cpfCliente, int numeroMesa, String data, String horario, int quantidadePessoas)
            throws MesaIndisponivelException, CapacidadeExcedidaException {

        Cliente cliente = buscarClientePorCpf(cpfCliente);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nao encontrado para o CPF informado.");
        }

        Mesa mesa = restaurante.buscarMesaPorNumero(numeroMesa);
        if (mesa == null) {
            throw new IllegalArgumentException("Mesa nao encontrada.");
        }

        if (!mesa.verificarDisponibilidade()) {
            throw new MesaIndisponivelException("A mesa " + numeroMesa + " ja esta ocupada.");
        }

        if (quantidadePessoas <= 0) {
            throw new IllegalArgumentException("A quantidade de pessoas deve ser maior que zero.");
        }

        if (quantidadePessoas > mesa.getCapacidade()) {
            throw new CapacidadeExcedidaException("A mesa comporta apenas " + mesa.getCapacidade() + " pessoas.");
        }

        Reserva reserva = new Reserva(proximoCodigoReserva, cliente, mesa, data, horario, quantidadePessoas);
        reserva.confirmarReserva();
        cliente.adicionarReserva(reserva);
        controleReservas.adicionarReserva(reserva);
        proximoCodigoReserva++;
        return reserva;
    }

    public void cancelarReserva(int codigoReserva) throws ReservaNaoEncontradaException {
        Reserva reserva = controleReservas.buscarReservaPorCodigo(codigoReserva);

        if (reserva == null) {
            throw new ReservaNaoEncontradaException("Reserva nao encontrada.");
        }

        reserva.cancelarReserva();
    }

    public List<Reserva> buscarReservasPorCliente(String cpfCliente) {
        Cliente cliente = buscarClientePorCpf(cpfCliente);
        if (cliente == null) {
            return new ArrayList<Reserva>();
        }
        return cliente.getHistoricoReservas();
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente.exibirDados());
        }
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.exibirDados());
        }
    }

    public void verificarDisponibilidadeMesa(int numeroMesa) {
        Mesa mesa = restaurante.buscarMesaPorNumero(numeroMesa);
        if (mesa == null) {
            System.out.println("Mesa nao encontrada.");
        } else {
            System.out.println(mesa.descricao() + " | Disponivel: " + (mesa.isDisponivel() ? "Sim" : "Nao"));
        }
    }

    public void gerarRelatorioOcupacao() {
        int total = restaurante.getMesas().size();
        int ocupadas = 0;

        for (Mesa mesa : restaurante.getMesas()) {
            if (!mesa.isDisponivel()) {
                ocupadas++;
            }
        }

        int disponiveis = total - ocupadas;

        System.out.println("\n===== RELATORIO DE OCUPACAO =====");
        System.out.println("Restaurante: " + restaurante.getNome());
        System.out.println("Total de mesas: " + total);
        System.out.println("Mesas ocupadas: " + ocupadas);
        System.out.println("Mesas disponiveis: " + disponiveis);
        System.out.println("\nDetalhamento das mesas:");
        restaurante.listarMesas();
        System.out.println("=================================\n");
    }
}
