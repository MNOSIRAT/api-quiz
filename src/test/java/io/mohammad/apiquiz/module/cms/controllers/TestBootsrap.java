package io.mohammad.apiquiz.module.cms.controllers;

import io.mohammad.apiquiz.ApiQuizApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AutoConfigureMockMvc
@SpringBootTest(classes = ApiQuizApplication.class)
@Transactional
public @interface TestBootsrap {
}