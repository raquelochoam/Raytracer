package edu.up.isgc.raytracer.tools;

public class Material {

    private double shinnines;
    private boolean isReflection;
    private boolean isReflactive;

    public double getShinnines() {
        return shinnines;
    }

    public void setShinnines(double shinnines) {
        this.shinnines = shinnines;
    }

    public boolean isReflection() {
        return isReflection;
    }

    public void setReflection(boolean reflection) {
        isReflection = reflection;
    }

    public boolean isReflactive() {
        return isReflactive;
    }

    public void setReflactive(boolean reflactive) {
        isReflactive = reflactive;
    }

    public Material(double shinnines, boolean isReflection, boolean isReflactive) {
        this.shinnines = shinnines;
        this.isReflection = isReflection;
        this.isReflactive = isReflactive;
    }
}