package se.walkercrou.places.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;


public abstract class AbstractScraper implements Scraper{
	
	//protected Logger logger = LoggerFactory.getLogger(getClass());
	
//	protected static String fieldMap = "id:id,category:category,title:title,summary:summary,detail:detail,tags:tags,video_url:videoUrl,content_url:contentUrl,audio_url:audioUrl,extra:extra";
	//protected static String fieldMap = "lat:lat,lng:lng";
	protected static String fieldMap = "placeid:placeid,name:name,lat:lat,lng:lng,address:address";

	
	protected interface LITERALS{
		String FAQS_LIST = "faq-list";
		String LI = "li";
		String ABS_HREF = "abs:href";
		String DL = "dl";
		String P = "p";
		String CI_BOX_SPECIAL = "ciBoxSpecial";
		String H5 = "h5";
	}
	
	public abstract String getFaqsUrl();
	
	public String getDefaultFieldMap(){
		return fieldMap;
	}
	
	public char getDefaultSeparator(){
		return "|".charAt(0);
	}
	
	public File getFileForFaqs(String filename){
		File file = new File("/temp/" + filename + "_faqs.csv");
//		File file = new File("/temp/"+getClass().getSimpleName()+"_faqs.csv");
		try {
			file.createNewFile();
		} catch (IOException e) {
			//logger.error("Error creating file:{}",file.getAbsolutePath(),e);
		}
		return file;
	}
	
	public void writeToFile(List<Hospital> faqList, String fileName){
		try {
			CustomCsvWriter csvWriter = new CustomCsvWriter(getFileForFaqs(fileName), getDefaultSeparator(), getDefaultFieldMap());
			csvWriter.writeToFile(faqList);
		} catch (Exception e) {
			//logger.error("Error writing faqs to file : ",e);
		}
	}
	
	@Override
	public List<Hospital> scrapeProducts(String url){
		return null;
	}

//	@Override
//	public Result scrapeProductsByQuery(String query){
//		return null;
//	}
//
//	@Override
//	public Result getCategories(){
//		return null;
//	}

	@Override
	public List<Hospital> getFaqs(String url){
		return null;
	}
	
	
}