package com.zte.myhomework;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
/**
 * Created by wang_ting on 2018/4/9.
 * @author wang_ting
 */
public class SudentSortTest {

    private List<StudentSort.Student> studentList;

    @Before
    public void setUp() {
        studentList = StudentSort.getStudentList();
    }

    @Test
    public void testOrderStudent() throws Exception {
        StudentSort.orderStudent(1, false);
        Assert.assertEquals(studentList.get(0), new StudentSort.Student("17031211422", 22, 85));
        StudentSort.orderStudent(2, false);
        Assert.assertEquals(studentList.get(0), new StudentSort.Student("17031211432", 18, 60));
        StudentSort.orderStudent(3, false);
        Assert.assertEquals(studentList.get(0), new StudentSort.Student("17031211432", 18, 60));
        StudentSort.orderStudent(1, true);
        Assert.assertEquals(studentList.get(0), new StudentSort.Student("17031211496", 20, 70));
        StudentSort.orderStudent(2, true);
        Assert.assertEquals(studentList.get(0), new StudentSort.Student("17031211481", 24, 76));
        StudentSort.orderStudent(3, true);
        Assert.assertEquals(studentList.get(0), new StudentSort.Student("17031211451", 23, 99));
    }
}