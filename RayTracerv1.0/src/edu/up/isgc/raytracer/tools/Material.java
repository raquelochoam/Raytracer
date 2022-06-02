package edu.up.isgc.raytracer.tools;

/**
 * @author Raquel Ochoa
 *
 */
public class Material {

    private double shinnines;
    private boolean isReflection;
    private boolean isRefractive;

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

    public boolean isRefractive() {
        return isRefractive;
    }

    public void setRefractive(boolean refractive) {
        isRefractive = refractive;
    }

    /**
     * Constructor
     * @param shinnines
     * @param isReflection
     * @param isReflactive
     */
    public Material(double shinnines, boolean isReflection, boolean isReflactive) {
        this.shinnines = shinnines;
        this.isReflection = isReflection;
        this.isRefractive = isReflactive;
    }
}