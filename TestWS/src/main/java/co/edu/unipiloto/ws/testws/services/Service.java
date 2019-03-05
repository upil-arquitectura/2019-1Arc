/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unipiloto.ws.testws.services;

import co.edu.unipiloto.ws.testws.model.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author OMAR
 */
@Path("service")
public class Service {
    
    //Simulara una ´base de datos´
    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();
    private static int sal;
    private static int prom;
    
    //Insertara los datos de la base de datos   
    static{
        int minimumSalary = 800000;
        int id = 0;
        for(int i=0; i<10; i++){
            Person person = new Person();
            id = i + 1;
            person.setId(id);
            person.setFullName("My hermosa persona " + id);
            person.setAge(new Random().nextInt(40+1));
            person.setSalary((person.getAge()*minimumSalary)/3);
            sal += person.getSalary();
            persons.put(id, person);
        }
        
        prom = sal / id;
    }
    
    //Metodo la cual va a escoger una persona unica objeto en formato XML
    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id")int id){
        return persons.get(id);
    }
    
    //Metodo la cual va a escoger una persona unica objeto en formato JSON
    @GET
    @Path("/getPersonByIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJSON(@PathParam("id")int id){
        return persons.get(id);
    }
    
    //Metodo la cual va a retornar la lista de todas las personas en formato XML
    @GET
    @Path("/getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonsInXML(){
        return new ArrayList<Person>(persons.values());
    }
    
    //Metodo la cual va a retornar la lista de todas las personas en formato XML
    @GET
    @Path("/getAllSalarysInXML")
    @Produces(MediaType.APPLICATION_XML)
    public int getAllSalarysInXML(@PathParam("salary")int salary){
        return sal;
    }
    
    @GET
    @Path("/getAllSalarysInJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public int getAllSalarysInJSON(@PathParam("salary")int salary){
        return prom;
    }
}
