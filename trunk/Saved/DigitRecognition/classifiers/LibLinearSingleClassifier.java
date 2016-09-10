/**
 * 
 */
package classifiers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import liblinear.FeatureNode;
import liblinear.Linear;
import liblinear.Model;
import liblinear.Parameter;
import liblinear.Problem;
import liblinear.SolverType;
import libsvm.svm;



import org.apache.log4j.Logger;

import classifiers.results.Classification;
import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;



/**
 * @author TOSHIBA
 *
 */
public class LibLinearSingleClassifier extends SingleClassifiers {

	/**
	 * 
	 */
	

	private static transient final Logger logger = Logger.getLogger(  LibLinearSingleClassifier.class);

  
       Model  libModel; 
      Problem libProblem;
      
      Parameter libParamter=null;
      
      
      
//    private  _svmModel;
//    private svm_parameter _svmParam=null;

//    private svm_problem prob;
//    
	
	
	public LibLinearSingleClassifier() {
   
	}
    /**
     * Set up the default set of parameters for the SVM classifier.
     */
    public static  Parameter defaultParam(){
        Parameter p = new Parameter(SolverType.L2R_L1LOSS_SVC_DUAL,100 , 0.01);
        //default values for now
       
     p.setC(100);
     p.setEps(0.01);
    
    
    
   
        return p;
    }



	@Override
	public void CrossValidate() {
		 DataResults=new ResultSetAcc();
		Problem prob = setDataProblemForTraining();
	    if(libParamter==null){
           	libParamter = defaultParam();
           }
	    
	    // now the cross validation i need to check the c and v 
	    
      
     
     int[] result=new  int [prob.l];
     // now i will runt the grid 
     // for each  loop value on  c  from 10 till 1000  with increment of 100 
     //Figure 2: Loose grid search on C = 2−5 , 2−3 , . . . , 215 
     //and γ = 2−15 , 2−13 , . . . , 23 .
     // now test wich values is maximum and 
      /// 
     //logger.error( svmParam);
     
     double accuracy=0;
 //    logger.info("  svm param    "+svmParam.C+"   g= "+svmParam.gamma );
     
     Linear.crossValidation( prob, libParamter,10,result);//.svm_train(prob,_svmParam);
     
     double correct=0,incorrect=0;
     for (int i = 0; i < result.length; i++) {
     	 DataResults.addResult( result[i],prob.y[i]);
     	//DataResults.addSampleError(i, (int) result[i] , " Error in Digit"+prob.y[i]+" Recognizied as  "+result[i]);
        if (prob.y[i]==result[i]){
     	   accuracy++;
     	  correct++;
			//logger.error( "subosed to be "+	prob.y[i]+ " target  "+i+"  =  "+ result[i]);
		}
        else 
        {
        	// DataResults.addResult( result[i],prob.y[i]);
        	DataResults.addSampleError(i, (int) result[i] , " Error in Digit"+prob.y[i]+" Recognizied as  "+result[i]);
        	 incorrect++;
        }
	}
     accuracy/=(double)prob.y.length;
     
     
     Result = new  ClassifierResult();
     Result.setDataResults(DataResults);
	 Result.setNumberOfIncorrect(correct);
	 Result.setPercentCorrect(accuracy);
	 Result.setNumberOfTestSamples(prob.y.length);

	 Result.setPercentError((double)incorrect/(double)prob.y.length);
     
     
     
     
     
     
	
	}
	
