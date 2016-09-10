package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;


import org.apache.log4j.Logger;

public class DataSet extends Observable implements Cloneable {
	private static transient final Logger logger = Logger.getLogger( DataSet.class);

	
	public static final int FILE_INPUT_FORMAT_TORCH=0;
	public static final int FILE_INPUT_FORMAT_CSV=1;
	public static final int FILE_INPUT_FORMAT_LIBSVM=2;
	public static final int FILE_INPUT_FORMAT_ARFF=3;
	
	
	//read and save file from/to torch format. 
	//read and save file from/to  libsvm format.
	//read and save file from/to arff format.
	int NumOfFeatures=0;
	int NumOfSamples=0;
	
	int NumOfClasses=2;
	
	int TrainSamples=0;
	int ValidationSamples=0;
	double data[][];
	double Validation[][];
	
	int dataTargets[];
	int validationTargets[];
	
	ArrayList<String> ClassLabels;
	ArrayList<Integer> ClassValues;
	
	int format=1;  //format to read
	public int getFormat() {
		return format;
	}
	public void setFormat(int format) {
		this.format = format;
	}
	boolean valuesOk;
  	boolean UseAllFeatures=true;
  	boolean FeatureDataSet=false;
  	boolean ValidationCreated=false;
  	boolean shuffled=false;
  	boolean twoClass=false;
  	
  	FeatureSet features=null; 
  	String AllFeatureString="";
  	ArrayList<double []> dataAdd=null;
  	ArrayList<Integer> TargetsAdd=null;

	private boolean binaryClasses;
  	
  
  	
  	public int getNumOfSamples() {
		return NumOfSamples;
	}
	public void addSample(double[] vals, int i) {
		 if (dataAdd==null)
		 {
			 dataAdd=new   	ArrayList<double []>();
			 TargetsAdd=new ArrayList<Integer>();
		 }
		 dataAdd.add( vals);
		 TargetsAdd.add(new Integer(i));
	
	}
	public void ConvertToArrays(){
		if ( dataAdd!=null)
		{
			data=new double [dataAdd.size()][dataAdd.get(0).length];
			dataTargets=new int [TargetsAdd.size()];
			for (int i = 0; i < dataAdd.size(); i++) {
				dataTargets[i]=TargetsAdd.get(i);
				for (int j = 0; j < dataAdd.get(i).length; j++) {
					data[i][j]=dataAdd.get(i)[j];
				}
			}
			this.NumOfSamples=dataAdd.size();
			this.NumOfFeatures=dataAdd.get(0).length;
			this.UseAllFeatures=true;
			
		}
	}
	
	
  public	Double [] getSample(int i){
  		Double[] sample=null;
  		if (UseAllFeatures)
  		{
  		sample=new Double [NumOfFeatures];
  		for (int j = 0; j < sample.length; j++) {
  		sample[j]=data[i][j];
  		}
  			
  		}
  		else{
  			int [] indeces=features.getSelectedFeaturesIndeces();
  			sample=new Double [indeces.length];
  			
  			for (int j = 0; j < indeces.length; j++) {
  				if (indeces[j]<data[i].length){
				sample[j]=new Double (data[i][indeces[j]]);
//				if (sample[j]==null)
//				{
//				     logger.debug(" the indeces  [ "+j+" ] "+indeces[j]);
//				     logger.debug(" data "+data[i].length+"  data "+data[i][indeces[j]]);
//				     
//				}
  				}
			}
  		}
  		return sample;
  		
  	}
 public int getTarget(int i){
	 return dataTargets[i];
	 
 }
 

 
 public FeatureSet getFeatures() {
	return features;
}
public void ShuffleDataSet(){
	
	 
	 double [][] RandomData=new double [data.length][NumOfFeatures];
	 int [] RandomTarget=new int[data.length];
	 //ArrayList<Integer>
	 ArrayList<Integer> indeces=new ArrayList<Integer>(data.length);
		indeces.ensureCapacity(data.length);
	// logger.info("suffling   "+data.length+" data ... ");
	// logger.info( " their is  "+indeces.size());
		for (int i = 0; i < data.length; i++) {
			indeces.add(new Integer(i));
			
		}
		int randomi;//=(int) Math.floor(Math.random()*indeces.size());
		int index;
		int j=0;
		for (int i = 0;   indeces.size()>0 ; i++) {
			//logger.info("suffling index  i "+i+" using the");
//			if (j>NumOfSamples)
//				break;
			
			randomi=(int) Math.floor(Math.random()*indeces.size());
		// now move files from he input to the ouptut array . 
			index=indeces.get(randomi);
			//logger.info("suffling index  i "+i+" using the "+"  with the new index "+index);
			//RandomSet.add( files.get(index));
//			for (int k = 0; k < data[index].length; k++) {
//				RandomData[j][k]=data[index][k];	
//			}
			RandomData[j]=data[index].clone();
			RandomTarget[j]=dataTargets[index];
			j++;
			
			indeces.remove(randomi);
			
		} 
		logger.info("shuffled  "+j+"  samples........");
//		if(indeces.size()>0)
//		{
//			for (int i = 0; i <indeces.size(); i++) {
//				index=indeces.get(i);
//				RandomData[i]=data[index];
//				RandomTarget[i]=dataTargets[index];
//				//RandomSet.add( files.get(index));
//			}
//		}
//		if (logger.isTraceEnabled()){
//			
//			for (int i = 0; i < 100 && i<RandomSet.size(); i++) {
//				logger.trace(" -- "+RandomSet.get(i));
//			}
		 shuffled=true;
			//RandomData[j]=data[index];
//		for ( j = 0; j<NumOfSamples; j++) {
//		 
//			data[j]=RandomData[j];
//			dataTargets[j]=RandomTarget[j];
//		}
		data=RandomData.clone();
		dataTargets=RandomTarget.clone();
			//RandomTarget[j]=dataTargets[index];
	 
	 
  	}
 

