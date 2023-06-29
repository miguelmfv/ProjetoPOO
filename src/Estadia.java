import java.util.Date;
import java.util.Objects;

public class Estadia {
    private int id_estadia;
    private int id_hospede;
    private int id_quarto;
    private Date data_inicio;
    private Date data_fim;
    private boolean concluida;

    private float valor;

    private String nome_hospede;

    private int numero_quarto;

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNome_hospede() {
        return nome_hospede;
    }

    public void setNome_hospede(String nome_hospede) {
        this.nome_hospede = nome_hospede;
    }

    public int getNumero_quarto() {
        return numero_quarto;
    }

    public void setNumero_quarto(int numero_quarto) {
        this.numero_quarto = numero_quarto;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public int getId_estadia() {
        return id_estadia;
    }
    public void setId_estadia(int id_estadia) {
        this.id_estadia = id_estadia;
    }
    public int getId_hospede() {
        return id_hospede;
    }
    public void setId_hospede(int id_hospede) {
        this.id_hospede = id_hospede;
    }
    public int getId_quarto() {
        return id_quarto;
    }
    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }
    public Date getData_inicio() {
        return data_inicio;
    }
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }
    public Date getData_fim() {
        return data_fim;
    }
    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id_estadia);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estadia other = (Estadia) obj;
        return id_estadia == other.id_estadia;
    }


}