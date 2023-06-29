import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class TelaHospede extends JFrame implements ActionListener {

    private JTable tabela;
    private JPanel header = new JPanel();
    private JLabel titulo = new JLabel("Hóspedes");
    private JPanel bottom = new JPanel();
    private JButton incluir = new JButton("Incluir");
    private JButton remover = new JButton("Remover");
    private JButton alterar = new JButton("Alterar");
    private JScrollPane scroll;
    private String[] colunas = {"ID", "Nome", "CPF", "Telefone"};
    private Modelo modelo = new Modelo();
    private List<Hospede> hospedes;
    private HospedeDAO dao = new HospedeDAO();



    public TelaHospede(){
        hospedes = dao.listar();
        tabela = new JTable(modelo);
        scroll = new JScrollPane(tabela);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        header.add(titulo);
        bottom.add(incluir);
        bottom.add(alterar);
        bottom.add(remover);

        getContentPane().add(header, BorderLayout.PAGE_START);
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.PAGE_END);

        incluir.addActionListener(this);
        alterar.addActionListener(this);
        remover.addActionListener(this);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Hospede hoSelected = new Hospede();
        int selected = tabela.getSelectedRow();

        if (selected != -1){
            hoSelected.setId_hospede(Integer.parseInt(tabela.getValueAt(selected, 0).toString()));
            hoSelected.setNome(tabela.getValueAt(selected, 1).toString());
            hoSelected.setCpf(tabela.getValueAt(selected,2).toString());
            hoSelected.setTelefone(tabela.getValueAt(selected, 3).toString());
        }
        if (e.getSource()==incluir){
            new editHospede(1, null); //incluir 1 // alterar 2
            this.dispose();
        }
        if (e.getSource()==alterar){
            if (selected!= -1){
                new editHospede(2, hoSelected);
                this.dispose();
            }else {
                System.out.println("Precisa selecionar uma row");
            }

        }
        if (e.getSource()==remover){
            if (selected!= -1){
                dao.excluir(hoSelected.getId_hospede());
                this.dispose();
                new TelaHospede();
            } else {
                System.out.println("Precisa selecionar uma row");
            }
        }

    }

    class Modelo extends AbstractTableModel {
        @Override
        public int getRowCount() {

            return hospedes.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public String getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.valueOf(hospedes.get(rowIndex).getId_hospede());
                case 1:
                    return hospedes.get(rowIndex).getNome();
                case 2:
                    return hospedes.get(rowIndex).getCpf();
                case 3:
                    return String.valueOf(hospedes.get(rowIndex).getTelefone());
                default:
                    return "";
            }


        }
    }
}
