import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";

import AdminLayout from "../layouts/AdminLayout";
import FacultyLayout from "../layouts/FacultyLayout";
import StudentLayout from "../layouts/StudentLayout";

const ProtectedRoute = ({ allowedRoles }) => {
  const { role } = useSelector((state) => state.auth);
  const token = localStorage.getItem("token");


  console.log("Role from redux:", role);
  console.log("Allowed roles:", allowedRoles);

  
  // logs for debugging
  // console.log("Role from redux:", role);
  // console.log("Allowed roles:", allowedRoles);

  // 1️⃣ Not logged in
  if (!token) {
    return <Navigate to="/" replace />;
  }

  // 2️⃣ Role not allowed
  if (allowedRoles && !allowedRoles.includes(role)) {
    return <Navigate to="/unauthorized" replace />;
  }

  // 3️⃣ Load Layout Based On Role
  if (role === "ADMIN") {
    return (
      <AdminLayout>
        <Outlet />
      </AdminLayout>
    );
  }

  if (role === "ADMIN") {
    return (
      <FacultyLayout>
        <Outlet />
      </FacultyLayout>
    );
  }

  if (role === "ADMIN") {
    return (
      <StudentLayout>
        <Outlet />
      </StudentLayout>
    );
  }

  return <Navigate to="/" replace />;
};

export default ProtectedRoute;
