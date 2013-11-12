import math

def poly(coeffs):
    """
    Returns a polynomial f(x).

    coeffs is a list of n + 1 numbers in which coeffs[i] represents a_i in:

    f(x) = a_0 + a_1*x + a_2*x**2 + ... + a_n*x**n

    for 0 <= i <= n.
    """
    return lambda x: sum([a*x**n for (n, a) in list(enumerate(coeffs))])

def taylor(poly_list):
    """
    Returns the Taylor series of a polynomial f(x).

    poly_list is a list of length n + 1 where index i contains the i-th order derivative of f(x),
    for 0 <= i <= n, where n is the degree of f(x).  poly_list[0] contains f(x) itself.

    Example:
    poly_list = [poly((-10,9,-3,1)), poly((9,-6,3)), poly((-6,6)), poly((6,0))]
    f = taylor(poly_list)
    f(1)
    Output:
    [-3.0, 6.0, 0.0, 1.0]
    """
    return lambda x: [p(x) / math.factorial(n) for (n, p) in list(enumerate(poly_list))]
