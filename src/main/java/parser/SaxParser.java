package main.java.parser;

import main.java.entity.Papers;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import main.java.entity.Paper;

import java.util.logging.Logger;

public class SaxParser extends DefaultHandler{

    public static final Logger LOG = Logger.getLogger(SaxParser.class.toString());

    Papers papers = new Papers();
    Paper paper = new Paper();
    String thisElement = "";

    public Papers getResult(){
        return papers;
    }

    @Override
    public void startDocument() throws SAXException {
        LOG.info("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
        if (thisElement == "paper"){
            paper = new Paper();
            papers.add(paper);
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (thisElement == "paper"){
            papers.add(paper);
        }
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        switch (thisElement){
            case "title": paper.setTitle(new String(ch,start,length));
                break;
            case "type": paper.setType(new String(ch, start, length));
                break;
            case "monthly": paper.setMonthly(new Boolean(new String(ch, start, length)));
                break;
            case "color": paper.setColor(new Boolean(new String(ch,start,length)));
                break;
            case "numberOfPage": paper.setNumberOfPages(new Integer(new String(ch, start, length)));
                break;
            case "glossy": paper.setGlossy(new Boolean(new String(ch,start,length)));
                break;
            case "subscriptionIndex": paper.setSubscriptionIndex(new Integer(new String(ch,start,length)));
                break;
        }
    }

    @Override
    public void endDocument() {
        LOG.info("Stop parse XML...");
    }
}