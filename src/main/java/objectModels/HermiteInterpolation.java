package objectModels;

/**
 * Hermite Interpolation class
 * * Method of Polynomial interpolation.
 *  * * Given (xi, yprime, yi) data points, this class will interpolate a polynomial that passes through all the points.
 *  * * * i.e. passing through (xi, yi) pairs and the hermite interpolated polynomial denoted by H
 * *  * * * H will have the same values as y and H prime being equal to y prime at the given xi points...
 * * The interpolated Polynomial (H) should be of least degree, passing through all the xi and yi pairs (points),
 * * And having the same values as yprime (derivative values) at the given xi points.
 * * The formula (steps) of this interpolation method can be found in the references in the readme file.
 * * The steps are also broken down into methods below. (aSubi, BSubi, computeLagrangeDerivativeAtXi, lagrangeBasisValue)
 * * --------------------------------------------------------------------------------------------------------------------
 * Create Hermites of your own!
 * * By calling the constructor and then the hermiteInterpolation method on the object.
 * * See the NASA_ExampleTest.java for an example of how to use this class.
 * *  * Under test/java/NASA_ExampleTest.java
 */

public class HermiteInterpolation {
    double[] xi;//(xi, yi, y prime i)
    double[] yprime ;
    double[] yi ;
    double[] derivativesAtXi;
    double A ;
    double B ;
    double H;

    /**
     * Constructor for the HermiteInterpolation class.
     * Initializes the input data points and computes the lagrange derivatives at xi to be used in the interpolation.
     */
    public HermiteInterpolation(double[] xi, double[] yprime, double[] yi) {
        this.xi = xi; //(xi, yi, y prime i)
        this.yprime = yprime;
        this.yi = yi;
        this.H = 0; // initialize H before being used in hermiteInterpolation
        this.derivativesAtXi = new double[xi.length];
        for (int i = 0; i < xi.length; i++) {
            derivativesAtXi[i] = computeLagrangeDerivativeAtXi(xi, i); //used in aSubi calculation
        }
    }
    /**
     * Computes the Hermite Interpolation at a given point.
     * returns first H then H prime
     * H is the interpolated value at xPoint
     * H prime is the derivative of the interpolated value at xPoint
     */
    public double[] hermiteInterpolation(HermiteInterpolation h, double xPoint) {
        double H_derivative = 0;
        for(int i =0; i<h.xi.length;i++){
            A = h.aSubi(xPoint, i); // aSubi calculation formula step
            B = h.BSubi(xPoint, i); // BSubi calculation formula step
            H += (A * h.yi[i]+ B*h.yprime[i]); // hermite intperpolation step

            // Calculate the derivative of the interpolated value at xPoint
            //logic for derivative calculation, may introduce new methods
            H_derivative = 456.0; //todo replace number wth actual calculations
        }
        System.out.println("is this our actual interpolated value? : " + H );
        System.out.println("Interpolated derivative: " + H_derivative);
        return new double[]{H, H_derivative};    }
    /**
     * Computes the value of aSubi at a given point.
     */
    public double aSubi(double xPoint, int i) {
        double li = lagrangeBasisValue(i, xPoint); // Value of i-th Lagrange basis polynomial at xPoint
        double li_prime = derivativesAtXi[i];
        return (1 - 2 * (xPoint - xi[i]) * li_prime) * Math.pow(li, 2);
    }
    /**
     * Computes the value of BSubi at a given point.
     */
    public double BSubi(double xPoint, int i) {
        double li = lagrangeBasisValue(i, xPoint); // Value of i-th Lagrange basis polynomial at xPoint
        return (xPoint - xi[i]) * Math.pow(li, 2);
    }
    /**
     * Computes the Lagrange Derivative at xi.
     */
    public double computeLagrangeDerivativeAtXi(double[] xi, int i) {
        // First, compute Li(x)
        double liOfX;
        liOfX = lagrangeBasisValue(i,xi[i]);
        // Now compute the sum of the reciprocals of (x - xm) for all m except i
        double sumOfReciprocals = 0.0;
        for (int m = 0; m < xi.length; m++) {
            if (m != i) {
                sumOfReciprocals += 1.0 / (xi[i] - xi[m]);
            }
        }
        return liOfX * sumOfReciprocals;
    }
    /**
     * Computes the value of the Lagrange basis at a given point.
     */
    public double lagrangeBasisValue(int i, double xPoint){
        double value = 1.0;
        for (int j = 0; j < xi.length; j++) {
            if (j != i) {
                value *= (xPoint - xi[j]) / (xi[i] - xi[j]);
            }
        }
        return value;
    }

}
