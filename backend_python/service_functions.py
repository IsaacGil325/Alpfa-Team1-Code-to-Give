import json
from openai import OpenAI
import os
import openai
from pymongo import MongoClient
from pymongo.server_api import ServerApi


uri = "mongodb+srv://username:password@cluster0.ggr6c.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

# Create a new client and connect to the server
mongo_client = MongoClient(uri, server_api=ServerApi("1"))
db = mongo_client["alpfadb"]

open_ai_access_key = os.environ.get("")


client = OpenAI(api_key="open_api_key")

openai.api_key = "open_api_key"

EMBEDDING_MODEL = "text-embedding-3-small"


def vector_search(query_embedding):
    """
    Perform a vector search in the MongoDB collection based on the user query.

    Args:
    user_query (str): The user's query string.
    collection (MongoCollection): The MongoDB collection to search.

    Returns:
    list: A list of matching documents.

    credit to: https://www.mongodb.com/developer/products/atlas/using-openai-latest-embeddings-rag-system-mongodb/
    """
    collection = db["embedded_candidates"]

    if query_embedding is None:
        return "Invalid query or embedding generation failed."

    # Define the vector search pipeline
    pipeline = [
        {
            "$vectorSearch": {
                "index": "vector_index",
                "queryVector": query_embedding,
                "path": "embedding",
                "numCandidates": 150,  # Number of candidate matches to consider
                "limit": 5,  # Return top 5 matches
            }
        },
        {
            "$project": {
                "_id": 0,  # Exclude the _id field
                "embedding": 0,  # Exclude the plot_embedding_opitimzed field
                "score": {
                    "$meta": "vectorSearchScore"  # Include the search score
                },
            }
        },
    ]

    # Execute the search
    results = collection.aggregate(pipeline)
    return list(results)


def insert_candidate_vector_to_collection(candidate_vector):
    collection = db["embedded_candidates"]
    collection.insert_one(candidate_vector)


def get_embedding(info_dictionary):
    # Convert the candidate dict to a JSON string
    info_string = json.dumps(info_dictionary)

    if not info_string or not isinstance(info_string, str):
        return None

    try:
        # Call OpenAI API to get the embedding
        embedding = (
            openai.embeddings.create(input=info_string, model=EMBEDDING_MODEL)
            .data[0]
            .embedding
        )
        return embedding
    except Exception as e:
        print(f"Error in get_embedding: {e}")
        return None


def get_candidates(query=None):
    collection = db["candidates"]

    if not query:
        # Retrieve all candidates
        candidates = list(
            collection.find(
                {},
                {
                    "id": 1,
                    "skills": 1,
                    "areasInterested": 1,
                    "experience": 1,
                    "education": 1,
                    "certifications": 1,
                    "human_aspects": 1,
                    "preferredLocation": 1,
                    "preferredJobType": 1,
                    "_id": 0,
                },
            )
        )

    else:
        candidates = list(
            collection.find(
                query,
                {
                    "_id": 0,
                },
            )
        )

    return candidates


# candidates = get_candidates()


# # Add embeddings to each candidate
# for candidate in candidates:
#     print(candidate)
#     candidate_id = candidate["id"]
#     candidate.pop("id")
#     embedding = get_embedding(candidate)
#     if not embedding:
#         print(f"{candidate['id']} has no embedding")
#         continue
#     insert_candidate_vector_to_collection({"id": candidate_id, "embedding": embedding})


# Now we have made vector database for candidates
##################################################################################################
# Time to add a job desc


def parse_job_desc():
    """
    Given a job desc, summarize it down to the following fields: title, short_summary, location, fulltime_or_parttime, required_skills.
    """
    pass


def get_job_posting(job_id, sponsor_id):
    collection = db["job_postings"]

    job_posting = collection.find_one(
        {"jobId": str(job_id), "sponsorId": str(sponsor_id)},
        {"sponsor": 0, "_id": 0, "createdAt": 0, "updatedAt": 0},
    )
    print(job_posting)

    return job_posting


def get_all_job_postings_for_sponsor(sponsor_id):
    collection = db["job_postings"]

    job_postings = collection.find(
        {"sponsorId": str(sponsor_id)},
        {"_id": 0},
    )
    print(job_postings)

    return job_postings


def reasoning(candidate, posting):
    completion = client.chat.completions.create(
        model="gpt-4o",
        messages=[
            {
                "role": "system",
                "content": "You are a job recruiter helping give reasoning for why a candidate is a good fit for a role. They might not be a perfect fit so don't exaggerate, just tell it how it is. Give your reasoning in 3 to 4 sentences.",
            },
            {
                "role": "user",
                "content": f"Here is the job posting: {posting}. Here is the candidate info: {candidate}",
            },
        ],
    )
    return completion.choices[0].message.content


def find_best_candidates_for_job(job_id, sponsor_id):
    posting = get_job_posting(job_id, sponsor_id)

    posting_embedding = get_embedding(posting)

    results = vector_search(posting_embedding)

    top_candidates = [x["id"] for x in results]
    scores = {x["id"]: f"{x['score']*100:1f}" for x in results}

    top_candidates_details = get_candidates(query={"id": {"$in": top_candidates}})

    for candidate in top_candidates_details:
        candidate["summary"] = reasoning(candidate, posting)
        candidate["similarityScore"] = scores[candidate["id"]]
        candidate.pop("user")

    print(top_candidates_details)


find_best_candidates_for_job(1, 1)
