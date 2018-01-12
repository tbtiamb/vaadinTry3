package com;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View {

    public MainView() {
        setSizeFull();
        setSpacing(true);
        addComponent(new Menu());

    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Showing view: Main!");
    }




}