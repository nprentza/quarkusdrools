package org.examples.cctransaction;

import org.drools.core.ClassObjectFilter;
import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/drools-app")
public class CCTDroolsEndpoint {

    @Inject
    KieRuntimeBuilder runtimeBuilder;
    KieSession kSession;


    @PostConstruct
    public void setup() {
        this.kSession = runtimeBuilder.newKieSession("CCTransactionKB");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public Response getAllowedCountries(){
        Collection cObjects = kSession.getObjects( new ClassObjectFilter( AllowedCountry.class ) );
        System.out.println("AllowedCountry objects in wm=" + cObjects.size());
        return Response.ok(cObjects).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/countries")
    public Response addAllowedCountry(AllowedCountry aCountry){

        this.kSession.insert(aCountry);
        kSession.fireAllRules();
        Collection cObjects = kSession.getObjects( new ClassObjectFilter( AllowedCountry.class ) );
        return Response.ok(cObjects).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/transaction")
    public Response registerTransaction(CCTransaction transaction){
        FactHandle fh_trans = this.kSession.insert(transaction);

        this.kSession.fireAllRules();

        CCTransaction trans_u = (CCTransaction) this.kSession.getObject(fh_trans);

        return Response.ok(trans_u).build();
    }
}
