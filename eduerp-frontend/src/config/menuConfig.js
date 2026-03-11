import DashboardIcon from "@mui/icons-material/Dashboard";
import SchoolIcon from "@mui/icons-material/School";
import PeopleIcon from "@mui/icons-material/People";
import AccountBalanceIcon from "@mui/icons-material/AccountBalance";
import BookIcon from "@mui/icons-material/Book";
import PaymentsIcon from "@mui/icons-material/Payments";

export const menuConfig = {
  ADMIN: [
    { label: "Dashboard", path: "/admin/dashboard", icon: DashboardIcon },
    { label: "Students", path: "/admin/students", icon: SchoolIcon },
    { label: "Faculty", path: "/admin/faculty", icon: PeopleIcon },
    { label: "Departments", path: "/admin/departments", icon: AccountBalanceIcon },
    { label: "Courses", path: "/admin/courses", icon: BookIcon },
    { label: "Fees", path: "/admin/fees", icon: PaymentsIcon }
  ],

  FACULTY: [
    { label: "Dashboard", path: "/faculty/dashboard", icon: DashboardIcon },
    { label: "Attendance", path: "/faculty/attendance", icon: SchoolIcon },
    { label: "Exams", path: "/faculty/exams", icon: BookIcon },
    { label: "Students", path: "/faculty/students", icon: PeopleIcon }
  ],

  STUDENT: [
    { label: "Dashboard", path: "/student/dashboard", icon: DashboardIcon },
    { label: "Courses", path: "/student/courses", icon: BookIcon },
    { label: "Attendance", path: "/student/attendance", icon: SchoolIcon },
    { label: "Fees", path: "/student/fees", icon: PaymentsIcon }
  ]
};