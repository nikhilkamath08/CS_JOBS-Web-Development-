package csjobs.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;

import csjobs.model.Job;
import csjobs.model.User;
import csjobs.model.dao.JobDao;
import csjobs.model.dao.UserDao;
import csjobs.web.editor.UserPropertyEditor;

@Controller
@SessionAttributes("job")
public class JobControllerS {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobDao jobDao;

    @Autowired
    private WebApplicationContext context;

    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( User.class,
            (UserPropertyEditor) context.getBean( "userPropertyEditor" ) );
        binder.registerCustomEditor( Date.class,
            new CustomDateEditor( new SimpleDateFormat( "M/d/yyyy" ), true ) );
    }

    @RequestMapping(value = "/job/create.html", method = RequestMethod.GET)
    public String create( ModelMap models )
    {
        models.put( "job", new Job() );
        models.put( "reviewers", userDao.getUsers( "ROLE_REVIEWER" ) );
        return "job/create";
    }

    @RequestMapping(value = "/job/create.html", method = RequestMethod.POST)
    public String create( @ModelAttribute Job job, SessionStatus sessionStatus,@RequestParam Long committeeChair,@RequestParam Long[] committeeMembers )
    {
    	List<User> reviewer1= new  ArrayList<User>();
    	for(int i=0;i<committeeMembers.length;i++)
    	{
    		User committeemem= userDao.getUser(committeeMembers[i]);
    		reviewer1.add(committeemem);
    	}
    
    	job.setCommitteeChair(userDao.getUser(committeeChair));
    	job.setCommitteeMembers(reviewer1);
    	if(!job.getCommitteeMembers().contains(userDao.getUser(committeeChair)))
    	{
    	reviewer1.add(userDao.getUser(committeeChair));
    	}
    	
        job = jobDao.saveJob( job );
        
        sessionStatus.setComplete();

        return "redirect:/admin.html";
    }

    @RequestMapping(value = "/job/edit.html", method = RequestMethod.GET)
    public String edit( @RequestParam Long id, ModelMap models )
    {
    	
        models.put( "job", jobDao.getJob( id ) );
        models.put( "reviewers", userDao.getUsers( "ROLE_REVIEWER" ) );
        return "job/edit";
    }

    @RequestMapping(value = "/job/edit.html", method = RequestMethod.POST)
    public String edit( @ModelAttribute Job job, SessionStatus sessionStatus,@RequestParam Long committeeChair,@RequestParam Long[] committeeMembers )
    {
    	List<User> reviewer1= new  ArrayList<User>();
    	for(int i=0;i<committeeMembers.length;i++)
    	{
    		User committeemem= userDao.getUser(committeeMembers[i]);
    		reviewer1.add(committeemem);
    	}
    
    	job.setCommitteeChair(userDao.getUser(committeeChair));
    	job.setCommitteeMembers(reviewer1);
    	if(!job.getCommitteeMembers().contains(userDao.getUser(committeeChair)))
    	{
    	reviewer1.add(userDao.getUser(committeeChair));
    	}
    	
        job = jobDao.saveJob( job );
        job = jobDao.saveJob( job );
        sessionStatus.setComplete();

        return "redirect:/admin.html";
    }

}
