package classifiers.MultiFeature;

import gui.AppDefaults;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.apache.log4j.Logger;

import applications.subMuliClassifiers.RunECOCclassifiers;

import classifiers.results.ClassifierResult;
import classifiers.results.ImageErrors;

import sun.reflect.generics.tree.FormalTypeParameter;
import util.BinaryTreeWithKey;
import util.lib;

import data.dataset.DataBaseConnector;
import data.image.RegionCreater;

public class ClassifierData  implements Cloneable{
	
	private static transient final Logger logger = Logger.getLogger(ClassifierData.class);
  	public ArrayList<ArrayList<String>> feat;
	 public ArrayList<RegionCreater>  regions;
	 public ArrayList<Integer> digit;
	 public ArrayList<Integer> digitC2=null;
	 public  String[] FeatureNames;
	 public String FeautureSummary="";
	 static public int Binary=0;
	 static public int MultiClass=1;
	 static public int Hierarchal=2;
	 int type=Hierarchal;
	 public boolean useSplit=false;
	 public int NumberTrainSamples=0;
	 private String name="";
	 public double Accuracy=0.0;

	public ClassifierResult Result=null;


	 public double noSamples=0;
	public boolean useRegions=false;
	public Integer HierarchyKey=null;
	public boolean	useProbability=true;
	 
	 public ClassifierData() {
	//	 private static transient final Logger logger = Logger.getLogger(ClassifierData .class);
		 feat=new ArrayList<ArrayList<String>>();
		 regions=new ArrayList<RegionCreater>();
		 digit=new ArrayList<Integer>();
		 digitC2=new ArrayList<Integer>();
		 HierarchyKey=new Integer(0);
	//	 FeatureNames=new ArrayList<String>();
		 
	}
	 public ClassifierData  Merge (ClassifierData c2 ){
		 ClassifierData merged=new ClassifierData();
		 ArrayList<String> finalFeatures ;

		  
		  //ClassifierData cTest = (ClassifierData) c2.clone();
		 
	
		
		 
		 for (int i = 0; i < regions.size(); i++) {
			
			 RegionCreater reg = regions.get(i);
			 
			 for (int j = 0; j <  c2.regions.size(); j++) {
				 if (reg.equals( c2.regions.get(j))){
				 //if i found a same region then i need to merge the feature then add the 
				 // this is incremental add, ass region can be in more then one classifier
			   finalFeatures = lib.mergeString(feat.get(i),c2.feat.get(j));
				 // add the region and the features to the class new
				 
				 c2.regions.remove(j);
				 c2.feat.remove(j);
				 
				 merged.feat.add(finalFeatures);
				 merged.regions.add(regions.get(j));
				break; 
			 }
			}
			
		}
		 
		 if (c2.regions.size()>0){
			 
			 for (int i = 0; i < c2.regions.size(); i++) {
				 merged.feat.add(c2.feat.get(i));
				 merged.regions.add(c2.regions.get(i));
			}
			 
			 
		 }
		 
		 
		 
		 
		 
		 
		 
		 ArrayList<Integer> mergedDigits=lib.merge(digit, c2.digit);
			
			merged.digit=mergedDigits;
		 if (digitC2!=null){
		 merged.digitC2=lib.merge(digitC2, c2.digitC2);
		 }
		 
		 return  merged;
		 
		 
	 }
	 
	 @Deprecated
	 public String getClassifierName(){
		 String temp="";
		 if (type==MultiClass){
 for (int i = 0; i < digit.size(); i++) {
	 if (i==0){
		 temp+=digit.get(i);
	 }
	if (i!=0){
	 temp+="_VS_"+digit.get(i);
	}
 }
		 }
 else {
	 temp=" To be done ";
 }
 

		 
		 return temp;
		 
	 }
	 
		public boolean isInDigits(int d){
			for (int i = 0; i < digit.size(); i++) {
				if (digit.get(i).equals(d))
					return true;
			}
			for (int i = 0; i < digitC2.size(); i++) {
				if (digitC2.get(i).equals(d))
					return true;
			}
			return false;
			}
		

