package main.java.main;


import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import main.java.sax.SaxParserPeriodicalPublication;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


/**
 * Created by DiZi on 12.11.2015.
 */
public class Main {

    public static void main (String args[]) {
        File file = new File("Paper.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        SaxParserPeriodicalPublication saxp = new SaxParserPeriodicalPublication();

        try {
            parser.parse(file, saxp);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        System.out.println(saxp.getResult());
    }
}