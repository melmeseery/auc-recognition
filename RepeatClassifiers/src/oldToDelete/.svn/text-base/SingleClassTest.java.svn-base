package oldToDelete;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import collection.NumericalComparator;
import collection.SortedValueMap;

import data.Accuracy;
import data.DataSet;
import data.FeatureStat;
import data.ResultSetAcc;

public class SingleClassTest {
	private static transient final Logger logger = Logger.getLogger(  SingleClassTest.class);
	SortedValueMap  TestsResults;
	int c1,c2;
	
	int alg=1;
	DataSet data;
	DataSet orginalData;
String FeatureString="";	
ResultSetAcc result;
	
	
	String [] FeaturesToTest;
	
	ArrayList <String> CurrentFeatures;
	
	public void ReadDataFromFile (String filename){
		//orginalData
		orginalData=new DataSet ();
		orginalData.setFormat(1);
		orginalData.ReadFromFile(filename);
	
	}
	
	public void setFeaturesToTest(String [] Feat ){
		
		FeaturesToTest=Feat;
		
	}
	
	public void  initTest(int c1,int c2,String filename){
		this.c1=c1;
	this.c2=c2;
		ReadDataFromFile(filename);
//		if (FeaturesToTest!=null)
//		{
//			
//			data= orginalData.GenearteClassDataSetTwoClasses(c1, c2,FeaturesToTest);
//		}
//		else {
		orginalData= orginalData.GenearteClassDataSetTwoClasses(c1, c2);
	//	}
		//now i have the setting of the data and the classes i want 
	}
	private ArrayList<String[]> getTwoCompination ( String[] source  ) {
		 ArrayList<String[]> compinations=new ArrayList<String []>();
		
		String[] temp=new String[2];
		
		
		for (int i = 0; i < source.length; i++) {
			for (int j = i; j < source.length; j++) {
				temp[0] = source[i];
				temp[1]=source[j];
				compinations.add(temp);
			}
		}
		
		
		return compinations;
		
		
		
		
	}
	
	
	private ArrayList<ArrayList<String>> getCompinations( String [] source , int size,int starti){
		
		if (size==1)
		{
			ArrayList<ArrayList<String>> compinations=new ArrayList<ArrayList<String>>();
			 
			for (int i = starti; i < source.length; i++) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(source[i]);
				compinations.add(temp);
			
			}
			return compinations;
			
		}
//		if (size==2){
//			
//			
//			
//		}
		else { 
			size--;
			ArrayList<ArrayList<String>> AllCompinations=new ArrayList<ArrayList<String>>(); 
			for (int i = starti; i < source.length; i++) {
			
				  ArrayList<ArrayList<String>> comp = getCompinations(source,size,i+1);
				  for (int j = 0; j < comp.size(); j++) {
					  comp.get(j).add(source[i]);
					  
					  AllCompinations.add(comp.get(j));
					
				}
							
			}
				return  AllCompinations;
			
			
		}
		
//		
//		for (int i = 0; i < source.length; i++) {
//			
//			
//			
//			
//		}
		
		
	}
	
	
	public void TestAndGenearteFeatures(){
		
		ArrayList<ArrayList<String>> allTest=new ArrayList<ArrayList<String>>(); 
		///for  each feature  
		for (int Test = 1; Test <FeaturesToTest.length; Test++) {
			//for the length of the features i need to test .......
			//do the following 
			logger.info("generating compination using  "+Test+" features  ");
			///start creating the combination for by 0 , 1 , 2 , 3, n features
			ArrayList<ArrayList<String>> TestCompinations=this.getCompinations(FeaturesToTest, Test, 0);
			logger.info("fount "+ TestCompinations.size() + " compinationssssss  ");
			for (int i = 0; i < TestCompinations.size(); i++) {
				
				allTest.add(TestCompinations.get(i));
				if (logger.isInfoEnabled())
				{
				display(TestCompinations.get(i));	
				}
			}
			
			//now i have all test compinations........
			//this loop is for the 
			//CurrentFeatures=new ArrayList(); //creat the set of feature to test 
			
			
		}
		//all the features compination
		ArrayList<String> t=new ArrayList<String>();
		for (int i = 0; i < FeaturesToTest.length; i++) {
			t.add(FeaturesToTest[i]);
		}
		allTest.add( t);
		
		logger.info("---------------------compinations founds--------------------------------------------------------------------");
		for (int i = 0; i < allTest.size(); i++) {
			display(allTest.get(i));
		}
		logger.info("total of "+allTest.size()+"  compinations ");
		
		logger.info("****************** finish genearting compination****************************************************");
		
		TestsResults=new SortedValueMap(new NumericalComparator());
		//int i = 16;
		
		for (int i = 0; i < allTest.size(); i++) {
			
		
		logger.trace(" ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ testing the test no. "+i);
		display(allTest.get(i));
			CurrentFeatures=allTest.get(i);
			
			//now this is the feature i want to test ....
			
			
			//create the data and test 
			double acc= TestFeatures();
			display(CurrentFeatures);
			logger.info("  result is "+acc);
			
			TestsResults.put(CurrentFeatures, new Double(acc));
			
			
		}
		
		//now dislay the final result 
		
		ArrayList<String> BestFeatures = (ArrayList<String>) TestsResults.getLastKey();
		double bestAcc=(Double) TestsResults.getLastValue();
		logger.warn( "     the accuracy achieved is     "+bestAcc);
		
		for (int h = 0; h < BestFeatures.size(); h++) {
			logger.warn(" feature  "+h+"  = "+BestFeatures.get(h));
		}
		
		/////////now i have an array with all compinations..................
		
		
		
		
	}
	public  void SaveResultsToFile(String filename){
		// TestsResults
	 
		
		 FileOutputStream file; 
         PrintStream out; // declare a print stream object
         try {
          // Create a new file output stream
         file = new FileOutputStream(filename);

                 // Connect print stream to the output stream
                out = new PrintStream(file);
                 out.println(" -------Result of run-------- " );
                 
             	ArrayList<String> BestFeatures = (ArrayList<String>) TestsResults.getLargestKey();
        		double bestAcc=(Double) TestsResults.getLargestValue();
        		out.println( "     the accuracy achieved is     "+bestAcc+"  this is with features ");
        		for (int i = 0; i < BestFeatures.size(); i++) {
        			out.print(BestFeatures.get(i));
        			if ((i+1)<BestFeatures.size()){
        				out.print(" , ");
        				
        				
        			}
        			else 
        			{
        				out.println();
        			}
				}
        		
        		out.println( "*********************************Details log of test and result**************************************************");
        		
        		int count=1;
                 //TestResults
                 for (Iterator iterator = TestsResults.entrySet().iterator(); iterator
						.hasNext();) {
                	 
					Entry it = (Entry) iterator.next();
					BestFeatures =(ArrayList<String>) it.getKey();
					bestAcc=(Double) it.getValue();
					out.println( "--------------------- Test no. "+ count+"---------------------------- ");
					out.println( "   This was      "+bestAcc+"  this is with features ");
	        		for (int i = 0; i < BestFeatures.size(); i++) {
	        			out.print(BestFeatures.get(i));
	        			if ((i+1)<BestFeatures.size()){
	        				out.print(" , ");
	        				
	        				
	        			}
	        			else 
	        			{
	        				out.println();
	        			}
					}
					
	        		count++;
				}
                 out.println("");    
             
				
               		  
               		  out.flush();
               		  System.out.println(" writing samples number ");
           
					
				
     
                 
         }
         catch (Exception e){
                 System.err.println ("Error in writing to file");
         }
		
		
	}
	
	private void display(ArrayList<String> st){
		String str="";
		for (int i = 0; i < st.size(); i++) {
			str+=st.get(i);
			if (i<st.size()-1)
				str+="  , ";
		}
		this.FeatureString=str.replace(" , ", "_");
		logger.info(str);
		
	}
	public double TestFeatures(){
       //first get the data 	
		logger.info("generating the data for this set of features... ");
		if (this.CurrentFeatures!=null)
		{
			String[] strFeat=new String[CurrentFeatures.size()];
			for (int i = 0; i <strFeat.length; i++) {
				strFeat[i]=CurrentFeatures.get(i);
			}
			
			data= orginalData.GenearteFeatureDataSet(strFeat);
		}
		logger.info("finshed data now train............");
		 result=trainValidateClassifier(alg);
		 result.computeAcc();
		 
	//	 result.getAccuracy();
		  Accuracy acc = result.getFinalAcc();
		  double Acc=acc.getAccuracy();
		 
		 
		return Acc;
	}
	
	//this function creat valiation set , save train , test and then 
	public ResultSetAcc trainValidateClassifier(int alg){
		ResultSetAcc result=new ResultSetAcc();
		try{
		data.CreateValidationSet();
		
		//+FeatureString.replace(" ", "")
		data.setFormat(2);
		String filename="C"+c1+"_Vs_C"+c2+"_"+".txt";
		String train=filename.replace(".txt", "_train.txt");
		String test="";
		test =filename.replace(".txt", "_validation.txt");
		
		
		
		logger.info("saving the new data ");
		data.SaveToFile( filename);
		
		
		//for a specifi feature string 
		logger.info("training and testing the features... ");
		
		ClassifierApp  classifier;
		classifier= new ClassifierApp ();
		 classifier.RunLibSVMClassifier( alg , train , test );
		 
		 
		 logger.info("   --------Finished traiing now processing the data --------- ");
		 
		 
		 result.setTargets(data.getValidationsTargets());
		 String resultFile=test.replace(".txt", "Perdict.out");
		 logger.info(" Reading  from the perdict file the result... ");
		result.ReadFromLibSVMFile(resultFile);
		result.computeAcc();
		
		
		}catch (Exception ex) {
			//ex.printStackTrace();
			logger.error("  error in this classifier ");
		}
		return result;
	   //after reaind the result of the classifier now analysis 
	}
	//SortedValueHashMap <Double, ArrayList<String> > testResults;
	////read data
	
	//set curet featurs from the features to test
	
	
	// train (validation )
	
	
	//test  (test)
	
	// read result set 
	
	
	// evaluate feature and add result to total accuaracy... 
	
	//complete ot nexxt test
	
	
	
}
