package com;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.server.*;
import com.vaadin.ui.UI;
import com.vaadin.ui.*;
import com.vaadin.ui.VerticalLayout;


@SuppressWarnings("serial")

public class MyVaadinApplication extends UI {

    protected static class Servlet extends VaadinServlet {
    }
    public Navigator navigator;

    public static final String MAINVIEW = "main";
    public static final String LOGINVIEW = "";


    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
        ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
        navigator = new Navigator(UI.getCurrent(), viewDisplay);
        navigator.addView(LOGINVIEW, new LoginView());
        navigator.addView(MAINVIEW, new MainView());
    }

}