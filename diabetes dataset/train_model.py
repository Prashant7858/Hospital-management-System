import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
import pickle

# Load dataset
df = pd.read_csv("diabetes_dataset.csv")

# Drop missing values
df = df.dropna()

# ✅ SELECT ONLY SIMPLE COLUMNS (ADD THIS HERE)
df = df[[
    "Age",
    "BMI",
    "Fasting_Blood_Glucose",
    "HbA1c",
    "Previous_Gestational_Diabetes"
]]

# Target
y = df["Previous_Gestational_Diabetes"]

# Features
X = df.drop("Previous_Gestational_Diabetes", axis=1)

# Split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

# Train model
model = RandomForestClassifier()
model.fit(X_train, y_train)

# Save model
pickle.dump(model, open("diabetes_model.pkl", "wb"))

print("✅ Model created successfully")