/**
 * 
 */
package data.dataset;

import gui.AppDefaults;

import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Logger;

import classifiers.MultiFeature.ClassifierData;

import util.lib;

import data.feature.Feature;
import data.image.Digit;
import data.image.FeaturedRegion;
import data.image.ImageReader;
import data.image.RegionCreater;

/**
 * @author TOSHIBA
 *
 */
public class ArabicDataSetGenerator extends DataSetGenerator {
	private static transient final Logger logger = Logger.getLogger(ArabicDataSetGenerator.class);
int DBtype=ArabicDataBaseConnector.MADBASE;
  public void setArabicDB(int db){
	  DBtype=db;
	  
  }	
	public void setStatus(int s) {
		this.status=s;
		
	}

	public Digit getSingleImage(String filename){
		ArabicDataBaseConnector db=new ArabicDataBaseConnector();
		db.setDataBaseType(DBtype);
		db.setStatus(status);
		
		
			
			// the image offset... 
		
			Digit image=new Digit();
			
		////logger.trace(" reading the that has Image  "+	 filename);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType );
		///////////	
			
			
			image.setLabel(db.getDigitFromFileName(filename));
			
			imreader.setFile(db.getFullPath(filename));
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
			
			
	//	 image.computeAllFeatures();
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
		  return image;
	 
	}
	
	
	public DataSet GetDataSetByDigits(ClassifierData  d, boolean storeLocations){
//		logger.info("inisde the get dataset by digit ");
		DataSet dataset=new  DataSet();
		
		//dataset.initDataSetFeatures(FeaturedRegion.getComputedFeatures());
		//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
		//db =new MNISTDataSetGenerator();
	    //db.generateFeatures()
		
//		MNISTDataBaseConnector db=new MNISTDataBaseConnector();
//		db.setDataBaseType(MNISTDataBaseConnector.MNIST);
//		db.setStatus(status);
		
		ArabicDataBaseConnector db=new ArabicDataBaseConnector();
		db.setDataBaseType(DBtype);
		db.setStatus(status);
          if (!d.useRegions){
        	  // use all image then set features 
        	  Digit.setFeaturesToCompute(d.feat.get(0));
        	  Digit.loadAllFeatureArray();
        	  
          }
		
 
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
		
ArrayList<String> filesnames=new ArrayList<String>();
ArrayList<Integer> digits=d.ClassifierDigits();		

          Collections.sort(digits );

		for (int i = 0; i < digits.size(); i++) {
			
				ArrayList<String> tempFiles = db.getImageFilesForDigit(digits.get(i));
				logger.info( "  Status    "+status+"  number of samples read for digit "+digits.get(i)+"   "+tempFiles.size());
			filesnames.addAll(tempFiles);
		}
		
		// logger
		logger.info(" number of file to be read is "+filesnames.size());
		if (AppDefaults.ShuffleDataFiles){
		filesnames= lib.shuffleFiles(filesnames);
		}
	//	ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits(d.ClassifierDigits());
		logger.info(" Reading database now in  number  0 ");
		for (int i = 0; i < filesnames.size(); i++) {
			if (i%1000==0)
			logger.info(" image number  "+i+"   with file name is "+filesnames.get(i));
			// the image offset... 
		
			Digit image=new Digit();
 
			 //logger.trace(" reading the "+	mnistImages.get(i).y +" that has label "+	mnistImages.get(i).x);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType );
		//	logger.info(" file is "+filesnames.get(i));
			imreader.setFile(filesnames.get(i));
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
	 
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
			// i changed offset to i because offset in arabic database has no meaning and it is 0 
			dataset.addSample(finalFeat, image.getLabel(),i);
			if (storeLocations){
				
				dataset.addSampleLocation(filesnames.get(i));
			}
			//dataset.addOffset(image.getOffset());
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
			
		}
		//mnistImages
		return dataset;//.SaveToFile("filename");
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
		
		ArabicDataBaseConnector db=new ArabicDataBaseConnector();
		db.setDataBaseType(DBtype);
		db.setStatus(status);
 
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
		
		ArrayList<String> filesnames=new ArrayList<String>();
		
		for (int i = 0; i < digits.size(); i++) {
			filesnames.addAll(	db.getImageFilesForDigit(digits.get(i)));
		}
		
		
		filesnames= lib.shuffleFiles(filesnames);
		
		
	//	ArrayList<Point> mnistImages = db.getImagesOffsetLabelsDigits(digits);
	
		for (int i = 0; i < filesnames.size(); i++) {
			if (i%2000==0)
			logger.info(" image number  "+i);
			// the image offset... 
		
			Digit image=new Digit();
			
			 //logger.trace(" reading the "+	mnistImages.get(i).y +" that has label "+	mnistImages.get(i).x);
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType);
			
			imreader.setFile(filesnames.get(i));
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
		//	image.setLabel(mnistImages.get(i).x);
			//FeaturedRegion.setFeaturesToCompute(feats);
			//FeaturedRegion.loadAllFeatureArray();
			ArrayList<Feature> finalFeat=new ArrayList<Feature>();
			for (int j = 0; j < region.size(); j++) {
				FeaturedRegion.setFeaturesToCompute(feats.get(j));
				FeaturedRegion.loadAllFeatureArray();
				if (i%2000==0){
					logger.info( "  In ARABIC  database..................");
					logger.info( region.get(j).getMaxHorizontalRegion());
				}
				// now create a region of the image 
				FeaturedRegion ROI=new FeaturedRegion(image,region.get(j));
				ArrayList<Feature> TempFeat = ROI.getFeatures();
				for (int k = 0; k < TempFeat.size(); k++) {
					TempFeat.get(k).setSmallName( TempFeat.get(k).getSmallName()+region.get(j).getRegionName());
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
			
			dataset.addSample(finalFeat,image.getLabel(),image.getOffset());
			//dataset.addOffset(image.getOffset());
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
			
			
		}
		//mnistImages
		return dataset;//.SaveToFile("filename");
 
	}
	public void DisplayImages(ArrayList<Integer> digits, int size) {
		logger.trace("inisde the get dataset by digit ");
		 
		//dataset.setFormat(DataSet.FILE_INPUT_FORMAT_ARFF);
		
		
		//db =new MNISTDataSetGenerator();
	    //db.generateFeatures()
		
		ArabicDataBaseConnector db=new ArabicDataBaseConnector();
		db.setDataBaseType(this.DBtype);
		db.setStatus(status);
		ArrayList<Integer> digitTemp;
//		digitsForTest.add(new Integer(d1));
//		digitsForTest.add(new Integer(d2));
		for (int j = 0; j < digits.size(); j++) {
			
			digitTemp=new 	ArrayList<Integer>();
			digitTemp.add(digits.get(j));
		
  ArrayList<String> filenames = db.getImageFilesForDigit(digits.get(j));
	 

		for (int i = 0; i < filenames.size()&& i<size; i++) {
			
			// the image offset... 
		
			Digit image=new Digit();
		    int label = db.getDigitFromFileName(filenames.get(i));
			logger.trace(" reading the "+	filenames.get(i) +" that has label "+ label	 );
			ImageReader imreader=ImageReader.CreateImageReader(db.DatabaseType );
			
			imreader.setFile(filenames.get(i));
			
			//read image and compute features. 
			image.ReadImage(imreader);
			
			//double[] Features = image.getFeaturesArray();
			
			image.setLabel(label);
		 image.getFeatures();
		//	dataset.addSample(image);
		//	dataset.addSample(Features, mnistImages.get(i).x);
		}//for filessss 
	
		}//for digits 
		
		
	}//function... 

}
