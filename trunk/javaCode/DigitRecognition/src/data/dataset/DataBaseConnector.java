package data.dataset;

public abstract class DataBaseConnector {

	
	public  static final int MADBASE=0;
	public  static final int ADBASE=1;
	public  static final int MNIST=2;

	
	
	public static final int TRAIN=0;
	public static final int VALIDATE_TRAIN=5;
	public static final int VALIDATE_TEST=6;
	//public static final int VALIDATE=1;
	public static final int TEST=2;
	
	public String ADbaseBaseDir="D:\\AUC\\Databases\\Arabic Digits Databases\\AHDBase\\";
	public static  String MADbaseBaseDir="D:\\AUC\\Databases\\Arabic Digits Databases\\MAHDBase\\";
	public static   String MNISTbaseBaseDir="D:\\AUC\\Databases\\MNIST\\";
	
	
	public String ADbaseBaseLinuxDir="/windows/D/AUC/Databases/Arabic Digits Databases/AHDBase/";
	public static  String MADbaseBaseLinuxDir="/windows/D/AUC/Databases/Arabic Digits Databases/MAHDBase/";
	public static   String MNISTbaseBaseLinuxDir="/windows/D/AUC/Databases/MNIST/";
	
	
	 public static final int OS_LINUX=0;
	 
	 public static final int OS_WINDOWS=1;
	 
	public static int OS;//=OS_LINUX;
	
	public String DefaultSeperator=SEPERATOR_LINUX;
	
	protected static final String SEPERATOR_WINDOWS="\\";
	protected static final String SEPERATOR_LINUX="/";	
			
	protected static String curDir="";
	double ValidationPercent=0.8; 
	


	 
	protected   int Status=TRAIN;
	

	public int DatabaseType=MADBASE;
	
	public void setOS(int os){
		
		OS=os;
		
	}
	
	 public void setDataBaseType(int db){
			DatabaseType=db;
			
		}
		
		
		public  int getStatus() {
			return Status;
		}

		public void setStatus(int status) {
			Status = status;
		}
	 public abstract String getFullPath(String filename);
	 
		protected void setCurrentDatabaseDir(){
			if (OS==OS_WINDOWS)
			{
			switch (DatabaseType) {
			case MADBASE:
				 curDir=MADbaseBaseDir;
				break;
			case MNIST:
				 curDir=MNISTbaseBaseDir;
				break;
				
			case  ADBASE:
				curDir=ADbaseBaseDir ;
				break;
			default:
				curDir=MADbaseBaseDir;
				break;
			}
			DefaultSeperator=SEPERATOR_WINDOWS;
			
			
			}else {
				
				
				
				switch (DatabaseType) {
				case MADBASE:
					 curDir=MADbaseBaseLinuxDir;
					break;
				case MNIST:
					 curDir=MNISTbaseBaseLinuxDir;
					break;
					
				case  ADBASE:
					curDir=ADbaseBaseLinuxDir ;
					break;
				default:
					curDir=MADbaseBaseLinuxDir;
					break;
				}
				
				DefaultSeperator=SEPERATOR_LINUX;
				
			}
			
		}
		public void setDataBaseDir(String dir)
		{
			switch (DatabaseType) {
			case MADBASE:
				MADbaseBaseDir=dir;
				break;
			case MNIST:
				MNISTbaseBaseDir=dir;
				break;
				
			case  ADBASE:
				ADbaseBaseDir=dir;
				break;
			default:
				MADbaseBaseDir=dir;
				break;
			}
		//	this.curDir=dir;
			
			
		}
	
}
