package com.rodrigofrazao.domain.supportConstraintsTest;

import com.rodrigofrazao.domain.supportConstraints.Mask;
import com.rodrigofrazao.domain.supportConstraints.SupportUpdateAlgorithm;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class SupportUpdateAlgorithmTest {

    Mask mask;
    SupportUpdateAlgorithm supportUpdateAlgorithm = new SupportUpdateAlgorithm();

    @Before
    public void setUp(){
        mask = new Mask(8,8);
    }

    @Test
    public void createFalseCircunferenceTest(){
        mask.ones();
        Mask newMask = supportUpdateAlgorithm.circumference(2,4,3,mask);
       Assertions.assertThat(newMask.getMask()[3][3]).isFalse();
    }

    @Test
    public void getRandomAllowedPosition(){
        mask.standardMask();
       int[] position = supportUpdateAlgorithm.findRandomPosition(mask);
        System.out.println(position[0]+" "+position[1]);
       Assertions.assertThat(position[0]).isNotEqualTo(1);
    }

    @Test
    public void getNewMaskTest(){

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
