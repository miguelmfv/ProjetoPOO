import java.util.Objects;

public class Hospede {
    private int id_hospede;
    private String nome;
    private String cpf;
    private String telefone;
    public int getId_hospede() {
        return id_hospede;
    }
    public void setId_hospede(int id_hospede) {
        this.id_hospede = id_hospede;
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
        this.cpf = cpf;
    }
    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}
    @Override
    public int hashCode() {
        return Objects.hash(id_hospede);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hospede other = (Hospede) obj;
        return id_hospede == other.id_hospede;
    }
}
