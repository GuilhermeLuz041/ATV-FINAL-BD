package models;

import java.time.LocalDate;

public class Reserva {
    
    private int id;
    private Livro livro;
    private Membro membro;
    private LocalDate dataReserva;

    public Reserva(LocalDate dataReserva, int id, Livro livro, Membro membro) {
        this.dataReserva = dataReserva;
        this.id = id;
        this.livro = livro;
        this.membro = membro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }

    @Override
    public String toString() {
        return "Reserva{id=" + id + ", livro=" + livro + ", membro=" + membro + ", dataReserva=" + dataReserva + "}";
    }

    
}
