package com;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import lab4.CheckBean;

import javax.ejb.EJB;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


//import org.vaadin.hezamu.canvas.Canvas;


@SuppressWarnings("serial")

@com.vaadin.annotations.JavaScript("js/canvas.js")
public class Menu extends CustomComponent {

    @EJB
    private CheckBean check = new CheckBean();

    public Menu() {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout inputDataLayout = new HorizontalLayout();


     //Чекбокс группы для X и радиууса

        CheckBoxGroup<Integer> groupX = new CheckBoxGroup<>("Координата X:");
        groupX.setItems(-3,-2,-1,0,1,2,3,4,5);

        CheckBoxGroup<Integer> groupR = new CheckBoxGroup<>("Радиус:");
        groupR.setItems(1,2,3,4,5);
        groupR.addSelectionListener(multiSelectionEvent -> {

            if(groupR.getSelectedItems().size() > 1){
                groupR.deselectAll();
            }
        });


     // Текстфилд и валидация для него

        VerticalLayout checkBoxLayotY = new VerticalLayout();
        Label coordY = new Label("Координата Y:");
        TextField textY = new TextField();
        Binder<Point> binder = new Binder<>();
        binder.forField(textY)
                .withConverter ( new StringToDoubleConverter( "Input must be Double" ) )
                .withValidator(new DoubleRangeValidator("Value must be a number between -3.0 and 5.0",-3.0, 5.0))
                .bind (Point::getY,Point::setY);
        checkBoxLayotY.addComponents(coordY, textY);

        inputDataLayout.addComponents(groupX,groupR,checkBoxLayotY);
        layout.addComponent(inputDataLayout);


        Button submittButton = new Button("Отправить");
        submittButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if (getUI().getSession().getAttribute("sesFlag") == null){
                    getUI().getNavigator().navigateTo(MyVaadinApplication.LOGINVIEW);
                }
                else {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    ArrayList<Integer> rad = new ArrayList<Integer>();
                    list.addAll(groupX.getSelectedItems());
                    rad.addAll(groupR.getSelectedItems());
                    Integer radius = rad.get(0);
                    for(int i = 0; i < list.size(); i++){
                        check.check(list.get(i), Double.parseDouble(textY.getValue()), radius);
                    }
                }
            }
        });
        layout.addComponent(submittButton);
        //Тут добавляем таблицу

        Grid<Point> pointsTable = new Grid<>();
        pointsTable.addColumn(Point::getX).setCaption("Координата X");
        pointsTable.addColumn(Point::getY).setCaption("Координата Y");
        pointsTable.addColumn(Point::getR).setCaption("Радиус");
        pointsTable.addColumn(Point::getStatus).setCaption("Попадание");
        layout.addComponent(pointsTable);
        layout.addComponent(logoutButton());

        layout.setSizeUndefined();
        layout.setSpacing(true);
        setSizeUndefined();


        StringBuilder script = new StringBuilder();
        // @formatter:off

        setCompositionRoot(layout);


    }


    private Button logoutButton() {
        Button button = new Button("Logout", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getSession().close();
                getUI().getPage().setLocation(getLogoutPath());
            }
        });
        return button;
    }

//    private Button submittButton() {
//        Button button = new Button("Отправить", new Button.ClickListener() {
//            @Override
//            public void buttonClick(ClickEvent event) {
//                if (getUI().getSession().getAttribute("sesFlag") == null){
//                    getUI().getNavigator().navigateTo(MyVaadinApplication.LOGINVIEW);
//                }
//            }
//        });
//        return button;
//    }

    private String getLogoutPath() {
        return getUI().getPage().getLocation().getPath();
    }


    public static class Point {

        private double coordX;
        private double coordY;
        private double coordR;
        private boolean status;


        public void setX(double x) {
            this.coordX = x;
        }

        public double getX() {
            return coordX;
        }

        public void setY(double y) {
            this.coordY = y;
        }

        public double getY() {
            return coordY;
        }

        public void setR(double R) {
            this.coordR = R;
        }

        public double getR() {
            return coordR;
        }

        public void setStatus(boolean value) {
            this.status = value;
        }

        public boolean getStatus() {
            return status;
        }

//        @Override
//        public String toString() {
//            return String.format("Point [coordX=%s, coordY=%s, coordR=%s, status=%s]", coordX,
//                    coordY, coordR, status);
//        }
    }
}