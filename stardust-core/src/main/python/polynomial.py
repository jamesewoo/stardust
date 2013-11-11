def poly(coeffs):
    return lambda x: sum([a*x**n for (n, a) in list(enumerate(coeffs))])
