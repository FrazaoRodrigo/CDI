package com.rodrigofrazao.domain.fourierTransform;

import com.rodrigofrazao.domain.image.AmplitudeOnlyImage;
import com.rodrigofrazao.domain.image.ComplexImage;

public class Fftshift {

   public static double[][] shiftOrigin(double[][] data){
        int numberOfRows = data.length;
        int numberOfCols = data[0].length;
        int newRows;
        int newCols;

        double[][] output = new double[numberOfRows][numberOfCols];

        if(numberOfRows%2 != 0){//odd
            newRows = numberOfRows + (numberOfRows + 1)/2;
        }else{//even
            newRows = numberOfRows + numberOfRows/2;
        }

        if(numberOfCols%2 != 0){//odd
            newCols = numberOfCols + (numberOfCols + 1)/2;
        }else{//even
            newCols = numberOfCols + numberOfCols/2;
        }

        double[][] temp = new double[newRows][newCols];

        for(int row = 0;row < numberOfRows;row++){
            for(int col = 0;col < numberOfCols;col++){
                temp[row][col] = data[row][col];
            }
        }

        //Do the horizontal shift first
        if(numberOfCols%2 != 0){
            for(int row = 0;row < numberOfRows;row++){
                for(int col = 0; col < (numberOfCols+1)/2;col++){
                    temp[row][col + numberOfCols] = temp[row][col];
                }
            }
            for(int row = 0;row < numberOfRows;row++){
                for(int col = 0; col < numberOfCols;col++){
                    temp[row][col] = temp[row][col+(numberOfCols + 1)/2];
                }
            }

        }else{
            for(int row = 0;row < numberOfRows;row++){
                for(int col = 0; col < numberOfCols/2;col++){
                    temp[row][col + numberOfCols] = temp[row][col];
                }
            }

            for(int row = 0;row < numberOfRows;row++){
                for(int col = 0; col < numberOfCols;col++){
                    temp[row][col] = temp[row][col + numberOfCols/2];
                }
            }
        }

        if(numberOfRows%2 != 0){
            for(int col = 0;col < numberOfCols;col++){
                for(int row = 0; row < (numberOfRows+1)/2;row++){
                    temp[row + numberOfRows][col] = temp[row][col];
                }
            }

            for(int col = 0;col < numberOfCols;col++){
                for(int row = 0;
                    row < numberOfRows;row++){
                    temp[row][col] = temp[row+(numberOfRows + 1)/2][col];
                }
            }

        }else{
            for(int col = 0;col < numberOfCols;col++){
                for(int row = 0; row < numberOfRows/2;row++){
                    temp[row + numberOfRows][col] = temp[row][col];
                }
            }

            for(int col = 0;col < numberOfCols;col++){
                for(int row = 0; row < numberOfRows;row++){
                    temp[row][col] = temp[row + numberOfRows/2][col];
                }
            }
        }

        for(int row = 0;row < numberOfRows;row++){
            for(int col = 0;col < numberOfCols;col++){
                output[row][col] = temp[row][col];
            }
        }
        return output;
    }
}
