package com.javapro;

class MyVariable {
    @Save public String var1;
    @Save private int var2;
    public boolean var3;

    MyVariable() {
        this.var1 = "Default constructor";
        this.var2 = 0;
        this.var3 = false;
    }

    MyVariable (String var1, int var2, boolean var3) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

    public String getVar1() {
        return var1;
    }

    public int getVar2() {
        return var2;
    }

    public boolean getVar3() {
        return var3;
    }

    @Override
    public String toString() {
        return "MyVariable{" +
                "var1='" + var1 + '\'' +
                ", var2=" + var2 +
                ", var3=" + var3 +
                '}';
    }
}
