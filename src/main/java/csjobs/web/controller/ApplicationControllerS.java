package csjobs.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.POITextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import csjobs.model.Application;
import csjobs.model.Degree;
import csjobs.model.Job;
import csjobs.model.Review;
import csjobs.model.Round;
import csjobs.model.User;
import csjobs.model.dao.ApplicationDao;
import csjobs.model.dao.JobDao;
import csjobs.model.dao.UserDao;
import csjobs.security.SecurityUtils;
import csjobs.util.FileIO;

@Controller
@SessionAttributes("application")
public class ApplicationControllerS {

    @Autowired
    private JobDao jobDao;
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private FileIO fileIO;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/application/apply.html",
        method = RequestMethod.GET)
    public String apply( @RequestParam Long jobId, ModelMap models )
    {
        Job job = jobDao.getJob( jobId );
        User applicant = SecurityUtils.getUser();
        Application application = applicationDao.getApplication( job,
            applicant );

        if( application != null && application.isSubmitted() )
        {
            models.put( "errorCode", "error.application.submitted" );
            return "error";
        }

        if( application == null )
            application = new Application( job, applicant );

        models.put( "application", application );
        return "application/apply";
    }

    @RequestMapping(value = "/application/apply.html",
        method = RequestMethod.POST)
    public String apply( @ModelAttribute Application application,
        @RequestParam(value = "cvfile", required = false ) MultipartFile cvFile,
        @RequestParam(value = "rsfile", required = false) MultipartFile rsFile,
        @RequestParam(value = "tsfile", required = false) MultipartFile tsFile,
        SessionStatus sessionStatus) throws InvalidFormatException,
        IOException, OpenXML4JException, XmlException
    {
    	
        User user = SecurityUtils.getUser();
        String fileDir = servletContext.getRealPath( "/WEB-INF/files" );
        
        if(cvFile.getContentType().contains("docx"))
    	{
    	File file = new File( "files", application.getCv().getId().toString());

        // Create a TextExtractor
        POITextExtractor extractor = ExtractorFactory.createExtractor( file );

        // Extract text and store it in a String
        String text = extractor.getText();
    	}
        
        else
    	{
        	// Locate the file
            File file = new File( "files",  application.getCv().getId().toString() );

            // Load the file into a PDDocument object
            PDDocument document = PDDocument.load( file );

            // Create a PDFTextStripper
            PDFTextStripper stripper = new PDFTextStripper();

            // Extract text and store it in a String
            StringWriter sw = new StringWriter();
            stripper.writeText( document, sw );
            String text = sw.toString();

            // Print out the text
            System.out.print( text );
    	}
        

        if( cvFile != null && !cvFile.isEmpty() )
            application.setCv( fileIO.save( fileDir, cvFile, user ) );
        if( rsFile != null && !rsFile.isEmpty() ) application
            .setResearchStatement( fileIO.save( fileDir, rsFile, user ) );
        if( tsFile != null && !tsFile.isEmpty() ) application
            .setTeachingStatement( fileIO.save( fileDir, tsFile, user ) );

        application = applicationDao.saveApplication( application );
        sessionStatus.setComplete();
        return "redirect:addDegree.html?applicationId=" + application.getId();
    }

    @RequestMapping(value = "/application/addDegree.html",
        method = RequestMethod.GET)
    public String addDegree( @RequestParam Long applicationId, ModelMap models )
    {
        models.put( "application",
            applicationDao.getApplication( applicationId ) );
        models.put( "degree", new Degree() );
        return "application/addDegree";
    }

    @RequestMapping(value = "/application/addDegree.html",
        method = RequestMethod.POST)
    public String addDegree( @ModelAttribute Application application,
        @ModelAttribute Degree degree, SessionStatus sessionStatus )
    {
        application.getDegrees().add( degree );
        application = applicationDao.saveApplication( application );
        sessionStatus.setComplete();
        return "redirect:addDegree.html?applicationId=" + application.getId();
    }
    @RequestMapping("/reviewerview.html")
    public String reviewview(ModelMap models ,@RequestParam Long id)
    {
    	
    	models.put("appjobs",applicationDao.getApp(id));
        
        return "reviewerview";
    }
    
    @RequestMapping("/reviewapp.html")
    public String reviewapp(ModelMap models ,@RequestParam Long jobid)
    {
    	models.put( "applis", applicationDao.getApplication(jobid) );
    	//System.out.println(applicationDao.getApp(jobid));
        return "reviewapp";
    }
    
    @RequestMapping("/viewapplicants.html")
    public String appview(ModelMap models ,@RequestParam Long id)
    {
    	
    	models.put("appjob",applicationDao.getApp(id));
        
        return "viewapplicants";
    }
    @RequestMapping("/viewapplication.html")
    public String applicationview(ModelMap models ,@RequestParam Long jobid)
    {
    	models.put( "appli", applicationDao.getApplication(jobid) );
    	//System.out.println(applicationDao.getApp(jobid));
        return "viewapplication";
    }
    @RequestMapping("/rankApplicant.html")
    public String rankApplicant(ModelMap models ,@RequestParam Long jid)
    {
    	
    	models.put("rank",applicationDao.getApplication(jid));
        
        return "rankApplicant";
    }
   
   /* @RequestMapping(value="/round1.html",method=RequestMethod.GET)
    public String round1(ModelMap models,Authentication auth,@RequestParam Long jid)
    {
    	models.put("reviewid"
    			+ "",((User) auth.getPrincipal()).getId());
    	models.put("r1",new Review());
        models.put("r2",applicationDao.getApplication(jid));
        return "round1";
    }
    
    @RequestMapping(value="/round1.html",method=RequestMethod.POST)
    public String round1(ModelMap models,@ModelAttribute Review r1,@RequestParam String comment,Authentication auth,@RequestParam Long jid,@RequestParam Integer indexes,HttpServletRequest request)
    {
    	
    	r1.setComments(comment);
    	r1.setDate(new Date());
    	r1.setReviewer((User) auth.getPrincipal());
    	r1.setRound(userDao.saveRound(jid, indexes));

    	String pass=request.getParameter("passed");
    	if(pass!=null)
    	{
    		Round rounds=userDao.saveRound(jid, indexes);
    		rounds.setPassed(true);
    		userDao.saveRound(rounds);	
    	}
    	
    	r1=jobDao.saveComment(r1);
    	return "redirect:/rankApplicant.html?jid="+jid;
    }
    
    
    @RequestMapping(value="/round2.html",method=RequestMethod.GET)
    public String round2(ModelMap models,Authentication auth,@RequestParam Long jid)
    {
    	models.put("reviewid"
    			+ "",((User) auth.getPrincipal()).getId());
    	models.put("r2",new Review());
        models.put("r3",applicationDao.getApplication(jid));
        return "round2";
    }
    
    @RequestMapping(value="/round2.html",method=RequestMethod.POST)
    public String round2(ModelMap models,@ModelAttribute Review r1,@RequestParam String comment,Authentication auth,@RequestParam Long jid,@RequestParam Integer indexes,HttpServletRequest request)
    {
    	
    	r1.setComments(comment);
    	r1.setDate(new Date());
    	r1.setReviewer((User) auth.getPrincipal());
    	r1.setRound(userDao.saveRound(jid, indexes));

    	String pass=request.getParameter("passed");
    	if(pass!=null)
    	{
    		Round rounds=userDao.saveRound(jid, indexes);
    		rounds.setPassed(true);
    		userDao.saveRound(rounds);	
    	}
    	
    	r1=jobDao.saveComment(r1);
    	return "redirect:/rankApplicant.html?jid="+jid;
    }
    
    
    @RequestMapping(value="/round3.html",method=RequestMethod.GET)
    public String round3(ModelMap models,Authentication auth,@RequestParam Long jid)
    {
    	models.put("reviewid"
    			+ "",((User) auth.getPrincipal()).getId());
    	models.put("r3",new Review());
        models.put("r4",applicationDao.getApplication(jid));
        return "round3";
    }
    
    @RequestMapping(value="/round3.html",method=RequestMethod.POST)
    public String round3(ModelMap models,@ModelAttribute Review r1,@RequestParam String comment,Authentication auth,@RequestParam Long jid,@RequestParam Integer indexes,HttpServletRequest request)
    {
    	
    	r1.setComments(comment);
    	r1.setDate(new Date());
    	r1.setReviewer((User) auth.getPrincipal());
    	r1.setRound(userDao.saveRound(jid, indexes));

    	String pass=request.getParameter("passed");
    	if(pass!=null)
    	{
    		Round rounds=userDao.saveRound(jid, indexes);
    		rounds.setPassed(true);
    		userDao.saveRound(rounds);	
    	}
    	
    	r1=jobDao.saveComment(r1);
    	return "redirect:/rankApplicant.html?jid="+jid;
    }
    */
    
    
    

}
