# movie-tickets.py

# TODO: Define your ticket prices and tax rate variables.
# For example:
# adult_tix_price = 11.25
# child_tix_price = 7.25
# tax_rate = 0.075

# TODO: Create a dictionary with three movies (keys as movie numbers, values as movie titles).
# Example format:
# movies = {"1": "Movie A", "2": "Movie B", "3": "Movie C"}

# TODO: Set up a dictionary to store the purchase summary per movie.
# The dictionary should eventually record the number of adult tickets, child tickets, and total cost for each movie.
# purchase_summary = {}

def calculate_total(num_adult, num_child):
    """
    TODO:
      Calculate the total ticket cost using the formula:
        (adult tickets * adult_tix_price + child tickets * child_tix_price) * (1 + tax_rate)
      Return the total cost rounded to two decimal places.
    """
    # Example: total = ((num_adult * adult_tix_price) + (num_child * child_tix_price)) * (1 + tax_rate)
    # return round(total, 2)
    pass  # Remove this line when you implement the function

# Start the purchase loop.
choice = "y"
print("Welcome to the movie ticket purchasing system!")

while choice.lower() == "y":
    # TODO: Display the list of available movies by looping through the movies dictionary.
    
    # TODO: Prompt the user to select a movie by its key.
    # Validate the input to ensure the user selects a valid movie.
    
    # TODO: Ask the user for the number of adult tickets.
    # Make sure to convert the input to an integer and handle non-numeric inputs.
    
    # TODO: Ask the user for the number of child tickets.
    # Again, validate the input appropriately.
    
    # TODO: Use the calculate_total function to compute the cost for the current transaction.
    
    # TODO: Display the transaction summary (movie, number of tickets, and the total cost).
    
    # TODO: Update the purchase summary dictionary with the current purchase details.
    
    # TODO: Ask the user if they want to purchase more tickets; update 'choice' accordingly.
    
# TODO: After exiting the loop, print a detailed purchase summary.
# The summary should include:
#   - Each movie the user is seeing.
#   - The number of adult and child tickets per movie.
#   - The total cost per movie.
#   - A grand total for all tickets.
print("Goodbye!")
