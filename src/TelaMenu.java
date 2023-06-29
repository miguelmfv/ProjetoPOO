import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMenu extends JFrame implements ActionListener {
    private JButton menuQuarto = new JButton("Verificar Quartos");
    private JButton menuHospede = new JButton("Ver Hospede");
    private JButton menuEstadia = new JButton("Verificar Reserva");



    public TelaMenu(){
        setTitle("Hotelaria!");
        setLayout(new GridLayout(1,3,3,3));
        getContentPane().add(menuQuarto);
        getContentPane().add(menuHospede);
        getContentPane().add(menuEstadia);
        menuEstadia.setBackground(Color.LIGHT_GRAY);
        menuQuarto.setBackground(Color.LIGHT_GRAY);
        menuHospede.setBackground(Color.LIGHT_GRAY);
        setSize(1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        menuHospede.addActionListener(this);
        menuQuarto.addActionListener(this);
        menuEstadia.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==menuEstadia){
            new TelaEstadia();
        }
        if(e.getSource()==menuHospede){
            new TelaHospede();
        }
        if (e.getSource()==menuQuarto){
            new TelaQuarto();
        }
    }
}
