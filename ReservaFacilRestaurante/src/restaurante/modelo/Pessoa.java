package restaurante.modelo;

public abstract class Pessoa implements Verificavel {
    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;

    public Pessoa(String nome, String cpf, String telefone, Endereco endereco) {
        this.nome = nome;
        setCpf(cpf);
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validar(cpf)) {
            this.cpf = cpf;
        } else {
            solicitarNovo();
            throw new IllegalArgumentException("CPF invalido: " + cpf);
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean validar(String codigo) {
        if (codigo == null) {
            return false;
        }

        String numeros = codigo.replaceAll("[^0-9]", "");
        if (numeros.length() != 11) {
            return false;
        }

        char primeiro = numeros.charAt(0);
        boolean todosIguais = true;
        for (int i = 1; i < numeros.length(); i++) {
            if (numeros.charAt(i) != primeiro) {
                todosIguais = false;
                break;
            }
        }
        return !todosIguais;
    }

    @Override
    public void solicitarNovo() {
        System.out.println("CPF invalido. Informe um novo CPF no formato 000.000.000-00.");
    }

    public abstract String exibirDados();
}
