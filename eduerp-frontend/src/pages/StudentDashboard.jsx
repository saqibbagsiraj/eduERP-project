import React, { useEffect, useState } from "react";
import axios from "axios";

export default function StudentDashboard() {

  const [student, setStudent] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {

    const fetchStudent = async () => {
      try {

        const token = localStorage.getItem("token"); // token stored after login

        const res = await axios.get(
          "http://localhost:8080/api/students",
          {
            headers: {
              Authorization: `Bearer ${token}`
            }
          }
        );

        setStudent(res.data[0] || null);

      } catch (error) {
        console.error("Failed to load student data", error);
      } finally {
        setLoading(false);
      }
    };

    fetchStudent();

  }, []);

  if (loading) {
    return <h2>Loading...</h2>;
  }

  return (
    <div style={{ padding: "30px", fontFamily: "Arial" }}>
      <h1>Student Dashboard</h1>

      <table
        border="1"
        cellPadding="10"
        style={{
          borderCollapse: "collapse",
          marginTop: "20px",
          width: "500px"
        }}
      >
        <thead>
          <tr style={{ background: "#f2f2f2" }}>
            <th>Field</th>
            <th>Details</th>
          </tr>
        </thead>

        <tbody>
          <tr>
            <td>Name</td>
            <td>{student?.user?.name || "null"}</td>
          </tr>

          <tr>
            <td>Email</td>
            <td>{student?.user?.email || "null"}</td>
          </tr>

          <tr>
            <td>Roll No</td>
            <td>{student?.rollNo || "null"}</td>
          </tr>

          <tr>
            <td>Phone</td>
            <td>{student?.phone || "null"}</td>
          </tr>

          <tr>
            <td>Department</td>
            <td>{student?.department?.name || "null"}</td>
          </tr>

          <tr>
            <td>Role</td>
            <td>{student?.user?.role?.name || "null"}</td>
          </tr>

          <tr>
            <td>Created At</td>
            <td>{student?.user?.createdAt || "null"}</td>
          </tr>
        </tbody>
      </table>

    </div>
  );
}