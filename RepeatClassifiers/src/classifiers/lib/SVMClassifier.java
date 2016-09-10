/*
 * $Id: SVMClassifier.java,v 1.16 2003/08/22 16:18:10 hwawen Exp $
 *
 * Copyright (c) 2003 The Regents of the University of California.
 * All rights reserved. See the file COPYRIGHT for details.
 */
package classifiers.lib;

import libsvm.*;
import java.util.*;
import java.io.*;

import org.apache.log4j.Logger;


import data.DataSet;

/**
 * This class uses libsvm, a SVM software library written in Java, to
 * do SVM classification. [1]<p>
 * [1]Chih-Chung Chang and Chih-Jen Lin
 * http://www.csie.ntu.edu.tw/~cjlin/libsvm
 * 
 * @author Heloise Hse (hwawen@eecs.berkeley.edu)
 */
public class SVMClassifier   {
	
	
	private static transient final Logger logger = Logger.getLogger(  SVMClassifier.class);
    private boolean _normalizeScale = true;
    private int _numFeatures;
    private HashMap _labelToType = new HashMap();
    private svm_model _svmModel;
    private svm_parameter _svmParam=null;
    private double[] _minFeatureVals;
    private double[] _maxFeatureVals;
    private svm_problem prob;
    /**
     *  Create a SVM Classifier with default parameters and specify
     *  whether the data needs to be scaled.
     */
    public SVMClassifier(boolean normalizeScale){
        _normalizeScale = normalizeScale;
        _svmParam = defaultSVMParam();
    }
    
