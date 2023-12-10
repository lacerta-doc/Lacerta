package one.nem.lacerta.data.repository;

import javax.inject.Inject;

public class TestData {

    @Inject
    public TestData(){
    }

    public String getTestData(){
        return "TestData";
    }
}
