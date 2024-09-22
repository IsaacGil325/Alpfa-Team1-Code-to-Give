from flask import Flask, render_template, request, jsonify
from pymongo import MongoClient
from pymongo.server_api import ServerApi
from datetime import datetime

from openai_similarity import (
    get_candidates,
    get_job_posting,
    get_similarity_scores,
    get_all_job_postings,
)

app = Flask(__name__)

uri = "mongodb+srv://atharva:atharvarockx@cluster0.ggr6c.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

# Create a new client and connect to the server
mongo_client = MongoClient(uri, server_api=ServerApi("1"))
db = mongo_client["alpfadb"]


# Define your routes (endpoints)
@app.route("/")
def home():
    return "Hello, Flask!"


@app.route("/")
def landing_page():
    return render_template("landing.html")


@app.route("/candidate", methods=["POST"])
def save_candidate_profile():
    candidate_data = request.json
    candidate_data["createdAt"] = datetime.datetime.utcnow()
    candidate_data["updatedAt"] = datetime.datetime.utcnow()
    db.candidates.insert_one(candidate_data)
    return jsonify({"message": "Candidate profile saved successfully!"}), 201


# 3) Route to upload candidate resume and parse data
@app.route("/upload_resume", methods=["POST"])
def upload_resume():
    file = request.files["resume"]
    # Parse resume data here (using a library like PyPDF2 or similar)
    # Save the parsed data to MongoDB
    return jsonify({"message": "Resume uploaded and parsed successfully!"}), 200


# 4) Route to fill form for job posting and save job posting data
@app.route("/job_posting", methods=["POST"])
def save_job_posting():
    job_posting_data = request.json
    job_posting_data["createdAt"] = datetime.datetime.utcnow()
    job_posting_data["updatedAt"] = datetime.datetime.utcnow()
    db.jobPostings.insert_one(job_posting_data)
    return jsonify({"message": "Job posting saved successfully!"}), 201


# 5) Route to fill form and save sponsor data
@app.route("/sponsor", methods=["POST"])
def save_sponsor():
    sponsor_data = request.json
    sponsor_data["createdAt"] = datetime.datetime.utcnow()
    sponsor_data["updatedAt"] = datetime.datetime.utcnow()
    db.sponsors.insert_one(sponsor_data)
    return jsonify({"message": "Sponsor data saved successfully!"}), 201


# 6) Sponsor Dashboard (contains list of sponsor job postings)
@app.route("/sponsor_dashboard/<sponsor_id>")
def sponsor_dashboard(sponsor_id):
    sponsor = db.sponsors.find_one({"_id": sponsor_id})
    job_postings = db.jobPostings.find({"sponsor": sponsor_id})
    return render_template(
        "sponsor_dashboard.html", sponsor=sponsor, job_postings=job_postings
    )


# 7) Route for each job posting made by sponsor (sponsor and job ID)
@app.route("/sponsor/<sponsor_id>/job/<job_id>")
def job_posting_details(sponsor_id, job_id):
    # Call get_candidates and get_job_posting functions
    candidates = get_candidates(db)
    job_posting_details = get_job_posting(db, job_id, sponsor_id)

    # Calculate similarity scores
    similarity_scores = get_similarity_scores(candidates, job_posting_details)

    # Render the template and pass job posting and similarity scores
    return jsonify(similarity_scores), 200


# 8) Candidate Dashboard
@app.route("/candidate_dashboard/<candidate_id>")
def candidate_dashboard(candidate_id):
    candidate = db.candidates.find_one({"_id": candidate_id})
    return render_template("candidate_dashboard.html", candidate=candidate)


# 9) Route to get all job postings for a sponsor
@app.route("/sponsor/<sponsor_id>/job_postings", methods=["GET"])
def get_job_postings(sponsor_id):
    postings = get_all_job_postings(db, sponsor_id)
    return postings, 200


if __name__ == "__main__":
    app.run(debug=True)