    /**
     *  Create a SVM Classifier, specify whether the data needs to be
     *  scaled, and use the given set of parameters for the SVM
     *  classifier.
     */
    public SVMClassifier(boolean normalizeScale, svm_parameter p){
        _normalizeScale = normalizeScale;
        _svmParam = p;
    }
public void loadClassifier( String  fmodel ){
	
	
	
	try {
		_svmModel = svm.svm_load_model(fmodel);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
    /**
     * Classify the given example using a single-class classifier.
     */
    public Classification classifySingle(Double[] fs) {
//        if(_normalizeScale){
//            fs = scale(fs);
//        }
        svm_node[] ex = new svm_node[fs.length];
        for(int j=0; j<fs.length; j++){
            ex[j]=new svm_node();
            ex[j].index=j;
            ex[j].value=fs[j];
        }
        double val = svm.svm_predict(_svmModel, ex);
        double[] values = new double[1];
        values[0]=0;
        svm.svm_predict_values(_svmModel, ex, values);
        String[] types = new String[1];
        double[] confidences = new double[1];
       // types[0] = (String)_labelToType.values().iterator().next();
        
        
        confidences[0] = Math.abs(values[0])*val;
       // confidences[1] = values[1]*-1;
        
	//logger.info("  confindence  =  "+    confidences[0]+"    ");
        return new Classification(types,confidences);
    }

    /**
     * Classify the given example using SVM.
     */
//    public Classification classify(FeatureSet fs) throws ClassifierException {
//        if(_svmParam.svm_type == svm_parameter.ONE_CLASS){
//            System.out.println("ONE_CLASS");
//            return classifySingle(fs);
//        }
//        else{
//            if(_normalizeScale){
//                fs = scale(fs);
//            }
//            svm_node[] ex = new svm_node[fs.getFeatureCount()];
//            for(int j=0; j<fs.getFeatureCount(); j++){
//                ex[j]=new svm_node();
//                ex[j].index=j;
//                ex[j].value=fs.getFeature(j);
//            }
//            int label = (int)svm.svm_predict(_svmModel, ex);
//            String type =(String)_labelToType.get(new Integer(label));
//            String[] types = {type};
//            double[] values = {1.0};
//            return new Classification(types,values);
//            /*
//            svm.SVMResult result = svm.svm_predict_hh(_svmModel, ex);
//            int[] labels = result.classPrediction();
//            ArrayList pairs = new ArrayList();
//            for(int i=0; i<labels.length; i++){
//                String type =(String)_labelToType.get(new Integer(labels[i]));
//                double[] decFuncValues = result.values(labels[i]);
//                for(int j=0; j<decFuncValues.length; j++){
//                    pairs.add(new Pair(type,decFuncValues[j]));
//                }
//            }
//            String[] types = new String[pairs.size()];
//            double[] values = new double[pairs.size()];
//            int i=0;
//            for(Iterator iter=pairs.iterator(); iter.hasNext();){
//                Pair p = (Pair)iter.next();
//                types[i]=p.type;
//                values[i]=p.value;
//                i++;
//            }
//            return new Classification(types,values);
//             */
//        }
//    }

    /**
     * A simple data structure to temporarily store classification
     * results.
     */
    private static class Pair {
        String type;
        double value;

        public Pair(String t, double v){
            type=t;
            value=v;
        }
    }

//    /**
//     * Convert TraininSet examples into libsvm data structure, build a
//     * svm_problem object for the training set, and call svm_train to
//     * compute the svm model.  If scaling is indicated, this method
//     * will scale the data values to be in the range of 0 and 1.
//     */
//    public void train(TrainingSet tset, int numFeatures) throws ClassifierException{
//        _numFeatures = numFeatures;
//        if(_normalizeScale){
//            tset = scale(tset,0,1);//scale values to be in the range of 0 and 1
//        }
//        ArrayList list = new ArrayList();
//        int label=1;
//        /// get all types and category of the training set 
//        for(Iterator types = tset.types(); types.hasNext();){
//            String type = (String)types.next();  //for each label 
//            _labelToType.put(new Integer(label),type); // put the label with its type in a map
//            // lop over the positive examples 
//            for(Iterator examples = tset.positiveExamples(type); examples.hasNext();){
//                FeatureSet fs = (FeatureSet)examples.next(); // get features for this 
//                svm_node[] ex = new svm_node[fs.getFeatureCount()];
//                for(int j=0; j<fs.getFeatureCount(); j++){
//                    ex[j]=new svm_node();
//                    ex[j].index=j;
//                    ex[j].value=fs.getFeature(j);
//                }
//                list.add(new Example(label,ex));
//            }
//            label++;            
//        }
//        svm_problem prob = new svm_problem();        
//        int num = list.size();
//        prob.l = num;
//        double[] y = new double[num];
//        svm_node[][] x = new svm_node[num][];
//        for(int i=0; i<num; i++){
//            Example ex=(Example)list.get(i);
//            y[i]=ex.label;
//            x[i]=ex.nodes;
//        }
//        prob.y=y;
//        prob.x=x;
//        if(_svmParam==null){
//            _svmParam = defaultSVMParam();
//        }
//        _svmModel = svm.svm_train(prob,_svmParam);
//
//    }

    /**
     * Set up the default set of parameters for the SVM classifier.
     */
    public static svm_parameter defaultSVMParam(){
        svm_parameter p = new svm_parameter();
        //default values for now
        p = new svm_parameter();
        p.svm_type = svm_parameter.C_SVC;
        p.kernel_type = svm_parameter.RBF;
        p.degree = 3;
        p.gamma = 0.5;
        p.coef0 = 0;
        p.nu = 0.5;
        p.cache_size = 100;
        p.C = 100;
        p.eps = 1e-3;
        p.p = 0.1;
        p.shrinking = 1;
        p.nr_weight = 0;
        p.weight_label = null;
        p.weight = null;
        return p;
    }

    /**
     * Scale the given feature vector based on the scale obtained from
     * the training set.  This method is called by classify to scale
     * the test example.
     */
    public double[] scale(double[] fvals){
        double[] normVals = new double[fvals.length];
        for(int i=0; i<fvals.length; i++){
            normVals[i] = (fvals[i]-_minFeatureVals[i])/(_maxFeatureVals[i]-_minFeatureVals[i]);
        }
       
        
        
        return  normVals;
     }

    
    /**
     * Scale the feature values in the training set to be in the
     * range of [min,max].
     */
    public DataSet scale(DataSet tset, int min, int max){
    	_numFeatures=tset.getFeatureCount();
        _minFeatureVals = new double[_numFeatures];
        _maxFeatureVals = new double[_numFeatures];
        for(int i=0; i<_numFeatures; i++){
            _minFeatureVals[i]=Double.POSITIVE_INFINITY;
            _maxFeatureVals[i]=Double.NEGATIVE_INFINITY;
        }
        //figure out the minimum and maximum values of each feature
     
            for(int j=0;j<tset.getNumOfSamples();j++){
                Double[] fs =  tset.getSample(j);
                for(int i=0; i<fs.length; i++){
                    _minFeatureVals[i]=Math.min(_minFeatureVals[i],fs[i]);
                    _maxFeatureVals[i]=Math.max(_maxFeatureVals[i],fs[i]);
                }
            }
            
 
        //scale feature values to be in the range of [0,1]
        DataSet normalizedSet = new    DataSet();
        normalizedSet.clear();
            for(int j=0;j<tset.getNumOfSamples();j++){
            	 Double[] fs =  tset.getSample(j);
                double[] vals = new double[_numFeatures];
                for(int i=0; i<fs.length; i++){
                    vals[i] = (fs[i]-_minFeatureVals[i])/(_maxFeatureVals[i]-_minFeatureVals[i]);
                }
                normalizedSet.addSample(vals,   tset.getTarget(j));
            }
            
            normalizedSet.ConvertToArrays();

       
        return normalizedSet;
    }

    /**
     * Not an incremental classifier.  Calls to train will remove the
     * original training data and retrain the classifier with the new
     * set.
     */
    public boolean isIncremental(){
        return false;
    }

    public void clear(){}
    
    
    public void initProblem(DataSet trainSet){
        if(_normalizeScale){
        	logger.info(" scale of  IN TRAIN ");
            trainSet = scale(trainSet,-1,1);//scale values to be in the range of 0 and 1
        }
        ArrayList list = new ArrayList();  //create a new array lis 
        int label=1; //start adding labels for the set 
        int featcount=0;
        /// get all types and category of the training set 
 
            for(int i=0;i<trainSet.getNumOfSamples();i++){
            	Double []fs = trainSet.getSample(i); // get features for this
                // now we will have to create nodes (featureindex, feature value )
             svm_node [] ex = new svm_node[  	fs.length];
             featcount=fs.length;
             //   svm_node[] ex = new svm_node[fs.getFeatureNoZeroCount()];
             //   int k=0;
                for(int j=0; j<fs.length; j++){
             //   	if (fs.getFeature(j)!=0){
	                    ex[j]=new svm_node();
	                    ex[j].index=j;
	                    
	                    ex[j].value=fs[j];
 
                }
                list.add(new Example(trainSet.getTarget(i),ex));// add the example ot list
            }
            logger.info( "  NO samples s in this Training is = "+trainSet.getNumOfSamples());     
        logger.info( "  NO of features in this Training is = "+featcount);
       // logger.error(  "   number of categories in this train  = " );
        
    	// create the proble 
            prob = new svm_problem();
        // for the list of exampel 
        int num = list.size();
        //get number of samples 
        prob.l = num;
        //create the label array y 
        double[] y = new double[num];
        //nnow create the two dimentional array of nodes
        svm_node[][] x = new svm_node[num][];
        for(int i=0; i<num; i++){
            Example ex=(Example)list.get(i);
            y[i]=ex.label;
            x[i]=ex.nodes;
        }
        prob.y=y;
        prob.x=x;
    	
    }
    public double RunCross( double c ,double g){
    	
    	svm_parameter svmParam=defaultSVMParam();
    	
    	svmParam.C=c;
    	
    	svmParam.gamma=g;
    	
    	
    	return RunCrossValidateJob(this.prob,svmParam);
    	
    }
    private    double RunCrossValidateJob(svm_problem prob ,  svm_parameter svmParam){
		 
    	//svm_problem prob ,  svm_parameter svmParam
        
        double[] result=new double [prob.l];
        // now i will runt the grid 
        // for each  loop value on  c  from 10 till 1000  with increment of 100 
        //Figure 2: Loose grid search on C = 2−5 , 2−3 , . . . , 215 
        //and γ = 2−15 , 2−13 , . . . , 23 .
        // now test wich values is maximum and 
         /// 
        //logger.error( svmParam);
        
        double accuracy=0;
        logger.info("  svm param    "+svmParam.C+"   g= "+svmParam.gamma );
        
        svm.svm_cross_validation(prob,svmParam, 10, result);
        for (int i = 0; i < result.length; i++) {
           if (prob.y[i]==result[i])
        	   accuracy++; 
			//logger.error( "subosed to be "+	prob.y[i]+ " target  "+i+"  =  "+ result[i]);
		}
        accuracy/=(double)prob.y.length;
        return accuracy;
	 
 }
 
    /**
     * An internal data structure representing an example.
     */
    private static class Example{
        public int label;
        public svm_node[] nodes;
        public Example(int l, svm_node[] n){
            label=l;
            nodes=n;
        }

        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append(label+" ");
            for(int i=0; i<nodes.length; i++){
                sb.append(nodes[i].index+":"+nodes[i].value+" ");
            }
            return sb.toString();
        }
    }
}
