package com.example.antiscam.dataclass;

import static org.junit.Assert.assertEquals;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.builder.ScamCaseBuilder;
import com.example.antiscam.builder.WholeScamCase;

import org.junit.Test;

public class ScamCaseDaoImplTest {
    @Test
    public void testAddScamCaseSuccess() {
        // Create a ScamCase object with valid data
        ScamCaseBuilder scamCaseBuilder=new ScamCaseBuilder();
        WholeScamCase wholeScamCase=new WholeScamCase();
        wholeScamCase.setScamCaseBuilder(scamCaseBuilder);
        ScamCase scamCase = wholeScamCase.makeScamCase("200","calls","27","05","2023",
                "I received an irresistible investment offer promising high returns. Greed got the best of me, and I invested a substantial sum. The company vanished, and so did my money. I learned the hard way that if it sounds too good to be true, it probably is.",
                "credit card","comp2100@anu.edu.au",2501,"investment scams","Beware of This Investment Scam!","30","Canberra");

        // Create an instance of ScamCaseDaoImpl
        ScamCaseDaoImpl dao = new ScamCaseDaoImpl();

        int initialSize =dao.getSizeOfScamCase();
        dao.addScamCase(scamCase);

        // Get the updated size after adding a scam case
        int finalSize = dao.getSizeOfScamCase();
        assertEquals(initialSize + 1, finalSize);
    }


   // @Test
//    public void testAddScamCaseFailure() {
//        // Create a ScamCase object with invalid data (for testing failure scenario)
//
//        // Create an instance of ScamCaseDaoImpl
//        ScamCaseDaoImpl dao = new ScamCaseDaoImpl();
//
//        // Call addScamCase and assert that it fails as expected
//
//        // You can add assertions or further checks here
//    }
}
