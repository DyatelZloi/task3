package main.java.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DiZi on 16.11.2015.
 */
public class Papers {

    List<Paper> paperList = new ArrayList<>();

    public  void add(Paper paper){
        this.paperList.add(paper);
    }

    @Override
    public String toString() {
        return "Papers{" +
                "paperList=" + paperList +
                '}';
    }

}
