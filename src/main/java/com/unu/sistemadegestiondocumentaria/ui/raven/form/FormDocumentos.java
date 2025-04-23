package com.unu.sistemadegestiondocumentaria.ui.raven.form;

//<editor-fold defaultstate="collapsed" desc=" Librerías...">
import com.formdev.flatlaf.FlatClientProperties;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.DocumentoDto;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.service.DocumentoService;
import com.unu.sistemadegestiondocumentaria.service.ExpedienteService;
import com.unu.sistemadegestiondocumentaria.service.OficioService;
import com.unu.sistemadegestiondocumentaria.ui.raven.tabbed.TabbedForm;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import com.unu.sistemadegestiondocumentaria.service.ActaService;
import com.unu.sistemadegestiondocumentaria.service.MemorandumService;
import com.unu.sistemadegestiondocumentaria.service.TipoDocumentoService;
import java.awt.Color;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.stream.Collectors;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
//</editor-fold>

/**
 *
 * @author RAVEN
 */
public class FormDocumentos extends TabbedForm {

    private final TipoDocumentoService ayuda = TipoDocumentoService.instanciar();

    private final DocumentoService docService = DocumentoService.instanciar();
    private final ExpedienteService expService = ExpedienteService.instanciar();
    private final OficioService ofService = OficioService.instanciar();
    private final MemorandumService memoService = MemorandumService.instanciar();
    private final ActaService actaService = ActaService.instanciar();

    private List<DocumentoDto> documentos;
    private List<TipoDocumento> tiposDoc;
    private List<Expediente> expedientes;
    private List<Object[]> dataTabla = new ArrayList<>();
    private DefaultTableModel modTabla = null;
    private int[] idexps;

    public FormDocumentos() {
        CompletableFuture<Void> unHilo = CompletableFuture.runAsync(() -> getData());
        initComponents();
        aplicarDisenioTabla(tblDocumentos);
        modTabla = (DefaultTableModel) tblDocumentos.getModel();
        iniciarDateChooser();
        unHilo.join();

        setDocumentos(documentos);
        setTipoDocs(cboTipoDoc, "TIPO DOC.", tiposDoc);
        setEstudiantes(cboEgresados, "ESTUDIANTES", expedientes);
        setExpedientes(cboExpedientes, "EXP.", idexps);
        
    }

    private void getDocumentos(List<DocumentoDto> documentos) {
        for (DocumentoDto docDto : documentos) {
            Object[] row = new Object[5];
            row[0] = getLista(docDto.getIdExpedientes());
            row[1] = docDto.getNombre();
            String asunto = "";
            switch (docDto.getIdTipoDoc()) {
                case 1 ->
                    asunto = ofService.getAsuntoByDoc(docDto.getId());
                case 2 ->
                    asunto = memoService.getAsuntoByDoc(docDto.getId());
                case 3 ->
                    asunto = actaService.getAsuntoByDoc(docDto.getId());
            }
            row[2] = (asunto != null) ? asunto : "";
            row[3] = getLista(docDto.getDestinatarios());
            row[4] = docDto.getFechaEmision().toString();
            dataTabla.add(row);
        }
    }

    private void setDocumentos(List<DocumentoDto> documentos) {
        modTabla.setRowCount(0);
        dataTabla = new ArrayList<>();

        getDocumentos(documentos);
        for (Object[] o : dataTabla) {
            modTabla.addRow(o);
        }

        tblDocumentos.setModel(modTabla);
    }

    private <T> String getLista(List<T> lista) {
        String s = "";
        if (!lista.isEmpty()) {
            int cantComas = lista.size() - 1;
            for (int i = 0; i < cantComas; i++) {
                s += lista.get(i) + ", ";
            }
            s += lista.get(cantComas);
        }
        return s;
    }

