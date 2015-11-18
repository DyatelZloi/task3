package main.java.parser;

import main.java.entity.Paper;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by DiZi on 15.11.2015.
 */
public class DomParser {

    Paper paper = new Paper();

    public Paper parse(Document input) {
        return parseNode(input.getDocumentElement());
    }

    private Paper parseNode(Node node) {

//если текущий элемент - текстовое содержимое тега
        if (node.getNodeName().equals("#text"))
            return null;

        StringBuffer result = new StringBuffer();

//имя тега
        result.append("Element name = '" + node.getNodeName() + "'\n");

//атрибуты тега
        NamedNodeMap nodeMap = node.getAttributes();

        if (nodeMap != null) {
            for (int i = 0; i < nodeMap.getLength(); i ++){
                result.append("Attribute name = '" + nodeMap.item(i).getNodeName()
                        + "'; Attribute value = '" + nodeMap.item(i).getNodeValue() + "'\n");
            }
        }

//содержимое элемента
        if (getElementContent(node) != null && !(getElementContent(node).equals("")))
            switch (node.getNodeName()){
                case "title": paper.setTitle(getElementContent(node));
            }
            switch (node.getNodeName()){
            case "title": paper.setTitle(getElementContent(node));
                break;
            case "type": paper.setType(getElementContent(node));
                break;
            case "monthly": paper.setMonthly(new Boolean(getElementContent(node)));
                break;
            case "color": paper.setColor(new Boolean(getElementContent(node)));
                break;
            case "numberOfPages": paper.setNumberOfPages(new Integer(getElementContent(node)));
                break;
            case "glossy": paper.setGlossy(new Boolean(getElementContent(node)));
                break;
            case "subscriptionIndex": paper.setSubscriptionIndex(new Integer(getElementContent(node)));
                break;
        }

            result.append("Element content = '" + getElementContent(node)+"'\n");

        NodeList nodeList = node.getChildNodes();

//рекурсивно вызываем метод для каждого из подэлементов в
//переданном элементе
        for (int i = 0; i < nodeList.getLength(); i++){
            result.append(parseNode(nodeList.item(i)));
        }

//закрытие тега
        result.append("Element closed, name = '" + node.getNodeName() + "'\n");

        return paper;
    }

    private String getElementContent(Node node) {

        Node contentNode = node.getFirstChild();
        if (contentNode != null)
            if (contentNode.getNodeName().equals("#text")) {
                String value = contentNode.getNodeValue();
                if (value != null)
                    return value.trim();
            }
        return null;
    }
}