	/**
	 */
  	
  	public void CreateValidationSet(){
  		
  		if (!shuffled){
  			logger.trace("  now starting toshuffle the data");
  			ShuffleDataSet();
  			logger.trace("  Finish the shuffling procedure ");
  		//}
  		}
  		
  		ValidationCreated=true;
  		
  		TrainSamples=(int)(0.8*this.NumOfSamples);
  		logger.info("number of training samples "+TrainSamples);
  		ValidationSamples=(int)(0.2*this.NumOfSamples);
  		
  		
  	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		DataSet tempdata=null;
		tempdata=(	DataSet )super.clone();
	//	tempdata.features= (FeatureSet) features.clone();
		//tempSolution.particlePoints=(int [])data.clone();
		//tempSolution.velocities=velocities.clone();
		
		//tempSolution.polygonVertices=(ArrayList<Point2D>)this.polygonVertices.clone();
	 
		return tempdata;  
		
		
		 
	}
	private void initAllFeatures(){
		
	}
	
	public void setFeaturesToUse(int [] indeces){
		features.createFeatureSelectedList(indeces);
	}
	
	public void setFeaturesToUse(String [] featuresNames){
		
		features.createFeatures(featuresNames);
		
	}
	
	public DataSet GenearteClassDataSetTwoClasses( int c1,int c2 ,String [] features  ){
		
		DataSet tempDataSet= GenearteFeatureDataSet( features);
		
		logger.info(" the new tempdata set has features is "+tempDataSet.NumOfFeatures+" number of samples = "+tempDataSet.NumOfSamples);
		return tempDataSet.GenearteClassDataSetTwoClasses(c1,c2);
	}
	
