package br.ucs.ffmilani.GestaoUni;

import br.ucs.ffmilani.GestaoUni.controller.web.MatriculaController;
import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
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
    @Autowired
    MatriculaRepository matriculaRepo;

    @Autowired
    MatriculaController matriculaController;


    public MainFrame(){
        super("Gestão Universitária");
        setContentPane(pMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var m = matriculaRepo.findById(Integer.parseInt(tfEmail.getText()));
                System.out.println(m.get().toString());
            }
        });
    }
}
