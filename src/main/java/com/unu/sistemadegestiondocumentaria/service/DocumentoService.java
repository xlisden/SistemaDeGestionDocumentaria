package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;

import java.time.LocalDate;
import java.util.List;

import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

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
        Administrativo dest = null;
        Administrativo emisor = null;
        Expediente exp = null;
        TipoDocumento td = null;
        try {
            td = tdService.getById(t.getIdTipoDoc());
            emisor = administrativoService.getById(t.getIdEmisor());

            t.setTipoDocumento(td);
            t.setEmisor(emisor);
            t.setEstado(estadoService.getByNombre("PENDIENTE"));
            t.setCorrelativo(setCorrelativo());
            Validation.validateDocumento(t);

            super.add(t);

            if (!t.getIdDestinatarios().isEmpty()) {
                for (Integer i : t.getIdDestinatarios()) {
                    dest = administrativoService.getById(i);
                    if (dest != null) {
                        t.getDestinatarios().add(dest);
                        detDestinatarioService.add(new DetalleDestinatario(t, dest));
                    }
                }
            }

            exp = expedienteService.getById(t.getIdExpediente());
            if (exp != null) {
                t.setExpediente(exp);
                detExpedienteService.add(new DetalleDocumento(t, exp));
            }

        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Documento t) {
        Documento doc = null;
        try {
            doc = getById(id);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }

            setDocumento(doc, t);
            Validation.validateDocumento(doc);

            super.update(id, doc);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public List<Documento> getAll() {
        return super.getAll();
    }

    @Override
    public Documento getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printMessage();
        }
        return null;
    }

    public void updateEstadoDocumento(int id) {
        try {
            Documento doc = getById(id);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }
            doc.setEstado(estadoService.getByNombre("ENTREGADO"));
            super.update(id, doc);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void updateDestinatario(int idDoc, int idAntDest, int idNuevoDest) {
        Administrativo nuevoDest = null;
        Administrativo antDest = null;
        Documento doc = null;
        try {
            doc = getById(idDoc);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }

            nuevoDest = administrativoService.getById(idNuevoDest);
            antDest = administrativoService.getById(idAntDest);
            detDestinatarioService.update(new DetalleDestinatario(doc, antDest), nuevoDest);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void deleteDestinatario(int idDoc, int idDest) {
        Administrativo dest = null;
        Documento doc = null;
        try {
            doc = getById(idDoc);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }

            dest = administrativoService.getById(idDest);
            if (dest == null) {
                throw new ValidationException(Validation.showWarning("El Destinatario no puede estar vacío."));
            }
            detDestinatarioService.delete(idDoc, idDest);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    public void deleteDocDependencias(int idDoc) {
        Documento doc = null;
        try {
            doc = getById(idDoc);
            if (doc == null) {
                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            }

            detDestinatarioService.deleteByDoc(idDoc);
            detExpedienteService.deleteByDoc(idDoc);
        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    private String setCorrelativo() {
        Documento doc = null;
        if (getAll().isEmpty()) {
            return "001";
        } else {
            doc = super.getLast();
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

    public Documento setDocumento(Documento documento, Documento doc) {
        Administrativo emisor = null;
        Estado estado = null;
        String correlativo = null;
        TipoDocumento td = null;

        emisor = (doc.getIdEmisor() == 0)
                ? (doc.getEmisor() != null ? doc.getEmisor() : documento.getEmisor())
                : administrativoService.getById(doc.getIdEmisor());
        td = (doc.getIdTipoDoc() == 0)
                ? ((doc.getTipoDocumento() != null) ? doc.getTipoDocumento() : documento.getTipoDocumento())
                : tdService.getById(doc.getIdTipoDoc());

        correlativo = doc.getCorrelativo() != null ? doc.getCorrelativo() : documento.getCorrelativo();
        estado = doc.getEstado() != null ? doc.getEstado() : documento.getEstado();

        documento.setCorrelativo(correlativo);
        documento.setFechaEmision(doc.getFechaEmision());
        documento.setTipoDocumento(td);
        documento.setEstado(estado);
        documento.setEmisor(emisor);

        return documento;
    }

}
