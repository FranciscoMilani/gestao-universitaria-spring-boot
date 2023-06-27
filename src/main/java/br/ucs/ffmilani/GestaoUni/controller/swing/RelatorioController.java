package br.ucs.ffmilani.GestaoUni.controller.swing;

import br.ucs.ffmilani.GestaoUni.Swing.MainFrame;
import br.ucs.ffmilani.GestaoUni.model.DTO.AlunoDTO;
import br.ucs.ffmilani.GestaoUni.model.DTO.CursoDTO;
import br.ucs.ffmilani.GestaoUni.model.Disciplina;
import br.ucs.ffmilani.GestaoUni.model.DTO.MatriculaDTO;
import br.ucs.ffmilani.GestaoUni.model.Universidade;
import br.ucs.ffmilani.GestaoUni.repository.UniversidadeRepository;
import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
import br.ucs.ffmilani.GestaoUni.service.RelatorioService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Controller
@AllArgsConstructor
public class RelatorioController extends AbstractFrameController {

    private MainFrame mainFrame;
    private MatriculaService matriculaService;
    private RelatorioService relatorioService;
    private UniversidadeRepository uniRepo;

    @PostConstruct
    private void prepareListeners(){
        registerAction(mainFrame.getRelatorioComboBox(), e -> selecionaRelatorio());
    }

    @Override
    public void prepareAndOpen() {
        inicializaComboBox();
        inicializaTable();
    }

    private void inicializaComboBox() {
        JComboBox<String> comboBox = mainFrame.getRelatorioComboBox();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addAll(Arrays.asList(
                "Universidades",
                "Matriculas",
                "Alunos",
                "Cursos",
                "Disciplinas"
        ));

        comboBox.dispatchEvent(new ActionEvent(new Object(), ActionEvent.ACTION_PERFORMED, ""));
        comboBox.setModel(comboBoxModel);
        comboBox.setSelectedIndex(0);
    }

    private void inicializaTable(){
        mainFrame.getRelatorioTable().setEnabled(false);
    }

    private void selecionaRelatorio() {
        String selected = mainFrame.getRelatorioComboBox()
                .getSelectedItem()
                .toString();

        List<String> headers;

        switch (selected) {
            case "Universidades" -> {
                headers = Arrays.asList("Id", "Sigla", "Nome");
                List<Universidade> universidades = (List<Universidade>) uniRepo.findAll();
                mostraRelatorio(headers, universidades, obj -> new Object[]{obj.getId(), obj.getSigla(), obj.getNome()});
            }
            case "Alunos" -> {
                headers = Arrays.asList("Nome", "Email", "Curso");
                List<AlunoDTO> alunos = relatorioService.listaAlunos();
                mostraRelatorio(headers, alunos, obj -> new Object[]{obj.nome(), obj.email(), obj.nomeCurso()});
            }
            case "Matriculas" -> {
                headers = Arrays.asList("Aluno", "Disciplina", "Semestre");
                List<MatriculaDTO> matriculas = matriculaService.listarMatriculas();
                mostraRelatorio(headers, matriculas, obj -> new Object[]{obj.nomeAluno(), obj.nomeDisciplina(), obj.semestre()});
            }
            case "Cursos" -> {
                headers = Arrays.asList("Curso", "Carga Horária", "Disciplinas", "Universidade (Sigla)");
                List<CursoDTO> cursos = relatorioService.listaCursos();
                mostraRelatorio(headers, cursos, obj -> new Object[]{obj.nome(), obj.cargaHoraria(), Arrays.deepToString(obj.disciplinas().toArray()), obj.siglaUniversidade()});
            }
            case "Disciplinas" -> {
                headers = Arrays.asList("Nome", "Sigla", "Créditos", "Carga Horária");
                List<Disciplina> disciplinas = relatorioService.listaDisciplinas();
                mostraRelatorio(headers, disciplinas, obj -> new Object[]{obj.getNome(), obj.getSigla(), obj.getCreditos(), obj.getCargahoraria()});
            }
        }
    }

    private <T> void mostraRelatorio(List<String> headers, List<T> data,  Function<T, Object[]> mappingFunction){
        Object[][] dataArr = new Object[data.size()][];
        String[] headerArr = headers.toArray(new String[0]);

        for (int i = 0; i < data.size(); i++) {
            T obj = data.get(i);
            dataArr[i] = mappingFunction.apply(obj);
        }

        DefaultTableModel tableModel = new DefaultTableModel(dataArr, headerArr);
        mainFrame.getRelatorioTable().setModel(tableModel);
    }
}
