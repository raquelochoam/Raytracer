/**
 * [1968] - [2022] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 * @autors Jafet Rodriguez and Raquel Ochoa
 */
package edu.up.isgc.raytracer;


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

/**
 * Main class
 */
public class Raytracer {

    public static void main(String[] args) {
        System.out.println(new Date());

        //Materials
        Material material1 = new Material(45, false, false);
        Material material2 = new Material(45,true, false);
        Material material3 = new Material(45,false,true);
        Material material4 = new Material(30,false, false);
        Material material5 = new Material(15,false, false);
        Material material6 = new Material(40,false, false);

        //Scenes
        Scene scene01 = new Scene();
        //camara
        scene01.setCamera(new Camera(new Vector3D(0, .5f, -7), 144f, 160f, 1920, 1080, 0.5f, 50f));
        //lights
        scene01.addLight(new PointLight(new Vector3D(0f, 3f, -4f), Color.WHITE, 1.2));
        scene01.addLight(new PointLight(new Vector3D(3f, 2f, -5f), Color.BLUE, 0.8));
        //floor
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,0,-600), new Vector3D(600, 0, -600), new Vector3D(600, 0, 600)),
                        new Triangle(new Vector3D(-600,0,-600), new Vector3D(600, 0, 600), new Vector3D(-600, 0, 600)),},
                Color.WHITE, material2));
        //back wall
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, -200, 4), new Vector3D(400, 200, 4)),
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, 200, 4), new Vector3D(-400, 200, 4)),},
                Color.WHITE, material1));
        //objects
        scene01.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(3f, 2f, .5f), new Color(230,230,120), material1));
        scene01.addObject(OBJReader.GetModel3D("Ring.obj",new Vector3D(-1.5f, .1f,1f), Color.WHITE, material2));
        scene01.addObject(OBJReader.GetModel3D("lotusx90.obj",new Vector3D(1.5f, 0,1.5f), Color.MAGENTA, material1));
        scene01.addObject(OBJReader.GetModel3D("lotusx90.obj",new Vector3D(-2.5f, 0,-1.7f), Color.MAGENTA, material1));
        scene01.addObject(OBJReader.GetModel3D("lotusx90.obj",new Vector3D(2.3f, 0,-1.25f), Color.MAGENTA, material1));

        Scene scene02 = new Scene();
        scene02.setCamera(new Camera(new Vector3D(0, 1f, -7), 144f, 160f, 1920, 1080, 0.5f, 50f));
        //lights
        scene02.addLight(new PointLight(new Vector3D(3f, 4f, -0.1f), Color.WHITE, 1.2));
        scene02.addLight(new PointLight(new Vector3D(0f, 4f, -1f), new Color(255,255,245), 0.8));
        //floor
        scene02.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,0,-600), new Vector3D(600, 0, -600), new Vector3D(600, 0, 600)),
                        new Triangle(new Vector3D(-600,0,-600), new Vector3D(600, 0, 600), new Vector3D(-600, 0, 600)),},
                Color.WHITE, material1));
        //back wall
        scene02.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,5), new Vector3D(400, -200, 5), new Vector3D(400, 200, 5)),
                        new Triangle(new Vector3D(-400,-200,5), new Vector3D(400, 200, 5), new Vector3D(-400, 200, 5)),},
                new Color(222,240,255), material1));
        //OBJECTS
        scene02.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(2f, .7f, 2.5f), Color.WHITE, material2));
        scene02.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(2f, 2f, 2.5f), Color.WHITE, material2));
        scene02.addObject(OBJReader.GetModel3D("Ring.obj", new Vector3D(2f, .3f, 2.5f), Color.WHITE, material2));
        scene02.addObject(OBJReader.GetModel3D("LotusFlowerfrente.obj", new Vector3D(3,3,-0.3), new Color(255,216,72), material1));
        scene02.addObject(OBJReader.GetModel3D("LotusFlowerBack.obj", new Vector3D(3,3,1), new Color(255,216,72), material1));

        scene02.addObject(OBJReader.GetModel3D("LotusFlowerfrente.obj", new Vector3D(-5,2.5,4), new Color(149,177,144), material5));
        scene02.addObject(OBJReader.GetModel3D("LotusFlowerfrente.obj", new Vector3D(-3,4,4), new Color(255,189,209), material5));
        scene02.addObject(OBJReader.GetModel3D("LotusFlowerfrente.obj", new Vector3D(-1,2.5,4), new Color(255,160,64), material5));
        scene02.addObject(OBJReader.GetModel3D("LotusFlowerfrente.obj", new Vector3D(-4,.8,4), new Color(255,64,64), material5));
        scene02.addObject(OBJReader.GetModel3D("LotusFlowerfrente.obj", new Vector3D(-2,.8,4), new Color(30,144,255), material5));

        scene02.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(-2f, .8f, 2.5f), Color.WHITE, material3));
        scene02.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(-3f, 4.1f, 2.5f), Color.WHITE, material3));


        Scene scene03 = new Scene();
        scene03.setCamera(new Camera(new Vector3D(0, .8, -9), 144f, 160f, 400,225 , .5f, 50f));
        //scene03.addLight(new PointLight(new Vector3D(0f, -0.3f, 600f), Color.ORANGE, 1.5));
        //lights
        scene03.addLight(new PointLight(new Vector3D(-2f, 3f, 0f), Color.ORANGE, 1.1));
        scene03.addLight(new PointLight(new Vector3D(2f, 3f, 0f), Color.RED, 1.1));
        scene03.addLight(new PointLight(new Vector3D(0f, 4f, 0f), Color.RED, 0.4));
        //floor
        scene03.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,0,-600), new Vector3D(600, 0, -600), new Vector3D(600, 0, 600)),
                        new Triangle(new Vector3D(-600,0,-600), new Vector3D(600, 0, 600), new Vector3D(-600, 0, 600)),},
                Color.WHITE, material2));
        //back wall
        scene03.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,5), new Vector3D(400, -200, 5), new Vector3D(400, 200, 5)),
                        new Triangle(new Vector3D(-400,-200,5), new Vector3D(400, 200, 5), new Vector3D(-400, 200, 5)),},
                Color.WHITE, material1));
        //objects
        scene03.addObject(OBJReader.GetModel3D("LotusFlowerFront.obj",new Vector3D(-1.5f, .6,4f), Color.YELLOW, material1));
        scene03.addObject(OBJReader.GetModel3D("LotusFlowerFront.obj",new Vector3D(1.5f, .6,4f), Color.YELLOW, material1));
        scene03.addObject(OBJReader.GetModel3D("LotusFlowerFront.obj",new Vector3D(0f, 1.8,4f), Color.YELLOW, material1));

        scene03.addObject(OBJReader.GetModel3D("lotusx90.obj",new Vector3D(-3f, 0,-3.5f), Color.MAGENTA, material1));
        scene03.addObject(OBJReader.GetModel3D("lotusx90.obj",new Vector3D(4f, 0,2.5f), Color.MAGENTA, material1));
        scene03.addObject(OBJReader.GetModel3D("lotusx90.obj",new Vector3D(4f, 0,-2.5f), Color.MAGENTA, material1));

        scene03.addObject(OBJReader.GetModel3D("Cube.obj",new Vector3D(-2f, 0,-3.9f), Color.WHITE, material3));

        //scene showing picture
        BufferedImage image = raytrace(scene03);
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }

    /**
     *
     * @param scene
     * @return a BufferedImage you have rendered the parameter scene
     */
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
                double specular;

                if (closestIntersection != null) {
                    Color objColor;

                    for (Light light : lights) {
                        //new ray to calculate shadows and check intersections
                        Ray ray2 = new Ray(Vector3D.add(closestIntersection.getPosition(), Vector3D.scalarMultiplication(closestIntersection.getNormal(), 0.28)), light.getPosition());
                        Intersection otherIntersection = raycast(ray2, objects,null, null);

                        if(otherIntersection == null){
                            if(closestIntersection.getObject().getMaterial().isReflection()){
                                //calculate reflection
                                closestIntersection = reflection(closestIntersection, scene,0);
                            }

                            if(closestIntersection.getObject().getMaterial().isRefractive()){
                                //caculate reflactive
                                closestIntersection = refraction(closestIntersection,scene,ray);
                            }

                            double nDotL = light.getNDotL(closestIntersection);
                            double intensity = light.getIntensity() * nDotL;
                            double li = intensity/ Math.pow(Vector3D.distancePoints(closestIntersection.getPosition(), light.getPosition()), 2);
                            Color lightColor = light.getColor();
                            objColor = closestIntersection.getObject().getColor();


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
                    }
                }

                Color ambientColor = new Color(0.05f, 0.05f, 0.05f);
                pixelColor = addColor(pixelColor, ambientColor);

                image.setRGB(i, j, pixelColor.getRGB());
            }
        }

        return image;
    }

    /**
     *
     * @param interscNormal
     * @param interscCamera
     * @return the Vector3D to cast reflection.
     */
    public static Vector3D reflectV3D(Vector3D interscNormal, Vector3D interscCamera){
        Vector3D reflect;
        double nDotc= Vector3D.dotProduct(interscNormal,interscCamera);
        Vector3D i2 = Vector3D.scalarMultiplication(interscNormal,-2);
        Vector3D iDotnDotL = Vector3D.scalarMultiplication(i2,nDotc);
        reflect = Vector3D.add(iDotnDotL, interscCamera);
        return reflect;
    }

    /**
     *
     * @param closestIntersection
     * @param scene
     * @param repeats-recursion limit
     * @return the intersection with reflection values.
     */
    public static Intersection reflection(Intersection closestIntersection, Scene scene, int repeats){
        if(repeats <= 1){
            Vector3D interscNormal = closestIntersection.getNormal();
            Vector3D cameraInters = Vector3D.substract(closestIntersection.getPosition(), scene.getCamera().getPosition());
            Vector3D reflect= reflectV3D(interscNormal,cameraInters);

            for (Object3D object:
                    scene.getObjects()) {
                if(object != closestIntersection.getObject()){
                    Vector3D positionRay = Vector3D.add(closestIntersection.getPosition(), Vector3D.scalarMultiplication(closestIntersection.getNormal(),0.01));
                    Intersection reflectedIntersc = raycast(new Ray(positionRay, reflect), scene.getObjects(), closestIntersection.getObject(), null);
                    if(reflectedIntersc == null){
                        return closestIntersection;
                    }
                    else{
                        if(reflectedIntersc.getObject() == object){
                            return closestIntersection;
                        }
                        else{
                            //return reflection(reflectedIntersc, scene,repeats+1);
                            return reflectedIntersc;
                        }
                    }
                }
            }
        }
        return closestIntersection;
    }

    /**
     *
     * @param closestIntersection
     * @param scene
     * @param ray
     * @return the intersection with refraction values.
     */
    public static Intersection refraction(Intersection closestIntersection, Scene scene, Ray ray){
        Vector3D nRefr = Vector3D.normalize(ray.getDirection());
        double nDotR = Vector3D.dotProduct(closestIntersection.getNormal(),nRefr);
        double indexRefr = 1.0;

        double c = Math.sqrt(1-Math.pow(indexRefr,2)*(1-Math.pow(nDotR,2)));

        Vector3D refractionV = Vector3D.add(Vector3D.scalarMultiplication(nRefr,indexRefr), Vector3D.scalarMultiplication(closestIntersection.getNormal(), (indexRefr*nDotR)-c));

        for (Object3D object:
                scene.getObjects()) {
            if(object != closestIntersection.getObject()){
                Intersection refracIntersc = raycast(new Ray(closestIntersection.getPosition(), refractionV), scene.getObjects(), closestIntersection.getObject(), null);
                if(refracIntersc == null){
                    return closestIntersection;
                }
                else{
                    if(refracIntersc == null){
                        return closestIntersection;
                    }
                    else{
                        return refraction(refracIntersc,scene,ray);
                    }
                }
            }
        }

        return  closestIntersection;
    }

    /**
     *
     * @param value
     * @param min
     * @param max
     * @return the maximized or minimized value within the parameters.
     */
    public static float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    /**
     *
     * @param original
     * @param otherColor
     * @return color combined with another
     */
    public static Color addColor(Color original, Color otherColor) {
        float red = clamp((original.getRed() / 255f) + (otherColor.getRed() / 255f), 0, 1);
        float green = clamp((original.getGreen() / 255f) + (otherColor.getGreen() / 255f), 0, 1);
        float blue = clamp((original.getBlue() / 255f) + (otherColor.getBlue() / 255f), 0, 1);
        return new Color(red, green, blue);
    }

    /**
     *
     * @param ray
     * @param objects
     * @param caster
     * @param clippingPlanes
     * @return the intersection found in the direction of the ray, in case there is no returns null.
     */
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