package com.javalopment.java10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LocalVariableTypeInference {

    public void withoutJep286() {
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
    }

    //public var nonLocalVarNotAllowed = "";

    public void localVariableTypeInference() {
        var name = "John";  // Local variable name is inferred to be of type String
        var age = 30;      // Local variable age is inferred to be of type int
        var list = new ArrayList<String>(); // Local variable list is inferred to be of type ArrayList<String>
        var stream = list.stream(); //Stream<String> is inferred.

        //var notAllowed;
        //var notAllowed = null;
        //var arrayVarNotAllowed = { 1, 2, 3};
        //var lambdaVarNotAllowed = () -> {};
    }
}
