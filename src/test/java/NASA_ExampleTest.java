import static org.junit.jupiter.api.Assertions.assertEquals;
import objectModels.HermiteInterpolation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
/**
 * This class represents a test driver for the HermiteInterpolation class.
 * It provides methods to test the computation of the Hermite Interpolation and its derivative.
 * This test case is based on the NASA example provided on their website.
 * It is also linked in the readme file references section.
 */
public class NASA_ExampleTest {
    private HermiteInterpolation hermite;
    /**
     * Empty constructor for the NASA_ExampleTest class.
     * * JUnit requirement
     */
    NASA_ExampleTest(){}
    /**
     * Setup method for the tests.
     * Initializes the test data.
     */
    @BeforeEach
    public void setUp() {
        double[] xi = {-1.0, 1.0, 3.0, 5.0};
        double[] y = {6.0, 8.0, 2210.0, 78180.0};
        double[] yprime = {3.0, 11.0, 5115.0, 109395.0};
        hermite = new HermiteInterpolation(xi,yprime,y);
    }
    /**
     * Test for the Hermite Interpolation computation on NASA example.
     */
    @Test
    public void NASAHermiteExample(){
        //Value being interpolated at from the NASA example
        double xPoint = 2.0;
        //Call the hermiteInterpolation method and get the result array
        double[] result = hermite.hermiteInterpolation(hermite, xPoint);
        double actualValue = result[0]; //Hermite Interpolated value
        double actualDerivative = result[1]; //Hermite Interpolated derivative
        //todo find out how to also return the created hermite polynomial
        //oracle values
        double expectedValue = 141.0;
        double expectedDerivative = 456.0;
        assertEquals(expectedValue,actualValue, 1e-6);
        assertEquals(expectedDerivative, actualDerivative, 1e-6);
    }

}
