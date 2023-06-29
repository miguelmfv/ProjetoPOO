import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class TelaEstadia extends JFrame implements ActionListener {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private JPanel header = new JPanel();
    private JPanel bottom = new JPanel();
    private JScrollPane scroll;
    private JLabel titulo = new JLabel("Estadias!");
    JTable tabela;
    private JButton incluir = new JButton("Incluir");
    private JButton alterar = new JButton("Alterar");
    private JButton remover = new JButton("Remover");
    private Date data;

    String colunas[] = {"ID Estadia", "Hospede", "Quarto", "Inicio", "Fim", "Valor Total"};
    private TelaEstadia.Modelo modelo = new Modelo();
    private List<Estadia> estadias;
    private EstadiaDAO dao = new EstadiaDAO();
    public TelaEstadia(){
        estadias = dao.listar();
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

        Estadia estselected = new Estadia();
        int selected = tabela.getSelectedRow();


        if (selected != -1) {

            estselected.setId_estadia(Integer.parseInt(tabela.getValueAt(selected, 0).toString()));
            estselected.setId_hospede(Integer.parseInt(tabela.getValueAt(selected, 1).toString()));
            estselected.setId_quarto(Integer.parseInt(tabela.getValueAt(selected, 2).toString()));
            Date dateini;
            Date datefim;
            if (e.getSource()==remover){
                if (selected!= -1){
                    dao.excluir(estselected.getId_estadia());
                    this.dispose();
                    new TelaEstadia();
                } else {
                    System.out.println("Precisa selecionar uma row");
                }
            }
            try {

                dateini = formatter.parse(String.valueOf(tabela.getValueAt(selected, 3)));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            try {
                datefim = formatter.parse(String.valueOf(tabela.getValueAt(selected, 4)));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            estselected.setData_inicio(dateini);
            estselected.setData_fim(datefim);
        }
        if (e.getSource()==incluir){
            new editEstadia(1, null);
            this.dispose();
        }
        if (e.getSource()==alterar){
            if (selected!= -1){
                new editEstadia(2, estselected);
                this.dispose();
            }else {
                System.out.println("Precisa selecionar uma row");
            }

        }


    }

        class Modelo extends AbstractTableModel{
        @Override
        public int getRowCount() {
            return estadias.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0: return estadias.get(rowIndex).getId_estadia();
                case 1: return estadias.get(rowIndex).getId_hospede();
                case 2: return estadias.get(rowIndex).getId_quarto();
                case 3: {
                    String datainic = formatter.format(estadias.get(rowIndex).getData_inicio());
                    return datainic;
                }
                case 4: {
                    String datafim = formatter.format(estadias.get(rowIndex).getData_fim());
                    return datafim;
                }
                case 5: {
                    return String.valueOf(estadias.get(rowIndex).getValor());
                }
                default: return "";
            }
        }
    }
}
