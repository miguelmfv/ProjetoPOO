import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaQuarto extends JFrame implements ActionListener {
    private JTable tabela;
    private JPanel header = new JPanel();
    private JLabel titulo = new JLabel("Quartos");
    private JPanel bottom = new JPanel();
    private JButton incluir = new JButton("Incluir");
    private JButton remover = new JButton("Remover");
    private JButton alterar = new JButton("Alterar");
    private JScrollPane scroll;
    private String[] colunas = {"ID", "Número", "Valor", "Disponibilidade"};
    private TelaQuarto.Modelo modelo = new TelaQuarto.Modelo();
    private List<Quarto> quartos;
    private QuartoDAO dao = new QuartoDAO();



    public TelaQuarto(){
        quartos = dao.listar();
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

    class Modelo extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return quartos.size();
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
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0: return String.valueOf(quartos.get(rowIndex).getId_quarto());
                case 1: return quartos.get(rowIndex).getNumero();
                case 2: return quartos.get(rowIndex).getValor_dia();
                case 3: return quartos.get(rowIndex).isDisponivel();
                default: return "";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Quarto selecQuarto = new Quarto();
        int selected = tabela.getSelectedRow();

        if (selected != -1){
            selecQuarto.setId_quarto(Integer.parseInt(tabela.getValueAt(selected, 0).toString()));
            selecQuarto.setNumero(Integer.parseInt(tabela.getValueAt(selected, 1).toString()));
            selecQuarto.setValor_dia(Float.parseFloat(tabela.getValueAt(selected,2).toString()));
            selecQuarto.setDisponivel(Boolean.parseBoolean(tabela.getValueAt(selected,2).toString()));
        }
        if (e.getSource()==incluir){
                new editQuarto(1, null);
                this.dispose();
        }
        if (e.getSource()==alterar){
            if (selected!= -1){
                new editQuarto(2, selecQuarto);
                this.dispose();
            }else System.out.println("Precisa selecionar uma row");
        }
        if (e.getSource()==remover){
            if (selected!= -1){
                dao.excluir(selecQuarto.getId_quarto());
                this.dispose();
                new TelaQuarto();
            }else System.out.println("Precisa selecionar uma row");
        }
    }
}