	 public void addFeatureRegion(ArrayList<String> Features,RegionCreater reg){
		 feat.add(Features);
		 regions.add(reg);
		 
	 }
	 public void addDigit(int d){
		 
		 digit.add(d);
	 }
	 public void setDigits( ArrayList<Integer> d){
		 digit=d;
	 }
	 public void createFeatureNameList(){
		 int size=0;
		 FeautureSummary="";
		 for (int i = 0; i < feat.size(); i++) {
			 if (feat.get(i)!=null)
			size+=feat.get(i).size();
		}
		 
		 FeatureNames =new String [size];
		 int k=0;
		 
		 for (int i = 0; i < feat.size(); i++) {
			 
			String Regname ="";
			if (useRegions){
		     	Regname= regions.get(i).getRegionName();
			}
			 if (feat.get(i)!=null)
			for (int j = 0; j < feat.get(i).size(); j++) {
				 
				FeatureNames[k]=feat.get(i).get(j)+Regname;
				FeautureSummary+=FeatureNames[k]+" , ";
				k++;
			}
			
			
			
		}
		 
		 
		
	 }
	 
		@Override
		public Object clone() {
		 
			try {
				ClassifierData  classObject  = (ClassifierData )super.clone();
				classObject.digit=(ArrayList<Integer>) this.digit.clone();
				if (this.digitC2!=null)
				classObject.digitC2=(ArrayList<Integer>) this.digitC2.clone();
				classObject.feat=new ArrayList<ArrayList<String>>();
				for (int i = 0; i < feat.size(); i++) {
					classObject.feat.add((ArrayList<String>) feat.get(i).clone());
				}
				classObject.HierarchyKey= new Integer(HierarchyKey);
				
				classObject.regions=new ArrayList<RegionCreater>();
				for (int i = 0; i < regions.size(); i++) {
					classObject.regions.add((RegionCreater) regions.get(i).clone());
				}
//				if (Result!=null);
//				 classObject.Result= (ClassifierResult) Result.clone();
				
				return classObject;
			} catch (CloneNotSupportedException e) {
				 
				e.printStackTrace();
				return this;
			}
		}

	public String getName() {
	 
		return name;
	}
	public void setName(String n)
	{
		name=n;
	}
	@Override
	public String toString() {
	 String s="";
	 s+=this.getName();//+" Result"+Accuracy;
		return s;
	}
	
	

