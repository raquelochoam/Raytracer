/**
 * [1968] - [2022] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;


import edu.up.isgc.raytracer.lights.DirectionalLight;
import edu.up.isgc.raytracer.lights.Light;
import edu.up.isgc.raytracer.lights.PointLight;
import edu.up.isgc.raytracer.objects.*;
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

        Scene scene02 = new Scene();
        scene02.setCamera(new Camera(new Vector3D(0, .5, -8), 160, 160, 800, 800, 8.2f, 50f));
        scene02.addLight(new PointLight(new Vector3D(0f, 2f, 0f), Color.WHITE, 1.1));
        scene02.addLight(new PointLight(new Vector3D(3f, 2f, 0f), Color.RED, 0.8));
        //piso
        scene02.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 0), new Vector3D(600, -2, 600)),
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 600), new Vector3D(-600, -2, -600)),},
                Color.WHITE));
        //pared de fondo
        scene02.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, -200, 4), new Vector3D(400, 200, 4)),
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, 200, 4), new Vector3D(-400, 200, 4)),},
                Color.WHITE));
        //OBJECTS
        //scene02.addObject(new Sphere(new Vector3D(0.35f, 1f, 4.5f), 0.3f, Color.BLUE));
        scene02.addObject(new Sphere(new Vector3D(2f, 1f, 1.5f), 0.4f, Color.WHITE));
        scene02.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(-3f, 2f, 2f), Color.WHITE));
        scene02.addObject(OBJReader.GetModel3D("SmallTeapot.obj", new Vector3D(0f, -1.8f, 1.5f), Color.MAGENTA));

        //scene02.addObject(OBJReader.GetModel3D("SmallTeapot.obj", new Vector3D(-1.5f, 1.5f, 1.5f), Color.GREEN));
        //scene02.addObject(OBJReader.GetModel3D("SmallTeapot.obj", new Vector3D(1.5f, 1.5f, 1.5f), Color.BLUE));
        //scene02.addObject(OBJReader.GetModel3D("SmallTeapot.obj", new Vector3D(1.5f, -2.5f, 1.5f), Color.YELLOW));

        //scene02.addObject(OBJReader.GetModel3D("Ring.obj", new Vector3D(2f, -1.0f, 1.5f), Color.BLUE));

        /*Scene scene01 = new Scene();
        //camara
        scene01.setCamera(new Camera(new Vector3D(0, .5f, -8), 160, 160, 800, 800, 8.2f, 50f));
        //lights
        scene01.addLight(new PointLight(new Vector3D(0f, 2f, 0f), Color.WHITE, 1.1));
        scene01.addLight(new PointLight(new Vector3D(3f, 2f, 0f), Color.RED, 0.8));
        //piso
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 0), new Vector3D(600, -2, 600)),
                        new Triangle(new Vector3D(-600,-2,0), new Vector3D(600, -2, 600), new Vector3D(-600, -2, -600)),},
                Color.WHITE));
        //pared de enfrente
        scene01.addObject(new Model3D(new Vector3D(0, 0, 0),
                new Triangle[]{
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, -200, 4), new Vector3D(400, 200, 4)),
                        new Triangle(new Vector3D(-400,-200,4), new Vector3D(400, 200, 4), new Vector3D(-400, 200, 4)),},
                Color.WHITE));
        //objects
        //scene01.addObject(new Sphere(new Vector3D(0f, 0f, 1.5f), 0.9f, Color.WHITE));
        scene01.addObject(OBJReader.GetModel3D("Cube.obj", new Vector3D(-1.5f, -.5f, 1f), Color.WHITE));*/

        BufferedImage image = raytrace(scene02);
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

                if (closestIntersection != null) {
                    Color objColor = closestIntersection.getObject().getColor();

                    for (Light light : lights) {

                        //nuevo rayo para calcular sombras, checar si hay intersecciones
                        Ray ray2 = new Ray(Vector3D.add(closestIntersection.getPosition(), Vector3D.scalarMultiplication(closestIntersection.getNormal(), 0.28)), light.getPosition());
                        Intersection otherIntersection = raycast(ray2, objects,null, null);

                        //si no hay intersección, calculate Color, lights...
                        if(otherIntersection == null){
                            double nDotL = light.getNDotL(closestIntersection);
                            double intensity = light.getIntensity() * nDotL;
                            double li = intensity/ Math.pow(Vector3D.distancePoints(closestIntersection.getPosition(), light.getPosition()), 2);
                            Color lightColor = light.getColor();

                            float[] lightColors = new float[]{lightColor.getRed() / 255.0f, lightColor.getGreen() / 255.0f, lightColor.getBlue() / 255.0f};
                            float[] objColors = new float[]{objColor.getRed() / 255.0f, objColor.getGreen() / 255.0f, objColor.getBlue() / 255.0f};
                            for (int colorIndex = 0; colorIndex < objColors.length; colorIndex++) {
                                objColors[colorIndex] *= li * lightColors[colorIndex];
                            }

                            Color diffuse = new Color(clamp(objColors[0], 0, 1), clamp(objColors[1], 0, 1), clamp(objColors[2], 0, 1));
                            pixelColor = addColor(pixelColor, diffuse);
                        }
                        else{
                            shadow = true;
                        }

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
