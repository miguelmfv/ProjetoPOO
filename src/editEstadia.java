

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class editEstadia extends JFrame implements ActionListener {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
    String formato = formatter.format(new Date());
    private int action;
    private EstadiaDAO dao;
    private JButton salvar = new JButton("Salvar!");
    private JButton cancelar = new JButton("Cancelar!");
    private JLabel titulo = new JLabel("Adicionar Clientes");
    private JPanel header = new JPanel();
    private JPanel body = new JPanel();
    private JPanel bottom = new JPanel();
    private JLabel id_estadia = new JLabel("ID Estadia");
    private JTextField idestText = new JTextField(5);
    private JLabel id_hospede = new JLabel("ID Hóspede");
    private JTextField idhosText = new JTextField(5);
    private JLabel id_quarto = new JLabel("ID Quarto");
    private JTextField idquaText = new JTextField(5);
    private JLabel dataStart = new JLabel("Data início");
    private JTextField startText = new JTextField(7);
    private JLabel dataEnd = new JLabel("Data fim");
    private JTextField endText = new JTextField(7);
    public editEstadia(int acao, Estadia est){
        this.action = acao;
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        idestText.setEditable(false);

        if (acao == 2){
            idestText.setText(String.valueOf(est.getId_estadia()));
            idhosText.setText(String.valueOf(est.getId_hospede()));
            idquaText.setText(String.valueOf(est.getId_quarto()));
            String start = formatter.format(est.getData_inicio());
            startText.setText(start);
            String end = formatter.format(est.getData_fim());
            endText.setText(end);
        }
        if (acao == 1){
            id_estadia.setVisible(false);
            idestText.setVisible(false);
        }
        header.add(titulo);
        titulo.setFont(new Font("Arial",Font.BOLD, 32));
        getContentPane().add(header, BorderLayout.PAGE_START);
        body.add(id_estadia);
        body.add(idestText);
        body.add(id_hospede);
        body.add(idhosText);
        body.add(id_quarto);
        body.add(idquaText);
        body.add(dataStart);
        body.add(startText);
        body.add(dataEnd);
        body.add(endText);
        getContentPane().add(body, BorderLayout.CENTER);
        bottom.add(salvar);
        bottom.add(cancelar);
        getContentPane().add(bottom, BorderLayout.PAGE_END);

        dataStart.requestFocus();
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
        if (e.getSource()==salvar) {
            dao = new EstadiaDAO();
            Date datainic;
            Date datafim;
            Estadia estadia = new Estadia();

            estadia.setId_hospede(Integer.parseInt(idhosText.getText()));
            estadia.setId_quarto(Integer.parseInt(idquaText.getText()));

            try {
                //formatter
                datainic = formatter.parse(String.valueOf(startText.getText()));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            try {
                datafim = formatter.parse(String.valueOf(endText.getText()));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            estadia.setData_inicio(datainic);
            estadia.setData_fim(datafim);



            //1 incluir
            if (action == 1) {
                dao.incluir(estadia);
                QuartoDAO qdao = new QuartoDAO();
                qdao.ocuparQuarto(estadia.getId_quarto());
                this.dispose();
                new TelaEstadia();
            }
            estadia.setId_estadia(Integer.parseInt(idestText.getText()));
            if (action == 2) { // 2 alterar
                dao.editar(estadia);
                this.dispose();
                new TelaEstadia();
            }
        }
        if (e.getSource()==cancelar){
            this.dispose();
        }
    }
}
