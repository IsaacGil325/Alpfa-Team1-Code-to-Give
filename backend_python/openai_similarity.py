from openai import OpenAI
import os
import numpy as np
from pymongo import MongoClient
from pymongo.server_api import ServerApi
from bson import ObjectId

from sklearn.metrics.pairwise import cosine_similarity

open_ai_access_key = os.environ.get("")


# OpenAI API key setup


def get_embedding(text):
    response = client.embeddings.create(input=text, model="text-embedding-ada-002")
    return response.data[0].embedding


def calculate_similarity(candidate_text, job_text):
    candidate_embedding = get_embedding(candidate_text)
    job_embedding = get_embedding(job_text)

    candidate_embedding = np.array(candidate_embedding).reshape(1, -1)
    job_embedding = np.array(job_embedding).reshape(1, -1)

    similarity = cosine_similarity(candidate_embedding, job_embedding)[0][0]
    return similarity


def generate_summary(candidate, job):
    summary_text = (
        f"Candidate Name: {candidate['fullName']}\n"
        f"Skills: {', '.join(candidate['skills'])}\n"
        f"Experience: {', '.join([exp['jobTitle'] for exp in candidate['experience']])}\n"
        f"Why this candidate is a good fit for the job '{job['title']}': {job['description']}"
    )

    response = client.chat.completions.create(
        model="gpt-3.5-turbo",
        messages=[{"role": "user", "content": summary_text}],
        max_tokens=100,
    )
    return response.choices[0].message.content.strip()


def get_similarity_scores(candidates, job):
    job_text = f"Job Title: {job['title']}\nJob Description: {job['description']}\nRequired Skills: {job['requiredSkills']}"
    scores = []
    for candidate in candidates:
        candidate_text = f"Name: {candidate['fullName']}\nSkills: {', '.join(candidate['skills'])}\nExperience: {', '.join([exp['jobTitle'] for exp in candidate['experience']])}\nPreferred Location: {candidate['preferredLocation']}\nPreferred Job Type: {candidate['preferredJobType']}"
        similarity = calculate_similarity(candidate_text, job_text)

        # Match skills
        matched_skills = set(candidate["skills"]) & set(
            job["requiredSkills"].split(", ")
        )

        # Generate summary
        summary = generate_summary(candidate, job)

        scores.append(
            {
                "candidateName": candidate["fullName"],
                "similarityScore": similarity * 100,
                "matchedSkills": list(matched_skills),
                "summary": summary,
                "email": candidate["email"],
                "phone": candidate["phone"],
            }
        )

    scores = sorted(scores, key=lambda x: x["similarityScore"], reverse=True)

    return scores


def get_candidates(db):
    collection = db["candidates"]

    # Retrieve all candidates
    candidates = list(collection.find())

    return candidates


def get_job_posting(db, job_id, sponsor_id):
    collection = db["job_postings"]

    job_posting = collection.find_one(
        {"jobId": str(job_id), "sponsorId": str(sponsor_id)}
    )
    print(job_posting)

    return job_posting


def get_all_job_postings(db, sponsor_id):
    collection = db["job_postings"]

    job_postings = list(collection.find({}, {"_id": 0}))
    # job_postings = {i: x for i, x in enumerate(job_postings)}
    print(job_postings)

    return job_postings


# # Print the results
# for score in similarity_scores:
#     print(
#         f"Candidate: {score['candidateName']}, Similarity Score: {score['similarityScore']*100}"
#     )
#     print(f"Email: {score['email']}, Phone: {score['phone']}")
#     print(f"Matched Skills: {', '.join(score['matchedSkills'])}")
#     print(f"Summary: {score['summary']}\n")
