package lab4;

import javax.ejb.Stateful;
//import javax.ws.rs.*;

//@Singleton(name = "UserEJB")
@Stateful
public class CheckBean {
//    public CheckBean() {
//    }

    public void check(double coordX, double coordY, double rad){//(String x, String y, String radius){
        //try {
            boolean status = false;
            //double coordX = Double.parseDouble(x);
            //double coordY = Double.parseDouble(y);
            //double rad = Double.parseDouble(radius);
            if (coordX >= -rad && coordX <= 0 && coordY >= -rad && coordY <= 0 || coordX >= 0 && coordY <= 0 && Math.pow(coordX, 2) + Math.pow(coordY, 2) <= Math.pow(rad, 2) || coordX <= 0 && coordY >= 0 && (coordY - coordX) <= rad / 2) {
                status = true;
            }
            ResultsEntity.addElem(coordX, coordY, rad, status);

        //}
        //catch (Exception e){}
    }
}
