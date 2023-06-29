import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class editQuarto extends JFrame implements ActionListener {
    private int action;
    private QuartoDAO dao;
    private JButton salvar = new JButton("Salvar!");
    private JButton cancelar = new JButton("Cancelar!");
    private JLabel titulo = new JLabel("Inscrição de Quartos");
    private JPanel header = new JPanel();
    private JPanel body = new JPanel();
    private JPanel bottom = new JPanel();
    private Boolean[]disp = {true, false};
    private JLabel id = new JLabel("ID: ");
    private JTextField idText = new JTextField(5);
    private JLabel numero = new JLabel("Número: ");
    private JTextField numText = new JTextField(7);
    private JLabel valor = new JLabel("Valor: ");
    private JTextField valText = new JTextField(7);
    private JComboBox<Boolean> dispText = new JComboBox<>(disp);
    private JLabel disponivel = new JLabel("Disponibilidade: ");
    public editQuarto(int acao, Quarto qua) {

        this.action = acao;
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        idText.setEditable(false);

        if (acao == 2){
            idText.setText(String.valueOf(qua.getId_quarto()));
            numText.setText(String.valueOf(qua.getNumero()));
            valText.setText(String.valueOf(qua.getValor_dia()));
            dispText.setSelectedItem(String.valueOf(qua.isDisponivel()));
        }
        if (acao == 1){
            id.setVisible(false);
            idText.setVisible(false);
        }
        header.add(titulo);
        titulo.setFont(new Font("Arial",Font.BOLD, 32));
        getContentPane().add(header, BorderLayout.PAGE_START);
        body.add(id);
        body.add(idText);
        body.add(numero);
        body.add(numText);
        body.add(valor);
        body.add(valText);
        body.add(disponivel);
        body.add(dispText);
        getContentPane().add(body, BorderLayout.CENTER);
        bottom.add(salvar);
        bottom.add(cancelar);
        getContentPane().add(bottom, BorderLayout.PAGE_END);

        numText.requestFocus();
        salvar.addActionListener(this);
        cancelar.addActionListener(this);
        if (acao == 1) {
            salvar.setText("Incluir");
        }else {
            salvar.setText("Alterar");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==salvar){
            dao = new QuartoDAO();
            Quarto quarto = new Quarto();

            quarto.setNumero(Integer.parseInt(numText.getText()));
            quarto.setValor_dia(Float.parseFloat(valText.getText()));
            quarto.setDisponivel((Boolean) dispText.getSelectedItem());

            //1 incluir
            if (action==1) {
                try {
                    dao.incluir(quarto);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                this.dispose();
                new TelaQuarto();
            }
            else {
                quarto.setId_quarto(Integer.parseInt(idText.getText()));
            }
            quarto.setId_quarto(Integer.parseInt(idText.getText()));
            if (action==2){ // 2 alterar
                dao.editar(quarto);
                this.dispose();
                new TelaQuarto();
            }
        }
        if (e.getSource()==cancelar){
            this.dispose();
        }
    }
}
