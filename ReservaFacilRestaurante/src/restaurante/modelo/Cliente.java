package restaurante.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    private String email;
    private ArrayList<Reserva> historicoReservas;

    public Cliente(String nome, String cpf, String telefone, Endereco endereco, String email) {
        super(nome, cpf, telefone, endereco);
        this.email = email;
        this.historicoReservas = new ArrayList<Reserva>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reserva> getHistoricoReservas() {
        return historicoReservas;
    }

    public void adicionarReserva(Reserva reserva) {
        historicoReservas.add(reserva);
    }

    @Override
    public String exibirDados() {
        return "Cliente: " + getNome() + " | CPF: " + getCpf() + " | Telefone: " + getTelefone()
                + " | Email: " + email + " | Endereco: " + getEndereco();
    }

    @Override
    public String toString() {
        return exibirDados();
    }
}
