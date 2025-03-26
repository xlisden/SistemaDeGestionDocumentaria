package com.unu.sistemadegestiondocumentaria.ui.raven.form;

import com.unu.sistemadegestiondocumentaria.ui.raven.tabbed.TabbedForm;
import com.unu.sistemadegestiondocumentaria.ui.raven.tabbed.WindowsTabbed;
import javax.swing.JOptionPane;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import raven.toast.Notifications;

/**
 *
 * @author RAVEN
 */
public class FormDocumentos extends TabbedForm {

//    private int opcion = -10;

    /**
     * Creates new form TestForm
     */
    public FormDocumentos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("Test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        jButton2.setText("Show Alerts");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(136, 136, 136))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Notifications.getInstance().show(Notifications.Type.INFO, "Test Toast Notifications");
        //WindowsTabbed.getInstance().removeTab(this);

        System.out.println("------------");
        // get all tab name
        String[] arr = WindowsTabbed.getInstance().getTabName();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("selected: " + WindowsTabbed.getInstance().getTabSelectedIndex());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MessageAlerts.getInstance().showMessage("Data Saving Failure", "Oops! We encountered an issue while attempting to save your data. Please try again later or contact support for assistance. Apologies for any inconvenience caused.", MessageAlerts.MessageType.ERROR, MessageAlerts.OK_OPTION, new PopupCallbackAction() {
            @Override
            public void action(PopupController pc, int i) {
                if (i == MessageAlerts.OK_OPTION) {
//                    System.out.println("Click ok");
                }
            }
        });
    }//GEN-LAST:event_jButton2ActionPerformed

    @Override
    public boolean formClose() {
//        opcion = -10;
        if (txt.getText().trim().equals("")) {
            return true;
        }
//        MessageAlerts.getInstance().showMessage("Datos no guardados", "¿Estás seguro de salir sin guardar los datos?", MessageAlerts.MessageType.WARNING, MessageAlerts.OK_OPTION, new PopupCallbackAction() {
//            @Override
//            public void action(PopupController pc, int i) {
//                opcion = i;
//                System.out.println("opcion = " + opcion);
//                if (i == MessageAlerts.OK_OPTION) {
//                }
//            }
//        });
//        System.out.println("out");
        int opt = JOptionPane.showConfirmDialog(this, "¿Estás seguro de salir sin guardar correctamente los datos?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        return opt == JOptionPane.YES_OPTION;
    }

    @Override
    public void formOpen() {
//        System.out.println("Form open");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
