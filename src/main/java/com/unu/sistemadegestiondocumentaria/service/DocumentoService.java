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
            Administrativo dest = administrativoService.getById(t.getIdDestinatarios().get(0));
            if (td == null || emisor == null || exp == null || dest == null) {
                return;
            }

            t.setTipoDocumento(td);
            t.setEmisor(emisor);
            t.setEstado(estadoService.getByNombre("PENDIENTE"));
            t.setCorrelativo(setCorrelativo());
            Validation.validateDocumento(t);

            super.add(t);

            t.setExpediente(exp);
            detExpedienteService.add(new DetalleDocumento(t, exp));

            for (Integer i : t.getIdDestinatarios()) {
                dest = administrativoService.getById(i);
                if (dest == null) {
                    return;
                }
                t.getDestinatarios().add(dest);
                detDestinatarioService.add(new DetalleDestinatario(t, dest));
            }

        } catch (ValidationException e) {
            e.printMessage();
        }
    }

    @Override
    public void update(int id, Documento t) {
        try {
            Documento doc = getById(id);
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
            Estado est = estadoService.getByNombre("ENTREGADO");
            if (doc == null || est == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            doc.setEstado(est);
            super.update(id, doc);
        } catch (ValidationException e) {
            e.printMessage();
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
            e.printMessage();
        }
    }

    public void deleteDestinatario(int idDoc, int idDest) {
        try {
            Documento doc  = getById(idDoc);
            Administrativo dest = administrativoService.getById(idDest);
            if (doc == null || dest == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            detDestinatarioService.delete(idDoc, idDest);
        } catch (ValidationException e) {
            e.printMessage();
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
            e.printMessage();
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
            e.printMessage();
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

    public Documento setDocumento(Documento documento, Documento doc) {
        Administrativo emisor = (doc.getIdEmisor() == 0)
                                ? (doc.getEmisor() != null ? doc.getEmisor() : documento.getEmisor())
                                : administrativoService.getById(doc.getIdEmisor());
        TipoDocumento td = (doc.getIdTipoDoc() == 0)
                            ? ((doc.getTipoDocumento() != null) ? doc.getTipoDocumento() : documento.getTipoDocumento())
                            : tdService.getById(doc.getIdTipoDoc());

        String correlativo = doc.getCorrelativo() != null ? doc.getCorrelativo() : documento.getCorrelativo();
        Estado estado = doc.getEstado() != null ? doc.getEstado() : documento.getEstado();

        documento.setCorrelativo(correlativo);
        documento.setFechaEmision(doc.getFechaEmision());
        documento.setTipoDocumento(td);
        documento.setEstado(estado);
        documento.setEmisor(emisor);

        return documento;
    }

}
