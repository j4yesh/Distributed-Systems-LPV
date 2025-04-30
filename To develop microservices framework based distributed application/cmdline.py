import requests

def user_service():
    print("\nUser Service:")
    print("1. Add User")
    print("2. View Users")
    choice = int(input("Enter choice: "))
    if choice == 1:
        name = input("Enter name: ")
        data = {"name": name}
        res = requests.post("http://localhost:5001/user", json=data)
        print(res.json())
    elif choice == 2:
        res = requests.get("http://localhost:5001/users")
        print(res.json())

def movie_service():
    print("\nMovie Service:")
    print("1. Add Movie")
    print("2. View Movies")
    choice = int(input("Enter choice: "))
    if choice == 1:
        title = input("Enter movie title: ")
        genre = input("Enter genre: ")
        data = {"title": title, "genre": genre}
        res = requests.post("http://localhost:5002/movie", json=data)
        print(res.json())
    elif choice == 2:
        res = requests.get("http://localhost:5002/movies")
        print(res.json())

def showtime_service():
    print("\nShowtime Service:")
    print("1. Add Showtime")
    print("2. View Showtimes")
    choice = int(input("Enter choice: "))
    if choice == 1:
        movie_id = input("Enter movie ID: ")
        time = input("Enter showtime: ")
        data = {"movie_id": movie_id, "time": time}
        res = requests.post("http://localhost:5003/showtime", json=data)
        print(res.json())
    elif choice == 2:
        res = requests.get("http://localhost:5003/showtimes")
        print(res.json())

def booking_service():
    print("\nBooking Service:")
    print("1. Book Ticket")
    print("2. View Bookings")
    choice = int(input("Enter choice: "))
    if choice == 1:
        user_id = input("Enter user ID: ")
        showtime_id = input("Enter showtime ID: ")
        data = {"user_id": user_id, "showtime_id": showtime_id}
        res = requests.post("http://localhost:5004/book", json=data)

        if res.headers.get('Content-Type') == 'application/json':
            print(res.json())
        else:
            print(f"Error: {res.status_code} - {res.text}")
    elif choice == 2:
        res = requests.get("http://localhost:5004/bookings")
        if res.headers.get('Content-Type') == 'application/json':
            print(res.json())
        else:
            print(f"Error: {res.status_code} - {res.text}")


def main_menu():
    while True:
        print("\nMain Menu:")
        print("1. User Service")
        print("2. Movie Service")
        print("3. Showtime Service")
        print("4. Booking Service")
        print("5. Exit")
        try:
            choice = int(input("Enter choice: "))
            if choice == 1:
                user_service()
            elif choice == 2:
                movie_service()
            elif choice == 3:
                showtime_service()
            elif choice == 4:
                booking_service()
            elif choice == 5:
                print("Exiting.")
                break
            else:
                print("Invalid choice!")
        except Exception as e:
            print("Error:", e)

if __name__ == "__main__":
    main_menu()
