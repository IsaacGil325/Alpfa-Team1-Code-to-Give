from openai import OpenAI
import os

import PyPDF2
import json
from datetime import datetime
import os
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

# Set up OpenAI API key
client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

class Candidate:
    def __init__(self, id, user, full_name, email, phone, skills, experience, education, certifications, human_aspects, resume_url, preferred_location, preferred_job_type, created_at, updated_at):
        self.id = id
        self.user = user
        self.full_name = full_name
        self.email = email
        self.phone = phone
        self.skills = skills
        self.experience = experience
        self.education = education
        self.certifications = certifications
        self.human_aspects = human_aspects
        self.resume_url = resume_url
        self.preferred_location = preferred_location
        self.preferred_job_type = preferred_job_type
        self.created_at = created_at
        self.updated_at = updated_at

class Experience:
    def __init__(self, job_title, company, duration, description):
        self.job_title = job_title
        self.company = company
        self.duration = duration
        self.description = description

class Education:
    def __init__(self, degree, institution, graduation_year):
        self.degree = degree
        self.institution = institution
        self.graduation_year = graduation_year

# Function to extract text from a PDF
def extract_text_from_pdf(pdf_path):
    with open(pdf_path, 'rb') as file:
        reader = PyPDF2.PdfReader(file)
        text = ''
        for page in range(len(reader.pages)):
            text += reader.pages[page].extract_text()
        return text

# Function to call OpenAI API for parsing resume
def parse_resume(text):
    prompt = f"""
    Extract the following details from this resume text:
    - Full Name
    - Email
    - Phone
    - Skills
    - Experience (Job Title, Company, Duration, Description)
    - Education (Degree, Institution, Graduation Year)
    - Certifications
    - Human Aspects
    - Preferred Location
    - Preferred Job Type

    Resume Text:
    {text}
    """

    response = client.chat.completions.create(model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "You are a resume parser. Return the result in json format."},
        {"role": "user", "content": prompt}
    ])

    parsed_resume = response.choices[0].message.content
    return parsed_resume



# Function to create Candidate object from parsed data
def create_candidate_object(data):
    experiences = [Experience(e["Job Title"], e["Company"], e["Duration"], e["Description"]) for e in data["Experience"]]
    
    # Parse education
    education = Education(
        degree=data["Education"]["Degree"],
        institution=data["Education"]["Institution"],
        graduation_year=data["Education"]["Graduation Year"]
    )
    
    # Create candidate object
    candidate = Candidate(
        id=1,  
        user=None,  
        full_name=data["Full Name"],
        email=data["Email"],
        phone=data["Phone"],
        skills=data["Skills"],
        experience=experiences,
        education=education,
        certifications=data["Certifications"],
        human_aspects=data["Human Aspects"],
        resume_url=None,  
        preferred_location=data["Preferred Location"],
        preferred_job_type=data["Preferred Job Type"],
        created_at=datetime.datetime.now(),
        updated_at=datetime.datetime.now()
    )
    
    return candidate


# Main function to process resume PDF and return Candidate object
def process_resume_pdf(pdf_path):
    # Step 1: Extract text from PDF
    resume_text = extract_text_from_pdf(pdf_path)

    # Step 2: Parse resume text using OpenAI
    parsed_data = parse_resume(resume_text)

    return parsed_data

# Example usage:
if __name__ == "__main__":
    # Include the resume file path here
    pdf_path = "/Users/atharva/Downloads/resume_sample_student.pdf"
    candidate = process_resume_pdf(pdf_path)
    print(candidate)
