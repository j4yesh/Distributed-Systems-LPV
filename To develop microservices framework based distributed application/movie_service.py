from flask import Flask, request, jsonify

app = Flask(__name__)
movies = []

@app.route('/movie', methods=['POST'])
def add_movie():
    data = request.get_json()
    movies.append(data)
    return jsonify({"message": "Movie added"}), 201

@app.route('/movies', methods=['GET'])
def get_movies():
    return jsonify(movies)

if __name__ == '__main__':
    app.run(port=5002)
