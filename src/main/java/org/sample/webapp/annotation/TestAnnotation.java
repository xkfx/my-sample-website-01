package org.sample.webapp.annotation;

@ThreadSafe
public class TestAnnotation {

    @GuardBy("this")
    private int aMutableVariable;
}
