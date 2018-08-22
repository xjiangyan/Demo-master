package com.example.hp.demo.utils;

import org.xml.sax.helpers.DefaultHandler;

public abstract class BaseHttpParseHandler extends DefaultHandler {
    public abstract Object getResult();
}
