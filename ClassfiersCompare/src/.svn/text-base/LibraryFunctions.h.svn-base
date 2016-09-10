/*
 * LibraryFunctions.h
 *
 *  Created on: Jan 7, 2009
 *      Author: Maha
 */

#ifndef LIBRARYFUNCTIONS_H_
#define LIBRARYFUNCTIONS_H_
#include <math.h>
#include <string.h>

#include<vector>
#include <stdio.h>
#include <stdlib.h>

#include<fstream>
#include<iostream>
#include <string.h>
#include <sstream>


#include "Timer.h"
#define debugLog true

using namespace std;
class LibraryFunctions {

private :
	  string* myStrings ;
 			int size;
public:

	LibraryFunctions();
	virtual ~LibraryFunctions();
	static int getFeatureCount(char* filename);
		 string getFile(int t);
		  void getFiles(char* filename);
	static	void getMax(real* arr, long n_arr, real &max_value, long &max_index);
	 string* getMyStrings();
		  int getSize();
		  static void getMin(real* arr, long n_arr, real &min_value, long &min_index);
};

#endif /* LIBRARYFUNCTIONS_H_ */
