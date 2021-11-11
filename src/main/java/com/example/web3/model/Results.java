package com.example.web3.model;

import com.example.web3.data.PSQLController;
import com.example.web3.validators.RValidator;
import com.example.web3.validators.XValidator;
import com.example.web3.validators.YValidator;
import lombok.Getter;
import lombok.Setter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Results implements Serializable {

    private AreaChecker<Point> areaChecker;
    private XValidator xValidator;
    private YValidator yValidator;
    private RValidator rValidator;

    private PSQLController dbController;

    @Getter
    @Setter
    private Point formPoint;

    @Getter
    @Setter
    private Point plotPoint;

    private List<Point> points;

    public Results(){
        areaChecker = new PointAreaChecker();
        dbController = new PSQLController();
        xValidator = new XValidator();
        yValidator = new YValidator();
        rValidator = new RValidator();
        formPoint = new Point();
        plotPoint = new Point();
    }

    public void addFormPoint() throws IOException {
        Point p = new Point(formPoint);
        addPoint(p);
    }

    public void addPlotPoint() throws IOException {
        Point p = new Point(plotPoint);
        addPoint(p);
    }

    private void addPoint(Point p) throws IOException {
        if (!xValidator.validate(p.getX())){
            goToErrorPage("X must be in range (-5; 5)");
            return;
        }
        if (!yValidator.validate(p.getY())){
            goToErrorPage("Y must be in range (-3; 3)");
            return;
        }
        if (!rValidator.validate(p.getR())){
            goToErrorPage("R must be in {-1, -2, -3, -4, -5}");
            return;
        }
        areaChecker.check(p);
        if(dbController.addPoint(p)){
            points.add(p);
        }
//        areaChecker.check(p);
//        points.add(p);
    }

    private void goToErrorPage(String errorMessage) throws IOException {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("errorMessage", errorMessage);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/error.xhtml");
    }

    public List<Point> getPoints() {
        if (points == null){
            points = dbController.getAll();
        }
        return points;
    }

    public void clear(){
        if (dbController.clear()){
            points.clear();
        }
    }
}
