import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProtectedRoute from "../components/ProtectedRoute";

import Login from "../features/auth/Login";
import Signup from "../features/auth/Signup";

import AdminDashboard from "../pages/AdminDashboard";
import FacultyDashboard from "../pages/TeacherDashboard";
import StudentDashboard from "../pages/StudentDashboard";
import Placeholder from "../pages/Placeholder";

import Unauthorized from "../pages/Unauthorized";
import NotFound from "../pages/NotFound";

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public */}
        <Route path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/unauthorized" element={<Unauthorized />} />

        {/* ADMIN ROUTES */}
        <Route element={<ProtectedRoute allowedRoles={["ADMIN"]} />}>
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
          <Route path="/admin/students" element={<StudentDashboard />} />
          <Route path="/admin/faculty" element={<FacultyDashboard />} />
          <Route path="/admin/departments" element={<Placeholder title="Departments" />} />
          <Route path="/admin/courses" element={<Placeholder title="Courses" />} />
          <Route path="/admin/fees" element={<Placeholder title="Fees" />} />
        </Route>

        {/* FACULTY ROUTES */}
        <Route element={<ProtectedRoute allowedRoles={["FACULTY"]} />}>
          <Route path="/faculty/dashboard" element={<FacultyDashboard />} />
          <Route path="/faculty/attendance" element={<Placeholder title="Faculty Attendance" />} />
          <Route path="/faculty/exams" element={<Placeholder title="Exams" />} />
          <Route path="/faculty/students" element={<Placeholder title="Faculty Students" />} />
        </Route>

        {/* STUDENT ROUTES */}
        <Route element={<ProtectedRoute allowedRoles={["STUDENT"]} />}>
          <Route path="/student/dashboard" element={<StudentDashboard />} />
          <Route path="/student/courses" element={<Placeholder title="Student Courses" />} />
          <Route path="/student/attendance" element={<Placeholder title="Student Attendance" />} />
          <Route path="/student/fees" element={<Placeholder title="Student Fees" />} />
        </Route>

        {/* 404 */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default AppRoutes;