    private <T> void setTipoDocs(JComboBox comboBox, String selected, List<T> lista) {
        comboBox.removeAllItems();
        comboBox.addItem(selected);
        for (T t : lista) {
            comboBox.addItem(t.toString());
        }
    }

    private void setEstudiantes(JComboBox comboBox, String selected, List<Expediente> lista) {
        comboBox.removeAllItems();
        comboBox.addItem(selected);
        int i = 0;
        for (Expediente t : lista) {
            comboBox.addItem(t.toStringPorAp());
            idexps[i] = t.getId();
            i++;
        }
//        for (int i = 0; i < lista.size(); i++) {
//            comboBox.addItem(lista.get(i).toStringPorAp());
//            idexps[i] = lista.get(i).getId();
//        }
    }

    private void setExpedientes(JComboBox comboBox, String selected, int[] lista) {
        comboBox.removeAllItems();
        comboBox.addItem(selected);
        for (int i = 0; i < lista.length; i++) {
            comboBox.addItem(lista[i]);
        }
    }

    private void getData() {
//        CompletableFuture<Void> docHilo = CompletableFuture.runAsync(() -> documentos = docService.getAll());
//        CompletableFuture<Void> expHilo = CompletableFuture.runAsync(() -> expedientes = expService.getAllExpOrdenAlfApPaterno());
//        CompletableFuture<Void> unoMas = CompletableFuture.runAsync(() -> tiposDoc = docService.getAllTiposDocumento());
//        new Thread(() -> documentos = docService.getAll()).start();
//        new Thread(() -> expedientes = expService.getAllExpOrdenAlfApPaterno()).start();
//        new Thread(() -> tiposDoc = docService.getAllTiposDocumento()).start();
        documentos = docService.getAll();
        expedientes = expService.getAllExpOrdenAlfApPaterno();
        tiposDoc = docService.getAllTiposDocumento();
//        expHilo.join();
        idexps = new int[expedientes.size()];
//        docHilo.join();
    }

    private static <T> void imprimirElementos(List<T> lista) {
        for (T x : lista) {
            System.out.println(x.toString());
        }
    }

    private void filtrarPorExp(int idExp) {
        List<DocumentoDto> docs = new ArrayList<>();

        for (DocumentoDto doc : documentos) {
            if (doc.getIdExpedientes().contains(idExp)) {
                docs.add(doc);
            }
        }

        setDocumentos(docs);
    }

    private void filtrarPorTipoDoc(String tipoDoc) {
        List<DocumentoDto> docs = documentos;

        docs = docs.stream().filter(x -> x.getTipoDocumento().equals(tipoDoc)).collect(Collectors.toList());

        setDocumentos(docs);
    }

    private void filtrarPorFecha(String fechaInicialString, String fechaFinalString) {
        List<DocumentoDto> docs = new ArrayList<>();
        Date fechaInicial = Date.valueOf(fechaInicialString);
        Date fechaFinal = Date.valueOf(fechaFinalString);

        for (DocumentoDto doc : documentos) {
            Date fecha = doc.getFechaEmision();
            if (fechaInicial.compareTo(fecha) <= 0 && fechaFinal.compareTo(fecha) >= 0) {
                docs.add(doc);
            }
        }

        setDocumentos(docs);
    }

    private void setEntregado(String nombre) {
        try {
            int id = docService.getIdByNombre(nombre);
            docService.updateEstadoDocumento(id);
            System.out.println("id = " + id);
        } catch (Exception e) {

        }
    }

