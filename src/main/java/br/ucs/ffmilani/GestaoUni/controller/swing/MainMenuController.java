package br.ucs.ffmilani.GestaoUni.controller.swing;

import br.ucs.ffmilani.GestaoUni.Swing.MainFrame;
import br.ucs.ffmilani.GestaoUni.controller.swing.MatriculaController;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MainMenuController extends AbstractFrameController {

    private MainFrame mainFrame;
    private RelatorioController relatorioController;
    private MatriculaController matriculaController;

    @Override
    public void prepareAndOpen() {
        mainFrame.setVisible(true);
    }
}
