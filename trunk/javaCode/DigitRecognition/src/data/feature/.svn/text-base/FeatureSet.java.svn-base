/**
 * 
 */
package data.feature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;


/**
 * @author Maha
 *
 */
public class FeatureSet  implements Cloneable,Serializable{
	private static transient final Logger logger = Logger.getLogger( FeatureSet.class);

	ArrayList<FeatureStat> FeaturesSelected=null;
	ArrayList<FeatureStat> AllFeaturesRead=null;
	
	
	public Object clone() throws CloneNotSupportedException {
		
		FeatureSet temp=null;
		
		temp=(FeatureSet) super.clone();
		if (AllFeaturesRead!=null)
		temp.AllFeaturesRead=(ArrayList<FeatureStat>) AllFeaturesRead.clone();
		if ( FeaturesSelected!=null)
		temp.FeaturesSelected=(ArrayList<FeatureStat>) FeaturesSelected.clone();
		
		return temp;
	}
	
	public void ComputeStates (){
		
	}
	
	public void ReadAllFeatures(String filename){
		
		
		
		filename= filename.replace(".txt", "_Feat.txt");
	     String line;
			try {
				 File afile ;
					Scanner input;
				logger.info("reading the file feature ................ wait");
				try{
				 afile = new File(filename);
				 input =  new Scanner(new BufferedReader(new FileReader(afile)));
				}catch (FileNotFoundException e) {
					
					logger.info("file not found will try using the default file ");
					afile=new File("feat.txt");
					 input =  new Scanner(new BufferedReader(new FileReader(afile)));
					
				//	e.printStackTrace();
				}
				
				
				
			
		//		features=new FeatureSet();
				ArrayList<FeatureStat> FeatureStatList=new ArrayList<FeatureStat>(); 
				  FeatureStat feat;
				  String tempF;
				  int    indexF;
			      while (input.hasNext()) {
			    	  tempF=input.nextLine();
			    	  indexF=Integer.parseInt(input.nextLine());
			    	  
			    	  feat=new FeatureStat();
			    	  feat.setFeatureIndex(indexF);
			    	  feat.setFeatureName(tempF);
			    	 logger.info(" adding feature "+tempF+" in the index "+indexF);
					 FeatureStatList.add(feat);
			      }
			     // UseAllFeatures=true;
//			      if (FeatureStatList.size()==2){
//			    	  this.twoClass=true;
//			      }
//			      if (FeatureStatList.size()!=this.NumOfFeatures){
//			    	  
//			    	  UseAllFeatures=false;
//			    	 logger.error("Error the number of featurs in data file not equal to that in the feature file");
//			    	 logger.error(" Reading  ... "+NumOfFeatures+"  feature from the data file and "+FeatureStatList.size()+" feature from the feautre file");
//			      }
//			      
			  	this.setAllFeaturesRead(FeatureStatList);
			      
				logger.info("finihsed reading the feature file..........");
			}
			catch (FileNotFoundException e) {
				
				logger.info(" the default file is not found... ");
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} 
	}
  public ArrayList<FeatureStat> getAllFeaturesRead() {
		return AllFeaturesRead;
	}
	public void setAllFeaturesRead(ArrayList<FeatureStat> allFeaturesRead) {
		AllFeaturesRead = allFeaturesRead;
	}
public int[] 	getSelectedFeaturesIndeces(){
	
	if (FeaturesSelected==null)
	{
	 //return the feature of the all reads 
		
		int[] ind=new int[AllFeaturesRead.size()];
		
		for (int i = 0; i < ind.length; i++) {
			ind[i]=AllFeaturesRead.get(i).getFeatureIndex();
		}
		return ind;
		
	}
	else {
		
	int[] ind=new int[FeaturesSelected.size()];
		
		for (int i = 0; i < ind.length; i++) {
			ind[i]=FeaturesSelected.get(i).getFeatureIndex();
		}
		return ind;
		
	}
		//get the featue indces 
	  
	//  return null;
	}
@SuppressWarnings("unchecked")
  public void  createFeatureSelectedList(int[] indeces){
	  
	  
	  if (AllFeaturesRead!=null)
		{
			FeaturesSelected=new ArrayList();
			
				for (int i = 0; i< indeces.length; i++) {
					 
					for (int j = 0; j < AllFeaturesRead.size(); j++) {
						if (AllFeaturesRead.get(j).getFeatureIndex()==indeces[i])
						{
						FeaturesSelected.add(AllFeaturesRead.get(j));
						break;
						}
						}
					 
				}
				
				
		}	
  }


public ArrayList<FeatureStat> getSelectedFeatures() {
	
	if (FeaturesSelected!=null)
	{
	
	return FeaturesSelected;
	}else 
	{
		return AllFeaturesRead;
	}
	
}


@SuppressWarnings("unchecked")
public void createFeatures(String[] featuresNames) {
	if (AllFeaturesRead!=null)
	{
		FeaturesSelected=new ArrayList();
		for (int i = 0; i < featuresNames.length; i++) {
			
			for (int j = 0; j < AllFeaturesRead.size(); j++) {
				if (AllFeaturesRead.get(j).getFeatureName().equals(featuresNames[i]))
				{
					FeaturesSelected.add(AllFeaturesRead.get(j));
					break;
				}
			}
			
			
		}
		
		
		
		
	}
	
}
  
  
	
}
