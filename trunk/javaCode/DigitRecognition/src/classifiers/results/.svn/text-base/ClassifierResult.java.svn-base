/**
 * 
 */
package classifiers.results;

import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * @author TOSHIBA
 *
 */
public class ClassifierResult implements Cloneable {

	double percentCorrect;
	double numberOfTestSamples;
	double numberOfIncorrect;
	double percentError;
	String ClassifierString;
	
	double FirstDigitTp;
	String FirstDigitTpLabel;
	double SecondDigitTp;
	String SecondDigitTpLabel;
	double ThirdDigitTp;
	String ThirdDigitTpLabel;

	
String FeatString="";

protected ResultSetAcc DataResults;  
	/**
 * @return the dataResults
 */
public ResultSetAcc getDataResults() {
	return DataResults;
}


/**
 * @param dataResults the dataResults to set
 */
public void setDataResults(ResultSetAcc dataResults) {
	DataResults = dataResults;
}


	/**
	 * 
	 */
	public ClassifierResult() {
		
	}


	public double getPercentCorrect() {
		return percentCorrect;
	}


	public void setPercentCorrect(double percentCorrect) {
		this.percentCorrect = percentCorrect;
	}


	public double getNumberOfTestSamples() {
		return numberOfTestSamples;
	}


	public void setNumberOfTestSamples(double numberOfTestSamples) {
		this.numberOfTestSamples = numberOfTestSamples;
	}


	public double getNumberOfIncorrect() {
		return numberOfIncorrect;
	}


	public void setNumberOfIncorrect(double numberOfIncorrect) {
		this.numberOfIncorrect = numberOfIncorrect;
	}


	public double getPercentError() {
		return percentError;
	}


	public void setPercentError(double percentError) {
		this.percentError = percentError;
	}


	public void setClassifierString(String string) {
		this.ClassifierString=string;
		
	}
	public String getClassifierString( ) {
		return this.ClassifierString;
		
	}

	@Override
	public String toString() {
		
		return " "+SmallString+" In "+ numberOfTestSamples +"Sample   Percent Correct = "+percentCorrect+"   with number of Error "+numberOfIncorrect;
	}

String SmallString;
	public void setSmallString(String string) {
		
	 SmallString=string;
	}
public String getSmallString(){
	return SmallString;
}
   public void setFeatString(String Feat){
	   FeatString=Feat;
   }
	public String getFeatureString() {
		
		return FeatString;
	}


	public void setFirstDigitTp(double Tp) {
		FirstDigitTp=Tp;
	}
	public void setFirstDigitTpLabel(String label) {
		 
		FirstDigitTpLabel=label;
	}
	public void setSecondDigitTp(double Tp) {
		 SecondDigitTp=Tp;
		
	}
	public void setSecondDigitTpLabel(String label) {
	 
		SecondDigitTpLabel=label;
	}
	public void setThirdDigitTp(double Tp) {
	 ThirdDigitTp=Tp;
		
	}
	public void setThirdDigitTpLabel(String label) {
		 ThirdDigitTpLabel=label;
		
	}


	public double getFirstDigitTp() {
		return FirstDigitTp;
	}


	public String getFirstDigitTpLabel() {
		return FirstDigitTpLabel;
	}


	public double getSecondDigitTp() {
		return SecondDigitTp;
	}


	public String getSecondDigitTpLabel() {
		return SecondDigitTpLabel;
	}


	public double getThirdDigitTp() {
		return ThirdDigitTp;
	}


	public String getThirdDigitTpLabel() {
		return ThirdDigitTpLabel;
	}


	public void saveAll(String string) {
		 DataResults.saveAll(string);
	     save(string+"ClassifierStatus.txt");
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
	     		
	     		out.println("----------------------------------------------");
	     		
	     		out.println(DataResults.write());
	     		out.println("----------------------------------------------");
	     	  out.close();
	                
	        }
	        catch (Exception e){
	                System.err.println (" Error in writing to file");
	        }
		
	 
		
	}
	
	public String write(){
		
 return this.toString();
		
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
 
}
