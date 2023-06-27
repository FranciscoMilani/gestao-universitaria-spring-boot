package br.ucs.ffmilani.GestaoUni.controller.swing;

import br.ucs.ffmilani.GestaoUni.Swing.MainFrame;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MainMenuController extends AbstractFrameController {

    private MainFrame mainFrame;
    private RelatorioController relatorioController;
    private SwingMatriculaController matriculaController;
    private SwingDadosController dadosController;

    @Override
    public void prepareAndOpen() {
        relatorioController.prepareAndOpen();
        matriculaController.prepareAndOpen();
        dadosController.prepareAndOpen();
        mainFrame.setVisible(true);
    }
}
