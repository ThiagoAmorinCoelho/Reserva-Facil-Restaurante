package restaurante.excecao;

public class MesaIndisponivelException extends Exception {
    public MesaIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
