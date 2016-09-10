/*
 * AccuracyComputation.h
 *
 *  Created on: Feb 5, 2009
 *      Author: Maha
 */

#ifndef ACCURACYCOMPUTATION_H_
#define ACCURACYCOMPUTATION_H_

class AccuracyComputation {

	//here is the table to define the persion
	/****
	 *                             The Required result
	 *                    | Current class (True)(Sick) | others(False)(healthy)|
	 * Test outcome
	 *    (sick) Positive |    True Positive           | False Positive        |  Positive predictive value
	 * (healthy)  Negtive |    False Negative          | True Negative         |  Negative predictive value
	 *                           Sensitivity           |  Specificity           | Accuracy
	 *
True positive: Sick people correctly diagnosed as sick
False positive: Healthy people wrongly identified as sick
True negative: Healthy people correctly identified as healthy
False negative: Sick people wrongly identified as healthy
	 * **/


public : double TruePositive; //  TP it is supposed of this class and recognized as is

	  double TrueNegative; // TN it is supposed not of this class and recognized as not

	 double  FalseNegative ;  // FN it is supposed of this class and recognized as not

  double FalsePositive; // FP it is supposed not of this class and recognized as is

//	String ClassName= "Class one ";
//	String OtherClassName="Class two";

	int SamplesClass1;
	int SamplesOthers;

	double FalsePositiveRate; // (alpha) = FP / (FP + TN) =   1 - specificity
	double FalseNegativeRate;//B  = FN / (TP + FN) = 1 - sensitivity
	double specificity; // TN / (FP + TN)
	double sensitivity ;//TP / (TP + FN)
	 double Positivepredictivevalue;// TP / (TP + FP)


	 double Negativepredictivevalue ;//TN / (TN + FN)
	double Accuracy; //  ((TP+TN)/ TotalCount)
	 double Power; // power = sensitivity = 1 - B

	 int TotalCount;   //  SamplesClass1+SamplesOthers ==> count of all samples in the test.

	 //In information retrieval positive predictive value is called precision, and sensitivity is called recall.
		double Recall; //sensitivity ;//TP / (TP + FN)
		double Percision;// Positivepredictivevalue=TP / (TP + FP)

		double Fmeasure;//F-measure=2*(Percision*recall)/(Percision+recall)
public:
	AccuracyComputation();

	virtual ~AccuracyComputation();

 void computeStat();



};

#endif /* ACCURACYCOMPUTATION_H_ */
