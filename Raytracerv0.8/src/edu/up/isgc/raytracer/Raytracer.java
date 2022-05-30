/**
 * [1968] - [2022] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;


import edu.up.isgc.raytracer.lights.DirectionalLight;
import edu.up.isgc.raytracer.lights.Light;
import edu.up.isgc.raytracer.lights.PointLight;
import edu.up.isgc.raytracer.objects.*;
import edu.up.isgc.raytracer.tools.Material;
import edu.up.isgc.raytracer.tools.OBJReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;

public class Raytracer {

    public static void main(String[] args) {
        System.out.println(new Date());

        Material material1 = new Material(45, false, false);
        Material material2 = new Material(45,true, false);

        Scene scene02 = new Scene();
        scene02.setCamera(new Camera(new Vector3D(0, .5, -8), 160, 160, 800, 800, 8.2f, 50f));
        scene02.addLight(new PointLight(new Vector3D(0f, 2f, 0f), Color.WHITE, 1.1));
        scene02.addLight(new PointLight(new Vector3D(3f, 2f, 0f), Color.RED, 0.8));
        //piso
        scene02.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 0), new Vector3D(600, -2, 600)),
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 600), new Vector3D(-600, -2, -600)),},
                Color.WHITE, material2));
        //pared de fondo
        scene02.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, -200, 4), new Vector3D(400, 200, 4)),
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, 200, 4), new Vector3D(-400, 200, 4)),},
                Color.WHITE, material1));
        //OBJECTS
        //scene02.addObject(new Sphere(new Vector3D(0.35f, 1f, 4.5f), 0.3f, Color.BLUE));
        scene02.addObject(new Sphere(new Vector3D(2f, 1f, 1.5f), 0.4f, Color.WHITE, material1));
        scene02.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(-3f, 2f, 2f), Color.WHITE, material1));
        scene02.addObject(OBJReader.GetModel3D("SmallTeapot.obj", new Vector3D(0f, -1.73f, 1.5f), Color.MAGENTA, material1));
        //scene02.addObject(OBJReader.GetModel3D("LotusFlower.obj", new Vector3D(0,0,1.5),Color.MAGENTA));

        //scene02.addObject(OBJReader.GetModel3D("Ring.obj", new Vector3D(2f, -1.0f, 1.5f), Color.BLUE));

        Scene scene01 = new Scene();
        //camara
        scene01.setCamera(new Camera(new Vector3D(0, .5f, -8), 160, 160, 800, 800, 8.2f, 50f));
        //lights
        scene01.addLight(new PointLight(new Vector3D(0f, 2f, 0f), Color.WHITE, 1.2));
        //piso
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 0), new Vector3D(600, -2, 600)),
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 600), new Vector3D(-600, -2, -600)),},
                Color.WHITE, material1));
        //pared de fondo
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, -200, 4), new Vector3D(400, 200, 4)),
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, 200, 4), new Vector3D(-400, 200, 4)),},
                Color.WHITE, material1));
        //pared left
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-3.7,-400,-15), new Vector3D(-2.5f, -400, 10), new Vector3D(-2.5, 400, 15)),
                        new Triangle(new Vector3D(-3.7,-400,-15), new Vector3D(-2.5, 400, 10), new Vector3D(-3.7, 400, -15)),},
                new Color(220,97,143), material1));
        //pared right
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(2.5f, -400, 10), new Vector3D(3.7,-400,-15), new Vector3D(3.7, 400, -15)),
                        new Triangle(new Vector3D(2.5f, -400, 10), new Vector3D(3.7, 400, -15), new Vector3D(3.7, 400, 15)),},
                new Color(156,134,173), material1));
        //objects
        scene01.addObject(new Sphere(new Vector3D(0f, 0f, 1.5f), 0.9f, Color.WHITE, material1));
        //scene01.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(-1.5f, -.5f, 1f), Color.WHITE));

        Scene scene03 = new Scene();
        scene03.setCamera(new Camera(new Vector3D(0, 0.5f, -8), 160, 160, 800, 800, 8.2f, 50f));
        //lights
        scene03.addLight(new PointLight(new Vector3D(0f, 4f, 0f), Color.WHITE, 1.5f));
        //piso
        scene03.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 0), new Vector3D(600, -2, 600)),
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 600), new Vector3D(-600, -2, -600)),},
                Color.WHITE, material2));
        //objects
        scene03.addObject(new Sphere(new Vector3D(0f, -1f, 5f), 0.7f, new Color(193,38,97), material1));


        BufferedImage image = raytrace(scene03);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new Date());
    }

    public static BufferedImage raytrace(Scene scene) {
        Camera mainCamera = scene.getCamera();
        float[] nearFarPlanes = mainCamera.getNearFarPlanes();
        float cameraZ = (float) mainCamera.getPosition().getZ();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();



        Vector3D[][] positionsToRaytrace = mainCamera.calculatePositionsToRay();
        for (int i = 0; i < positionsToRaytrace.length; i++) {
            for (int j = 0; j < positionsToRaytrace[i].length; j++) {
                double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
                double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
                double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();

                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                Intersection closestIntersection = raycast(ray, objects, null, new float[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});

                Color pixelColor = Color.BLACK;
                boolean shadow = false;
                double specular;

                if (closestIntersection != null) {
                    Color objColor = closestIntersection.getObject().getColor();

                    for (Light light : lights) {

                        //nuevo rayo para calcular sombras, checar si hay intersecciones
                        Ray ray2 = new Ray(Vector3D.add(closestIntersection.getPosition(), Vector3D.scalarMultiplication(closestIntersection.getNormal(), 0.28)), light.getPosition());
                        Intersection otherIntersection = raycast(ray2, objects,null, null);

                        //si no hay intersección, calculate Color, lights...
                        if(otherIntersection == null){

                            if(closestIntersection.getObject().getMaterial().isReflection()){
                                //calculate reflection
                                Vector3D interscNormal = closestIntersection.getNormal();
                                Vector3D cameraInters = Vector3D.substract(closestIntersection.getPosition(), scene.getCamera().getPosition());
                                Vector3D reflect= reflect(interscNormal,cameraInters);

                                Intersection reflectedIntersection = raycast(new Ray(closestIntersection.getPosition(), reflect),objects, closestIntersection.getObject(), null);

                                if(reflectedIntersection != null){
                                    closestIntersection = reflectedIntersection;
                                    objColor = closestIntersection.getObject().getColor();
                                }
                            }

                            if(closestIntersection.getObject().getMaterial().isReflactive()){
                                //caculate reflactive
                            }

                            double nDotL = light.getNDotL(closestIntersection);
                            double intensity = light.getIntensity() * nDotL;
                            double li = intensity/ Math.pow(Vector3D.distancePoints(closestIntersection.getPosition(), light.getPosition()), 2);
                            Color lightColor = light.getColor();

                            float[] lightColors = new float[]{lightColor.getRed() / 255.0f, lightColor.getGreen() / 255.0f, lightColor.getBlue() / 255.0f};
                            float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};

                            //caculate specular
                            Vector3D cameraPos = Vector3D.normalize(scene.getCamera().getPosition());
                            Vector3D lightPos = Vector3D.normalize(light.getPosition());
                            Vector3D halfAngle = Vector3D.normalize(Vector3D.scalarMultiplication((Vector3D.add(cameraPos, lightPos)), 1/Vector3D.magnitude(Vector3D.add(cameraPos,lightPos))));
                            double shininess = closestIntersection.getObject().getMaterial().getShinnines();
                            specular = (Math.pow(Vector3D.dotProduct(closestIntersection.getNormal(), halfAngle),shininess));

                            double ambient = 0.05;

                            for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                                objColors[colorIndex] *= (li+specular+ambient) * lightColors[colorIndex];
                            }

                            Color diffuse = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                            pixelColor = addColor(pixelColor, diffuse);
                        }
                        /*else{
                            shadow = true;
                            pixelColor = Color.BLACK;
                        }*/
                    }
                }

                Color ambientColor = new Color(0.05f, 0.05f, 0.05f);
                pixelColor = addColor(pixelColor, ambientColor);

                /*if(shadow){
                    pixelColor = Color.BLUE;
                }*/
                image.setRGB(i, j, pixelColor.getRGB());
            }
        }

        return image;
    }

    public static Vector3D reflect(Vector3D interscNormal, Vector3D interscCamera){
        Vector3D reflect;
        double nDotc= Vector3D.dotProduct(interscNormal,interscCamera);
        Vector3D i2 = Vector3D.scalarMultiplication(interscNormal,-2);
        Vector3D iDotnDotL = Vector3D.scalarMultiplication(i2,nDotc);
        reflect = Vector3D.add(iDotnDotL, interscCamera);
        return reflect;
    }

    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static Color addColor(Color original, Color otherColor) {
        float red = clamp((original.getRed() / 255f) + (otherColor.getRed() / 255f), 0, 1);
        float green = clamp((original.getGreen() / 255f) + (otherColor.getGreen() / 255f), 0, 1);
        float blue = clamp((original.getBlue() / 255f) + (otherColor.getBlue() / 255f), 0, 1);
        return new Color(red, green, blue);
    }

    public static Intersection raycast(Ray ray, List<Object3D> objects, Object3D caster, float[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            if (caster == null || !currentObj.equals(caster)) {
                Intersection intersection = currentObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }

        return closestIntersection;
    }

}
