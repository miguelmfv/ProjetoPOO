import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDAO {
    private static final String IP       = "82.180.153.65";
    private static final String USUARIO  = "u142008557_jm";
    private static final String SENHA    = "Aula01Aula01!";
    private static final String BANCO    = "u142008557_jm";
    private static final String URL_JDBC = "jdbc:mysql://"+ IP + "/" + BANCO;

    Connection conexao = null;
    PreparedStatement comando = null;
    ResultSet resultado = null;

    public HospedeDAO(){
        try {
            conexao = DriverManager.getConnection(URL_JDBC, USUARIO, SENHA);
        }catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }

    public void incluir(Hospede hospede){
        try{
            comando = conexao.prepareStatement("INSERT INTO Hospede VALUES(NULL, ?, ?, ?);");
            comando.setString(1, hospede.getNome());
            comando.setString(2, hospede.getCpf());
            comando.setString(3, hospede.getTelefone());

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public void excluir(int id){
        try {
            comando = conexao.prepareStatement("DELETE FROM Hospede WHERE id_hospede = ?");
            comando.setInt(1, id);

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }


    public void editar(Hospede hospede){
        try {
            comando = conexao.prepareStatement("UPDATE Hospede SET nome = ?, cpf = ?, telefone = ? WHERE id_hospede = ?;");
            comando.setString(1, hospede.getNome());
            comando.setString(2, hospede.getCpf());
            comando.setString(3, hospede.getTelefone());
            comando.setInt(4, hospede.getId_hospede());

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public List<Hospede> listar(){
        List<Hospede> hospedes = new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Hospede");

            resultado = comando.executeQuery();

            while (resultado.next()){
                Hospede hospede = new Hospede();
                hospede.setId_hospede(resultado.getInt("id_hospede"));
                hospede.setCpf(resultado.getString("cpf"));
                hospede.setNome(resultado.getString("nome"));

                hospede.setTelefone(resultado.getString("telefone"));

                hospedes.add(hospede);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }

        return hospedes;
    }

    public List<Hospede> listarPorNome(String nome){
        List<Hospede> hospedes = new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Hospede WHERE nome like ?;");
            comando.setString(1, "%"+nome+"%");

            resultado = comando.executeQuery();

            while (resultado.next()){
                Hospede hospede = new Hospede();
                hospede.setId_hospede(resultado.getInt("id_hospede"));
                hospede.setCpf(resultado.getString("cpf"));
                hospede.setNome(resultado.getString("nome"));

                hospede.setTelefone(resultado.getString("telefone"));

                hospedes.add(hospede);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }

        return hospedes;
    }
}