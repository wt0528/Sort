package com.zte.myhomework;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wang_ting on 2018/4/9.
 * @author wang_ting
 */
public class StudentSort {

    private static final String SEPARATOR = File.separator;
    private static final String PROJECT_ROOTPATH = System.getProperty("user.dir");
    private static final String FILEPATH = PROJECT_ROOTPATH + SEPARATOR + "static" + SEPARATOR + "student.csv";
    private static List<Student> studentList = new ArrayList<>();

    static {
        loadData();
    }

    private static void loadData() {
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(FILEPATH);
            // 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                Student student = new Student();
                student.setSno(csvReader.get("SNO")).setAge(Integer.parseInt(csvReader.get("AGE"))).setScore(Integer.parseInt(csvReader.get("SCORE")));
                studentList.add(student);
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void orderStudent(int orderType, boolean reverse) {
        if (orderType == 1) {
            orderBySNO(reverse);
        } else if (orderType == 2) {
            orderByAGE(reverse);
        } else if (orderType == 3) {
            orderBySCORE(reverse);
        } else {
            System.out.println("排序类型不存在，请重新选择!");
        }
    }

    private static void orderBySNO(boolean reverse) {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (reverse) {
                    return (-1) * o1.getSno().compareTo(o2.getSno());
                }
                return o1.getSno().compareTo(o2.getSno());
            }
        });
        getResult(1);
    }

    private static void orderByAGE(boolean reverse) {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAge() == o2.getAge()) {
                    return 0;
                } else {
                    if (reverse) {
                        return (-1) * (o1.getAge() > o2.getAge() ? 1 : -1);
                    }
                    return o1.getAge() > o2.getAge() ? 1 : -1;
                }
            }
        });
        getResult(2);
    }

    private static void orderBySCORE(boolean reverse) {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getScore() == o2.getScore()) {
                    return 0;
                } else {
                    if (reverse) {
                        return (-1) * (o1.getScore() > o2.getScore() ? 1 : -1);
                    }
                    return o1.getScore() > o2.getScore() ? 1 : -1;
                }
            }
        });
        getResult(3);
    }

    /**
     * @param orderType : 排序类型(1 : 按学号排序； 2 : 按年龄排序； 3 : 按分数排序)
     *
     */
    private static void getResult(int orderType) {
        System.out.println("排序结果为： ");
        for (Student student : studentList) {
            System.out.println(student);
        }
        writeData(orderType);
    }

    private static void writeData(int orderType) {
        String filePath;

        if (orderType == 1) {
            filePath = PROJECT_ROOTPATH + SEPARATOR + "static" + SEPARATOR + "studentOrderBySNO.csv";
        } else if (orderType == 2) {
            filePath = PROJECT_ROOTPATH + SEPARATOR + "static" + SEPARATOR + "studentOrderByAGE.csv";
        } else {
            filePath = PROJECT_ROOTPATH + SEPARATOR + "static" + SEPARATOR + "studentOrderBySCORE.csv";
        }

        try {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("utf-8"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头
            String[] headers = {"SNO","AGE","SCORE"};
            csvWriter.writeRecord(headers);

            for (Student student : studentList) {
                String[] content = {student.getSno(), student.getAge() + "", student.getScore() + ""};
                csvWriter.writeRecord(content);
            }
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Student {

        private String sno;
        private int age;
        private int score;

        public Student() {};

        public Student(String sno, int age, int score) {
            this.sno = sno;
            this.age = age;
            this.score = score;
        }

        public String getSno() {
            return sno;
        }

        public Student setSno(String sno) {
            this.sno = sno;
            return this;
        }

        public int getAge() {
            return age;
        }

        public Student setAge(int age) {
            this.age = age;
            return this;
        }

        public int getScore() {
            return score;
        }

        public Student setScore(int score) {
            this.score = score;
            return this;
        }

        @Override
        public String toString() {
            return "sno: " + sno + " age: " + age + " score: " + score;
        }

        @Override
        public boolean equals(Object obj) {
            Student student = (Student) obj;
            return this.getSno().equals(student.getSno()) && this.getAge() == student.getAge()
                    && this.getScore() == student.getScore();
        }
    }

    public static List<Student> getStudentList() {
        return studentList;
    }
}
