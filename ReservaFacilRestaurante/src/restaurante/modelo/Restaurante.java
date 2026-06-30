package restaurante.modelo;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nome;
    private Endereco endereco;
    private ArrayList<Mesa> mesas;

    public Restaurante(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.mesas = new ArrayList<Mesa>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void adicionarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public Mesa buscarMesaPorNumero(int numero) {
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numero) {
                return mesa;
            }
        }
        return null;
    }

    public void listarMesas() {
        if (mesas.isEmpty()) {
            System.out.println("Nenhuma mesa cadastrada.");
            return;
        }

        for (Mesa mesa : mesas) {
            System.out.println(mesa);
        }
    }

    @Override
    public String toString() {
        return nome + " | Endereco: " + endereco;
    }
}
