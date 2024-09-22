from openai import OpenAI
import numpy as np
import os
from sklearn.metrics.pairwise import cosine_similarity
from dotenv import load_dotenv

# OpenAI API key setup
load_dotenv()
client = OpenAI(api_key= os.getenv("OPENAI_API_KEY"))



def get_embedding(text):
    """
    This function takes a string of text and returns the text embedding using OpenAI's embedding model.
    """
    response = client.embeddings.create(input=text, model="text-embedding-ada-002")
    return response.data[0].embedding

def calculate_similarity(candidate_text, job_text):
    """
    This function calculates the cosine similarity between the candidate info and the job description.
    """
    candidate_embedding = get_embedding(candidate_text)
    job_embedding = get_embedding(job_text)

    # Reshape embeddings for similarity calculation
    candidate_embedding = np.array(candidate_embedding).reshape(1, -1)
    job_embedding = np.array(job_embedding).reshape(1, -1)

    # Calculate cosine similarity
    similarity = cosine_similarity(candidate_embedding, job_embedding)[0][0]
    return similarity

def get_similarity_scores(candidates, job):
    """
    This function calculates the similarity score for each candidate in the list
    by comparing the candidate's information with the job details.
    """
    job_text = f"Job Title: {job['title']}\nJob Description: {job['description']}\nRequired Skills: {', '.join(job['requiredSkills'])}"

    scores = []
    for candidate in candidates:
        candidate_text = f"Name: {candidate['fullName']}\nSkills: {', '.join(candidate['skills'])}\nExperience: {', '.join([exp['jobTitle'] for exp in candidate['experience']])}\nPreferred Location: {candidate['preferredLocation']}\nPreferred Job Type: {candidate['preferredJobType']}"
        similarity = calculate_similarity(candidate_text, job_text)
        scores.append({'candidateName': candidate['fullName'], 'similarityScore': similarity})

    return scores

# Sample input data
candidates = [
    {
        "fullName": "Alice Smith",
        "skills": ["Python", "Machine Learning", "Data Analysis"],
        "experience": [{"jobTitle": "Data Scientist", "company": "TechCorp", "duration": "2 years"}],
        "preferredLocation": "Remote",
        "preferredJobType": "Full-time"
    },
    {
        "fullName": "Bob Johnson",
        "skills": ["Java", "Spring Boot", "Microservices"],
        "experience": [{"jobTitle": "Software Engineer", "company": "CloudOps", "duration": "3 years"}],
        "preferredLocation": "New York",
        "preferredJobType": "Full-time"
    }
]

job = {
    "title": "Machine Learning Engineer",
    "description": "We are looking for a skilled Machine Learning Engineer to join our AI team.",
    "requiredSkills": ["Python", "Machine Learning", "TensorFlow"]
}

# Get similarity scores
similarity_scores = get_similarity_scores(candidates, job)

# Print the results
for score in similarity_scores:
    print(f"Candidate: {score['candidateName']}, Similarity Score: {score['similarityScore']}")