package br.ucs.ffmilani.GestaoUni.controller.swing;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class AbstractFrameController {
    public abstract void prepareAndOpen();

    protected void registerAction(JComboBox<?> comboBox, ActionListener listener) {
        comboBox.addActionListener(listener);
    }

    protected void registerAction(JButton btn, ActionListener listener) {
        btn.addActionListener(listener);
    }
}
