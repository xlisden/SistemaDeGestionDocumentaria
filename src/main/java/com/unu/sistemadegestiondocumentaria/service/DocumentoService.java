package com.unu.sistemadegestiondocumentaria.service;

import java.time.LocalDate;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDocumento;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.entity.DocumentoDto;
import com.unu.sistemadegestiondocumentaria.entity.Estado;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Oficio;
import com.unu.sistemadegestiondocumentaria.entity.TipoDocumento;
import com.unu.sistemadegestiondocumentaria.repository.AdministrativoRepository;
import com.unu.sistemadegestiondocumentaria.repository.DetalleDestinatarioRepository;
import com.unu.sistemadegestiondocumentaria.repository.DetalleDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.DocumentoRepository;
import com.unu.sistemadegestiondocumentaria.repository.EstadoRepository;
import com.unu.sistemadegestiondocumentaria.repository.ExpedienteRepository;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.repository.TipoDocumentoRepository;
import com.unu.sistemadegestiondocumentaria.validations.Validation;
import com.unu.sistemadegestiondocumentaria.validations.ValidationException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.unu.sistemadegestiondocumentaria.entity.IDocumento;

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
            t.setCorrelativo(setCorrelativo());

            docRepository.add(t);            
           
            for (Integer i : t.getIdDestinatarios()) {
                Administrativo dest  = adminRepository.getById(i);
                // aqui es != porque no queremos que termine el proceso si un dest es incorrecto, queremos que siga
                if (dest != null) {
                    t.getDestinatarios().add(dest);
                    detDestRepository.add(new DetalleDestinatario(t, dest));
                }
            }
            
            for (Integer i : t.getIdExpedientes()) {
                Expediente exp  = expRepository.getById(i);
                // aqui es != porque no queremos que termine el proceso si un dest es incorrecto, queremos que siga
                if (exp != null) {
                    t.getExpedientes().add(exp);
                    detDocRepository.add(new DetalleDocumento(t, exp));
                }
            }            
    }
    
    public void update(int id, Documento t) {
        try {
            Documento doc = docRepository.getById(id);
            if (doc == null) {
//                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
                return;
            }

            TipoDocumento td = tipoDocRepository.getById(t.getIdTipoDoc());
            Administrativo emisor = adminRepository.getById(t.getIdEmisor());
            String correlativo = t.getCorrelativo() != null ? t.getCorrelativo() : doc.getCorrelativo();
            Estado estado = t.getEstado() != null ? t.getEstado() : doc.getEstado();
            Date fechaEmision = t.getFechaEmision() != null ? t.getFechaEmision() : doc.getFechaEmision();

            if (emisor == null || td == null || correlativo.isEmpty() || estado == null || fechaEmision == null) {
                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
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

                    if (dest != null) { // aqui es != porque no queremos que termine el proceso si un dest es incorrecto, queremos que siga
                        t.getDestinatarios().add(dest);
                    }
                }
            }            
            if (t.getDestinatarios() != null && !t.getDestinatarios().isEmpty()) { // si es nulo, no hay necesidad de actualizar
                updateDestinatarios(doc, t.getDestinatarios());
            }
//            doc.setDestinatarios(getDestinatarios(doc.getId()));

//            setDocumento(doc, t);
//            Validation.validateDocumento(doc);


        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    
    public List<DocumentoDto> getAll() {
    	List<Documento> docs = docRepository.getAll();
        List<DocumentoDto> documentos = new ArrayList<DocumentoDto>();
        
        for (Documento doc : docs) {
            List<Expediente> expedientes = detDocRepository.getExpedientesByDoc(doc.getId());
            List<Administrativo> destinatarios = detDestRepository.getDestsByDoc(doc.getId());
            
            documentos.add(new DocumentoDto(doc, destinatarios, expedientes));
        }
        
        return documentos;
    }
    
    public void delete(int id) {
//        Documento doc = docRepository.getById(id);
//        if (doc == null) {
//        	return;
//        }
        
        // tienen que ir en oficio, memo y acta. Porque estos 3 tienen delete en cascada para doc
        // por lo que deben ser eliminados alla primero
//        detDestRepository.deleteByDoc(id);
//        detDocRepository.deleteByDoc(id);
        docRepository.delete(id);
    }

    public DocumentoDto getById(int id) {
        Documento doc = docRepository.getById(id);
        List<Expediente> expedientes = detDocRepository.getExpedientesByDoc(doc.getId());
        List<Administrativo> destinatarios = detDestRepository.getDestsByDoc(doc.getId());
        
        DocumentoDto dto = new DocumentoDto(doc, destinatarios, expedientes);
        return dto;
    }
    
    /* propios de doc service */
    private String setCorrelativo() {
        if (docRepository.getAll().isEmpty()) {
            return "001";
        } else {
            Documento doc = docRepository.getLast();
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
    
    public void updateEstadoDocumento(int id) {
        try {
        	// verificar el add y update de docservice. Guiarse de add en booleano
//            Documento doc = getById(id);
//            Estado est = estadoService.getByNombre("ENTREGADO");
//            if (doc == null || est == null) {
//                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//                return;
//            }
//
//            doc.setEstado(est);
//            super.update(id, doc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateDestinatario(int idDoc, int idAntDest, int idNuevoDest) {
        try {
//            Documento doc = getById(idDoc);
//            Administrativo nuevoDest = administrativoService.getById(idNuevoDest);
//            Administrativo antDest = administrativoService.getById(idAntDest);
//            if (doc == null || nuevoDest == null || antDest == null) {
//                throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//            }
//
//            detDestinatarioService.update(new DetalleDestinatario(doc, antDest), nuevoDest);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateDestinatarios(Documento doc, List<Administrativo> nuevosDest) {
        try {
//            if (doc == null || nuevosDest.isEmpty()) {
//                System.out.println("en el return");
//                if (nuevosDest.isEmpty()) {
//                    System.out.println("nuevosDest.isEmpty()");
//                }
//                return;
//            }
//
//            detDestinatarioService.updateDestinatariosByDoc(doc, nuevosDest);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void deleteDestinatario(int idDoc, int idDest) {
//        try {
//            Documento doc = getById(idDoc);
//            Administrativo dest = administrativoService.getById(idDest);
//            if (doc == null || dest == null) {
//                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//                return;
//            }
//
//            detDestinatarioService.delete(idDoc, idDest);
//        } catch (ValidationException e) {
//            e.printConsoleMessage();
//        }
    }

    public void deleteDocDependencias(int idDoc) {
        try {
//            Documento doc = getById(idDoc);
//            if (doc == null) {
//                // throw new ValidationException(Validation.showWarning("El Documento no puede estar vacío."));
//                return;
//            }
//
//            detDestinatarioService.deleteByDoc(idDoc);
//            detExpedienteService.deleteByDoc(idDoc);
            // aqui tambien se puede borrar segun tipo de doc, pero se tendria que instanciar a oficioservice y no es posible
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void updateExpediente(int idDoc, int idAntExp, int idNuevoExp) {
        try {
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

//            detExpedienteService.updateExp(new DetalleDocumento(doc, antExp), nuevoExp);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }



    public List<Administrativo> getDestinatarios(int idDoc) {
        List<Administrativo> destinatarios = new ArrayList<>();
        try {
//            destinatarios = detDestinatarioService.getDestinatariosByDoc(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return destinatarios;
    }

    public List<Expediente> getExpediente(int idDoc) {
        try {
//            return detExpedienteService.getExpedientesByDoc(idDoc);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
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
    	try {
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
    	} catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    /* para no instanciar otros service */    
    public List<TipoDocumento> getAllTiposDocumento() {
        return tipoDocRepository.getAll();
    }    
    
}
