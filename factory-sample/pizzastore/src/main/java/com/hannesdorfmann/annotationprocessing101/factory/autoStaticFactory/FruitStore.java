/*
 * Copyright (C) 2015 Hannes Dorfmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hannesdorfmann.annotationprocessing101.factory.autoStaticFactory;

import com.hannesdorfmann.annotationprocessing101.factory.Fruit;
import com.hannesdorfmann.annotationprocessing101.factory.FruitFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author February
 */
public class FruitStore {

    private final FruitFactory factory = new FruitFactory();

    public Fruit order(String mealName) {
        return factory.create(mealName);
    }

    private static String readConsole() throws IOException {
        System.out.println("What do you like?");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferRead.readLine();
        return input;
    }

    public static void main(String[] args) throws IOException {
        FruitStore pizzaStore = new FruitStore();
        Fruit fruit = pizzaStore.order(readConsole());
        System.out.println("Bill: $" + fruit.getPrice(""));
    }
}
