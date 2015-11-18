package main.java.main;

import jdk.nashorn.internal.objects.NativeDebug;
import main.java.entity.Paper;
import main.java.parser.DomParser;
import main.java.parser.SaxParser;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.xml.parsers.*;

import org.w3c.dom.Document;

/**
 * Created by DiZi on 12.11.2015.
 */
public class Main {

    public static final Logger LOG = Logger.getLogger(Main.class.toString());

    public static void main (String args[]) throws NoSuchFieldException {
        File file = new File("Paper.xml");
        //SAX
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        SaxParser saxp = new SaxParser();

        try {
            parser.parse(file, saxp);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        LOG.info("Print result : " + saxp.getResult());
        //DOM
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            DomParser domParser = new DomParser();
            LOG.info("DOM parser result:\n" + domParser.parse(doc));

        } catch (Exception e) {
            e.printStackTrace();
        }
        // TO DO
        Paper p = new Paper();
        Class c = p.getClass();
        Field[] publicFields = c.getDeclaredFields();
        for (Field field : publicFields) {
            Class fieldType = field.getType();
            LOG.info("Name: " + field.getName());
            LOG.info("Type: " + fieldType.getName());
        }

        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.println("Name: " + method.getName());
            System.out.println("Return type: " + method.getReturnType().getName());

            Class[] paramTypes = method.getParameterTypes();
            System.out.print("Типы параметров: ");
            for (Class paramType : paramTypes) {
                System.out.print(" " + paramType.getName());
            }
            System.out.println();
        }

        Class[] paramTypes = new Class[] {String.class};
        Method method = null;
        try {
            method = c.getMethod("setTitle", paramTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object[] args1 = new Object[] {"Do any"};
        try {
            String d = (String) method.invoke(p, args1);
            System.out.println(d);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}