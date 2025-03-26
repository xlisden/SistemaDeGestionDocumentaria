package com.unu.sistemadegestiondocumentaria.ui.raven.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.unu.sistemadegestiondocumentaria.Main;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

public class Login extends JPanel {

//    private String usuario = "ingyupanqui";
//    private String contrasenia = "yupanqui2025";
    private String usuario = "";
    private String contrasenia = "";

    public Login() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        cmdLogin = new JButton("Iniciar Sesión");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 35 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:20;"
                + "[light]background:darken(@background,3%);"
                + "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]background:darken(@background,10%);"
                + "[dark]background:lighten(@background,10%);"
                + "margin:4,6,4,6;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");

        cmdLogin.addActionListener((e) -> {
            if (txtUsername.getText().equals(usuario) && txtPassword.getText().equals(contrasenia)) {
                Main.main.showMainForm();
            } else {
                MessageAlerts.getInstance().showMessage("Error al iniciar sesión", "Por favor, ingresa las credenciales correctas.", MessageAlerts.MessageType.ERROR, MessageAlerts.OK_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController pc, int i) {
                    }
                });
            }
        });
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu usuario.");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingresa tu contraseña.");

        JLabel lbTitle = new JLabel("Sist. de Gestión Documentaria");
        JLabel description = new JLabel("Oficina de Grados y Títulos");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, ""
                + "[light]foreground:lighten(@foreground,30%);"
                + "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Usuario"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(txtPassword);
        panel.add(cmdLogin, "gapy 10");
        add(panel);
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton cmdLogin;
}
