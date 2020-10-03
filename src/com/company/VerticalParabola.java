package com.company;

public class VerticalParabola {
    double a,b,c;
    public VerticalParabola(double a, double b, double c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public boolean isAboveParabola(double x, double y){
        return y>=a*x*x+b*x+c;
    }
}
