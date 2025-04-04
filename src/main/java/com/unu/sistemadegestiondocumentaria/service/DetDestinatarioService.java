package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.Administrativo;
import com.unu.sistemadegestiondocumentaria.entity.DetalleDestinatario;
import com.unu.sistemadegestiondocumentaria.entity.Documento;
import com.unu.sistemadegestiondocumentaria.repository.DetalleDestinatarioRepository;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetDestinatarioService {

    private static DetDestinatarioService INSTANCIA;
    
    private DetalleDestinatarioRepository detDestRepository;

    private DetDestinatarioService() {
        detDestRepository = DetalleDestinatarioRepository.instanciar();
    }

    public static DetDestinatarioService instanciar() {
        if (INSTANCIA == null) {
            INSTANCIA = new DetDestinatarioService();
        }
        return INSTANCIA;
    }

    
    public void add(DetalleDestinatario t) {
        detDestRepository.add(t);
    }

    public void update(DetalleDestinatario t, Administrativo nuevoDest) {
        try {
            int id = getId(t.getDocumento().getId(), t.getDestinatario().getId());

            DetalleDestinatario detDest = getById(id);
            if (detDest == null) {
                // throw new ValidationException(Validation.showWarning("El Det. Destinatario no puede estar vacío."));
                return;
            }

            Validation.validateDetDestinatario(t);
            detDest.setDestinatario(nuevoDest);

//            super.update(id, detDest);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    public void delete(int idDoc, int idDest) {        
        try {
            int id = getId(idDoc, idDest);
//            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    
    public DetalleDestinatario getById(int id) {
        try {
//            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }

    public int getId(int idDoc, int idDest) {
        int id = 0;
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idDoc", idDoc);
            parametros.put("idDest", idDest);

//            DetalleDestinatario detDest = getByQuery( "SELECT x FROM DetalleDestinatario x WHERE x.documento.id = :idDoc AND x.destinatario.id = :idDest", parametros);
//            if (detDest == null){
//                return 0;
//            }
//
//            id = detDest.getId();
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return id;
    }

    public void deleteByDoc(int idDoc) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idDoc", idDoc);
            
//            deleteOrUpdateByQuery("DELETE FROM DetalleDestinatario x WHERE x.documento.id = :idDoc", parametros);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }
    
    public List<Administrativo> getDestinatariosByDoc(int idDoc) {
        List<Administrativo> destinatarios = new ArrayList<>();
        try {
//            for(DetalleDestinatario detDest: getAll()){
//                if (detDest.getDocumento().getId() == idDoc) {
//                    destinatarios.add(detDest.getDestinatario());
//                }
//            }
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return destinatarios;
    }
    
    public void updateDestinatariosByDoc(Documento doc, List<Administrativo> nuevosDest) {
    	try {
//    		System.out.println(Validation.showInMagenta(doc.toString()));
    		List<Administrativo> antiguosDest = getDestinatariosByDoc(doc.getId());
            int antSize = antiguosDest.size();
            int nuevoSize = nuevosDest.size();
            
            if (nuevoSize < antSize) {
                System.out.println(Validation.showInMagenta("hay menos destinatarios nuevos"));
            } else if (nuevoSize > antSize) {
                System.out.println(Validation.showInMagenta("hay mas destinatarios nuevos"));
            } else {
                System.out.println(Validation.showInMagenta("solo actualizando"));
            }

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
			
    	} catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

}
