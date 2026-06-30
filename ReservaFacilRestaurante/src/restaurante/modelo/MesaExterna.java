package restaurante.modelo;

public class MesaExterna extends Mesa {
    private boolean coberta;

    public MesaExterna(int numero, int capacidade, boolean coberta) {
        super(numero, capacidade);
        this.coberta = coberta;
    }

    public boolean isCoberta() {
        return coberta;
    }

    public void setCoberta(boolean coberta) {
        this.coberta = coberta;
    }

    @Override
    public String descricao() {
        return "Mesa Externa #" + getNumero() + " | Coberta: " + (coberta ? "Sim" : "Nao");
    }
}
