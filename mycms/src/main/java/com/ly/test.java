package com.ly;

import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {
        Map<String, Integer> map= new HashMap<String, Integer>();
        map.put("one",1);
        System.out.println(map.get("one"));
    }
}
