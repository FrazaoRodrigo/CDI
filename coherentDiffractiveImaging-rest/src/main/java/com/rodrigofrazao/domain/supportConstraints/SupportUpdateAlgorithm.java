package com.rodrigofrazao.domain.supportConstraints;

import java.util.ArrayList;
import java.util.Random;

public class SupportUpdateAlgorithm {

    public SupportUpdateAlgorithm() {
    }

    public ArrayList updateNewMasks(ArrayList<Mask> masks,int radius){
        for(int i =0; i<masks.size();++i){
            int[] position = findRandomPosition(masks.get(i));
            masks.set(i,circumference(radius, (double) position[0], (double) position[1], masks.get(i)));
        }
        return masks;
    }

    public ArrayList getNewMask(Mask mask, int radius, int nbrOfNewMasks) {
        ArrayList<Mask> maskList = new ArrayList<>();
        for (int i = 0; i < nbrOfNewMasks; ++i) {
            int[] position = findRandomPosition(mask);
            maskList.add(circumference(radius, (double) position[0], (double) position[1], mask));
        }
        return maskList;
    }

    public Mask circumference(int radius, double xCenter, double yCenter, Mask mask) {

        for (int j = (int) xCenter - radius ; j <= (int) xCenter + radius; ++j) {
            for (int k = (int) yCenter - radius ; k <= (int) yCenter + radius; ++k) {
                mask.getMask()[j][k] = false;
            }
        }
        return mask;
    }

    public int[] findRandomPosition(Mask mask) {
        ArrayList<int[]> positionsList = new ArrayList<>();
        for (int j = 0; j < mask.getMask().length; ++j) {
            for (int k = 0; k < mask.getMask()[0].length; ++k) {
                if (mask.getMask()[j][k] == true) {
                    int[] position = new int[]{j, k};
                    positionsList.add(position);
                }
            }
        }
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(positionsList.size());
        return positionsList.get(index);
    }
}
