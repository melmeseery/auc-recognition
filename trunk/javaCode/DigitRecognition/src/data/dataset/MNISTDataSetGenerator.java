/**
 * 
 */
package data.dataset;



import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;

import classifiers.MultiFeature.ClassifierData;

import data.feature.Feature;
import data.image.Digit;
import data.image.FeaturedRegion;
import data.image.ImageReader;
import data.image.RegionCreater;


/**
 * @author TOSHIBA
 *
 */
public class MNISTDataSetGenerator  extends DataSetGenerator {
	
// this class will read all image in the data set... 
	private static transient final Logger logger = Logger.getLogger(MNISTDataSetGenerator.class);
	

	
	public Digit getSingleImage(int idint){
		MNISTDataBaseConnector db=new MNISTDataBaseConnector();
		db.setDataBaseType(MNISTDataBaseConnector.MNIST);
		db.setStatus(status);
		
		
		
		
		 Point p = db.getImagesOfsetLabel(idint);
	
			
			// the image offset... 
		
			Digit image=new Digit();
			
			logger.trace(" reading the that has Image  "+	idint);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType,idint );
			image.setLabel(p.x);
			imreader.setFile(db.getFilename());
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
			
			
		 image.computeAllFeatures();
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
		  return image;
	 
	}
	public Digit getSingleImage(String id){
		
		int idint=Integer.parseInt(id);
	 return getSingleImage(idint);
	 
		}
		

	///read images 
	// read image get labale, 
	// get offset....
public void getImageDisplayed(ArrayList<Integer> digits,int size){
	
	logger.trace("inisde the get dataset by digit ");
 
	//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
	
	
	//db =new MNISTDataSetGenerator();
    //db.generateFeatures()
	
	MNISTDataBaseConnector db=new MNISTDataBaseConnector();
	db.setDataBaseType(MNISTDataBaseConnector.MNIST);
	db.setStatus(status);
	ArrayList<Integer> digitTemp;
	
	
	
//	digitsForTest.add(new Integer(d1));
//	digitsForTest.add(new Integer(d2));
	for (int j = 0; j < digits.size(); j++) {
		digitTemp=new 	ArrayList<Integer>();
		digitTemp.add(digits.get(j));
	
	ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits(digitTemp);

	for (int i = 0; i < mnistImages.size()&& i<size; i++) {
		
		// the image offset... 
	
		Digit image=new Digit();
		
		logger.trace(" reading the "+	mnistImages.get(i).y +" that has label "+	mnistImages.get(i).x);
		ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType,	mnistImages.get(i).y );
		
		imreader.setFile(db.getFilename());
		
		//read image and compute features. 
		image.ReadImage(imreader);
		
		//double[] Features = image.getFeaturesArray();
		
		image.setLabel(mnistImages.get(i).x);
	 image.getFeatures();
	//	dataset.addSample(image);
	//	dataset.addSample(Features, mnistImages.get(i).x);
		
		
	}
 
	}//for digits 
	
	
}
	/// get all  offcet of label 1 or 2 or ....
	public DataSet GetDataSetByDigits(ArrayList<Integer> digits){
	//	logger.info("inisde the get dataset by digit ");
		DataSet dataset=new  DataSet();
		dataset.initDataSetFeatures(Digit.getComputedFeatures());
		//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
		//db =new MNISTDataSetGenerator();
	    //db.generateFeatures()
		
		MNISTDataBaseConnector db=new MNISTDataBaseConnector();
		db.setDataBaseType(MNISTDataBaseConnector.MNIST);
		db.setStatus(status);
 
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
		
		ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits(digits);
	
		for (int i = 0; i < mnistImages.size(); i++) {
			if (i%2000==0)
			logger.info(" image number  "+i);
			// the image offset... 
		
			Digit image=new Digit();
			
			logger.trace(" reading the "+	mnistImages.get(i).y +" that has label "+	mnistImages.get(i).x);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType,	mnistImages.get(i).y );
			
			imreader.setFile(db.getFilename());
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
			image.setLabel(mnistImages.get(i).x);
			
			dataset.addSample(image.getFeatures(),mnistImages.get(i).x,image.getOffset());
			//dataset.addOffset(image.getOffset());
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
			
		}
		//mnistImages
		return dataset;//.SaveToFile("filename");
		
	}
	// get 
	public DataSet GetDataSet(){
		
		DataSet dataset=new 	DataSet();
		dataset.initDataSetFeatures(Digit.getComputedFeatures());
		//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
		//db =new MNISTDataSetGenerator();
	    //db.generateFeatures()
		
		MNISTDataBaseConnector db=new MNISTDataBaseConnector();
		db.setDataBaseType(MNISTDataBaseConnector.MNIST);
		db.setStatus(status);
//		ArrayList<Integer> digitsForTest=new 	ArrayList<Integer> ();
		
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
		
		ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits();
	
		for (int i = 0; i < mnistImages.size(); i++) {
			
			// the image offset... 
		
			Digit image=new Digit();
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType,	mnistImages.get(i).y );
			
			imreader.setFile(db.getFilename());
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
			
			image.setLabel(mnistImages.get(i).x);
		//	dataset.addSample(image);
			dataset.addSample(image.getFeatures(),mnistImages.get(i).x);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
			
		}
		//mnistImages
		return dataset;//.SaveToFile("filename");
		
		
	}
	public void setStatus(int st) {

		status=st;
	}
