// import React, { useState } from 'react';
// import 'bootstrap/dist/css/bootstrap.min.css';

// interface Candidate {
//   name: string;
//   similarityScore: number;
//   matchedSkills: string[];
//   summary: string;
// }

// const candidates: Candidate[] = [
//   {
//     name: "Michael Clark",
//     similarityScore: 0.7819,
//     matchedSkills: ["Node.js", "AWS"],
//     summary: "Michael Clark is a strong candidate for the Data Analyst position because of his experience as a Full-Stack Developer and Frontend Developer. With his skills in JavaScript, Node.js, and React, he has a strong foundation in programming and data manipulation. Additionally, his experience with AWS shows that he has experience working with large datasets and is familiar with cloud computing technologies. His background in creating reports and working with data as a developer makes him well-equipped to analyze large datasets and extract meaningful insights. He is"
//   },
//   {
//     name: "Sara Thompson",
//     similarityScore: 0.8340,
//     matchedSkills: ["Python"],
//     summary: "Sara Thompson would be an excellent fit for the job of a Data Analyst due to her skills and experience in Python, data analysis, and machine learning. With her background as a Data Analyst and Junior Data Scientist, Sara has experience in analyzing large datasets, creating reports, and utilizing machine learning techniques to extract valuable insights from data. Sara's expertise in Python and data analysis will enable her to efficiently manipulate and analyze complex datasets, while her knowledge of machine learning will allow her to apply advanced statistical"
//   },
//   {
//     name: "John Doe",
//     similarityScore: 0.7845,
//     matchedSkills: ["Java"],
//     summary: "John Doe is a strong candidate for the Data Analyst position because of his extensive background as a Backend Developer and Software Engineer. His skills in Java, Spring Boot, and Microservices demonstrate his ability to work with large datasets and analyze complex information. John's experience in creating reports and building software solutions makes him well-suited to the role of a Data Analyst, where he can leverage his expertise to extract valuable insights from data and help the business make informed, data-driven decisions. With his strong technical skills"
//   },
//   {
//     name: "Emily Davis",
//     similarityScore: 0.7805,
//     matchedSkills: [],
//     summary: "Emily Davis is a strong candidate for the Data Analyst position due to her expertise in HTML, CSS, JavaScript, and React. As a Frontend Developer and Web Designer, she has experience working with data visualization and creating user-friendly interfaces. Her skills in data manipulation and visualization make her well-equipped to analyze large datasets and generate reports that can help the business make informed, data-driven decisions. Additionally, her experience working on the frontend of websites demonstrates attention to detail and an ability to problem-solve effectively,"
//   },
//   {
//     name: "Robert Brown",
//     similarityScore: 0.7595,
//     matchedSkills: [],
//     summary: "Robert Brown is a strong candidate for the Data Analyst position due to his skills and experience in embedded systems and IoT. This background in working with complex systems and large amounts of data makes him well-equipped to handle datasets and create reports for data analysis. Robert has experience working with C++, a programming language commonly used in data analysis, and as a Junior Software Engineer, he has honed his analytical skills and attention to detail. With his expertise in embedded systems, Robert understands how to extract valuable insights from data"
//   }
// ];

// // Sort candidates by similarityScore in descending order
// const sortedCandidates = candidates.sort((a, b) => b.similarityScore - a.similarityScore);

// const CandidateScores: React.FC = () => {
//   const [selectedSummary, setSelectedSummary] = useState<string | null>(null);

//   const handleNameClick = (summary: string) => {
//     setSelectedSummary(summary);
//   };

//   return (
//     <div className="container mt-4">
//       <div className="row">
//         <div className="col-md-8">
//           <h2>Candidate Scores</h2>
//           <table className="table table-bordered">
//             <thead className="thead-light">
//               <tr>
//                 <th>Name</th>
//                 <th>Similarity Score</th>
//               </tr>
//             </thead>
//             <tbody>
//               {sortedCandidates.map((candidate, index) => (
//                 <tr key={index}>
//                   <td>
//                     <button
//                       className="btn btn-link p-0"
//                       onClick={() => handleNameClick(candidate.summary)}
//                     >
//                       {candidate.name}
//                     </button>
//                   </td>
//                   <td>{Math.round(candidate.similarityScore * 100)}</td>
//                 </tr>
//               ))}
//             </tbody>
//           </table>
//         </div>
//         <div className="col-md-4">
//           <h2>Job Description</h2>
//           <p>This is a sample job description.</p>
//         </div>
//       </div>
//       <div className="row mt-4">
//         <div className="col-12">
//           <div className="border p-3">
//             {selectedSummary ? (
//               <p>{selectedSummary}</p>
//             ) : (
//               <p>Select a candidate to see their summary.</p>
//             )}
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default CandidateScores;
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

