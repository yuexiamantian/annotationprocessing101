//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.hannesdorfmann.annotationprocessing101.factory.staticFactory;

import com.hannesdorfmann.annotationprocessing101.factory.Apple;
import com.hannesdorfmann.annotationprocessing101.factory.Banana;
import com.hannesdorfmann.annotationprocessing101.factory.Fruit;
import com.hannesdorfmann.annotationprocessing101.factory.Orange;

/**
 * @author February
 */
public class StaticMealFactory {
    StaticMealFactory() {
    }

    public Fruit create(String fruitName) {
        if (fruitName == null) {
            throw new IllegalArgumentException("Name of the fruit is null!");
        }

        if ("orange".equals(fruitName)) {
            return new Orange();
        }

        if ("apple".equals(fruitName)) {
            return new Apple();
        }

        if ("banana".equals(fruitName)) {
            return new Banana();
        }
        throw new IllegalArgumentException("Name of the fruit is null!");

    }
}