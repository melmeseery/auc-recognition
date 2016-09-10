/**
 *
 */
package data;

import io.MatHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import app.SystemSettings;

import com.jmatio.types.MLArray;
import com.jmatio.types.MLCell;
import com.jmatio.types.MLDouble;

/**
 * @author TOSHIBA
 *
 */
public class GeneraicDataSet<Ob extends DataObject,LabOb extends Label> extends Observable implements Cloneable, Serializable{

	public static final String FILE_INPUT_EXTENTION_ARFF = ".arff";
	public static final String FILE_INPUT_EXTENTION_CSV = ".csv";

	public static final String FILE_INPUT_EXTENTION_EXCELL = ".xls";
	public static final String FILE_INPUT_EXTENTION_LIBSVM = ".xt";
	public static final String FILE_INPUT_EXTENTION_TORCH = ".txt";
	public static final String FILE_INPUT_EXTENTION_MATLAB = ".mat";
	public static final int FILE_INPUT_FORMAT_ARFF = 3;
	public static final int FILE_INPUT_FORMAT_CSV = 1;

	public static final int FILE_INPUT_FORMAT_EXCELL = 4;
	public static final int FILE_INPUT_FORMAT_LIBSVM = 2;
	public static final int FILE_INPUT_FORMAT_TORCH =  0;
	public static final int FILE_INPUT_FORMAT_MATLAB = 5;
	private static transient final Logger logger = Logger
			.getLogger(DataSet.class);
	private static final long RandomSEED = 55;
	private  Ob EmptyObject ;
	private  LabOb EmptyLabel ;


	//protected HashMap _labelToType = null;
	// transient FeatureSet features=null;
	String AllFeatureString = "";


	private boolean ClassValuesGenerated = false;
	//transient Ob data[] = null;
	transient ArrayList<Ob> dataAdd = null;

	//LabOb dataTargets[] = null;
	transient ArrayList<LabOb> TargetsAdd = null;

	transient ArrayList<String> ClassLabels;

	ArrayList<Integer> ClassSamplesCount; // count of the number of sample in each
										// categories
	ArrayList<LabOb> ClassValues;

    transient ArrayList<Ob> trainData;
	transient ArrayList<LabOb> trainDataTargets;
	transient ArrayList<Ob> Validation;


	boolean FeatureDataSet = false;


	int format = FILE_INPUT_FORMAT_TORCH; // format to read
	protected int NumOfClasses = 2;

	// read and save file from/to torch format.
	// read and save file from/to libsvm format.
	// read and save file from/to arff format.
	int NumOfFeatures = 0;
	protected int NumOfSamples = 0;

	boolean RandomizeData = false;

	boolean shuffled = false;
	private double SplitPercent = 0.75;
	boolean StoreLocations = false;

	//private ArrayList<String> targetsString;



	protected int TrainSamples = 0;
	boolean twoClass = false;
	//boolean UseAllFeatures = true;

	// private boolean binaryClasses;

//	private boolean UseStringLabels = false;


	boolean ValidationCreated = false;

	protected int ValidationSamples = 0;
	transient ArrayList<LabOb> validationTargets;
	boolean valuesOk;
	private String formatExtention;
	public GeneraicDataSet(Ob obj, LabOb label){
         EmptyLabel=label;
         EmptyObject=obj;
	}

	public GeneraicDataSet(GeneraicDataSet tset) {

		this.ClassLabels = (ArrayList<String>) tset.ClassLabels.clone();
		this.ClassValues = (ArrayList<LabOb>) tset.ClassValues.clone();
		this.ClassSamplesCount = (ArrayList<Integer>) tset.ClassSamplesCount.clone();
		this.NumOfFeatures = tset.NumOfFeatures;
		this.NumOfSamples = tset.NumOfSamples;

	}

