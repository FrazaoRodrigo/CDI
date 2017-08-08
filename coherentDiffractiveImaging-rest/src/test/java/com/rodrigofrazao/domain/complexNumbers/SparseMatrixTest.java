package com.rodrigofrazao.domain.complexNumbers;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.image.ReadImage;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class SparseMatrixTest {

    ComplexImage complexImage;
    Complex[] arraytest= new Complex[5];
    double[][] testArray = new double[64][32];

    @Before
    public void setUp() {
        File f = new File("C:\\Users\\rodrpmff\\CDI\\images\\example.bmp");
        ReadImage image = new ReadImage(f);
        complexImage = image.bufferedImageToComplexImage();

        for(int k = 0; k < 5; ++k) {
          arraytest[k]=new Complex(Math.random() * 256,0);
        }
        for(int j = 0; j < 64; ++j) {
            for(int k = 0; k < 32; ++k) {
                testArray[j][k] =  Math.random() * 256;
            }
        }
    }

    @Test
    public void sparseMatrixCreationTest(){
        SparseMatrix sparseMatrix = new SparseMatrix(complexImage);
        Assertions.assertThat(sparseMatrix).isNotNull();
    }

    @Test
    public void sparseMatrixCreationTestAmplitudeOnly(){
        ComplexImage test = new AmplitudeOnlyImage(testArray);
        SparseMatrix sparseMatrix = new SparseMatrix(test);
        Assertions.assertThat(sparseMatrix.getElement(5,5)).isNotNull();
    }

    @Test
    public void getCollumsTest(){
        ComplexImage test = new AmplitudeOnlyImage(testArray);
        SparseMatrix sparseMatrix = new SparseMatrix(test);
        Complex[] collum = sparseMatrix.getCollum(31);
        Assertions.assertThat(collum[2]).isNotNull();
        Assertions.assertThat(collum.length).isEqualTo(64);
    }

    @Test
    public void getRowTest(){
        ComplexImage test = new AmplitudeOnlyImage(testArray);
        SparseMatrix sparseMatrix = new SparseMatrix(test);
        Complex[] row = sparseMatrix.getRow(31);
        Assertions.assertThat(row[2]).isNotNull();
        Assertions.assertThat(row.length).isEqualTo(32);
    }

    @Test
    public void setCollumsTest(){
       SparseMatrix sparseMatrix = new SparseMatrix(5,3);
       sparseMatrix.setCollum(2,arraytest);
        sparseMatrix.setCollum(0,arraytest);
        sparseMatrix.setCollum(1,arraytest);
        Assertions.assertThat(sparseMatrix.getWidth()).isEqualTo(3);
        Assertions.assertThat(sparseMatrix.getElement(1,1)).isNotNull();
    }

    @Test
    public void setRowTest(){
        SparseMatrix sparseMatrix = new SparseMatrix(3,5);
        sparseMatrix.setRow(2,arraytest);
        sparseMatrix.setRow(0,arraytest);
        sparseMatrix.setRow(1,arraytest);
        Assertions.assertThat(sparseMatrix.getElement(1,1)).isNotNull();
    }


}
