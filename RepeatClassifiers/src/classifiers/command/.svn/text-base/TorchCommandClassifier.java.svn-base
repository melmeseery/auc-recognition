/**
 * 
 */
package classifiers.command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;


/**
 * @author Maha
 *
 */
public class TorchCommandClassifier extends CommandClassifier {
	private static transient final Logger logger = Logger.getLogger( TorchCommandClassifier.class);
	Logger logCompare = Logger.getLogger("TorchLog");
	
	public TorchCommandClassifier(){
		
		this.TrainCommand="ClassfiersCompare -train ";
		this.TestCommand="ClassfiersCompare -test ";
	}
	/* (non-Javadoc)
	 * @see classifiers.CommandClassifier#CrossValidate(java.lang.String)
	 */
	@Override
	public double CrossValidate(String filename) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void SetOptions(String[] names, double[] options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Test(String filename, String modelfile, String perdictFilename) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Runtime r = Runtime.getRuntime();
		Process p = null;

		try {

			String s = this.TestCommand + "  "+filename;
			if (modelfile != null)
				s += "  -model " + modelfile;
            if(perdictFilename!=null)
            	s+= "  -per " + perdictFilename;
			 

			s += "  " + this.OptionString;

		 logger.info(s);

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			BufferedReader inputerr = new BufferedReader(new InputStreamReader(err));
			String line;
			boolean flag = false;
			int count1 = 0;
			int count2 = 0;
			String TheSummary = "[Summary]";
			while ((line = input.readLine()) != null) {

				logger.info(line);

				if (TheSummary.equals(line))
				// /then next time
				{
					// System.out.println(" line  ===== "+line);
					// count1++;
					// System.out.println("count1 ="+count1);
					// change the flag...........
					flag = !flag;// true;

				}
				if (flag) {

					// count2++;
					// System.out.println("count2 ="+count2);
					logCompare.info(line);
				}

			}
			
			while ((line = inputerr.readLine()) != null ){
				
				logger.error(line);
				
			}
			
			input.close();
			out.write(4);

		} catch (Exception e) {
			logger.error("error===" + e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Override
	public void Train(String filename, String modelfile) {
		// TODO Auto-generated method stub
		Runtime r = Runtime.getRuntime();
		Process p = null;

		try {

			String s = this.TrainCommand + "  "+filename;
			if (modelfile != null)
				s += "  -model " + modelfile;

			 

			s += "  " + this.OptionString;

		 logger.info(s);

			p = r.exec(s);

			InputStream in = p.getInputStream();
			OutputStream out = p.getOutputStream();
			InputStream err = p.getErrorStream();

			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			BufferedReader inputerr = new BufferedReader(new InputStreamReader(err));
			String line;
			boolean flag = false;
			int count1 = 0;
			int count2 = 0;
			String TheSummary = "[Summary]";
			while ((line = input.readLine()) != null) {

				logger.info(line);

				if (TheSummary.equals(line))
				// /then next time
				{
					// System.out.println(" line  ===== "+line);
					// count1++;
					// System.out.println("count1 ="+count1);
					// change the flag...........
					flag = !flag;// true;

				}
				if (flag) {

					// count2++;
					// System.out.println("count2 ="+count2);
					logCompare.info(line);
				}

			}
			
			while ((line = inputerr.readLine()) != null ){
				
				logger.error(line);
				
			}
			
			input.close();
			out.write(4);

		} catch (Exception e) {
		logger.error("error===" + e.getMessage());
			e.printStackTrace();
		}
	}
}
