import java.util.Objects;

public class Quarto {
    private int id_quarto;
    private int numero;
    private float valor_dia;
    private boolean disponivel;
    public int getId_quarto() {
        return id_quarto;
    }
    public void setId_quarto(int id_quarto) {
        this.id_quarto = id_quarto;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public float getValor_dia() {
        return valor_dia;
    }
    public void setValor_dia(float valor_dia) {
        this.valor_dia = valor_dia;
    }
    public boolean isDisponivel() {
        return disponivel;
    }
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id_quarto);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Quarto other = (Quarto) obj;
        return id_quarto == other.id_quarto;
    }


}

