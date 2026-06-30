package restaurante.controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import restaurante.modelo.Reserva;

public class ControleReserva<T> {
    private T codigoControle;
    private ArrayList<Reserva> listaReservas;

    public ControleReserva(T codigoControle) {
        this.codigoControle = codigoControle;
        this.listaReservas = new ArrayList<Reserva>();
    }

    public T getCodigoControle() {
        return codigoControle;
    }

    public void setCodigoControle(T codigoControle) {
        this.codigoControle = codigoControle;
    }

    public void adicionarReserva(Reserva reserva) {
        listaReservas.add(reserva);
    }

    public List<Reserva> getListaReservas() {
        return listaReservas;
    }

    public Reserva buscarReservaPorCodigo(int codigo) {
        for (Reserva reserva : listaReservas) {
            if (reserva.getCodigo() == codigo) {
                return reserva;
            }
        }
        return null;
    }

    public void imprimirReservas() {
        ArrayList<Reserva> copia = new ArrayList<Reserva>(listaReservas);
        Collections.sort(copia);

        if (copia.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        for (Reserva reserva : copia) {
            System.out.println(reserva);
        }
    }
}
