package br.ucs.ffmilani.GestaoUni.controller.swing;

import br.ucs.ffmilani.GestaoUni.Swing.MainFrame;
import br.ucs.ffmilani.GestaoUni.model.MatriculaDTO;
import br.ucs.ffmilani.GestaoUni.service.MatriculaService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class SwingMatriculaController extends AbstractFrameController {

    private MainFrame mf;
    private MatriculaService matriculaService;

    @PostConstruct
    public void prepareListeners(){
        registerAction(mf.getBtnCadastrar(), e -> matricular());
    }

    @Override
    public void prepareAndOpen() {

    }

    public void matricular() {
        List<String> lista = Arrays.stream(mf.getTfSigla().getText().split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        String outMessage = matriculaService.matricularAluno(
                new MatriculaDTO(
                        mf.getTfEmail().getText(),
                        mf.getTfSigla().getText(),
                        mf.getTfSemestre().getText()),
                lista
        );

        mf.getLbStatus().setText(outMessage);
    }
}
