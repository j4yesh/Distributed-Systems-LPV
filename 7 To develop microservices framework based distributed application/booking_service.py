from flask import Flask, request, jsonify

app = Flask(__name__)

bookings = []

@app.route('/book', methods=['POST'])
def book_ticket():
    data = request.get_json()
    user_id = data.get('user_id')
    showtime_id = data.get('showtime_id')

    if not user_id or not showtime_id:
        return jsonify({"error": "Missing user_id or showtime_id"}), 400

    booking = {
        "user_id": user_id,
        "showtime_id": showtime_id
    }
    bookings.append(booking)
    return jsonify({"message": "Booking successful", "booking": booking})

@app.route('/bookings', methods=['GET'])
def get_bookings():
    return jsonify(bookings)

if __name__ == '__main__':
    app.run(port=5004)