	  /**
     * An internal data structure representing an example.
     */
    private static class Example{
        public int label;
        public FeatureNode[] nodes;
        public Example(int l, FeatureNode[] n){
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
    
    

    
    @SuppressWarnings("unchecked")
	private Problem setDataProblemForTraining(){
    	
    	   if(_normalizeScale){
               TrainData = scale(TrainData,0,1);//scale values to be in the range of 0 and 1
           }
           ArrayList list = new ArrayList();
           int label=1;
           for (int i = 0; i < TrainData.getClassLabels().size(); i++) {
           	   String type = TrainData.getClassLabels().get(i); 
           	   _labelToType.put(new Integer(i),type);
           	
           	
           	
   		}
         
           //get 
           for (int i = 0; i < TrainData.getNumOfSamples(); i++) {
   			
           	
           	  Double[] fs = TrainData.getSample(i); // get features for this 
           	  
           	  
                 FeatureNode[] ex = new FeatureNode[fs.length];
                 for(int j=0; j<fs.length; j++){
                     ex[j]=new FeatureNode(j+1,fs[j]);
                    
                 }
                  int labelp=getByValue(_labelToType, TrainData.getStringTarget(i));
                 // get the label for target ..
                 
                   // check if target didi exist before or not. ....
                 
                 list.add(new Example(labelp,ex));
           	
   		}
           
            
           
           libProblem = new Problem();        
           int num = list.size();
           libProblem.l = num;
           int[] y = new int[num];
          FeatureNode[][] x = new FeatureNode[num][];
           for(int i=0; i<num; i++){
               Example ex=(Example)list.get(i);
               y[i]=ex.label;
               x[i]=ex.nodes;
           }
           libProblem.y=y;
           libProblem.x=x;
       

           return libProblem;
    	
    }
    /**
     * Convert TraininSet examples into libsvm data structure, build a
     * svm_problem object for the training set, and call svm_train to
     * compute the svm model.  If scaling is indicated, this method
     * will scale the data values to be in the range of 0 and 1.
     */
    public void train() {
    	libProblem = setDataProblemForTraining();
        if(libParamter==null){
           	libParamter = defaultParam();
           }
        
     //   liblinear.Linear.enableDebugOutput();
     
    	
        
    	        libModel= Linear.train( libProblem, libParamter);//.svm_train(prob,_svmParam);

    }

    
    
//    /**
//     * Classify the given example using a single-class classifier.
//     */
//    private Classification classifySingle(double[] fs) {
//        if(_normalizeScale){
//            fs = scale(fs);
//        }
//        FeatureNode[] ex = new  FeatureNode[fs.length];
//        for(int j=0; j<fs.length; j++){
//            ex[j]=new FeatureNode(j, fs[j]);
////            ex[j].index=j;
////            ex[j].value=fs[j];
//        }
//        
//        double val =    Linear.predict(libModel, ex);
//         
//       // double val = svm.svm_predict(_svmModel, ex);
//        String[] types = new String[1];
//        double[] confidences = new double[1];
//        types[0] = (String)_labelToType.values().iterator().next();
//        confidences[0] = val;
//        return new Classification(types,confidences);
//    }

    /**
     * Classify the given example using SVM.
     */
    public String getInstanceClass(double[] fs) {
//        i == svm_parameter.ONE_CLASS){
//            logger.info("ONE_CLASS");
//            return classifySingle(fs);
//        }
//        else{
            if(_normalizeScale){
                fs = scale(fs);
            }
            FeatureNode[] ex = new  FeatureNode[fs.length];
            for(int j=0; j<fs.length; j++){
                ex[j]=new FeatureNode(j+1, fs[j]);
//                ex[j].index=j;
//                ex[j].value=fs[j];
            }
            
            int label = (int)Linear.predict(libModel, ex);
            logger.info(" _ now has table has "+"    for label "+label+"   and "+_labelToType);
            String type =(String)_labelToType.get(new Integer(label));
            String[] types = {type};
            double[] values = {1.0};
            return type;
            /*
            svm.SVMResult result = svm.svm_predict_hh(_svmModel, ex);
            int[] labels = result.classPrediction();
            ArrayList pairs = new ArrayList();
            for(int i=0; i<labels.length; i++){
                String type =(String)_labelToType.get(new Integer(labels[i]));
                double[] decFuncValues = result.values(labels[i]);
                for(int j=0; j<decFuncValues.length; j++){
                    pairs.add(new Pair(type,decFuncValues[j]));
                }
            }
            String[] types = new String[pairs.size()];
            double[] values = new double[pairs.size()];
            int i=0;
            for(Iterator iter=pairs.iterator(); iter.hasNext();){
                Pair p = (Pair)iter.next();
                types[i]=p.type;
                values[i]=p.value;
                i++;
            }
            return new Classification(types,values);
             */
        }
    
    public Classification getInstanceDistribution(double[] fs) {
//      i == svm_parameter.ONE_CLASS){
//          logger.info("ONE_CLASS");
//          return classifySingle(fs);
//      }
//      else{
          if(_normalizeScale){
              fs = scale(fs);
          }
          FeatureNode[] ex = new  FeatureNode[fs.length];
          for(int j=0; j<fs.length; j++){
              ex[j]=new FeatureNode(j+1, fs[j]);

          }

         
          double[] probab=new double[_labelToType.size()];
		int label = Linear.predictValues(libModel, ex,probab);
  
     
          String[] types = new String[probab.length];
          double[] values = new double[probab.length];
          int i=0;
  for (int j = 0; j < values.length; j++) {
	          types[i]=(String)_labelToType.get(new Integer(i));
              values[i]=probab[i];
}
          
       
          return new Classification(types,values);
            
      }
	@Override
	public void loadModel(String string) {
		//svm_model
		try {
			 // Deserialize from a file
	        File file = new File(string+".ser");
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
	        // Deserialize the object
	        LibLinearSingleClassifier libClass= (  LibLinearSingleClassifier) in.readObject();
	        
	        this.CopyClassifierData(libClass);
	        in.close();
	        
			libModel= Linear.loadModel( new File(string));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		}
		
	}
	@Override
	public void saveModel(String string) {
		 
		try {
		       // Serialize to a file
	        ObjectOutput out = new ObjectOutputStream(new FileOutputStream(string+".ser"));
	        out.writeObject(this);
	        out.close();
			Linear.saveModel(new File(string), libModel);
		 
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public void trainProbability() {
//	 	libProblem = setDataProblemForTraining();
//        if(libParamter==null){
//           	libParamter = defaultParam();
//           }
//        
//    	        libModel= Linear.train( libProblem, libParamter);//.svm_train(prob,_svmParam);
		train();
	}
    
   // }
}
