from flask import Flask, request, jsonify

app = Flask(__name__)
showtimes = []

@app.route('/showtime', methods=['POST'])
def add_showtime():
    data = request.get_json()
    showtimes.append(data)
    return jsonify({"message": "Showtime added"}), 201

@app.route('/showtimes', methods=['GET'])
def get_showtimes():
    return jsonify(showtimes)

if __name__ == '__main__':
    app.run(port=5003)
