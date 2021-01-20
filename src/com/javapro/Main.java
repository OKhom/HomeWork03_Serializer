package com.javapro;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        MyVariable myVariable = new MyVariable("How are you?", 173, true);
        System.out.println("New Object: " + myVariable.toString());

        String mySerialize = Serializer.serialize(myVariable);
        System.out.println("Serialized: " + mySerialize);

        MyVariable myDeserialize = Serializer.deserialize(mySerialize, myVariable);
        System.out.println("Deserialized: " + myDeserialize.toString());
    }
}
