import java.awt.*;

public class Planet {
    public double xxPos, yyPos, xxVel, yyVel, mass;
    private static final double G = 6.67e-11;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public Boolean equals(Planet p){
        return xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel &&
                yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = xxPos - p.xxPos;
        double dy = yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public double calcForceExertedBy(Planet p){
        double r2 = calcDistance(p) * calcDistance(p);
        return mass * p.mass * G / r2;
    }
    public double calcForceExertedByX(Planet p){
        double Force = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffX = p.xxPos - xxPos;
        return Force * diffX / r;
    }
    public double calcForceExertedByY(Planet p){
        double Force = calcForceExertedBy(p);
        double r = calcDistance(p);
        double diffY = p.yyPos - yyPos;
        return Force * diffY / r;
    }
    public double calcNetForceExertedByX(Planet []allPlants){

        double Force_sum_X = 0;
        for(Planet p: allPlants){
            if(this.equals(p)){continue;}
            else{Force_sum_X += calcForceExertedByX(p);}
        }
        return Force_sum_X;
    }
    public double calcNetForceExertedByY(Planet []allPlants){
        double Force_sum_Y = 0;
        for(Planet p: allPlants){
            if(this.equals(p)){continue;}
            else{Force_sum_Y += calcForceExertedByY(p);}
        }
        return Force_sum_Y;
    }
    public void update(double dt, double fx, double fy){
        xxVel += dt * fx / mass;
        yyVel += dt * fy / mass;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
