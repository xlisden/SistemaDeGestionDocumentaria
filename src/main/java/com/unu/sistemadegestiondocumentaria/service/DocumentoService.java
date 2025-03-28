package com.unu.sistemadegestiondocumentaria.service;

import java.time.LocalDate;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DocumentoService extends Repository<Documento> {

    private final AdministrativoService administrativoService = AdministrativoService.instanciar();
    private final DetDestinatarioService detDestinatarioService = DetDestinatarioService.instanciar();
    private final DetExpedienteService detExpedienteService = DetExpedienteService.instanciar();
    private final EstadoService estadoService = EstadoService.instanciar();
    private final ExpedienteService expedienteService = ExpedienteService.instanciar();
    private final TipoDocumentoService tdService = TipoDocumentoService.instanciar();

    private static DocumentoService INSTANCIA;

    private DocumentoService(Class<Documento> type) {
        super(type);
    }

    public static DocumentoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new DocumentoService(Documento.class);
        }
        return INSTANCIA;
    }

    @Override
    public void add(Documento t) {
        try {
            TipoDocumento td = tdService.getById(t.getIdTipoDoc());
            Administrativo emisor = administrativoService.getById(t.getIdEmisor());
            Expediente exp = expedienteService.getById(t.getIdExpediente());
//            Administrativo dest = administrativoService.getById(t.getIdDestinatarios().get(0));
//            || dest == null
            if (td == null || emisor == null || exp == null) {
                return;
            }

            t.setTipoDocumento(td);
            t.setEmisor(emisor);
            t.setEstado(estadoService.getByNombre("PENDIENTE"));
            t.setCorrelativo(setCorrelativo());
            t.setExpediente(exp);

            Validation.validateDocumento(t);
            super.add(t);

            System.out.println("exp = " + exp);
            detExpedienteService.add(new DetalleDocumento(t, exp));

            Administrativo dest = null;
            for (Integer i : t.getIdDestinatarios()) {
                dest = administrativoService.getById(i);

                // aqui es != porque no queremos que termine el proceso si un dest es incorrecto, queremos que siga
                if (dest != null) {
                    t.getDestinatarios().add(dest);
                    detDestinatarioService.add(new DetalleDestinatario(t, dest));
                }
            }

        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public boolean addDoc(Documento t) {
        try {
            TipoDocumento td = tdService.getById(t.getIdTipoDoc());
            Administrativo emisor = administrativoService.getById(t.getIdEmisor());
            Expediente exp = expedienteService.getById(t.getIdExpediente());
            if (td == null || emisor == null || exp == null) {
                return false;
            }

            t.setTipoDocumento(td);
            t.setEmisor(emisor);
            t.setEstado(estadoService.getByNombre("PENDIENTE"));
            t.setCorrelativo(setCorrelativo());
            t.setExpediente(exp);

            Validation.validateDocumento(t);
            super.add(t);

            detExpedienteService.add(new DetalleDocumento(t, exp));

            Administrativo dest = null;
            for (Integer i : t.getIdDestinatarios()) {
                dest = administrativoService.getById(i);

                // aqui es != porque no queremos que termine el proceso si un dest es incorrecto, queremos que siga
                if (dest != null) {
                    t.getDestinatarios().add(dest);
                    detDestinatarioService.add(new DetalleDestinatario(t, dest));
                }
            }

        } catch (ValidationException e) {
            e.printConsoleMessage();
            return false;
        }
        return true;
    }

    @Override
    public void update(int id, Documento t) {
        try {
            Documento doc = getById(id);
            if (doc == null) {
//                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            TipoDocumento td = tdService.getById(t.getIdTipoDoc());
            Administrativo emisor = administrativoService.getById(t.getIdEmisor());
            Expediente exp = expedienteService.getById(t.getIdExpediente());
            String correlativo = t.getCorrelativo() != null ? t.getCorrelativo() : doc.getCorrelativo();
            Estado estado = t.getEstado() != null ? t.getEstado() : doc.getEstado();
            Date fechaEmision = t.getFechaEmision() != null ? t.getFechaEmision() : doc.getFechaEmision();

            if (emisor == null || td == null || exp == null || correlativo.isEmpty() || estado == null || fechaEmision == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            doc.setCorrelativo(correlativo);
            doc.setFechaEmision(fechaEmision);
            doc.setTipoDocumento(td);
            doc.setEstado(estado);
            doc.setEmisor(emisor);

            doc.setExpediente(getExpediente(doc.getId())); // para obtener su exp anterior
//            System.out.println("exp en t = " + exp.toString());
//            System.out.println("exp en doc = " + doc.getExpediente().toString() + "\n");
            updateExpediente(doc, doc.getExpediente(), exp);
            doc.setExpediente(exp);

            Administrativo dest = null;
            if (t.getIdDestinatarios() != null) {
                for (Integer i : t.getIdDestinatarios()) {
                    dest = administrativoService.getById(i);

                    if (dest != null) { // aqui es != porque no queremos que termine el proceso si un dest es incorrecto, queremos que siga
                        t.getDestinatarios().add(dest);
                    }
                }
            }
            if (t.getDestinatarios() != null && !t.getDestinatarios().isEmpty()) { // si es nulo, no hay necesidad de actualizar
                updateDestinatarios(doc, t.getDestinatarios());
            }
            doc.setDestinatarios(getDestinatarios(doc.getId()));

//            setDocumento(doc, t);
            Validation.validateDocumento(doc);

            super.update(id, doc);

        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public List<Documento> getAll() {
        List<Documento> documentos = new ArrayList<Documento>();
        documentos = super.getAll();
        for (Documento doc : documentos) {
            doc.setExpediente(getExpediente(doc.getId()));
            doc.setDestinatarios(getDestinatarios(doc.getId()));
        }
        return documentos;
    }

    @Override
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public Documento getById(int id) {
        Documento doc = null;
        try {
            doc = super.getById(id);
            try {
                doc.setExpediente(getExpediente(id));
            } catch (ValidationException e) { // para que me traiga los destinatarios si no hay exp
                e.printConsoleMessage();
            }
            doc.setDestinatarios(getDestinatarios(id));
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return doc;
    }

    public void updateEstadoDocumento(int id) {
        try {
            Documento doc = getById(id);
            Estado est = estadoService.getByNombre("ENTREGADO");
            if (doc == null || est == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            doc.setEstado(est);
            super.update(id, doc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateDestinatario(int idDoc, int idAntDest, int idNuevoDest) {
        try {
            Documento doc = getById(idDoc);
            Administrativo nuevoDest = administrativoService.getById(idNuevoDest);
            Administrativo antDest = administrativoService.getById(idAntDest);
            if (doc == null || nuevoDest == null || antDest == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }

            detDestinatarioService.update(new DetalleDestinatario(doc, antDest), nuevoDest);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateDestinatarios(Documento doc, List<Administrativo> nuevosDest) {
        try {
            if (doc == null || nuevosDest.isEmpty()) {
                System.out.println("en el return");
                if (nuevosDest.isEmpty()) {
                    System.out.println("nuevosDest.isEmpty()");
                }
                return;
            }

            detDestinatarioService.updateDestinatariosByDoc(doc, nuevosDest);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void deleteDestinatario(int idDoc, int idDest) {
        try {
            Documento doc = getById(idDoc);
            Administrativo dest = administrativoService.getById(idDest);
            if (doc == null || dest == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            detDestinatarioService.delete(idDoc, idDest);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void deleteDocDependencias(int idDoc) {
        try {
            Documento doc = getById(idDoc);
            if (doc == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            detDestinatarioService.deleteByDoc(idDoc);
            detExpedienteService.deleteByDoc(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateExpediente(int idDoc, int idAntExp, int idNuevoExp) {
        try {
            Documento doc = getById(idDoc);
            Expediente antExp = expedienteService.getById(idAntExp);
            Expediente nuevoExp = expedienteService.getById(idNuevoExp);
            if (doc == null || antExp == null || nuevoExp == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }
            // if (antExp == null) {
            //     throw new ValidationException(Validation.showWarning("El antiguo Expediente no puede estar vacío."));
            // }

            // if (nuevoExp == null) {
            //     throw new ValidationException(Validation.showWarning("El nuevo Expediente no puede estar vacío."));
            // }
            detExpedienteService.update(new DetalleDocumento(doc, antExp), nuevoExp);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateExpediente(Documento doc, Expediente antExp, Expediente nuevoExp) {
        // si los objetos ya fueron verificados que existen en la database
        try {
            if (doc == null || antExp == null || nuevoExp == null) {
                return;
            }

            detExpedienteService.update(new DetalleDocumento(doc, antExp), nuevoExp);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    private String setCorrelativo() {
        if (getAll().isEmpty()) {
            return "001";
        } else {
            Documento doc = super.getLast();
            if (doc.getFechaEmision().getYear() + 1900 < LocalDate.now().getYear()) {
                return "001";
            } else {
                int n = Integer.parseInt(doc.getCorrelativo().trim());
                n++;
                String c = String.format("%03d%n", n);
                return c;
            }
        }
    }

    public List<Administrativo> getDestinatarios(int idDoc) {
        List<Administrativo> destinatarios = new ArrayList<>();
        try {
            destinatarios = detDestinatarioService.getDestinatariosByDoc(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return destinatarios;
    }

    public Expediente getExpediente(int idDoc) {
        Expediente exp = null;
        try {
            exp = detExpedienteService.getExpedienteByDoc(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return exp;
    }

    // usado en oficio para
    /* puede usarse restos del codigo para cuando en los update, se quiere poner los mismos datos y solo se cambia algo? */
//    public Documento setDocumento(Documento documento, Documento doc) {
//        Administrativo emisor = (doc.getIdEmisor() == 0)
//                ? (doc.getEmisor() != null ? doc.getEmisor() : documento.getEmisor())
//                : administrativoService.getById(doc.getIdEmisor());
//        TipoDocumento td = (doc.getIdTipoDoc() == 0)
//                ? ((doc.getTipoDocumento() != null) ? doc.getTipoDocumento() : documento.getTipoDocumento())
//                : tdService.getById(doc.getIdTipoDoc());
//        Expediente exp = (doc.getIdExpediente() == 0)
//                ? ((doc.getExpediente() != null) ? doc.getExpediente() : documento.getExpediente())
//                : expedienteService.getById(doc.getIdExpediente());
//
//        String correlativo = doc.getCorrelativo() != null ? doc.getCorrelativo() : documento.getCorrelativo();
//        Estado estado = doc.getEstado() != null ? doc.getEstado() : documento.getEstado();
//
//        if (emisor == null || td == null || exp == null || correlativo.isEmpty() || estado == null) {
//            // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//            return null;
//        }
//
//        documento.setCorrelativo(correlativo);
//        documento.setFechaEmision(doc.getFechaEmision());
//        documento.setTipoDocumento(td);
//        documento.setEstado(estado);
//        documento.setEmisor(emisor);
//        documento.setExpediente(exp);
//
//        return documento;
//    }
}
