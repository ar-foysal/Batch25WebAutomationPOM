package utilities;

import org.testng.annotations.DataProvider;

public class DataSet {

    @DataProvider(name = "invalidCredentials")
    public static Object invalidUserDataSet(){
        Object[][] data  = {
                            {"shobuj@yopmai.com","shobuj123", "Your email or password is incorrect!", "", ""},
                            {"shobuj","shobuj123", "", "Please enter an email address.", ""},
                            {"","shobuj123", "",  "Please fill out this field.", ""}
        };

        return data;
    }
}
