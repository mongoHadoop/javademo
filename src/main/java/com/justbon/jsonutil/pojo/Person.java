package com.justbon.jsonutil.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ganli
 * @version 1.0
 * @file Person.java
 * @Modified By：
 * @date 2021-06-24 上午10:19
 * @description
 */
public class Person {
    private String name;
    private FullName fullName;
    private int age;
    private Date birthday;
    private List<String> hobbies;
    private Map<String, String> clothes;
    private List<Person> friends;

    // getter/setter省略

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Person [name=" + name + ", fullName=" + fullName + ", age="
                + age + ", birthday=" + birthday + ", hobbies=" + hobbies
                + ", clothes=" + clothes + "]\n");
        if (friends != null) {
            str.append("Friends:\n");
            for (Person f : friends) {
                str.append("\t").append(f);
            }
        }
        return str.toString();
    }

}