	public String toStringDetailed() {
	 String s="";
	 s+=this.getName();
	 if(useRegions)
	 {
		 if (regions!=null)
			 for (int i = 0; i < regions.size(); i++) {
				for (int j = 0; j < feat.get(i).size(); j++) {
					s+="  Feature "+feat.get(i).get(j)+" in "+regions.get(i).getRegionName();
					
				}
			 }
	 }else {
		 		if (feat.size()>0)
						s+="  Feat("+feat.get(0)+")";
						
					}
	 s+="   Using  type="+type+"  and   hierarchy key = "+HierarchyKey;
				 
	
	 
		return s;
	}
	public ArrayList<Integer> ClassifierDigits() {
		logger.info("  type is "+type);
		 if (type==Binary||type==Hierarchal){
			 ArrayList<Integer> di=new ArrayList<Integer>();
//			      di.addAll(digit);
//			 if (digitC2!=null);
//			    di.addAll(digitC2);
		di=	 lib.merge(digit, digitC2);
			    
			    
			    logger.info("  digits is   "+di);
			    
			    
			    
			 return di;
			 
		 }
		 else if (type==MultiClass)
		 {
			return digit; 
		 }
		return null;
	}
	public void setType(int binary2) {
		this.type=binary2;
		
	}
	public int getType(){
		return this.type;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Accuracy);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(FeatureNames);
		result = prime * result + ((digit == null) ? 0 : digit.hashCode());
		result = prime * result + ((digitC2 == null) ? 0 : digitC2.hashCode());
		result = prime * result + ((feat == null) ? 0 : feat.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((regions == null) ? 0 : regions.hashCode());
		result = prime * result + type;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassifierData other = (ClassifierData) obj;
		if (Double.doubleToLongBits(Accuracy) != Double
				.doubleToLongBits(other.Accuracy))
			return false;
		if (!Arrays.equals(FeatureNames, other.FeatureNames))
			return false;
		if (digit == null) {
			if (other.digit != null)
				return false;
		} else {
			if (digit.size()!=other.digit.size())
				return false;
				
				for (int i = 0; i < digit.size(); i++) {
					if (!digit.get(i).equals(other.digit.get(i)))
						return false;
					
				}
			
		}
	
		if (digitC2 == null) {
			if (other.digitC2 != null)
				return false;
		} else {
			if (digitC2.size()!=other.digitC2.size())
			return false;
			
			for (int i = 0; i < digitC2.size(); i++) {
				if (!digitC2.get(i).equals(other.digitC2.get(i)))
					return false;
				
			}
			
		}
	
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (HierarchyKey==null){
			if (other.HierarchyKey!=null)
				return false;
			
		}else if (!HierarchyKey.equals(other.HierarchyKey)){
			
              return false;			
			
		}
		
		
		if (regions == null) {
			if (other.regions != null)
				return false;
		} else {
			if (regions.size()!=other.regions.size())
			return false;
			for (int i = 0; i < regions.size(); i++) {
				if (!regions.get(i).equals(other.regions.get(i)))
						return false;
			}
			
		}
		if (feat == null) {
			if (other.feat != null)
				return false;
		} else 
//			if (!feat.equals(other.feat))
//			return false;
		{
			if (feat.size()!=other.feat.size())
				return false;
				for (int i = 0; i < feat.size(); i++) {
					if (feat.get(i).size()!=other.feat.get(i).size())
						return false;
					
					for (int j = 0; j < feat.get(i).size(); j++) {
						if (!feat.get(i).get(j).equals(other.feat.get(i).get(j)))
						return false;
					}
					
 
				}
				
			
		}
		
		if (type != other.type)
			return false;
		
		if (useRegions!=other.useRegions)
			return false;
		
		return true;
	}
	public void createClassifierName() {
		name = "";
		name = "Classifier(";
		for (int j = 0; j <digit.size(); j++) {
			 if (j==0)
				 name +=  digit.get(j);
			 else
				name += "," + digit.get(j);
		 
		}
		 name += ")";
		 if (type==Hierarchal){
		if(digitC2!=null)
		{
			name+="_Vs_(";
				for (int j = 0; j <digitC2.size(); j++) {
					if (j==0)
						name +=  digitC2.get(j);
					else 
					name += "," + digitC2.get(j);
			 
			}
				 name += ")";
		}
		 }
		 if(useRegions)
		 	for (int i = 0; i < regions.size(); i++) {
			name += " In " + regions.get(i).getRegionName();
		}
		

	}
	public static ArrayList<ClassifierData> ReadClassifiersDetails(String filename) {
		ArrayList<ClassifierData> dataArr =null;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename+".txt");
			Scanner input = new Scanner(new BufferedReader(
					new FileReader(afile)));
			String inputString = "", inputStringInner;
			int inputInt;
			int inputDouble;
			ClassifierData data;
			dataArr = new ArrayList<ClassifierData>();
			// boolean finishClassifier=false,finishRegion=false;

