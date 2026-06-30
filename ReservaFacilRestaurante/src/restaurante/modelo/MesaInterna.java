package restaurante.modelo;

public class MesaInterna extends Mesa {
    private String setor;

    public MesaInterna(int numero, int capacidade, String setor) {
        super(numero, capacidade);
        this.setor = setor;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public String descricao() {
        return "Mesa Interna #" + getNumero() + " | Setor: " + setor;
    }
}
