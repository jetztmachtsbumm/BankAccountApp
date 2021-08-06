package de.kellner.bankaccountapp.controllers;

import de.kellner.bankaccountapp.Workflow;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ErrorWindowController {
    public void ok_btn_click(MouseEvent mouseEvent) throws IOException {
        Workflow.GetInstance().getWindowLoader().hideErrorWindow();
    }
}
