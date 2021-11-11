package com.example.web3.model;

public class PointAreaChecker implements AreaChecker<Point> {

    public boolean check(Point p){
        p.setResult(inFirstQuarter(p) && (p.getY() <= p.getR() - p.getX()) ||
                inSecondQuarter(p) && (p.getX() >= -p.getR() / 2) && (p.getY() <= p.getR()) ||
                inFourthQuarter(p) && (p.getX() * p.getX() + p.getY() * p.getY() <= p.getR() * p.getR() / 4));
        return p.isResult();
    }

    private boolean inFirstQuarter(Point p){
        return p.getX() >= 0 && p.getY() >= 0;
    }

    private boolean inSecondQuarter(Point p){
        return p.getX() <= 0 && p.getY() >= 0;
    }

    private boolean inThirdQuarter(Point p){
        return p.getX() <= 0 && p.getY() <= 0;
    }

    private boolean inFourthQuarter(Point p){
        return p.getX() >= 0 && p.getY() <= 0;
    }
}
