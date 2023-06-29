import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadiaDAO {
    private static final String IP       = "82.180.153.65";
    private static final String USUARIO  = "u142008557_jm";
    private static final String SENHA    = "Aula01Aula01!";
    private static final String BANCO    = "u142008557_jm";
    private static final String URL_JDBC = "jdbc:mysql://"+ IP + "/" + BANCO;

    Connection conexao = null;
    PreparedStatement comando = null;
    ResultSet resultado = null;

    public EstadiaDAO(){
        try {
            conexao = DriverManager.getConnection(URL_JDBC, USUARIO, SENHA);
        }catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }

    public void incluir(Estadia estadia){
        try {
            comando = conexao.prepareStatement("INSERT INTO Estadia VALUES(NULL, ?, ?, ?, ?, false);");
            comando.setInt(1, estadia.getId_hospede());
            comando.setInt(2, estadia.getId_quarto());
            comando.setDate(3, new java.sql.Date(estadia.getData_inicio().getTime()) );
            comando.setDate(4, new java.sql.Date(estadia.getData_fim().getTime()) );
            //comando.setBoolean(5, estadia.isConcluida());



            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("SQL Erro: "+e.getMessage());
        }
    }


    public void excluir(int id){
        try {
            comando = conexao.prepareStatement("DELETE FROM Estadia WHERE id_estadia = ?");
            comando.setInt(1, id);

            comando.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public void editar(Estadia estadia) {
        try {
            comando = conexao.prepareStatement("UPDATE Estadia SET id_hospede = ?, id_quarto = ?, data_inicio = ?, data_fim = ?, concluida = ? WHERE id_estadia = ?;");
            comando.setInt(1, estadia.getId_hospede());
            comando.setInt(2, estadia.getId_quarto());
            comando.setDate(3, new java.sql.Date(estadia.getData_inicio().getTime()));
            comando.setDate(4, new java.sql.Date(estadia.getData_fim().getTime()));
            comando.setBoolean(5, estadia.isConcluida());
            comando.setInt(6, estadia.getId_estadia());

            comando.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        }
    }

    public void concluir(int id){
        try{
            comando = conexao.prepareStatement("UPDATE Estadia SET concluida = true WHERE id_estadia = ?;");
            comando.setInt(1, id);

            comando.executeUpdate();
        } catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }
    }

    public List<Estadia> listar(){
        List<Estadia> estadias = new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Estadia ;");

            ResultSet rs = comando.executeQuery();

            while (rs.next()){
                Estadia estadia = new Estadia();
                estadia.setId_estadia(rs.getInt("id_estadia"));
                estadia.setId_hospede(rs.getInt("id_hospede"));
                estadia.setId_quarto(rs.getInt("id_quarto"));
                estadia.setData_fim(rs.getDate("data_fim"));
                estadia.setData_inicio(rs.getDate("data_inicio"));
                estadia.setValor(this.valorEstadia(estadia.getId_estadia()));

                estadias.add(estadia);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }

        return estadias;
}

    public List<Estadia> listaPorHospede(int id_hospede){
        List<Estadia> estadias = new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Estadia JOIN Quarto ON Estadia.id_quarto = Quarto.id_quarto JOIN Hospede ON Estadia.id_hospede = Hospede.id_hospede WHERE Estadia.id_hospede = ?;");
            comando.setInt(1, id_hospede);

            resultado = comando.executeQuery();

            while (resultado.next()){
                Estadia estadia = new Estadia();
                estadia.setId_estadia(resultado.getInt("id_estadia"));
                estadia.setId_hospede(resultado.getInt("id_hospede"));
                estadia.setId_quarto(resultado.getInt("id_quarto"));
                estadia.setData_fim(resultado.getDate("data_fim"));
                estadia.setData_inicio(resultado.getDate("data_inicio"));
                estadia.setNome_hospede(resultado.getString("nome"));
                estadia.setNumero_quarto(resultado.getInt("numero"));
                estadia.setConcluida(resultado.getBoolean("concluida"));

                estadias.add(estadia);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }

        return estadias;
    }

    public List<Estadia> listaPorQuarto(int id_quarto){
        List<Estadia> estadias = new ArrayList<>();

        try {
            comando = conexao.prepareStatement("SELECT * FROM Estadia JOIN Quarto ON Estadia.id_quarto = Quarto.id_quarto JOIN Hospede ON Estadia.id_hospede = Hospede.id_hospede WHERE Estadia.id_quarto = ?;");
            comando.setInt(1, id_quarto);

            resultado = comando.executeQuery();

            while (resultado.next()){
                Estadia estadia = new Estadia();
                estadia.setId_estadia(resultado.getInt("id_estadia"));
                estadia.setId_hospede(resultado.getInt("id_hospede"));
                estadia.setId_quarto(resultado.getInt("id_quarto"));
                estadia.setData_fim(resultado.getDate("data_fim"));
                estadia.setData_inicio(resultado.getDate("data_inicio"));
                estadia.setNome_hospede(resultado.getString("nome"));
                estadia.setNumero_quarto(resultado.getInt("numero"));
                estadia.setConcluida(resultado.getBoolean("concluida"));

                estadias.add(estadia);
            }
        }catch (SQLException e){
            System.out.println("Erro SQL: "+e.getMessage());
        }

        return estadias;
    }

    public float valorEstadia(int id_estadia) {

        Float result = null;
        try {
            comando = conexao.prepareStatement("SELECT valor_dia * (data_fim - data_inicio) as Valor_Total FROM Estadia JOIN Quarto ON Estadia.id_quarto = Quarto.id_quarto JOIN Hospede ON Estadia.id_hospede = Hospede.id_hospede WHERE Estadia.id_estadia = ?;");
            comando.setInt(1, id_estadia);

            ResultSet res = comando.executeQuery();
            if (res.next()){
                result = res.getFloat("valor_total");}

        } catch (SQLException e) {
            System.out.println("Erro SQL: " + e.getMessage());
        }

        return result;
    }
}