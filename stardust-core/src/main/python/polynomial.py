import math

def diff(coeffs):
    """
    Differentiates a polynomial.

    coeffs is a list representing the coefficients of the polynomial to differentiate.
    Returns a list of coefficients representing the derivative.
    See #poly for details on polynomial representation.

    Example:
    diff([-10, 9, -3, 1])
    Output:
    [9, -6, 3]
    """
    return [n*a for (n, a) in list(enumerate(coeffs))][1:]

def poly(coeffs):
    """
    Creates a polynomial:                                                  
    f(x) = a_0 + a_1*x + a_2*x**2 + ... + a_n*x**n

    coeffs is a list of n + 1 numbers where coeffs[i] represents a_i in the polynomial,
    for 0 <= i <= n.
    Returns a function that takes an arbitrary value c and returns f(c).

    Example:
    f = poly([-10, 9, -3, 1])
    f(2)
    Output:
    4
    """
    return lambda x: sum([a*x**n for (n, a) in list(enumerate(coeffs))])

def taylor(coeffs):
    """
    Given a polynomial f(x), generates the Taylor series of f(x + c):
    f(x + c) = f(c) + f'(c)*x + f''(c)/2!*x**2 + ... + f^{n}(c)/n!*x**n
                                                    
    coeffs is a list representing the coefficients of f(x).
    Returns a function which takes an arbitrary value c and returns the coefficients of the Taylor series of f(x + c).
    See #poly for details on polynomial representation.

    Example:
    f = taylor([-10, 9, -3, 1])
    f(1)
    Output:
    [-3.0, 6.0, 0.0, 1.0]
    """
    cur = coeffs
    deriv_list = []
    while len(cur) > 0:
        deriv_list.append(cur)
        cur = diff(cur)
    #print("Derivatives:", list(enumerate(deriv_list)))
    return lambda x: [(poly(p))(x) / math.factorial(n) for (n, p) in list(enumerate(deriv_list))]
