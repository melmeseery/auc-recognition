/**
 * 
 */
package classifiers.results;

import java.util.ArrayList;

/**
 * @author maha
 *
 */
public class ResultSetAccuracyMulitClassifier extends ResultSetAcc {
	ArrayList<Accuracy>  ClassAcc;
	
	// confusion matrix 
	// multi class support.... 
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

public void addResult(double d, double e) {
	//TODO  IMPROTAANT TRY TARGET IS double  >>>>>>>>>
	
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

}
