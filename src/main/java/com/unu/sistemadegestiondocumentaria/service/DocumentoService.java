package com.unu.sistemadegestiondocumentaria.service;

import java.time.LocalDate;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.DocumentoDto;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.AdministrativoRepository;
import com.unu.sistemadegestiondocumentaria.repository.DetalleDestinatarioRepository;
import com.unu.sistemadegestiondocumentaria.repository.DetalleDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.DocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.EstadoRepository;
import com.unu.sistemadegestiondocumentaria.repository.ExpedienteRepository;
import com.unu.sistemadegestiondocumentaria.repository.TipoDocumentoRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocumentoService {

    private AdministrativoRepository adminRepository;
    private DetalleDestinatarioRepository detDestRepository;
    private DetalleDocumentoRepository detDocRepository;
    private DocumentoRepository docRepository;
    private EstadoRepository estRepository;
    private ExpedienteRepository expRepository;
    private TipoDocumentoRepository tipoDocRepository;

    private static DocumentoService INSTANCIA;

    private DocumentoService() {
        adminRepository = AdministrativoRepository.instanciar();
        detDestRepository = DetalleDestinatarioRepository.instanciar();
        detDocRepository = DetalleDocumentoRepository.instanciar();
        docRepository = DocumentoRepository.instanciar();
        estRepository = EstadoRepository.instanciar();
        expRepository = ExpedienteRepository.instanciar();
        tipoDocRepository = TipoDocumentoRepository.instanciar();
    }

    public static DocumentoService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new DocumentoService();
        }
        return INSTANCIA;
    }

    public void add(Documento t) {
        TipoDocumento td = tipoDocRepository.getById(t.getIdTipoDoc());
        Administrativo emisor = adminRepository.getById(t.getIdEmisor());
        if (td == null || emisor == null) {
            return;
        }

        t.setTipoDocumento(td);
        t.setEmisor(emisor);
        t.setEstado(estRepository.getByNombre("PENDIENTE"));
        t.setCorrelativo(setCorrelativoPorTipoDoc(td.getId()));

        docRepository.add(t);

        for (Integer i : t.getIdDestinatarios()) {
            Administrativo dest = adminRepository.getById(i);
            t.getDestinatarios().add(dest);
            detDestRepository.add(new DetalleDestinatario(t, dest));
//                if (dest != null) {
//                }
        }

        for (Integer i : t.getIdExpedientes()) {
            Expediente exp = expRepository.getById(i);
            t.getExpedientes().add(exp);
            detDocRepository.add(new DetalleDocumento(t, exp));
//                if (exp != null) {
//                }
        }
    }

    public void update(int id, Documento t) {
        Documento doc = docRepository.getById(id);
        if (doc == null) {
//                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
            return;
        }

        TipoDocumento td = tipoDocRepository.getById(t.getIdTipoDoc());
        Administrativo emisor = adminRepository.getById(t.getIdEmisor());
        String correlativo = (t.getCorrelativo() != null && !t.getCorrelativo().isEmpty()) ? t.getCorrelativo()
                : doc.getCorrelativo();
        Estado estado = t.getEstado() != null ? t.getEstado() : doc.getEstado();
        Date fechaEmision = t.getFechaEmision() != null ? t.getFechaEmision() : doc.getFechaEmision();

        if (emisor == null || td == null || correlativo.isEmpty() || estado == null || fechaEmision == null) {
            return;
        }

        doc.setCorrelativo(correlativo);
        doc.setFechaEmision(fechaEmision);
        doc.setTipoDocumento(td);
        doc.setEstado(estado);
        doc.setEmisor(emisor);

        docRepository.update(id, t);

        if (t.getIdDestinatarios() != null) {
            for (Integer i : t.getIdDestinatarios()) {
                Administrativo dest = adminRepository.getById(i);
                t.getDestinatarios().add(dest);
            }
        }
        if (t.getDestinatarios() != null && !t.getDestinatarios().isEmpty()) { // si es nulo, no hay necesidad de
            // actualizar
            updateDestinatarios(doc, t.getDestinatarios());
        }

        if (t.getIdExpedientes() != null) {
            for (Integer i : t.getIdExpedientes()) {
                Expediente exp = expRepository.getById(i);
                t.getExpedientes().add(exp);
            }
        }
        if (t.getExpedientes() != null && !t.getExpedientes().isEmpty()) {
//            	updateExpediente(doc, null, null);
        }
    }

    public List<DocumentoDto> getAll() {
        List<Documento> docs = docRepository.getAll();
        List<DocumentoDto> documentos = new ArrayList<>();

        for (Documento doc : docs) {
            List<Expediente> expedientes = detDocRepository.getExpedientesByDoc(doc.getId());
            List<Integer> idExpedientes = detDocRepository.getIdExpedientesByDoc(doc.getId());
            List<Administrativo> destinatarios = detDestRepository.getDestsByDoc(doc.getId());

            documentos.add(new DocumentoDto(doc, destinatarios, expedientes, idExpedientes));
        }

        return documentos;
    }

    public void delete(int id) {
        docRepository.delete(id);
    }

    public DocumentoDto getDtoById(int id) {
        Documento doc = docRepository.getById(id);
        List<Expediente> expedientes = detDocRepository.getExpedientesByDoc(doc.getId());
        List<Integer> idExpedientes = detDocRepository.getIdExpedientesByDoc(doc.getId());
        List<Administrativo> destinatarios = detDestRepository.getDestsByDoc(doc.getId());

        DocumentoDto dto = new DocumentoDto(doc, destinatarios, expedientes, idExpedientes);
        return dto;
    }

    public int getIdByNombre(String nombre) {
        return docRepository.getIdByNombre(nombre);
    }

    /* propios de doc service */
    public String setCorrelativoPorTipoDoc(int idTipoDoc) {
        return setCorrelativo(getAllPorTipoDoc(idTipoDoc));
    }

    private String setCorrelativo(List<Documento> lista) {
        if (lista.isEmpty()) {
            return "001";
        } else {
            Documento doc = lista.get(lista.size()-1);
            if (doc.getFechaEmision().getYear() + 1900 < LocalDate.now().getYear()) {
                return "001";
            } else {
                int n = Integer.parseInt(doc.getCorrelativo().trim());
                n++;
                return String.format("%03d%n", n);
            }
        }
    }

    /* vienen de otros repository */
    public List<Documento> getAllPorTipoDoc(int idTipoDoc) {
        return docRepository.getAllPorTipoDoc(idTipoDoc);
    }
    
    public List<DocumentoDto> getAllPendientes() {
        List<Documento> docs = docRepository.getAllPendientes();
        List<DocumentoDto> documentos = new ArrayList<>();

        for (Documento doc : docs) {
            List<Expediente> expedientes = detDocRepository.getExpedientesByDoc(doc.getId());
            List<Integer> idExpedientes = detDocRepository.getIdExpedientesByDoc(doc.getId());
            List<Administrativo> destinatarios = detDestRepository.getDestsByDoc(doc.getId());

            documentos.add(new DocumentoDto(doc, destinatarios, expedientes, idExpedientes));
        }

        return documentos;
    }
    
    
    public void updateEstadoDocumento(int id) {
        Documento doc = docRepository.getById(id);
        Estado est = estRepository.getByNombre("ENTREGADO");
        if (doc == null || est == null) {
            return;
        }

        doc.setEstado(est);
        docRepository.update(id, doc);
    }

    public void updateDestinatarios(Documento doc, List<Administrativo> nuevosDest) {
        List<Administrativo> antiguosDest = detDestRepository.getDestsByDoc(doc.getId());
        int cantAntiguos = antiguosDest.size();
        int cantNuevos = nuevosDest.size();

        adminRepository.orderByApPaterno(antiguosDest);
        adminRepository.orderByApPaterno(nuevosDest);

        for (int i = 0; i < Math.max(cantAntiguos, cantNuevos); i++) {
            if (i < cantNuevos && i < cantAntiguos) {
                int idDetDest = detDestRepository.getId(doc.getId(), antiguosDest.get(i).getId());
                detDestRepository.update(idDetDest, new DetalleDestinatario(doc, nuevosDest.get(i)));
            }

            if (i >= cantNuevos) {
                // si los nuevos son menos
                int idDetDest = detDestRepository.getId(doc.getId(), antiguosDest.get(i).getId());
                detDestRepository.delete(idDetDest);
            }

            if (i >= cantAntiguos) {
                // si hay mas nuevos
                detDestRepository.add(new DetalleDestinatario(doc, nuevosDest.get(i)));
            }
        }

        /*
			for (int i = 0; i < Math.max(antSize, nuevoSize); i++) {
				if (i < nuevoSize && i < antSize) {
					update(new DetalleDestinatario(doc, antiguosDest.get(i)), nuevosDest.get(i));
				}
				
				if (i >= nuevoSize) {
					// si los nuevos son menos
					delete(doc.getId(), antiguosDest.get(i).getId());
				}
				
				if (i >= antSize) {
					// si hay mas nuevos
					add(new DetalleDestinatario(doc, nuevosDest.get(i)));
				}
			}
         */
    }

    public void deleteDestinatario(int idDoc, int idDest) {
//        
//            Documento doc = getById(idDoc);
//            Administrativo dest = administrativoService.getById(idDest);
//            if (doc == null || dest == null) {
//                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//                return;
//            }
//
//            detDestinatarioService.delete(idDoc, idDest);
//        
    }

    public void deleteDocDependencias(int idDoc) {

//            Documento doc = getById(idDoc);
//            if (doc == null) {
//                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//                return;
//            }
//
//            detDestinatarioService.deleteByDoc(idDoc);
//            detExpedienteService.deleteByDoc(idDoc);
        // aqui tambien se puede borrar segun tipo de doc, pero se tendria que
        // instanciar a oficioservice y no es posible
    }

    public void updateExpediente(int idDoc, int idAntExp, int idNuevoExp) {

        // una lista de exp por si son dos
        // traer todos los expedientes porque son dos nada mas
        // eliminar los dos
        // aumentar los dos (o solo uno)
//            Documento doc = getById(idDoc);
//            Expediente antExp = expedienteService.getById(idAntExp);
//            Expediente nuevoExp = expedienteService.getById(idNuevoExp);
//            if (doc == null || antExp == null || nuevoExp == null) {
//                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//                return;
//            }
//            // if (antExp == null) {
//            //     throw new ValidationException(Validation.showWarning("El antiguo Expediente no puede estar vacío."));
//            // }
//
//            // if (nuevoExp == null) {
//            //     throw new ValidationException(Validation.showWarning("El nuevo Expediente no puede estar vacío."));
//            // }
//            detExpedienteService.updateExp(new DetalleDocumento(doc, antExp), nuevoExp);
    }

    public void updateExpediente(Documento doc, Expediente antExp, Expediente nuevoExp) {
        // si los objetos ya fueron verificados que existen en la database

        if (doc == null || antExp == null || nuevoExp == null) {
            return;
        }

//            detExpedienteService.updateExp(new DetalleDocumento(doc, antExp), nuevoExp);
    }

    public List<Administrativo> getDestinatarios(int idDoc) {
        List<Administrativo> destinatarios = new ArrayList<>();

//            destinatarios = detDestinatarioService.getDestinatariosByDoc(idDoc);
        return destinatarios;
    }

    public List<Expediente> getExpediente(int idDoc) {

//            return detExpedienteService.getExpedientesByDoc(idDoc);
        return Collections.emptyList();
    }

