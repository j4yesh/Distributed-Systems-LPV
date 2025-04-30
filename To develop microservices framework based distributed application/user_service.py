from flask import Flask, request, jsonify

app = Flask(__name__)
users = []

@app.route('/user', methods=['POST'])
def add_user():
    data = request.get_json()
    users.append(data)
    return jsonify({"message": "User added"}), 201

@app.route('/users', methods=['GET'])
def get_users():
    return jsonify(users)

if __name__ == '__main__':
    app.run(port=5001)
