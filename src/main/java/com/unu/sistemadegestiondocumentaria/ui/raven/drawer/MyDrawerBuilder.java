package com.unu.sistemadegestiondocumentaria.ui.raven.drawer;

import com.unu.sistemadegestiondocumentaria.ui.raven.form.TestForm;
import com.unu.sistemadegestiondocumentaria.Main;
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
//            {"~MAIN~"},
//            {"Dashboard"},
//            {"~WEB APP~"},
//            {"Email", "Inbox", "Read", "Compost"},
//            {"Chat"},
//            {"Calendar"},
//            {"~COMPONENT~"},
//            {"Advanced UI", "Cropper", "Owl Carousel", "Sweet Alert"},
//            {"Forms", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
//            {"~OTHER~"},
//            {"Charts", "Apex", "Flot", "Sparkline"},
//            {"Icons", "Feather Icons", "Flag Icons", "Mdi Icons"},
//            {"Special Pages", "Blank page", "Faq", "Invoice", "Profile", "Pricing", "Timeline"},
//            {"Logout"}            
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
//            "dashboard.svg",
//            "email.svg",
//            "chat.svg",
//            "calendar.svg",
//            "ui.svg",
//            "forms.svg",
//            "chart.svg",
//            "icon.svg",
//            "page.svg",
//            "logout.svg"
            "documentos.svg",
            "email.svg",
            "calendar.svg",
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
                            WindowsTabbed.getInstance().addTab("Test Form", new TestForm());
                        } else if (index == 5) {
                            Main.main.login();
                        }
                        System.out.println("Menu selected " + index + " " + subIndex);
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
