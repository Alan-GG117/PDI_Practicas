import Controller.Pract1Controller;
import Controller.Pract2Controller;
import Model.Pract1Model;
import Model.Pract2Model;
import View.Pract1View;
import View.Pract2View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Pract1Model modelo = new Pract1Model();
        //Pract1View vista = new Pract1View();
        //Pract1Controller controlador = new Pract1Controller(modelo, vista);

        SwingUtilities.invokeLater(() -> {
            Pract2Model model = new Pract2Model();
            Pract2View view = new Pract2View();
            Pract2Controller controller = new Pract2Controller(model, view);

            controller.init();
        });
    }
}
