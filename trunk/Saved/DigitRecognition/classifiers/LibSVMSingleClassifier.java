package classifiers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import libsvm.svm;
import libsvm.svm_model;
import libsvm.svm_node;
import libsvm.svm_parameter;
import libsvm.svm_problem;
import classifiers.results.Classification;
import classifiers.results.ClassifierResult;
import classifiers.results.ResultSetAcc;



public class LibSVMSingleClassifier extends SingleClassifiers implements Serializable{
	private static transient final Logger logger = Logger.getLogger(  LibSVMSingleClassifier .class);
  private  svm_model _svmModel;
  private svm_parameter _svmParam=null;

  private  svm_problem prob;

  /**
   * Set up the default set of parameters for the SVM classifier.
   */
  public static svm_parameter defaultSVMParam(){
      svm_parameter p = new svm_parameter();
      //default values for now
      p = new svm_parameter();
      p.svm_type = svm_parameter.C_SVC;
      p.kernel_type = svm_parameter.LINEAR;
      p.degree = 3;
      p.gamma = 0.01;
      p.coef0 = 0;
      p.nu = 0.5;
      p.cache_size = 40;
      p.C = 100;
      p.eps = 1e-3;
      p.p = 0.1;
      p.shrinking = 0;
      p.nr_weight = 0;
      p.weight_label = null;
      p.weight = null;
      p.probability=0;
      return p;
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
  }
      private svm_problem setDataProblemForTraining(){

   	   if(_normalizeScale){
              TrainData = scale(TrainData,0,1);//scale values to be in the range of 0 and 1
          }
          ArrayList list = new ArrayList();
          int label=1;
          for (int i = 0; i < TrainData.getClassLabels().size(); i++) {
          	   String type = TrainData.getClassLabels().get(i); 
          	   //_labelToType.put(new Integer(TrainData.getClassValues().get(i)),type);
          	   //(TrainData.getClassValues().get(i),
          	 _labelToType.put(new Integer(i),type);
          	
          	
  		}
          
          for (int i = 0; i < TrainData.getNumOfSamples(); i++) {
  			
          	
          	  Double[] fs = TrainData.getSample(i); // get features for this 
          	 svm_node[] ex = new  svm_node[fs.length];
                for(int j=0; j<fs.length; j++){
                    ex[j]=new svm_node( );
                    ex[j].index=j;
                    ex[j].value=fs[j];
                   
                }
                
                int labelp=getByValue(_labelToType, TrainData.getStringTarget(i));
                list.add(new Example( labelp,ex));
          	
  		}
          
           
          
         prob = new    svm_problem();        
          int num = list.size();
          prob.l = num;
          double[] y = new double[num];
         svm_node[][] x = new svm_node[num][];
          for(int i=0; i<num; i++){
              Example ex=(Example)list.get(i);
              y[i]=ex.label;
              x[i]=ex.nodes;
          }
          prob.y=y;
          prob.x=x;
      

          return prob;
      }
	@Override
	public void CrossValidate() {
		
		 DataResults=new ResultSetAcc();
		prob= setDataProblemForTraining();
		 
		 if(_svmParam==null){
	            _svmParam = defaultSVMParam();
	        }
		
		  double[] result=new  double[prob.l];
		     // now i will runt the grid 
		     // for each  loop value on  c  from 10 till 1000  with increment of 100 
		     //Figure 2: Loose grid search on C = 2−5 , 2−3 , . . . , 215 
		     //and γ = 2−15 , 2−13 , . . . , 23 .
		     // now test wich values is maximum and 
		      /// 
		     //logger.error( svmParam);
		     
		     double accuracy=0;
		 //    logger.info("  svm param    "+svmParam.C+"   g= "+svmParam.gamma );
		     
		svm.svm_cross_validation( prob,  _svmParam ,10,result);//.svm_train(prob,_svmParam);
		     
		     double correct=0,incorrect=0;
		     for (int i = 0; i < result.length; i++) {
		    	 DataResults.addResult( (int)result[i],(int)prob.y[i]);
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
		     Result.setDataResults(DataResults);
			 Result.setNumberOfIncorrect(correct);
			 Result.setPercentCorrect(accuracy);
			 Result.setNumberOfTestSamples(prob.y.length);

			 Result.setPercentError((double)incorrect/(double)prob.y.length);
		     
		     

	}

	@Override
	public void train() {

		prob= setDataProblemForTraining();
		 
		 if(_svmParam==null){
	            _svmParam = defaultSVMParam();
	        }
	        _svmModel = svm.svm_train(prob,_svmParam);
	}
	 private Classification classifySingle(double[] fs) {
//       if(_normalizeScale){
//           fs = scale(fs);
//       }
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
	@Override
	public String getInstanceClass(double[] fs) {
      if(_svmParam.svm_type == svm_parameter.ONE_CLASS){
      logger.info("ONE_CLASS");
      return classifySingle(fs).getHighestConfidenceType();
  }
  else{
      if(_normalizeScale){
          fs = scale(fs);
      }
      svm_node[] ex = new svm_node[fs.length];
      for(int j=0; j<fs.length; j++){
          ex[j]=new svm_node();
          ex[j].index=j;
          ex[j].value=fs[j];
      }
      int label = (int)svm.svm_predict(_svmModel, ex);
     // logger.info("  the label is "+label);
  //    logger.info(" _ now has table has "+"    for label "+label+"   and "+_labelToType);
      String type =(String)_labelToType.get(new Integer(label));
      String[] types = {type};
      double[] values = {1.0};
      return type+"";
  }
//      return new Classification(types,values);
//      /*
//      svm.SVMResult result = svm.svm_predict_hh(_svmModel, ex);
//      int[] labels = result.classPrediction();
//      ArrayList pairs = new ArrayList();
//      for(int i=0; i<labels.length; i++){
//          String type =(String)_labelToType.get(new Integer(labels[i]));
//          double[] decFuncValues = result.values(labels[i]);
//          for(int j=0; j<decFuncValues.length; j++){
//              pairs.add(new Pair(type,decFuncValues[j]));
//          }
//      }
//      String[] types = new String[pairs.size()];
//      double[] values = new double[pairs.size()];
//      int i=0;
//      for(Iterator iter=pairs.iterator(); iter.hasNext();){
//          Pair p = (Pair)iter.next();
//          types[i]=p.type;
//          values[i]=p.value;
//          i++;
//      }
//  
	}

	@Override
	public Classification getInstanceDistribution(double[] fs) {
	     
	        if(_normalizeScale){
	            fs = scale(fs);
	        }
	        svm_node[] ex = new svm_node[fs.length];
	        for(int j=0; j<fs.length; j++){
	            ex[j]=new svm_node();
	            ex[j].index=j;
	            ex[j].value=fs[j];
	        }
	        
	        double[] probab=new double[_labelToType.size()];
	        double t=svm.svm_predict_values(_svmModel , ex, probab);
	 // logger.info("  the value of perdict is "+ t);      
	          String[] types = new String[probab.length];
	          double[] values = new double[probab.length];
	          int i=0;
	  for (int j = 0; j < values.length; j++) {
		          types[j]=(String)_labelToType.get(new Integer(j));
	              values[j]=probab[j];
	}
	          
	       
	          return new Classification(types,values);
	        

	}
	@Override
	public void loadModel(String filename) {
		//svm_model
		try {
			
			 // Deserialize from a file
	        File file = new File(filename+".ser");
	        ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
	        // Deserialize the object
	        LibSVMSingleClassifier libClass= (  LibSVMSingleClassifier) is.readObject();
	        
	        _svmParam= defaultSVMParam();
			logger.info( "labels size is "+ this._labelToType.size());
			logger.info(" normilized  "+this._normalizeScale);
		
			logger.info(this.toString());
			_svmParam=new svm_parameter();
			

			_svmParam.svm_type = is.readInt();
			_svmParam.kernel_type = is.readInt();
			_svmParam.degree = is.readInt();
			_svmParam.gamma =  is.readDouble();
			_svmParam.coef0 =  is.readDouble();
			_svmParam.nu =  is.readDouble();
			_svmParam.cache_size =  is.readDouble();
			_svmParam.C =  is.readDouble();
			_svmParam.eps =  is.readDouble();
			_svmParam.p =  is.readDouble();
			_svmParam.shrinking= is.readInt();
			_svmParam.nr_weight =  is.readInt();
			_svmParam.weight_label =  (int[]) is.readObject();
			_svmParam.weight =  (double[]) is.readObject();
			
			 logger.info(_svmParam);
			  logger.info(" SVMC has code  "+ _svmParam.hashCode());
		        logger.info(" svm type   "+_svmParam.svm_type);
		        logger.info("  svm C  "+_svmParam.C);
	        
	        
	        
	        //defaultSVMParam();
	        
	        this.CopyClassifierData(libClass);
	    	//logger.info( "  minFeatureVals "+this._minFeatureVals.length);
	        logger.info("  this classifier min feature length is "+_minFeatureVals.length);
	        is.close();
	    logger.info("   Lodding the svm model    .........");
			 _svmModel=svm.svm_load_model( filename);
		 logger.info(" Finished   Lodding the svm model    .........");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		 
			e.printStackTrace();
		}
		
	}
	@Override
	public void saveModel(String  filename) {
		 
		try {
			
		       // Serialize to a file
	        ObjectOutput os = new ObjectOutputStream(new FileOutputStream(filename+".ser"));
	        os.writeObject(this);
	        
	    	logger.info( "labels size is "+ this._labelToType.size());
			logger.info(" normilized  "+this._normalizeScale);
			logger.info( "  minFeatureVals "+this._minFeatureVals.length);
			logger.info(this.toString());
			
			//os.defaultWriteObject();
			if (_svmParam==null)
				_svmParam=this.defaultSVMParam();
			// now write the paramter 
			os.writeInt( _svmParam.svm_type);
		    os.writeInt( _svmParam.kernel_type); 
		    os.writeInt(_svmParam.degree);
	        os.writeDouble(_svmParam.gamma );
	        os.writeDouble(_svmParam.coef0 );//;= 0;
	        os.writeDouble(_svmParam.nu);// = 0.5;
	        os.writeDouble(_svmParam.cache_size);// = 40;
	        os.writeDouble(_svmParam.C );//= 100;
	        os.writeDouble(_svmParam.eps );//= 1e-3;
	        os.writeDouble(_svmParam.p );// 0.1;
	        os.writeInt(_svmParam.shrinking); //= 1;
	        os.writeInt(_svmParam.nr_weight);// = 0;
	        os.writeObject( _svmParam.weight_label);
	        os.writeObject( _svmParam.weight );
	        logger.info(" wrtitng the  "+_svmParam);
	        logger.info(" svm type   "+_svmParam.svm_type);
	        logger.info("  svm C  "+_svmParam.C);
	        logger.info(" SVMC has code  "+ _svmParam.hashCode());
	        
	        logger.info("  this is has code "+this.hashCode());
	        
	        os.close();
	        
			svm.svm_save_model(filename, _svmModel);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void trainProbability() {
	
//		prob= setDataProblemForTraining();
//		
//		 
//		 if(_svmParam==null){
//	            _svmParam = defaultSVMParam();
//	        }
//	    //  _svmParam.probability=1;// must do probability... 
//	        _svmModel = svm.svm_train(prob,_svmParam);
		train();
	}

}
