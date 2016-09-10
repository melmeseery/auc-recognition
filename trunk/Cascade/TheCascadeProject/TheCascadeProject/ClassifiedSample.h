#pragma once
//This class represents a sample in some dataset classified by some classifier. 
//A sample is fully specified by its index (order) in the dataset, the score given to it by the classifier,
//and wether it is falsely classified.

class ClassifiedSample
{
public:
	ClassifiedSample(void);
public:
	~ClassifiedSample(void);
		//the sample index(order) in the dataset
	int index;

	//the score given to it by the classifier
	float score;

	//wether the sample is falsely classified or not
	float err;
	
};
//a class function used with sort algorithm to sort a vector of samples in a descending order according to their scores
class score_descend_compare_samples
{
public:
	bool operator()(const ClassifiedSample* obj1, const ClassifiedSample* obj2)
	{return(obj1->score>obj2->score);} //descending order

	bool operator()(const ClassifiedSample obj1, const ClassifiedSample obj2)
	{return(obj1.score>obj2.score);} //descending order

};

//a class function used with sort algorithm to sort a vector of samples in an ascending order according to their scores
class score_ascend_compare_samples
{
public:
	bool operator()(const ClassifiedSample* obj1, const ClassifiedSample* obj2)
	{return(obj1->score<obj2->score);} //ascending order

	bool operator()(const ClassifiedSample obj1, const ClassifiedSample obj2)
	{return(obj1.score<obj2.score);} //ascending order

};

class index_descend_compare_samples
{
public:
	bool operator()(const ClassifiedSample* obj1, const ClassifiedSample* obj2)
	{return(obj1->index>obj2->index);} //descending order

	bool operator()(const ClassifiedSample obj1, const ClassifiedSample obj2)
	{return(obj1.index>obj2.index);} //descending order

};

class index_ascend_compare_samples
{
public:
	bool operator()(const ClassifiedSample* obj1, const ClassifiedSample* obj2)
	{return(obj1->index<obj2->index);} //ascending order

	bool operator()(const ClassifiedSample obj1, const ClassifiedSample obj2)
	{return(obj1.index<obj2.index);} //ascending order

};