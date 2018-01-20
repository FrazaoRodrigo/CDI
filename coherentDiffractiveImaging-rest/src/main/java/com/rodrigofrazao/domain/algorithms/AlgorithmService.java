package com.rodrigofrazao.domain.algorithms;

import com.rodrigofrazao.domain.image.ComplexImage;
import com.rodrigofrazao.domain.supportConstraints.Mask;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AlgorithmService {

    ArrayList<ComplexImage> guesses = new ArrayList();
    ArrayList<List<Double>> outerErrorList;

    public ArrayList errorReduction_multiRandomPhaseIteration(double[][] diffractionPattern, int iter, int nbrOfParallelPhases, Mask mask) throws ExecutionException, InterruptedException {
        outerErrorList = new ArrayList<>();

        for (int i =0;i<nbrOfParallelPhases;++i) {
            ErrorReduction errorReduction = new ErrorReduction(diffractionPattern);
            Double[] errorEr = errorReduction.iterationWithSupport(iter, mask);
            errorMananger(errorEr);
            guesses.add(errorReduction.getGuess());
        }
        return guesses;
    }

    public ArrayList errorReduction_multiPhaseIteration(double[][] diffractionPattern,int iter,ArrayList<ComplexImage> guesses,ArrayList<Mask> masks) throws ExecutionException, InterruptedException {
        outerErrorList = new ArrayList<>();
        this.guesses=guesses;
        for (int i =0;i<guesses.size();++i) {
            ErrorReduction errorReduction = new ErrorReduction(diffractionPattern,guesses.get(i));
            Double[] errorEr = errorReduction.iterationWithSupport(iter, masks.get(i));
            errorMananger(errorEr);
            guesses.set(i,errorReduction.getGuess());
        }
        return guesses;
    }

    public ComplexImage getFinalGuess() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    private void errorMananger(Double[] errorEr) {
        ArrayList<Double> errorList =  new ArrayList();
        errorList.addAll(Arrays.asList(errorEr));
        outerErrorList.add(errorList);
    }


}