	public void addSample(Ob vals, LabOb i) {
		if (dataAdd == null) {
			dataAdd = new ArrayList<Ob>();
			TargetsAdd = new ArrayList<LabOb>();
		}
		dataAdd.add(vals);
		TargetsAdd.add(i);
		NumOfSamples = dataAdd.size();

	}
	public void addSamples(ArrayList<Ob> strokes,
			LabOb labels) {

		for (int i = 0; i < strokes.size(); i++) {
			 addSample(strokes.get(i), (LabOb) labels.clone());
		}


	}
	@Override
	protected Object clone() throws CloneNotSupportedException {

		GeneraicDataSet tempdata = null;
		tempdata = (GeneraicDataSet) super.clone();
		for (int i = 0; i < dataAdd.size(); i++) {
			tempdata.dataAdd.set(i, dataAdd.get(i).clone());
			tempdata.trainDataTargets.set(i,trainDataTargets.get(i).clone() );

		}

		for (int i = 0; i < ClassLabels.size(); i++) {
			tempdata.ClassLabels.set(i, new String(ClassLabels.get(i)));
			tempdata.ClassSamplesCount.set(i, new Integer(ClassSamplesCount.get(i)));
			tempdata.ClassValues.set(i,ClassValues.get(i).clone());
		}
		 tempdata.ValidationCreated=false;

		// tempdata.features= (FeatureSet) features.clone();
		// tempSolution.particlePoints=(int [])data.clone();
		// tempSolution.velocities=velocities.clone();

		// tempSolution.polygonVertices=(ArrayList<Point2D>)this.polygonVertices.clone();

		return tempdata;

	}

	public void doNoShuffle() {
		shuffled = true;

	}

	public void fixClassArray() {

		ClassLabels = new ArrayList<String>();
		ClassValues = new ArrayList<LabOb>();
		ClassSamplesCount = new ArrayList<Integer>();
		LabOb targ ;
		Integer Count;
		boolean found = false;
		if (dataAdd  == null) {
			return ;
		}
		for (int i = 0; i < TargetsAdd.size(); i++) {
			targ = TargetsAdd.get(i);
			found = false;
			for (int j = 0; j < ClassValues.size(); j++) {
				if (ClassValues.get(j).equals(targ)) {
					found = true;
					Count = ClassSamplesCount.get(j) + 1;
					ClassSamplesCount.set(j, Count);

					break;
				}
			}

			if (!found) {// not found in current classs values

					ClassValues.add(targ);
					ClassLabels.add("" + targ);
					ClassSamplesCount.add(new Integer(1));


			}

		}// for targets
		logger.trace(" There are " + this.NumOfClasses
				+ " classes in the data set ");
		for (int i = 0; i < ClassSamplesCount.size(); i++) {
			logger.trace("  the label " + ClassValues.get(i) + "  has "
					+ ClassSamplesCount.get(i) + "  sample ");
		}
		this.NumOfClasses = ClassValues.size();

		ClassValuesGenerated = true;

	}

	public ArrayList<LabOb> getAllTargets() {


		return this.TargetsAdd;
	}
	public ArrayList<Ob> getData(){

		return this.dataAdd;
	}
	// I do not see a use for this so i deprected...
	@Deprecated
	protected int getByValue(HashMap<Integer, String> map, String type) {

		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry<Integer, String> e = (Entry<Integer, String>) iterator.next();
			if (e.getValue().equals(type)) {
				return e.getKey();
			}

		}

