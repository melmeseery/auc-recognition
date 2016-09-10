package classifiers.results;

import gui.AppDefaults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

 

import org.apache.log4j.Logger;




public class ResultSetAcc {
	private static transient final Logger logger = Logger.getLogger( ResultSetAcc.class);
	public int NumOfSamples=0;
	int NumOfClasses=2;
	protected ArrayList<Double> Perdicts=null;
	protected ArrayList<Integer> Targets=null;
	
	protected ArrayList<Double> Scores;
	protected  ArrayList<Classification> probabilities;
	protected Accuracy  FinalAcc;

protected String DetaliedStringOutput;
 
        
/**
 * @return the detaliedStringOutput
 */
public String getDetaliedStringOutput() {
	return DetaliedStringOutput;
}
/**
 * @param detaliedStringOutput the detaliedStringOutput to set
 */
public void setDetaliedStringOutput(String detaliedStringOutput) {
	DetaliedStringOutput = detaliedStringOutput;
}
private boolean StoreErrors=AppDefaults.StoreErrorImage;
ImageErrors  imageEr=null;
public void addSampleError(int Sample, int perdict, String errorString ){
	if (StoreErrors & imageEr==null)
	{  imageEr=new 	ImageErrors ();
	}
	if (StoreErrors)
	{  imageEr.addSampleError( Sample, perdict, errorString);
	}
}
	public void computeAcc(){
		
		FinalAcc=new Accuracy();
		double p,d;
		FinalAcc.TruePositive=0;
		for (int i = 0; i < Perdicts.size(); i++) {
			p=Perdicts.get(i);
			d=Targets.get(i);
			if (p==d){
				
				FinalAcc.TruePositive++;
			}
			else {
				
				FinalAcc.FalsePositive++;
			}
			
			
			
			
			
		}
		
		
		FinalAcc.computeStat();
	}
	
	public void setTargets(ArrayList<Integer> targets){
		
		// 
		Targets=new ArrayList<Integer>(targets);
		
//		for (int j = 0; j < targets.length; j++) {
//			Targets.add(new Integer(targets[j]));
//		}
		//Targets.add(new Integer(targets[i]));
		
		
	}
	   public Accuracy getFinalAcc() {
			return FinalAcc;
		}

		public void setFinalAcc(Accuracy finalAcc) {
			FinalAcc = finalAcc;
		}
	public ArrayList<Double> getPerdicts() {
		return Perdicts;
	}


	public void setPerdicts(ArrayList<Double> perdicts) {
		Perdicts = perdicts;
	}


	
	public double getPerdictOfSample(int i){
		
		if (Perdicts==null)
			return Double.NaN;
		else {
		//	logger.info(  "  the size of  perdict is "+Perdicts.size() +  "   i  "+i  );
			if (Perdicts.size()>i) 
			return Perdicts.get(i);
			else 
				return Double.NaN;
			}
		//return 
	}
	
	
	
	public void ReadFromTorchFile(String filename){
		   String line;
			try {
				logger.trace("reading the file................ wait");
				File afile = new File(filename);
				Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
			 
				Perdicts=new ArrayList();
				Scores=new ArrayList();
				 Double per;
				 Double score;
	       
	          //input.useDelimiter(" ");
				
			      while (input.hasNext()) {

			           per=new Double(input.nextDouble());
			           score=new Double(input.nextDouble());
			           
			           Perdicts.add(per);
			           Scores.add(score);
			           
			   
			      }
			      NumOfSamples=Perdicts.size();
			      // dispose all the resources after using them.
			      input.close();
		
	} catch (FileNotFoundException e) {
		 
		e.printStackTrace();
	} catch (IOException e) {
	 
		e.printStackTrace();
	} 
			
	}
	
	public void ReadFromLibSVMFile(String filename){
		   String line;
			try {
				logger.info("reading the file................ wait");
				File afile = new File(filename);
				Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
				Perdicts=new ArrayList();
				//Scores=new ArrayList();
				 Double per;
				 Double score;
	          //input.useDelimiter(" ");
			      while (input.hasNext()) {

			           per=new Double(input.nextDouble());
			       //    score=new Double(input.nextDouble());
			           
			           Perdicts.add(per);
			         //  Scores.add(score);
			           
			   
			      }
                NumOfSamples=Perdicts.size();
                
                logger.trace(" Read "+  NumOfSamples+" samples ...");
			      // dispose all the resources after using them.
			      input.close();
		
	} catch (FileNotFoundException e) {
	 
		e.printStackTrace();
	} catch (IOException e) {
	 
		e.printStackTrace();
	} 
			
		
		
	}
	public void addResult(int catInt, String target){
		//TODO  IMPROTAANT TRY TARGET IS STRING >>>>>>>>>
	}
	public void addResult(int catInt, int target) {
		 if(Perdicts==null||Targets==null){
			 Perdicts=new ArrayList<Double>();
			 Targets=new  ArrayList<Integer>();
		 }
		Perdicts.add(new Double(catInt));
		Targets.add(target);
			
		 NumOfSamples=Perdicts.size();
	}
	public void addResult(Classification classification,int target){
		
		 if(Perdicts==null||Targets==null){
			 probabilities=new ArrayList<Classification>();
			 Targets=new  ArrayList<Integer>();
			 Perdicts=new ArrayList<Double>();
			 Scores=new ArrayList<Double>();
		 }
		 probabilities.add(classification);
		 Scores.add( classification.getHighestConfidence());
		Perdicts.add(Double.parseDouble(classification.getHighestConfidenceType()));
		Targets.add(target);
			
		 NumOfSamples=Perdicts.size();
	}

	public int getTarget(int i) {
		return Targets.get(i);
		
	}
	public void addResult(double d, double e) {
	
		//TODO  IMPROTAANT TRY TARGET IS double  >>>>>>>>>
	}
	public void setDataSetLocations(ArrayList<String> locations, ArrayList <Integer> labels, int dbType) {
		
		if (imageEr!=null){
			
			// if not null then process ot 
			 imageEr.setDb(dbType);
			 imageEr.setOrginalLocations(locations, labels);
		}
	}
	public void saveAll(String string) {
		
		if (imageEr!=null){
			imageEr.StoreImages(string);
			//imageEr.StoreSumFile(string);
			
		}
		this.save(string+"classifierStatis.txt");
	}
	public void save(String filen) {
		
		  FileOutputStream file; 
	        PrintStream out; // declare a print stream object
	        try {
	         // Create a new file output stream
	        file = new FileOutputStream(filen);

	                // Connect print stream to the output stream
	               out = new PrintStream(file);

 
	     		out.println( write());
	     			 
	     	  out.close();
	                
	        }
	        catch (Exception e){
	                System.err.println (" Error in writing to file");
	        }
		
	 
		
	}
	public String write( ) {
		String s=this.toString()+		AppDefaults.LineSeperator+			AppDefaults.LineSeperator + DetaliedStringOutput;
	 return s;
		
	}
	
}
