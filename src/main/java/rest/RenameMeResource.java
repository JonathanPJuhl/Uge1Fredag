package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import entities.BankCustomer;
import utils.EMF_Creator;
import facades.FacadeExample;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("bankcustomer")
public class RenameMeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final FacadeExample FACADE =  FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo2(@PathParam("id") int id) {
        List<BankCustomer> emp = FACADE.getAllCustomers();
        List<CustomerDTO> list = new ArrayList();
        for(BankCustomer bC : emp){
            list.add(new CustomerDTO(bC));
        }
      if(emp!=null){
      return new Gson().toJson(list);}
      else{
          return null;
      }
    }
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo3() {
         List<BankCustomer> emp = FACADE.getAllCustomers();
       
      if(emp!=null){
      return new Gson().toJson(emp);}
      else{
          return null;
      }
    }
//    @Path("count")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String getRenameMeCount() {
//        long count = FACADE.getRenameMeCount();
//        System.out.println("--------------->"+count);
//        return  "{\"count\":"+count+"}";  //Done manually so no need for a DTO
//    }
}
