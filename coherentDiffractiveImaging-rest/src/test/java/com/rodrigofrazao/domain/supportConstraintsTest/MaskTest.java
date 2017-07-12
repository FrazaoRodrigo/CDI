package com.rodrigofrazao.domain.supportConstraintsTest;

import com.rodrigofrazao.domain.supportConstraints.Mask;
import com.sun.beans.decoder.ValueObject;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MaskTest {

    Mask mask;
     boolean[][] trueArray={{true, true},{true, true},{true, true}};

    @Before
    public void setUp(){
        mask = new Mask(10,10);
    }

    @Test
    public void test(){
        printArray(mask.standardMask());
    }

    @Test
    public void onesMaskTest_shouldReturnAllTrue(){
        Assert.assertArrayEquals(mask.ones().getMask(),trueArray);
    }

    @Test
    public void drawcircletest() throws Exception {
      printArray(mask.lowpassfilter(2).getMask());
    }

    public void printArray(boolean[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
