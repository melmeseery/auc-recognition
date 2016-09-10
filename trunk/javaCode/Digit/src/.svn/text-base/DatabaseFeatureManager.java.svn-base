import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class DatabaseFeatureManager {

	//in this class the features for all database is computedd an
	// statistical data is stored for all features 
	private static transient final Logger logger = Logger.getLogger( DatabaseFeatureManager.class);

	private static  String filename = "trainig_set.txt";

	FeatureStat tempfeat =new FeatureStat();
	ArrayList<FeatureStat> features=new ArrayList<FeatureStat>();
	DataBaseConnector db=new DataBaseConnector();
	
	double[][] featuresValues=null;
	
	DigitImage image=new DigitImage();
	boolean init=false;
	   int minFileIndex[] ;
	   int maxFileIndex[] ;
 		
	void setStartD(int d){
		
		db.setStartD(d);
	}   
	void compteFeaturesToTrainFileAllDigit(){
	
		try {
			//settings.
			//setStartD(1);
		//	db.Status=db.TEST;
			
			
			/// application 
			ArrayList<String> filenames =db.getImageFilesForAll();
			/// here we get all filename from the 
			if (db.Status!=db.TEST)
				filenames =shuffleFiles(filenames);
			
		    if (db.Status==db.TEST)
		    {
		    	filename="testing_set.txt";
		    	
		    }
		    if (db.Status==db.TRAIN)
		    filename="training_set.txt";
	
			//now i have shuffeled set of the files // to use it for training. 
		
			//create the file that will store features. 
			FileWriter aFile;
			aFile = new FileWriter(new File(filename));
			//use buffering
			BufferedWriter output = new BufferedWriter( aFile);
		
			String datastring="";
			//need to store count of features and the number of samples 
			datastring=""+filenames.size();
			output.write(datastring);
			output.newLine();
			
			
			datastring=""+(DigitImage.getComputedFeatures()+1);
			output.write(datastring);
			output.newLine();
		
		//for every file in the list / image in the database 
		for (int j = 0; j < filenames.size(); j++) {
			if (j%200==0)
				logger.info( "reading  "+j+"   "+filenames.get(j) );
			   logger.debug( filenames.get(j));
				//create and image. 
			image=new DigitImage();
			//read image and compute features. 
			image.ReadImage(filenames.get(j));
			int count =image.computeAllFeatures();
	   
			datastring=image.getFeatureString();
			output.write(datastring);
			output.newLine();
			//initailize the array that will hold all features for all images for digit 1 . 
			
		
			
		
			
			
			
		}//for all files  
		

	 
	//now close the files 
		output.close();
		aFile.close();
		
		
		   // create a file and store the file names 
	    //to know it
	    //when testing the calssifiers. 
	    storeFileNames(filenames);
	logger.info("      ------------------finished. ------------------ ");
		
		
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		
	}
	
	/**
	 * @param filenames
	 */
	private void storeFileNames(ArrayList<String> filenames) {
		FileWriter aFile ;
		//another file to write the names of the image files in same sequence they are 
		//added into the trainign set file
		try {
			aFile = new FileWriter(new File("files_names.txt"));
			
			//use buffering
			BufferedWriter out = new BufferedWriter( 	aFile );
		
			///first save the size of image set tested
			
			String temp=""+filenames.size();
			///
			int index;
		 out.write(temp);
		 out.newLine();
		 
		 for (int i = 0; i < filenames.size(); i++) {
			 temp=filenames.get(i);
			 index= temp.lastIndexOf("\\");
			 temp=temp.substring(index+1);
			 
			 out.write(temp);
			 out.newLine();
		}
			
			
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
	}
	/**
	 * @param files
	 * @return
	 */
	private ArrayList<String> shuffleFiles(
			ArrayList<String> files) {
		
		//create an emypt array to hold the shuffeld files 
		ArrayList<String>  RandomSet=new ArrayList<String>(files.size());
		RandomSet.ensureCapacity(files.size());
		
	ArrayList<Integer> indeces=new ArrayList<Integer>(files.size());
	
	
	for (int i = 0; i < files.size(); i++) {
		indeces.add(new Integer(i));
		
	}
	int randomi=(int) Math.floor(Math.random()*files.size());
	int index;
	for (int i = 0; i < indeces.size(); i++) {
		
		randomi=(int) Math.floor(Math.random()*indeces.size());
	// now move files from he input to the ouptut array . 
		index=indeces.get(randomi);
		
		RandomSet.add( files.get(index));
		indeces.remove(randomi);
		
	}
	 
		 //check that all indeces are added 
	if(indeces.size()>0)
	{
		for (int i = 0; i <indeces.size(); i++) {
			index=indeces.get(i);
			RandomSet.add( files.get(index));
		}
	}
	
	
	if (logger.isTraceEnabled()){
		
		for (int i = 0; i < 100 && i<RandomSet.size(); i++) {
			logger.trace(" -- "+RandomSet.get(i));
		}
		
		
		
	}
		
		
		
		
		// TODO Auto-generated method stub
		return RandomSet;
	}

	/**
	 * 
	 */
	void generateStateForAllDigits(){
		
		
		int minf,maxf;
		
		//for every digit. 
		for (int d =db.getStartD(); d <  App.CATEGORY_SIZE; d++) {
		
			ArrayList<String> filenames = db.getImageFilesForDigit(d);
			
         
			//for every image in the database 
			for (int j = 0; j < filenames.size(); j++) {
				if (j%200==0)
					logger.info( "reading "+filenames.get(j) );
				   logger.debug( filenames.get(j));
					
				image=new DigitImage();
				//read image and compute features. 
				image.ReadImage(filenames.get(j));
				int count =image.computeAllFeatures();
				
				//initailize the array that will hold all features for all images for digit 1 . 
				
				if (featuresValues==null)
					
				{	featuresValues=new double[count][ ];
				
				logger.info("  features values is initalized ----------");
				     for (int i = 0; i < featuresValues.length; i++) {
				    	 featuresValues[i]=new double[  filenames.size()];
				    	 for (int k = 0; k < featuresValues[i].length; k++) {
				    		 featuresValues[i][k]=0.0;
						}
					}
				
				}
				
				//for every features do the following. 
				for (int k = 0; k < count; k++) {
					//get the feature values , and the feature stat. 
					//featValue=image.getFeature(k);
					featuresValues[k][j]=image.getFeature(k);
					
					
					
//					tempfeat=features.get(k);
//					// now get the values for this 
//					
////					// now add change the values of avg, max, min ...
////					 tempfeat.getAvg()[d]+=featValue;
//					if (tempfeat.getMax()[d]<featValue){
//						tempfeat.getMax()[d]=featValue;
//						
//					}
////					
//					if (tempfeat.getMin()[d]>featValue){
//						tempfeat.getMin()[d]=featValue;
//					}
				}
				
				
				
			}//for all files for digit d 
			
			storeStats(featuresValues,d);
			
			
			for (int i = 0; i < features.size(); i++) {
			features.get(i).minFilename[d]=filenames.get(minFileIndex[i]);	
			features.get(i).maxFilename[d]=filenames.get(maxFileIndex[i]);	
			}
		
		}
		
		
		logger.info("      ------------------finished. ------------------ ");
	}
	
	

	
	/**
	 * initFeaturesStat
	 * initialize the features statistics array 
	 */
	public void initFeaturesStat(){
		logger.info(" - -----------------init features status. ");
		int count =DigitImage.getComputedFeatures();
	
		for (int i = 0; i <count; i++) {
			 tempfeat =new FeatureStat(DigitImage.getFeatureName(i));
			 features.add(tempfeat);
			
			
			
		}
		 init=true;
	}
	/**
	 * The function generate the statistics needed ( minimum, max, mean , standard deviation )
	 *  from the featuresValues for all images of the d digit. 
	 *  after generating the statistics the fucntion store values in the featureStat objects. 
	 * 
	 * @param featuresValues the double array of features. (images, features)
	 * @param d the digit that all features are computed at. 
	 */
	public void storeStats(double[][] featuresValues,int d ){
		
		
		if (init==false)
			initFeaturesStat();
		   double avg,std,max,min,featValue;	
		   
		    minFileIndex=new int[featuresValues.length];
		    maxFileIndex=new int[featuresValues.length];
		   
		// now i have a list of all features with the  digit d. 
		
		//for all features 
		for (int i = 0; i <featuresValues.length; i++) {
			//
			tempfeat=features.get(i);
			
			// now i want to compute mean, std, max and min., 
			avg=std=0.0;
			max=Double.MIN_VALUE;
			min=Double.MAX_VALUE;
			for (int k = 0; k <featuresValues[i].length; k++) {
				
				
				avg+=featuresValues[i][k];
				if (max<featuresValues[i][k])
				{
					
					max=featuresValues[i][k];
					maxFileIndex[i]=k;
					
					
				}
				if (min>featuresValues[i][k]){
					min=featuresValues[i][k];
					minFileIndex[i]=k;
				}
				
				
			}
			avg/=featuresValues[i].length;
			// this four loop is to compute the standard divation of the feature 
			// it must be computed after the mean is computed. 
			for (int k = 0; k <featuresValues[i].length; k++) {
				
			 std+=(avg-featuresValues[i][k])*(avg-featuresValues[i][k]);
				
			}
			std/=featuresValues[i].length;
			
			std=Math.sqrt(std);
			
			tempfeat.setAvg(avg, d);
			tempfeat.setStd(std,d);
			tempfeat.setMax(max,d);
			tempfeat.setMin(min,d);
			
		}
		
		
		
	}
		
	/**
	 * This function will create the excel file that 
	 * will store all statistics computed from the feature computations.
	 * 
	 * @param filename  excel file to store statistics 
	 */
	public void storeAllDataToFile (String filename ){
		HSSFWorkbook wb =  new HSSFWorkbook();
		
	   
		
		for (int i = 0; i <features.size(); i++) {
			features.get(i).writeToXlsSheet(wb);
		}
		
		
		 FileOutputStream fileOut;
			try {
				fileOut = new FileOutputStream(filename);
				 wb.write(fileOut);
				    fileOut.close();
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				//e.printStackTrace();
			}
		
	}

	/**
	 * @param type
	 */
	public void setDataBaseType(int type) {
		// TODO Auto-generated method stub
		db.setDataBaseType(type);
		
	}

	/**
	 * @param dirName
	 */
	public void setDataBaseDir(String dirName) {
		
		db.setDataBaseDir(dirName);
	}	
	
	
	
}
