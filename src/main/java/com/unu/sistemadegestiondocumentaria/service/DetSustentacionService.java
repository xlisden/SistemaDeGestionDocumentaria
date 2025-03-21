package com.unu.sistemadegestiondocumentaria.service;

import com.unu.sistemadegestiondocumentaria.entity.DetalleSustentacion;
import com.unu.sistemadegestiondocumentaria.entity.Expediente;
import com.unu.sistemadegestiondocumentaria.entity.Sustentacion;
import com.unu.sistemadegestiondocumentaria.repository.Repository;
import com.unu.sistemadegestiondocumentaria.validations.*;

public class DetSustentacionService extends Repository<DetalleSustentacion> {

	private static DetSustentacionService INSTANCIA;

	private final ExpedienteService expService = ExpedienteService.instanciar();
	private final SustentacionService sustService = SustentacionService.instanciar();

	private DetSustentacionService(Class<DetalleSustentacion> type) {
		super(type);
	}

	public static DetSustentacionService instanciar() {
		if (INSTANCIA == null) {
			INSTANCIA = new DetSustentacionService(DetalleSustentacion.class);
		}
		return INSTANCIA;
	}

	@Override
	public void add(DetalleSustentacion t) {
		try {
			Expediente exp = expService.getById(t.getIdExp());
			Sustentacion sust = sustService.getById(t.getIdSust());
			if (exp == null || sust == null) {
				return;
			}

			t.setExpediente(exp);
			t.setSustentacion(sust);
			Validation.validateDetSustentacion(t);

			super.add(t);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
	}
	
	public void addDet(int idExp, Sustentacion s) {
		try {
			Expediente exp = expService.getById(idExp);
			if (exp == null) {
				return;
			}
			
			sustService.add(s);
			
			Sustentacion sust = sustService.getLast();
			if(sust.getId() == getLast().getSustentacion().getId()) {
				return;
			}
			
			DetalleSustentacion detSust = new DetalleSustentacion();
			detSust.setExpediente(exp);
			detSust.setSustentacion(sust);
			Validation.validateDetSustentacion(detSust);

			super.add(detSust);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
	}

	@Override
	public void update(int id, DetalleSustentacion t) {
		try {
			DetalleSustentacion detSust = super.getById(id);
			if (detSust == null) {
				return;
			}
			
			Expediente exp = expService.getById(t.getIdExp());
			Sustentacion sust = sustService.getById(t.getIdSust());
			if (exp == null || sust == null) {
				return;
			}

			detSust.setExpediente(exp);
			detSust.setSustentacion(sust);
			Validation.validateDetSustentacion(detSust);
			
			super.update(id, detSust);
		} catch (ValidationException e) {
			e.printConsoleMessage();
		}
	}
    public void delete(int id) {
        try {
            super.delete(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
    }

    @Override
    public DetalleSustentacion getById(int id) {
        try {
            return super.getById(id);
        } catch (ValidationException e) {
            e.printConsoleMessage();
        }
        return null;
    }
}
