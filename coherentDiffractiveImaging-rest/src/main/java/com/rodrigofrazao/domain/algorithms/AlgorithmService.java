package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmService {

    ArrayList<List<Double>> outerErrorList;

    public ArrayList errorReduction_multiRandomPhaseIteration(double[][] diffractionPattern, int iter, int nbrOfParallelPhases, Mask mask){
        outerErrorList = new ArrayList<>();
        ArrayList<ComplexImage> guesses = new ArrayList();

        for (int i =0;i<nbrOfParallelPhases;++i) {
            ErrorReduction errorReduction = new ErrorReduction(diffractionPattern);
            Double[] errorEr = errorReduction.iterationWithSupport(iter, mask);
            errorMananger(errorEr);
            guesses.add(errorReduction.getGuess());
        }
        return guesses;
    }

    public ArrayList errorReduction_multiPhaseIteration(double[][] diffractionPattern,int iter,ArrayList<ComplexImage> guesses,ArrayList<Mask> masks){
        outerErrorList = new ArrayList<>();
        int nbrOfParallelPhases =guesses.size();
        for (int i =0;i<nbrOfParallelPhases;++i) {
            ErrorReduction errorReduction = new ErrorReduction(diffractionPattern,guesses.get(i));
            Double[] errorEr = errorReduction.iterationWithSupport(iter, masks.get(i));
            errorMananger(errorEr);
            guesses.set(i,errorReduction.getGuess());
        }
        return guesses;
    }

    private void errorMananger(Double[] errorEr) {
        ArrayList<Double> errorList =  new ArrayList();
        errorList.addAll(Arrays.asList(errorEr));
        outerErrorList.add(errorList);
    }
}
