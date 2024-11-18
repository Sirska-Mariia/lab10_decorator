"""
Decorators
"""
def square(f):
    def new_f(*args, **kwargs):
        return f(*args, **kwargs) ** 2
    return new_f

@square
def my_abs(num):
    return -num if num < 0 else num

@square
def dummy():
    return 14.88

print(dummy())