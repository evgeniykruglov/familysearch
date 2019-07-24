package org.familysearch;

public class InputParametersValidation {
    public static void inputParametersValidator(int startPage, int endPage) {
        if ((endPage-startPage>100)||(startPage<=0)) {
            System.out.println("Wrong input parameters, please check");
            System.exit(0);
        }
    }
}