    private void mostrarPendientes() {
        setDocumentos(docService.getAllPendientes());
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
        jButton1 = new javax.swing.JButton();
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
                "width 150!",
                "width 200"
            }
        ));

        cboExpedientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EXP." }));
        cboExpedientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboExpedientesActionPerformed(evt);
            }
        });
        crazyPanel2.add(cboExpedientes);

        cboEgresados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EGRESADOS" }));
        cboEgresados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEgresadosActionPerformed(evt);
            }
        });
        crazyPanel2.add(cboEgresados);

        cboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TIPO DOC." }));
        cboTipoDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoDocActionPerformed(evt);
            }
        });
        crazyPanel2.add(cboTipoDoc);

        txtFecha.setText("DD-MM-AAAA");
        crazyPanel2.add(txtFecha);

        btnTodos.setText("Todos");
        btnTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTodosActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnTodos);

        btnCambiarEstado.setText("Cambiar Estado");
        btnCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoActionPerformed(evt);
            }
        });
        crazyPanel2.add(btnCambiarEstado);

        btnEditarDoc.setText("Editar Doc.");
        crazyPanel2.add(btnEditarDoc);

        jButton1.setText("Pendientes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton1);

        crazyPanel1.add(crazyPanel2);

        tblDocumentos.setBackground(new java.awt.Color(246, 246, 246));
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
        tblDocumentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDocumentosMouseClicked(evt);
            }
        });
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
            tblDocumentos.getColumnModel().getColumn(3).setMinWidth(200);
            tblDocumentos.getColumnModel().getColumn(3).setPreferredWidth(250);
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
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
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

    private void cboEgresadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEgresadosActionPerformed
        if (cboEgresados.getSelectedIndex() != 0 && cboEgresados.getSelectedIndex() != -1) {
            int idxEgresados = cboEgresados.getSelectedIndex();
            cboExpedientes.setSelectedIndex(idxEgresados);
        }
    }//GEN-LAST:event_cboEgresadosActionPerformed

    private void cboExpedientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboExpedientesActionPerformed
        if (cboExpedientes.getSelectedIndex() != 0 && cboExpedientes.getSelectedIndex() != -1) {
            int idxEgresados = cboExpedientes.getSelectedIndex();
            cboEgresados.setSelectedIndex(idxEgresados);

            filtrarPorExp((int) cboExpedientes.getSelectedItem());
        }
    }//GEN-LAST:event_cboExpedientesActionPerformed

    private void cboTipoDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoDocActionPerformed
        if (cboTipoDoc.getSelectedIndex() != 0 && cboTipoDoc.getSelectedIndex() != -1) {
            filtrarPorTipoDoc((String) cboTipoDoc.getSelectedItem());
        }
    }//GEN-LAST:event_cboTipoDocActionPerformed

    private void btnTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTodosActionPerformed
        setDocumentos(documentos);
    }//GEN-LAST:event_btnTodosActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        int fila = tblDocumentos.getSelectedRow();
        if (fila != -1) {
            String nombre = tblDocumentos.getValueAt(fila, 1).toString();
            setEntregado(nombre); //si trae el id
            mostrarPendientes();
        }
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    private void tblDocumentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDocumentosMouseClicked
        if (evt.getClickCount() == 2) {
            int fila = tblDocumentos.getSelectedRow();
            String nombre = tblDocumentos.getValueAt(fila, 1).toString();
        }
    }//GEN-LAST:event_tblDocumentosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarPendientes();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDocumentos;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables

    private final DateChooser dateChooser = new DateChooser();

    private void aplicarDisenioTabla(JTable tabla) {
        JScrollPane scroll = (JScrollPane) tabla.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackInsets: 3,3,3,3;"
                + "thumbInsets: 3,3,3,3;"
                + "trackArc:999");

        tabla.setCellSelectionEnabled(false);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
                    label.setHorizontalAlignment(SwingConstants.LEADING);
                    label.setBackground(new Color(246, 246, 246));
                }
                return com;
            }

        };
    }

    private void iniciarDateChooser() {
        dateChooser.setTextField(txtFecha);
        dateChooser.setForeground(Color.WHITE);
        dateChooser.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        dateChooser.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        dateChooser.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String fechaInicial = format.format(date.getFromDate());
                String fechaFinal = format.format(date.getToDate());

                filtrarPorFecha(fechaInicial, fechaFinal);
            }

        });
    }

}
