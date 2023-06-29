import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {

    private static final String IP       = "82.180.153.65";
    private static final String USUARIO  = "u142008557_jm";
    private static final String SENHA    = "Aula01Aula01!";
    private static final String BANCO    = "u142008557_jm";
    private static final String URL_JDBC = "jdbc:mysql://"+ IP + "/" + BANCO;

    Connection conexao = null;
    PreparedStatement comando = null;
    ResultSet resultado = null;

    public QuartoDAO(){
        try {
            conexao = DriverManager.getConnection(URL_JDBC, USUARIO, SENHA);
        }catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }

    public void incluir(Quarto quarto) throws SQLException {
        try {
            comando = conexao.prepareStatement("INSERT INTO Quarto VALUES (NULL, ?, ?, TRUE);");
            comando.setInt(1, quarto.getNumero());
            comando.setFloat(2, quarto.getValor_dia());

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public void excluir(int id){
        try{
            comando = conexao.prepareStatement("DELETE FROM Quarto WHERE id_quarto = ?;");
            comando.setInt(1, id);

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public void editar(Quarto quarto){
        try {
            comando = conexao.prepareStatement("UPDATE Quarto SET numero = ?, valor_dia = ?, disponivel = ? WHERE id_quarto = ?;");
            comando.setInt(1, quarto.getNumero());
            comando.setFloat(2, quarto.getValor_dia());
            comando.setBoolean(3, quarto.isDisponivel());
            comando.setInt(4, quarto.getId_quarto());

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public void tornarDisponivel(int id){
        try {
            comando = conexao.prepareStatement("UPDATE Quarto SET disponivel = true WHERE id_quarto = ?;");
            comando.setInt(1, id);

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public void ocuparQuarto(int id){
        try {
            comando = conexao.prepareStatement("UPDATE Quarto SET disponivel = false WHERE id_quarto =?;");
            comando.setInt(1, id);

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public  List<Quarto> listar(){
        List<Quarto> quartos= new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Quarto;");

            resultado = comando.executeQuery();

            while (resultado.next()){
                Quarto quarto = new Quarto();
                quarto.setId_quarto(resultado.getInt("id_quarto"));
                quarto.setDisponivel(resultado.getBoolean("disponivel"));
                quarto.setNumero(resultado.getInt("numero"));
                quarto.setValor_dia(resultado.getFloat("valor_dia"));

                quartos.add(quarto);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }

        return quartos;
    }

    public List<Quarto> verDisponiveis(){
        List<Quarto> quartos= new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Quarto WHERE disponivel = TRUE;");

            resultado = comando.executeQuery();

            while (resultado.next()){
                Quarto quarto = new Quarto();
                quarto.setId_quarto(resultado.getInt("id_quarto"));
                quarto.setDisponivel(resultado.getBoolean("disponivel"));
                quarto.setNumero(resultado.getInt("numero"));
                quarto.setValor_dia(resultado.getFloat("valor_dia"));

                quartos.add(quarto);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
        return quartos;
    }

    public int conferirID(Quarto quarto){
        int res = 0;
        try {
            comando = conexao.prepareStatement("SELECT id_quarto FROM Quarto WHERE numero=?");
            comando.setInt(1, quarto.getNumero());

            res = comando.executeQuery().getInt("id_quarto");

        }catch(SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
        return res;
    }
}