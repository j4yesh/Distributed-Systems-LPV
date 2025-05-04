from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/divide', methods=['GET'])
def divide():
    try:
        a = float(request.args.get('a'))
        b = float(request.args.get('b'))
        if b == 0:
            return jsonify({"result": "Division by zero error"})
        return jsonify({"result": a / b})
    except:
        return jsonify({"error": "Invalid input"}), 400

if __name__ == '__main__':
    app.run(port=5005)
