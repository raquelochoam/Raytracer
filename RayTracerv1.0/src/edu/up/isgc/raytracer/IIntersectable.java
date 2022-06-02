/**
 * [1968] - [2022] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.raytracer;

import edu.up.isgc.raytracer.Intersection;
import edu.up.isgc.raytracer.Ray;
/**
 * @author Jafet Rodriguez
 *
 */
public interface IIntersectable {
    Intersection getIntersection(Ray ray);
}
