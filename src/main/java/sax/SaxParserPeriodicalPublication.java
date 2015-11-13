package main.java.sax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import main.java.entity.Paper;

public class SaxParserPeriodicalPublication extends DefaultHandler{

    Paper paper = new Paper();
    String thisElement = "";

    public Paper getResult(){
        return paper;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
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
            case "numberOfPages": paper.setNumberOfPages(new Integer(new String(ch, start, length)));
                break;
            case "glossy": paper.setGlossy(new Boolean(new String(ch,start,length)));
                break;
            case "subscriptionIndex": paper.setSubscriptionIndex(new Integer(new String(ch,start,length)));
                break;
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
    }
}