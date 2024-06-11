import static org.junit.jupiter.api.Assertions.assertEquals;
import objectModels.HermiteInterpolation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;



public class TestDriver {
    private double[] xi ;
    private double[] y ;
    private double[] yprime ;
    private double[] x ;
    int i ;
    double xPoint ;
    double expectedASubi;
    double expectedBSubi;
    double expectedLiPrimeTest;
    double expectLi;
    double expectedLagrangeBasisValue;
    double expectHermite;
    double expectedDerivative;
    double expectedH;
    final double DELTA = 1e-6;

    /*
    Empty (JUnite requirement) Constructor for the TestDriver class
     */
    public TestDriver() {
    }
    //todo redo-entire test driver. Need to manually verify the expected values for each step with a suite of inputs passed through a library or online calculator.
    public static void main(String[] argv) {


        TestDriver tests = new TestDriver();
        // Example calls with placeholder values - adjust as needed
        tests.hermiteTestController();
    }
    @BeforeEach
    public void setup(){

        //Hermite Interpolation Data
        xi = new double[]{-1, 0, 1, 3};
        y = new double[]{-2, -7, -8, 26};
        yprime = new double[]{1, 4, -2, 8};
        x = new double[]{1, 2, 3};

        //Point at which we are testing
        i = 0;
        xPoint = 2.0;

        //expectedH = 141.0; // NASA expected value for f(2)
        expectedDerivative = 456.0; // NASA expected value for f'(2)


        /* Manually decided oracle values and inputs
        //Hermite Interpolation Data
        xi = new double[]{-1, 0, 1, 3};
        y = new double[]{-2, -7, -8, 26};
        yprime = new double[]{1, 4, -2, 8};
        x = new double[]{1, 2, 3};
        i = 0;
        xPoint = 2.9;
         */
        expectedASubi= 0.06949617; //0.06949617
        expectedBSubi= 0.01850069;

        expectedLiPrimeTest = -1.75;
        expectLi = 0.068875;

        expectedLagrangeBasisValue = 0.068875;
        expectedDerivative = -1.75; //todo part of original test values
        expectHermite = 25.54049;

    }
    /**
     * Test for the Hermite Interpolation computation.
     */
    @Test
    public void HermiteInterpolationTestCase1(){
        HermiteInterpolation hermite = new HermiteInterpolation(xi,yprime,y);
        double[] H = hermite.hermiteInterpolation(hermite, xPoint );

        assertEquals(H[0],expectedH, DELTA);


    }
    /**
     * Test for the aSubi computation.
     */
    @Test
    public void aSubiTest(){
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);
        double actualASubi = hermiteInterpolation.aSubi(xPoint,i);

        assertEquals(expectedASubi, actualASubi, DELTA);
    }
    /**
     * Test for the computeLagrangeDerivativeAtXi computation.
     */
    @Test
    public void liprimeTest(){
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);
        double actualLiPrimeTest = hermiteInterpolation.computeLagrangeDerivativeAtXi(xi,i);
        assertEquals(actualLiPrimeTest,expectedLiPrimeTest);
    }
    /**
     * Test for the lagrangeBasisValue computation.
     */
    @Test
    public void litest(){
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);
        double actualLi = hermiteInterpolation.lagrangeBasisValue(i,xPoint);
        assertEquals(expectLi,actualLi, DELTA);

    }
    /*todo pretty sure this entire test case is redundant
    A test for the derivative calc Test
    PARAMS: A set of known xi values.
    Expected Output: Manually calculated derivatives for each xi.
    Test: Compare the output of computeLagrangeDerivativeAtXi with the expected derivatives.
     */
    @Test
    public void derivativeCompTest() {
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);
        double actualDerivative = hermiteInterpolation.computeLagrangeDerivativeAtXi(xi, i);

        assertEquals(expectedDerivative, actualDerivative, DELTA);
    }
    @Test
    public void lagrangeBasisValueTest() {
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);
        double actualValue = hermiteInterpolation.lagrangeBasisValue(i, xPoint);
        assertEquals(expectedLagrangeBasisValue, actualValue, DELTA);
    }
    @Test
    public void aSubiBSubiTest() {
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);
        double actualASubi = hermiteInterpolation.aSubi(xPoint, i);
        double actualBSubi = hermiteInterpolation.BSubi(xPoint, i);
       assertEquals(expectedASubi, actualASubi, DELTA);
       assertEquals(expectedBSubi, actualBSubi, DELTA);
    }
    @Test
    public void bSubiTest() {
        //Hermite function object initializing
        HermiteInterpolation hermiteInterpolation = new HermiteInterpolation(xi, yprime, y);

        //oracle values
        double actualBSubi = hermiteInterpolation.BSubi(xPoint, i);
        //oracle
        assertEquals(expectedBSubi, actualBSubi, DELTA);
    }
    /*
    Test for the hermite interpolation calculation steps
     */
    @Test
    public void hermiteTestController() {
        //todo make hermiteinterpolation test for final value.
        liprimeTest();
        derivativeCompTest();
        //lagrangeBasisValueTest();
        aSubiBSubiTest();
        aSubiTest();
        bSubiTest();
    }

}