package com.carRepair.carRepair.Web.AdminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RepairsController {

    // Selida Repair(Repair)

    @RequestMapping(value = "/admin/service" ,  method = RequestMethod.GET)
    public String services(){

        //List<Services> services = findAll(); // Services

        return "admin/services/home";
    }

    @RequestMapping(value = "/admin/new-service" ,  method = RequestMethod.GET)
    public String createService(){

        //ServiceUserForm()
        return "admin/services/new_service";
    }

    @RequestMapping(value = "/admin/new-service" ,  method = RequestMethod.POST)
    public String createServicePost(){

        //save(Repair service); // Repair
        return "admin/service/new_service";
    }

    @RequestMapping(value = "/admin/update-service" ,  method = RequestMethod.GET)
    public String updateService(){

        //UpdateServiceForm()
        return "admin/services/update_service";
    }

    @RequestMapping(value = "/admin/update-service" ,  method = RequestMethod.POST)
    public String updateServicePost(){

        //save(Long id , Repair service); // Repair
        return "admin/service/update_service";
    }

    @RequestMapping(value = "/admin/delete-service" ,  method = RequestMethod.GET)
    public String deleteService(){

        //DeleteServiceForm()
        return "admin/services/delete_service";
    }

    @RequestMapping(value = "/admin/delete-service" ,  method = RequestMethod.POST)
    public String deleteServicePost(){

        //delete(Long id); // Repair
        return "admin/service/delete_service";
    }

    @RequestMapping(value = "/admin/search-service" ,  method = RequestMethod.GET)
    public String searchService(){

        //SearchServiceForm()
        return "admin/services/search_service";
    }

    @RequestMapping(value = "/admin/search-service" ,  method = RequestMethod.POST)
    public String searchServicePost(){

        //Repair service = findOne(Long id); // Repair
        return "admin/service/search_service";
    }



}