//	public void DataSet GetDataSet(ArrayList<ClassifierData> d){
//		
//	}
	public DataSet GetDataSetByDigits(ClassifierData  d, boolean storeLocations){
		try{
//		logger.info("inisde the get dataset by digit ");
		DataSet dataset=new  DataSet();
		
		//dataset.initDataSetFeatures(FeaturedRegion.getComputedFeatures());
		//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
		//db =new MNISTDataSetGenerator();
	    //db.generateFeatures()
		
		MNISTDataBaseConnector db=new MNISTDataBaseConnector();
		db.setDataBaseType(MNISTDataBaseConnector.MNIST);
		db.setStatus(status);
		
		
	     if (!d.useRegions){
       	  // use all image then set features 
       	  Digit.setFeaturesToCompute(d.feat.get(0));
       	  Digit.loadAllFeatureArray();
       	  
         }
 
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
	     ArrayList<Integer> digits=d.ClassifierDigits();		

         Collections.sort(digits );
		//logger.info(" the data i sin digit c1 "+d.diAUCgit.size()+"  and in digit c2 "+d.digitC2.size());
		
		ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits(digits);
	
		for (int i = 0; i < mnistImages.size(); i++) {
			if (i%2000==0)
			logger.info(" image number  "+i);
			// the image offset... 
		
			Digit image=new Digit();
			
			 //logger.trace(" reading the "+	mnistImages.get(i).y +" that has label "+	mnistImages.get(i).x);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType,	mnistImages.get(i).y );
			
			imreader.setFile(db.getFilename());
			
			//read image and compute features. 
			image.ReadImage(imreader);
			 
			//double[] Features = image.getFeaturesArray();
			image.setLabel(mnistImages.get(i).x);
			//FeaturedRegion.setFeaturesToCompute(feats);
			//FeaturedRegion.loadAllFeatureArray();
			ArrayList<Feature> finalFeat=new ArrayList<Feature>();
			
			if (d.useRegions){
			for (int j = 0; j < d.regions.size(); j++) {
				FeaturedRegion.setFeaturesToCompute(d.feat.get(j));
				FeaturedRegion.loadAllFeatureArray();
			 
				// now create a reagion of the image 
				FeaturedRegion ROI=new FeaturedRegion(image,d.regions.get(j));
				ArrayList<Feature> TempFeat = ROI.getFeatures();
				for (int k = 0; k < TempFeat.size(); k++) {
					
					TempFeat.get(k).setSmallName( TempFeat.get(k).getSmallName()+d.regions.get(j).getRegionName());
				}
				finalFeat.addAll(TempFeat);
				
				
			}
			

			
			}
			else {
				
				
				finalFeat.addAll(image.getFeatures());
				
			}
			
			if (i==0){ // if this is the first image to get
				ArrayList<String> featNames=new ArrayList<String>();
				for (int j = 0; j < finalFeat.size(); j++) {
				   featNames.add(	finalFeat.get(j).getSmallName());
				}
				 
				dataset.initDataSetFeatures(featNames);
			}
			
			
			//ROI.setComputedFeatures(feats);
			
			dataset.addSample(finalFeat,mnistImages.get(i).x,image.getOffset());
			if (storeLocations)
			  {
				dataset.addSampleLocation(image.getOffset()+"");
			  
			  }
			//dataset.addOffset(image.getOffset());
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
			
		}
		//mnistImages
		return dataset;//.SaveToFile("filename");
		}
		catch(Exception ex) {
			
			logger.error(" exection  wile reading.... ");
			return null;
		}
	}
	@Deprecated
	public DataSet GetDataSetByDigits(ArrayList<Integer> digits,
			ArrayList<RegionCreater>  region , ArrayList<ArrayList<String>> feats) {
//		logger.info("inisde the get dataset by digit ");
		DataSet dataset=new  DataSet();
		
		//dataset.initDataSetFeatures(FeaturedRegion.getComputedFeatures());
		//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
		//db =new MNISTDataSetGenerator();
	    //db.generateFeatures()
		
		MNISTDataBaseConnector db=new MNISTDataBaseConnector();
		db.setDataBaseType(MNISTDataBaseConnector.MNIST);
		db.setStatus(status);
 
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
		
		ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits(digits);
	
		for (int i = 0; i < mnistImages.size(); i++) {
			if (i%2000==0)
			logger.info(" image number  "+i);
			// the image offset... 
		
			Digit image=new Digit();
			
			 //logger.trace(" reading the "+	mnistImages.get(i).y +" that has label "+	mnistImages.get(i).x);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType,	mnistImages.get(i).y );
			
			imreader.setFile(db.getFilename());
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
			image.setLabel(mnistImages.get(i).x);
			//FeaturedRegion.setFeaturesToCompute(feats);
			//FeaturedRegion.loadAllFeatureArray();
			ArrayList<Feature> finalFeat=new ArrayList<Feature>();
			for (int j = 0; j < region.size(); j++) {
				FeaturedRegion.setFeaturesToCompute(feats.get(j));
				FeaturedRegion.loadAllFeatureArray();
				if (i%2000==0){
					logger.info( "  In MNIST database..................");
					logger.info( region.get(j).getMaxHorizontalRegion());
				}
				// now create a reagion of the image 
				FeaturedRegion ROI=new FeaturedRegion(image,region.get(j));
				ArrayList<Feature> TempFeat = ROI.getFeatures();
				for (int k = 0; k < TempFeat.size(); k++) {
					TempFeat.get(k).setSmallName( TempFeat.get(k).getSmallName()+"_OF_R"+region.get(j).getRegionName());
				}
				finalFeat.addAll(TempFeat);
				
				
			}
			
		if (i==0){ // if this is the first image to get
			ArrayList<String> featNames=new ArrayList<String>();
			for (int j = 0; j < finalFeat.size(); j++) {
			   featNames.add(	finalFeat.get(j).getSmallName());
			}
			 
			dataset.initDataSetFeatures(featNames);
		}
			
			
			//ROI.setComputedFeatures(feats);
			
			dataset.addSample(finalFeat,mnistImages.get(i).x,image.getOffset());
			//dataset.addOffset(image.getOffset());
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
			
		}
		//mnistImages
		return dataset;//.SaveToFile("filename");
 
	}
	
}