		return -1;
	}

	private int getClassIndex(LabOb digitIndex) {
		int index = 0;
		for (int j = 0; j < ClassValues.size(); j++) {

			if (ClassValues.get(j).index()==digitIndex.index() ) {
				return j;
			}

		}

		return 0;
	}

	public ArrayList<String> getClassLabels() {
		return ClassLabels;
	}

	public ArrayList<LabOb> getClassValues() {
		return ClassValues;
	}


	public int getFormat() {
		return format;
	}

	public String getFormatString(int format) {
		if (format == FILE_INPUT_FORMAT_TORCH)
			return "Torch";
		if (format == FILE_INPUT_FORMAT_LIBSVM)
			return "LibSVM";
		if (format == FILE_INPUT_FORMAT_ARFF)
			return "Arff";
		if (format == FILE_INPUT_FORMAT_CSV)
			return "CSV";
		return "";
	}

	public int getNumOfSamples() {
		return NumOfSamples;
	}
	public Ob getSample(int i) {


		if (dataAdd!=null)

			return dataAdd.get(i);
		else
			return null;

		//return sample;

	}


	public LabOb getTarget(int i) {
		if (TargetsAdd == null)
			return this.TargetsAdd.get(i);
		return null;

	}

	public ArrayList<LabOb> getValidationsTargets() {
		ArrayList<LabOb> tar = new ArrayList<LabOb>();

		for (int i = TrainSamples; i < this.NumOfSamples; i++) {
			tar.add( (LabOb) TargetsAdd.get(i).clone());
		}
		return tar;
	}



	public boolean isRandomizeData() {
		return RandomizeData;
	}

	public GeneraicDataSet loadDataSet(String name) {

		// wirte other data...

		// unserialize the Queue

		try {
			FileInputStream fin = new FileInputStream(name + "_Serial.dat");
			ObjectInputStream ois = new ObjectInputStream(fin);
			GeneraicDataSet data = (GeneraicDataSet) ois.readObject();

			data.ReadFromFile(name);

			ois.close();

			return data;

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error(" No file ", e);

		}

		// logger.info(theQueue=.toString());

		return null;

	}



	public void setClassValues(ArrayList<LabOb> classValues) {
		ClassValues = classValues;
	}

	public void setFormat(int format) {
		this.format = format;
		switch (this.format) {
		case FILE_INPUT_FORMAT_ARFF:
			formatExtention=FILE_INPUT_EXTENTION_ARFF;
			break;

		case FILE_INPUT_FORMAT_CSV:
			formatExtention=FILE_INPUT_EXTENTION_CSV;
			break;

		case FILE_INPUT_FORMAT_EXCELL:
			formatExtention=FILE_INPUT_EXTENTION_EXCELL;
			break;


		case FILE_INPUT_FORMAT_LIBSVM:
			formatExtention=FILE_INPUT_EXTENTION_LIBSVM;
			break;


		case FILE_INPUT_FORMAT_MATLAB:
			formatExtention=FILE_INPUT_EXTENTION_MATLAB;
			break;
		case FILE_INPUT_FORMAT_TORCH:
			formatExtention=FILE_INPUT_EXTENTION_TORCH;
			break;

		default:
			formatExtention=FILE_INPUT_EXTENTION_ARFF;
			break;
		}
	}


