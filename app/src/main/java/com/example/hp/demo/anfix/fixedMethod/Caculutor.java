package com.example.hp.demo.anfix.fixedMethod;

import com.example.hp.demo.anfix.Replace;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class Caculutor {
    @Replace(clazz = "com.example.hp.demo.anfix.AnFixActivity", Method = "caculutor")
    public int caculutor() {
        int a = 66, b = 1;
        return a / b;
    }
}
