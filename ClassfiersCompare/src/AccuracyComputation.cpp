/*
 * AccuracyComputation.cpp
 *
 *  Created on: Feb 5, 2009
 *      Author: Maha
 */

#include "AccuracyComputation.h"

AccuracyComputation::AccuracyComputation() {
	// TODO Auto-generated constructor stub

}

AccuracyComputation::~AccuracyComputation() {
	// TODO Auto-generated destructor stub
}
 void AccuracyComputation::computeStat(){

			 TotalCount=SamplesClass1+ SamplesOthers;
			FalsePositiveRate=((double)FalsePositive)/(double)(FalsePositive+TrueNegative);

			FalseNegativeRate=((double)FalseNegative)/(double)(TruePositive+FalseNegative);

			specificity=((double)TrueNegative)/(double)(FalsePositive+TrueNegative);

			sensitivity=((double)TruePositive)/(double)(TruePositive+FalseNegative);
			Positivepredictivevalue=((double)TruePositive)/(double)(TruePositive+FalsePositive);


			Negativepredictivevalue=((double)TrueNegative)/(double)(FalseNegative+TrueNegative);

			Accuracy=((double)(TrueNegative+TruePositive))/(double)(FalseNegative+TrueNegative+TruePositive+FalsePositive);

			Power=sensitivity;
			Recall=sensitivity;
			Percision=Positivepredictivevalue;

			Fmeasure=(double)2*((double)(Recall*Percision))/((double)(Recall+Percision));




		}
