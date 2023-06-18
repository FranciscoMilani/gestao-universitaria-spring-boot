package br.ucs.ffmilani.GestaoUni.Swing;

import br.ucs.ffmilani.GestaoUni.controller.swing.RelatorioController;
import br.ucs.ffmilani.GestaoUni.model.AlunoDTO;
import br.ucs.ffmilani.GestaoUni.model.CursoDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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
    private JComboBox relatorioComboBox;

    @Autowired
    public MainFrame(MatriculaService matriculaService, RelatorioService relatorioService){
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

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> lista = Arrays.stream(tfSigla.getText().split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .toList();

                String outMessage = matriculaService.matricularAluno(new MatriculaDTO(tfEmail.getText(), tfSigla.getText(), tfSemestre.getText()), lista);
                lbStatus.setText(outMessage);
            }
        });

//        relatorioComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selected = relatorioComboBox.getSelectedItem().toString();
//                List<String> headers;
//
//                switch (selected) {
//                    case "Alunos" -> {
//                        headers = Arrays.asList("Nome", "Email", "Curso");
//                        List<AlunoDTO> alunos = relatorioService.listaAlunos();
//                        mostraRelatorio(headers, alunos, obj -> new Object[]{obj.nome(), obj.email(), obj.nomeCurso()});
//                    }
//                    case "Matriculas" -> {
//                        headers = Arrays.asList("Aluno", "Disciplina", "Semestre");
//                        List<MatriculaDTO> matriculas = matriculaService.listarMatriculas();
//                        mostraRelatorio(headers, matriculas, obj -> new Object[]{obj.nomeAluno(), obj.nomeDisciplina(), obj.semestre()});
//                    }
//                    case "Cursos" -> {
//                        headers = Arrays.asList("Curso", "Carga Horária", "Disciplinas");
//                        List<CursoDTO> cursos = relatorioService.listaCursos();
//                        mostraRelatorio(headers, cursos, obj -> new Object[]{obj.nome(), obj.cargaHoraria(), Arrays.deepToString(obj.disciplinas().toArray())});
//                    }
//                    case "Disciplinas" -> {
//                        headers = Arrays.asList("Nome", "Sigla", "Créditos", "Carga Horária");
//                        List<Disciplina> disciplinas = relatorioService.listaDisciplinas();
//                        mostraRelatorio(headers, disciplinas, obj -> new Object[]{obj.getNome(), obj.getSigla(), obj.getCreditos(), obj.getCargahoraria()});
//                    }
//                }
//            }
//        });
    }

    @PostConstruct
    public void initialize() {
        inicializaComboBox();
        inicializaTable();
    }


    private void inicializaComboBox() {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addAll(Arrays.asList(
                "Matriculas",
                "Alunos",
                "Cursos",
                "Disciplinas"
        ));

        relatorioComboBox.dispatchEvent(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, ""));
        relatorioComboBox.setModel(comboBoxModel);
        relatorioComboBox.setSelectedIndex(0);
    }

//    private <T> void mostraRelatorio(List<String> headers, List<T> data,  Function<T, Object[]> mappingFunction){
//        Object[][] dataArr = new Object[data.size()][];
//        String[] headerArr = headers.toArray(new String[0]);
//
//        for (int i = 0; i < data.size(); i++) {
//            T obj = data.get(i);
//            dataArr[i] = mappingFunction.apply(obj);
//        }
//
//        DefaultTableModel tableModel = new DefaultTableModel(dataArr, headerArr);
//        relatorioTable.setModel(tableModel);
//    }

    private void inicializaTable(){
        relatorioTable.setEnabled(false);
    }
}
