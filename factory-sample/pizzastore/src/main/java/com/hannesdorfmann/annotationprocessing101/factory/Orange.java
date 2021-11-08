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

package com.hannesdorfmann.annotationprocessing101.factory;

import com.hannesdorfmann.annotationprocessing101.factory.annotation.Factory;

/**
 * @author February
 */
@Factory(
        id = "orange",
        type = Fruit.class
)
public class Orange implements Fruit {

    @Override
    public float getPrice(String name) {
        return 8.5f;
    }
}
