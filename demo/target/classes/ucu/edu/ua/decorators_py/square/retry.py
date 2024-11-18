import time

def retry_decorator(func):
    def wrapper(*args, **kwargs):
        attempts = 3
        for _ in range(attempts):
            try:
                return func(*args, **kwargs)
            except Exception as e:
                print(f"Error: {e}, retrying...")
                time.sleep(1)
        print("All retry attempts failed.")
    return wrapper

@retry_decorator
def unreliable_function():
    if time.time() % 2 < 1: 
        raise Exception("Random failure")
    return "Success!"

print(unreliable_function())