			while (input.hasNext()) {
				inputString = input.nextLine();
				// skip any ## comment line..
				if (inputString.startsWith("##"))
					continue;

				// now check for the follwing
				if (inputString.trim().startsWith("db")) {
					inputInt = input.nextInt();
				      AppDefaults.dbType = inputInt;
				}
				if (inputString.trim().startsWith("MaxNumber")) {
					inputInt = input.nextInt();
					AppDefaults.MaxNumberOfClassifier = inputInt;
				}
				if (inputString.trim().startsWith("Classifier")) {
					logger.info(" Reading a classsifer data  "+dataArr.size()+1);
					// now read the data of classifier
					data = new ClassifierData();
					// now each classifier
					while (input.hasNext() ) {
						inputStringInner = input.nextLine();
						// skip any ## comment line..
						if (inputStringInner.startsWith("##"))
							continue;
//						if (inputStringInner.trim().startsWith("Name")) {
//
//							data.setName(input.nextLine().trim());
//							
//						}
						if (inputStringInner.trim().startsWith("type")) {

							data.setType(input.nextInt());
						}
						if (inputStringInner.trim().startsWith("NumberTrainSamples")) {

							data. NumberTrainSamples=input.nextInt();
						}
						if (inputStringInner.trim().startsWith("useSplit")) {

							data.useSplit=input.nextBoolean();
						}
			

						if (inputStringInner.trim().startsWith("result")) {

							data.Accuracy = input.nextDouble();
						}
						if (inputStringInner.trim().startsWith("useProbability")){

							data.useProbability=input.nextBoolean();
//							
						}
						if (inputStringInner.trim().startsWith("useRegions")){

							data.useRegions=input.nextBoolean();
//							
						}
						if (inputStringInner.trim().startsWith("HierarchyKey")){
//							 public double noSamples=0;
//								public boolean useRegions=false;
//								public Integer HierarchyKey=null;
							data.HierarchyKey=new Integer(input.nextInt());
//							
						}
						
						if (inputStringInner.trim().startsWith("noSamples")){

							data.noSamples=input.nextDouble();
//							
						}
						
						

						if (inputStringInner.trim().startsWith("DS")) {
							ArrayList<Integer> d1 = new ArrayList<Integer>();
							String str = input.nextLine();

							String[] digits = str.trim().split(" ");

							for (int i = 0; i < digits.length; i++) {
								d1.add(Integer.parseInt(digits[i]));
							}
							if (inputStringInner.equals("DS1")) {
								data.digit = d1;
							} else {
								data.digitC2 = d1;
							}
						}
						if (inputStringInner.trim().startsWith("Regions")) {
							ArrayList<RegionCreater> tempR = new ArrayList<RegionCreater>();
							ArrayList<ArrayList<String>> tempF = new ArrayList<ArrayList<String>>();

							while (input.hasNext()) {
					//			logger.info("Reading Regionsss  "+tempR.size());
								String inputStr = input.nextLine();
								// skip any ## comment line..
								if (inputStr.startsWith("##"))
									continue;
								
								if(inputStr.trim().startsWith("Region"))
								{
									String stReg=input.nextLine();
									String[] values=stReg.trim().split(",");
									 
									if (values.length==4){
									int maxh=Integer.parseInt( values[0]);
									int maxv=Integer.parseInt( values[1]);
									int h=Integer.parseInt( values[2]);
									int v=Integer.parseInt( values[3]);
									 RegionCreater reg= RegionCreater.getRegion(maxh, maxv, h, v);
									 tempR.add(reg);
									}
								}
								
								if(inputStr.trim().startsWith("Feat"))
								{
									String st=input.nextLine();
									String[] features = st.trim().split(",");
									ArrayList<String> feat=new ArrayList<String>();
									for (int i = 0; i < features.length; i++) {
											feat.add( features[i]);
									}
								
								  tempF.add(feat);	
								}
								if(inputStr.trim().startsWith("Finish"))
								{
									break;
								}

							}// for the regionsss. 
							
							data.regions=tempR;
							data.feat=tempF;

						}

						
						if(inputStringInner.trim().startsWith("Finish"))
						{
							break;
						}
						
					}// while classifier 
                       data.createFeatureNameList();
                       data.createClassifierName();
                       logger.info("  Classifier no.  "+(dataArr.size()+1)+"  is  "+data.toStringDetailed());
					dataArr.add(data);
				}

				// finishClassifier=true;

				// GenearateTestFiles = input.nextInt();
				// Train =input.nextInt();
				// Test=input.nextInt();

			}
			
           
			input.close();
			

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		logger.info("Finished reading details.........");
		return dataArr;
	}

	public static void SaveClassifiersDetails(ArrayList<ClassifierData> ClassesData,
			String Filename,boolean sort) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			if(sort){
			Collections.sort(ClassesData,
			new Comparator<ClassifierData>() {
				    public int compare(ClassifierData o1, ClassifierData o2) {
				   return 	Double.compare( o1.Accuracy, o2.Accuracy);
				   //return (int) (o2.Accuracy- o1.Accuracy);
				        //return o2.size() - o1.size();
				    }
			 });
			}
			// Create a new file output stream
			file = new FileOutputStream(Filename+".txt");

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// wirte the type of database
			out.println("## This is a comment line ");
			out.println("## The First few Lines define the format");
			out.println("## The type of database === minist  "
					+ DataBaseConnector.MNIST + "or madbase "
					+ DataBaseConnector.MNIST);
			out.println("## Maximum number of classifiers..  ");
			out
					.println("## Now classifier Data  and actual number of classifier is "
							+ ClassesData.size());
			out.println("## Classifiers For each one write ");
			out.println("## Name  ");
			out.println("## Type (binary or multi class) ");
			out.println("## Digits DS1 (First digits set)");
			out.println("## Digits DS2 (Second digits set)");
			out.println("## Regions of the Classifier ");
			out.println("## Region maxh,maxv,h,v ");
			out.println("##--------------------------------- ");
			out.println("##The type of database ");
			out.println("db");
			out.println(AppDefaults.dbType);
			out.println("## Maximum number of classifiers..  ");
			out.println("MaxNumber");
			out.println(AppDefaults.MaxNumberOfClassifier);
			out
					.println("## Now classifier Data  and actual number of classifier is "
							+ ClassesData.size());
			ClassifierData temp;
			ArrayList<Integer> tempd;
			ArrayList<RegionCreater> tempR;
			ArrayList<ArrayList<String>> tempF;

			for (int i = 0; i < ClassesData.size(); i++) {
				out.println("#####================================================================");
				out.println("## Classifier  " + i);
				logger.info("writing classifier  "+i+"   "+ClassesData.get(i).toStringDetailed());
				// get the digit
				temp = ClassesData.get(i);
				out.println("Classifier");

				out.println("Name");
				out.println(temp.getName());
				out.println("type");
				out.println(temp.getType());
				out.println("## This is previous result  ");
				out.println("result");
				out.println(temp.Accuracy);
				out.println("## This is previous no of sample per classifier ");
				out.println("noSamples");
				out.println(temp.noSamples);
					out.println("NumberTrainSamples");
				out.println(temp.NumberTrainSamples);
				
				out.println("##   The hierarchy key that determine where to put classiifer.   ");
				out.println("HierarchyKey");
				out.println(temp.HierarchyKey);
				out.println("##   display and use distribution inform   ");
				out.println("useProbability");
				out.println(temp.useProbability);
				
 
				out.println("useSplit");
				out.println(temp.useSplit);
		
				
//				 public double noSamples=0;
//					public boolean useRegions=false;
//					public Integer HierarchyKey=null;
				out.println("##   Use regions or  all the image..  ");
				out.println("useRegions");
				out.println(temp.useRegions);
				
				out.println("## Digits " + i);

				out.println("DS1");
				tempd = temp.digit;

				for (int j = 0; j < tempd.size(); j++) {
					out.print(tempd.get(j));
					out.print(" ");
				}//digit 
				out.println();
				out.println("## Vs digits" + i);

				out.println("DS2");
				tempd = temp.digitC2;

				for (int j = 0; j < tempd.size(); j++) {
					out.print(tempd.get(j));
					out.print(" ");
				}//digit 
				out.println();
				// //////////////now the Regions and featurs..
				// first region...
				//
				out.println("## Regions of the Classifier ");
				 out.println("Regions");
				tempR = temp.regions;
				tempF = temp.feat;
              if (!temp.useRegions)
              {
            		out.println("##  THERE IS NO REGION IN THIS CLASSIIFERS SO CREATING ONE....");
            	  //just creating region to backword compability 
            	  tempR=new ArrayList<RegionCreater>();
            	  tempR.add(RegionCreater.getRegion(1, 1, 0, 0));
            	  
              }
				for (int j = 0; j < tempR.size(); j++) {
					out.println("## Region number " + j);
					out.println("## Region maxh,maxv,h,v ");
					out.println("Region");
					out.println(tempR.get(j).getMaxHorizontalRegion()
							+ "," + tempR.get(j).getMaxVerticalRegion() + ","
							+ tempR.get(j).getHorizonal() + ","
							+ tempR.get(j).getVertical());

					// out.println("R:"+j);
					// out.print(tempR.get(j).getMaxHorizontalRegion());
					// out.print(" ");
					// out.print(tempR.get(j).getMaxVerticalRegion());
					// out.print(" ");
					// out.print(tempR.get(j).getHorizonal());
					// out.print(" ");
					// out.print(tempR.get(j).getVertical());
					// out.print(" ");
					// out.println();
					out.println("## Now featurs for the region " + j);
					out.println("Feat");
					for (int j2 = 0; j2 < tempF.get(j).size(); j2++) {
						if (j2 > 0) {
							out.print(",");
						}
						out.print(tempF.get(j).get(j2));
					}//features 
					out.println();
					out.println("Finish");
				}// region
				
				
				
         
      
				out.println("Finish");
			
			}

			// String[] FeaturesSingle = featureST.split(",");
			//              
			// for (int i = 0; i < FeaturesSingle.length; i++) {
			//					
			// out.println(FeaturesSingle[i]);
			// out.println(i);
			//            	
			//             
			// }

			out.println("## This is The end of file.......");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in writing to file");
		}
		logger.info("Finished writing the properties..........");
	}
	
	public static void SaveClassifiersSummary(ArrayList<ClassifierData> ClassesData,
			String Filename,boolean sort,boolean hierarchyPrint) {

		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			

			// Create a new file output stream
			file = new FileOutputStream(Filename+"_Summary.txt");

			// Connect print stream to the output stream
			out = new PrintStream(file);

			// wirte the type of database
			out.println("## This is Summary file   ");
			out.print("## This the result of running  Classifierss  on ");
			
			
			if (AppDefaults.dbType==DataBaseConnector.MNIST){
				
				out.print("  MNIST Database ");
			}
			else if  (AppDefaults.dbType==DataBaseConnector.MADBASE){
				out.print("  MADBASE  Database ");
			}
			else {
				out.print("  ADBASE Database ");
			}
		 
			out.print(" The total Number of Classifiers is "+ClassesData.size());
			
			out.println();
			//this is hierarchal classifer 
			
			if (hierarchyPrint){
				//create a binar tree. 
				BinaryTreeWithKey< ClassifierData, Integer>  tree=new BinaryTreeWithKey<ClassifierData, Integer>();
				for (int i = 0; i < 	ClassesData.size(); i++) {
			
				tree.insert(			ClassesData.get(i), 			ClassesData.get(i).HierarchyKey);
				}
				tree.writeTree(0,out);
				
				tree.writeTree(1,out);
				
				
			}
			if(sort){
			Collections.sort(ClassesData,
			new Comparator<ClassifierData>() {
				    public int compare(ClassifierData o1, ClassifierData o2) {
				 	   return 	Double.compare( o1.Accuracy, o2.Accuracy);
				        //return o2.size() - o1.size();
				    }
			 });
			}
		
			 
			out.println("##---------------------------------------------------------------------------------------- ");
	 
			ClassifierData temp;
			ArrayList<Integer> tempd;
			ArrayList<RegionCreater> tempR;
			ArrayList<ArrayList<String>> tempF;

			for (int i = 0; i < ClassesData.size(); i++) {
				temp = ClassesData.get(i);
				logger.info("writing classifier  "+i+"   "+temp.toStringDetailed());
				// get the digit
				out.print("   Classifiier  "+i);
				out.print("   Result = "+temp.Accuracy+"  using "+temp.noSamples+"   With Details");
				out.print("   "+temp.getName());
				out.print("   HierarchyKey = "+  temp.HierarchyKey);
				out.print("   using Features  "+temp.FeautureSummary);
				out.println(" ");
			}
		 
			out.println("##---------------------------------------------------------------------------------------- ");
			 
	

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in writing to file");
		}
		logger.info("Finished writing the properties..........");
	}
	
	
}
