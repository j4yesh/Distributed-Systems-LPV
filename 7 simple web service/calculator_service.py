from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/calculate', methods=['GET'])
def calculate():
    try:
        a = float(request.args.get('a'))
        b = float(request.args.get('b'))
        
        result = {
            "addition": a + b,
            "subtraction": a - b,
            "multiplication": a * b,
            "division": a / b if b != 0 else "Division by zero error"
        }
        return jsonify(result)
    except (TypeError, ValueError):
        return jsonify({"error": "Invalid input. Please provide numeric 'a' and 'b' as query parameters."}), 400

if __name__ == '__main__':
    app.run(debug=True, port=5001)
