import pickle
import numpy as np
from flask import Flask, request, jsonify

app = Flask(__name__)

# Load model
model = pickle.load(open("diabetes_model.pkl", "rb"))

@app.route("/predict", methods=["POST"])
def predict():
    data = request.json["features"]

    input_data = np.array(data).reshape(1, -1)

    prediction = model.predict(input_data)

    return jsonify({
        "prediction": int(prediction[0])
    })

if __name__ == "__main__":
    app.run(port=5000)