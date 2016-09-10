/*
 * LibraryFunctions.cpp
 *
 *  Created on: Jan 7, 2009
 *      Author: Maha
 */

#include "LibraryFunctions.h"

LibraryFunctions::LibraryFunctions() {
	// TODO Auto-generated constructor stub

}

LibraryFunctions::~LibraryFunctions() {
	// TODO Auto-generated destructor stub
}

string* LibraryFunctions::getMyStrings(){

	return LibraryFunctions::myStrings;
}
		int LibraryFunctions::getSize(){
			return size;
			}
string LibraryFunctions::getFile(int t) {

	if (LibraryFunctions::myStrings == NULL) {

		// do the old stuff
		char filename[50];
		int writer, pass, digit;

		writer = t / 100;
		pass = (t - (writer * 100)) / 10;
		digit = (t - (writer * 100)) % 10;
		sprintf(filename, "writer%03u_pass%02u_digit%u.bmp", 601 + writer, 1
				+ pass, digit);
		string s = filename;
		return s;

	} else {

		return LibraryFunctions::myStrings[t];

	}

}
void LibraryFunctions::getFiles(char* filename) {

	ifstream inFile;
	// inFile.open("files_names.txt");
	inFile.open(filename);
	if (!inFile) {
		cout << "Unable to open file" << filename << endl;
		return;
		// exit(1); // terminate with error
	}
	char x[500];

	//int size;
	int i = 0;
	if (inFile >>  size) {
		cout << "Size of file " <<  size << endl;
		myStrings = new string[ size];

		while (inFile >> x) {

			myStrings[i] = x;
			if (i % 400 == 0)
				cout << " reading " << LibraryFunctions::myStrings[i].data() << endl;
			i++;
		}
	}
	inFile.close();
	cout << "Size of file " <<  size << endl;
	// cout << "Sum = " << sum << endl;

}
void LibraryFunctions::getMax(real* arr, long n_arr, real &max_value,
		long &max_index) {
	max_index = 0;
	max_value = arr[0];
	for (int i = 1; i < n_arr; i++) {
		if (arr[i] > max_value) {
			max_index = i;
			max_value = arr[i];
		}
	}
}
void LibraryFunctions::getMin(real* arr, long n_arr, real &min_value, long &min_index)
{
	min_index=0;
	min_value= arr[0];
	for(int i=1; i<n_arr; i++)
	{
		if(arr[i]<min_value)
		{
			min_index =i;
			min_value =arr[i];
		}
	}
}

int LibraryFunctions::getFeatureCount(char* filename) {
	ifstream inFile;
	inFile.open(filename);
	if (!inFile) {
		cout << "Unable to open file" << filename << endl;
		exit(1); // terminate with error
	}
	char x[500];

	int ssize;
	int i = 0;
	if (inFile >> ssize) {
		cout << "number of sampels  " << ssize << endl;

	}
	int nCount = 0;
	if (inFile >> nCount) {

		cout << "   feature count " << nCount << endl;

	}
	inFile.close();
	//cout<<"Size of file "<<size<<endl;
	// cout << "Sum = " << sum << endl;
	return (nCount - 1);
}
