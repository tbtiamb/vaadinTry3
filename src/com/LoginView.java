package com;


import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.*;

import java.io.*;

import com.vaadin.server.*;
import com.vaadin.ui.Button.ClickEvent;
import lab4.UserControllerBean;
import org.apache.http.HttpResponse;
import org.eclipse.jetty.util.security.Password;

import javax.ejb.EJB;
import javax.ejb.Stateful;


@SuppressWarnings("serial")
@Stateful
public class LoginView extends VerticalLayout implements View {
    @EJB
    private UserControllerBean userController = new UserControllerBean();

    public LoginView() {
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label label = new Label("Enter your information below to log in.");
        Label labelLaba = new Label("Лабораторная рабоа № 4");
        Label labelNames = new Label("Божик, Егоров, Лысенко");
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");

        Button loginButton = new Button("Log in");

        loginButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                //lab4.UserControllerBean user = new UserControllerBean();
                //user.authentication()
                if(userController.authentication(username.getValue(), password.getValue())){
                    getUI().getSession().setAttribute("sesFlag", getUI().getSession().getSession().getId());
                    getUI().getNavigator().navigateTo(MyVaadinApplication.MAINVIEW);
                }
            }
        });

        Button registerButton = new Button("Register");
        registerButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                userController.addUser(username.getValue(), password.getValue());
                getUI().getNavigator().navigateTo(MyVaadinApplication.MAINVIEW);
            }
        });


        //     if (getUI().getSession().getAttribute("sesFlag") == null){
        //        getUI().getNavigator().navigateTo(MyVaadinApplication.LOGINVIEW);
        //  }

        String basepath = VaadinService.getCurrent()
                .getBaseDirectory().getAbsolutePath();

        FileResource resource = new FileResource(new File(basepath +
                "/images/logo.png"));

        Image image = new Image(null, resource);
        image.setWidth("1020px");
        image.setHeight("210px");

        //Binder binder = new Binder<>();
        addComponent(image);
        addComponent(labelLaba);
        addComponent(labelNames);
        addComponent(label);
        // binder.bind(username, "user");
        // binder.bind(password, "passw");
        addComponent(username);
        addComponent(password);
        HorizontalLayout layout = new HorizontalLayout();
        //layout.addComponents(loginButton(), registerButton());
        layout.addComponent(loginButton);
        layout.addComponent(registerButton);
        addComponent(layout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome! Please log in.");
    }

}