//	public void SetOVATargets(int i) {
//
//		for (int j = 0; j < dataTargets.length; j++) {
//			if (dataTargets[j] == i) {
//
//				dataTargets[j] = 1;
//			} else {
//				dataTargets[j] = -1;
//			}
//		}
//
//	}

	public void setRandomizeData(boolean randomizeData) {
		RandomizeData = randomizeData;
	}
	public void ShuffleDataSet() {

		if (dataAdd == null) {

			return;

		}
		//Ob[] RandomData = new double[dataAdd.size()];
		//int[] RandomTarget = new int[data.length];


	      ArrayList<Ob> RandomData=new ArrayList<Ob>();
	   ArrayList<LabOb> RandomTarget=new ArrayList< LabOb>();




		// ArrayList<Integer>
		ArrayList<Integer> indeces = new ArrayList<Integer>(dataAdd.size());
		indeces.ensureCapacity(dataAdd.size());
		// logger.info("suffling   "+data.length+" data ... ");
		// logger.info( " their is  "+indeces.size());
		for (int i = 0; i < dataAdd.size(); i++) {
			indeces.add(new Integer(i));

			RandomData.add(dataAdd.get(i));
			RandomTarget.add(TargetsAdd.get(i));
		}
		Random r1Random = new Random(RandomSEED);
		int randomi;// =(int) Math.floor(Math.random()*indeces.size());
		int index;
		int j = 0;
		for (int i = 0; indeces.size() > 0; i++) {
			// logger.info("suffling index  i "+i+" using the");
			// if (j>NumOfSamples)
			// break;

			randomi = (int) Math.floor(r1Random.nextDouble() * indeces.size());
			// now move files from he input to the ouptut array .
			index = indeces.get(randomi);
			// logger.info("suffling index  i "+i+" using the "+"  with the new index "+index);
			// RandomSet.add( files.get(index));
			// for (int k = 0; k < data[index].length; k++) {
			// RandomData[j][k]=data[index][k];
			// }
			RandomData.set(j,(Ob) dataAdd.get(index).clone());
			RandomTarget.set(j,(LabOb) TargetsAdd.get(index).clone());
			j++;

			indeces.remove(randomi);

		}
		logger.info("shuffled  " + j + "  samples........");
		// if(indeces.size()>0)
		// {
		// for (int i = 0; i <indeces.size(); i++) {
		// index=indeces.get(i);
		// RandomData[i]=data[index];
		// RandomTarget[i]=dataTargets[index];
		// //RandomSet.add( files.get(index));
		// }
		// }
		// if (logger.isTraceEnabled()){
		//
		// for (int i = 0; i < 100 && i<RandomSet.size(); i++) {
		// logger.trace(" -- "+RandomSet.get(i));
		// }
		shuffled = true;
		// RandomData[j]=data[index];
		// for ( j = 0; j<NumOfSamples; j++) {
		//
		// data[j]=RandomData[j];
		// dataTargets[j]=RandomTarget[j];
		// }
//

		// RandomTarget[j]=dataTargets[index];

	}


	public ArrayList<Ob>  getDataByLabel(LabOb l){

		ArrayList<Ob> dataOfL=new ArrayList<Ob> ();
		if (TargetsAdd==null)
			return dataOfL;

		for (int i = 0; i < TargetsAdd.size(); i++) {

			//logger.info(" the target is "+TargetsAdd.get(i))
			if(TargetsAdd.get(i).equals(l)){

		    // logger.info("adding a values of data" +i);
				dataOfL.add(dataAdd.get(i));

			}



		}

		return dataOfL;

	}
	public void ReadFromFile(String filename) {
		//featuresNames = null;

		if (format == FILE_INPUT_FORMAT_TORCH) {
			ReadFromTorchFile(filename);

			logger.info(" Finished reading the file ..." + filename);

		}

		if (format == FILE_INPUT_FORMAT_LIBSVM) {
			//ReadFromLibSVMFile(filename);
			logger.info(" Finished reading the file ..." + filename);

		}

		if (format == FILE_INPUT_FORMAT_ARFF) {
			if (!filename.contains(".arff")) {
				filename += ".arff";

			}

			//ReadFromARFFFile(filename);
		}


		if (format == FILE_INPUT_FORMAT_MATLAB) {
			if (!filename.contains(".mat")) {
				filename += ".mat";

			}

			//ReadFromMATFile(filename);
		}
		// logger.info(" reading the feature file ");
		// initAllFeatures(filename);
		fixClassArray();

	}



	private void ReadFromTorchFile(String filename) {
		// String line;
		try {
			logger.info("reading the file................ wait");
			File afile = new File(filename);
			Scanner input = new Scanner(new BufferedReader( new FileReader(afile)));
			int linecount = 0;
			int readcount = 0;
			int j = 0;

			dataAdd=new ArrayList<Ob>();
			TargetsAdd=new ArrayList<LabOb>();


			while (input.hasNext()) {

				if (readcount == 0) {
					this.NumOfSamples = input.nextInt();
					readcount++;
					NumOfFeatures = input.nextInt() - 1;
					readcount++;

					logger.info(" the first time read = " + NumOfSamples + "  with " + NumOfFeatures + " features ");
					j = 0;

				} else {


					Ob obj= (Ob) EmptyObject.ReadObjectTxt(input,NumOfFeatures );
					dataAdd.add(obj);
					LabOb tar=(LabOb) EmptyLabel.ReadObjectTxt(input);
					TargetsAdd.add(tar);



					j++;

				}

				if (j > NumOfSamples) {
					break;
				}
				linecount++;

				if (linecount % 3000 == 0)
					logger.info(" reading line n " + linecount);

				// this statement reads the line from the file and print it to
				// the console.
				// logger.info(line);
			}

			// dispose all the resources after using them.
			input.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// catch (IOException e) {
		//
		// e.printStackTrace();
		// }


	}

	public void SaveToFile(String filename) {
		logger.info(" Saving file " + filename);


		if (ClassValuesGenerated == false) {
			fixClassArray();

		}
		if (format == FILE_INPUT_FORMAT_EXCELL) {
		// /
				// this.SaveToxlsFile(filename);
			//}
		}
		if (format == FILE_INPUT_FORMAT_TORCH) {

			//	this.SaveToTorchFile(filename);

		}
		else if (format == FILE_INPUT_FORMAT_MATLAB) {


			 SaveToMatFile(filename);


	}
		else if (format == FILE_INPUT_FORMAT_LIBSVM) {


				//this.SaveToLibSVMFile(filename);


		} else if (format == FILE_INPUT_FORMAT_ARFF) {

				if (!filename.contains(".arff")) {
					filename += ".arff";

				}

				SaveToARFFFile(filename);
			}



	}

	private void SaveToARFFFile(String filename) {
		/*******
		 *
		 *
		 * @RELATION DigitsClassifier
		 * @attribute 'Class' numeric
		 * @attribute '1' numeric
		 * @attribute '2' numeric
		 * @attribute '3' numeric
		 * @attribute '4' numeric
		 * @attribute '5' numeric
		 * @attribute '6' numeric
		 * @attribute '7' numeric
		 * @attribute '8' numeric
		 * @attribute '9' numeric
		 * @attribute '10' numeric
		 * @data 1 , 26 , 0 , 1 , 0 , 0 , 5 , 0 , 0 , 13.6667 , 6 1 , 80 , 0 , 1
		 *       , 1 , 0 , 7 , 0 , 0 , 6 , 9.66667 1 , 129 , 0 , 1 , 1 , 0 , 6 ,
		 *       0 , 0 , 7.33333 , 14.666
		 *
		 * ****/
		logger.debug("  In the same file add ng th arff extentions.... ");
		FileOutputStream file;
		PrintStream out; // declare a print stream object
		try {
			// Create a new file output stream
			file = new FileOutputStream(filename);

			// Connect print stream to the output stream
			out = new PrintStream(file);
			out.println(" @RELATION    "+getRelationName(filename));
			out.println("");

			//ArrayList<String> featemp = featuresNames;

			for (int i = 0; i < this.NumOfFeatures; i++) {

				out.println(" @attribute   '" + "F_"+i + "'  numeric  ");

			}

			String Classes = "";
			Classes += " {";
			for (int i = 0; i < ClassValues.size(); i++) {

				Classes += ClassValues.get(i);
				if ((i + 1) < ClassValues.size()) {
					Classes += ",";

				} else {
					Classes += "}";
				}

			}
			if (Classes.equals("")) {

				Classes = " numeric ";
			}

			out.println(" @attribute 'class'  " + Classes);

			out.println("");
			out.println("@data");
			// out.println ( this.NumOfSamples +" "+ (this.NumOfFeatures+1));
			double dataw;
			logger.info("Start writing ............................"
					+ this.NumOfSamples + "  samples");
			for (int i = 0; i < this.NumOfSamples; i++) {

				// out.print("  ");
//				for (int j = 0; j < this.NumOfFeatures; j++) {
//
//					if (Double.isNaN(data[i][j])) {
//						logger.error("NANA ins SAMPLE " + i + "  FEATURE  " + j);
//						dataw = 0.0;
//					} else
//						dataw = data[i][j];
//
//					out.print(dataw);
//
//					// }
//
//					out.print(",");
//					out.print("  ");
//				}s

				dataAdd.get(i).write(out);
//				out.print(",");
//				out.print("  ");
				TargetsAdd.get(i).write(out);

				//out.print(TargetsAdd.get(i));
				out.println("");

				if (i % 2000 == 0) {

					out.flush();
					logger.info(" writing samples number " + i);
				}

			}
			out.close();
			logger.info("  total number of lines read  =  " + this.NumOfSamples
					+ "  each has " + NumOfFeatures + "  feature");
		} catch (Exception e) {
			System.err.println("Error in writing to file");
			logger.error(e.getMessage(), e.getCause());
		}

	}

	private String getRelationName(String filename) {


		int i=filename.lastIndexOf(SystemSettings.CurrentOSSeperator);
		if (i>0){

			return filename.substring(i);
		}

		return "DigitsClassifier";

	}

	private void SaveToMatFile(String filename) {
		MatHandler mat;
		//MLCell cell;

		mat=new MatHandler();

		LabOb l;
		ArrayList<Ob> data;
		int[] dimsClass=new int[2];
	    dimsClass[0]= 1;
	    dimsClass[1]=ClassValues.size();
	    MLCell cellOut=new MLCell("Data", dimsClass);

	    ArrayList<Integer> ind=new ArrayList<Integer>();

		for (int i = 0; i < ClassValues.size(); i++) {
			l=ClassValues.get(i);
			data=getDataByLabel(l);
			logger.info("the data in "+i+" category is "+data.size());
             ind.add(l.getIntValue());
		    logger.info("  dmiss is "+dimsClass);



    		int[] dimsCat=new int[2];
    	    dimsCat[0]= data.size();
    	    dimsCat[1]=1;

            MLCell cellInner=new MLCell("Category"+i, dimsCat);

           MLArray arr;
         //  logger.trace("the size of strokes. is "+data.size()+"  cell size is  "+cellInner.getSize());
           for (int j = 0; j < data.size(); j++) {
        // 	  logger.trace(" j "+j+"  index is "+ cellInner.getIndex(j,1));
         	  arr=new MLDouble ("S"+j,data.get(j).getDataArray())  ;
         	 cellInner.set(arr,j);

 		}

           cellOut.set(cellInner, i);


		}



         mat.addCellArray(cellOut);
         mat.addDoubleArray("Sizes", ClassSamplesCount);

         mat.addDoubleArray("indeces",ind);
         mat.addStringArray("Labels",ClassLabels);

		 mat.writeFile(filename);
	}

	public void SaveToFileAll(String name) {
		SaveToFile(name);

		// write object....

		try {
			FileOutputStream fout = new FileOutputStream(name + "_Serial.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);

			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public String getFormatExtention() {

		return formatExtention;
	}

	public void setFormat(String name) {


		if (name.equals(FILE_INPUT_EXTENTION_ARFF))
			setFormat(FILE_INPUT_FORMAT_ARFF);

		if(name.equals(FILE_INPUT_EXTENTION_MATLAB))
			setFormat(FILE_INPUT_FORMAT_MATLAB);

		if(name.equals(FILE_INPUT_EXTENTION_CSV))
			setFormat(FILE_INPUT_FORMAT_CSV);

		if(name.equals(FILE_INPUT_EXTENTION_EXCELL))
			setFormat(FILE_INPUT_FORMAT_EXCELL);


		if(name.equals(FILE_INPUT_EXTENTION_TORCH))
			setFormat(FILE_INPUT_FORMAT_TORCH);

			if(name.equals(FILE_INPUT_EXTENTION_LIBSVM))
			setFormat(FILE_INPUT_FORMAT_LIBSVM);




	}





}