//    public String getAsunto(int idDoc) {
//        Map<String, Object> parametros = new HashMap<>();
//        parametros.put("idDoc", idDoc);
//
//        IDocumento doc = getByQuery("SELECT x FROM Oficio x WHERE x.documento.id = :idDoc", parametros);
//        doc.getAsunto();
//        return doc.getAsunto();
//    }
    public void updateDestinatariosByDoc(Documento doc, List<Administrativo> nuevosDest) {

////    		System.out.println(Validation.showInMagenta(doc.toString()));
////    		List<Administrativo> antiguosDest = getDestinatariosByDoc(doc.getId());
////            int antSize = antiguosDest.size();
//            int nuevoSize = nuevosDest.size();
//            
//            if (nuevoSize < antSize) {
//                System.out.println(Validation.showInMagenta("hay menos destinatarios nuevos"));
//            } else if (nuevoSize > antSize) {
//                System.out.println(Validation.showInMagenta("hay mas destinatarios nuevos"));
//            } else {
//                System.out.println(Validation.showInMagenta("solo actualizando"));
//            }
//
//			for (int i = 0; i < Math.max(antSize, nuevoSize); i++) {
//				if (i < nuevoSize && i < antSize) {
////					update(new DetalleDestinatario(doc, antiguosDest.get(i)), nuevosDest.get(i));
//				}
//				
//				if (i >= nuevoSize) {
//					// si los nuevos son menos
////					delete(doc.getId(), antiguosDest.get(i).getId());
//				}
//				
//				if (i >= antSize) {
//					// si hay mas nuevos
////					add(new DetalleDestinatario(doc, nuevosDest.get(i)));
//				}
//			}
//			
    }

    /* para no instanciar otros service */
    public List<TipoDocumento> getAllTiposDocumento() {
        return tipoDocRepository.getAll();
    }

}
