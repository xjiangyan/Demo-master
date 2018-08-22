package com.example.hp.demo.anfix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class DxManager {
    private Context context;

    public DxManager(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFilePath) {
        File optFile = new File(context.getCacheDir(), dexFilePath.getName());
        if (optFile.exists()) {
            optFile.delete();
        }
        try {
            //加载dex
            DexFile dexFile = DexFile.loadDex(dexFilePath.getAbsolutePath(), optFile.getAbsolutePath(), Context.MODE_PRIVATE);
            //遍历dex里面的class
            Enumeration<String> entries = dexFile.entries();
            while (entries.hasMoreElements()) {
                String className = entries.nextElement();
                //修复好的realClazz  怎样找到出bug 的class
                Class realClazz = dexFile.loadClass(className, context.getClassLoader());
                Log.i("DxManager", "找到类     " + className);
                fix(realClazz);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fix(Class realClazz) {
        Method[] declaredMethods = realClazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            //拿到注解
            Replace replace = method.getAnnotation(Replace.class);
            if (replace == null) {
                continue;
            }
            String wrongClassName = replace.clazz();
            String wrongMethodName = replace.Method();
            try {
                Class wrongClass = Class.forName(wrongClassName);
                //最终拿到错误的Method对象
                Method wrongMethod = wrongClass.getMethod(wrongMethodName, method.getParameterTypes());
                //修复
//                replace(wrongMethod, method);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

//    private native void replace(Method wrongMethod, Method method);

}
