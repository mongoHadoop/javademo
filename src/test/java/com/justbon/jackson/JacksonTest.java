package com.justbon.jackson;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.justbon.util.Student;
import org.junit.Test;

import java.util.*;

@SuppressWarnings("ALL")
public class JacksonTest {
    /**
     * 序列化
     */
    @Test
    public void test01(){
        Student s = new Student();
        s.setName("cjj");
        s.setAge(22);
        s.setProfileImageUrl("link");
        String s1 = JsonUtil.obj2String(s);
        System.out.println(s1);

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("a", Arrays.asList(1, 2, 3));
        map.put("b", Arrays.asList(1, 2, 3));
        String result1 = JsonUtil.obj2StringPretty(map);
        System.out.println(result1);
    }

    /**
     * 反序列化
     */
    @Test
    public void Test02(){
        String str = "{\"name\":\"name\",\"age\":10,\"profileImageUrl\":\"link\"}";
        Student student = JsonUtil.str2Obj(str, Student.class);
        System.out.println(student);
    }
    @Test
    public void test03() throws Exception {

        Student student1 = new Student();
        student1.setAge(10);
        student1.setName("name1");
        student1.setProfileImageUrl("link1");

        Student student2 = new Student();
        student2.setAge(20);
        student2.setName("name2");
        student2.setProfileImageUrl("link2");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        String result = JsonUtil.obj2String(studentList);
        // [{"name":"name1","age":10,"profileImageUrl":"link1"},{"name":"name2","age":20,"profileImageUrl":"link2"}]
        System.out.println(result);

        List<Student> finalList = JsonUtil.string2Obj(result, new TypeReference<List<Student>>() {});
        // [{name=name1, age=10, profileImageUrl=link1}, {name=name2, age=20, profileImageUrl=link2}]
        System.out.println(finalList);
    }
    @Test
    public void test04() throws Exception {

        Student student1 = new Student();
        student1.setAge(10);
        student1.setName("name1");
        student1.setProfileImageUrl("link1");

        Student student2 = new Student();
        student2.setAge(20);
        student2.setName("name2");
        student2.setProfileImageUrl("link2");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        String result = JsonUtil.obj2String(studentList);
        // [{"name":"name1","age":10,"profileImageUrl":"link1"},{"name":"name2","age":20,"profileImageUrl":"link2"}]
        System.out.println(result);

        List<Student> finalList = JsonUtil.string2Obj(result, List.class, Student.class);
        // [{name=name1, age=10, profileImageUrl=link1}, {name=name2, age=20, profileImageUrl=link2}]
        System.out.println(finalList);
    }
    @Test
    public void jsonProperty() throws Exception {

        String str = "{\"name\":\"name\",\"age\":10,\"setImage\":\"link\"}";
        Student student1 = JsonUtil.string2Obj(str, Student.class);
        // name
        System.out.println(student1.getName());
        // 10
        System.out.println(student1.getAge());
        // link
        System.out.println(student1.getProfileImageUrl());
        // {"name":"name","age":10,"getImage":"link"}
        System.out.println(JsonUtil.obj2String(student1));
    }

    /**
     * 除了将json转为对象外，还可以用Tree Mode方式解析JSON，全程无脑操作，
     * 除了一些特别复杂的JSON，或者只取JSON中的一部分，千万不要用这种二B方式解析JSON。
     * 正确的方式是将JSON直接转为对象。
     * @throws Exception
     */
    @Test
    public void parseJson() throws Exception {
        String jsonStr = "{\"name\":\"zhansan\",\"age\":100,\"schools\":[{\"name\":\"tsinghua\",\"location\":\"beijing\"},{\"name\":\"pku\",\"location\":\"beijing\"}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonStr);

        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        // name is zhansan age is 100
        System.out.println("name is " + name + " age is " + age);

        JsonNode schoolsNode = jsonNode.get("schools");
        for (int i = 0; i < schoolsNode.size(); i++) {
            String schooleName = schoolsNode.get(i).get("name").asText();
            String schooleLocation = schoolsNode.get(i).get("location").asText();
            // schooleName is tsinghua schooleLocation is beijing
            // schooleName is pku schooleLocation is beijing
            System.out.println("schooleName is " + schooleName + " schooleLocation is " + schooleLocation);
        }
    }
}