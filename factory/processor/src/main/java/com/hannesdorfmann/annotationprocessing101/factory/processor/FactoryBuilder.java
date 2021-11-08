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

package com.hannesdorfmann.annotationprocessing101.factory.processor;

import com.squareup.javapoet.*;
import javafx.util.Pair;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.io.IOException;
import java.util.*;

/**
 * 工厂类文件及构建
 *
 * @author February
 */
public class FactoryBuilder {

    /**
     * 自动工厂类后缀
     */
    private static final String SUFFIX = "Factory";
    /**
     * 父类信息
     */
    private TypeElement fruitElement;
    /**
     * 需要处理的类元素
     */
    private List<Pair<String, TypeElement>> elements;

    public FactoryBuilder(TypeElement fruitElement, List<Pair<String, TypeElement>> elements) {
        this.fruitElement = fruitElement;
        this.elements = elements;
    }

    public FactoryBuilder() {
    }

    public synchronized void addElement(String fruitName, TypeElement fruitType) {
        if (null == elements || elements.size() < 1) {
            elements = new ArrayList<>();
        }
        elements.add(new Pair<>(fruitName, fruitType));
    }

    public void generateCode(Elements elementUtils, Filer filer) throws IOException {
        // 构建工厂类类名
        String factoryClassName = fruitElement.getSimpleName() + SUFFIX;
        // 构建工厂类包名
        PackageElement pkg = elementUtils.getPackageOf(fruitElement);
        String packageName = pkg.isUnnamed() ? null : pkg.getQualifiedName().toString();

        // 构建工厂类方法
        MethodSpec.Builder method = MethodSpec.methodBuilder("create")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "id")
                .returns(TypeName.get(fruitElement.asType()));

        // 校验空入参
        method.beginControlFlow("if (id == null)")
                .addStatement("throw new IllegalArgumentException($S)", "id is null!")
                .endControlFlow();

        // 构建工厂方法
        elements.forEach(e ->
                method.beginControlFlow("if ($S.equals(id))", e.getKey())
                        .addStatement("return new $L()", e.getValue().getQualifiedName().toString())
                        .endControlFlow());

        method.addStatement("throw new IllegalArgumentException($S + id)", "Unknown id = ");

        TypeSpec typeSpec = TypeSpec.classBuilder(factoryClassName)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(method.build())
                .build();

        // Write file
        assert packageName != null;
        JavaFile.builder(packageName, typeSpec).build().writeTo(filer);
    }
}