	public DataSet GenearteClassDataSetTwoClasses(int c1,int c2){
		DataSet NewDataSet = null;
		//get the used data
		if (data==null)
			return null;
		else{
			
		
	
			try {
				  NewDataSet=(DataSet) this.clone();
			
			         int newFeatureCount=0;
			         int newSampleCount=0;
			//set the new data 
			
		
			
			ArrayList<Double[]> NewDataList= new ArrayList<Double[]>();
			ArrayList<Integer> targets=new ArrayList<Integer>();
			for (int s = 0; s < NumOfSamples; s++) {
				if (dataTargets[s]==c1||dataTargets[s]==c2){
					
//				    	double[] tempData;//=new Double[indeces.length];
//				    	
//				    	tempData=;
//				    	
//				    	Double[] tempD=new Double[tempData.length];
//				    	
//				    	for (int i = 0; i < tempD.length; i++) {
//				    		tempD[i]=new Double(tempData[i]);
//						}
//				    	
					NewDataList.add(getSample(s));
				
					targets.add(new Integer(getTarget(s) ));
					
				}
				
				
			}
			int [] indeces=features.getSelectedFeaturesIndeces();
			//logger.info("number of selected features = "+indeces.length);
			
			newSampleCount=NewDataList.size();
			newFeatureCount=indeces.length;
			//logger.info(" new number of samples  "+newSampleCount+" new features count is "+newFeatureCount);
  			// sample=new double [indeces.length];
			
			double [][] NewData=new double [newSampleCount][indeces.length];
			
			
			int [] NewTarget=new int [targets.size()];
			
			for (int i = 0; i <  newSampleCount; i++) {
				NewTarget[i]=targets.get(i);	
				for (int j = 0; j < indeces.length; j++) {
			//		logger.info("i = "+i+" j= "+j);
			//		logger.info( "data= "+ NewDataList.get(i)[j] );
					
					NewData[i][j]= NewDataList.get(i)[j];
				}
				
			}//for i number of samples 
			NewDataSet.NumOfClasses=2;
			NewDataSet.NumOfSamples=newSampleCount;
			NewDataSet.NumOfFeatures=newFeatureCount;
			NewDataSet.FeatureDataSet=true;
			NewDataSet.UseAllFeatures=false;
			NewDataSet.twoClass=true;
			NewDataSet.data=NewData;
			NewDataSet.dataTargets=NewTarget;
			if (this.ValidationCreated){
				
				NewDataSet.CreateValidationSet();
			}
			
			
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//else 
		
		return NewDataSet;
	}
	public DataSet  GenearteFeatureDataSet(String[] featStrings)
	{
		setFeaturesToUse (featStrings);
		ArrayList<FeatureStat> testing = features.getSelectedFeatures();
		for (int i = 0; i <  testing.size(); i++) {
			logger.trace(" the feature "+testing.get(i).getFeatureName()+"  index "+testing.get(i).FeatureIndex);
		}
		
		
		return GenearteFeatureDataSet();
		
		
	}
	
	public DataSet GenearteFeatureDataSet(){
		DataSet NewDataSet = null;
		//get the used data
		if (data==null)
			return null;
		else{
			
		
	
			try {
		 
				  NewDataSet=(DataSet) this.clone();
			
			
			//set the new data 
			
			int [] indeces=features.getSelectedFeaturesIndeces();
			
			
  			// sample=new double [indeces.length];
			double [][] NewData=new double [this.NumOfSamples][indeces.length];
			int [] NewTarget=new int [this.NumOfSamples];
			
			for (int i = 0; i < NewData.length; i++) {
				NewTarget[i]=dataTargets[i];	
				for (int j = 0; j < NewData[i].length; j++) {
					if (indeces[j]<data[i].length){
						NewData[i][j]=data[i][indeces[j]];
		  		}//check on indeces. 
				}//for number of features 
			}//for i number of samples 
			
			
			NewDataSet.NumOfFeatures= indeces.length;
			NewDataSet.NumOfSamples=this.NumOfSamples;
			NewDataSet.FeatureDataSet=true;
			
			////set all the features set 
			
			FeatureSet feat=new FeatureSet();
			
			ArrayList<FeatureStat> old =this.features.getSelectedFeatures();
			
			ArrayList<FeatureStat> temp =   new ArrayList<FeatureStat>() ;
			
			FeatureStat te=new FeatureStat();
			
			for (int i = 0; i < old.size(); i++) {
			
				
					logger.trace(" the feature "+old.get(i).getFeatureName()+"  index "+old.get(i).FeatureIndex+"  now will have the following index "+i);
					te=new FeatureStat();
					te.setFeatureName(old.get(i).getFeatureName());
					te.setFeatureIndex(i);
					temp.add(te);
					
					
			}
			
			feat.setAllFeaturesRead(temp);
			
			NewDataSet.features=feat;
			NewDataSet.UseAllFeatures=true;
			
			NewDataSet.data=NewData.clone();
			
			NewDataSet.dataTargets=NewTarget.clone();
			if (this.ValidationCreated){
				
				NewDataSet.CreateValidationSet();
			}
			
			
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//else 
		
		return NewDataSet;
	}
	public void ReadFromFile(String filename){
		logger.info(" Finished reading the file ..."+filename);
		if (format==FILE_INPUT_FORMAT_TORCH){
			ReadFromTorchFile(filename);
			
			
			
		}
		
		if (format==FILE_INPUT_FORMAT_LIBSVM){
			ReadFromLibSVMFile(filename);
			
			
		}
		logger.info(" reading the feature file ");
		initAllFeatures(filename);
		fixClassArray();
		
		
	}
	private void fixClassArray(){
		
		 ClassLabels=new ArrayList<String>();
		 ClassValues=new ArrayList<Integer>();
		 Integer targ=new Integer(0);
		 boolean found=false;
		for (int i = 0; i < dataTargets.length; i++) {
			targ=dataTargets[i];
			found=false;
			for (int j = 0; j < ClassValues.size(); j++) {
				if ( ClassValues.get(j).equals(  targ))
				{
					found=true;
					break;
				}
			}
			
			if (!found){//not found in current classs values
				
				ClassValues.add(targ);
				ClassLabels.add("+targ");
			}
			
		}//for targets
		
		this.NumOfClasses=ClassValues.size();
		logger.trace(" There are "+this.NumOfClasses+" classes in the data set ");
	}
	
private void fixClassBinaryArray(int i, int j) {
		 
	 ClassLabels=new ArrayList<String>();
	 ClassValues=new ArrayList<Integer>();
	 
	 
	 ClassLabels.add(""+i);
	 ClassValues.add(1);
	 ClassLabels.add(""+j);
	 ClassValues.add(-1);
	 
	 
	 this.NumOfClasses=ClassValues.size();
	 
	 
	 
	}
	
	public void initAllFeatures(String filename) {
		// TODO Auto-generated method stub
		filename= filename.replace(".txt", "_Feat.txt");
	     String line;
			try {
				 File afile ;
					Scanner input;
				System.out.println("reading the file feature ................ wait");
				try{
				 afile = new File(filename);
				 input =  new Scanner(new BufferedReader(new FileReader(afile)));
				}catch (FileNotFoundException e) {
					
					System.out.println("file not found will try using the default file ");
					afile=new File("feat.txt");
					 input =  new Scanner(new BufferedReader(new FileReader(afile)));
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
				
				
				
			
				features=new FeatureSet();
				ArrayList<FeatureStat> FeatureStatList=new ArrayList<FeatureStat>(); 
				  FeatureStat feat;
				  String tempF;
				  int    indexF;
				  AllFeatureString="";
			      while (input.hasNext()) {
			    	  tempF=input.nextLine();
			    	  indexF=Integer.parseInt(input.nextLine());
			    	  
			    	  feat=new FeatureStat();
			    	  feat.setFeatureIndex(indexF);
			    	  feat.setFeatureName(tempF);
			    	  AllFeatureString+=tempF;
			    	  AllFeatureString+=",";
			    	 logger.info(" adding feature "+tempF+" in the index "+indexF);
					 FeatureStatList.add(feat);
			      }
			      UseAllFeatures=true;
//			      if (FeatureStatList.size()==2){
//			    	  this.twoClass=true;
//			      }
			      if (FeatureStatList.size()!=this.NumOfFeatures){
			    	  
			    	  UseAllFeatures=false;
			    	 logger.error("Error the number of featurs in data file not equal to that in the feature file");
			    	 logger.error(" Reading  ... "+NumOfFeatures+"  feature from the data file and "+FeatureStatList.size()+" feature from the feautre file");
			      }
			      
			  	features.setAllFeaturesRead(FeatureStatList);
			      
			 logger.info("finihsed reading the feature file..........");
			}
			catch (FileNotFoundException e) {
			 	  UseAllFeatures=true;
				System.out.println(" the default file is not found... ");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	}

	public String getAllFeatureString() {
		return AllFeatureString;
	}
	public void setAllFeatureString(String allFeatureString) {
		AllFeatureString = allFeatureString;
	}
	public void SaveToFile(String filename){
		logger.trace(" Saving file "+filename);
		if (format==FILE_INPUT_FORMAT_TORCH){
		if (this.ValidationCreated){
			
			  String trainFile=filename.replace(".txt", "_train.txt");
		  SaveToTorchFileTrainSet(trainFile );
		  
		  String ValidationFile=filename.replace(".txt", "_validation.txt");
		  SaveToTorchFileValidationSet(ValidationFile);
			
		}
		else { 
			this.SaveToTorchFile(filename);
		}
		}
		else if (format==FILE_INPUT_FORMAT_LIBSVM){
			
			if (this.ValidationCreated){
				
				String trainFile=filename.replace(".txt", "_train.txt");
				SaveToLibSVMFileTrain(trainFile );
			  
			  String ValidationFile=filename.replace(".txt", "_validation.txt");
			  SaveToLibSVMFileValidation(ValidationFile);
				
			}
			else { 
				this.SaveToLibSVMFile(filename);
			}
			
		}
		else if (format==FILE_INPUT_FORMAT_ARFF){
			if (this.ValidationCreated){
				
				String trainFile=filename.replace(".txt", "_train.txt");
				SaveToARFFFileTrain(trainFile );
			  
			  String ValidationFile=filename.replace(".txt", "_validation.txt");
			  SaveToARFFFileValidation(ValidationFile);
				
			}
			else { 
				SaveToARFFFile(filename);
			}	
		}
		
	}
	void ReadFromTorchFile(String filename){
	      String line;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename);
			Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
		 
	
          int linecount=0;
          int readcount=0;
          String[] datastrings;
          int j=0;
          //input.useDelimiter(" ");
			
		      while (input.hasNext()) {

		          
		    //	 datastrings = line.split(" ");
		    	
		    	   if ( readcount==0)	{
		    		   this.NumOfSamples=input.nextInt();
		    		   readcount++;
		    		   NumOfFeatures=input.nextInt()-1;
		    		   readcount++;
		    		   data=new double[NumOfSamples][NumOfFeatures];
		    		   dataTargets=new int[NumOfSamples];
		    		  // System.out.println(" the first time read = "+NumOfSamples + "  with "+NumOfFeatures+" features ");
		    		   j=0;
		    		   
		    	   }
		    	   else{
		    		   //System.out.println();
		    		  // System.out.print(" sample "+j);
		    		   for (int i = 0; i < NumOfFeatures && input.hasNext(); i++) {
						
		    			   data[j][i]=input.nextDouble();
		    			   readcount++;
		    			    //System.out.print("  "+data[j][i]);
					}
		    		   
		    		   if (input.hasNext()){
		    			   dataTargets[j]=input.nextInt(); 
		    			   readcount++;
		    			  //System.out.print("  target is =  "+  dataTargets[j]);
		    		   }
		    		   
		    		   j++;
		    		   
		    	   }
		    	   
		    	   if (j>NumOfSamples){
		    		   break;
		    	   }
		    	   linecount++;
		    	   
		    	   if (linecount%3000==0)
		    		   logger.trace(" reading line n "+linecount);
		    	   
		      // this statement reads the line from the file and print it to
		        // the console.
		        //System.out.println(line);
		      }

		      // dispose all the resources after using them.
		      input.close();
		      
		     
		      
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	void SaveToTorchFile(String filename){
		
		
		  FileOutputStream file; 
          PrintStream out; // declare a print stream object
          try {
           // Create a new file output stream
          file = new FileOutputStream(filename);

                  // Connect print stream to the output stream
                 out = new PrintStream(file);

                  out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
                  
                  for (int i = 0; i < this.NumOfSamples; i++) {
                	  
                	  for(int j=0; j< this.NumOfFeatures ;j++){
                		  out.print( data[i][j]);
                		  out.print("  ");
                	  }
                	  
                	  out.println(dataTargets[i]);
                	  
                	  if (i%500==0){
                		  
                		  out.flush();
                		  logger.trace(" writing samples n "+i);
                	  }
					
				}
      
                  
          }
          catch (Exception e){
                  System.err.println ("Error in writing to file");
          }
		
		    
	}
	
	void SaveToTorchFileTrainSet(String filename){
		
		
		  FileOutputStream file; 
        PrintStream out; // declare a print stream object
        try {
         // Create a new file output stream
        file = new FileOutputStream(filename);

                // Connect print stream to the output stream
               out = new PrintStream(file);

                out.println ( this.TrainSamples+" "+ (this.NumOfFeatures+1));
                
                for (int i = 0; i < this.TrainSamples; i++) {
              	  
              	  for(int j=0; j< this.NumOfFeatures ;j++){
              		  out.print( data[i][j]);
              		  out.print("  ");
              	  }
              	  
              	  out.println(dataTargets[i]);
              	  
              	  if (i%300==0){
              		  
              		  out.flush();
              		  System.out.println(" writing samples number "+i);
              	  }
					
				}
    
                
        }
        catch (Exception e){
                System.err.println ("Error in writing to file");
        }
		
		    
	}
	void SaveToTorchFileValidationSet(String filename){
		
		
		  FileOutputStream file; 
      PrintStream out; // declare a print stream object
      try {
       // Create a new file output stream
      file = new FileOutputStream(filename);

              // Connect print stream to the output stream
             out = new PrintStream(file);

              out.println (  (this.NumOfSamples-this.TrainSamples) +" "+ (this.NumOfFeatures+1));
              
              for (int i =  this.TrainSamples; i < this.NumOfSamples; i++) {
            	  
            	  for(int j=0; j< this.NumOfFeatures ;j++){
            		  out.print( data[i][j]);
            		  out.print("  ");
            	  }
            	  
            	  out.println(dataTargets[i]);
            	  
            	  if (i%300==0){
            		  
            		  out.flush();
            		  System.out.println(" writing samples number "+i);
            	  }
					
				}
  
              
      }
      catch (Exception e){
              System.err.println ("Error in writing to file");
      }
		
		    
	}
	
	
	
	void ReadFromLibSVMFile(String filename){
	     String line;
			try {
				logger.info("reading the file................ wait");
				File afile = new File(filename);
				Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
			 
		
	          int linecount=0;
	          int readcount=0;
	          String[] datastrings;
	          int j=0;
	          
	          int noSamples=0;
	          int noFeat=0;
	          
	          
	          ArrayList<ArrayList<Double>> dataArray=new ArrayList<ArrayList<Double>> ();
	          ArrayList<Integer> targets=new    ArrayList<Integer>();
	          //input.useDelimiter(" ");
				String line1,lin;
				int tar=0;
				int maxFeatures=0;
			      while (input.hasNext()) {
			    	  
			    	  tar=input.nextInt();
			    	  targets.add(new Integer(tar)); //adding targes now add the features
			    	  lin=input.nextLine();
			    	  String[] featLinArr=lin.split(" ");
//first read the first line to get the maximum features and the  
			    	  ArrayList<Double> feat=new ArrayList<Double>();
                             
			    		   for (int i = 0; i <featLinArr.length; i++) {
							
			    			  String[] tm=featLinArr[i].trim().split(":");
			    			  if (tm.length==2){
			    				  int index=Integer.parseInt(tm[0]);
			    				  double f=Double.parseDouble(tm[1]);
			    				  
			    				  if (index>=i){
			    					 // feat.set(index,f);
			    					  for (int k = i; k <=index; k++) {
			    						  if (i==index)
			    							  feat.add(f);
			    						  else 
			    							  feat.add(0.0);
									}//from i to index. 
			    					//  feat.set(index,f);
			    				  }///if index larget then i from 14 to 16 fill features with 0
			    				  
			    			  }
			    			  
			    			    //System.out.print("  "+data[j][i]);
						}///for the features array 
			    		   if (featLinArr.length>maxFeatures)
			    		   {
			    			   maxFeatures=featLinArr.length;
			    		   }
			    		   
			    		 
			    		   dataArray.add(feat);
			    	   
			    	 
			    		   linecount++;
			    	   if (linecount%3000==0)
			    		   logger.trace(" reading line n "+linecount);
			    	   
			      // this statement reads the line from the file and print it to
			        // the console.
			        //System.out.println(line);
			      }
			      
			      //after all data i sread...
			      data=new double[ dataArray.size()][maxFeatures];
			      dataTargets=new int[ dataArray.size()];
			      for (int i = 0; i <  dataArray.size(); i++) {
					dataTargets[i]=targets.get(i);
					
					for (int k = 0; k < maxFeatures ; k++) {
						if (k<dataArray.get(i).size()){
							data[i][k]= dataArray.get(i).get(k);
						}else 
						{
							data[i][k]= 0.0;
						}
					}
					
					
					this.NumOfFeatures=maxFeatures;
					this.NumOfSamples=data.length;
				}
			      

			      // dispose all the resources after using them.
			      input.close();
			      
			     
			      
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		
	}
	void SaveToLibSVMFile(String filename){
		  FileOutputStream file; 
          PrintStream out; // declare a print stream object
          try {
           // Create a new file output stream
          file = new FileOutputStream(filename);

                  // Connect print stream to the output stream
                 out = new PrintStream(file);

                 // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
                 double dataw;
                  for (int i = 0; i < this.NumOfSamples; i++) {
                	  out.print(dataTargets[i]);
                	  out.print("  ");
                	  for(int j=0; j< this.NumOfFeatures ;j++){
                 		  if (data[i][j]!=0){
                  			  if ( !Double.isNaN( data[i][j])  )
                  			  {
                  			
                  		  out.print(j);
                  		  out.print(":");
                  		  out.print(data[i][j]);
                  		  out.print("  ");
                  			  }
                	  }
                	  }
                	  out.println("");
                	  
                	  if (i%500==0){
                		  
                		  out.flush();
                		  logger.trace(" writing samples number "+i);
                	  }
					
				}
      
                  
          }
          catch (Exception e){
                  System.err.println ("Error in writing to file");
          }
		
		    
	}
	void SaveToLibSVMFileTrain(String filename){
		  FileOutputStream file; 
        PrintStream out; // declare a print stream object
        try {
         // Create a new file output stream
        file = new FileOutputStream(filename);

                // Connect print stream to the output stream
               out = new PrintStream(file);

               // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
                double dataw; 
                for (int i = 0; i < this.TrainSamples; i++) {
              	  out.print(dataTargets[i]);
              	  out.print("  ");
              	  for(int j=0; j< this.NumOfFeatures ;j++){
             		  if (data[i][j]!=0){
             			  if ( !Double.isNaN( data[i][j])  )
              			  {
              			
              		  out.print(j);
              		  out.print(":");
              		  out.print(data[i][j]);
              		  out.print("  ");
              			  }
              	  }
              	  }
              	  out.println("");
              	  
              	  if (i%500==0){
              		  
              		  out.flush();
              		  System.out.println(" writing samples number "+i);
              	  }
					
				}
    
                
        }
        catch (Exception e){
                System.err.println ("Error in writing to file");
        }
		
		    
	}
	void SaveToLibSVMFileValidation(String filename){
		  FileOutputStream file; 
        PrintStream out; // declare a print stream object
        try {
         // Create a new file output stream
        file = new FileOutputStream(filename);

                // Connect print stream to the output stream
               out = new PrintStream(file);

               // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
                double dataw;
                for (int i = this.TrainSamples; i < this.NumOfSamples; i++) {
              	  out.print(dataTargets[i]);
              	  out.print("  ");
              	  for(int j=0; j< this.NumOfFeatures ;j++){
              		  if (data[i][j]!=0){
              			  if ( !Double.isNaN( data[i][j])  )
              			  {
              			
              		  out.print(j);
              		  out.print(":");
              		  out.print(data[i][j]);
              		  out.print("  ");
              			  }
              		  }
              	  }
              	  
              	  out.println("");
              	  
              	  if (i%500==0){
              		  
              		  out.flush();
              		  logger.trace(" writing samples number "+i);
              	  }
					
				}
    
                
        }
        catch (Exception e){
                System.err.println ("Error in writing to file");
        }
		
		    
	}
	void ReadFromARFFFile(String filename){
		
//		 @attribute   'lxre6'  numeric  
//		 @attribute   'lxrb'  numeric  
//		 @attribute 'class'   {0,1,2,3,4,5,6,7,8,9}
	     String line;
			try {
				logger.info("reading the file................ wait");
				File afile = new File(filename);
				Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
			 
		
	          int linecount=0;
	          int readcount=0;
	          String[] datastrings;
	          int j=0;
	          
	          int noSamples=0;
	          int noFeat=0;
	          
	          
	          ArrayList<double[]> dataArray=new ArrayList<double[]> ();
	          ArrayList<Integer> targets=new    ArrayList<Integer>();
	          //input.useDelimiter(" ");
				String line1,lin;
				int tar=0;
				int maxFeatures=0;
			      while (input.hasNext()) {
			    	  
			    	  tar=input.nextInt();
			    	  targets.add(new Integer(tar)); //adding targes now add the features
			    	  lin=input.nextLine();
			    	  String[] featLinArr=lin.split(" ");
//first read the first line to get the maximum features and the  
			    	  ArrayList<Double> feat=new ArrayList<Double>();
                          
			    		   for (int i = 0; i <featLinArr.length; i++) {
							
			    			  String[] tm=featLinArr[i].trim().split(":");
			    			  if (tm.length==2){
			    				  int index=Integer.parseInt(tm[0]);
			    				  Double f=Double.parseDouble(tm[1]);
			    				  
			    				  if (index>=i){
			    					  feat.set(index,f);
			    					  for (int k = i; k <=index; k++) {
			    						  if (i==index)
			    							  feat.add(f);
			    						  else 
			    							  feat.add(new Double(0.0));
									}//from i to index. 
			    					  feat.set(index,f);
			    				  }///if index larget then i from 14 to 16 fill features with 0
			    				  
			    			  }
			    			  
			    			    //System.out.print("  "+data[j][i]);
						}///for the features array 
			    		   if (featLinArr.length>maxFeatures)
			    		   {
			    			   maxFeatures=featLinArr.length;
			    		   }
			    		   
			    		 
			    		  // dataArray.add(feat);
			    	   
			    	 
			    		   linecount++;
			    	   if (linecount%3000==0)
			    		   logger.trace(" reading line n "+linecount);
			    	   
			      // this statement reads the line from the file and print it to
			        // the console.
			        //System.out.println(line);
			      }
//			      
//			      //after all data i sread...
//			      data=new double[ dataArray.size()][maxFeatures];
//			      dataTargets=new int[ dataArray.size()];
//			      for (int i = 0; i <  dataArray.size(); i++) {
//					dataTargets[i]=targets.get(i);
//					
//					for (int k = 0; k < maxFeatures ; k++) {
//						if (k<dataArray.get(i).size()){
//							data[i][k]= dataArray.get(i).get(k);
//						}else 
//						{
//							data[i][k]= 0.0;
//						}
//					}
//					
//					
//					
//				}
			      

			      // dispose all the resources after using them.
			      input.close();
			      
			     
			      
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		
		
	}
	void SaveToARFFFile(String filename){
		/*******
		 * 
		 * 
		 * @RELATION  DigitsClassifier

@attribute 'Class' numeric  
@attribute '1' numeric  
@attribute '2' numeric  
@attribute '3' numeric  
@attribute '4' numeric  
@attribute '5' numeric  
@attribute '6' numeric  
@attribute '7' numeric  
@attribute '8' numeric  
@attribute '9' numeric  
@attribute '10' numeric  

@data
1   , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6
1   , 80 , 0 , 1 , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667
1   , 129 , 0 , 1 , 1 , 0 , 6 , 0 , 0 , 7.33333 , 14.666
		 * 
		 * ****/
		logger.debug("  In the same file add ng th arff extentions.... ");
		  FileOutputStream file; 
          PrintStream out; // declare a print stream object
          try {
           // Create a new file output stream
          file = new FileOutputStream(filename+".arff");

                  // Connect print stream to the output stream
                 out = new PrintStream(file);
                  out.println(" @RELATION  DigitsClassifier " );
                  out.println("");
                
                  ArrayList<FeatureStat> featemp=  features.getAllFeaturesRead();
                  
                  for (int i = 0; i < featemp.size(); i++) {
					
                	   out.println(" @attribute   '"+featemp.get(i).FeatureName+"'  numeric  ");
                	   
                	  
				}
                  
                  String  Classes="";
                  Classes+=" {" ;
                  for (int i = 0; i < ClassValues.size(); i++) {
                	
                	  Classes+=ClassValues.get(i);
                	  if ((i+1)< ClassValues.size()){
                		  Classes+="," ;
                		  
                	  }
                	  else {
                		  Classes+="}" ;
                	  }
                	
				}
                  if ( Classes.equals("")){
                	  
                	  Classes=" numeric ";
                  }
                  
                  
                  out.println(" @attribute 'class'  "+Classes);
                  
                  
                  
                  out.println("");
                  out.println("@data");
                 // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
                  double dataw;
                  for (int i = 0; i < this.NumOfSamples; i++) {
                	 
                	  //out.print("  ");
                	  for(int j=0; j< this.NumOfFeatures ;j++){
             
                	
                		  if ( Double.isNaN( data[i][j])  )
              			  {
              				 dataw= 0.0;
              			  }
              			  else 
              				  dataw= data[i][j];
                		  out.print(   dataw);
                	
                		//  }
                	
                		  out.print(",");
                		  out.print("  ");
                	  }
                	 
                	  out.print(dataTargets[i]);
                	  out.println("");
                	  
                	  if (i%700==0){
                		  
                		  out.flush();
                		  System.out.println(" writing samples number "+i);
                	  }
					
				}
      
                  
          }
          catch (Exception e){
                  System.err.println ("Error in writing to file");
                  logger.error(e.getMessage(),e.getCause());
          }
		
		    
	}
	void SaveToARFFFileTrain(String filename){
		/*******
		 * 
		 * 
		 * @RELATION  DigitsClassifier

@attribute 'Class' numeric  
@attribute '1' numeric  
@attribute '2' numeric  
@attribute '3' numeric  
@attribute '4' numeric  
@attribute '5' numeric  
@attribute '6' numeric  
@attribute '7' numeric  
@attribute '8' numeric  
@attribute '9' numeric  
@attribute '10' numeric  

@data
1   , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6
1   , 80 , 0 , 1 , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667
1   , 129 , 0 , 1 , 1 , 0 , 6 , 0 , 0 , 7.33333 , 14.666
		 * 
		 * ****/
		
		  FileOutputStream file; 
          PrintStream out; // declare a print stream object
          try {
           // Create a new file output stream
          file = new FileOutputStream(filename+".arff");

                  // Connect print stream to the output stream
                 out = new PrintStream(file);
                  out.println(" @RELATION  DigitsClassifier " );
                  out.println("");
                //  out.println(" @attribute 'Class' numeric  ");
                  ArrayList<FeatureStat> featemp=  features.getAllFeaturesRead();
                  
                  for (int i = 0; i < featemp.size(); i++) {
					
                	   out.println(" @attribute   '"+featemp.get(i).FeatureName+"'  numeric  ");
                	   
                	  
				}
                  String  Classes="";
                  Classes+=" {" ;
                  for (int i = 0; i < ClassValues.size(); i++) {
                	
                	  Classes+=ClassValues.get(i);
                	  if ((i+1)< ClassValues.size()){
                		  Classes+="," ;
                		  
                	  }
                	  else {
                		  Classes+="}" ;
                	  }
                	
				}
                  if ( Classes.equals("")){
                	  
                	  Classes=" numeric ";
                  }
                  
                  
                  out.println(" @attribute 'class'  "+Classes);
                  out.println("");
                  out.println("@data");
         
                  double dataw;
                  for (int i = 0; i < this.TrainSamples; i++) {
           
                    	  for(int j=0; j< this.NumOfFeatures ;j++){
                 
                    	
                    		  if ( Double.isNaN( data[i][j])  )
                  			  {
                  				 dataw= 0.0;
                  			  }
                  			  else 
                  				  dataw= data[i][j];
                    		  out.print(   dataw);
                    	
                    		//  }
                    	
                    		  out.print(",");
                    		  out.print("  ");
                    	  }
                    	 
                    	  out.print(dataTargets[i]);
                    	  out.println("");
                	  
                	  if (i%500==0){
                		  
                		  out.flush();
                		  System.out.println(" writing samples number "+i);
                	  }
					
				}
      
                  
          }
          catch (Exception e){
                  System.err.println ("Error in writing to file");
          }
		
		    
	}
	void SaveToARFFFileValidation(String filename){
		/*******
		 * 
		 * 
		 * @RELATION  DigitsClassifier

@attribute 'Class' numeric  
@attribute '1' numeric  
@attribute '2' numeric  
@attribute '3' numeric  
@attribute '4' numeric  
@attribute '5' numeric  
@attribute '6' numeric  
@attribute '7' numeric  
@attribute '8' numeric  
@attribute '9' numeric  
@attribute '10' numeric  

@data
1   , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6
1   , 80 , 0 , 1 , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667
1   , 129 , 0 , 1 , 1 , 0 , 6 , 0 , 0 , 7.33333 , 14.666
		 * 
		 * ****/
		
		  FileOutputStream file; 
          PrintStream out; // declare a print stream object
          try {
           // Create a new file output stream
          file = new FileOutputStream(filename+".arff");

                  // Connect print stream to the output stream
                 out = new PrintStream(file);
                  out.println(" @RELATION  DigitsClassifier " );
                  out.println("");
                //  out.println(" @attribute 'Class' numeric  ");
                  ArrayList<FeatureStat> featemp=  features.getAllFeaturesRead();
                  
                  for (int i = 0; i < featemp.size(); i++) {
					
                	   out.println(" @attribute   '"+featemp.get(i).FeatureName+"'  numeric  ");
                	   
                	  
				}
                  String  Classes="";
                  Classes+=" {" ;
                  for (int i = 0; i < ClassValues.size(); i++) {
                	
                	  Classes+=ClassValues.get(i);
                	  if ((i+1)< ClassValues.size()){
                		  Classes+="," ;
                		  
                	  }
                	  else {
                		  Classes+="}" ;
                	  }
                	
				}
                  if ( Classes.equals("")){
                	  
                	  Classes=" numeric ";
                  }
                  
                  
                  out.println(" @attribute 'class'  "+Classes);
                  out.println("");
                  out.println("@data");
                 // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
            	  double dataw;
                  for (int i = this.TrainSamples; i < this.NumOfSamples; i++) {
                    	 
                	  //out.print("  ");
                	  for(int j=0; j< this.NumOfFeatures ;j++){
             
                	
                		  if ( Double.isNaN( data[i][j])  )
              			  {
              				 dataw= 0.0;
              			  }
              			  else 
              				  dataw= data[i][j];
                		  out.print(   dataw);
                	
                		//  }
                	
                		  out.print(",");
                		  out.print("  ");
                	  }
                	 
                	  out.print(dataTargets[i]);
                	  out.println("");
                	  
                	  if (i%500==0){
                		  
                		  out.flush();
                		  System.out.println(" writing samples number "+i);
                	  }
					
				}
      
                  
          }
          catch (Exception e){
                  System.err.println ("Error in writing to file");
          }
		
		    
	}
	public ArrayList<Integer> getValidationsTargets() {
		ArrayList<Integer> tar=new ArrayList<Integer>();
		
		for (int i = TrainSamples; i < this.NumOfSamples; i++) {
			tar.add(new Integer(dataTargets[i]));
		}
		return tar;
	}
	
	public ArrayList<Integer> getAllTargets(){
		
		ArrayList<Integer> tar=new ArrayList<Integer>();
		
		for (int i = 0; i < this.NumOfSamples; i++) {
			tar.add(new Integer(dataTargets[i]));
		}
		return tar;
	}
	public void GenearteBinaryTargest(int i, int j) {
	 
          
		// TODO Auto-generated method stub
		 for (int j2 = 0; j2 < this.NumOfSamples; j2++) {
			//for each target 
			 if (this.dataTargets[j2]==i)
			 {
				 dataTargets[j2]=1;
			 }
			 else {
				 dataTargets[j2]=-1;
			 }
		}
		 fixClassBinaryArray(i,j);
	}
	
	public DataSet CreateOVADataset(int i) {
		this.UseAllFeatures=true;
		DataSet temp=GenearteFeatureDataSet();
		temp.SetOVATargets(i);
		return temp;
	}
	public void SetOVATargets(int i){
		
		for (int j = 0; j < dataTargets.length; j++) {
			if (dataTargets[j]==i)
			{
				
				dataTargets[j]=1;
			}else 
			{
				dataTargets[j]=-1;
			}
		}
		
	}
	public int getFeatureCount() {
		if(UseAllFeatures)
			return this.NumOfFeatures;
			else 
				return this.getSample(0).length;
	//	return 0;
	}
	public void clear() {
		 //cear all data....................
		
		
	}
	public String getFormatString(int format) {
	      if (format==this.FILE_INPUT_FORMAT_TORCH)
	    	  return "Torch";
	      if (format==FILE_INPUT_FORMAT_LIBSVM)
	    	  return "LibSVM";
	      if (format==FILE_INPUT_FORMAT_ARFF)
	    	  return "Arff";
	      if (format==FILE_INPUT_FORMAT_CSV)
	    	  return "CSV";
		return "";
	}

	
	
 
}
