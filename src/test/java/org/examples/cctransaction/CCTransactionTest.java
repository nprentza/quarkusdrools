package org.examples.cctransaction;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import javax.inject.Inject;

@QuarkusTest
public class CCTransactionTest {
    @Inject
    KieRuntimeBuilder runtimeBuilder;

    @Test
    public void test() {

        KieSession ksession = runtimeBuilder.newKieSession("CCTransactionKB");

        AllowedCountry countryCy = new AllowedCountry("1234","Cyprus");
        FactHandle fh_countryCy = ksession.insert(countryCy);
        AllowedCountry countryGr = new AllowedCountry("1234","Greece");
        FactHandle fh_countryGr = ksession.insert(countryGr);

        CCTransaction trans1 = new CCTransaction("1234","Greece");
        CCTransaction trans2 = new CCTransaction("1234","Italy");

        FactHandle fh_trans1 = ksession.insert(trans1);
        FactHandle fh_trans2 = ksession.insert(trans2);

        ksession.fireAllRules();

        CCTransaction trans1_u = (CCTransaction) ksession.getObject(fh_trans1);
        CCTransaction trans2_u = (CCTransaction) ksession.getObject(fh_trans2);

        System.out.println("Trans1 status: " + trans1_u.getStatus());
        System.out.println("Trans2 status: " + trans2_u.getStatus());

        System.out.println("Done.");

    }

}
