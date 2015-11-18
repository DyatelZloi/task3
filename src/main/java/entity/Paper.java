package main.java.entity;


import java.io.File;
import java.util.List;

/**
 * Created by DiZi on 12.11.2015.
 */
public class Paper {

    private static final int SIZE = 4;

    private String title;
    private String type;
    private boolean monthly;
    private boolean color;
    private int numberOfPages;
    private boolean glossy;
    private int subscriptionIndex;

    public Paper() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public boolean isGlossy() {
        return glossy;
    }

    public void setGlossy(boolean glossy) {
        this.glossy = glossy;
    }

    public int getSubscriptionIndex() {
        return subscriptionIndex;
    }

    public void setSubscriptionIndex(int subscriptionIndex) {
        this.subscriptionIndex = subscriptionIndex;
    }


    public static byte[] parse(String ip)
    {
        final String[] strings = ip.split("\\.");
        if(strings.length != SIZE)
            throw new IllegalArgumentException("Invalid input string - '" + ip + "'");

        byte[] bytes = new byte[SIZE];
        for(int i = 0; i < strings.length; i++)
        {
            String s = strings[i];
            int b = Integer.parseInt(s);
            if(b < 0 || b > 255)
                throw new IllegalArgumentException("Invalid input string - '" + ip + "'");

            bytes[i] = (byte) b;
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", monthly=" + monthly +
                ", color=" + color +
                ", numberOfPages=" + numberOfPages +
                ", glossy=" + glossy +
                ", subscriptionIndex=" + subscriptionIndex +
                '}';
    }

    private static byte[] toByteArray(final int i)
    {
        byte[] bytes = new byte[SIZE];
        bytes[0] = (byte) ((i >>> 24) & 0xFF);
        bytes[1] = (byte) ((i >>> 16) & 0xFF);
        bytes[2] = (byte) ((i >>> 8) & 0xFF);
        bytes[3] = (byte) (i & 0xFF);
        return bytes;
    }

    private static int toInt(final byte[] bytes)
    {
        if(bytes.length != SIZE)
            throw new IllegalArgumentException("Invalid array length, expected - 4, found - " + bytes.length);

        return ((bytes[0] & 0xFF) << 24) +
                ((bytes[1] & 0xFF) << 16) +
                ((bytes[2] & 0xFF) << 8) +
                (bytes[3] & 0xFF);
    }


}
