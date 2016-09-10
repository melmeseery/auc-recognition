/**
 * 
 */
package classifiers.results;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import classifiers.MultiClassClassifier;

import data.dataset.ArabicDataSetGenerator;
import data.dataset.DataBaseConnector;
import data.dataset.DataSetGenerator;
import data.dataset.MNISTDataSetGenerator;
import data.image.Digit;

/**
 * @author TOSHIBA
 *
 */
public class ImageErrors {
	private static transient final Logger logger = Logger.getLogger( ImageErrors .class);
	ArrayList<Integer> offsets;
	ArrayList<Integer> samples;
	ArrayList<Integer> 	Classified ;
	ArrayList<Integer> labels;
	ArrayList<Digit> images;
	ArrayList<String> ErrorString=null;
	ArrayList<Integer> orginalIndex;
	ArrayList<String> Locations;
	int db;
	public void setOrginalLocations(ArrayList<String> locations, ArrayList<Integer> labels){
		// based on the curret 
		Locations=locations;
		this.labels=labels;
		// i have to process samples now to get the orginal...
		
		
		
	}
	 
	@Deprecated
	public void ReadFileName(ArrayList<Integer> index, ArrayList<String> filenames)
	
	{
		
	}
	@Deprecated
public	void ReadOffsets(String filename){
		try {
			logger.info(" reading the file................ wait ");
			File afile = new File(filename);
			Scanner input =  new Scanner(new BufferedReader(new FileReader(afile)));
		
          int linecount=0;
          
          ArrayList<Integer>    Tempoffsets=new ArrayList<Integer>();
          ArrayList<Integer>   Templabels=new ArrayList<Integer>();
          ArrayList<Integer>    TempSample=new ArrayList<Integer>();
          
          ArrayList<Integer>  TempOriginalIndex=new ArrayList<Integer>();
          //input.useDelimiter(" ");
			//String line1;
			String lin;
			int tar=0;
			int maxFeatures=0;
			int correctFeatcount=0;
		      while (input.hasNext()) {
		    	  
		      int index=input.nextInt();
		      int indexMain=input.nextInt();
		       int off=input.nextInt();
		       int target=input.nextInt();
		    
		       Tempoffsets.add(new Integer(off));
		       TempOriginalIndex.add(new Integer(index));
		       TempSample.add(new Integer(indexMain));
		       Templabels.add(new Integer(target));
    
			}
		      

		      // now create the real offset with sample in sample only 
		      if (samples!=null){
		    	  offsets=new ArrayList<Integer>();
		    	  labels=new ArrayList<Integer>();
		    	  orginalIndex=new ArrayList<Integer>();
		    	 int k=0;
		    	 int samp;
		    	  for (int i = 0; i < samples.size(); i++) {
					samp=samples.get(i);
					if (samp<TempSample.size()){
						//less than sample then get the index and offset and target. 
						offsets.add(Tempoffsets.get(samp));
						labels.add(Templabels.get(samp));
						orginalIndex.add(TempOriginalIndex.get(samp));
						
					}
						
					
				}
		      }
		      
		      
		      // dispose all the resources after using them.
		      input.close();
		      
		     
		      
		} catch (FileNotFoundException e) {
	 
			e.printStackTrace();
		}

	}
	
	
	 public void addSampleId(int id){
		if(samples==null)
			samples=new ArrayList<Integer>();
       samples.add(new Integer(id));		
	}
	 public  void addSampleError(int id, int finalvote, String st){
		if(samples==null)
			samples=new ArrayList<Integer>();
       samples.add(new Integer(id));
   	if(Classified==null)
       Classified=new ArrayList<Integer>();
   	Classified.add(new Integer(finalvote));
   	
   	
       if (ErrorString==null)
    	   ErrorString=new ArrayList<String>();
       ErrorString.add(st);
	}
	// now read image from the minist files...
 
	 public  void StoreImages(String dir){
		int off;
		

		
		// 
		if (Locations!=null){
			
			// check if dir exist 
			File f=new File(dir);
			if (!f.exists()){
				  if  (!f.mkdirs()){
					  return ;
					  
				  }
			}
			
			//
			logger.info("  after check all the directory is createdd.........................");
		// read image from minist and store them..
			DataSetGenerator dbr=null;
			if (db==DataBaseConnector.MNIST){
					dbr=new  MNISTDataSetGenerator();
			}
			else {
				
				dbr=new ArabicDataSetGenerator();
				
			}
		 Digit d;
		 String finalfile="";
		 
		 for (int i = 0; i < samples.size(); i++) {
		        logger.info(" the file  "+ Locations.get(samples.get(i))+"");
			 
			  d=dbr.getSingleImage( Locations.get(samples.get(i))+"");
			  finalfile=dir+"L"+labels.get(samples.get(i))+"_Cas_"+Classified.get(i)+"_S"+samples.get(i);
			d.SaveImage(finalfile);
			d.WriteTextImage(finalfile);
			//d.getImage().	
			  logger.trace("i "+   i+ finalfile);
			if (i%100==0)
				logger.info( " i "+i+ finalfile);
		}
		 
		 
		}
		
		StoreSumFile(dir);
	}
	
		public void StoreSumFile(String dir){
			
			  FileOutputStream file; 
		        PrintStream out; // declare a print stream object
		        try {
		         // Create a new file output stream
		        file = new FileOutputStream(dir+"dataSummary.txt");

		                // Connect print stream to the output stream
		               out = new PrintStream(file);

		               // out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
		             //  out.println(" There are "+offsets.size()+"  offsets ");
		               
		               out.println("  there are some error and here is the details. ");
		                //double dataw; 
		               for (int i = 0; i < samples.size(); i++) {
		     		out.println("  Error "+ i+ " Location of "+ Locations.get(samples.get(i))+ " for sample "+" L "+labels.get(samples.get(i))+" _S "+samples.get(i)+"  "+ErrorString.get(i));
		     			 
		     			//d.getImage().
		     		}
		              
		      
		              	  
		              	  
		              	 
							
						 
		    
		                
		        }
		        catch (Exception e){
		                logger.error (" Error in writing to file");
		        }
			
			
		}
		/**
		 * @return the db
		 */
		public int getDb() {
			return db;
		}
		/**
		 * @param db the db to set
		 */
		public void setDb(int db) {
			this.db = db;
		}
}
