import Grid from "@mui/material/Grid";
import { Box, Typography, Paper } from "@mui/material";
import DashboardCard from "../components/DashboardCard";

import PeopleIcon from "@mui/icons-material/People";
import SchoolIcon from "@mui/icons-material/School";
import MenuBookIcon from "@mui/icons-material/MenuBook";
import PaymentsIcon from "@mui/icons-material/Payments";

import { useEffect, useState } from "react";
import axios from "../services/axiosInstance";

import StudentGrowthChart from "../components/StudentGrowthChart";
import RevenueChart from "../components/RevenueChart";
import RecentUsersTable from "../components/RecentUsersTable";
import AdminQuickActions from "../components/AdminQuickActions";

export default function AdminDashboard() {

  const [stats, setStats] = useState({
    totalStudents: 0,
    totalFaculty: 0,
    totalCourses: 0,
    totalRevenue: 0
  });

  const [loading, setLoading] = useState(true);

  useEffect(() => {

    const fetchStats = async () => {
      try {
        const res = await axios.get("/admin/stats");
        setStats(res.data);
      } catch (error) {
        console.error("Failed to load admin stats", error);
      } finally {
        setLoading(false);
      }
    };

    fetchStats();

  }, []);

  return (

    <Box sx={{ p: 4 }}>

      <Typography variant="h4" mb={3} fontWeight="bold">
        Admin Dashboard
      </Typography>

      {/* KPI CARDS */}

      <Grid container spacing={3}>

        <Grid size={{ xs: 12, md: 6, lg: 3 }}>
          <DashboardCard
            title="Total Students"
            value={loading ? "..." : stats.totalStudents}
            icon={<PeopleIcon />}
            color="#4b2dbf"
          />
        </Grid>

        <Grid size={{ xs: 12, md: 6, lg: 3 }}>
          <DashboardCard
            title="Total Faculty"
            value={loading ? "..." : stats.totalFaculty}
            icon={<SchoolIcon />}
            color="#00b894"
          />
        </Grid>

        <Grid size={{ xs: 12, md: 6, lg: 3 }}>
          <DashboardCard
            title="Courses"
            value={loading ? "..." : stats.totalCourses}
            icon={<MenuBookIcon />}
            color="#0984e3"
          />
        </Grid>

        <Grid size={{ xs: 12, md: 6, lg: 3 }}>
          <DashboardCard
            title="Revenue"
            value={loading ? "..." : `₹${stats.totalRevenue}`}
            icon={<PaymentsIcon />}
            color="#e17055"
          />
        </Grid>

      </Grid>

      {/* CHARTS */}

      <Grid container spacing={3} sx={{ mt: 2 }}>

        <Grid size={{ xs: 12, md: 8 }}>
          <Paper sx={{ p: 3, borderRadius: 3 }}>
            <Typography variant="h6" mb={2}>
              Student Growth
            </Typography>

            <StudentGrowthChart />
          </Paper>
        </Grid>

        <Grid size={{ xs: 12, md: 4 }}>
          <Paper sx={{ p: 3, borderRadius: 3 }}>
            <Typography variant="h6" mb={2}>
              Revenue Overview
            </Typography>

            <RevenueChart />
          </Paper>
        </Grid>

      </Grid>

      {/* TABLE + ACTIONS */}

      <Grid container spacing={3} sx={{ mt: 2 }}>

        <Grid size={{ xs: 12, md: 8 }}>
          <Paper sx={{ p: 3, borderRadius: 3 }}>
            <Typography variant="h6" mb={2}>
              Recent Registrations
            </Typography>

            <RecentUsersTable />
          </Paper>
        </Grid>

        {/* <Grid size={{ xs: 12, md: 4 }}>
          <Paper sx={{ p: 3, borderRadius: 3 }}>
            <Typography variant="h6" mb={2}>
              Quick Actions
            </Typography>

            <AdminQuickActions />
          </Paper>
        </Grid> */}

      </Grid>

    </Box>
  );
}