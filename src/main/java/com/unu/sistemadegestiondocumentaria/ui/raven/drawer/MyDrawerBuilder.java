package com.unu.sistemadegestiondocumentaria.ui.raven.drawer;

import com.unu.sistemadegestiondocumentaria.ui.raven.form.TestForm;
import com.unu.sistemadegestiondocumentaria.Main;
import com.unu.sistemadegestiondocumentaria.service.AdministrativoService;
import com.unu.sistemadegestiondocumentaria.service.DocumentoService;
import com.unu.sistemadegestiondocumentaria.service.ExpedienteService;
import com.unu.sistemadegestiondocumentaria.service.TipoDocumentoService;
import com.unu.sistemadegestiondocumentaria.ui.raven.form.FormDocumentos;
import com.unu.sistemadegestiondocumentaria.ui.raven.form.FormDocumentos;
import com.unu.sistemadegestiondocumentaria.ui.raven.form.FormOficios;
import com.unu.sistemadegestiondocumentaria.ui.raven.form.FormOficios1;
import com.unu.sistemadegestiondocumentaria.ui.raven.tabbed.WindowsTabbed;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

/**
 *
 * @author RAVEN
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("Imagenes/profile.png"), 60, 60, 999))
                .setTitle("Sistema de Gestión Documentaria")
                .setDescription("Oficina de Grados y Títulos");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~DOCUMENTOS~"},
            {"Documentos"},
            {"Crear Documento", "Oficio", "Memorándum", "Acta de Sust."},
            {"~ESTUDIANTES~"},
            {"Sustentaciones"},
            {"Estudiantes"},
            {"~ADMINISTRATIVOS~"},
            {"Administrativos"},
            {"~OTROS~"},
            {"Cerrar Sesión"}
        };

        String icons[] = {
            "documentos.svg",
            "crear-documento.svg",
            "sustentaciones.svg",
            "estudiantes.svg",
            "administrativos.svg",
            "cerrar-sesion.svg"
        };

        return new SimpleMenuOption()
                .setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("Icons")
                .setIconScale(0.45f)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction action, int index, int subIndex) {
                        if (index == 0) {
                            WindowsTabbed.getInstance().addTab("Documentos", new FormDocumentos());
                        } else if (index == 1 && subIndex == 1) {
                            // si es Esditar title = "Editar Oficio"
                            WindowsTabbed.getInstance().addTab("Oficio", new FormOficios1(true));
                        } else if (index == 1 && subIndex == 2) {
//                            WindowsTabbed.getInstance().addTab("Memorándum", new FormDocumentos());
                        } else if (index == 1 && subIndex == 3) {
//                            WindowsTabbed.getInstance().addTab("Acta de Sust.", new FormDocumentos());
                        } else if (index == 2) {
//                            WindowsTabbed.getInstance().addTab("Sustentaciones", new FormDocumentos());
                        } else if (index == 3) {
//                            WindowsTabbed.getInstance().addTab("Estudiantes", new FormDocumentos());
                        } else if (index == 4) {
//                            WindowsTabbed.getInstance().addTab("Administrativos", new FormDocumentos());
                        } else if (index == 5) {
                            Main.main.login();
                        }
//                        System.out.println("Menu selected " + index + " " + subIndex);
                    }

                })
                .setMenuValidation(new MenuValidation() {
                    @Override
                    public boolean menuValidation(int index, int subIndex) {
//                        if(index==0){
//                            return false;
//                        }else if(index==3){
//                            return false;
//                        }
                        return true;
                    }

                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Universidad Nacional de Ucayali")
                .setDescription("Pucallpa - 2025");
    }

    @Override
    public int getDrawerWidth() {
        return 275;
    }


}
