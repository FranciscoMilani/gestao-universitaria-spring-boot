package br.ucs.ffmilani.GestaoUni;

import br.ucs.ffmilani.GestaoUni.dao.MatriculaRepository;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
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
    MatriculaService matriculaService;


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
                String out = matriculaService.matricularAluno(new MatriculaDTO(tfEmail.getText(), tfSigla.getText(), tfSemestre.getText()));
                lbStatus.setText(out);
            }
        });
    }
}
