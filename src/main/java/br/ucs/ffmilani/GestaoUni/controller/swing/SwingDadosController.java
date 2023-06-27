package br.ucs.ffmilani.GestaoUni.controller.swing;

import br.ucs.ffmilani.GestaoUni.GestaoUniApplication;
import br.ucs.ffmilani.GestaoUni.swingview.MainFrame;
import br.ucs.ffmilani.GestaoUni.service.DatabaseStatusService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class SwingDadosController extends AbstractFrameController {

    private MainFrame mf;
    private GestaoUniApplication gu;
    private DatabaseStatusService dbss;

    @PostConstruct
    public void prepareListeners(){
        registerAction(mf.getBtnCadastrarDadosPadroes(), e -> dbss.tentaCadastrarDados());
    }

    @Override
    public void prepareAndOpen() {
        mf.getLbBancoAtivo().setText(gu.dbms());
    }


}
