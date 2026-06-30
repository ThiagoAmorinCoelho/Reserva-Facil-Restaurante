package restaurante.modelo;

public class Reserva implements Comparable<Reserva> {
    private int codigo;
    private Cliente cliente;
    private Mesa mesa;
    private String data;
    private String horario;
    private int quantidadePessoas;
    private String status;

    public Reserva(int codigo, Cliente cliente, Mesa mesa, String data, String horario, int quantidadePessoas) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.mesa = mesa;
        this.data = data;
        this.horario = horario;
        this.quantidadePessoas = quantidadePessoas;
        this.status = "Pendente";
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public String getStatus() {
        return status;
    }

    public void confirmarReserva() {
        mesa.reservar();
        status = "Confirmada";
    }

    public void cancelarReserva() {
        mesa.cancelarReserva();
        status = "Cancelada";
    }

    @Override
    public int compareTo(Reserva reserva) {
        int comparacaoNome = this.cliente.getNome().compareToIgnoreCase(reserva.cliente.getNome());
        if (comparacaoNome != 0) {
            return comparacaoNome;
        }
        return Integer.compare(this.codigo, reserva.codigo);
    }

    @Override
    public String toString() {
        return "Reserva #" + codigo
                + " | Cliente: " + cliente.getNome()
                + " | Mesa: " + mesa.getNumero()
                + " | Data: " + data
                + " | Horario: " + horario
                + " | Pessoas: " + quantidadePessoas
                + " | Status: " + status;
    }
}
