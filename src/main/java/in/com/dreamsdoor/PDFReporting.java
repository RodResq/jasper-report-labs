package in.com.dreamsdoor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import in.com.dreamsdoor.beans.Student;
import in.com.dreamsdoor.beans.Teacher;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PDFReporting {

	static final String fileName = "src/main/resources/JasperDesign.jrxml";
	static final String outFile = "src/main/resources/Reports.pdf";
	 
	public static void main(String[] args) throws JRException, FileNotFoundException {
		// TODO Auto-generated method stub
		List<Student> studentList = new ArrayList<Student>();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		
		Map<String, Object> parameter =  new HashedMap();
		
		studentList.add(new Student(1, "Student 1", "FY BSc"));
		studentList.add(new Student(2, "Student 2", "TY BSc"));
		studentList.add(new Student(3, "Student 3", "TY BBA"));
		
		teacherList.add(new Teacher(1, "Teacher 1", 50000.2));
		teacherList.add(new Teacher(2, "Teacher 2", 45000.0));
		teacherList.add(new Teacher(3, "Teacher 3", 65000.0));
		
		
		JRBeanCollectionDataSource studentCollectionDataSource = new JRBeanCollectionDataSource(studentList);
		JRBeanCollectionDataSource teacherCollectionDataSource = new JRBeanCollectionDataSource(teacherList);
		
		parameter.put("studentDataSource", studentCollectionDataSource);
		parameter.put("teacherDataSource", teacherCollectionDataSource);
		
		parameter.put("title", new String("Hi, I am Title"));

		JasperReport jasperDesign = JasperCompileManager.compileReport(fileName);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter, new JREmptyDataSource());

		File file = new File(outFile);
		FileOutputStream outputStream = new FileOutputStream(file);
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
		System.out.println("Report Generated!");
		
	}

}
