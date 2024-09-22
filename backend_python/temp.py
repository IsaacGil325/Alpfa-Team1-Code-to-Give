from pymongo.mongo_client import MongoClient
from pymongo.server_api import ServerApi

# MongoDB URI and server API version
uri = "mongodb+srv://atharva:atharvarockx@cluster0.ggr6c.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

# Create a new client and connect to the server
client = MongoClient(uri, server_api=ServerApi("1"))
db = client["alpfadb"]


collection = db["job_postings"]

# Retrieve all candidates
candidates = list(collection.find())[0]


print(candidates)