interface Candidate {
  name: string;
  similarityScore: number;
  matchedSkills: string[];
  summary: string;
}

const candidates: Candidate[] = [
  {
    name: "Michael Clark",
    similarityScore: 0.7819,
    matchedSkills: ["Node.js", "AWS"],
    summary: "Michael Clark is a strong candidate for the Data Analyst position because of his experience as a Full-Stack Developer and Frontend Developer. With his skills in JavaScript, Node.js, and React, he has a strong foundation in programming and data manipulation. Additionally, his experience with AWS shows that he has experience working with large datasets and is familiar with cloud computing technologies. His background in creating reports and working with data as a developer makes him well-equipped to analyze large datasets and extract meaningful insights. He is"
  },
  {
    name: "Sara Thompson",
    similarityScore: 0.8340,
    matchedSkills: ["Python"],
    summary: "Sara Thompson would be an excellent fit for the job of a Data Analyst due to her skills and experience in Python, data analysis, and machine learning. With her background as a Data Analyst and Junior Data Scientist, Sara has experience in analyzing large datasets, creating reports, and utilizing machine learning techniques to extract valuable insights from data. Sara's expertise in Python and data analysis will enable her to efficiently manipulate and analyze complex datasets, while her knowledge of machine learning will allow her to apply advanced statistical"
  },
  {
    name: "John Doe",
    similarityScore: 0.7845,
    matchedSkills: ["Java"],
    summary: "John Doe is a strong candidate for the Data Analyst position because of his extensive background as a Backend Developer and Software Engineer. His skills in Java, Spring Boot, and Microservices demonstrate his ability to work with large datasets and analyze complex information. John's experience in creating reports and building software solutions makes him well-suited to the role of a Data Analyst, where he can leverage his expertise to extract valuable insights from data and help the business make informed, data-driven decisions. With his strong technical skills"
  },
  {
    name: "Emily Davis",
    similarityScore: 0.7805,
    matchedSkills: [],
    summary: "Emily Davis is a strong candidate for the Data Analyst position due to her expertise in HTML, CSS, JavaScript, and React. As a Frontend Developer and Web Designer, she has experience working with data visualization and creating user-friendly interfaces. Her skills in data manipulation and visualization make her well-equipped to analyze large datasets and generate reports that can help the business make informed, data-driven decisions. Additionally, her experience working on the frontend of websites demonstrates attention to detail and an ability to problem-solve effectively,"
  },
  {
    name: "Robert Brown",
    similarityScore: 0.7595,
    matchedSkills: [],
    summary: "Robert Brown is a strong candidate for the Data Analyst position due to his skills and experience in embedded systems and IoT. This background in working with complex systems and large amounts of data makes him well-equipped to handle datasets and create reports for data analysis. Robert has experience working with C++, a programming language commonly used in data analysis, and as a Junior Software Engineer, he has honed his analytical skills and attention to detail. With his expertise in embedded systems, Robert understands how to extract valuable insights from data"
  }
];

// Sort candidates by similarityScore in descending order
const sortedCandidates = candidates.sort((a, b) => b.similarityScore - a.similarityScore);

const CandidateScores: React.FC = () => {
  const [selectedCandidate, setSelectedCandidate] = useState<Candidate | null>(null);

  const handleNameClick = (candidate: Candidate) => {
    setSelectedCandidate(candidate);
  };

  return (
    <div className="container mt-4">
      <div className="row">
        <div className="col-md-8">
          <h2>Candidate Scores</h2>
          <table className="table table-bordered">
            <thead className="thead-light">
              <tr>
                <th>Name</th>
                <th>Similarity Score</th>
              </tr>
            </thead>
            <tbody>
              {sortedCandidates.map((candidate, index) => (
                <tr key={index}>
                  <td>
                    <button
                      className="btn btn-link p-0"
                      onClick={() => handleNameClick(candidate)}
                    >
                      {candidate.name}
                    </button>
                  </td>
                  <td>{Math.round(candidate.similarityScore * 100)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="col-md-4">
          <h2>Job Description</h2>
          <p>This is a sample job description.</p>
        </div>
      </div>
      <div className="row mt-4">
        <div className="col-12">
          <div className="border p-3">
            {selectedCandidate ? (
              <>
                <h3>Matched Skills</h3>
                <p>{selectedCandidate.matchedSkills.join(', ') || 'No matched skills'}</p>
                <h3>Summary</h3>
                <p>{selectedCandidate.summary}</p>
              </>
            ) : (
              <p>Select a candidate to see their summary.</p>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default CandidateScores;