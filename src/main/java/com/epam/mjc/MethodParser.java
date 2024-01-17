package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;


public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */


    public MethodSignature parseFunction(String signatureString) {
        int startArgumentsIndex = signatureString.indexOf('(');
        int endArgumentsIndex = signatureString.indexOf(')');

        String arguments = signatureString.substring(startArgumentsIndex + 1, endArgumentsIndex);

        List<MethodSignature.Argument> argumentsList = new ArrayList<>();

        if (!arguments.isEmpty()) {
            String[] argsArray = arguments.split(", ");
            for (String argument : argsArray) {
                String[] argumentParse = argument.split(" ");
                argumentsList.add(new MethodSignature.Argument(argumentParse[0], argumentParse[1]));
            }
        }


        String methodDeclaration = signatureString.substring(0, startArgumentsIndex);
        String[] signature = methodDeclaration.split(" ");

        MethodSignature methodSignature = new MethodSignature(signature[signature.length - 1], argumentsList);
        methodSignature.setReturnType(signature[signature.length - 2]);

        methodSignature.setAccessModifier(signature.length == 3 ? signature[0] : null);

        return methodSignature;
    }

    public static void main(String[] args) {
        MethodParser s = new MethodParser();
        MethodSignature m = s.parseFunction("private void log()");

        System.out.println(m.getArguments().size());


    }

}
