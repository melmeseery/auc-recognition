package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import oldToDelete.LibSVMTest;

import org.apache.log4j.Logger;


public class ResultSetAcc {
	private static transient final Logger logger = Logger.getLogger( ResultSetAcc.class);
	int NumOfSamples=0;
	int NumOfClasses=2;
	ArrayList<Double> Perdicts;
	ArrayList<Integer> Targets;
	

   Accuracy  FinalAcc;


ArrayList<Accuracy>  ClassAcc;
   
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
		Targets=new ArrayList(targets);
		
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

	ArrayList<Double> Scores;
	
	double getPerdictOfSample(int i){
		
		if (Perdicts!=null)
			return Double.NaN;
		else {
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
			
	}
	
	public void ReadFromLibSVMFile(String filename){
		   String line;
			try {
				System.out.println("reading the file................ wait");
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
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
			
		
		
	}
}
