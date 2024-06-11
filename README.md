
# Background
- Why?
- - I enjoy coding in JAVA and doing math.
  - I hope you enjoy this too.
- **What** is interpolation?
	- Interpolation is a method of estimating unknown values that fall **between** known values. 
	- It involves constructing new data points **within** the range of a discrete set of known data points.
- **Intro** to **Polynomial** Interpolation:
	- Polynomial interpolation is a form of interpolation where the *interpolant* is a polynomial. 
	- Given a set of n+1 data points, 
		- Polynomial interpolation finds a polynomial of degree at most **n** that passes through all these points.
# Note
>**Hermite interpolation** resources referenced in the implementation process can be found below under the **References** section below. This implementation of a **Hermite interpolation** is tested against **NASA**'s  I/O Example posted [here](https://naif.jpl.nasa.gov/pub/naif/toolkit_docs/FORTRAN/spicelib/hrmesp.html).  The final version of this interpolation calculator will hopefully return the entire interpolated polynomial and its derivative at given points. Any missing functionality is outlined by **todo**'s within the code and will be added to issues soon. 
# **Hermite** Interpolation
**Hermite** interpolation is a type of polynomial interpolation where both the function values and the derivatives are matched at given points. 
This makes it particularly useful when the **slope** of the function is also known at the **data points**, providing a more accurate and smooth **approximation** (*interpolation*).
### **Key** Concepts
- **Where** the interpolating polynomial matches **both** function and derivates **at given** points *(xi. yi)* pairs.
	- Abscissa *x* and Ordinate *y* 
### **Formulation**
- Explanation summarized from [this document](https://sam.nitk.ac.in/courses/MA608/Hermite_Interpolation.pdf) 
- i.e. Given (xi, yi, y'i), where i = 0,.., n *number of inputs* 
	- Determines a polynomial of least degree, denoted by:
	$$H_{2n+1}(x) = \text{Hermite interpolated polynomial}$$
### Such that 

$$H_{2n+1}(x_{i}) = y_{i} \text{ = Hermite interpolated polynomial}$$

$$H\prime_{2n+1}(x_{i}) =y\prime_{i} \text{ = Hermite interpolated polynomial derivative}$$


### NASA C implementation
- The example provided along with the I/O specification of NASA's implementation slightly differs from the general approach. NASA performs the computation of only the Hermite interpolation value at a single point along with its derivative. It does not return the entire polynomial. This approach is suitable for their specific application of this interpolation method, likely due to the format of their inputs being time intervals (evenly spaced/uniform time steps).
#### Adapting to my **JAVA** implementation. 
- The inputs from NASA's example were fitted to match the parameters of my implementation by:
	- **NASA's Example**: Uses `first` and `step` to generate equally spaced points.
		- Directly input the generated points into my implementation's `xi` array.
	-  **NASA's Example**: Interleaves function values and their derivatives in the `yvals` array.
		- Separate the function values and derivative values into `yi` and `yprime` arrays, respectively.
	-  **NASA's Example**: Evaluates the interpolation at a specific point `x`.
		- Use the same point `x` to evaluate both the function value and its derivative using my implementation.
# **Conclusion**

- The general Hermite interpolation implementation was successfully validated against NASA's example, demonstrating that it can achieve the same results with different input formats. This flexibility allows the implementation to be used in a variety of scenarios, **both** with **equally** and **unequally** spaced data points.
### References

- [Hermite Interpolation Document](https://sam.nitk.ac.in/courses/MA608/Hermite_Interpolation.pdf)
- [NASA Hermite Interpolation Example](https://naif.jpl.nasa.gov/pub/naif/toolkit_docs/FORTRAN/spicelib/hrmesp.html)

---

This README provides a comprehensive overview of Hermite interpolation, including theoretical background, implementation details, and testing. If you need further modifications or have any questions, feel free to ask!

## Running The Code
Run the NASA Test file test case 1.
Read JAVA docs or the code to see how to call with your own data. 
