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


//import org.vaadin.hezamu.canvas.Canvas;


@SuppressWarnings("serial")

@com.vaadin.annotations.JavaScript("js/canvas.js")
public class Menu extends CustomComponent {


    public Menu() {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout inputDataLayout = new HorizontalLayout();

     /*   VerticalLayout checkBoxLayotX = new VerticalLayout();
        Label coordX = new Label("Координата X:");
        checkBoxLayotX.addComponent(coordX);
        int value = -3;
        for (int i = 0; i < 8; i++) {
            CheckBox xCheckBox = new CheckBox(String.valueOf(value));
            checkBoxLayotX.addComponent(xCheckBox);
            value++;
        } */

     //Чекбокс группы для X и радиууса

        CheckBoxGroup<Integer> groupX = new CheckBoxGroup<>("Координата X:");
        groupX.setItems(-3,-2,-1,0,1,2,3,4,5);
//        groupX.addSelectionListener(multiSelectionEvent -> {
//
//            if(groupX.getSelectedItems().size() > 1){ //Вешаю листенер, если выбрано больше двух элементов, то он стирает все
//                groupX.deselectAll();
//            }
//        });

        CheckBoxGroup<Integer> groupR = new CheckBoxGroup<>("Радиус:");
        groupR.setItems(1,2,3,4,5);
        groupR.addSelectionListener(multiSelectionEvent -> {

            if(groupR.getSelectedItems().size() > 1){
                groupR.deselectAll();
            }
        });


     /*   VerticalLayout checkBoxLayotR = new VerticalLayout();  Старые чекбоксы
        Label coordR = new Label("Радиус:");
        checkBoxLayotR.addComponent(coordR);
        value = 1;
        for (int i = 0; i < 6; i++) {
            CheckBox xCheckBox = new CheckBox(String.valueOf(value));
            checkBoxLayotR.addComponent(xCheckBox);
            value++;
        }*/

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

        layout.addComponent(submittButton());

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

    private Button submittButton() {
        Button button = new Button("Отправить", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (getUI().getSession().getAttribute("sesFlag") == null){
                    getUI().getNavigator().navigateTo(MyVaadinApplication.LOGINVIEW);
                }
            }
        });
        return button;
    }

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

        @Override
        public String toString() {
            return String.format("Point [coordX=%s, coordY=%s, coordR=%s, status=%s]", coordX,
                    coordY, coordR, status);
        }
    }
}