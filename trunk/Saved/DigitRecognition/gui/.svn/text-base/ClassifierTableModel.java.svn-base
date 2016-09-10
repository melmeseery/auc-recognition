/**
 * 
 */
package gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import applications.RunWekaClassifier;

import classifiers.MultiFeature.ClassifierData;


/**
 * @author TOSHIBA
 *DefaultTableModel{//
 */
public class ClassifierTableModel extends AbstractTableModel {
	private static transient final Logger logger = Logger.getLogger(  ClassifierTableModel  .class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames={ "Name","First Class","Second Class","Features","Use Region","Regions","Type","Hierarcal Key","Previous Result"};// = ...//same as before...
    private ArrayList <ClassifierData> data;//same as before...

    public ClassifierTableModel(){
    	
    	data=new ArrayList<ClassifierData>();
    	
    }
    
    public void setTableNames(String [] Names){
    	columnNames=Names;
    }
    
    public int getColumnCount() {
     
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    public ClassifierData getRow(int row){
    	if (row==-1)
    		return new ClassifierData();
    	
    	return data.get(row);
    	
    }
 public void removeRow(int row){
	 data.remove(row);
	 fireTableRowsDeleted(row, row);
 }
    public void  setRow (int row, ClassifierData cal){
    	logger.info(" i am  updatina a row i am already seletec.. ");
    	data.set(row, cal);
    	fireTableDataChanged();
    	//fireTableRowsUpdated(row-1, row+1);
    	
    }
    public void addClassifier(ClassifierData cal){
    	int size=data.size();
    	data.add(cal);
    	logger.info(" I am adding a an new data to table  "+cal);
       fireTableRowsInserted(size-1, size);
    
    }
    
    @Override
    public Object getValueAt(int row, int col) {
    	//return 
    	ClassifierData temp=data.get(row);
    	String out="";
    	//get Column name 
          //it is part of the classdat 

			if (columnNames[col].equals("Name"))
			{  return temp.getName();
			}

			if (columnNames[col].equals("First Class"))
			{
				String digits=temp.digit+"";
				
	    		  return digits;
			}
	 
	 
			if (columnNames[col].equals("Second Class"))
			{
				String digits=temp.digitC2+"";
				
	    		  return digits;
			}	
 
			if (columnNames[col].equals("Features"))
			{
				 
				
	    		  return temp.FeautureSummary;
			}	
		 
			if (columnNames[col].equals("Use Region"))
			{
				 
				
	    		  return temp.useRegions;
			}	
	 
			if (columnNames[col].equals("Type"))
			{
				if (temp.getType()==ClassifierData.Binary)
				{
					return "Binary";
				}
				if (temp.getType()==ClassifierData.MultiClass)
				{
					return "MultiClass";
				}
				else 
				{
					 
						return "Hierarchal"; 
					 
				}
				 
				
	    		  
			}
			if (columnNames[col].equals("Hierarcal Key"))
			{
				 
				
	    		  return temp.HierarchyKey;
			}
			if (columnNames[col].equals("Previous Result"))
			{
				 
				
	    		  return temp.Accuracy;
			}
    		
    		
    		
        return  0;

    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
//        if (col < 2) {
//            return false;
//        } else {
//            return true;
//        }
    	return false;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
    	ClassifierData temp=data.get(row);
    	
//		if (columnNames[col].equals("Name"))
//		{  return temp.getName();
//		}

//		if (columnNames[col].equals("First Class"))
//		{
//			String digits=temp.digit+"";
//			
//    		  return digits;
//		}
// 
// 
//		if (columnNames[col].equals("Second Class"))
//		{
//			String digits=temp.digitC2+"";
//			
//    		  return digits;
//		}	

		if (columnNames[col].equals("Features"))
		{
			 
			
    		  temp.FeautureSummary=(String) value;
		}	
	 
		if (columnNames[col].equals("Use Region"))
		{
			 
			
    		  temp.useRegions=(Boolean) value;
		}	
 
		if (columnNames[col].equals("Type"))
		{
//			if (temp.getType()==ClassifierData.Binary)
//			{
//				return "Binary";
//			}
//			if (temp.getType()==ClassifierData.MultiClass)
//			{
//				return "MultiClass";
//			}
//			else 
//			{
//				 
//					return "Hierarchal"; 
//				 
//			}
			 
			
    		  
		}
		if (columnNames[col].equals("Hierarcal Key"))
		{
			 
			
    		   temp.HierarchyKey=(Integer) value;
		}
		if (columnNames[col].equals("Previous Result"))
		{
			 
			
    		   temp.Accuracy=(Double) value;
		}
    	

        fireTableCellUpdated(row, col);
    }

	public void setData(ArrayList<ClassifierData> temp) {
		data=temp;
		fireTableDataChanged();
		
	}

	public ArrayList<ClassifierData> getData() {
		 
		return data;
	}



}
