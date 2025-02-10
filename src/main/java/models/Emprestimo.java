package models;

import java.time.LocalDate;

public class Emprestimo {
    
    private int id;
    private Livro livro;
    private Membro membro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(LocalDate dataDevolucao, LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao, int id, Livro livro, Membro membro) {
        this.dataDevolucao = dataDevolucao;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
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

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isAtrasado() {
        if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Emprestimo{id=" + id + ", livro=" + livro.getTitulo() + ", membro=" + membro.getNome() + ", dataEmprestimo=" + dataEmprestimo + ", dataPrevistaDevolucao=" + dataPrevistaDevolucao + ", dataDevolucao=" + dataDevolucao + "}";
    }

    
}

