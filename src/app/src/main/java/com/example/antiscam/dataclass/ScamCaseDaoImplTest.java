package com.example.antiscam.dataclass;

import static org.junit.Assert.assertEquals;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.builder.ScamCaseBuilder;

import org.junit.Test;

public class ScamCaseDaoImplTest {
    @Test
    public void testAddScamCaseSuccess() {
        // Create a ScamCase object with valid data
        ScamCase scamCase=new ScamCase();
        scamCase = ScamCaseBuilder.makeScamCase(2501,"200","calls","27","05","2023",
                "I received an irresistible investment offer promising high returns. Greed got the best of me, and I invested a substantial sum. The company vanished, and so did my money. I learned the hard way that if it sounds too good to be true, it probably is.",
                "credit card","comp2100@anu.edu.au","investment scams","Beware of This Investment Scam!","30","Canberra");

        // Create an instance of ScamCaseDaoImpl
        ScamCaseDaoImpl dao = new ScamCaseDaoImpl();

       // int initialSize =dao.getSizeOfScamCase();
        dao.addScamCase(scamCase);

        // Get the updated size after adding a scam case
        //int finalSize = dao.getSizeOfScamCase();
        //assertEquals(initialSize + 1, finalSize);
    }
}
