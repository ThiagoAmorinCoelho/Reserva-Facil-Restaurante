package restaurante.modelo;

public class Funcionario extends Pessoa {
    private String cargo;
    private String matricula;

    public Funcionario(String nome, String cpf, String telefone, Endereco endereco, String cargo, String matricula) {
        super(nome, cpf, telefone, endereco);
        this.cargo = cargo;
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String exibirDados() {
        return "Funcionario: " + getNome() + " | CPF: " + getCpf() + " | Cargo: " + cargo
                + " | Matricula: " + matricula;
    }

    @Override
    public String toString() {
        return exibirDados();
    }
}
