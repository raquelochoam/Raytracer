/**
 * [1968] - [2022] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;
/**
 * @author Jafet Rodriguez and Raquel
 *
 */
public class Vector3D {
    //VECTOR ZERO
    private static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    private double x, y, z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Constructor Vector3D
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     *
     * @param vectorA
     * @param vectorB
     * @return a double result of dot product of vector A and B
     */
    public static double dotProduct(Vector3D vectorA, Vector3D vectorB){
        return (vectorA.getX() * vectorB.getX()) + (vectorA.getY() * vectorB.getY()) + (vectorA.getZ() * vectorB.getZ());
    }

    /**
     *
     * @param vectorA
     * @param vectorB
     * @return a Vector3D result of cross product of vector A and B
     */
    public static Vector3D crossProduct(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D((vectorA.getY() * vectorB.getZ()) - (vectorA.getZ() * vectorB.getY()),
                (vectorA.getZ() * vectorB.getX()) - (vectorA.getX() * vectorB.getZ()),
                (vectorA.getX() * vectorB.getY()) - (vectorA.getY() * vectorB.getX()));
    }

    /**
     *
     * @param vectorA
     * @return a double magnitude or distance of a Vector A
     */
    public static double magnitude (Vector3D vectorA){
        return Math.sqrt(dotProduct(vectorA, vectorA));
    }

    /**
     *
     * @param vectorA
     * @param vectorB
     * @return a add of vector A and B
     */
    public static Vector3D add(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY(), vectorA.getZ() + vectorB.getZ());
    }

    /**
     *
     * @param vectorA
     * @param vectorB
     * @return the substract of vector A and B
     */
    public static Vector3D substract(Vector3D vectorA, Vector3D vectorB){
        return new Vector3D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY(), vectorA.getZ() - vectorB.getZ());
    }

    /**
     *
     * @param vectorA
     * @return vectorA normalize
     */
    public static Vector3D normalize(Vector3D vectorA){
        double mag = Vector3D.magnitude(vectorA);
        return new Vector3D(vectorA.getX() / mag, vectorA.getY() / mag, vectorA.getZ() / mag);
    }

    /**
     *
     * @param vectorA
     * @param scalar
     * @return the scalar multiplication of a vector A with a double scalar
     */
    public static Vector3D scalarMultiplication(Vector3D vectorA, double scalar){
        return new Vector3D(vectorA.getX() * scalar, vectorA.getY() * scalar, vectorA.getZ() * scalar);
    }

    /**
     *
     * @param v1
     * @param v2
     * @return the distance between 2 Vectors
     */
    public static double distancePoints(Vector3D v1, Vector3D v2){
        return Math.sqrt(Math.pow(v1.getX()- v2.getX(), 2) + Math.pow(v1.getY()- v2.getY(), 2) + Math.pow(v1.getZ()- v2.getZ(), 2) );
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Vector3D clone(){
        return new Vector3D(getX(), getY(), getZ());
    }

    /**
     *
     * @return a Vector Zero, (x=0,y=0,z=0)
     */
    public static Vector3D ZERO(){
        return ZERO.clone();
    }
}