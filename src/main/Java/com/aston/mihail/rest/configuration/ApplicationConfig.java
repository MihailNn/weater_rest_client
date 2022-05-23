package com.aston.mihail.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

        @Override
        protected Class<?>[] getRootConfigClasses() {
            return new Class[] {};
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[] {MyConfig.class};
        }

        @Override
        protected String[] getServletMappings() {
            return new String[] { "/" };
        }
}
