package com.unu.sistemadegestiondocumentaria.ui.raven.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.unu.sistemadegestiondocumentaria.ui.raven.tabbed.TabbedForm;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class FormDocumentos extends TabbedForm {

    public FormDocumentos() {
        initComponents();
        aplicarDisenioTabla(tblDocumentos);
    }

    private void aplicarDisenioTabla(JTable tabla) {
        JScrollPane scroll = (JScrollPane) tabla.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        tabla.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tabla.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        tabla.getTableHeader().setDefaultRenderer(getAligmentCellRenderer(tblDocumentos.getTableHeader().getDefaultRenderer(), true));
    }

    private TableCellRenderer getAligmentCellRenderer(TableCellRenderer antiguo, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = antiguo.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }
                return com;
            }

        };
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        cboExpedientes = new javax.swing.JComboBox<>();
        cboEgresados = new javax.swing.JComboBox<>();
        cboTipoDoc = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        btnTodos = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        btnEditarDoc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDocumentos = new javax.swing.JTable();

        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[][][][]push[][]",
            "",
            new String[]{
                "width 30",
                "width 250",
                "width 150",
                "width 200"
            }
        ));

        cboExpedientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EXP." }));
        crazyPanel2.add(cboExpedientes);

        cboEgresados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EGRESADOS" }));
        crazyPanel2.add(cboEgresados);

        cboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TIPO DOC." }));
        crazyPanel2.add(cboTipoDoc);

        txtFecha.setText("DD-MM-AAAA");
        crazyPanel2.add(txtFecha);

        btnTodos.setText("Todos");
        crazyPanel2.add(btnTodos);

        btnCambiarEstado.setText("Cambiar Estado");
        crazyPanel2.add(btnCambiarEstado);

        btnEditarDoc.setText("Editar Doc.");
        crazyPanel2.add(btnEditarDoc);

        crazyPanel1.add(crazyPanel2);

        tblDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exp.", "Documento", "Asunto/Tesis", "Destinatario(s)", "F. Emisión", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDocumentos.setColumnSelectionAllowed(true);
        tblDocumentos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblDocumentos);
        tblDocumentos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblDocumentos.getColumnModel().getColumnCount() > 0) {
            tblDocumentos.getColumnModel().getColumn(0).setMinWidth(60);
            tblDocumentos.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblDocumentos.getColumnModel().getColumn(0).setMaxWidth(60);
            tblDocumentos.getColumnModel().getColumn(1).setMinWidth(150);
            tblDocumentos.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblDocumentos.getColumnModel().getColumn(1).setMaxWidth(200);
            tblDocumentos.getColumnModel().getColumn(2).setMinWidth(200);
            tblDocumentos.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblDocumentos.getColumnModel().getColumn(2).setMaxWidth(300);
            tblDocumentos.getColumnModel().getColumn(3).setMinWidth(250);
            tblDocumentos.getColumnModel().getColumn(3).setPreferredWidth(250);
            tblDocumentos.getColumnModel().getColumn(3).setMaxWidth(300);
            tblDocumentos.getColumnModel().getColumn(4).setMinWidth(100);
            tblDocumentos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblDocumentos.getColumnModel().getColumn(4).setMaxWidth(100);
            tblDocumentos.getColumnModel().getColumn(5).setMinWidth(50);
            tblDocumentos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblDocumentos.getColumnModel().getColumn(5).setMaxWidth(50);
        }

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public boolean formClose() {
//        if (txt.getText().trim().equals("")) {
//            return true;
//        }
//        int opt = JOptionPane.showConfirmDialog(this, "¿Estás seguro de salir sin guardar correctamente los datos?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//        return opt == JOptionPane.YES_OPTION;
        return true;
    }

    @Override
    public void formOpen() {
//        System.out.println("Form open");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnEditarDoc;
    private javax.swing.JButton btnTodos;
    private javax.swing.JComboBox<String> cboEgresados;
    private javax.swing.JComboBox<String> cboExpedientes;
    private javax.swing.JComboBox<String> cboTipoDoc;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
