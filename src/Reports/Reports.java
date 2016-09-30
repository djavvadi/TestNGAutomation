package Reports;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;



public class Reports {
	
	private String htmlpath;
	private String reportLog;
	private String screenshots;
public void createTempPath(String TestName) {
	
	//Create a result folder with time stamp appending to it to the folder name
	String resultFoldername = getTimestamp("ddMMyyyyHHmmss");
		
	File file = new File("");
	String resultpath = file.getAbsolutePath()+"\\Results\\Run_"+TestName+"_"+resultFoldername;
	htmlpath = resultpath+"\\HTML Result";
	screenshots = resultpath+"\\Screenshots";
	reportLog = htmlpath+"\\HTMLResult.html";
	File files = new File(resultpath);
	File htmlfile = new File(htmlpath);
	File screen = new File(screenshots);
	files.mkdir();
	htmlfile.mkdir();
	screen.mkdir();
	createheader();
}
public void createheader(){
	String html = "<html><h1 align=center>Test Results</h1>"
			+ "\t\t <style type='text/css'> \n"
			+ "\t\t\t table { \n" +
									"\t\t\t\t border: 1px solid #4D7C7B; \n" +
									"\t\t\t\t border-collapse: collapse; \n" +
									"\t\t\t\t border-spacing: 0px; \n" +
									"\t\t\t\t width: 1000px; \n" +
									"\t\t\t\t margin-left: auto; \n" +
									"\t\t\t\t margin-right: auto; \n" +
								"\t\t\t } \n\n"
								+ "\t\t\t body { \n" +
									"\t\t\t\t font-family: Verdana, Geneva, sans-serif; \n" +
									"\t\t\t\t text-align: center; \n" +
								"\t\t\t } \n\n"
								+ "\t\t\t td, th { \n" +
									"\t\t\t\t padding: 4px; \n" +
									"\t\t\t\t text-align: inherit\\0/; \n" +
								"\t\t\t } \n\n"
								+ "\t\t\t td.pass { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: green; \n" +
								"\t\t\t } \n\n"
								+ "\t\t\t td.done, td.screenshot { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: black; \n" +
								"\t\t\t } \n\n"+
								"\t\t\t td.fail { \n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t color: red; \n" +
								"\t\t\t } \n\n"
								+ "\t\t\t th.heading { \n" +
									"\t\t\t\t background-color:#7D80C0; \n" +
									"\t\t\t\t color: #000405;\n" +
									"\t\t\t\t font-size: 0.9em; \n" +
									"\t\t\t\t font-weight: bold; \n" +
								"\t\t\t } \n\n"
								+ "\t\t\t tr.subheading { \n" +
									"\t\t\t\t background-color:#9FB4BE;\n" +
									"\t\t\t\t color:#000405;\n" +
									"\t\t\t\t font-weight: bold; \n" +
									"\t\t\t\t font-size: 0.9em; \n" +
									"\t\t\t\t text-align: justify; \n" +
								"\t\t\t } \n\n"
								+ "</style>"
			+ "<body><table align=center width=100>"
			+ "<th class='heading'>Step Name</th>"
			+ "<th class='heading' >Step Description</th>"
			+ "<th class='heading'>Status</th>"
			+ "<th class='heading'>Step Time</th>";
		
	
	try {
		File htmlTemplateFile = new File(reportLog);
		PrintStream printStream = new PrintStream(htmlTemplateFile);	
		printStream.println(html);
		printStream.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public void updateTestlog(String stepName,String Description,String Status){
	String testSteps = "<tr class = 'subheading'><td>"+stepName+"</td>"
			+ "<td>"+Description+"</td>"
					+ "<td class="+Status.toString().toLowerCase()+">"+Status+"</td>"
							+ "<td>"+getTimestamp("dd/MM/YYYY HH:mm:ss")+"</td></tr>";
	File htmlTemplateFile = new File(reportLog);
	try {
		BufferedWriter bw = new BufferedWriter (new FileWriter(htmlTemplateFile,true));
		bw.write(testSteps);
		bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public void testreportClosure(){
	String reportFooter = "</table></body></html>";
	File htmlTemplateFile = new File(reportLog);
	try {
		BufferedWriter bw = new BufferedWriter (new FileWriter(htmlTemplateFile,true));
		bw.write(reportFooter);
		bw.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
public String getTimestamp(String Format){
	DateFormat dateFormat = new SimpleDateFormat(Format);
	Calendar cal = Calendar.getInstance();
	String timeStamp = dateFormat.format(cal.getTime());
	return timeStamp;
}

}