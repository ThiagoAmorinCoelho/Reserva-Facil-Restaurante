package restaurante.modelo;

public abstract class Mesa {
    private int numero;
    private int capacidade;
    private boolean disponivel;

    public Mesa(int numero, int capacidade) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.disponivel = true;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean verificarDisponibilidade() {
        return disponivel;
    }

    public void reservar() {
        this.disponivel = false;
    }

    public void cancelarReserva() {
        this.disponivel = true;
    }

    public abstract String descricao();

    @Override
    public String toString() {
        return descricao() + " | Capacidade: " + capacidade + " pessoas | Disponivel: "
                + (disponivel ? "Sim" : "Nao");
    }
}
