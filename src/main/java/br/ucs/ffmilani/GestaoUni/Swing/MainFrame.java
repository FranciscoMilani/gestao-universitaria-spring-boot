package br.ucs.ffmilani.GestaoUni.Swing;

import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
@Getter
public class MainFrame extends JFrame {
    private JTextField tfEmail;
    private JTextField tfSigla;
    private JTextField tfSemestre;
    private JButton btnCadastrar;
    private JLabel lbStatus;
    private JTabbedPane tbMatricular;
    private JPanel pMatricular;
    private JPanel pRelatorio;
    private JPanel pMain;
    private JTable relatorioTable;
    private JComboBox<String> relatorioComboBox;

    public MainFrame(){
        super("Gestão Universitária");
        setContentPane(pMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500, 500);
        this.pack();
        this.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
