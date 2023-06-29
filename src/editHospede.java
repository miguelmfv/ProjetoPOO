import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

public class editHospede extends JFrame implements ActionListener {
    private int action;
    private HospedeDAO dao;
    private JButton salvar = new JButton("Salvar!");
    private JButton cancelar = new JButton("Cancelar!");
    private JLabel titulo = new JLabel("Inscrição de hóspedes");
    private JPanel header = new JPanel();
    private JPanel body = new JPanel();
    private JPanel bottom = new JPanel();
    private  JLabel id = new JLabel("ID: ");
    private JTextField idText = new JTextField(5);
    private   JLabel nome = new JLabel("Nome: ");
    private     JTextField nomeText = new JTextField(20);
    private    JLabel CPF = new JLabel("CPF: ");
    private    JTextField cpfText = new JTextField(11);
    private   JLabel telefone = new JLabel("Telefone");
    private   JTextField teleText = new JTextField(10);
    public editHospede(int acao, Hospede hos){
        this.action = acao;
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        idText.setEditable(false);
        if (acao == 2){ //alterar
            idText.setText(String.valueOf(hos.getId_hospede()));
            nomeText.setText(hos.getNome());
            cpfText.setText(hos.getCpf());
            teleText.setText(hos.getTelefone());
        }
        if (acao == 1){ //incluir
            id.setVisible(false);
            idText.setVisible(false);
        }
        header.add(titulo);
        titulo.setFont(new Font("Arial",Font.BOLD, 32));
        getContentPane().add(header, BorderLayout.PAGE_START);
        body.add(id);
        body.add(idText);
        body.add(nome);
        body.add(nomeText);
        body.add(CPF);
        body.add(cpfText);
        body.add(telefone);
        body.add(teleText);
        getContentPane().add(body, BorderLayout.CENTER);
        bottom.add(salvar);
        bottom.add(cancelar);
        getContentPane().add(bottom, BorderLayout.PAGE_END);

        nomeText.requestFocus();
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

            dao = new HospedeDAO();
            Hospede hospede = new Hospede();



            hospede.setNome(nomeText.getText());
            hospede.setCpf(cpfText.getText());
            hospede.setTelefone(teleText.getText());

            if (action==1){ //1 incluir
                dao.incluir(hospede);
                this.dispose();
                new TelaHospede();
            }else {
                hospede.setId_hospede(parseInt(idText.getText()));
            }
            hospede.setId_hospede(parseInt(idText.getText()));



            if (action==2){ // 2 alterar
                dao.editar(hospede);
                this.dispose();
                new TelaHospede();
            }
        }
        if (e.getSource()==cancelar){
            this.dispose();
        }
    }
}
