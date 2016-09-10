/**
 *
 */
package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;



/**
 * @author TOSHIBA
 *
 */
public class StrokeGroupTableModel extends AbstractTableModel {
	private static transient final Logger logger = Logger.getLogger( StrokeGroupTableModel  .class);
	int colCount=5;

	private String[] columnNames={ "Id","Color","isAccepted","Label","Stroke Count","Box"};// = ...//same as before...



	ArrayList<StrokeGroup> list=new	ArrayList<StrokeGroup> ();



    public void setTableNames(String [] Names){
    	columnNames=Names;
    }

    public ArrayList<StrokeGroup> getList() {
		return list;
	}

	public void setList(ArrayList<StrokeGroup> list) {
		this.list = list;
		fireTableDataChanged();
	}

	public int getColumnCount() {

        return columnNames.length;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public StrokeGroup getRow(int row){
    	if (row==-1)
    		return new StrokeGroup();

    	return list.get(row);

    }
    public void removeRow(int row){
   	 list.remove(row);
   	 fireTableRowsDeleted(row, row);
    }


    public void  setRow (int row, StrokeGroup cal){
    	//logger.info(" i am  updatina a row i am already seletec.. ");
    	list.set(row, cal);
    	fireTableDataChanged();
    	//fireTableRowsUpdated(row-1, row+1);

    }


    public void addStrokeGroup(StrokeGroup cal){


    	int size=list.size();

    	boolean notFound=true;
    	for (int i = 0; i < list.size(); i++) {

    		if (cal.SameLabel(list.get(i))){
                notFound=false;
                 // same list then
                list.get(i).merge(cal);
    		}

		}
		if (notFound){
		    	list.add(cal);
		}
    	logger.info(" I am adding a an new data to table  "+cal);
       fireTableRowsInserted(size-1, size);

    }
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
//	@Override
//	public int getColumnCount() {
//
//		return colCount;
//	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {

		return list.size();
	}

	 public   void paint(Graphics2D g, double scalex, double scaley, double h){

        for (int i = 0; i < list.size(); i++) {
        	logger.info("  drawing group no. .  "+i);
			list.get(i).paint(g,scalex,scaley, h);
		}
	 }
	@Override
	public Object getValueAt(int rowIndex, int col) {

	 StrokeGroup temp=list.get(rowIndex);
	 //logger.info("  the temp is .... "+temp );

	 //"Id","Color","isAccepted","","Label","Stroke Count","Box"
		//if (columnNames[col].equals("Id"))
		if (col==0)
		{  return temp.getId();
		}
		if (col==1)
		{
			return temp.getC();

    		  //return digits;
		}
		//if (columnNames[col].equals("isAccepted"))
		if (col==2)
		{
			return temp.getAccepted();

    		  //return digits;
		}

		if (col==3)
		//if (columnNames[col].equals("Label"))
		{


    		  return temp.getLabel();
		}
		if (col==4)
		//if (columnNames[col].equals("Stroke Count"))
		{


    		  return new Integer(temp.getStrokes().size());
		}

		if (col==5)
		//if (columnNames[col].equals("Box"))
		{


    		  return " x= "+temp.getBox().getCenterX()+" , y= "+temp.getBox().getCenterY();
		}

		return new Integer(0);



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

    	if (columnNames[col].equals("Label"))
		{

    		return true;
		}
    	else if (columnNames[col].equals("isAccepted"))
    		return true;
    	else
    	return false;

    }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {


       	 StrokeGroup temp=list.get(row);
       	 switch (col) {


//     	case 1: "Id"  0  ,"Color" 1 ,"isAccepted" 2 ,"","Label",
//     	     temp.setId(value);
//     		break;
     	case 2:
     		 temp.setAccepted((Boolean) value);
     		break;
     	case 1:
    		 temp.setC((Color) value);
    		break;
     	case 3:
   		 temp.setLabel(value.toString());
   		break;

     	default:
     		break;
     	}



            fireTableCellUpdated(row, col);
        }

        public Class getColumnClass(int c) {
        //	logger.info("column   c   " +c+"   value ... "+getValueAt(0, c));

            return getValueAt(0, c).getClass();
        }


}
