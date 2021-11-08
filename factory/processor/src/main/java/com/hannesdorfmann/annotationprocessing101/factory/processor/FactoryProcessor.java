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

import com.google.auto.service.AutoService;
import com.hannesdorfmann.annotationprocessing101.factory.annotation.Factory;
import javafx.util.Pair;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.*;

/**
 * Annotation Processor for @Factory annotation
 *
 * @author February
 */
@AutoService(Processor.class)
public class FactoryProcessor extends AbstractProcessor {

    private Elements elementUtils;
    private Filer filer;
    private Types typeUtils;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        typeUtils = processingEnv.getTypeUtils();
        elementUtils = processingEnv.getElementUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();
        annotations.add(Factory.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 准备收集注解类容器
        List<Pair<String, TypeElement>> factoryClasses = new ArrayList<>();
        FactoryBuilder factoryBuilder = null;

        try {
            // 扫描被注解（@Factory）类信息
            for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Factory.class)) {

                // 非注解在类不需要处理
                if (annotatedElement.getKind() != ElementKind.CLASS) {
                    continue;
                }

                // 注解在类的可以直接强转
                TypeElement typeElement = (TypeElement) annotatedElement;

                if (null == factoryBuilder) {
                    factoryBuilder = new FactoryBuilder(
                            ((TypeElement) typeUtils.asElement(typeElement.getInterfaces().get(0))), factoryClasses);
                }

                factoryClasses.add(new Pair<>(typeElement.getAnnotation(Factory.class).id(), typeElement));
            }

            if (null == factoryBuilder) {
                return true;
            }
            factoryBuilder.generateCode(elementUtils, filer);
            // 用完及时释放，防止多次调用数据异常
            factoryBuilder = null;
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage(), null);
        }
        return true;
    }
}
